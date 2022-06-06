/**
 * 드모르간 법칙
 * 
 * 공식1. 
 * if (!(A && B)){
 * }
 * 👇
 * if(!A || !B) {
 * }
 * 
 * 공식2.
 * if (!(A || B)) {
 * }
 * 👇
 * if (!A && !B) {
 * }
 * 
 * true is not true (?)
 * false is not false (?)
 */

const isValidUser = true; 
const isValidToken = true;

if (isValidToken && isValidUser) {
    console.log('로그인 성공')
}

// 추가 요구사항 
if (!(isValidToken && isValidUser) ... && someLoginc) {
    console.log('로그인 실패')
}

