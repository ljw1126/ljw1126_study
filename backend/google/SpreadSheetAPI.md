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
