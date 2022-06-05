/**
 * 경계 다루기 (min - max)
 * 
 * 1. 최소와 최대값을 다룬다 
 * 2. 최소값과 최대값 포함 여부를 결정해야 한다.(이상-초과/이하-미만) 
 * 3. 혹은 네이밍에 최소값과 최대값 포함 여부를 포함한다. 
 * 
 * 신경 쓰면 명시적인 코드를 작성가능함(권장)✨
 * 
 * _IN_
 * _LIMIT
 */

// 랜덤 함수
function genRandomNumber(min, max) {
    return Math.floor(Math.random() * (max - min + 1)) + min;
}

// 상수 
const MIN_NUMBER = 1;
const MAX_NUMBER = 45;

// 👨‍💻함수 내부를 보지 않아도 외부 매개변수와 함수명만으로도 명시적인 사용법 예상 가능(표현 good)
console.log(genRandomNumber(MIN_NUMBER, MAX_NUMBER));



/**
 * 그런데 팀마다 매개변수에 대한 경계가 다를 수 있으니 
 * 이상, 이하, 미만, 초과 와 같은 비교연산자에 대한 team convention도 정하는게 좋다✨
 */
const MAX_AGE = 20;

function isAdult(age) {
    // 최소값, 최대값 (포함되는지 vs 안되는지)
    // 이상, 초과 vs 이하, 미만 
    if (age > 20) {

    }
}
