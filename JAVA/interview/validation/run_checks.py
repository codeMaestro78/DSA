"""Run the local knowledge-base suites with explicit result/proof boundaries.

No downloads, network, servers or live credentials. Optional --output saves JSON.
Native SQL and browser checks require their separate environment/permissions and
are not silently counted as passes here. Java compilation stays in a temp dir.
"""
from pathlib import Path
import argparse
import json
import os
import subprocess
import sys
import tempfile

ROOT = Path(__file__).resolve().parents[2]

def main():
    parser = argparse.ArgumentParser()
    parser.add_argument('--output', type=Path)
    args = parser.parse_args()
    env = {**os.environ, 'PYTHONDONTWRITEBYTECODE': '1'}
    results = []
    def run(name, command):
        try:
            result = subprocess.run(command, cwd=ROOT, env=env, capture_output=True,
                                    text=True, timeout=120)
            record = {'suite': name, 'status': 'pass' if result.returncode == 0 else 'fail',
                      'command': command, 'output': result.stdout + result.stderr}
        except (OSError, subprocess.TimeoutExpired) as error:
            record = {'suite': name, 'status': 'fail', 'command': command, 'output': str(error)}
        results.append(record)
        print(record['status'].upper() + ': ' + name)
        print(record['output'].strip())
        return record['status'] == 'pass'
    with tempfile.TemporaryDirectory(prefix='interview-final-java-') as directory:
        ok = run('DSA Java compilation', ['javac', '--release', '17', '-d', directory,
                 'interview/validation/dsa/CoreAlgorithms.java',
                 'interview/validation/dsa/CoreAlgorithmsTest.java'])
        if ok:
            run('DSA Java contracts', ['java', '-cp', directory, 'CoreAlgorithmsTest'])
    suites = [
        ('Design arithmetic', 'design/check_capacity.py'),
        ('AI helper contracts', 'ai/check_examples.py'),
        ('ML numerical examples', 'ml/check_ml.py'),
        ('AI design arithmetic', 'ai-design/check_architecture.py'),
        ('Question core examples', 'questions-core/check_examples.py'),
        ('Question AI helpers', 'questions-ai/check_examples.py'),
        ('Question quantile fixture', 'questions-review/check_reasoning.py'),
        ('Mock core examples', 'mocks-core/check_examples.py'),
        ('Mock AI fixtures', 'mocks-ai/check_examples.py'),
        ('Mock design arithmetic', 'mocks-design/check_fixtures.py'),
        ('Question index', 'check_questions.py'),
        ('Mock index', 'check_mocks.py'),
        ('Research bookkeeping', 'check_research.py'),
        ('Document structure', 'check_documents.py'),
        ('Whole-library coverage', 'check_library.py'),
        ('Local anchor regression contracts', 'check_anchor_contract.py'),
    ]
    for name, relative in suites:
        run(name, [sys.executable, '-B', 'interview/validation/' + relative])
    result = {'scope': 'Local code, arithmetic and document checks only', 'results': results,
              'not_executed': ['Native PostgreSQL suites: separate disposable-server setup required',
                               'Browser rendering: separate browser setup/permission required',
                               'Hosted providers, training, cloud, payment and production systems']}
    if args.output:
        args.output.write_text(json.dumps(result, indent=2) + '\n')
    failed = [item['suite'] for item in results if item['status'] != 'pass']
    print(f'{len(results)-len(failed)}/{len(results)} local suite/compilation steps passed; '
          'separate unexecuted checks are not passes.')
    return bool(failed)

if __name__ == '__main__':
    raise SystemExit(main())
