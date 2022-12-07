
### @supports
- 미디어 쿼리 문법과 사용방법이 유사
- 조건 지원하는 브라우저의 경우 중괄호{} 내용을 적용하게됨

> @supports (조건) { 적용 css }

#### 참고 
[https://abcdqbbq.tistory.com/71](https://abcdqbbq.tistory.com/71)

#### 문제 
- 상단 메뉴바 고정 위해 bootstrap에서 지원하는 .sticky-top 을 사용해서 처리함 
- 그런데 실서버 반영시 동작 x 
- 확인 결과 minify 하면서 공백 제거 되서 동작이 제대로 이뤄지지 않은 

```
// minify 전
@supports ((position: -webkit-sticky) or (position: sticky)) {
  .sticky-top {
    position: -webkit-sticky;
    position: sticky;
    top: 0;
    z-index: 1020;
  }
}

// minify 결과 👉 or 뒤에 공백이 사라져서 제대로 동작 x 
                                     ✔       
@supports((position:-webkit-sticky) or(position:sticky)){.sticky-top{position:-webkit-sticky;position:sticky;top:0;z-index:1020}}


// 그래서 조건절 따로 분리하여 처리
@supports (position: -webkit-sticky) {
    .sticky-top {
        position: -webkit-sticky;
        top: 0;
        z-index: 1020;
    }
}
@supports (position: sticky) {
    .sticky-top {
        position: sticky;
        top: 0;
        z-index: 1020;
    }
}

```

### 부트스트랩 dropdown 
- 파일변환시 사용하려 했는데 .. csv 변환시 display:none일 경우 안 먹힘
```
<div class="dropdown">
    <button type="button" class="btn btn-secondary" data-toggle="dropdown">
        <img src="/resources/images/svg/download.svg" alt="테이블 파일 변환 다운로드"/>
    </button>
    <ul class="dropdown-menu">
        <li><a class="dropdown-item convert-table-to-file" data-extension="xlsx">xlsx</a></li>
        <li><a class="dropdown-item convert-table-to-file" data-extension="csv">csv</a></li>
    </ul>
</div>


// javascript
$("a.convert-table-to-file").click(function() {
        const extension = $(this).data("extension");

        const files = [];
        $("table.data-table").each((index, table) => files.push(common.toConvertFile($(table), $(table).data("file-name"), extension)));

        common.convertFile2Zip(files);
    });
```