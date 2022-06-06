/**
 * Truthy & Falsy 
 * 
 * javascript는 형변환이 자유로운 언어
 * Truthy 와 Falsy를 사용해서 조건문을 좀 더 줄 일 수 있다.
 * 
 * 참고. 참 같은 값 과 거짓 같은 값
 * https://developer.mozilla.org/ko/docs/Glossary/Truthy
 * https://developer.mozilla.org/ko/docs/Glossary/Falsy
 */

if('string'.length) { // true 

}

if (10) { // true

}

if (true) { // true

}

/** 
// MDN에 있는 예시. 참 값은 값 
if (true)
if ({})
if ([])
if (42)
if ("0")
if ("false")
if (new Date())
if (-42)
if (12n)
if (3.14)
if (-3.14)
if (Infinity)
if (-Infinity)

// MDN에 있는 예시. 거짓 같은 값
if (false)
if (null)
if (undefined)
if (0)
if (-0)
if (0n)
if (NaN)
if ('')
*/


function printName(name) {
    //if (name === undefined || name === null) {
    if(!name) { // falsy 에 부정연산자 붙여서 사용
        return '사람이 없네요';
    }

    return '안녕하세요 ' + name + '님';
}

console.log(printName());