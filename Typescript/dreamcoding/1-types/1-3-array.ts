{
  // Array
  const fruits: string[] = ['π', 'π'];
  const scroes: Array<number> = [1, 3, 4];         //number[] λ κ°λ₯ , Array<number>λ generic μ±ν°μμ
  function printArray(fruits: readonly string[]) {} //ν¨μ λ΄λΆμμ λ°°μ΄ μΈμ λ³κ²½ x νλλ‘ readonly μν΄
                                                    //readonly μ¬μ©μ Array<string>μ λͺ»μ! string[] ννμ²λΌ μΌκ΄μ± μ§μΌμ£ΌκΈ° 
  // Tuple -> interface, type alias, class
  let student: [string, number]; //μ΄λ κ² μ°λκ±΄ κ°λμ± λ¨μ΄μ§λ μλ κ²½μ°λ₯Ό μ°κ±°λ λμ²΄νλκ² μ’λ€ν¨ 
  student = ['name', 123];
  student[0]; // name
  student[1]; // 123
  const [name, age] = student;      // **object distructure μ¬μ©νλ©΄ μ‘°κΈμ κ°λμ± μ¬λΌκ° ( Reactμ useState λ§€κ°λ³μ μ¬μ©μ μ΄λ°μμΌλ‘ μ¬μ©νλ€ν¨)

  /*
    μλ¦¬λ νν κΆμ₯ μν¨ 
    λ°°μ΄μ μΈλ±μ€λ‘ μΆλ ₯νλ κ²μ κ°λμ± λ¨μ΄μ§λ€ν¨ 
    > μ°¨λΌλ¦¬ interface, type alias, class λ‘ λμ²΄νλκ² μ’μ 
  */



}
