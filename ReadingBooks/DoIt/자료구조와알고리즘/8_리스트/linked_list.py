# 포인터로 연결 리스트 구현하기 

from __future__ import annotations 
from typing import Any, Type 

class Node : 
    """연결 리스트용 노드 클래스"""

    """초기화"""
    def __init__(self, data:Any = None, next: Node = None):
        self.data = data       # 데이터에 대한 참조 
        self.next = next       # 뒤쪽 노드에 대한 참조 

class LinkedList : 
    """연결 리스트 클래스 """

    def __init__(self)->None:
        self.no = 0         # 노드의 개수
        self.head = None    # 머리 노드
        self.current = None # 주목 노드 

    def __len__(self)->int:
        return self.no 


    # 종료 조건 1 : 검색 조건을 만족하는 노드를 발견하지 못하고 꼬리 노드까지 왔을 경우 
    # 종료 조건 2 : 검색 조건을 만족하는 노드를 발견한 경우 
    def search(self, data:Any) -> int:
        """data와 값이 같은 노드를 검색"""
        cnt = 0 
        ptr = self.head 
        while ptr is not None : 
            if ptr.data == data:
                self.current = ptr 
                return cnt 
            cnt += 1 
            ptr = ptr.next 
        return -1 


    def __contains__(self, data:Any)->bool:
        """연결 리스트에 data가 포함되어 있는지 확인 """
        return self.search(data) >= 0 
    
    def add_first(self, data:Any)->None:
        """맨앞에 노드를 삽입 p330"""
        ptr = self.head # 삽입하기 전의 머리 노드 
        self.head = self.current = Node(data,ptr) 
        self.no += 1 

    def add_last(self, data:Any) :
        """맨 끝에 노드 삽입"""
        if self.head is None : # 리스트가 비어 있으면 
            self.add_first(data) # 맨 앞에 노드 삽입 
        else : 
            ptr = self.head 
            while ptr.next is not None : 
                ptr = ptr.next 
            # ptr = None 을 찾으면 Node 생성해서 넣음 (해당 Node가 마지막이니 next는 None)
            ptr.next = self.current = Node(data, None)
            self.no += 1 

    def remove_first(self)->None:
        """머리 노드 삭제"""
        if self.head is not None : #리스트가 비어 있지 않으면  
            self.head = self.current = self.head.next 
        self.no -= 1

    def remove_last(self):
        """꼬리 노드 삭제"""
        if self.head is not None : 
            if self.head.next is None: # 노드가 1개 뿐이라면
                self.remove_first() # 머리 노드 삭제 
            else : 
                ptr = self.head     # 스캔중인 노드
                pre = self.head     # 스캔중인 노드의 앞쪽 노드 

                while ptr.next is not None :  # next가 None인 앞의 노드 찾음 
                    pre = ptr 
                    ptr = ptr.next 
                pre.next = None 
                self.current = pre 
                self.no -= 1 

    def remove(self, p:Node)->None:
        """임의 노드 p 삭제"""
        if self.head is not None : 
            if p is self.head :  # p가 머리노드이면 
                self.remove_first() # 머리 노드 삭제
            else : 
                ptr = self.head 

                while ptr.next is not p : 
                    ptr = ptr.next 
                    if ptr is None : 
                        return        # 다음 노드가 None이면 종료 
                # ptr.next가 p와 같아지면 while문 종료 (p336)
                ptr.next = p.next       
                self.current = ptr 
                self.no -= 1 

    def remove_current_node(self)->None:
        """주목 노드 삭제"""
        self.remove(self.current)

    def clear(self) -> None : 
        """전체 노드를 삭제"""
        while self.head is not None :  # 전체가 비어 있을 때까지 
            self.remove_first()      # 머리노드 삭제 
        self.current = None 
        self.no = 0 

    def next(self) -> bool:
        """주목 노드를 한칸 뒤로 이동"""
        if self.current is None or self.current.next is None : 
            return False      # 이동 불가 
        self.current = self.current.next 
        return True 

    def print_current_node(self)->None:
        """주목 노드를 출력"""
        if self.current is None : 
            print("주목 노드가 존재하지 않습니다.")
        else : 
            print(self.current.data)

    def print(self) ->None:
        """모든 노드를 출력"""
        ptr = self.head 
        while ptr is not None : 
            print(ptr.data)
            ptr = ptr.next 

    def __iter__(self)-> LinkedListIterator:
        """이터레이터를 반환"""
        return LinkedListIterator(self.head)

class LinkedListIterator : # p339 참조 
    """ 클래스 LinkedList의 이터레이터용 클래스 """
    def __init__(self, head:Node):
        self.current = head 

    def __iter__(self) -> LinkedListIterator:
        return self 

    def __next__(self)->Any:
        if self.current is None : 
            raise StopIteration
        else : 
            data = self.current.data 
            self.current = self.current.next 
            return data 