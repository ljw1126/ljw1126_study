# 원형 이중 연결리스트 구현하기 (p358)

from __future__ import annotations 
from typing import Any, Type 

class Node : 
    """원형 이중 연결 리스트용 노드 클래스"""

    def __init__(self, data:Any = None, prev:Node = None, next:Node = None)->None:
        """초기화"""
        self.data = data            # 데이터 
        self.prev = prev or self    # 앞쪽 포인터
        self.next = next or self    # 뒤쪽 포인터

class DoubleLinkedList:
    """원형 이중 연결 리스트 클래스"""

    def __init__(self) -> None:
        self.head = self.current = Node() # 더미 노드 생성 
        self.no = 0 

    def __len__(self) -> None : 
        """연결 리스트의 노드 수를 반환"""
        return self.no 

    """
    head.next가 더미 노드인 head 참조하면 리스트가 비어 있는 상태 
    """
    def is_empty(self) -> bool : 
        """리스트가 비었는지 확인"""
        return self.head.next is self.head # self.head.next 가 자기 자신(self.head , 더미노드)인 경우 

    def search(self, data:Any)->Any:
        """data와 값이 같은 노드를 검색"""
        cnt = 0 
        ptr = self.head.next # 현재 스캔 중인 노드 , 더미노드 다음 노드부터
        while ptr is not self.head : 
            if data == ptr.data : 
                self.current = ptr 
                return cnt          # 검색 성공 
            cnt += 1 
            ptr = ptr.next  # 뒤쪽 노드에 주목 
        return -1  # 검색 실패 

    def __contain__(self, data:Any) -> bool : 
        """연결 리스트에 data가 포함되어 있는지 판단"""
        return self.search(data) >= 0 

    def print_current_node(self) -> None:
        """주목 노드를 출력"""
        if self.is_empty():
            print('주목 노드는 없습니다.')
        else : 
            print(self.current.data)

    def print(self) -> None:
        """모든 노드를 출력"""
        ptr = self.head.next # 더미 노드의 뒤쪽 노드 
        while ptr is not self.head : 
            print(ptr.data) 
            ptr = ptr.next 

    def print_reverse(self)->None:
        """모든 노드를 역순으로 출력"""
        ptr = self.head.prev    # 더미 노드의 앞쪽 노드 
        while ptr is not self.head : 
            print(ptr.data)
            ptr = ptr.prev 
        
    def next(self) -> bool:
        """주목 노드를 한 칸 뒤로 이동"""
        if self.is_empty() or self.current.next is self.head : 
            return False   # 이동할 수 없음 
        self.current = self.current.next 
        return True 

    def prev(self) -> bool : 
        """주목 노드를 한 칸 앞으로 이동"""
        if self.is_empty() or self.current.prev is self.head : 
            return False  # 이동할 수 없음
        self.current = self.current.prev 
        return True 

    def add(self, data:Any)->None: 
        """주목 노드 바로 뒤에 노드 삽입"""
        node = Node(data, self.current, self.current.next)
        self.current.next.prev = node  # 현시점에 self.current.next는 더미노드 자기 자신
        self.current.next = node 
        self.currnet = node 
        self.no += 1 

    def add_first(self, data:Any)->None:
        """맨 앞에 노드를 삽입"""
        self.current = self.head # 더미 노드(self.head)의 바로 뒤에 삽입 
        self.add(data)

    def add_last(self, data:Any)->None: 
        """맨 뒤에 노드를 삽입"""
        self.current = self.head.prev # 꼬리 노드(self.head.prev)의 바로 뒤에 삽입 , self.head.prev 의 경우 마지막 노드 또는 자기자신(더미노드) 가르킴
        self.add(data)

    def remove_current_node(self)->None:
        """주목 노드 삭제"""
        if not self.is_empty():
            self.current.prev.next = self.current.next
            self.current.next.prev = self.current.prev 
            self.current = self.current.prev 
            self.no -= 1 
            if self.current is self.head : 
                self.current = self.head.next 

    def remove(self, p:None)->None: 
        """노드 p를 삭제"""
        ptr = self.head.next # 더미노드.next 부터 시작해서 검색 

        while ptr is not self.head : 
            if ptr is p : # 찾을 경우
                self.current = p    # self.current 주목 노드를 p로 변경
                self.remove_current_node() # 주목 노드 삭제  
                break 
            ptr = ptr.next 

    def remove_first(self) -> None:
        """머리 노드(=더미노드 다음노드) 삭제"""
        self.current = self.head.next       # 머리 노드 head.next를 삭제 
        self.remove_current_node()

    def remove_last(self)->None: 
        """꼬리 노드 삭제"""
        self.current = self.head.prev  # 꼬리 노드 head.prev 를 삭제 
        self.remove_current_node() 

    def clear(self)->None:
        """모든 노드를 삭제"""
        while not self.is_empty():  # 리스트 전체가 빌 때까지 
            self.remove_first()     # 머리 노드를 삭제 
        self.no = 0 


    def __iter__(self) -> DoubleLinkedListIterator:
        """이터레이터를 반환"""
        return DoubleLinkedListIterator(self.head)

    def __reversed__(self) -> DoubleLinkedListReverseIterator : 
        """내림차순 이터레이터를 반환"""
        return DoubleLinkedListReverseIterator(self.head)

class DoubleLinkedListIterator :
    """DoubleLinkedList 의 이터레이터용 클래스 """

    def __init__(self, head:Node):
        self.head = head 
        self.current = head.next 

    def __iter__(self)-> DoubleLinkedListIterator:
        return self 

    def __next__(self)->Any: 
        if self.current is self.head : 
            raise StopIteration
        else : 
            data = self.current.data 
            self.current = self.current.next 
            return data 

class DoubleLinkedListReverseIterator:
    """ DoubleLinkedList의 내림차순 이터레이터 클래스"""
    def __init__(self, head:Node):
        self.head = head 
        self.current = head.prev 

    def __iter__(self)-> DoubleLinkedListReverseIterator:
        return self 

    def __next__(self)->Any: 
        if self.current is self.head : 
            raise StopIteration
        else : 
            data = self.current.data 
            self.current = self.current.prev 
            return data 
