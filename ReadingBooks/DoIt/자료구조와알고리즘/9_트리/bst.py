# 이진 검색 트리 구현하기

from __future__ import annotations 
from typing import Any, Type 

class Node : 
    """이진 검색 트리의 노드"""
    def __init__(self, key:Any, value:Any, left : Node = None,
    right:Node = None) :
        """생성자(constructor)"""
        self.key = key      # 키
        self.value = value  # 값
        self.left = left    # 왼쪽 포인터 
        self.right = right  # 오른쪽 포인터 