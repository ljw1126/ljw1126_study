{
  /*
    inheritance (상속)
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
  
  // 상속을 이용한 카페라떼 머신 구현 , 부모에서 하던 기능을 재사용하고 자식 커스텀 기능을 추가/사용가능 
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

    /*
      자식 class에서 constructor 생성시 (부모 클래스생성자) super()를 꼭 호출해야함  
    */
    constructor(beans : number , public readonly serailNumber:string){
      super(beans); // 자식클래스 생성자 사용시 부모클래스에 필요한 데이터도 넣어줌 
    }
  } 

  const machine = new CoffeeMakerImpl(23); // 생성자를 protected로 하니 접근이 안되어 public 변경 
  const latteMachine = new CaffeLatteMachine(23, 'SSSS'); // CaffeLatteMachine 은 CoffeeMakerImpl을 상속했기때문에 CoffeeMakerImpl이 가진 public 함수,변수 사용가능
  const coffee = latteMachine.makeCoffee(1);
  console.log(coffee);
  console.log(latteMachine.serailNumber);


}
