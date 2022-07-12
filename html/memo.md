## hash
- 책갈피 용도, 주소 파라미터에 # 으로 파라미터 붙임 .
- hash값에 대한 이벤트도 등록가능
- Live지표 > KPI Report > 포코팡에서 search 했을때 hash가 url에 붙음 .. 
  - 화면 screenshot 변환과 다운로드 처리 위해 사용하는거 같은데.. 
```
<!--script-->
 location.hash = "GET파라미터"  👉 http://localhost:8080/#GET파라미터

 $(window).on("hashchange", function(){

 });

```

## html2canvas 라이브러리 (웹화면 캡처)
- screenshot을 만들 수 있는 매우 가벼운 라이브러리 
- 깃 허브 : https://github.com/niklasvh/html2canvas

```javascript
        ...,
        screenshotAndDownload : function() {
            const dateType = $(".dateType:checked").val(); // 체크 된 값 가져옴
            const $targets = $(".download-screenshot-" + dateType); // target dom 찾음

            $targets.each(function (index) { // 반복문 돌면서 그림 .. 
                const $this = $(this);
                html2canvas($this[0]).then(function (canvas) {
                    const link = document.createElement('a');
                    link.href = canvas.toDataURL(); // *.toDataURL() : png타입의 base64인코딩된 data url 형식의 문자열을 반환
                    link.download = index;
                    document.body.appendChild(link);
                    link.click();
                    document.body.removeChild(link);
                });
            });
        }
```

#### 자바인데 
- 초기화 블록 https://hashcode.co.kr/questions/654/%EC%9E%90%EB%B0%94%EC%97%90%EC%84%9C-static-%EB%B8%94%EB%A1%9D%EC%9D%80-%EB%AC%B4%EC%97%87%EC%9D%84-%EC%9D%98%EB%AF%B8%ED%95%98%EB%82%98%EC%9A%94
```java 
static {

}

```

#### 참고 사이트
css 선택자 
https://code.tutsplus.com/ko/tutorials/the-30-css-selectors-you-must-memorize--net-16048

display 속성에 대해 
https://www.daleseo.com/css-display-inline-block/

html 태그별 display 
https://calmdawnstudio.tistory.com/51

css 다루기 
http://contents2.kocw.or.kr/KOCW/document/2016/chungbuk/choimin/7.pdf