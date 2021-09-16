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

class BinarySeachTree:
    """이진 검색 트리"""

    def __init__(self):
        """초기화"""
        self.root = None

    def search(self, key:Any) -> Any: 
        """키가 key인 노드를 검색"""
        p = self.root   # 루트에 주목 
        while True : 
            if p is None :      # 더 이상 진행 할 수 없으면 
                return None     # 검색 실패
            if key == p.key :   # key와 노드 p의 키가 같으면 
                return p.value  # 검색 성공 
            elif key < p.key :  # key쪽이 작으면
                p = p.left      # 왼쪽 ㅓ브트리에서 검색 
            else :              # key쪽이 크면 
                p = p.right     # 오른쪽 서브트리에서 검색

    # start : add()
    def add(self, key:Any, value:Any)->bool: 
        """키가 key이고 값이 value인 노드를 삽입"""

        def add_node(node:Node, key:Any, value:Any)->None:
            if key == node.key : 
                return False # key가 이진 검색 트리에 이미 존재 
            elif key < node.key : 
                if node.left is None : # 노드의 왼쪽이 빈 경우 
                    node.left = Node(key, value, None, None)
                else : # 왼쪽이 비어 있지 않다면 
                    add_node(node.left, key, value)
            else:
                if node.right is None : 
                    node.right = Node(key,value,None,None)
                else:
                    add_node(node.right, key, value)
            return True 
        # 1. 여기 부터 실행 
        if self.root is None : # root가 None 인 경우 
            self.root = Node(key, value, None, None)
            return True 
        else : # root가 None이 아닌 경우
            return add_node(self.root, key, value)
    # end : add()
            

    """
    1. 자식 노드가 없는 노드를 삭제하는 경우 
    2. 자식 노드가 1개인 노드를 삭제하는 경우
       > 삭제할 노드가 부모 노드의 왼쪽 자식인 경우 
         부모의 왼쪽 포인터가 삭제할 노드의 자식을 가르키도록 업데이트 함
       > 삭제할 노드가 부모 노드의 오른쪽 자식인 경우 
         부모의 오른쪽 포인터가 삭제할 노드의 자식을 가르키도록 업데이트 함 
    3. 자식 노드가 2개인 노드를 삭제하는 경우 (가장 복잡, p392)
       > 삭제할 노드의 왼쪽 서브트리에서 키값이 가장 큰 노드를 검색합니다. 
       > 검색한 노드를 삭제 위치로 옮김. 즉, 검색한 노드의 데이터를 삭제할 노드 위치에 복사함 
       > 옮긴 노드를 삭제합니다. 이때 자식 노드의 개수에 따라 다음 수행함 
         >> 옮긴 노드에 자식이 없으면 : '자식 노드가 없는 노드의 삭제'에 따라 노드 삭제함 
         >> 자식이 1개만 있으면 : '자식 노드가 1개인 노드의 삭제'에 따라 노드 삭제함 
    """
    def remove(self, key:Any)->bool : 
        """키가 key인 노드를 삭제"""
        p = self.root                       # 스캔 중인 노드
        parent = None                       # 스캔 중인 노드의 부모 노드 
        is_left_child = True                # p는 parent 의 왼쪽 자식 노드인지 확인 

        # start : while 
        # 삭제하려는 노드를 찾음 (p)
        while True : 
            if p is None :                  # 더 이상 진행할 수 없으면 
                return False                # 그 키는 존재하지 않음 

            if key == p.key :               # key와 노드 p의ㅣ 키가 같으면 
                break                       # 검색 성공 
            else : 
                parent = p                  # 가지를 내려가지 전에 부모를 설정 
                if key < p.key :            # key쪽이 작으면 
                    is_left_child = True    # 여기서 내려가는 것은 왼족 자식 
                    p = p.left              # 왼쪽 서브트리에서 검색 
                else : 
                    is_left_child = False   # 여기서 내려가는 것은 오른쪽 자식 
                    p = p.right             # 오른쪽 서브트리에서 검색     

        # end : while 

        """
        if와 elif의 경우 
            > 자식 노드가 없는 노드를 삭제하는 경우 
            > 자식 노드가 1개인 노드를 삭제하는 경우 
        else 의 경우 
            > 자식 노드가 2개이 노드를 삭제하는 경우를 수행함         
        """
        if p.left is None :                 # p에 왼쪽 자식이 없으면 
            if p is self.root :             # 루트 자신인 경우 
                self.root = p.right 
            elif is_left_child : 
                parent.left = p.right       # 부모의 왼쪽 포인터가 오른족 자식을 가르킴 
            else : 
                parent.right = p.right      # 부모의 오른쪽 포인터가 오른쪽 자식을 가르킴  (p393 그림에서 11삭제시 )
        elif p.right is None :              # p에 오른쪽 자식이 없으면 
            if p is self.root :             # 루트 자신인 경우
                self.root = p.left          
            elif is_left_child : 
                parent.left = p.left        # 부모의 왼쪽 포인터가 왼쪽 자식을 가리킴 
            else : 
                parent.right = p.left       # 부모의 오른쪽 포인터가 왼쪽 자식을 가르킴 
        else : 
            parent = p 
            left = p.left                   # 서브트리 안에서 가장 큰 노드 
            is_left_child = True 
            while left.right is not None :  # 가장 큰 노드 left를 검색 
                parent = left 
                left = left.right 
                is_left_child = False 

            p.key = left.key                # left의 키를 p로 이동 
            p.value = left.value            # left의 데이터를 p로 이동 
            if is_left_child : 
                parent.left = left.left     # left를 삭제 
            else :  
                parent.right = left.left    # left를 삭제 
        return True 

    
    def dump(self)->None : 
        """덤프(모든 노들르 키의 오름차순으로 출력)"""

        def print_subtree(node:Node):
            """node를 루트로 하는 서브트리의 노드를 키의 오름차순으로 출력"""
            if node is not None :  
                print_subtree(node.left)           # 왼쪽 서브트리를 오름차순으로 출력 
                print(f'{node.key}/{node.value}')  # node를 출력  
                print_subtree(node.right)          # 오른쪽 서브트리를 오름차순으로 출력 

        print_subtree(self.root)


    def min_key(self) -> Any : 
        """가장 작은키 (제일 왼쪽)"""

        if self.root is None : 
            return None 
        p = self.root 

        while p.left is not None : 
            p = p.left 
 
        return p.key 
            
    def max_key(self) -> Any : 
        """가장 큰 키 (제일 오른쪽)"""
        if self.root is None : 
            return None 
        p = self.root

        while p.right is not None : 
            p = p.right 

        return p.key 

    def dump(self, reverse = False) -> None : 
        """덤프(모든 노드를 키의 오름차순/내림차순으로 출력)"""

        def print_subtree(node:Node):
            """node를 루트로 하는 서브트리의 노드를 키의 오름차순으로 출력"""
            if node is not None : 
                print_subtree(node.left)            # 왼쪽 서브트리를 오름차순으로 출력
                print(f'{node.key}/{node.value}')   # node를 출력 
                print_subtree(node.right)           # 오른쪽 서브트리를 오름차순으로 출력 

        def print_subtree_rev(node:Node):
            """node를 루트로 하는 서브트리의 노드를 키의 내림차순으로 출력"""
            if node is not None : 
                print_subtree(node.right)           # 오른쪽 서브트리를 내림차순으로 출력
                print(f'{node.key}/{node.value}')   # node를 출력 
                print_subtree(node.left)            # 왼쪽 서브트리를 내림차순으로 출력         

        # 매개변수 reverse 가 true이면 print_subtree_rev() 내림차순 실행 , 아닌 경우 print_subtree() 오름차순 실행 
        print_subtree_rev(self.root) if reverse else print_subtree(self.root)

        