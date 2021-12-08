{
  // 2.객체지향적 프로그래밍
  // type CoffeeCup = {
  //   shots: number;
  //   hasMilk: boolean;
  // };

  // class CoffeeMaker {
  //   static BEANS_GRAMM_PER_SHOT: number = 7; // class level
  //   coffeeBeans: number = 0; // instance (object) level

  //   constructor(coffeeBeans: number) {
  //     this.coffeeBeans = coffeeBeans;
  //   }

  //   static makeMachine(coffeeBeans: number): CoffeeMaker {
  //     return new CoffeeMaker(coffeeBeans);
  //   }

  //   makeCoffee(shots: number): CoffeeCup {
  //     if (this.coffeeBeans < shots * CoffeeMaker.BEANS_GRAMM_PER_SHOT) {
  //       throw new Error('Not enough coffee beans!');
  //     }
  //     this.coffeeBeans -= shots * CoffeeMaker.BEANS_GRAMM_PER_SHOT;
  //     return {
  //       shots,
  //       hasMilk: false,
  //     };
  //   }
  // }

  // const maker = new CoffeeMaker(32);
  // console.log(maker);
  // const maker2 = new CoffeeMaker(14);
  // console.log(maker2);

  // const maker3 = CoffeeMaker.makeMachine(3);

  type CoffeeCup ={
    shots : number;
    hasMilk : boolean;
  }
  // 커피 관련된 속성, 함수를 class안에 넣음
  class CoffeeMaker {
    static BEANS_GRAMM_PER_SHOT:number = 7; //맴버 변수인데 동일한 object생성시 동일한 멤버변수가 있으면 메모리 낭비 발생가능 , 그래서 static 붙여 class 레벨로 만듦  
    coffeeBeans : number = 0 ; // 커피콩 수 , 멤버 변수 instance ( object ) level 
    
    // 클래스, object 만들때 항상 처음 호출 됨 
    constructor(coffeeBeans : number){
      this.coffeeBeans = coffeeBeans; 
    }

    makeCoffee(shots : number):CoffeeCup{ //함수 
        // 커피 콩이 필요한 양보다 적은 경우 
        if( this.coffeeBeans < shots * CoffeeMaker.BEANS_GRAMM_PER_SHOT){
          throw new Error('Not enought coffee Bean!');
        }
  
        this.coffeeBeans -= shots * CoffeeMaker.BEANS_GRAMM_PER_SHOT;
  
        //key와 value 명이 동일하면 생략 가능
        return { 
          shots, 
          hasMilk : false 
        }
    }

    static makeMachine(coffeeBeans:number) : CoffeeMaker{ // class 내에 변수를 사용하지 않기 때문에 
      return new CoffeeMaker(coffeeBeans);
    }
  }

  const maker = new CoffeeMaker(32);  //new CoffeeMaker() 생성자 호출하는거  
  console.log(maker);

  const maker2 = new CoffeeMaker(3);  //new CoffeeMaker() 생성자 호출하는거  
  console.log(maker2);

  const maker3 = CoffeeMaker.makeMachine(4); // class 레벨 함수
  console.log(maker3);

  // 클래스에 한번 정의되고, 동일 클래스에서 공유하는 맴버변수는 object 생성시 
  // 메모리 낭비를 일으킬수 있음 >> static 키워드 붙여 class level로 지정함 ( 반대는 instance|object level)
  // static을 붙이면 class와 연결되어 있어 object 생성시 매번 생성되지 않음 ( 메모리 낭비 x )

  /*
    class는 관련된 속성, 함수 묶어서 어떤 모양의 데이터가 될거란걸 정의함 
    정의된 클래스를 이용해서 실제 데이터를 넣어 object 만듦 

    이때 object 마다 새로 만들어져야 하는 데이터가 있다면 맴버변수로 만들고, 
    class 레벨에서 함께 공유되야 하는 것은 static 을 붙여줌 ( 이때 static은 *함수에도 붙일 수 있음 ) 

  */
}
