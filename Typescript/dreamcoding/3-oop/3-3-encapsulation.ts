{
  // 3. 캡슐화 
  // type CoffeeCup = {
  //   shots: number;
  //   hasMilk: boolean;
  // };

  // // public
  // // private
  // // protected
  // class CoffeeMaker {
  //   private static BEANS_GRAMM_PER_SHOT: number = 7; // class level
  //   private coffeeBeans: number = 0; // instance (object) level

  //   private constructor(coffeeBeans: number) {
  //     this.coffeeBeans = coffeeBeans;
  //   }

  //   static makeMachine(coffeeBeans: number): CoffeeMaker {
  //     return new CoffeeMaker(coffeeBeans);
  //   }

  //   fillCoffeeBeans(beans: number) {
  //     if (beans < 0) {
  //       throw new Error('value for beans should be greater than 0');
  //     }
  //     this.coffeeBeans += beans;
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

  // const maker = CoffeeMaker.makeMachine(32);
  // maker.fillCoffeeBeans(32);

  // class User {
  //   get fullName(): string {
  //     return `${this.firstName} ${this.lastName}`;
  //   }
  //   private internalAge = 4;
  //   get age(): number {
  //     return this.internalAge;
  //   }
  //   set age(num: number) {
  //     if (num < 0) {
  //     }
  //     this.internalAge = num;
  //   }
  //   constructor(private firstName: string, public lastName: string) {}
  // }
  // const user = new User('Steve', 'Jobs');
  // user.age = 6;
  // console.log(user.fullName);



  type CoffeeCup ={
    shots : number;
    hasMilk : boolean;
  }
  // public ( default )
  // private : 클래스 외부에서 절대 접근 불가 
  // protected : 클래스 외부에서 접근 불가하지만, 상속한 자식클래스에서만 접근가능 
  class CoffeeMaker {
    private static BEANS_GRAMM_PER_SHOT:number = 7; 
    private coffeeBeans : number = 0 ; 
  
    private constructor(coffeeBeans : number){
      this.coffeeBeans = coffeeBeans; 
    }

    makeCoffee(shots : number):CoffeeCup{ //함수 
        if( this.coffeeBeans < shots * CoffeeMaker.BEANS_GRAMM_PER_SHOT){
          throw new Error('Not enought coffee Bean!');
        }
  
        this.coffeeBeans -= shots * CoffeeMaker.BEANS_GRAMM_PER_SHOT;
  
        return { 
          shots, 
          hasMilk : false 
        }
    }

    static makeMachine(coffeeBeans:number) : CoffeeMaker{ // class 내에 변수를 사용하지 않기 때문에 
      return new CoffeeMaker(coffeeBeans);
    }

    //외부에서 직접 속성을 호출하지 않고 , 함수 통해 속성값 처리 
    //public , 인자가 유효한지 높여 안정성 있게 속성값 처리 가능 
    fillCoffeeBeans(beans : number){  
      if(beans < 0 ){
        throw new Error('value for beans should be greater than 0 ');
      }
      this.coffeeBeans = beans;
    }
  }

  const maker = CoffeeMaker.makeMachine(2);  //new CoffeeMaker() 생성자 호출하는거  
  maker.fillCoffeeBeans(10);
  /*
   문제 1 . coffeeBenas를 설정가능한데 , 잘못된 값 기입 가능 🤷‍♀️
   maker.coffeeBeans = -39; // 💩invallid , 외부에서 object 상태를 유효하지 않게 함

   - 생성자를 private로 만들면 , public한 함수통해 object를 생성하는 방법을 만들어야함 
   - 클래스를 만들때 외부에서 접근할 수 있는건 무엇인지, 내부적으로 가지고 있어야 되는게 무엇인지
     결정가능 
   
     따라서 이런걸 고려해서 클래스 설계 하는 것이 중요 👍 
   */

   // Getter, Setter 
   class User{
     get fullNm():string{ // get ,set 사용시 멤버변수처럼 접근가능 ! 
       return `${this.firstNm} ${this.lastNm}`; 
     }
     // 생성자에 매개변수로 선언을 하면 맴버변수로 사용가능 ! ( 더 깔끔 )
     constructor(private firstNm : string, private lastNm : string){}
     
     private internalAge = 4; 
     get age():number{ 
       return this.internalAge;
     }

     set age(num : number){
       if(num < 0 ){
         throw new Error('invalid age value ');
       }
       this.internalAge = num ; 
     }
  
    }

  const user = new User('Steve', 'Jobs');
  console.log(user.fullNm);
  user.age = 6 ; // 멤버변수 같아 보이지만 , setter를 호출해서 internalAge 갱신가능
}
