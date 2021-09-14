# 커서로 연결 리스트 구현하기 

from __future__ import annotations 
from typing import Any, Type 

Null = -1 

class Node :
    """연결 리스트용 노드 클래스(배열 커서 버전)"""

    def __init__(self, data=Null, next= Null, dnext = Null):
        """초기화"""
        self.data = data 
        self.next = next        # 리스트의 뒤쪽 포인터
        self.dnext = dnext      # 프리 리스트의 뒤쪽 포인터 p351참조 

class ArrayLinkedList:
    """연결 리스트 클래스(배열 커서 버전)"""

    def __init__(self, capacity:int):
        """초기화"""
        self.head = Null                    # 머리 노드 
        self.current = Null                 # 주목 노드
        self.max = Null                     # 사용 중인 꼬리 레코드
        self.deleted = Null                 # 꼬리 리스트의 머리 노드 
        self.capacity = capacity            # 리스트의 크기 
        self.n = [Node()] * self.capacity   # 리스트 본체 
        self.no = 0 

    def __len__(self) -> int:
        """연결 리스트의 노드 수를 반환"""
        return self.no 


    """
    get_insert_index() 호출시 
    1. self.deleted == Null >> 삭제된 인덱스 없는 경우 ( self.deleted == -1 )
       > 인덱스 리턴
       > 지정된 배열 사이즈 초과시 -1 리턴         
    2. else : 삭제된 인덱스 존재하는 경우 ( deleted )
       self.deleted 값을 리턴하고 self.deleted 갱신 (없는 경우 -1)
    """
    def get_insert_index(self):
        """다음에 삽입할 레코드의 인덱스를 구함"""
        if self.deleted == Null :
            if self.max + 1 < self.capacity : # 크기 초과 여부 판별 
                self.max += 1 
                return self.max               # 새 레코드 사용
            else : 
                return Null                   # 크기 초과
        else : 
            rec = self.deleted 
            self.deleted = self.n[rec].dnext  # 프리 리스트에서 맨앞 rec를 꺼내기 
            return rec 

    def delete_index(self, idx:int) -> None :
        """레코드 idx를 프리 리스트에 등록"""
        # idx -> -1
        if self.deleted == Null :          # 삭제 레코드는 존재하지 않음 
            self.deleted = idx 
            self.n[idx].dnext = Null       # idx를 프리 리스트의 맨 앞에 등록   
        # idx -> 이전idx -> -1 
        else : 
            rec = self.deleted 
            self.deleted = idx             # idx를 프리 리스트의 맨 앞에 삽입  
            self.n[idx].dnext = rec 

    def search(self, data:Any) -> int :
        """data와 값이 같은 노드를 검색"""
        cnt = 0 
        ptr = self.head 
        while ptr != Null : 
            if self.n[ptr].data == data : 
                self.current = ptr 
                return cnt              # 검색 성공 
            cnt += 1 
            ptr = self.n[ptr].next      # 뒤쪽 노드에 주목 
        return Null                     # 검색 실패
            
    def __contains__(self, data:Any):
        """연결 리스트에 data가 포함되어 있는지 확인"""
        return self.search(data) >= 0 

    def add_first(self, data:Any):
        """머리 노드에 삽입"""
        ptr = self.head 
        rec = self.get_insert_index() 
        if rec != Null : # 배열 데이터가 사이즈 넘지 않는 경우
            # head와 current 포인터 이동 
            self.head = self.current = rec
            # 배열 rec번째 (레코드)에 삽입
            self.n[self.head] = Node(data, ptr)
            self.no += 1 


    def add_last(self, data:Any) -> None:
        """꼬리 노드에 삽입"""
        if self.head == Null :  # 리스트가 비어 있으면 
            self.add_first(data) # 맨 앞에 노드 삽입 
        else : 
            ptr = self.head 
            while self.n[ptr].next != Null :
                ptr = self.n[ptr].next 
            rec = self.get_insert_index()

            if rec != Null : 
                # -1 가르켰던 노드의 next를 갱신시켜 줌 
                self.n[ptr].next = self.current = rec 
                self.n[rec] = Node(data)
                self.no += 1 

    def remove_first(self) -> None:
        """머리 노드를 삭제"""
        if self.head != Null : 
            ptr = self.n[self.head].next 

            # self.head (인덱스)에 해당하는 삭제시 Node의 dnext와 deleted 갱신 
            # 7 -> 3 -> -1 순으로 LIFO (7번 부터 채워짐)
            self.delete_index(self.head)
            
            self.head = self.current = ptr 
            self.no -= 1 

    def remove_last(self) -> None:
        """꼬리 노드를 삭제"""
        if self.head != Null : 
            if self.n[self.head].next == Null : # 노드가 1개 뿐이면 
                self.remove_first() 
            else :
                pre = self.head     # 스캔 중인 노드의 앞쪽 노드 
                ptr = self.head     # 스캔 중인 노드
               
                while self.n[ptr].next != Null :
                    pre = ptr # -1 앞쪽 노드의 인덱스  
                    ptr = self.n[ptr].next # -1이 되는 노드를 가진 인덱스
                self.n[pre].next = Null # pre는 삭제한 뒤의 고리 노드 
                self.delete_index(ptr)
                self.current = pre 
                self.no -= 1 

    def remove(self, p:int) -> None:
        """레코드 p(인덱스)를 삭제""" 
        if self.head != Null : 
            if p == self.head : # head인 경우 
                self.remove_first()       # p가 머리 노드면 머리 노드를 삭제 (self.head에 머리노드 index 있음)
            else : # head가 아닌 경우 
                ptr = self.head           # head부터 시작하여 다음 노드 번호를 구해서 조회 

                while self.n[ptr].next != p : 
                    ptr = self.n[ptr].next 
                    if ptr == Null : 
                        return          # p는 리스트에 존재하지 않음 (삭제 실패)
                self.n[ptr].next = Null 
                self.delete_index(p)
                self.n[ptr].next = self.n[p].next 
                self.current = ptr 
                self.no -= 1 

    def remove_current_node(self) -> None:
        """주목 노드를 삭제"""
        self.remove(self.current)

    def clear(self) -> None : 
        """모든 노드를 삭제"""
        while self.head != Null : # 리스트 전체가 빌때까지 
            self.remove_first() 
        self.current = Null 

    def next(self) -> bool:
        """주목 노드를 한 칸 뒤로 이동"""
        if self.current == Null or self.n[self.current].next == Null : 
            return False 
        self.current = self.n[self.current].next 
        return True 

    def print_current_node(self) -> None:
        """주목 노드를 출력"""
        if self.current == Null : 
            print('주목 노드가 없습니다.')
        else : 
            print(self.n[self.current].data)

    def print(self) -> None : 
        """모든 노드를 출력"""
        ptr = self.head 

        while ptr != Null : 
            print(self.n[ptr].data)
            prt = self.n[ptr].next 


    def dump(self)-> None:
        """배열을 덤프(출력)"""
        for i in self.n : 
            print(f'[{i}] {i.data}, {i.next}, {i.dnext}')
        print(f'head = {self.head}, current = {self.current}, max = {self.max}, deleted = {self.deleted}, no = {self.no}')

    def __iter__(self) -> ArrayLinkedListIterator: 
        """이터레이터를 반환"""
        return ArrayLinkedListIterator(self.n, self.head)

class ArrayLinkedListIterator : 
    """클래스 ArrayLinkedList의 이터레이터용 클래스"""
    
    def __init__(self, n : int, head : int):
        self.n = n 
        self.current = head 
    
    def __iter__(self)-> ArrayLinkedListIterator:
        return self 
    
    def __next__(self) -> Any : 
        if self.current == Null :
            raise StopIteration
        else : 
            data = self.n[self.current].data
            self.current = self.n[self.current].next 
            return data 