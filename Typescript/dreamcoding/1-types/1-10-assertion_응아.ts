{
  /**
   * Type Assertions 💩
   * 100% 장담할때 써야함 , function 리던 형이 100% 확실될때 사용함 
   * 뭔가 casting 같은 느낌인거 같으면서도 
   */
  function jsStrFunc(): any {
    return 2;
  }
  const result = jsStrFunc();
  console.log((result as string).length); // 
  console.log((<string>result).length);

  const wrong: any = 5;
  console.log((wrong as Array<number>).push(1)); // 😱 TypeError: wrong.push is not a function , 섣부른 판단으로 이렇게 하면 안됨 

  function findNumbers(): number[] | undefined {
    return undefined;
  }
  const numbers = findNumbers()!;
  numbers.push(2); // 😱 숫자일수도 undefined 일수 있기때문에 push 를 쓰면 좋지 않음 ! 
  numbers!.push(3); // 변수 뒤에 ! 붙이면 확신한다는 뜻 ( ? : optional 과 반대 )
  
  const button = document.querySelector('class')!; // 선택자 함수 return 값 확인해보면 엘리먼트나 , null 이 올 수도 있다함 
}
