#  체인법으로 해시 함수 구현하기 

# 아래 future 같은 경우 08-2 에서 배움
from __future__ import annotations      
from typing import Any, Type 
import hashlib 

"""해시를 구성하는 노드"""
class Node : 

    """ 초기화 """
    def __init__(self, key:Any, value:Any, next:Node) -> None:
        self.key = key         # 키 
        self.value = value     # 값
        self.next = next       # 뒤쪽 노드 참조

"""체인법으로 해시 클래스 구현"""
class ChainedHash: 

    def __init__(self, capacity:int) -> None:
        self.capacity = capacity            # 해시 테이블의 크기 지정
        self.table = [None] * self.capacity # 해시 테이블(리스트)를 선언 

    """해시값을 구함 """
    def hash_value(self, key:Any) -> int : 

        if isinstance(key,int):
            return key % self.capacity  # int인 경우 
        return (int(hashlib.sha256(str(key).encode()).hexdigest(),16) % self.capacity) # 문자인경우

    """키가 key인 원소를 검색하여 값을 반환 """
    def search(self, key:Any) -> Any:
        hash = self.hash_value(key) # 검색하는 키의 해시값 
        p = self.table[hash]        # 노드를 주목 

        while p is not None : 
            if p.key == key:
                return p.value     # 검색성공
            p = p.next             # 뒤쪽 노드를 주목 
        return None                # 검색 실패 

    """키가 key이고 값이 value인 원소를 추가"""
    def add(self, key:Any, value:Any) -> bool :
        hash = self.hash_value(key)  # 추가하는 key의 해시값 
        p = self.table[hash] # 노드를 주목 

        while p is not None :
            if p.key == key : 
                return False  #추가실패 
            p = p.next          # 뒤쪽 노드를 주목 
        
        # 해당 인덱스가 None인 경우 self.table[hash] 는 None 해당 
        # 해당 인덱스 값이 있는 경우 self.table[hash] 는 특정 Node 가르킴 ( 신규 None -> Node -> None )
        temp = Node(key, value, self.table[hash])
        self.table[hash] = temp # 노드를 추가 
        return True # 추가 설공 

    """키가 key인 원소를 삭제"""
    def remove(self, key:Any) -> bool : 
        hash = self.hash_value(key)  # 삭제할 key의 해시값
        p = self.table[hash]         # 노드를 주목
        pp = None                    # 바로 앞의 노드 주목 

        # 예시 그림 참고해서 값이 3개 있을때 각각 삭제하는 경우를 생각해보기! 
        while p is not None : 
            if p.key == key : 
                if pp is None : 
                    self.table[hash] = p.next 
                else : 
                    pp.next = p.next 
                return True      # key 삭제 성공 
            pp = p 
            p = p.next      # 뒤쪽 노드를 주목 
        return False    # 삭제 실패 ( key )가 존재않함 

    """해시 테이블을 덤프 ( 그냥 내용 출력 ) """
    def dump(self) -> None : 
        for i in range(self.capacity):
            p = self.table[i]
            print(i , end = '')
            while p is not None :
                print(f' › {p.key} ({p.value})' , end = '')
                p = p.next 
            print()
