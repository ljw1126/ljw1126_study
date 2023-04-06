## COUNT 사용시 GROUP 무조건 해야함 
```
ERROR 1081: Cannot cast to bag. Exprected bytearray but received: chararry
ERROR 1081: Cannot cast to bag. Exprected bytearray but received: map  // TupleBagConvert 변환 필드로 카운트하니깐..

Could not infer the matching function for org.apache.pig.builtin.COUNT as multiple or none of them fit. 
Pleas use an explicit case

group_for_continue : A column needs to bs projected from a relation for it to be uses as a scalar

# relation에 group by 할 field 가 존재하지 않을 경우 GROUP [] ALL; 사용해서 하면 됨 !! 
## 다른 pig의 경우 data_key를 위에서 정의 내려 줬지만, data_key 분리할 필요없다보니 field가 적절치 않았음
**group [relation] ALL; 해서 FOREACH로 cOUNT(relation) 해야됨
https://stackoverflow.com/questions/43379466/generate-count-value-in-pig-latin
```