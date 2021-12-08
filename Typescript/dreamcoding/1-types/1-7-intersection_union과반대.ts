{
  /**
   * Intersection Types: & ( Intersection 은 AND의 느낌)
   * 모든 케이스를 다 합함 ( <> UNION은 OR 의 성격이라면)
   */
  type Student = {
    name: string;
    score: number;
  };

  type Worker = {
    empolyeeId: number;
    work: () => void;
  };

  function internWork(person: Student & Worker) {
    console.log(person.name, person.empolyeeId, person.work());
  }

  internWork({
    name: 'ellie',
    score: 1,
    empolyeeId: 123,
    work: () => {},
  });
}
