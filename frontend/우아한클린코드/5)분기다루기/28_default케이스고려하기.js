/**
 * Default case 고려하기 
 * 
 * 명시적 에티켓 만들어 둠 -> 편의성 향상 , 사용자 실 수 예방
 * 
 * 참고.  Swiper Reach Components 에 default 
 * https://swiperjs.com/react
 */

// ex1
function sum(x, y) {
    x = x || 1;
    y = y || 1;

    return x + y;
}

sum(2+2);
sum(); // 2가 출력, 명시적 애티켓.


// ex2. 코어한 라이브러리 팀에서는 이러한 default case를 고려 
// 안전하고 확장성 높은 코드 작성 가능 
function createElement(type, height, width) {
    const element = document.createElement(type || 'div');

    element.style.height = height || 100;
    element.style.width = width || 100;

    return element;
}

createElement();

// 사용자 입력이 잘못될 경우 에러를 출력하거나 하는 환경,애티켓이 필요 (try catch 등)

// ex3. 이미 만들어진 코드를 좀 더 안전하고 편리하게 
function safeParseInt(number, radix) {
    return parseInt(number, radix || 10);
}
