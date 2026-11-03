"""Regression contracts for the local fragment checker's declared slug subset.

Extract the actual function rather than maintaining a second implementation.
These fixtures are not a universal Markdown-renderer compatibility claim.
"""
import ast
from collections import Counter
import html
from pathlib import Path
import re
import unicodedata

source = Path(__file__).with_name('check_library.py')
tree = ast.parse(source.read_text())
function = next(node for node in tree.body if isinstance(node, ast.FunctionDef) and node.name == 'anchors')
scope = {'Counter': Counter, 're': re, 'html': html, 'unicodedata': unicodedata}
exec(compile(ast.Module(body=[function], type_ignores=[]), str(source), 'exec'), scope)
anchors = scope['anchors']
cases = [
    ('# read_user', {'read_user'}),
    ('# `read_user`', {'read_user'}),
    ('# _Read_ **User**', {'read-user'}),
    ('# __Read__ user_id', {'read-user_id'}),
    ('# Read User\n# Read User', {'read-user', 'read-user-1'}),
    ('<a id="explicit"></a>\n## Name', {'explicit', 'name'}),
    ('```html\n<a id="fake"></a>\n# hidden\n```\n# Visible', {'visible'}),
    ('~~~md\n# hidden\n~~~\n# Visible', {'visible'}),
    ('````md\n```\n# hidden\n````\n# Visible', {'visible'}),
    ('# [Read](topic.md) &amp; write', {'read--write'}),
    ('# Café', {'café'}),
    ('# <em>Read</em> user', {'read-user'}),
]
for text, expected in cases:
    observed = anchors(text)
    if observed != expected:
        raise AssertionError((text, observed, expected))
print(f'PASS: {len(cases)} local anchor regression contracts; no universal renderer proof.')
