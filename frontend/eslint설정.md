# 220915 eslint 적용 

git stash push -um "0916 eslinst, prettier 설정" /mnt/d/gitRepository/gaia-webapp/.eslintignore /mnt/d/gitRepository/gaia-webapp/.eslintrc.js /mnt/d/gitRepository/gaia-webapp/.prettierrc.js /mnt/d/gitRepository/gaia-webapp/.prettierignore /mnt/d/gitRepository/gaia-webapp/package.json

git restore . // 
git clean -fd // 추적 안되는 파일을 다 날려 버리는거고.

> 🤔 추적 하는/추적 안하는 파일 다 날려버리는 방법은 없을까?

## 1. Node 설치 
> wsl2 terminal (window) 환경에서 진행

```bash
$ sudo apt-get install curl 
$ curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.39.1/install.sh | bash
$ command -v nvm 
$ nvm --version      // git bash 설치시 안 먹힘 > 스택 오버플로우 참고 https://stackoverflow.com/questions/16904658/node-version-manager-install-nvm-command-not-found
                     // source ~/.nvm/nvm.sh     ✨ git bash 동작 확인 됨
                     // ubuntu 환경에서도 "nvm command not found" 출력 !!
$ nvm ls

    nvm            N/A
    iojs -> N/A (default)
    node -> stable (-> N/A) (default)
    unstable -> N/A (default)

$ nvm install --lts 
$ nvm use --lts       // lts(안정화 된 버전)
$ nvm ls              // 확인

    nvm       v16.17.0
    ->      v18.9.0
    default -> node (-> v18.9.0)
    iojs -> N/A (default)
    unstable -> N/A (default)
    node -> stable (-> v18.9.0) (default)
    stable -> 18.9 (-> v18.9.0) (default)
    lts/* -> lts/gallium (-> v16.17.0)
    lts/argon -> v4.9.1 (-> N/A)
    lts/boron -> v6.17.1 (-> N/A)
    lts/carbon -> v8.17.0 (-> N/A)
    lts/dubnium -> v10.24.1 (-> N/A)
    lts/erbium -> v12.22.12 (-> N/A)
    lts/fermium -> v14.20.0 (-> N/A)
    lts/gallium -> v16.17.0

❔ npm info {패키지명} [peerDependencies] 

$ npm info "eslint-config-google"    -- 해당 패키지 최신 버전에 대한 정보 출력
$ npm info "eslint-config-google@latest" peerDependencies  -- 해당 패키지에 대한 의존성 정보 출력 (ex. eslint 버전 몇 이상 필요하다.) 
$ npm info "eslint-config-airbnb@latest" peerDependencies    
```

## 2. intellj 연동 
// 사진 첨부 

```bash
# 프로젝트 루트 경로에서 
$ npm init     // package.json 생성 확인 
$ npm install -g eslint        // global 설치 
$ npm install --save-dev eslint    // package.json에 dependency 추가 및 node_modules 설치됨

$ eslint --init    // 적당히 항목 선택 후 완료하면 .eslintrc.js 파일 생성됨 
```

- .eslintrc.js 에 rules/플러그인/환경 등 설정
- .eslintignore 파일 생성 (*eslint 적용 제외 대상 지정하는 파일)


```
$ npm install --save-dev eslint-config-airbnb      // .eslintrc.js 에 "extends" : 'airbnb' 추가 하면 prettier랑 indent 충돌 나서 온통 빨간색 뜸 ! 
$ npm install --save-dev eslint-config-prettier    // 'prettier와 eslint를 구분해서 사용하자' 블로그 확인 시 해당 플러그인만 설치해서 사용하면 되는거 같음 

$ vim .eslintrc.js  

// default
module.exports = {
    env: {
        browser: true,
        es6: true
    },
    // "extends": "eslint:recommended",  eslint-config- 접두사 생략가능
    extends: ['eslint:recommended', 'prettier'],
    overrides: [],
    parserOptions: { // ESLint는 구문 분석을 위해 기본적으로 Espress 사용함(변경 가능)
        ecmaVersion: '6',      // 사용할 ECMAScript 버전을 설정 
        sourceType: 'module'   // parser의 export 형태를 설정
    },
    rules: {
        'no-undef': 'off',
        'no-var': 'error'
    }
};

$ eslint . --quite // 전체 검사 (.eslintignore 설정 경로 제외) 
```

[https://velog.io/@kyusung/eslint-config-2](https://velog.io/@kyusung/eslint-config-2 'ESLint 설정 살펴보기')

#### eslint:recommended rules 위치
```
위치 : node_modules/eslint/conf/eslint-recommended.js
// 요거 실행 안 되는데?
module.exports = {
    rules: {
        "constructor-super": "error",
        "for-direction": "error",
        "getter-return": "error",
        "no-async-promise-executor": "error",
        "no-case-declarations": "error",

        "no-class-assign": "error",
        "no-compare-neg-zero": "error",
        "no-cond-assign": "error",
        "no-const-assign": "error",

        "no-constant-condition": "error",
        
        "no-control-regex": "error",
        "no-debugger": "error",
        
        "no-delete-var": "error",     // Suggestions 항목

        "no-dupe-args": "error", // 객체 리터럴에서 중복 키 허용 여부, --fiexd 옵션주고 실행해도 error (직접 수정 필요) (✨)
        "no-dupe-class-members": "error",
        "no-dupe-else-if": "error",
        "no-dupe-keys": "error",
        "no-duplicate-case": "error",

        "no-empty": "error", // 빈 블록문 허용 여부, Suggestions 항목

        "no-empty-character-class": "error",
        "no-empty-pattern": "error",
        "no-ex-assign": "error",

        "no-extra-boolean-cast": "error", // Suggestions 항목, --fixed 했을 때 이슈도 있는 듯
        "no-extra-semi": "error", // Suggestions 항목, --fixed 했을 때 이슈도 있는 듯
        
        "no-fallthrough": "error",
        "no-func-assign": "error",
 
        "no-global-assign": "error", // Suggestions 항목
        
        "no-import-assign": "error",
        "no-inner-declarations": "error",
        "no-invalid-regexp": "error", 
        "no-irregular-whitespace": "error",      // 🤔 불규칙한 공백에 대한 규칙 같은데.. 이해가 잘 안됨
        "no-loss-of-precision": "error",         // 64비트 부동 소수점 반올림으로 인해, 특정 길이 이상의 (실수) 숫자 리터럴은 정밀도 떨어뜨림
        "no-misleading-character-class": "error", // 뭔지 모르겠음.

        "no-mixed-spaces-and-tabs": "error", // 들여쓰기 위해 탭과 스페이스 혼용 사용에 대한 규칙 같은데.. 🤔 오류 case 모르겠음
        
        "no-new-symbol": "error", // 👩‍💻 사용하진 않지만 넣는 걸로 

        "no-nonoctal-decimal-escape": "error",  // Suggestions 항목, 🤔 쓸모없어진 escape 에 대한 얘기인데.. 쓸일 없지만 넣어두자 ..
        
        "no-obj-calls": "error", // new JSON() -> JSON.parse() 와 같이 호출 사용해야 함, 객체 중 일부는 대문자로 인해 생성자로 보여 함수로 실행하려고 하면 에러 발생 가능

        "no-octal": "error", // Suggestions 항목

        "no-prototype-builtins": "error", // *.hasOwnProperty('something') 호출시 에러 
        
        "no-redeclare": "error", // Suggestions 항목, var를 사용시 동일한 변수이름을 재 선언가능 -> 변수가 초기화 되는 위치에 대한 혼란 초래
        "no-regex-spaces": "error", // Suggestions 항목
        
        "no-self-assign": "error", // 자체 할당 (foo = foo); 
        "no-setter-return": "error",
       
        "no-shadow-restricted-names": "error", // Suggestions 항목, 선언시 변수명에 제한된 이름 사용 금지
       
        "no-sparse-arrays": "error", // 배열 사이에 쉼표로 아무것도 없는 형태를 허용하지 않음 (배열 마지막 속성에 쉼표는 상관 x)
        "no-this-before-super": "error",
        "no-undef": "error",  //  /*global */ 주석에서 언급하지 않는 한 선언되지 않는 변수의 사용을 허용하지 않음 (꺼버리는 걸로..)
        "no-unexpected-multiline": "error",
        "no-unreachable": "error",
        "no-unsafe-finally": "error",
        "no-unsafe-negation": "error",
        "no-unsafe-optional-chaining": "error",

        "no-unused-labels": "error",  // Suggestions 항목, --fixed 사용시 이슈 존재 

        "no-unused-vars": "error", // 사용하지 않는 변수 허용하지 않음 (✨)
        "no-useless-backreference": "error",
        
        "no-useless-catch": "error", // Suggestions 항목
        "no-useless-escape": "error", // Suggestions 항목, some problems reported
        "no-with": "error", // Suggestions 항목, with문 처음 봄😅
        "require-yield": "error", // Suggestions 항목, 🤔yield 키워드..?

        "use-isnan": "error",
        "valid-typeof": "error"
    }
};

👩‍💻v8.23.1 recommend 등록되지 않았는데 사용가능함 rules (공식 docs에서 체크박스 없는거)
array-callback-return 
no-await-in-loop
no-constant-binary-expression
no-constructor-return
no-duplicate-imports
no-promise-executor-return
no-self-compare
no-template-curly-in-string
no-unmodified-loop-condition
no-unreachable-loop
no-unused-private-class-members
no-use-before-define
require-atomic-updates

🤔개인적으로 언급해보고 싶은 rule 
prefer-template        // --fixed 시 문제 보고됨 .. 이것도 문제 있었음. 예로 객체 key property 사용 못함
object-shorthand       // --fixed 시 문제 보고됨 .. 문제 있을 거 같음
dot-notation           // --fixed 시 문제 보고됨 
eqeqeq                 // == 대신 === 사용하도록 하는거
yoda                   // 특이하네 







😅 그냥 살펴본거 
sort-vars          // 모든 변수 선언 블록을 확인하고 모든 변수가 알파벳순으로 정렬되어 있는지 확인합니다. 규칙의 기본 구성은 대소 문자를 구분함  
                      동일한 블록 내에서 여러 변수를 선언 할 때 일부 개발자는 나중에 필요한 변수를 쉽게 찾을 수 있도록 변수 이름을 사전 순으로 정렬하는 것을 선호합니다. 다른 사람들은 그것이 복잡성을 더하고 유지해야하는 부담이된다고 생각합니다.
spaced-comment     // 주석에서 // 또는 /* 뒤에 일정한 간격 허용 여부
strict             // "use strict"; 명시인데, 사용할 필요 없는 듯? 
symbol-description // Symbol("description") 기호 설명이 필요함, Symbol을 사용하지 않는데?
vars-on-top        // 변수 선언이 해당 범위의 맨 위에 있어야 함. (var를 사용안하는데?)
```

#### eslint-config-prettier 
> eslint-config-prettier : eslint에서 prettier와 충돌할 수 있는 rule을 꺼버림 ✅ 코드 오류를 잡는데는 eslint, 코드 포맷팅에는 prettier를 사용하는 방법이다.

[https://yrnana.dev/post/2021-03-21-prettier-eslint](https://yrnana.dev/post/2021-03-21-prettier-eslint 'prettier와 eslint를 구분해서 사용하자')

#### 참고 
[https://eslint.org/docs/latest/rules/](https://eslint.org/docs/latest/rules/ 'Eslint rules (공식)')
[https://runebook.dev/ko/docs/eslint/-index-](https://runebook.dev/ko/docs/eslint/-index- '한글 가이드 ESLint 8.16') 
> 8.16에 rules 만 306개 정도가 되네.. 

#### husky
- husky 플러그인이 라이센스 변경됨에 따라 **5버전 부터 유료이고 4버전까지는 무료**라함 -> 근데 왜 MIT 라이센스 붙여져 있지 ? 🤔
- 관련 블로그 : [https://taegon.kim/archives/10276]('https://taegon.kim/archives/10276')
- 관련 기사 : [https://dev.to/typicode/what-s-new-in-husky-5-32g5](https://dev.to/typicode/what-s-new-in-husky-5-32g5 '')
- MIT 라이센스란 ? [https://onelife2live.tistory.com/36]('https://onelife2live.tistory.com/36')

```
// ✔ Install husky
> npm install --save-dev husky@8.0.1    

> npm set-script prepare "husky install"    //package.json 에 script 명령어 추가됨
> npm run prepare

// ✔ Add a hook
> npx husky add .husky/pre-commit "echo 'execute pre-commit ------'"
> git add .husky/pre-commit
> git commit -m "pre-commit test"

// ✔ 실제 script 명령어 실행 등록
npx husky add .husky/pre-commit "npm run eslint"

// Test
❯ git commit -m "pre-commit test" 
gitAlias tip: gcmsg "pre-commit test"
execute pre-commit-----      // 커밋전 메시지가 출력됨
[feature/codeConvention f96111a] pre-commit test
 1 file changed, 4 insertions(+)
 create mode 100644 .husky/pre-commit

// Reset After Test 
> git reset --mixed HEAD^ 

// ✔ Install lint-staged
> npm install --save-dev lint-staged@13.0.3

// ✔ .package.json 에 설정을 해줘야 함
   "scripts": {
      "lint": "eslint \"**/*.js\"",
      "prepare": "husky install", -- npx prepare 로 실행되지 않을까 ? (npm run prepare나 .. 패키지 runner이 npx 나 .. 🤔)
      "lint-staged" : "lint-staged"
   },
   "lint-staged": {
      "**/*.js" : [
         "eslint"
      ]
   },

   npx husky add .husky/pre-commit "npm run lint-staged"

   > npm run lint-staged

```

https://www.npmjs.com/package/husky
https://www.npmjs.com/package/lint-staged
https://github.com/typicode/husky
https://kir93.tistory.com/entry/husky-lint-staged%EB%A5%BC-%ED%99%9C%EC%9A%A9%ED%95%98%EC%97%AC-git-hook%EA%B1%B8%EA%B8%B0

#### 셋팅 참고
https://onlydev.tistory.com/122

> 💡 git empty commit  
> $ git commit --allow-empty -m "Empty-Commit"

---

#### Rules 
```
Rules 의 경우 { "규칙" : "값", ... } 형태로 설정 
👉 "off" 또는 0 : **false** / "warn" 또는 1 : **경고** / "error" 또는 2 : **오류**
```

* prefer-template 
```javascript
📚 문자열 대신 탬플릿 리퍼럴 문법 사용 제안함
// 수정 전 
const $table = $('#' + _module.table.id); // error '#'  ESLint: Unexpected string concatenation.(prefer-template)

// 수정 후
 const $table = $(`#${_module.table.id}`);
```

* dot-notation
```javascript 
const _ = this;
const _openMonitoring = modules['openMonitoring']; // error : object 속성 접근 방법에 대해 ESLint: ["flowerScoreRateChart"] is better written in dot notation.(dot-notation)

// 수정 후
const _openMonitoring = modules.openMonitoring;
```

* object-shorthand
```javascript
// 수정 전
const ModuleEvent = {
      init: function () { // ESLint: Expected method shorthand.(object-shorthand), 매개변수가 없는 function() 사용해서 그런듯 
         // ...
      }
}

// 수정 후
const ModuleEvent = {
    init() { // 간략화 됨
        // ...
    }
}
```

* no-unused-vars 
```javascript
// 수정 전
let test = ''; // ESLint: 'test' is assigned a value but never used.(no-unused-vars) , 사용하지 않은 변수에 대해 error 출력

// 수정 후 .. 변수 사용하면 없어지는 에러임
```

* no-undef 
```
📚 /*global */ 주석에서 언급하지 않는 한 선언되지 않은 변수의 사용 허용 x

최상단에 아래 주석 추가 할 경우 에러 사라짐 -> 전체 파일에 추가하기는 좀 그럼 ..
ex. /*global $, service, Mustache*/

💡 'no-undef': 'off'      // 그래서 설정 꺼버림
```

* no-dupe-keys ([https://runebook.dev/ko/docs/eslint/rules/no-dupe-keys](https://runebook.dev/ko/docs/eslint/rules/no-dupe-keys))
```
📚 객체 리터럴에서 중복 키 허용 x 

// Error 인 경우
const notEmpty = {
    1 : "test",
    0x1 : "tt"  // 0x1 === 1 
}

👩‍💻 eslint --fixed 옵션을 줘도 직접 수정하기 전 까지 error 출력함 

💡 'no-dupe-keys' : 'error'
```

* no-var ([]())

```
📚

💡 'no-var' : 'error'
```

--- 

#### .editorconfig 
- (단축키) alt + insert 누른 후 
- 폴더별로 개별 설정 가능함 -- 🤔 root 폴더에 추가했는데 하위 폴더에는 적용이 안되는 거 같음..
    - Settings > Code Style >  Enable EditorConfig support 체크✔
- .editorconfig 설정 변경 후 좌측 눈모양 아이콘 누르고 파일 선택하면 code style preview 가능 
  - 또는 우측 최하단 문서에 톱니바퀴있는거 눌러서 file 선택 가능
- 전체 적용은 Project diretory에서 Root 폴더 선택 > 마우스 오른쪽 클릭 > Reformat Code (ctrl + alt + l) \
    - Include subdirectories 옵션 ✔(체크) 후 Run ✨
    - exclude folder만 하면 되는데..

> 현 파일 적용 : ctrl + alt + l , 전체 파일 적용 : ctrl + alt + shift + l  // global pattern 확인 후 하기..

💡 전체 폴더에 적용하는 방법 
- 반드시 설정 적용하기 전에 git 이력 commit하고 하기 👉 원복하기 쉽도록
    - 폴더에 Reformat Code 선택 > "Include subdirectories" 옵션 체크 후 Run 
    - 그런데 .. **exclude 폴더**는 어떻게 처리 .. ? 🤔  

[https://stackoverflow.com/questions/41518677/how-to-apply-editorconfig-to-existing-project-in-intellij](https://stackoverflow.com/questions/41518677/how-to-apply-editorconfig-to-existing-project-in-intellij)

```
[#1. Tabs and Indents]
ij_continuation_indent_size = 4     // 들여 쓰기 싸이즈

                                    // keep indents on empty lines 👉 줄 바꿈시 동일한 indent 유지 (🙂선호)
                                    
                                    // 쓸거면 둘다 사용해야 할 듯🤔
                                    // Indent chained methods 👉 메소드 체이닝 될 때 indenet 부여 (별로..)
                                    // Indent all chained calls in a group 👉 위에랑 한 세트, 메서드 체이닝 거는 앞 그룹에 대해서도 indent 적용
                                    
```
![Alt text](/images/frontend/code-style-Tabs-and-Indents.png)


```
[#2. Spaces]

```

```
[#3. Wrapping and Braces]   --- (Brace, 중괄호)

```


```
[#4. Blank Lines] --- default 유지
ij_javascript_keep_blank_lines_in_code = 2     // 최대 Blank line 수 (3줄인 경우 2줄로 수정됨)

ij_javascript_blank_lines_after_imports = 1    // import 문 이후에 공백
ij_javascript_blank_lines_around_class = 1     // clase {} 위, 아래 공백
ij_javascript_blank_lines_around_field = 0     // const foo = 0; 아래 공백
ij_javascript_blank_lines_around_method = 1    // foo() {} 위, 아래 공백
ij_javascript_blank_lines_around_function = 1  // function something() {} 아래 공백
```
![Alt text](/images/frontend/code-style-blank-lines.png)

```
[#5. Punctuation] --- 🤔 double quotes를 true 했을 때 문제 확인해보기.. (punctuation, 구두점)
ij_javascript_use_semicolon_after_statement = true   //4. 세미콜론(boolean) -> always는 따로 수정해야 하는 듯?
ij_javascript_use_double_quotes = true               //5. 쌍따옴표 -> always는 따로 수정해야 하는 듯?
ij_javascript_enforce_trailing_comma = remove        //7. Trailing Commas 끝 자리 콤마 제거, (default remove)
```
![Alt text](/images/frontend/code-style-punctuation.png)


```
[#6. 그외] --- default 유지
- Code Generation
- Improts 
- Arrangement 
```


#### 이슈) 
- 1) java 파일 수정 후 인텔리제이 git 으로 commit 해서 올리는데 commit 실패되고 에러 메시지 출력 
     
     husky - pre-commit hook exited with code 1

     👩‍💻 git bash/wsl 에서 npm install -g {패캐지명} 했을 때 경로가 각각 틀려서 동작 하지 않는 것이었음. 
         나의 경우 wsl 에 작업하는데, intellij git plugin 의 executor의 경우 git bash 가 설정 되어 있다보니 문제 발생
        > git bash에서 global로 플러그인 설치 

- 2) 한글 깨짐
     https://www.lesstif.com/java/intellij-file-console-encoding-121012310.html

💡 terminal command line으로 commit시 정상 동작함

- 3) git bash 로 lint-staged 실행시 반복 실행되는 문제 
     👩‍💻 아래 이슈 확인해보니 git bash 최신 버전에도 이슈가 존재해서 npx lint-stage -q 옵션 줘서 에러만 출력하도록 하는게 현실적이라 얘기하고 잇음

     https://github.com/okonet/lint-staged/issues/1164
     https://github.com/okonet/lint-staged/issues/1179 



----- 

## sub directory로 설정 파일 관리시

#### 1. 인텔리제이 환경 설정
- Manual ESLint configuration ✔
  - ESLint package에 global 설치된 eslint 폴더 선택 
  - Working directories에 해당 프로젝트 루트 경로 선택 
  - Configuration file 에 eslintrc.js 옮긴 위치 선택 

#### 2. cmd line 설정
- eslintrc.js 와 .eslintignore 파일이 .config 폴더 안에 들어갔을 때

lint-stage 명령어를 아래와 같이 수정 (옵션 통해 override할 설정 파일 경로 지정)
```json
"lint-staged": {
     "src/**/*.js": [
       "eslint -c .config/.eslintrc.js --ignore-path .config/.eslintignore"
     ]
}
```

> npx lint-stage -q     // 실행 테스트✨


💣 문제. git sub module로 관리시 hashcode 이력을 따로 관리 해줘야 하기 때문에 기존 보다 많은 git command 를 쳐야 함
👉 remote로 부터 sub module 내려 받을 시 conflict 발생할 경우 수동으로 처리해야 하는데 bash script로 관리 하는 것은 .. 어렵다고 판단됨
