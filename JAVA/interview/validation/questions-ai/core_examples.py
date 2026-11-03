"""Provider-neutral, bounded exercises for QB-AI-001 and QB-AI-002."""
from contextlib import contextmanager


@contextmanager
def managed_client(acquire, release):
    """Acquire once; release once after entry, including body exceptions."""
    client = acquire()
    try:
        yield client
    finally:
        release(client)


class TransientError(Exception):
    """Synthetic failure explicitly eligible for retry in this exercise."""


def retry_read(operation, sleep, *, attempts=3):
    """Retry a caller-declared safe read; injected sleep uses logical seconds."""
    if type(attempts) is not int or not 1 <= attempts <= 5:
        raise ValueError("attempts must be an integer from 1 through 5")
    for index in range(attempts):
        try:
            return operation()
        except TransientError:
            if index + 1 == attempts:
                raise
            sleep(2 ** index)
