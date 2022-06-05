/**
 * prefix - suffix
 * 접두사와 접미사
 * 
 * 개발언어에서 사용되는 prefix 네이밍 규칙 예시  
 * - getter, setter 예약어
 * - react 계열에 hook 이름보면 use* 로 되어 있음
 * - jquery 의 $ (prefix)
 * - javscript에서 클래스의 private한 멤버 변수 선언시
 * 
 * # 과거 
 * function Func(name) {
 *      this._name = name;    // _ 언더 스코어 사용 
 * }
 * 
 * # 현재
 * class Func(name) {
 *    #name = name;       // #, prefix 사용함으로써 private field 로 인정 
 * }
 * 
 * 참고. _ (언어스코어) 사용하는 라이브러리
 * - underscore.js
 * - lodash.js
 */

// 결국 명시적 일관성을 지키기 위해 잘 정하는게 좋다 ! ✨👨‍💻