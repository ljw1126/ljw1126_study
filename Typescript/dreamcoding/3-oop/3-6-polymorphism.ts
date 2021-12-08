{
  // Polymorphism (다형성)
  /*
    부모 클래스의 함수를 자식 클래스가 물려받아 
    자식의 형상에 맞게 구현함으로서 다형성을 가지게 됨  
  */
  // type CoffeeCup = {
  //   shots: number;
  //   hasMilk?: boolean;
  //   hasSugar?: boolean;
  // };

  // interface CoffeeMaker {
  //   makeCoffee(shots: number): CoffeeCup;
  // }

  // class CoffeeMachine implements CoffeeMaker {
  //   private static BEANS_GRAMM_PER_SHOT: number = 7; // class level
  //   private coffeeBeans: number = 0; // instance (object) level

  //   constructor(coffeeBeans: number) {
  //     this.coffeeBeans = coffeeBeans;
  //   }

  //   static makeMachine(coffeeBeans: number): CoffeeMachine {
  //     return new CoffeeMachine(coffeeBeans);
  //   }

  //   fillCoffeeBeans(beans: number) {
  //     if (beans < 0) {
  //       throw new Error('value for beans should be greater than 0');
  //     }
  //     this.coffeeBeans += beans;
  //   }

  //   clean() {
  //     console.log('cleaning the machine...🧼');
  //   }

  //   private grindBeans(shots: number) {
  //     console.log(`grinding beans for ${shots}`);
  //     if (this.coffeeBeans < shots * CoffeeMachine.BEANS_GRAMM_PER_SHOT) {
  //       throw new Error('Not enough coffee beans!');
  //     }
  //     this.coffeeBeans -= shots * CoffeeMachine.BEANS_GRAMM_PER_SHOT;
  //   }

  //   private preheat(): void {
  //     console.log('heating up... 🔥');
  //   }

  //   private extract(shots: number): CoffeeCup {
  //     console.log(`Pulling ${shots} shots... ☕️`);
  //     return {
  //       shots,
  //       hasMilk: false,
  //     };
  //   }

  //   makeCoffee(shots: number): CoffeeCup {
  //     this.grindBeans(shots);
  //     this.preheat();
  //     return this.extract(shots);
  //   }
  // }

  // class CaffeLatteMachine extends CoffeeMachine {
  //   constructor(beans: number, public readonly serialNumber: string) {
  //     super(beans);
  //   }
  //   private steamMilk(): void {
  //     console.log('Steaming some milk... 🥛');
  //   }
  //   makeCoffee(shots: number): CoffeeCup {
  //     const coffee = super.makeCoffee(shots);
  //     this.steamMilk();
  //     return {
  //       ...coffee,
  //       hasMilk: true,
  //     };
  //   }
  // }

  // class SweetCoffeeMaker extends CoffeeMachine {
  //   makeCoffee(shots: number): CoffeeCup {
  //     const coffee = super.makeCoffee(shots);
  //     return {
  //       ...coffee,
  //       hasSugar: true,
  //     };
  //   }
  // }

  // const machines: CoffeeMaker[] = [
  //   new CoffeeMachine(16),
  //   new CaffeLatteMachine(16, '1'),
  //   new SweetCoffeeMaker(16),
  //   new CoffeeMachine(16),
  //   new CaffeLatteMachine(16, '1'),
  //   new SweetCoffeeMaker(16),
  // ];
  // machines.forEach(machine => {
  //   console.log('-------------------------');
  //   machine.makeCoffee(1);
  // });

  type CoffeeCup ={
    shots : number;
    hasMilk?: boolean;
    hasSugar?: boolean;
  }
 
  interface CoffeeMaker{ 
    makeCoffee(shots:number):CoffeeCup;
  }

  class CoffeeMakerImpl implements CoffeeMaker {
    private static BEANS_GRAMM_PER_SHOT:number = 7; 
    private coffeeBeans : number = 0 ; 
  
    // 상속(extends)할 경우 private는 안됨 , 그래서 public이나 protected로 고쳐사용
    constructor(coffeeBeans : number){
      this.coffeeBeans = coffeeBeans; 
    }

    static makeMachine(coffeeBeans : number ):CoffeeMakerImpl{
       return new CoffeeMakerImpl(coffeeBeans);
    }

    private grindBeans(shots){
      console.log(`grinding beans for ${shots}`);
      if(this.coffeeBeans < shots * CoffeeMakerImpl.BEANS_GRAMM_PER_SHOT){
        throw new Error('Not enough coffee beans!');
      }
      this.coffeeBeans -= shots * CoffeeMakerImpl.BEANS_GRAMM_PER_SHOT;
    }

    private preheat() : void{
      console.log('heating up ... ❤');
    }

    private extract(shots : number): CoffeeCup{
      console.log(`Pulling ${shots} shots....☕`);
      return {
        shots,
        hasMilk : false
      }
    }

    makeCoffee(shots : number):CoffeeCup{ //함수 
      // 1. 커피 콩을 갈아야함 
      this.grindBeans(shots);
      // 2. 커피 기계 준비 
      this.preheat();
      // 3. 커피 추출 
      return this.extract(shots);
   }

    fillCoffeeBeans(beans : number){  
     if(beans < 0 ){
      throw new Error('value for beans should be greater than 0 ');
     }
     this.coffeeBeans = beans;
    }

    clean(){
      console.log('cleaning the machine... 🧼')
    }
  }
  

  class CaffeLatteMachine extends CoffeeMakerImpl {

    //overridding 사용 
    makeCoffee(shots:number):CoffeeCup{
      const coffee = super.makeCoffee(shots);//부모(CoffeeMakerImpl)의 함수 호출 
      this.steamMilk(); //자식클래스 내부 함수 생성
      return {
          ...coffee,
          hasMilk:true 
        }
      }

    private steamMilk():void{
      console.log('Steaming some milk... 🥛');
    }

    constructor(beans : number , public readonly serailNumber:string){
      super(beans); 
    }
  } 

  // 추가 , 커피컵에 설탕 추가해주는 클래스
  class SweetCoffeeMaker extends CoffeeMakerImpl{
    makeCoffee(shots:number):CoffeeCup{
      const coffee = super.makeCoffee(shots);
     
      return {
          ...coffee,
          hasSugar:true 
        }
      }

   
  }

  const machines:CoffeeMaker[] = [
    new CoffeeMakerImpl(16),
    new CaffeLatteMachine(16, 'SerailNum1'),
    new SweetCoffeeMaker(16),
    new CoffeeMakerImpl(16),
    new CaffeLatteMachine(16, 'SerailNum2'),
    new SweetCoffeeMaker(16),
  ]

  machines.forEach(machine =>{
    console.log('================');
    console.log(machine.makeCoffee(1));
  });
  /*
    다형성 장점 
    - 내부적으로 구현된 다양한 클래스들이 한가지 인터페이스를 구현하거나
    또는 동일한 부모 클래스 상속시 동일한 함수를 어떤 클래스인지 구분하지 않고
    동일한 api를 호출가능함
    - 다형성이란 ? 
    하나의 인터페이스나 부모클래스 상속한 자식클래스들이 인터페이스와 부모 클래스 함수를 다른 방식으로 다양하게 구성
    함으로서 다양성을 만듦
    이처럼 인터페이스와 부모클래스에 있는 동일한 함수 api 호출함으로서 사용자도 간편하게 다양한 기능을 이용가능 
    (자식클래스에 뭐가 구현되어 있든  )
  */
}
