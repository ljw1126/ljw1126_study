/**
 * Type Inference 타입추론 
 * 알아서 자동으로 타입이 결정되는 경우가 있음 
 * ' 타입추론이 좋은건 아니라고 생각함, 보통 프로젝트에서 아래와 같이 
 *  간단하게 하지 않기 때문에 왠만해선 (리턴타입 있다면) 
 *  타입명시하는게 좋음' ( 엘리 )
 */
let text = 'hello';
function print(message = 'hello') { // 따로 명시 하지 않으면 any 형이 됨 ( 좋지 않음 ) , 인자의 형을 명시하는 것이 좋음 
  console.log(message);
}
print('hello');

function add(x: number, y: number): number {
  return x + y;
}
const result = add(1, 2);
