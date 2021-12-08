{
  /**
   * Type Aliases 
   * -새로운 타입을 내가 정의가능 
   */
  type Text = string; // Text 라는 새로운 타입은 문자열만 할당 가능
  const name: Text = 'ellie';
  const address: Text = 'korea';
  const test : Text = '1';

  type Num = number;
  
  type Student = { //이것이 Student 타입이다 ! 라고 선언함 
    name: string;
    age: number;
  };
  
  const student: Student = {
    name: 'ellie',
    age: 12,
  };

  /**
   * String Literal Types
   * - 타입을 문자열로도 정의가능 ( 왜 사용하지 ? 다음 장 )
   */
  type Name = 'name';
  let ellieName: Name;
  ellieName = 'name'; //'name' 텍스트 말고는 할당 불가함 
  type JSON = 'json';
  const json: JSON = 'json';

  type Boal = true;
}
