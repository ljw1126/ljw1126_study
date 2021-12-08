/**
 * Let's make a game 🕹
 * 2. 좌표게임 
 */
 let position = {
     x : 0, 
     y : 0
 }

 type direction = 'up'|'down'|'left'|'right'

 function move(method : direction ){
    //  switch(method){
    //      case 'up' : position.y = position.y + 1; break;
    //      case 'down' : position.y = position.y - 1; break;
    //      case 'left' : position.x = position.x - 1; break;
    //      case 'right' : position.x = position.x + 1; break;
    //      default : throw Error('unknown direction');
    //  }

    switch(method){
        case 'up' : position.y += 1; break;
        case 'down' : position.y -= 1; break;
        case 'left' : position.x -= 1; break;
        case 'right' : position.x += 1 ; break;
        default : throw Error(`unknown direction : ${method}`);
    }
 }

console.log(position); // { x: 0, y: 0}
move('up');
console.log(position); // { x: 0, y: 1}
move('down');
console.log(position); // { x: 0, y: 0}
move('left');
console.log(position); // { x: -1, y: 0}
move('right');
console.log(position); // { x: 0, y: 0}

