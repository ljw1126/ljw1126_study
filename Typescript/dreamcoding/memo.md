[공부]
1. 하나의 *.ts 파일을 분해해서 모듈로 만들 수 있다 
2. 이때 모듈을 연결할때는 export와 import 구문을 활용해야함 
3. export default 의 경우 import문으로 불러올때 중괄호{} 없이 사용할 수 있습니다. 


==================================================================================================================================================================================================================
[scoop 프로그램 설치] // window7에서 설치 안되서 포기함 ..
- window powershell(cmd창)을 관리자 권한으로 실행 
> Set-ExecutionPolicy RemoteSigned -scope CurrentUser     //실행 후 Y 입력 
> $env:SCOOP='C:\Scoop'
> iex (new-object net.webclient).downloadstring('https://get.scoop.sh')  
> scoop install aria2
> scoop install git 



[타입스크립트 컴파일러 설치] 
> npm i -g typescript   //i:install, g:global 
> tsc -v       //버전 확인 ( tsc : typescript compiler 와 client 라는 의미가 동시에 있음 ) 

※ tsc의 경우 ts파일을 js파일로 바꿔줄 뿐 실행은 안해줌 

> npm i -g ts-node  //ts-node 프로그램 통해 변환과 실행 시킬 수 있음
> ts-node -v        

[타입스크립트 테스트 프로젝트]
> mkdir ch02-1
> cd ch02-1
> npm init --y 
> npm i -D typescript ts-node   //package.json 에 등록 ( ※ package.json 자바스크립트 라이브러리 관리하는 설정파일 ) 
> npm i -D @types/node          //마찬가지 @types/node를 devDependencies 추가
> npm i                         //다른 사람이 해당 명령어 입력하면 package.json에 등록된 패키지들이 node_modules 디렉토리에 자동설치됨

> tsc --init //tsconfig.json(타입스크립트 컴파일러의 설정파일) 생성
             //tsconfig.json 설정파일 내용 수정은 p39 참조 

> mkdir -p src/utils 
> touch 경로/파일명 경로/파일명 

> package.json에 아래 내용 추가 npm command 관련 
 "scripts": {
    "test": "echo \"Error: no test specified\" && exit 1",
    "dev" : "ts-node src",
    "build" : "tsc && node dist"
  },

> tsconfig.json 설정은 그냥 파일채로 

==================================================================================================================================================================================================================
{
  "compilerOptions": {
    /* Visit https://aka.ms/tsconfig.json to read more about this file */

    /* Basic Options */
    // "incremental": true,                   /* Enable incremental compilation */
    "target": "es5",                          /* Specify ECMAScript target version: 'ES3' (default), 'ES5', 'ES2015', 'ES2016', 'ES2017', 'ES2018', 'ES2019', 'ES2020', or 'ESNEXT'. */
    "module": "commonjs",                     /* Specify module code generation: 'none', 'commonjs', 'amd', 'system', 'umd', 'es2015', 'es2020', or 'ESNext'. */
    // "lib": [],                             /* Specify library files to be included in the compilation. */
    // "allowJs": true,                       /* Allow javascript files to be compiled. */
    // "checkJs": true,                       /* Report errors in .js files. */
    // "jsx": "preserve",                     /* Specify JSX code generation: 'preserve', 'react-native', or 'react'. */
    // "declaration": true,                   /* Generates corresponding '.d.ts' file. */
    // "declarationMap": true,                /* Generates a sourcemap for each corresponding '.d.ts' file. */
     "sourceMap": true,                     /* Generates corresponding '.map' file. */
    // "outFile": "./",                       /* Concatenate and emit output to single file. */
    "outDir": "dist",                        /* Redirect output structure to the directory. */
    // "rootDir": "./",                       /* Specify the root directory of input files. Use to control the output directory structure with --outDir. */
    // "composite": true,                     /* Enable project compilation */
    // "tsBuildInfoFile": "./",               /* Specify file to store incremental compilation information */
    // "removeComments": true,                /* Do not emit comments to output. */
    // "noEmit": true,                        /* Do not emit outputs. */
    // "importHelpers": true,                 /* Import emit helpers from 'tslib'. */
     "downlevelIteration": true,            /* Provide full support for iterables in 'for-of', spread, and destructuring when targeting 'ES5' or 'ES3'. */
    // "isolatedModules": true,               /* Transpile each file as a separate module (similar to 'ts.transpileModule'). */

    /* Strict Type-Checking Options */
    //"strict": true,                           /* Enable all strict type-checking options. */
     "noImplicitAny": false,                 /* Raise error on expressions and declarations with an implied 'any' type. */
    // "strictNullChecks": true,              /* Enable strict null checks. */
    // "strictFunctionTypes": true,           /* Enable strict checking of function types. */
    // "strictBindCallApply": true,           /* Enable strict 'bind', 'call', and 'apply' methods on functions. */
    // "strictPropertyInitialization": true,  /* Enable strict checking of property initialization in classes. */
    // "noImplicitThis": true,                /* Raise error on 'this' expressions with an implied 'any' type. */
    // "alwaysStrict": true,                  /* Parse in strict mode and emit "use strict" for each source file. */

    /* Additional Checks */
    // "noUnusedLocals": true,                /* Report errors on unused locals. */
    // "noUnusedParameters": true,            /* Report errors on unused parameters. */
    // "noImplicitReturns": true,             /* Report error when not all code paths in function return a value. */
    // "noFallthroughCasesInSwitch": true,    /* Report errors for fallthrough cases in switch statement. */
    // "noUncheckedIndexedAccess": true,      /* Include 'undefined' in index signature results */

    /* Module Resolution Options */
     "moduleResolution": "node",            /* Specify module resolution strategy: 'node' (Node.js) or 'classic' (TypeScript pre-1.6). */
     "baseUrl": ".",                       /* Base directory to resolve non-absolute module names. */
     "paths": { "*": ["node_modules/*"]},                           /* A series of entries which re-map imports to lookup locations relative to the 'baseUrl'. */
    // "rootDirs": [],                        /* List of root folders whose combined content represents the structure of the project at runtime. */
    // "typeRoots": [],                       /* List of folders to include type definitions from. */
    // "types": [],                           /* Type declaration files to be included in compilation. */
    // "allowSyntheticDefaultImports": true,  /* Allow default imports from modules with no default export. This does not affect code emit, just typechecking. */
    "esModuleInterop": true,                  /* Enables emit interoperability between CommonJS and ES Modules via creation of namespace objects for all imports. Implies 'allowSyntheticDefaultImports'. */
    // "preserveSymlinks": true,              /* Do not resolve the real path of symlinks. */
    // "allowUmdGlobalAccess": true,          /* Allow accessing UMD globals from modules. */

    /* Source Map Options */
    // "sourceRoot": "",                      /* Specify the location where debugger should locate TypeScript files instead of source locations. */
    // "mapRoot": "",                         /* Specify the location where debugger should locate map files instead of generated locations. */
    // "inlineSourceMap": true,               /* Emit a single file with source maps instead of having a separate file. */
    // "inlineSources": true,                 /* Emit the source alongside the sourcemaps within a single file; requires '--inlineSourceMap' or '--sourceMap' to be set. */

    /* Experimental Options */
    // "experimentalDecorators": true,        /* Enables experimental support for ES7 decorators. */
    // "emitDecoratorMetadata": true,         /* Enables experimental support for emitting type metadata for decorators. */

    /* Advanced Options */
    //"skipLibCheck": true,                     /* Skip type checking of declaration files. */
    //"forceConsistentCasingInFileNames": true  /* Disallow inconsistently-cased references to the same file. */
  },
  "include" : ["src/**/*"]
}

==================================================================================================================================================================================================================

> npm i -S chance ramda   //옵션 -S 달면 package.json에 dependencies에 내용추가됨 
> npm i -D @types/chance @types/ramda //옵션 -D 달면 devDependencies에 추가됨

