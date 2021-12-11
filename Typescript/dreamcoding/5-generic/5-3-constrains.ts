interface Employee {
  pay(): void;
}

class FullTimeEmployee implements Employee {
  pay() {
    console.log(`full time!!`);
  }
  workFullTime() {}
}

class PartTimeEmployee implements Employee {
  pay() {
    console.log(`part time!!`);
  }
  workPartTime() {}
}

// 세부적인 타입을 인자로 받아서 정말 추상적인 타입으로 다시 리턴하는 함수는 💩💩💩
function payBad(employee: Employee): Employee {
  employee.pay();
  return employee;
}

// 세부적인 constrain 제약 조건담, 일반 타입이긴 한테 employee를 구현한 확장한 아이만 가능해
function pay<T extends Employee>(employee: T): T {
  employee.pay();
  return employee;
}

const ellie = new FullTimeEmployee();
const bob = new PartTimeEmployee();
ellie.workFullTime();
bob.workPartTime();

const ellieAfterPay = pay(ellie); // FullTimeEmployee 클래스 타입을 잃어버림 => generic으로 보장
const bobAfterPay = pay(bob); // PartTimeEmployee 클래스 타입을 잃어버릴 수 있음 => generic으로 보장

const obj = {
  name: 'ellie',
  age: 20,
};

const obj2 = {
  animal: '🐕',
};

console.log(getValue(obj, 'name')); // ellie
console.log(getValue(obj, 'age')); // 20
console.log(getValue(obj2, 'animal')); // 🐕

// K extends keyof T : object T에 있는 key 중에 하나가 K 
// 리턴되는 것은 그 object에 있는 key가 가르키고 있는 value 타입이여야 한다고 명시 가능 
function getValue<T, K extends keyof T>(obj: T, key: K): T[K] {
  return obj[key];
}
