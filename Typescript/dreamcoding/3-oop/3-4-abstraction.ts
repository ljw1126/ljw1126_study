{
  /*
    4. abstraction μΆμν 
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
  //     console.log('cleaning the machine...π§Ό');
  //   }

  //   private grindBeans(shots: number) {
  //     console.log(`grinding beans for ${shots}`);
  //     if (this.coffeeBeans < shots * CoffeeMachine.BEANS_GRAMM_PER_SHOT) {
  //       throw new Error('Not enough coffee beans!');
  //     }
  //     this.coffeeBeans -= shots * CoffeeMachine.BEANS_GRAMM_PER_SHOT;
  //   }

  //   private preheat(): void {
  //     console.log('heating up... π₯');
  //   }

  //   private extract(shots: number): CoffeeCup {
  //     console.log(`Pulling ${shots} shots... βοΈ`);
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
    μΆμνλ ?
     μΈν°νμ΄μ€λ₯Ό κ°λ¨νκ² λ§λ¦μΌλ‘μ¨ μ¬μ©μκ° κ°λ¨νκ² μ¬μ©ν  μ μλλ‘ λμμ€ 
     privateμμ λ΄λΆμμλ§ μ¬μ©νκ³  μΈλΆλΈμΆ μλκ² ν¨μΌλ‘μ 
     μΈλΆ λΈμΆλλ μ λ³΄(μ¬μ©κ°λ₯ν ν¨μ)λ§ κ°μ§κ³  μ¬μ©μκ° μ΄μ©ν  μ μλλ‘ λ§λ¦ 
     
     μΈν°νμ΄μ€λ₯Ό μ¬μ©νλ©΄ μΆμνλ₯Ό κ·Ήλνμν¬ μ μμ 
   */


  type CoffeeCup ={
    shots : number;
    hasMilk : boolean;
  }
 
  //κ³μ½μ κ°μ μΈν°νμ΄μ€ 
  interface CoffeeMaker{ // prefix λ₯Ό Iλ‘ λΆμ΄κ±°λ , κ΅¬ννλ ν΄λμ€μμ λ€λ₯Έ μ΄λ¦ μ¬μ© *Impl
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

    //μΈλΆμμλ§ λμνλ κ±°λ λ΄λΆμ λΈμΆ(νΈμΆ)ν  νμμκ² private μ²λ¦¬ν¨
    private grindBeans(shots){
      console.log(`grinding beans for ${shots}`);
      if(this.coffeeBeans < shots * CoffeeMakerImpl.BEANS_GRAMM_PER_SHOT){
        throw new Error('Not enough coffee beans!');
      }
      this.coffeeBeans -= shots * CoffeeMakerImpl.BEANS_GRAMM_PER_SHOT;
    }

    private preheat() : void{
      console.log('heating up ... β€');
    }

    private extract(shots : number): CoffeeCup{
      console.log(`Pulling ${shots} shots....β`);
      return {
        shots,
        hasMilk : false
      }
    }

    makeCoffee(shots : number):CoffeeCup{ //ν¨μ 
      // 1. μ»€νΌ μ½©μ κ°μμΌν¨ 
      this.grindBeans(shots);
      // 2. μ»€νΌ κΈ°κ³ μ€λΉ 
      this.preheat();
      // 3. μ»€νΌ μΆμΆ 
      return this.extract(shots);
   }

    fillCoffeeBeans(beans : number){  
     if(beans < 0 ){
      throw new Error('value for beans should be greater than 0 ');
     }
     this.coffeeBeans = beans;
    }

    clean(){
      console.log('cleaning the machine... π§Ό')
    }
  }
  
  // // class λ‘ μ μνκ² λλ©΄ publicν λ©μλμ μ κ·Ό κ°λ₯ν¨
  // const maker:CoffeeMakerImpl = CoffeeMakerImpl.makeMachine(10);
  // maker.fillCoffeeBeans(10);  
  // maker.makeCoffee(2);

  // // μΈν°νμ΄μ€λ‘ μ μνκ² λλ©΄, μΈν°νμ΄μ€μμ μ μν λ©μλλ§ μ κ·Όκ°λ₯ν¨
  // const maker2:CommercialCoffeeMaker = CoffeeMakerImpl.makeMachine(10);
  // maker2.fillCoffeeBeans(10);  
  // maker2.makeCoffee(2);
  // maker2.clean();
  
  class AmateurUser{ // CoffeeMaker μΈν°νμ΄μ€λ₯Ό κ°μ Έμ΄ 
    constructor(private machine:CoffeeMaker){}
    makeCoffee(){
      const coffee = this.machine.makeCoffee(2);
      console.log(coffee);
    }
  }  

  class ProBarista{// CommercialCoffeeMaker μΈν°νμ΄μ€λ₯Ό κ°μ Έμ΄ 
    constructor(private machine:CommercialCoffeeMaker){}
    makeCoffee(){
      const coffee = this.machine.makeCoffee(2);
      console.log(coffee);
      this.machine.fillCoffeeBeans(40);
      this.machine.clean();
    }
  }

  
  // class λ‘ μ μνκ² λλ©΄ publicν λ©μλμ μ κ·Ό κ°λ₯ν¨
  const maker:CoffeeMakerImpl = CoffeeMakerImpl.makeMachine(30);
  const amateur = new AmateurUser(maker);
  const pro = new ProBarista(maker);

  amateur.makeCoffee(); // μΈν°νμ΄μ€μ κ·μ½λ ν¨μμλ§ μ κ·Ό κ°λ₯ 
  pro.makeCoffee();
}
