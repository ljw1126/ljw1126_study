{ //괄호 쳐서 글로벌 scope가 되지 않게 로컬 scope화함 
  //old:var hoisting 문제 있음 !! 그래서 안 쓰는게 좋음 .. 
  /**
   * JavaScript type 2가지
   * 1) Primitive(원시): number, string, boolean, bigint, symbol, null, undefined
   * 2) Object: function, array.....
   */
  // 


  // number
  const num: number = -6;
  // const num2: number = 6;
  // string
  const str: string = 'hello';
  // const str2: string = 'world';
  // boolean
  const boal: boolean = false;
  // const boal2: boolean = true; 
  
  // undefined '값이 있는 없는지 아무것도 결정안됨!' 상태
  let name: undefined; // 💩 단독적으로 이런건 쓰지 않음 
  let age: number | undefined; // *숫자 또는 undefined 할당가능한 데이터 타입 이다 ( 보편적으로 사용함 )
  age = undefined;
  age = 1;
  function find(): number | undefined {
    return undefined;
  }

  // null 조금더 명확하게 텅텅 '비어져 있다!'는 것 
  let person: null; // 💩 단독적으로 사용하지 않음 
  let person2: string | null; // 문맥상 undefined 보다 null 이 의미가 맞다면 선택하여 사용하기
  
  // unknown(알수없음) 💩 , *가능하면 쓰지 않는게 좋다 , 어떤 종류의 데이터가 담길지 알 수 없음
  let notSure: unknown = 0;
  notSure = 'he'; //문자열도 할당가능 
  notSure = true; //boolean도 할당가능 

  // any (무엇이든,anything) 💩 , *가능하면 쓰지 않는게 좋다 
  let anything: any = 0;
  anything = 'hello';

  // void
  function print(): void {
    console.log('hello');
    return;  //(아무것도 리턴하지 않으면 생략가능, void형은 return;이 생략되어 왔음)
  }
  let unusable: void = undefined; // 💩 *void로 선언하면 undefined밖에 할당 못하니 , 이렇게 안씀 

  // never >> return 할 계획이 없다는 뜻 
  function throwError(message: string): never {
    // message -> server (log)
    throw new Error(message);
    while (true) {}
  }
  let neverEnding: never; // 💩, *이런 경우 없음 

  // objet
  let obj: object; // 💩, 광범위해서 쓰지 않는게 좋음 ,사용한다면 구체적으로 어떤 타입인지 명시하는게 좋음 
  function acceptSomeObject(obj: object) {}
  acceptSomeObject({ name: 'ellie' });
  acceptSomeObject({ animal: 'dog' });
  acceptSomeObject({ age : 20});
}
