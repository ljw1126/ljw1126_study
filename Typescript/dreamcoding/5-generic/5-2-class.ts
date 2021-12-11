// either: a or b
interface Either<L, R> { // 인터페이스에 generic 타입 설정 , 결국 사용자가 나중에 설정한 타입을 따라감
  left: () => L;
  right: () => R;
}

class SimpleEither<L, R> implements Either<L, R> {
  //내부적으로 사용되므로 private 지정함 , 이렇게 생성자에 매개변수 private 하면 지역변수 생성됨 (눈에 안보이는)
  constructor(private leftValue: L, private rightValue: R) {}
  left(): L {
    return this.leftValue;
  }

  right(): R {
    return this.rightValue;
  }
}
const either: Either<number, number> = new SimpleEither(4, 5); // 전달인자 타입에 따라 generic타입 결정 
either.left(); // 4
either.right(); //5
const best = new SimpleEither({name: 'ellie'}, 'hello');
console.log(best.left().name); //ellie
console.log(best.right()); // hello
