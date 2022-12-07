## attr, prop, is 

**attr()**의 경우 
    - html 속성(attribute)을 다룸
    - dom element 에 해당 속성이 명시되어 있을 경우 지정한 속성의 값을 가져옴 
      (만약 속성이 없을 경우 undefined를 리턴함)

**prop()**의 경우 
    - javascript property 를 다룸 
    - 
    (동적 생성된 element에도 지원됨)

#### 
*.attr() - element 가 가지는 속성값에 대해 조회/변경하는 형식 
*.prop() - element 가 가지는 상태값(disable 활성화여부, checked, selected 선택여부..)을 제어하는 업무


#### 개행 문자 처리(enter)

- textarea 입력값 저장시 줄바꿈은 \n (생략된) 형태로 DB 저장됨 
- jsp 표출시 <br> 태그만 인식해서 줄바꿈 안되는 형상 확인 
- 이 상태로 textarea에 넣으면 줄바꿈 되는 웃기는 현상 확인 

👩‍💻 String.prototype에 개행문자 변환 함수 2개 추가해서 사용함

```javascript 
String.prototype.convertNewLineToTag = function() { // 저장시 사용
	return this.replace(/\n/g, "<br>");
}
String.prototype.reverseTagToNewLine = function() { // 댓글 수정시 textarea 에서 줄바꿈 하기 위해 사용
	return this.replaceAll(/(<br>)/g, "\n");
}
```

#### 페이징 


```javascript 
// 호출 
loadHistoryLogModal : function(issueAndSuggestId = "", page = 1) {
        if (issueAndSuggestId === "") return;

        const _ = this;
        const _modal = _.$obj.modal;
        _modal.target.modal("show");
        _modal.body.showLoadingImage();

        ajaxManager.simpleRequest("/ajax/supports/getIssueAndSuggestLogs", {
            issueAndSuggestId : issueAndSuggestId,
            page : page
        }).then(response => {
            _modal.paginationInfo.text(`총 ${response.totalCount} 건, 현재 ${response.currentPage}/${response.totalPage}`);
            _modal.body.html(Mustache.render(_modal.listTemplate, response.data));

            common.generatePagination({
                onclick: (page) => _.loadHistoryLogModal(issueAndSuggestId, page),
                $target: _modal.pagination,
                startPage: response.startPage,
                endPage: response.endPage,
                totalPage: response.totalPage,
                currentPage: page,
                numOfPageList: response.numOfPageList
            });
        }).catch(console.log);
    }


// common.js => common 전역 객체에 선언된 페이징 함수 사용
paginationTemplate : "<ul class='pagination'>{{#.}}<li class='{{className}}'><a href='javascript:void(0)' data-currency='{{currency}}' data-page='{{page}}'>{{text}}</a></li>{{/.}}</ul>",
generatePagination : function (param){
    const numOfPageList = param.numOfPageList;
    const totalPage = param.totalPage;
    const startPage = param.startPage;
    const endPage = param.endPage;
    const currentPage = param.currentPage;

    const data = [];

    if(currentPage > numOfPageList && startPage > numOfPageList) {
        data.push({text : "«", page : 1});
        data.push({text : "‹ ", page : startPage - 1});
    }

    for(let i = startPage; i <= endPage; ++i) {
        data.push({text : i, page : i, className : i === currentPage ? "active" : ""});
    }

    if(endPage < totalPage) {
        data.push({text: "›", page: endPage + 1});
        data.push({text : "»", page : totalPage});
    }

    param.$target.html(Mustache.render(this.paginationTemplate, data));
    param.$target.find(".pagination a").click(function(){
        param.onclick($(this).data("page"));
    });
},

```