public class Student implements Comparable<Student>{
    
    private int no;
    private String name;
    private int score;

    public Student(int no, String name, int score){
        this.no = no;
        this.name = name;
        this.score = score;
    }

    public int getNo() {
        return this.no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }


    @Override
    public int compareTo(Student o) {
        return this.no - o.getNo();
    }
    
}
