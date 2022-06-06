/**
 * map vs forEach  
 * 
 * 차이
 * 1. return 
 *    forEach 의 return은 undefined   -- callback 을 실행시키는 동작
 *    map 의 return 은 callbock 결과를 모은 새로운 배열  -- callback 결과값을 새로운 배열에 생성(원본 그대로)
 *    https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/Array/forEach
 *    https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/Array/map
 */

const prices = ['1000', '2000', '3000'];

const newPricesForEach = prices.forEach((price) => price + '원');
const newPriceMap = prices.map((price) => price + '원');

console.log(newPricesForEach); // undefined
console.log(newPriceMap); // [ '1000원', '2000원', '3000원' ]

// 둘다 동일하게 출력되나 명세에 맞게 사용하기
prices.forEach((price) => console.log(price + '원'));
prices.map((price) => console.log(price + '원'));