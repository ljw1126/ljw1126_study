/**
 * else if 피하기 (중요✨)
 * 
 * else if 를 계속 늘어 쓰는 것보다 switch 문을 사용하길 ! 
 */

const x = 1;

// 위아래 동일 형태, coverage가 넓어짐 
if (x >= 0) {
    console.log('x는 0과 같거나 크다');
} else if(x >0) {
    console.log('x는 0보다 크다');
}

if (x >= 0) {
    console.log('x는 0과 같거나 크다');
} else {
    if (x > 0) {
        console.log('x는 0보다 크다 ');
    }
}

// switch문을 사용하거나, 아니면 그냥 if문을 따로 분리하는게 낫다.
//  better than.
if (x >= 0) {
    console.log('x는 0과 같거나 크다');
} 

if (x > 0) {
    console.log('x는 0보다 크다 ');
}