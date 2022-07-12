## CSS 속성 

``` 
- 수직 정렬 : 
  
  vertical-align : middle;  // 가운데 정렬 
  
  - 부트스트랩의 경우 table의 td, th에 vertical-align : bottom | top 잡혀있어 가운데 정렬이 안되었음 
  - 이외 부트스트랩에서 지원하는 align-middle-center(?) 와 같은 클래스 형태가 있는 듯(필요시 찾아보기)


  


```

## 모던 자바스크립트 CSS Selector 부분 
[https://poiemaweb.com/css3-selector](https://poiemaweb.com/css3-selector)

## 웹 css 컬러 색상표 
[https://at-corner.tistory.com/17](https://at-corner.tistory.com/17)

## CSS 스타일 규칙 가이드 
- 구글 
- 네이버 

## MDN CSS 가이드 
[https://developer.mozilla.org/ko/docs/Learn/Getting_started_with_the_web/CSS_basics](https://developer.mozilla.org/ko/docs/Learn/Getting_started_with_the_web/CSS_basics)

## 웹 접근성과 웹 표준 
[https://seulbinim.github.io/WSA/font.html#font%E2%80%93kerning-%EC%86%8D%EC%84%B1](https://seulbinim.github.io/WSA/font.html#font%E2%80%93kerning-%EC%86%8D%EC%84%B1)

## CSS : 반응형 웹(Responsive Web) - (예전)넥스트리소프트 홈
[https://www.nextree.co.kr/p8622/](https://www.nextree.co.kr/p8622/)

## 테이블 th/td 대각선 넣기 
[https://zetawiki.com/wiki/HTML_table_%EB%8C%80%EA%B0%81%EC%84%A0_%EA%B7%B8%EB%A6%AC%EA%B8%B0](https://zetawiki.com/wiki/HTML_table_%EB%8C%80%EA%B0%81%EC%84%A0_%EA%B7%B8%EB%A6%AC%EA%B8%B0)

```
<style>
.back-slash {
    text-align: left;
}
.back-slash div {
    text-align: right;
}
.back-slash {
    background: url('data:image/svg+xml;utf8,<svg xmlns="http://www.w3.org/2000/svg"><line x1="0" y1="0" x2="100%" y2="100%" stroke="black" /></svg>');
}
</style>
<table>
  <thead>
    <tr>
      <th scope="col" class="back-slash" rowspan="2"><div>오른쪽 위 ▶</div>왼쪽 아래 ▼</th>
    </tr>
  </thead>
  ...
</table>

```