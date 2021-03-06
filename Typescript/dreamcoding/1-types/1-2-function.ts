{
  // JavaScript ๐ฉ
  function jsAdd(num1, num2) {
    return num1 + num2;
  }

  // TypeScript โจ
  function add(num1: number, num2: number): number {
    return num1 + num2;
  }

  // JavaScript ๐ฉ
  function jsFetchNum(id) {
    // code ...
    // code ...
    // code ...
    return new Promise((resolve, reject) => {
      resolve(100);
    });
  }

  // TypeScript โจ
  function fetchNum(id: string): Promise<number> {
    // code ...
    // code ...
    // code ...
    return new Promise((resolve, reject) => {
      resolve(100);
    });
  }
  // test
  function fetchNum2(id : string) : Promise<any>{ // Promise๋ generic ์ด๊ตฌ๋ 
    return new Promise((resolve, reject) => {
      //resolve('String');
      resolve(1);
    });
  }


  // JavaScript โจ => TypeScript 
  // Optional parameter ( ์ ํ์  ํ๋ผ๋ฏธํฐ, ์์ด๋ ๋๊ณ  ์์ด๋ ๋๊ฒ ๋จ, lastName?:string  )
  function printName(firstName: string, lastName?: string | null ) { // optional ์๋๋ฉด ์ ํํ๊ฒ ํ๋ผ๋ฏธํฐ ์๋ ฅ์ด ๋ฌด์กฐ๊ฑด ์์ด์ผ ํจ 
    console.log(firstName);
    console.log(lastName); // undefined
  }
  printName('Steve', 'Jobs');
  printName('Ellie');
  printName('Anna');

  // Default parameter (ํ๋ผ๋ฏธํฐ ๊ธฐ๋ณธ๊ฐ ์ง์ )
  function printMessage(message: string = 'default message') {
    console.log(message);
  }
  printMessage();

  // Rest parameter(...) , ๋ชจ๋  ์ ๋ฌ๋๋ ์ธ์๋ค์ number[] ๋ก ๋ฐ์ 
  function addNumbers(...numbers: number[]): number {
    return numbers.reduce((a, b) => a + b);
  }
  console.log(addNumbers(1, 2));
  console.log(addNumbers(1, 2, 3, 4));
  console.log(addNumbers(1, 2, 3, 4, 5, 0));

  //โป *.reduce( function() , ์ด๊ธฐ๊ฐ) ;  ์ฐจ๋ก๋๋ก ๋ค ๋ํ๋๊ฑด๋ฐ, ์ด๊ธฐ๊ฐ ์์ผ๋ฉด ์ด๊ธฐ๊ฐ์๋ค๊ฐ ์ฐจ๋ก๋๋ก ๋ํจ  

}
