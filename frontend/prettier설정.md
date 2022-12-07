## 배포시 
- eslint, prettier 설정 파일 외에 package.json 파일 필요함 
- node_modules, package-lock.json은 불필요함 => 삭제 후 재설치 가능 
```
$ git clean -dn  
$ git clean -fn 
$ rm -rf package-lock.json
$ rm -rf node-modules 

# node_modules 재설치 
$ npm install
```


## intellj 설정 
1. Prettier Plugin 설치 후 재시작

> 적용 단축키 : Alt + Shift + Ctrl + P 

2. dependency 추가
```
$ npm install --save-dev prettier 
```

3. Settings (Ctrl + Alt + s) > Language & Frameworks > JavaScript > Prettier 설정 
![Alt text](/images/frontend/prettier-setup-in-intellij.png)

4. 명령어 실행 
```
$ prettier --check .      // 지정된 경로, 확장자 파일에 대해 이상있는 파일 목록 출력해 줌
$ prettier --write .      // .. 전체 적용같은 느낌이.
```

5. .prettierignore 생성 (https://prettier.io/docs/en/ignore.html)
- 무시되는 폴더에 대해 작성하는 듯함
- root 경로 생성 (파일 생성시 intellij 에서 .ignore plugin 설치 추천 하면 설치하면 됨)


#### 공식 사이트 
[https://prettier.io/docs/en/options.html]('https://prettier.io/docs/en/options.html' 'Prettier Option Document')
[https://runebook.dev/ko/docs/prettier/ignore]('https://runebook.dev/ko/docs/prettier/ignore' 'Prettier 번역 사이트')

## .prettierrc.js

```
module.exports = {
    속성 : 값
}    
```

1. Print Width  ( https://prettier.io/docs/en/options.html#print-width )
```
라인 당 코드 보여지는 넓이 

# 설정 

    printWidth : <int>    --- default : 80 

## Before :: (printWidth : 200 설정 또는 없을 경우)
    
    loadProbabilityOnLevelDifficulty: function (_probabilityOnLevelDifficulty, id, additionalDateOption = {}) {
      const parameter = visualization.getRequestParam(id, additionalDateOption);

      visualization.loadAndDrawTable(_probabilityOnLevelDifficulty['probabilityOnLevelDifficultyTable'], parameter, (response, _module) => {
        const $table = $('#' + _module.table.id);
        const colsAndRows = visualization.getTableColsAndRows(response);
        visualization.drawTable($table, colsAndRows, {
          table: { tbody: visualization.getDynamicTbodyTemplate(response) }
        });
        visualization.renderClearRateAndHurdleAvgTfoot($table, colsAndRows, _module.template.table.tfoot);
      });

      visualization.loadAndDrawChart(_probabilityOnLevelDifficulty['probabilityOnLevelDifficultyChart'], parameter);
    }

## After :: (printWidth : 100 설정)
    loadProbabilityOnLevelDifficulty: function (
      _probabilityOnLevelDifficulty,
      id,
      additionalDateOption = {}
    ) {
      const parameter = visualization.getRequestParam(id, additionalDateOption);

      visualization.loadAndDrawTable(
        _probabilityOnLevelDifficulty['probabilityOnLevelDifficultyTable'],
        parameter,
        (response, _module) => {
          const $table = $('#' + _module.table.id);
          const colsAndRows = visualization.getTableColsAndRows(response);
          visualization.drawTable($table, colsAndRows, {
            table: { tbody: visualization.getDynamicTbodyTemplate(response) }
          });
          visualization.renderClearRateAndHurdleAvgTfoot(
            $table,
            colsAndRows,
            _module.template.table.tfoot
          );
        }
      );

      visualization.loadAndDrawChart(
        _probabilityOnLevelDifficulty['probabilityOnLevelDifficultyChart'],
        parameter
      );
    }, 
    // .. 이하 생략

## default 80으로 설정하는 경우 
    loadStageReward: function (_stageReward, id, additionalDateOption = {}) {
      const parameter = visualization.getRequestParam(id, additionalDateOption);

      visualization.loadAndDrawTable(
        _stageReward['stageRewardTable'],
        parameter,
        (response, _module) =>
          visualization.drawTable(
            $('#' + _module.table.id),
            { cols: response.cols, rows: response.rows },
            {
              table: { tbody: visualization.getDynamicTbodyTemplate(response) }
            }
          )
      );

      visualization.loadAndDrawChart(
        _stageReward['stageRewardChart'],
        parameter
      );
    },   

```

<br/>

2. Tab Width ( https://prettier.io/docs/en/options.html#tab-width )
```
코드 라인당 indent(들여쓰기)

# 설정 

    tabWidth : <int> --- default 2

## Before (default == { tabWidth : 2 })
$(function () {
  const comboBoxManager = {
    template:
      '{{#.}}<option value="{{id}}" data-event-id="{{eventId}}" data-start-date="{{startDate}}" data-end-date="{{endDate}}">{{title}}</option>{{/.}}',
    init: function () {
      this.executeLoadChapter(true);
    },    
    // 이하 생략 

## After (tabWidth : 3 설정)
$(function () {
   const comboBoxManager = {
      template:
         '{{#.}}<option value="{{id}}" data-event-id="{{eventId}}" data-start-date="{{startDate}}" data-end-date="{{endDate}}">{{title}}</option>{{/.}}',
      init: function () {
         this.executeLoadChapter(true);
      },
      // 이하생략
      
```

<br/>

3. Tabs ( https://prettier.io/docs/en/options.html#tabs )
```
들여쓰기를 공백 대신 tabs로 처리
*일반적으로 사용하지 않는 기능이며, 띄어쓰기 대신 탭을 사용하여 간격을 조정하고 싶을때 활성화 함

# 설정 

    useTabs : <bool>   -- default false 

```

<br/>

4. ✨Semicolons ( https://prettier.io/docs/en/options.html#semicolons )
```
구문 끝에 무조건 ; (세미콜론) 삽입

# 설정 

    semi : <bool>  --- default true

## Before 
    this.executeLoadChapter(true)      // 세미 콜론 없어도 상관 없음 

## After 
    this.executeLoadChapter(true);     // 구문 끝에 자동으로 세미콜론 붙여줌    
```

<br/>

5. Quotes ( https://prettier.io/docs/en/options.html#quotes )
```
큰 따옴표("something") 대신 작은 따옴표('something') 사용여부

# 설정 

    singleQuote : <bool>  --- default false

## Before (false)
    "/ajax/load/chapters"    // 그대로 

## After (true)
   '/ajax/load/chapters'    // 자동변환 해줌 
```

<br/>

6. Quote Props ( https://prettier.io/docs/en/options.html#quote-props )
```
객체 속성 표현시 따옴표 적용 설정

# 설정 
    quoteProps: "<as-needed|consistent|preserve>" --- default "as-needed"

## as-needed 의 경우 필요한 경우에만 객체에 따옴표 붙임 (default)
    eventParticipation: {
        eventParticipationTable: {
            table: { id: 'eventParticipationTable' },
            url: '/ajax/normal/event/participation/table'
        },
        eventParticipationChart: {
            url: '/ajax/normal/event/participation/chart',
            chart: {
                id: 'eventParticipationChart',
                type: 'columnChart',
                option: {
                    seriesType: 'bars',
                    series: { 2: { type: 'line', targetAxisIndex: 1 } }
                }
            }
        },
        'test-a': {}   // 자동으로 붙여주진 않고 직접 필요한 경우 붙일 경우 허용만 해주는 듯
    },


## consistent의 경우 하나라도 따옴표 필요하다면 모든 속성에 따옴표 붙임 (확인x)
## preserve(v.보존하다)의 경우 따옴표 사용을 존중해준다는데 .. 그냥 붙여도 상관없는 듯
 Respect the input use of quotes in object properties.


default만 사용해도 문제 없을 듯
```
<br/>

7. JSX Quotes ( https://prettier.io/docs/en/options.html#jsx-quotes )
> react와 같은 frontend 프레임워크에나 필요한 것으로 생각됨

<br/>

8. Trailing Commas ( https://prettier.io/docs/en/options.html#trailing-commas )

```
배열의 요소 or 객체의 속성을 나열하는 경우, 맨 마지막 요소 뒤에 쉼표 입력 여부를 결정함 (후행 쉼표 처리)

# 설정 
    trailingComma : <es5|none|all>     --- default es5

## es5의 경우 유효한 ES5 문법/객체(objects, array, etc) 내에 한 해 끝에 콤마(,) 붙임 
    {a : 1, b : 2, c : 3,}
    [1, 2, 3,]

## none의 경우 끝에 붙이지 않음 ✨

## all 의 경우 그냥 다 붙임 

🤔none이 나은듯
```

<br/>

9. Bracket Spacing ( https://prettier.io/docs/en/options.html#bracket-spacing )

```
객체 선언시 괄호 양 끝 사이의 간격 사용여부

# 설정 
    bracketSpacing: <bool> --- true 

## false 인 경우
    {foo: bar}.
## true 인 경우 
    { foo: bar }.

🤔 true 로 설정함   
```

<br/>

10. Bracket Line ( https://prettier.io/docs/en/options.html#bracket-line )

```
 > (꺽괄호) 의 경우 줄 바꿈 해서 출력 할지 여부 설정 
 HTML, JSX, Vue, Angular 같은 태그에 속성 정의하는 element 에 대해서 유효한 옵션으로 보임

# 설정 
    bracketSameLine : <bool>  --- default false 

## true 인 경우 
<button
  className="prettier-class"
  id="prettier-id"
  onClick={this.handleClick}>
  Click Here
</button>

## false 인 경우 
<button
  className="prettier-class"
  id="prettier-id"
  onClick={this.handleClick}
> 👈 줄이 변경되었네 !
  Click Here
</button>

🤔 false 로 설정함   
```

<br/>

11. Arrow Function Parentheses ( https://prettier.io/docs/en/options.html#arrow-function-parentheses )

```
arrow function 사용시 '매개변수가 한 개' 일때 괄호 표현할지 말지 결정하는 옵션으로 생각
👉 매개변수가 없으면 () => {something} 형태로 표출 ! 

# 설정 
    arrowParens : "<always|avoid>"    --- default always 

## always 의 경우 
    "always" - Always include parens. Example: (x) => x

## avoid 의 경우 
    "avoid" - Omit(생략) parens when possible. Example: x => x

🤔 avoid 로 설정함   
```