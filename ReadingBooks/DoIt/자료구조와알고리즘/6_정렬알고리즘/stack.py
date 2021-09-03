# 고정 길이 스택 클래스 구현하기(collections.deque를 사용)

from typing import Any 
from collections import deque 

class Stack : 
    
    """스택 초기화"""
    def __init__(self, maxlen:int = 256) -> None :
        self.capacity = maxlen       # 스택의 크기   
        self.__stk = deque([],maxlen)

    """스택에 쌓여있는 데이터 개수 반환"""
    def __len__(self) -> int:
        return len(self.__stk)

    """스택이 비어있는지 판단"""
    def is_empty(self) -> bool:
        return not self.__stk

    """스택이 가득 차 있는지 판단"""
    def is_full(self) -> bool:
        return len(self.__stk) == self.__stk.maxlen

    """스택(배열)에 value를 푸시(데이터를 넣음)"""
    def push(self, value:Any) -> None:
       self.__stk.append(value)

    """스택(배열)에서 데이터를 팝(마지막,꼭대기데이터 꺼냄)"""
    def pop(self) -> Any:
        return self.__stk.pop()

    """스택에서 데이터를 피크(꼭대기 데이터를 들여다봄)"""
    def peek(self) -> Any:
        return self.stk[-1]

    """스택을 비움(모든 데이터를 삭제)"""
    def clear(self) -> None :
        self.__stk.clear()

    """스택에서 선형검색 통해 value를 찾아 인덱스를 반환(없으면 -1반환)"""
    def find(self, value : Any) ->Any:
        try:
            return self.__stk.index(value)
        except ValueError:
            return -1 #검색 실패 

    """스택에 있는 특정value의 개수를 반환"""
    def count(self, value:Any) ->bool:
        return self.__stk.count(value)

    """스택에 value가 있는지 판단"""
    def __contains__(self, value:Any) -> bool:
        return self.count(value) # bool 타입의 경우 숫자가 0만 거짓, 나머지는 참 

    """덤프(=스택안의 모든 데이터를 바닥부터 꼭대기 순으로 출력)"""
    def dump(self) -> None:
        print(list(self.__stk))