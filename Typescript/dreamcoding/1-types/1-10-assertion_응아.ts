{
  /**
   * Type Assertions π©
   * 100% μ₯λ΄ν λ μ¨μΌν¨ , function λ¦¬λ νμ΄ 100% νμ€λ λ μ¬μ©ν¨ 
   * λ­κ° casting κ°μ λλμΈκ±° κ°μΌλ©΄μλ 
   */
  function jsStrFunc(): any {
    return 2;
  }
  const result = jsStrFunc();
  console.log((result as string).length); // 
  console.log((<string>result).length);

  const wrong: any = 5;
  console.log((wrong as Array<number>).push(1)); // π± TypeError: wrong.push is not a function , μ£λΆλ₯Έ νλ¨μΌλ‘ μ΄λ κ² νλ©΄ μλ¨ 

  function findNumbers(): number[] | undefined {
    return undefined;
  }
  const numbers = findNumbers()!;
  numbers.push(2); // π± μ«μμΌμλ undefined μΌμ μκΈ°λλ¬Έμ push λ₯Ό μ°λ©΄ μ’μ§ μμ ! 
  numbers!.push(3); // λ³μ λ€μ ! λΆμ΄λ©΄ νμ νλ€λ λ» ( ? : optional κ³Ό λ°λ )
  
  const button = document.querySelector('class')!; // μ νμ ν¨μ return κ° νμΈν΄λ³΄λ©΄ μλ¦¬λ¨ΌνΈλ , null μ΄ μ¬ μλ μλ€ν¨ 
}
