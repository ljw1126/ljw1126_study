
import java.util.*;

public class MyHashChainingTemplate {
    
    // LinkedList를 이용한 오픈 해싱법 
    Slot[] hashTable;

    public MyHashChainingTemplate(int size){
        this.hashTable = new Slot[size];
    }   

    public class Slot{
      
    }

    public int hashFunc(String key){
        return 0;
    }

    public boolean saveData(String key, String value){
        return false;   
    }

    public String getDate(String key){
        return null;
    }

    public static void main(String[] args) {
        MyHashChaining mainObject = new MyHashChaining(20);
        mainObject.saveData("DaveLee", "01022223333");
        mainObject.saveData("fun-coding", "01033334444");
        mainObject.saveData("David", "01044445555");
        mainObject.saveData("Dave", "01055556666");
        System.out.println(Arrays.toString(mainObject.hashTable)); 
        System.out.println(mainObject.getDate("David"));
    }


}
