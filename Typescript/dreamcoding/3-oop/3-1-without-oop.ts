{
  // 1.절차지향 프로그래밍적으로 구현 
  // type CoffeeCup = {
  //   shots: number;
  //   hasMilk: boolean;
  // };

  // const BEANS_GRAMM_PER_SHOT: number = 7;

  // let coffeeBeans: number = 0;
  // function makeCoffee(shots: number): CoffeeCup {
  //   if (coffeeBeans < shots * BEANS_GRAMM_PER_SHOT) {
  //     throw new Error('Not enough coffee beans!');
  //   }
  //   coffeeBeans -= shots * BEANS_GRAMM_PER_SHOT;
  //   return {
  //     shots,
  //     hasMilk: false,
  //   };
  // }

  // coffeeBeans += 3 * BEANS_GRAMM_PER_SHOT;
  // const coffee = makeCoffee(2);
  // console.log(coffee);

  type CoffeeCup ={
    shots : number;
    hasMilk : boolean;
  }

  const BEANS_GRAMM_PER_SHOT:number = 1; // 커피 샷당 7그람 필요하다고 가정
  let coffeeBeans : number = 0 ; // 커피콩 수 
  
  function makeCoffee(shots : number):CoffeeCup{
      // 커피 콩이 필요한 양보다 적은 경우 
      if( coffeeBeans < shots * BEANS_GRAMM_PER_SHOT){
        throw new Error('Not enought coffee Bean!');
      }

      coffeeBeans -= shots * BEANS_GRAMM_PER_SHOT;

      //key와 value 명이 동일하면 생략 가능
      return { 
        shots, 
        hasMilk : false 
      }

  }

  coffeeBeans += 3 * BEANS_GRAMM_PER_SHOT;
  const coffee = makeCoffee(2);
  console.log(coffee);

}
