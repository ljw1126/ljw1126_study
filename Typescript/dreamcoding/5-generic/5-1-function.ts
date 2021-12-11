{
  function checkNotNullBad(arg: number | null): number {
    if (arg == null) {
      throw new Error('not valid number!');
    }
    return arg;
  }

  function checkNotNullAnyBad(arg: any | null): any { // 좋지 않은 예시 , 타입의 정보를 잃을 수 있음
    if (arg == null) {
      throw new Error('not valid number!');
    }
    return arg;
  }
  const result = checkNotNullAnyBad(123);

  function checkNotNull<T>(arg: T | null): T { // 타입보장 받을 수 있음 
    if (arg == null) {
      throw new Error('not valid number!');
    }
    return arg;
  }
  //generic 사용시 사용하는 사람이 어떤 타입인지 결정할 수 있고, 유연하지만 컴파일시 타입 보장 받을 수 있다.
  const number = checkNotNull(123); // 매개변수
  const boal: boolean = checkNotNull(true); // 매개변수로 boolean을 전달하므로 , 리턴도 boolean 받아 올수 있음
}
