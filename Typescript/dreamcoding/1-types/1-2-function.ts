{
  // JavaScript 💩
  function jsAdd(num1, num2) {
    return num1 + num2;
  }

  // TypeScript ✨
  function add(num1: number, num2: number): number {
    return num1 + num2;
  }

  // JavaScript 💩
  function jsFetchNum(id) {
    // code ...
    // code ...
    // code ...
    return new Promise((resolve, reject) => {
      resolve(100);
    });
  }

  // TypeScript ✨
  function fetchNum(id: string): Promise<number> {
    // code ...
    // code ...
    // code ...
    return new Promise((resolve, reject) => {
      resolve(100);
    });
  }
  // test
  function fetchNum2(id : string) : Promise<any>{ // Promise는 generic 이구나 
    return new Promise((resolve, reject) => {
      //resolve('String');
      resolve(1);
    });
  }


  // JavaScript ✨ => TypeScript 
  // Optional parameter ( 선택적 파라미터, 있어도 되고 없어도 되게 됨, lastName?:string  )
  function printName(firstName: string, lastName?: string | null ) { // optional 아니면 정확하게 파라미터 입력이 무조건 있어야 함 
    console.log(firstName);
    console.log(lastName); // undefined
  }
  printName('Steve', 'Jobs');
  printName('Ellie');
  printName('Anna');

  // Default parameter (파라미터 기본값 지정)
  function printMessage(message: string = 'default message') {
    console.log(message);
  }
  printMessage();

  // Rest parameter(...) , 모든 전달되는 인자들을 number[] 로 받음 
  function addNumbers(...numbers: number[]): number {
    return numbers.reduce((a, b) => a + b);
  }
  console.log(addNumbers(1, 2));
  console.log(addNumbers(1, 2, 3, 4));
  console.log(addNumbers(1, 2, 3, 4, 5, 0));

  //※ *.reduce( function() , 초기값) ;  차례대로 다 더하는건데, 초기값 있으면 초기값에다가 차례대로 더함  

}
