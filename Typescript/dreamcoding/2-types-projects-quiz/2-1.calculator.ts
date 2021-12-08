/**
 * Let's make a calculator 🧮
 */
// type을 지정하는걸 생각못했네 .. 
type command  = 'add'|'substract'|'multiply'|'divide'|'remainder'

function calculate(cmd: command , num1 : number , num2 : number ):number{
    // let result;
    
    // switch(cmd){
    //     case 'add' : result = num1 + num2; break;
    //     case 'substract' : result = num1 - num2; break;
    //     case 'multiply' : result = num1 * num2; break;
    //     case 'divide' : result = num1 / num2; break;
    //     case 'remainder' : result = num1 % num2; break; 
    // }
    // return result  ;

    
    switch(cmd){
        case 'add' : return num1 + num2; 
        case 'substract' : return  num1 - num2; 
        case 'multiply' : return  num1 * num2; 
        case 'divide' : return  num1 / num2; 
        case 'remainder' : return  num1 % num2; 
        default : throw Error('unknown command');
    }
    
}


console.log(calculate('add', 1, 3)); // 4
console.log(calculate('substract', 3, 1)); // 2
console.log(calculate('multiply', 4, 2)); // 8
console.log(calculate('divide', 4, 2)); // 2
console.log(calculate('remainder', 5, 2)); // 1
