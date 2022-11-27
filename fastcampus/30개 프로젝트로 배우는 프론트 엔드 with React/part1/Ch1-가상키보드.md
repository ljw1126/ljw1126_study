# Ch 1. 가상키보드 

## 02. 개발환경 설정 - Webpack

```

> npm init -y         // package.json 초기화
> npm i -D webpack webpack-cli webpack-dev-serve      // webpack 관련 패키지 설치
    
    👨‍💻 같은 버전 권장
    "webpack" : "^5.65.8",
    "webpack-cli" : "^4.9.1",
    "webpack-dev-server" : "^4.7.2"

    👨‍💻 버전 다른 경우 package.json에 버전 수정 후 (명령어) npm install 입력하면 설치됨 

    npm i -D 패키지명       
    // i:install, -D : devDependency에 설치 (로컬 개발, 테스트 용도)
    // -D 뺀 경우 : dependency에 설치 (프로던션 환경)

## 프로젝트 root 경로에 src폴더, webpack.config.js 생성 

const path = require("path");       // 해당 모듈 통해 build시 webpack이 절대경로 찾도록 함(output설정 관련)
module.exports = {
    entry : "./src/js/index.js",        // js 파일 진입점 나타냄
    output : {                          // build시 bundle 파일 관련 설정
        filename : "bundle.js",
        path : path.resolve(__dirname, "./dist"),         //이게 맞나?🤔
        clean : true       // burdle 파일 생성될 path경로에 파일 있으면 지우고 생성
    },
    devtool : "source-map"
}



> npm i -D terser-webpack-plugin    // 추가 설치

```

## 03. 개발 환경 설정 - eslint & prettier 


## 04. HTML과 CSS 


## 05. dark theme 적용 


## 06. font 변경 기능 


## 07. event에 대한 이해 


## 08. keyboard event 적용 


## 09. mouse event 적용


