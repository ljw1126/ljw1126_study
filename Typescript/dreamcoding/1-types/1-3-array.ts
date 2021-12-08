{
  // Array
  const fruits: string[] = ['🍅', '🍌'];
  const scroes: Array<number> = [1, 3, 4];         //number[] 도 가능 , Array<number>는 generic 챕터에서
  function printArray(fruits: readonly string[]) {} //함수 내부에서 배열 인자 변경 x 하도록 readonly 시킴
                                                    //readonly 사용시 Array<string>은 못씀! string[] 표현처럼 일관성 지켜주기 
  // Tuple -> interface, type alias, class
  let student: [string, number]; //이렇게 쓰는건 가독성 떨어지니 아래 경우를 쓰거나 대체하는게 좋다함 
  student = ['name', 123];
  student[0]; // name
  student[1]; // 123
  const [name, age] = student;      // **object distructure 사용하면 조금은 가독성 올라감 ( React에 useState 매개변수 사용시 이런식으로 사용한다함)

  /*
    엘리는 튜플 권장 안함 
    배열을 인덱스로 출력하는 것은 가독성 떨어진다함 
    > 차라리 interface, type alias, class 로 대체하는게 좋음 
  */



}
