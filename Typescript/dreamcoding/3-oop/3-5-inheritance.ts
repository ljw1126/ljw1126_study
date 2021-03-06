{
  /*
    inheritance (์์)
  */
  // type CoffeeCup = {
  //   shots: number;
  //   hasMilk: boolean;
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
  //     console.log('cleaning the machine...๐งผ');
  //   }

  //   private grindBeans(shots: number) {
  //     console.log(`grinding beans for ${shots}`);
  //     if (this.coffeeBeans < shots * CoffeeMachine.BEANS_GRAMM_PER_SHOT) {
  //       throw new Error('Not enough coffee beans!');
  //     }
  //     this.coffeeBeans -= shots * CoffeeMachine.BEANS_GRAMM_PER_SHOT;
  //   }

  //   private preheat(): void {
  //     console.log('heating up... ๐ฅ');
  //   }

  //   private extract(shots: number): CoffeeCup {
  //     console.log(`Pulling ${shots} shots... โ๏ธ`);
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
  //     console.log('Steaming some milk... ๐ฅ');
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

  // const machine = new CoffeeMachine(23);
  // const latteMachine = new CaffeLatteMachine(23, 'SSSS');
  // const coffee = latteMachine.makeCoffee(1);
  // console.log(coffee);
  // console.log(latteMachine.serialNumber);


  type CoffeeCup ={
    shots : number;
    hasMilk : boolean;
  }
 
  interface CoffeeMaker{ 
    makeCoffee(shots:number):CoffeeCup;
  }

  class CoffeeMakerImpl implements CoffeeMaker {
    private static BEANS_GRAMM_PER_SHOT:number = 7; 
    private coffeeBeans : number = 0 ; 
  
    // ์์(extends)ํ? ๊ฒฝ์ฐ private๋ ์๋จ , ๊ทธ๋์ public์ด๋ protected๋ก ๊ณ?์ณ์ฌ์ฉ
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
      console.log('heating up ... โค');
    }

    private extract(shots : number): CoffeeCup{
      console.log(`Pulling ${shots} shots....โ`);
      return {
        shots,
        hasMilk : false
      }
    }

    makeCoffee(shots : number):CoffeeCup{ //ํจ์ 
      // 1. ์ปคํผ ์ฝฉ์ ๊ฐ์์ผํจ 
      this.grindBeans(shots);
      // 2. ์ปคํผ ๊ธฐ๊ณ ์ค๋น 
      this.preheat();
      // 3. ์ปคํผ ์ถ์ถ 
      return this.extract(shots);
   }

    fillCoffeeBeans(beans : number){  
     if(beans < 0 ){
      throw new Error('value for beans should be greater than 0 ');
     }
     this.coffeeBeans = beans;
    }

    clean(){
      console.log('cleaning the machine... ๐งผ')
    }
  }
  
  // ์์์ ์ด์ฉํ ์นดํ๋ผ๋ผ ๋จธ์? ๊ตฌํ , ๋ถ๋ชจ์์ ํ๋ ๊ธฐ๋ฅ์ ์ฌ์ฌ์ฉํ๊ณ? ์์ ์ปค์คํ ๊ธฐ๋ฅ์ ์ถ๊ฐ/์ฌ์ฉ๊ฐ๋ฅ 
  class CaffeLatteMachine extends CoffeeMakerImpl {
    

    //overridding ์ฌ์ฉ 
    makeCoffee(shots:number):CoffeeCup{
      const coffee = super.makeCoffee(shots);//๋ถ๋ชจ(CoffeeMakerImpl)์ ํจ์ ํธ์ถ 
      this.steamMilk(); //์์ํด๋์ค ๋ด๋ถ ํจ์ ์์ฑ
      return {
          ...coffee,
          hasMilk:true 
        }
      }

    private steamMilk():void{
      console.log('Steaming some milk... ๐ฅ');
    }

    /*
      ์์ class์์ constructor ์์ฑ์ (๋ถ๋ชจ ํด๋์ค์์ฑ์) super()๋ฅผ ๊ผญ ํธ์ถํด์ผํจ  
    */
    constructor(beans : number , public readonly serailNumber:string){
      super(beans); // ์์ํด๋์ค ์์ฑ์ ์ฌ์ฉ์ ๋ถ๋ชจํด๋์ค์ ํ์ํ ๋ฐ์ดํฐ๋ ๋ฃ์ด์ค 
    }
  } 

  const machine = new CoffeeMakerImpl(23); // ์์ฑ์๋ฅผ protected๋ก ํ๋ ์?๊ทผ์ด ์๋์ด public ๋ณ๊ฒฝ 
  const latteMachine = new CaffeLatteMachine(23, 'SSSS'); // CaffeLatteMachine ์ CoffeeMakerImpl์ ์์ํ๊ธฐ๋๋ฌธ์ CoffeeMakerImpl์ด ๊ฐ์ง public ํจ์,๋ณ์ ์ฌ์ฉ๊ฐ๋ฅ
  const coffee = latteMachine.makeCoffee(1);
  console.log(coffee);
  console.log(latteMachine.serailNumber);


}
