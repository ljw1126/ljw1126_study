# 힙 정렬 알고리즘 구현하기 (heapq.push와 heapq.pop 사용)

import heapq
from typing import MutableSequence

def heap_sort(a:MutableSequence) -> None : 
    heap = []
    for i in a : 
        heapq.heappush(heap,i)
    for i in range(len(a)):
        a[i] = heapq.heappop(heap)
    # 이하 생략     