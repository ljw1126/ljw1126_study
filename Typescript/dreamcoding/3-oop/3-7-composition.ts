{
  // type CoffeeCup = {
  //   shots: number;
  //   hasMilk?: boolean;
  //   hasSugar?: boolean;
  // };

  // interface MilkFrother {
  //   makeMilk(cup: CoffeeCup): CoffeeCup;
  // }

  // interface SugarSource {
  //   addSugar(cup: CoffeeCup): CoffeeCup;
  // }

  // class CheapMilkSteamer implements MilkFrother {
  //   makeMilk(cup: CoffeeCup): CoffeeCup {
  //     console.log(`Steaming some milk🥛...`);
  //     return {
  //       ...cup,
  //       hasMilk: true,
  //     };
  //   }
  // }

  // class FancyMilkSteamer implements MilkFrother {
  //   makeMilk(cup: CoffeeCup): CoffeeCup {
  //     console.log(`Fancy!!!! Steaming some milk🥛...`);
  //     return {
  //       ...cup,
  //       hasMilk: true,
  //     };
  //   }
  // }

  // class AutomaticSugarMixer implements SugarSource {
  //   addSugar(cuppa: CoffeeCup): CoffeeCup {
  //     console.log(`Adding sugar...`);
  //     return {
  //       ...cuppa,
  //       hasSugar: true,
  //     };
  //   }
  // }

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

  // class SweetCaffeLatteMachine extends CoffeeMachine {
  //   constructor(
  //     beans: number,
  //     private sugar: SugarSource,
  //     private milk: MilkFrother,
  //   ) {
  //     super(beans);
  //   }
  //   makeCoffee(shots: number): CoffeeCup {
  //     const coffee = super.makeCoffee(shots);
  //     const milkCoffee = this.milk.makeMilk(coffee);
  //     return this.sugar.addSugar(milkCoffee);
  //   }
  // }
  // const machine = new SweetCaffeLatteMachine(
  //   32,
  //   new AutomaticSugarMixer(),
  //   new FancyMilkSteamer()
  // );
  // machine.makeCoffee(2);
  /*
    상속의 문제점 
    - 상속의 깊이가 길어질수록 관계가 복잡해질 수 있음 
    - 상속은 수직적인 관계가 형성되게 햄 
    - 부모 클래스 수정시(신기능추가) 모든 자식 클래스에 영향 끼칠 수 있음 ( 치명적 )
    - typescript에서 클래스는 한가지 이상 부모클래스를 상속 x 
      >> 이럴때 composition(구성요소들,구성) 사용함 

      ' Favor COMPOSITION 
       over inheriatance '      //상속대신 컴포지션을 더 선호하라 

       공통적인 행동을 상속 통해 재활용가능했슴 
       > 하지만 추가될 수로 복잡해지니 불필요한 상속 대신 composition 이용해보자 !
  */
  type CoffeeCup ={
    shots : number;
    hasMilk?: boolean;
    hasSugar?: boolean;
  }
 
  interface CoffeeMaker{ 
    makeCoffee(shots:number):CoffeeCup;
  }
  // 추가 6
  interface MilkFrother{
    makeMilk(cup:CoffeeCup):CoffeeCup;
  }
  interface SugarProvider{
    addSugar(cup:CoffeeCup):CoffeeCup;
  }

  // 마지막 수정 , 이거 하나로 모든 커피 머신을 생성가능해짐 
  class CoffeeMakerImpl implements CoffeeMaker {
    private static BEANS_GRAMM_PER_SHOT:number = 7; 
    private coffeeBeans : number = 0 ; 
  
    constructor(
      coffeeBeans : number,
      private milk:MilkFrother,
      private sugar:SugarProvider,
    ){
      this.coffeeBeans = coffeeBeans; 
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
      const coffee = this.extract(shots);
      // 4. 설탕 추가 
      const sugarAdded = this.sugar.addSugar(coffee);
      return this.milk.makeMilk(sugarAdded);
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
  

  class NoMilk implements MilkFrother{
    makeMilk(cup:CoffeeCup):CoffeeCup{
      return cup;
    }
  }

  class NoSuagr implements SugarProvider{
    addSugar(cup:CoffeeCup):CoffeeCup{
      return cup; 
    }
  }

  // class CaffeLatteMachine extends CoffeeMakerImpl {

  //   //overridding 사용 
  //   makeCoffee(shots:number):CoffeeCup{
  //     const coffee = super.makeCoffee(shots);//부모(CoffeeMakerImpl)의 함수 호출 
  //     return this.milkFother.makeMilk(coffee);
  //   }

  //   // 3. 의존성 주입 함!
  //   constructor(
  //     beans : number, 
  //     public readonly serailNumber:string, 
  //     private milkFother : MilkFrother
  //     ){
  //     super(beans); 
  //   }
  // } 

 
  // class SweetCoffeeMaker extends CoffeeMakerImpl{
  //    makeCoffee(shots:number):CoffeeCup{
  //       const coffee = super.makeCoffee(shots);
  //       return this.sugar.addSugar(coffee);
  //    }
  //     //추가 ,설탕 재고 확인 로직이 설탕 필요할때마다 필요하니.. 귀찮아짐 (이런걸 compostion으로 해결하려함 ㄴ)
  //     constructor(
  //       beans : number,
  //       private sugar : SugarProvider){
  //       super(beans);  // 부모 생성자 매개변수 
  //     };
  // }

  // 추가1, 싸구려 우유 거품기 
  class CheapMilkSteamer implements MilkFrother{
    private steamMilk():void{
      console.log('Steaming some milk... 🥛');
    }

    makeMilk(cup:CoffeeCup):CoffeeCup{
      this.steamMilk();
      return {
        ...cup,
        hasMilk : true 
      }
    }
  }

  //추가2, 설탕 제조기 
  class CandySugarMixer implements SugarProvider{
    private getSugar(){ // 내부적으로 어딘가에서 설탕을 가져왔다고 가정
      console.log('Getting some sugar from 🍬');
      return true; 
    }

    addSugar(cup:CoffeeCup):CoffeeCup{
      const sugar = this.getSugar();
      return {
        ...cup, 
        hasSugar : sugar
      }
    }
  }

  // 추가6
  class FancyMilkSteamer implements MilkFrother{
    private steamMilk():void{
      console.log('fancy steaming some milk... 🥛');
    }

    makeMilk(cup:CoffeeCup):CoffeeCup{
      this.steamMilk();
      return {
        ...cup,
        hasMilk : true 
      }
    }
  }

  class ColdMilkSteamer implements MilkFrother{
    private steamMilk():void{
      console.log('cold steaming some milk... 🥛');
    }

    makeMilk(cup:CoffeeCup):CoffeeCup{
      this.steamMilk();
      return {
        ...cup,
        hasMilk : true 
      }
    }
  }

  class FancySugarMixer implements SugarProvider{
    private getSugar(){ // 내부적으로 어딘가에서 설탕을 가져왔다고 가정
      console.log('Fancy Getting some sugar from 🍬');
      return true; 
    }

    addSugar(cup:CoffeeCup):CoffeeCup{
      const sugar = this.getSugar();
      return {
        ...cup, 
        hasSugar : sugar
      }
    }
  }

  class SugarMixer implements SugarProvider{
    private getSugar(){ // 내부적으로 어딘가에서 설탕을 가져왔다고 가정
      console.log('Getting some sugar from 🥥🥝');
      return true; 
    }

    addSugar(cup:CoffeeCup):CoffeeCup{
      const sugar = this.getSugar();
      return {
        ...cup, 
        hasSugar : sugar
      }
    }
  }


    /*
      4. 각각의 기능별로 클래스를 따로 만들어 둚으로서  
      필요한 곳(외부)에서 가져다 쓰는 composition 방법으로 구현해봄 
    */
  // 추가5
  // class SweetCaffeLatteMachine extends CoffeeMakerImpl{
  //   constructor(
  //     private beans : number, 
  //     private milk : MilkFrother,
  //     private sugar : SugarProvider
  //   ){
  //      super(beans); 
  //   }

  //   //오버라이딩함 
  //   makeCoffee(shots:number):CoffeeCup{
  //     const coffee= super.makeCoffee(shots);
  //     return this.milk.makeMilk(this.sugar.addSugar(coffee));
  //   }
  // }

  /*
  하지만 compostion 의 문제점도 있음 
    SweetCaffeLatteMachine 입장에서는 우유거품기, 설탕혼합기 아무거나 와도 상관없는데
    지금같은 상황에서는 지정된거만 사용할 수 있는 제약이 발생함 ( =클래스간의 커플링 )
    > 클래스를 바꾸거나 , 클래스 내용이 바뀌면 연관된 것들을 모두 업데이트 해야함(귀찮..)
  */

  //우유  , 필요한 기능들을 인터페이스 통해 구현함 
  const cheapMilkSteamer = new CheapMilkSteamer();
  const fancyMilkSteamer = new FancyMilkSteamer();
  const coldMilkSteamer = new ColdMilkSteamer();
  const noMilk = new NoMilk();

  //설탕
  const candySugar = new CandySugarMixer();
  const sugar = new SugarMixer();
  const noSugar = new NoSuagr();

  /*
    원하는 용도에 따라 원하는 부품끼워 원하는 용도에 맞는 객체 생성가능 
  */
  const sweetCandyMachine = new CoffeeMakerImpl(12, noMilk, candySugar);
  const sweetMachine = new CoffeeMakerImpl(12,noMilk, sugar);

  const latteMachine = new CoffeeMakerImpl(12, cheapMilkSteamer, noSugar);
  const coldMachine = new CoffeeMakerImpl(12, coldMilkSteamer, noSugar);
  // 지정된 cheapMilkSteamer, candySugar만 사용가능하니 재활용성이 떨어짐 ( 서울우유 사용하려면 다시 만들어야 함 )
  // 인터페이스(계약서)에 의거해서 의사소통(상호작용) 해야 함 !! >> 디 커플링의 원칙 
  const sweetLatterMachine = new CoffeeMakerImpl(
    12,
    cheapMilkSteamer,
    candySugar
  );

 

   
   
}
