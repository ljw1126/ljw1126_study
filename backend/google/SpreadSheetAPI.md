## 기록

#### 전달 받은 sheet url 예시 
```
https://docs.google.com/spreadsheets/d/{sheet id}/edit#gid=9자리난수
```

#### POSTMAN 호출 테스트 
1. https://sheets.googleapis.com/v4/spreadsheets/{sheet id}
   ㄴ 결과는 403 에러 확인
```
# 결과
{
  "error": {
    "code": 403,
    "message": "The request is missing a valid API key.",
    "status": "PERMISSION_DENIED"
  }
}
```

- 4XX 에러가 계속 출력되서 포기 .. 

2. 

```
# BatchGet API
  - BatchGet으로 호출할 경우 지정한 List<String> ranges 만큼 진짜.. 값만 가져옴 
  - 문제는 텍스트에 ',' 구분자가 들어가는게 많은데.. 호따옴표나 쌍따옴표 처리 없이 그냥 들어가 있어 배열 , 와 구분이 되지 않음 ;; 

# SpreadSheet.get API 
  - includeGridData 포함시 사용자가 수정한 메타데이터 포함해서 가져옴 
  - 아래 예시는 "포코포코2022_스케줄(Daily)!A16:OI16" 데이터 호출했을때 결과 일부

{
    "data":[
        {
            "columnMetadata":[], // 의미없어 보임.. {pixelSize: 0}, ... 정보 밖에 존재 안함 💩
            "rowData":[ // 📌 그리드 설정 정보✨
                {
                    "values":[
                        // row 2번 sample 
                        {
                            "effectiveFormat":{},
                            "userEnteredFormat":{}
                        },
                        {
                            "effectiveFormat":{},
                            "effectiveValue":{},
                            "formattedValue":"연간 개발&이벤트 스케줄",
                            "userEnteredFormat":{},
                            "userEnteredValue":{
                                 "stringValue":"연간 개발&이벤트 스케줄"
                            }
                        },
                        {
                            "effectiveFormat":{},
                            "effectiveValue":{
                                "numberValue":44530.0        --- 2021.12.1 
                            },
                            "formattedValue":"11/30",
                            "userEnteredFormat":{},
                            "userEnteredValue":{
                            "numberValue":44530.0
                        }
                        },

                        // row 16번 sample
                        {
                            "effectiveFormat":{
                                "backgroundColor":{ // 📌 병합을 하지 않기때문에 해당 속성값으로 범위를 알아야 할듯..
                                    "green":1.0,
                                    "red":1.0
                                },
                                "backgroundColorStyle":{},
                                "horizontalAlignment":"LEFT",
                                "hyperlinkDisplayType":"PLAIN_TEXT",
                                "padding":{},
                                "textFormat":{},
                                "verticalAlignment":"MIDDLE",
                                "wrapStrategy":"OVERFLOW_CELL"
                            },
                            "effectiveValue":{
                                "stringValue":"12월 콜라보"
                            },
                            "formattedValue":"12월 콜라보",
                            "userEnteredFormat":{},
                            "userEnteredValue":{
                                "stringValue":"12월 콜라보" // 📌 이벤트명은 이게 정확해 보임
                            }
                        },

                    ]
                },
                {
                    .. 다음 행
                }
            ],
            "rowMetadata":[], // 의미 없어 보임 💩
            "startRow":15 // ?, 여러 row 포함되는 Ranges 로 호출하니 해당 field 없음 
        }, 
        /* List<String> ranges 에 범위 지정이 여러개 인 경우 data : [{row 영역범위 데이터} , {row 영역범위 데이터} , ...]
           api에 data 속성이 담기는 클래스는  SpreadSheet> Sheet[] > GridData[]*
            
           ex)  
            String dateDataRow = "포코포코2022_스케줄(Daily)!A2:OI2";   👉 List<GridData> 에 0번 인덱스 존재
            String selectedDataRow = "포코포코2022_스케줄(Daily)!A14:OI44"; 👉List<GridData> 에 1번 인덱스 존재
        */
        {
            ...
        }
    ],
    "merges":[ // 📌지정한 row의 병합 정보인인듯 , 병합 row의 경우 분류로 정의할 수 있지 않을까?
        {
            "startColumnIndex":0,    --- 0 이 존재 안하는데?
            "endColumnIndex":1,      --- row 13 에 A1부터 병합 . -> 가로로 긴 데이터 알아보려고 '이벤트, 매직볼 이벤트, 게임센터'
            "startRowIndex":13       --- 시작 row 
            "endRowIndex":26,        --- 끝 row
            "sheetId":269855735,
        },
        ...
    ],
    "properties":{ // 📌 해당 sheet의 properties 정보 인듯
        "gridProperties":{
            "columnCount":399,      // 열 끝 카운트 (분류 및 제목 2 + 32 + 365 = 399)
            "frozenColumnCount":2,  // 고정 열(col) 뜻하는 듯?
            "frozenRowCount":2,     // 고정 행(row) 뜻하는 듯? 
            "rowCount":952          // 행 끝 카운트 (사용안 해도 그냥 표출되 있는 행 카운트)
        },
        "index":0,
        "sheetId":269855735,
        "sheetType":"GRID",
        "title":"포코포코2022_스케줄(Daily)"
    },
    "rowGroups":[
        {
            "depth":1,
            "range":{
                "dimension":"ROWS",
                "endIndex":57,
                "sheetId":269855735,
                "startIndex":6
            }
        }
    ]
}


```


#### properties 위치 
- com.google.api.services.sheets.v4.model > Sheet.class 안에 SheetProperties properties 호출 가능 
- com.google.api.services.sheets.v4.model > SheetProperties 안에 GridProperties gridProperties 필드 호출 가능
- com.google.api.services.sheets.v4.model > GridProperties 안에 행/열 고정 길이 알 수 있음 
  - Integer columnCount : 열 끝 번호 카운트
  - Integer frozenColumnCount : 고정 열 카운트
  - Integer frozenRowCount : 고정 행 카운트
  - Integer rowCount : 행 끝 번호 카운트


#### options - hAxis 관련 
- reference가 뿔뿔이 흟어져 있어서 여기저기서 찾아야 함 
- https://developers.google.com/chart/interactive/docs/gallery/linechart

#### Axis 별 format 
- MM/dd \nE  ==> 10/25 \n월 
  - https://stackoverflow.com/questions/14062420/google-charts-how-to-line-break-axis-label-into-two-rows-multiple-x-axes


#### row label 을 상단으로 이동.
- option에 **axes** 속성 통해서 처리 가능할 거 같지만 timeline의 경우 되지 않음(22-06-27, 지원하지 않는 것으로 파악)
  > 다른 차트의 경우 stack overflow 주소 확인 [https://stackoverflow.com/questions/32122142/haxis-label-position-google-charts](https://stackoverflow.com/questions/32122142/haxis-label-position-google-charts)
  > 공식 문서에 timeline 유형만 빠짐 [https://developers.google.com/chart/interactive/docs/customizing_axes](https://developers.google.com/chart/interactive/docs/customizing_axes)
- timeline의 경우 어쩔 수 없이, dom 요소 복사해서 처리하는 방식으로 함 
  > 참고 stack overflow [https://stackoverflow.com/questions/55185413/how-to-stick-freeze-an-svg-header-to-top](https://stackoverflow.com/questions/55185413/how-to-stick-freeze-an-svg-header-to-top)
```
- ready 이벤트 동작
    // 차트 그리기
  searchMonthlyEventSchedule : function() {
            const _ = this;

            $.ajax({
                url : "/ajax/report/service/getMonthlyEventSchedule",
                type : "POST",
                data : JSON.stringify(_.getMonthlyEventScheduleParam()),
                contentType : 'application/json',
                dataType : 'json',
                success : function(res) {
                    _.elements.forEach(($element) => {
                        const elementId = "#" + $element.id;
                        const monthlyEventScheduleData = res[$element.category];

                        if (monthlyEventScheduleData.length === 0) {
                            $(elementId).css("min-height", "10px");
                            $(elementId).html("has no data");
                        } else {
                            $(elementId).css("min-height", "450px");

                            const rows = [];
                            monthlyEventScheduleData.forEach((obj) => {
                                const row = [];
                                row.push(obj.subCategory);
                                row.push(obj.eventName);
                                row.push(new Date(obj.startDate));
                                row.push(new Date(obj.endDate));
                                rows.push(row);
                            });

                            chart.draw({
                                firstColumnIsNotDate : true,
                                column : [
                                    { type : 'string', name : 'category' },
                                    { type : 'string', name : 'eventName' },
                                    { type : 'date', name : 'startDate' },
                                    { type : 'date', name : 'endDate' }
                                ],
                                data : rows,
                                chartId : $element.id,
                                type : "timeline",
                                option : {
                                    timeline : {
                                        //colorByRowLabel : true       해당 속성 사용시 row 별로 색상이 일관성은 있는데, 다른 row에 비슷한 색상이 있어서 별로..
                                        singleColor : '#184D41'
                                    },
                                    hAxis : {
                                        format : 'MM/dd \nE',   // E가 요일을 나타내고 \n 들어가니.. <text> 두개 생성됨
                                    },
                                    vAxis : {
                                        gridlines: {
                                            interval : 1
                                        }
                                    }
                                },
                                eventListener : [{
                                    type : 'ready',
                                    callback : function() {
                                        _.moveRowLabelToHeader($element);
                                    }
                                }]
                            });
                        }
                    });
                },
                error: common.ajaxError
            });
        },
        moveRowLabelToHeader : function($element) {
            const header = $("#" + $element.header);
            header.empty();

            const svg = $("#" + $element.id).find("svg:eq(0)");
            header.append(svg.clone());

            const headerSvg = header.find("svg");

            headerSvg.removeAttr("width");
            headerSvg.removeAttr("height");
            headerSvg.css({height: '50px', width: '100%'});

            let idx = 0;
            headerSvg.find("text").each(function() {
                if (idx % 2 === 0) {  // 짝수인 경우 MM/dd
                  $(this).attr("y", "-20");
                } else { // 홀수 인 경우 '요일'
                  const day = $(this).text();
                  $(this).text(day.toKorean); // prototype 선언 해서 날짜 변환해줌
                  $(this).attr("y", "-5");
                }

                idx++;

                $(this).css({transform : 'translate(15px, 50px)'}); // x 축으로 15px 이동, y축으로 50px 이동
                if ($(this).attr("font-weight")) { // font-weight 가 있는 경우와 없는 경우가 있어 걍 다 없애 버림 
                    $(this).removeAttr("font-weight");
                }
            });

            svg.remove();
        }


// 한국어로 요일 변환
String.prototype.toKorean = function() {
    if (!this.textContent) return "";

    switch(this.textContent) {
        case 'Sun' : return '일';
        case 'Mon' : return '월';
        case 'Tue' : return '화';
        case 'Wed' : return '수';
        case 'Thu' : return '목';
        case 'Fri' : return '금';
        case 'Sat' : return '토';
        default : return '';
    }
}

```