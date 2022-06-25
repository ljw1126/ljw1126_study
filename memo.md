※ mustache에 function() {} 정의해서 호출가능  (뭐.. object내 속성할당 되어 있어야 하지만)
   > http://mustache.github.io/
   > https://localcoder.org/calling-function-with-arguments-in-mustache-javascript

※ SVG 태그(구글차트) 에 특정 텍스트, 도형 추가시 native api 사용해야 
   https://developer.mozilla.org/ko/docs/Web/API/Document/createElementNS

※ javascript Number와 parseInt 차이 
※ jquery prop attr 차이
※ each와 ForEach 차이
   > $.each 사용시 arrow function doesnt working에 대해 
      >> https://github.com/jquery/jquery/issues/3801


※ set selected combox in mustache 
- 확인해보니 function 해주는게 있었음.. 
```javascript
<script type="x-tmpl-mustache" id="testTemplate">
{{#.}}
    {{#likelihood}}
        <select>
            {{#options}}
               <option value="{{.}}" {{#selected}}{{value}}{{/selected}}>{{.}}</option>
            {{/options}}
        </select>
    {{/likelihood}}
{{/.}}
</script>
<script type="text/javascript">
    const obj  = [{
            likelihood : [{value : 9}, {value : 5}, {value : 3}, {value : 1}, {value : 0}],
            options : [9, 5, 3, 1, 0]
    }]; // 25개로 늘려서 테스트시 1초도 안걸림.

    // typeof 1 == "1" (number == string) 인데 .. 예외로
    obj["selected"] =function() {
        return (val, render) => this == render(val) ? "selected" : "";
    }

    const html = Mustache.render($("#testTemplate").html(), obj);
    console.log(html);
</script>     
```

※ Scatter Chart 작업중 
- option
   - textPosition : x,y축 범위 수치를 그래프 어디에 둘것인가 in/out/none, default 'out'
   - baseline : 중심선 표시
   - baselineColor : 중신선 색상
   - vAixs : {}, y 축 
   - hAxis : {}, x 축

