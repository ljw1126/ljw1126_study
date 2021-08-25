# 고정 길이 큐 클래스 FixedQueue 구현하기

from typing import Any

class FixedQueue:

    class Empty(Exception):
        pass

    class Full(Exception):
        pass

    """큐 초기화"""
    # front, rear 값의 경우 큐가 비어있을때/꽉 찼을때 숫자 동일
    def __init__(self, capacity:int)->None:
        self.no = 0                  # 현재 데이터 개수
        self.front = 0               # 맨 앞 원소 커서 
        self.rear = 0                # 맨 끝 원소 커서 ( 인큐시 데이터가 들어갈 인덱스)
        self.capacity = capacity    # 큐의 크기 
        self.que = [None] * capacity # 리스트로 큐 선언

    def __len__(self)->int:
        return self.no 
    
    def is_empty(self)->bool:
        return self.no <= 0

    def is_full(self)->bool:
        return self.no >= self.capacity

    """ 데이터 x를 인큐(넣기)"""
    def enque(self, x:Any) -> None:
        if self.is_full():
            raise FixedQueue.Full     # 큐가 가득 차 있는 경우 예외 처리 발생 
        self.que[self.rear] = x       # rear가 다음 값이 들어갈 인덱스
        self.rear += 1                # rear 인덱스 위치 올림 
        self.no += 1 
        if self.rear == self.capacity: # rear 값이 큐의 크기인 capacity와 같은 경우 rear를 배열 맨앞 인덱스 0으로 되돌림 
            self.rear = 0

    """데이터를 디큐(꺼내기)"""
    def deque(self) -> Any:
        if self.is_empty():
            raise FixedQueue.Empty
        x = self.que[self.front]
        self.front += 1 
        self.no -= 1 
        if self.front == self.capacity: # front 의 값이 큐의 크기인 capacity와 같은 경우 front를 1 증가시켜 배열의 맨 앞 인덱스인 0으로 되돌림 
            self.front = 0 
        return x 
        
    # """큐에서 맨앞 데이터를 피크(들여다봄, 확인)"""
    # def peek(self) -> Any:
    #     print()

    # """큐에서 value를 찾아 해당 인덱스 반환(없을 경우 -1 qksghks)"""
    # def find(self, value:Any) ->Any:
    #     return -1 


    # """큐에 있는 value의 개수를 반환"""
    # def count(self, value:Any) -> bool:
    #     return 0 

    # """큐에 value가 있는지 판단"""
    # def __contains__(self, value:Any) -> bool:
    #     return 0 

    # """큐의 모든 데이터를 비움"""
    # def clear(self) -> None:
    #     self.no = self.front = self.rear = 0

    # """모든 데이터를 맨 앞부터 맨 끝 순으로 출력"""
    # def dump(self) -> None:
    #     print()


    """큐에서 맨앞 데이터를 피크(들여다봄, 확인)"""
    def peek(self) -> Any:
        if self.is_empty():
            raise FixedQueue.Empty
        return self.que[self.front]

    """큐에서 value를 찾아 해당 인덱스 반환(없을 경우 -1 qksghks)"""
    def find(self, value:Any) ->Any:
        for i in range(self.no):
            idx = (i + self.front) % self.capacity
            if self.que[idx] == value :  # 검색성공
                return idx     
        return -1 # 검색실패


    """큐에 있는 value의 개수를 반환"""
    def count(self, value:Any) -> bool:
        c = 0 
        for i in range(self.no): # 큐데이터를 선형 검색 
            idx = (i + self.front) % self.capacity
            if self.que[idx] == value :
                c += 1 
        return c 

    """큐에 value가 있는지 판단"""
    def __contains__(self, value:Any) -> bool:
        return self.count(value) 

    """큐의 모든 데이터를 비움"""
    def clear(self) -> None:
        self.no = self.front = self.rear = 0

    """모든 데이터를 맨 앞부터 맨 끝 순으로 출력"""
    def dump(self) -> None:
        if self.is_empty():
            raise FixedQueue.Empty
        else:
            for i in range(self.no):
                #idx = (i + self.front) % self.capacity
                print(self.que[(i + self.front) % self.capacity], end=' ')
            print()



