#### 참고.
[https://hea-www.harvard.edu/~fine/Tech/vi.html](https://hea-www.harvard.edu/~fine/Tech/vi.html 'vi Complete Key Binding List')
[https://www.linuxtrainingacademy.com/]('https://www.linuxtrainingacademy.com/')
[https://www.joinc.co.kr/w/Site/Vim/Documents/UsedVim]('https://www.joinc.co.kr/w/Site/Vim/Documents/UsedVim')


## 요약

#### insert mode 
입력 모드(insert mode) 전환 
    i : 현재 커서 위치에서 insert mode
    a : 한 칸 띄우고 insert mode
    I : 맨 앞에 커서 이동 후 insert mode 
    A : 맨 뒤에 커서 이동 후 insert mode 
    O : 위로 커서 이동 후 한줄 삽입 후 insert mode
    o : 아래로 커서 이동 후 한줄 삽입 후 insert mode 

    ✨ {count}command => 입력 => esc 할 경우 입력 내용을 앞에 커맨드 수만큼 insert 해줌 !!

#### Replace , Change  // normal mode 동작
- r : 한 글자만 
- R : 한 단어 이상

- c{motion} 
  - cw / cW하면 delete 후 insert mode가 start 된다함 (:h c) // c$는 현재 위치부터 라인 끝까지
  - cb / cB 뒤로 
  - ["x]cc     // "x 레지스터네
  - ["x]C      // :h C 로 확인

- UPPERCASE, lowercase
  - ~ : 커서 기준 한 글자만 
  - g~w : 커서 기준 한 단어만
  - g~~ : 한 라인 전체 switch 
  - gUw / gUW / gUU: to perform the uppercase operation on the word motion 
  - guw / guW / guu : 한단어 / 한 라인 전체 소문자 변환

[https://stackoverflow.com/questions/16416758/what-is-the-difference-between-s-c-and-r-commands-in-vi-vim](https://stackoverflow.com/questions/16416758/what-is-the-difference-between-s-c-and-r-commands-in-vi-vim 'c/r/s 차이')


### 검색 
#### normal mode 동작
linewise search (한 줄내 검색): f/F  (f : search forward, F : search backword)
- f{char} : forward search 
- F{char} : reverse search 
- t{char} : forward till(까지) search 
- T{char} : Reverse till search 
  - ; - Repeat in the same direction (같은 방향으로 다음 검색)
  - , - Repeat in the opposite direction (반대 방향으로 이전 검색)

ex. The Time Traveller (for ...)        // 🤔 미묘한 차이가 있군 마치 a, i 같은
    ff                  ↑
    tf                ↑
    FT      ↑ 
    TT       ↑     

#### command mode 동작
search (파일내 검색): / 와 ? (/ : 정방향, ? : 역방향)
- /{pattern} : forward search 
- ?{pattern} : reverse search 
  - n : Repeat search in the same direcction 
  - N : Repeat search in the opposite direction 
  - * : Forward search for word (단어 앞에 커서가 있어야 함, 해당 단어 간편 검색) 
  - \#\ : Reverse search for word (마찬가지)

✨Replace (s:substitute)
Format 
    :[range]s/{pattern}/{string}/[flags]  -- pattern, string 이라기 보단 old 와 new
    
    * [range] 에 사용 가능한 예약어 
    . = current line
    $ = last line
    % = All lines (entire file, % == 1,$), % is a range which represents the entire file.

    * [flags] 에 아무것도 없으면 현재 라인만, g를 하면 파일 내용 전체 대상

    :/PATTERN-1/,/PATTERN-2/s/old/new/[flags]        // , 기준으로 보면 range 지정하는 거랑 똑같음✨

Global Substitution : 
    :%s/{pattern}/{string}/g       -- 마찬가지

/ 와 같은 문자가 들어가는 경우 (백스페이스 대신 아래 형식으로 하는게 훨씬 편함)
    :s#old#new#


[https://vim.fandom.com/wiki/Search_and_replace]('https://vim.fandom.com/wiki/Search_and_replace' 'Search and Replace 참고')

---

### Text Objects 처리 

> :h objects     // objects 종류 확인 가능👍




#### vim 안에서 화면 분리
[https://m.blog.naver.com/nfwscho/220357935591](https://m.blog.naver.com/nfwscho/220357935591)

#### vim 안에서 화면 분리하여 내용 가져 오기 

```bash


```

#### vim 편집 중에 실수로 ctrl + z 눌러서 나갔을 때
```bash
$ jobs 
$ fg %[번호]
```

