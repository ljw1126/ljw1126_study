## jsTree 공식 Reference 
[https://www.jstree.com/docs/config/](https://www.jstree.com/docs/config/)

## 전체 선택/해제 
[https://stackoverflow.com/questions/25252409/jstree-i-cant-reset-all-the-checkboxes-to-uncheck-by-default-when-i-reload-my](https://stackoverflow.com/questions/25252409/jstree-i-cant-reset-all-the-checkboxes-to-uncheck-by-default-when-i-reload-my)

## 검색 필드 연계 
[https://everyething.com/Example-of-simple-jsTree-with-Search](https://everyething.com/Example-of-simple-jsTree-with-Search)

## 전체 닫기/열기
- close_all(), open_all()
[https://mkil.tistory.com/436](https://mkil.tistory.com/436)

## 기타참고 
[https://everyething.com/Example-of-jsTree-to-get-all-checked-nodes](https://everyething.com/Example-of-jsTree-to-get-all-checked-nodes)

```

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.2.1/themes/default/style.min.css" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.2.1/jstree.min.js"></script>

// jsTree 이벤트 부분
            $('.search-input').on('keyup, focus',function() {
                $("#jsTree").css({display : "block"});
                const keyword = $(this).val();
                $('#jsTree').jstree('search', keyword);
            });

            $('.all-checked').click(function(e) {
                const checked = $(this).data("checked");

                !checked ? $("#jsTree").jstree(true).check_all() : $("#jsTree").jstree(true).uncheck_all();

                $(this).data("checked", !checked);
            });

            $(".jstree-folding").click(function(e) {
                const folding = $(this).data("folding");

                if (!folding) {
                    $("#jsTree").jstree(true).close_all();
                } else {
                    $("#jsTree").jstree(true).open_all();
                }

                $(this).data("folding", !folding);
            })

// jsTree 설정 부분
setJsTree : function($data) {
            // id, parent : "# || id", text
            console.log($data);

            const data = [];

            let idxMap = new Map();
            let index = 1;
            $data.forEach(function(item) {
                 if (!idxMap.get(item.subCategory)) {
                     data.push({id : index, parent : "#", text : item.subCategory, state : {opened : true, selected : true}});
                     idxMap.set(item.subCategory, index);
                     index++;
                 }

                 data.push({id : index, parent : idxMap.get(item.subCategory) || "#", text : item.eventName, startDate : item.startDate, endDate : item.endDate});
                 index++;
            });

            $("#jsTree").jstree({
                "core" : {
                   "multiple" : true,
                   "data" : data,
                   'themes' : {
                       "icons" : false
                   }
                },
                "checkbox" : {
                    "keep_selected_style" : false
                },
                "search" : {
                    "case_sensitive" : false,
                    "show_only_matches" : true
                },
                "plugins" : ["checkbox", "search"]
            });


        }

    // html 부분
         <input id="search-input" class="search-input">
                    <input type="button" class="btn btn-primary jstree-folding" value="[-]" data-folding="false">
                    <input type="button" class="btn btn-primary all-checked" value="전체선택" data-checked="true">
                    <input type="button" class="btn btn-primary" value="filter">
                    <div id="jsTree" style="display: none;min-height:200px;overflow-y: auto;width: auto;">test</div>

```