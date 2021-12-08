{
  /*
    4. abstraction 추상화 
  */

  // type CoffeeCup = {
  //   shots: number;
  //   hasMilk: boolean;
  // };

  // interface CoffeeMaker {
  //   makeCoffee(shots: number): CoffeeCup;
  // }

  // interface CommercialCoffeeMaker {
  //   makeCoffee(shots: number): CoffeeCup;
  //   fillCoffeeBeans(beans: number): void;
  //   clean(): void;
  // }

  // class CoffeeMachine implements CoffeeMaker, CommercialCoffeeMaker {
  //   private static BEANS_GRAMM_PER_SHOT: number = 7; // class level
  //   private coffeeBeans: number = 0; // instance (object) level

  //   private constructor(coffeeBeans: number) {
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

  // class AmateurUser {
  //   constructor(private machine: CoffeeMaker) {}
  //   makeCoffee() {
  //     const coffee = this.machine.makeCoffee(2);
  //     console.log(coffee);
  //   }
  // }

  // class ProBarista {
  //   constructor(private machine: CommercialCoffeeMaker) {}
  //   makeCoffee() {
  //     const coffee = this.machine.makeCoffee(2);
  //     console.log(coffee);
  //     this.machine.fillCoffeeBeans(45);
  //     this.machine.clean();
  //   }
  // }

  // const maker: CoffeeMachine = CoffeeMachine.makeMachine(32);
  // const amateur = new AmateurUser(maker);
  // const pro = new ProBarista(maker);
  // pro.makeCoffee();

  /*
    추상화란 ?
     인터페이스를 간단하게 만듦으로써 사용자가 간단하게 사용할 수 있도록 도와줌 
     private에서 내부에서만 사용하고 외부노출 안되게 함으로서 
     외부 노출되는 정보(사용가능한 함수)만 가지고 사용자가 이용할 수 있도록 만듦 
     
     인터페이스를 사용하면 추상화를 극대화시킬 수 있음 
   */


  type CoffeeCup ={
    shots : number;
    hasMilk : boolean;
  }
 
  //계약서 같은 인터페이스 
  interface CoffeeMaker{ // prefix 를 I로 붙이거나 , 구현하는 클래스에서 다른 이름 사용 *Impl
    makeCoffee(shots:number):CoffeeCup;
  }

  interface CommercialCoffeeMaker{
    makeCoffee(shots:number):CoffeeCup;
    fillCoffeeBeans(beans:number):void;
    clean():void; 
  }

  class CoffeeMakerImpl implements CoffeeMaker, CommercialCoffeeMaker {
    private static BEANS_GRAMM_PER_SHOT:number = 7; 
    private coffeeBeans : number = 0 ; 
  
    private constructor(coffeeBeans : number){
      this.coffeeBeans = coffeeBeans; 
    }

    static makeMachine(coffeeBeans : number ):CoffeeMakerImpl{
       return new CoffeeMakerImpl(coffeeBeans);
    }

    //외부에서만 동작하는 거니 내부에 노출(호출)할 필요없게 private 처리함
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
  
  // // class 로 정의하게 되면 public한 메소드에 접근 가능함
  // const maker:CoffeeMakerImpl = CoffeeMakerImpl.makeMachine(10);
  // maker.fillCoffeeBeans(10);  
  // maker.makeCoffee(2);

  // // 인터페이스로 정의하게 되면, 인터페이스에서 정의한 메소드만 접근가능함
  // const maker2:CommercialCoffeeMaker = CoffeeMakerImpl.makeMachine(10);
  // maker2.fillCoffeeBeans(10);  
  // maker2.makeCoffee(2);
  // maker2.clean();
  
  class AmateurUser{ // CoffeeMaker 인터페이스를 가져옴 
    constructor(private machine:CoffeeMaker){}
    makeCoffee(){
      const coffee = this.machine.makeCoffee(2);
      console.log(coffee);
    }
  }  

  class ProBarista{// CommercialCoffeeMaker 인터페이스를 가져옴 
    constructor(private machine:CommercialCoffeeMaker){}
    makeCoffee(){
      const coffee = this.machine.makeCoffee(2);
      console.log(coffee);
      this.machine.fillCoffeeBeans(40);
      this.machine.clean();
    }
  }

  
  // class 로 정의하게 되면 public한 메소드에 접근 가능함
  const maker:CoffeeMakerImpl = CoffeeMakerImpl.makeMachine(30);
  const amateur = new AmateurUser(maker);
  const pro = new ProBarista(maker);

  amateur.makeCoffee(); // 인터페이스에 규약된 함수에만 접근 가능 
  pro.makeCoffee();
}
