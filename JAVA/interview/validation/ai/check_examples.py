"""Deterministic reference/property checks for authored educational functions."""
from itertools import product
import math
from pathlib import Path
import random
import sys
sys.dont_write_bytecode = True
from core_examples import chunks, cosine, rrf, ranking_metrics, bounded_loop

checks = 0

def check(condition, label):
    global checks
    if not condition:
        raise AssertionError(label)
    checks += 1

def raises(error, fn, label):
    try:
        fn()
    except error:
        check(True, label)
    else:
        raise AssertionError(label)

check(chunks('abcdefghij', 4, 1) == [(0,4,'abcd'),(3,7,'defg'),(6,10,'ghij')], 'offset trace')
check(chunks('',4) == [], 'empty text')
check(chunks('a😀b',2,1) == [(0,2,'a😀'),(1,3,'😀b')], 'code points not UTF16')
for size in range(1, 9):
    for overlap in range(size):
        for length in range(21):
            text = 'x' * length
            out = chunks(text,size,overlap)
            check(all(text[a:b] == t and 0 < b-a <= size for a,b,t in out), 'source slices')
            covered = {i for a,b,t in out for i in range(a,b)}
            check(covered == set(range(length)), 'coverage')
            check(not out or out[-1][1] == length, 'last offset')
for args in [(0,0),(4,4),(3,-1),(3,4)]:
    raises(ValueError, lambda args=args: chunks('abc',*args), 'invalid chunk config')
check(math.isclose(cosine([1,0],[0,1]),0), 'orthogonal')
check(math.isclose(cosine([1,2],[2,4]),1), 'parallel')
check(math.isclose(cosine([1,0],[-1,0]),-1), 'opposite')
raises(ValueError,lambda:cosine([0,0],[1,2]),'zero norm')
raises(ValueError,lambda:cosine([1],[1,2]),'dimension mismatch')
raises(ValueError,lambda:cosine([float('nan')],[1]),'NaN coordinate')
raises(ValueError,lambda:cosine([10**1000],[1]),'unrepresentable coordinate')
rng=random.Random(20261102)
for _ in range(300):
    a=[rng.uniform(-10,10) for _ in range(5)]
    b=[rng.uniform(-10,10) for _ in range(5)]
    oracle=sum(x*y for x,y in zip(a,b))/math.sqrt(sum(x*x for x in a)*sum(y*y for y in b))
    check(math.isclose(cosine(a,b),oracle,abs_tol=1e-12),'cosine independent formula')
    check(math.isclose(cosine(a,b),cosine(b,a),abs_tol=1e-12),'cosine symmetry')
ranked=rrf([['a','b'],['b','c']])
check([x for x,_ in ranked]==['b','a','c'],'known RRF ranking')
check(math.isclose(dict(ranked)['b'],1/62+1/61),'known RRF score')
check(rrf([['b'],['a']])==[('a',1/61),('b',1/61)],'deterministic ties')
raises(ValueError,lambda:rrf([['a','a']]),'duplicate branch')
raises(ValueError,lambda:rrf([['a']],-1),'negative smooth')
raises(TypeError,lambda:rrf([iter(['a'])]),'branch generator rejected')
check(rrf([['a']],1_000_000)==[('a',1/1_000_001)],'maximum teaching smoothing')
raises(ValueError,lambda:rrf([['a']],1_000_001),'smoothing above teaching bound')
raises(ValueError,lambda:rrf([['a']],10**1000),'huge smoothing rejected before float conversion')
raises(ValueError,lambda:rrf([['a']],True),'boolean smoothing rejected')
m=ranking_metrics(['a','x','b'],{'a':3,'b':1},3)
check(math.isclose(m['precision'],2/3),'precision')
check(m['recall']==1 and m['rr']==1,'recall and rr')
check(math.isclose(m['ndcg'],7.5/(7+1/math.log2(3))), 'graded NDCG trace')
check(ranking_metrics(['x','a'],{'a':1},2)['rr']==0.5,'rr second')
check(ranking_metrics(['a'],{'a':1},3)['precision']==1/3,'short list convention')
check(ranking_metrics([],{'a':1},2)['ndcg']==0,'empty ranking with gold')
check(ranking_metrics([],{},2)['ndcg'] is None,'no gold undefined')
raises(ValueError,lambda:ranking_metrics(['a','a'],{'a':1},2),'duplicate metric ID')
raises(ValueError,lambda:ranking_metrics([],{'a':-1},1),'negative grade')
raises(ValueError,lambda:ranking_metrics([],{'a':10**1000},1),'unbounded integer grade')
raises(ValueError,lambda:ranking_metrics([],{},0),'zero cutoff')
for permutation in product('abc',repeat=3):
    if len(set(permutation))!=3: continue
    result=ranking_metrics(list(permutation),{'a':3,'b':2,'c':1},3)
    check(0<=result['ndcg']<=1+1e-12,'bounded NDCG')
call={'type':'call','id':'one','tool':'sum','args':{'x':2,'y':3}}
finish={'type':'final','text':'5'}
check(bounded_loop([call,finish],{'sum'})['results']==[{'id':'one','value':5}],'safe call')
check(bounded_loop([call,call,finish],{'sum'})['results']==[{'id':'one','value':5}]*2,'replay result')
raises(PermissionError,lambda:bounded_loop([call],set()),'allowlist')
raises(ValueError,lambda:bounded_loop([{**call,'args':{'x':True,'y':2}}],{'sum'}),'bool is not numeric contract')
raises(ValueError,lambda:bounded_loop([{**call,'args':{'x':10**1000,'y':2}}],{'sum'}),'unbounded integer argument')
raises(ValueError,lambda:bounded_loop([{**call,'args':{'x':2,'y':float('inf')}}],{'sum'}),'nonfinite args')
raises(ValueError,lambda:bounded_loop([call,{**call,'args':{'x':4,'y':3}}],{'sum'}),'payload replay conflict')
raises(ValueError,lambda:bounded_loop([{**call,'extra':1}],{'sum'}),'extra fields')
check(bounded_loop([call,finish],{'sum'},max_steps=1)['status']=='budget_or_input_exhausted','bounded steps')
check(bounded_loop([call],{'sum'},cancelled=lambda:True)['status']=='cancelled','cooperative cancel')
# Verify every published Python block in the linked lesson is exactly the tested source.
source=(Path(__file__).parent/'core_examples.py').read_text()
chapter=(Path(__file__).parents[2]/'08-LLM-RAG-and-Agent-Engineering'/'Tool_Calling.md').read_text()
import re
blocks=re.findall(r'```python\n(.*?)\n```',chapter,re.S)
check(len(blocks)==1 and blocks[0].strip() in source,'published call validator source parity')
print(f'PASS: {checks} checks; Python {sys.version.split()[0]}; fixed seed20261102; no provider/model calls.')
