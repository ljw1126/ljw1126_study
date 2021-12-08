{
  /**
   * Enum
   * 여러가지 관련된 상수값을 한 곳에 모아 정의하는데 도와줌 
   */
  // JavaScript ( javascript에서는 따로 enum 제공 안함 )
  const MAX_NUM = 6;
  const MAX_STUDENTS_PER_CLASS = 10;
  const MONDAY = 0;
  const TUESDAY = 1;
  const WEDNESDAY = 2;
  const DAYS_ENUM = Object.freeze({ MONDAY: 0, TUESDAY: 1, WEDNESDAY: 2 });
  const dayOfToday = DAYS_ENUM.MONDAY;

  // TypeScript ( enum 지원하지만 쓰지 않는게 좋다함 )
  // 값 할당 안하면 숫자의 경우 0 부터 할당됨, string은 추론 안되기때문에 적절히 해주는게 좋음
  type DaysOfWeek = 'Monday' | 'Tuesday' | 'Wednesday';
  enum Days {
    Monday = 1,  //  따로 값을 지정하지 않으면 0 부터 할당 
    Tuesday, // 1
    Wednesday, // 2 
    Thursday,
    Friday,
    Saturday,
    Sunday,
  } 
  console.log(Days.Monday);
  let day: Days = Days.Saturday;
  console.log(day);
  day = Days.Tuesday;
  console.log(day);
  day = 10; // 아무런 이슈가 발생하지 않지만, 아무숫자나 넣어지니 타입이 보장되지 않음 .. ( 0|1~ 6 ) 
  console.log(day);

  // Enum 대신 Union 을 사용한다함
  let dayOfweek: DaysOfWeek = 'Monday'; 
  dayOfweek = 'Wednesday';
}
