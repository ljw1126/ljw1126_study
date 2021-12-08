{
  //abstract (추상화)
  /*
    상속 클래스 이용시 반복적인 절차가 있고 
    어떤 특정기능만 자식클래스에서 달라진다면 추상화 활용가능
  */
 
  // type CoffeeCup = {
  //   shots: number;
  //   hasMilk?: boolean;
  //   hasSugar?: boolean;
  // };

  // interface CoffeeMaker {
  //   makeCoffee(shots: number): CoffeeCup;
  // }

  // abstract class CoffeeMachine implements CoffeeMaker {
  //   private static BEANS_GRAMM_PER_SHOT: number = 7; // class level
  //   private coffeeBeans: number = 0; // instance (object) level

  //   constructor(coffeeBeans: number) {
  //     this.coffeeBeans = coffeeBeans;
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

  //   protected abstract extract(shots: number): CoffeeCup;

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

  //   protected extract(shots: number): CoffeeCup {
  //     this.steamMilk();
  //     return {
  //       shots,
  //       hasMilk: true,
  //     };
  //   }
  // }

  // class SweetCoffeeMaker extends CoffeeMachine {
  //   protected extract(shots: number): CoffeeCup {
  //     return {
  //       shots,
  //       hasSugar: true,
  //     };
  //   }
  // }

  // const machines: CoffeeMaker[] = [
  //   new CaffeLatteMachine(16, '1'),
  //   new SweetCoffeeMaker(16),
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
  // abstract 키워드 붙임 , 추상(부모)클래스가 되면서 자체 object 만들수 없음
  abstract class CoffeeMakerImpl implements CoffeeMaker {
    private static BEANS_GRAMM_PER_SHOT:number = 7; 
    private coffeeBeans : number = 0 ; 
  
    // 상속(extends)할 경우 private는 안됨 , 그래서 public이나 protected로 고쳐사용
    constructor(coffeeBeans : number){
      this.coffeeBeans = coffeeBeans; 
    }

    // static makeMachine(coffeeBeans : number ):CoffeeMakerImpl{
    //    return new CoffeeMakerImpl(coffeeBeans);
    // }

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

    //추상 메소드로도 선언 가능, 이때 추상이기에 부모에서 정의 x , protected 로 자식에서 호출사용가능하도록함 
    protected abstract extract(shots : number): CoffeeCup;

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

    private steamMilk():void{
      console.log('Steaming some milk... 🥛');
    }

    constructor(beans : number , public readonly serailNumber:string){
      super(beans); 
    }

    // 추상클래스의 추상메소드 구현 
    protected extract(shots:number):CoffeeCup{
       this.steamMilk();
       return{
         shots,
         hasMilk : true
       } 
    }
  } 

  // 추가 , 커피컵에 설탕 추가해주는 클래스
  class SweetCoffeeMaker extends CoffeeMakerImpl{
    // 추상클래스의 추상메소드(구현하는 클래스마다 달라지는) 구현 
    protected extract(shots:number):CoffeeCup{
      return{
        shots,
        hasSugar : true
      } 
   }
   
  }

  const machines:CoffeeMaker[] = [
    new CaffeLatteMachine(16, 'SerailNum1'),
    new SweetCoffeeMaker(16),
    new CaffeLatteMachine(16, 'SerailNum2'),
    new SweetCoffeeMaker(16),
  ]

  machines.forEach(machine =>{
    console.log('================');
    console.log(machine.makeCoffee(1));
  });
}
