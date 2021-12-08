/*
    typescript 제공함수 사용하지 않고 Stack 구현 해보기  
    - LIFO ( Last In First Out )
*/
//스택 규격사항을 인터페이스로 구현 
interface Stack{
    push(value:string):void;
    pop():string;
    readonly size:number;
}
/*
    조건.단일 연결 리스트로 구현하기!
    - head 에 할당
    - 데이터를 감쌀 수 있는 Node 가 필요 .. ?
*/
type StackNode = { // readonly로 불변성 
   readonly value:string; 
   readonly next?:StackNode ;
}
class StackImpl implements Stack{
    //내부에 readonly 붙이면 접근 x 
    private _size : number; //내부에서만 쓰이는 변수, 동명의 변수가 있구나로 이해
    //head는 stacknode를 가질 수 있고, 없고 
    private head?:StackNode;
    //보통은 생성자로 스택 사이즈 전달받아 생성함
    constructor(private capaticy : number){}
    get size(){
        return this._size;
    }

    push(value:string){
        
        if(this._size === this.capaticy){
            throw new Error('Stack is full!');
        }

        const node:StackNode = {
            value,
            next:this.head
        };
        this.head = node ;
        this._size++; 
    }
    
    pop():string{ // |undefined 정의하면 사용자가 null 체크 처리해줘야 함 (불편)
        if(this.head == null){ // null == undefined (값체크시)이지만 ,, null !== undefined (유형은 다름)
            throw new Error('Stack is empty!');
        }
        const node = this.head;
        this.head = node.next;
        this._size--;
        return node.value;
    }
}

const stack = new StackImpl(5);
stack.push('Ellie 1');
stack.push('Ellie 2');
stack.push('Ellie 3');
stack.push('Ellie 4');
console.log(stack.size);
// while(stack.size !== 0){
//     console.log(stack.pop());
// }

// 단일 연결 리스트를 사용해라는데? head가 마지막 노드 번호를 가르키라는데 
// class StackImpl implements Stack{
//     static size:number;
//     private arr:string[];
//     private head:number = 0;

//     push(value:string){
//         this.arr[this.head] = value;
//         this.head = this.arr.length;
//     }

//     pop(){
//         let value:string;
//         if(this.arr.length == 0 ){
//             throw new Error('There is nothing');
//         }else{
//             value = this.arr[this.arr.length];
//             this.arr.length = this.arr.length -1 ;
//             this.head = this.arr.length;
//         }
//         return value;
//     }
// }

