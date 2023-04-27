/*
    https://www.jdoodle.com/execute-nodejs-online/

    a + b 문제 

    *readFileSync 경로가 사이트별 상대적이라서 보편적으로 사용할 수 없다.
    https://wooooooak.github.io/node.js/2018/09/26/Node.js-%EC%9E%85%EB%A0%A5-%EB%B0%9B%EA%B8%B0/
    https://wonyoung2257.tistory.com/36

    문제.
    https://www.acmicpc.net/submit/1000

    콘솔에서 close 하려면 ctrl + d 를 해야 EOF걸려서 끝남

    /dev/stdin  은 리눅스 환경.. 


    
*/



/*
// 1.
let fs = require('fs');
//let input = fs.readFileSync('/dev/stdin').toString().split('\n');
let input = fs.readFileSync('../input.txt').toString().split('\n');

const numbers = input[0].split(' ');
const result = parseInt(numbers[0]) + parseInt(numbers[1]);

console.log(result);

// 2.
const readline = require("readline");
const rl = readline.createInterface({
    input : process.stdin,
    output : process.stdout
});

let input = [];
rl.on("line", function(line) {
    input = line.split(' ').map((el) => parseInt(el));
}).on("close", function() {
    console.log(input[0] + input[1]);
    process.exit();
});
*/


/*
    문제) a * b 

    https://www.acmicpc.net/problem/10998

    const fs = require('fs');
    const input = fs.readFileSync('/dev/stdin').toString().split('\n'); // ['3 4'];

    console.log(input[0].split(' ').map((number) => Number(number)).reduce((a,b) => a * b));
*/

/*
    문제) 사칙 연산 

    입력)
        두 자연수 A와 B가 주어진다. (1 ≤ A, B ≤ 10,000)
    출력)
        첫째 줄에 A+B, 둘째 줄에 A-B, 셋째 줄에 A*B, 넷째 줄에 A/B, 다섯째 줄에 A%B를 출력한다.

    // 제출 답안
    const fs = require('fs');
    const input = fs.readFileSync('/dev/stdin').toString().split('\n');

    const numbers = input[0].split(' ').map((el) => Number(el));
    console.log((numbers[0] + numbers[1]));
    console.log(numbers[0] - numbers[1]);
    console.log(numbers[0] * numbers[1]);
    console.log(parseInt(numbers[0] / numbers[1]));
    console.log(numbers[0] % numbers[1]);
*/


/*
    문제) 곱셈 

    입력)
        472
        385 
    출력)
        2360
        3776
        1416
        181720

    https://www.acmicpc.net/problem/2588


*/
/*
//내가 한거는 가독성이 떨어진다..
const readline = require("readline");
const rl = readline.createInterface({
    input : process.stdin,
    output : process.stdout
});

let input = [];
rl.on("line", function(line) {
    input.push(line);
}).on("close", function() {
    const num1 = Number(input[0]);
    
    const num2 = input[1].split('');
    for(let i = num2.length -1 ; i >= 0 ; i--) {
        console.log(num2[i] * num1);
    }
    
    console.log(num1 * Number(input[1]));
    process.exit();
});


// 답안
const fs = require('fs');
const input = fs.readFileSync('/dev/stdin').toString().split('\n');

const a = input[0];
const b = input[1];

x_1 = b[2];
x_2 = b[1];
x_3 = b[0];

console.log(Number(a) * Number(x_1));
console.log(Number(a) * Number(x_2));
console.log(Number(a) * Number(x_3));
console.log(Number(a) * Number(b));

*/