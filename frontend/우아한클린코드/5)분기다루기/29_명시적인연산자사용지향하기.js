/**
 * 명시적인 연산자 사용 지향하기 
 * 
 * 팁. 항상 괄호✨를 달아 주는게 좋음 -> 명시적
 * 
 * 예측 가능하고 디버깅 하기 쉽도록 만들기!
 * 연산자 우선 순위 외울 필요없이 괄호로 표기해서 명시적으로 표현 ((x + y) * z)
 * 증감 연산자를 사용하는 것을 지양.
 * 
 * 참고. 연산자 우선 순위 
 * https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Operators/Operator_Precedence
 */

// 예측 가능한 앱을 만들도록 노력해야 한다. 

let number;

function decrement() {
    number--; //💩

    number = number - 1; //✨
}

function increment() {
    number++; //💩

    number = number + 1; //✨
}