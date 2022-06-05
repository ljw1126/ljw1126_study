/**
 * undefined 와 null 
 */
console.log(!null); // true
console.log(!!null); // false
console.log(null === false); // false💩
console.log(!null === true); // true🤦‍♂️

// null => math 적으로 표현하면 => 0 에 가깝다
console.log(null + 123); // 123


/****************************/
var varb;

console.log(varb); // undefined : 변수 선언했지만 값 정의x, 할당되지 않은 상태
console.log(typeof varb); // undefined 
console.log(undefined + 10); // NaN(Not-A-Number : 숫자 아님)

console.log(!undefined); // true💩
console.log(undefined == null); // true💩
console.log(undefined === null); // false💩
console.log(!undefined === !null); // true💩

/**
 * 많은 부분이 헷갈림
 * ✨그래서 비어있는 값을 null 또는 undefined 로 활용하는 것에 대한 team convention이 필요 ! 
 * 
 * 📝정리 
 * undefined, null => 값이 없거나, 정의되지 않은 명시적인 표현 
 * 
 * 수학적으로 
 * > undefined는 NaN 
 * > null은 0에 가까움
 * 
 * 타입으로 봤을 때 
 * > type undefined 
 * > null은 Object 
 * 
 * 그래서 undefined와 null의 쓰임에 대해서 조심해야 한다.
 */