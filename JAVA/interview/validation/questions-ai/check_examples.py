"""Focused standard-library tests; no provider, network, or actual sleep."""
import pathlib
import sys
import unittest

sys.dont_write_bytecode = True
from core_examples import TransientError, managed_client, retry_read


class ContextTests(unittest.TestCase):
    def test_success_identity_and_one_release(self):
        client, closed = object(), []
        with managed_client(lambda: client, closed.append) as obtained:
            self.assertIs(obtained, client)
            self.assertEqual(closed, [])
        self.assertEqual(closed, [client])

    def test_body_exception_propagates_after_release(self):
        failure, client, closed = ValueError("body"), object(), []
        with self.assertRaises(ValueError) as caught:
            with managed_client(lambda: client, closed.append):
                raise failure
        self.assertIs(caught.exception, failure)
        self.assertEqual(closed, [client])

    def test_acquisition_failure_does_not_release(self):
        closed = []
        def fail():
            raise LookupError("acquire")
        with self.assertRaises(LookupError):
            with managed_client(fail, closed.append):
                self.fail("body entered")
        self.assertEqual(closed, [])

    def test_release_failure_is_visible(self):
        def fail(_):
            raise RuntimeError("release")
        with self.assertRaisesRegex(RuntimeError, "release"):
            with managed_client(object, fail):
                pass


class RetryTests(unittest.TestCase):
    def test_success_no_sleep(self):
        waits = []
        self.assertEqual(retry_read(lambda: 0, waits.append), 0)
        self.assertEqual(waits, [])

    def test_two_transients_then_success(self):
        calls, waits = [], []
        def operation():
            calls.append(1)
            if len(calls) < 3:
                raise TransientError()
            return "ok"
        self.assertEqual(retry_read(operation, waits.append), "ok")
        self.assertEqual((len(calls), waits), (3, [1, 2]))

    def test_exhaustion_preserves_last_error_and_has_no_final_wait(self):
        errors = [TransientError(str(i)) for i in range(3)]
        calls, waits = [], []
        def operation():
            calls.append(1)
            raise errors[len(calls)-1]
        with self.assertRaises(TransientError) as caught:
            retry_read(operation, waits.append)
        self.assertIs(caught.exception, errors[-1])
        self.assertEqual((len(calls), waits), (3, [1, 2]))

    def test_permanent_error_not_retried(self):
        calls, waits = [], []
        def operation():
            calls.append(1)
            raise PermissionError("denied")
        with self.assertRaises(PermissionError):
            retry_read(operation, waits.append)
        self.assertEqual((len(calls), waits), (1, []))

    def test_one_attempt(self):
        waits = []
        with self.assertRaises(TransientError):
            retry_read(lambda: (_ for _ in ()).throw(TransientError()),
                       waits.append, attempts=1)
        self.assertEqual(waits, [])

    def test_five_attempt_upper_bound(self):
        calls, waits = [], []
        def operation():
            calls.append(1)
            raise TransientError()
        with self.assertRaises(TransientError):
            retry_read(operation, waits.append, attempts=5)
        self.assertEqual((len(calls), waits), (5, [1, 2, 4, 8]))

    def test_invalid_attempts_do_no_work(self):
        for invalid in (0, -1, 6, True, False, 2.0, "3", None):
            with self.subTest(invalid=invalid):
                calls, waits = [], []
                with self.assertRaises(ValueError):
                    retry_read(lambda: calls.append(1), waits.append,
                               attempts=invalid)
                self.assertEqual((calls, waits), ([], []))

    def test_sleep_failure_propagates(self):
        calls = []
        def operation():
            calls.append(1)
            raise TransientError()
        def sleep(_):
            raise RuntimeError("timer")
        with self.assertRaisesRegex(RuntimeError, "timer"):
            retry_read(operation, sleep)
        self.assertEqual(len(calls), 1)

    def test_published_source_parity(self):
        source = pathlib.Path(__file__).with_name("core_examples.py").read_text()
        bank = pathlib.Path(__file__).resolve().parents[2] / "12-Interview-Question-Bank/AI_Engineer_Questions.md"
        codeblocks = bank.read_text().split("```python\n")[1:]
        self.assertEqual(len(codeblocks), 2)
        for block in codeblocks:
            self.assertIn(block.split("```", 1)[0].rstrip(), source)


if __name__ == "__main__":
    unittest.main(verbosity=2)
