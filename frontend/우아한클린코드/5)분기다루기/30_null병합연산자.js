/**
 * Nullish coalescing operator 
 * 
 * null병합 연산자 - 최근에 나온거라 브라우저 별로 동작 안할 수 있음 
 * 
 * 단축평가 - 혼합해서 쓰면 안됨 , 사람들 실수가 많아 언어상 제약있음 
 * || : falsy 
 * ?? : null과 undefined 평가할때 사용
 * 
 * 참고. Nullish coalescing operator 
 * https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Operators/Nullish_coalescing_operator
 * 
 * 참고. 참 같은 값 과 거짓 같은 값
 * https://developer.mozilla.org/ko/docs/Glossary/Truthy
 * https://developer.mozilla.org/ko/docs/Glossary/Falsy
 */

console.log(!!0); //false , falsy에 귀결됨 

// case 1
function createElement(type, height, width) {
    const element = document.createElement(type || 'div');

    element.style.height = String(height || 100) + 'px';
    element.style.width = String(width || 100) + 'px';

    return element;
}

const el = createElement('div', 0, 0);
console.log(el.style.width); // 100px  , 0은 falsy로 귀결되기 떄문에 
console.log(el.style.height); // 100px , 0은 falsy로 귀결되기 떄문에

// case1 nullish coalescing operator 활용하기 
// 왼쪽 값이 null 또는 undefined일때만 동작 
function createElement2(type, height, width) {
    const element = document.createElement(type ?? 'div');

    element.style.height = String(height ?? 100) + 'px';
    element.style.width = String(width ?? 100) + 'px';

    return element;
}

const el2 = createElement2('div', 0, 0);
console.log(el2.style.width);  // 0 px
console.log(el2.style.height); // 0 px

