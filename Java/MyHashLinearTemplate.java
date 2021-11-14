import java.util.*;

public class MyHashLinearTemplate {
    
    Slot[] hashTable = null;

    public MyHashLinearTemplate(int size){
        this.hashTable = new Slot[size];
    }

    public class Slot{}

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
        MyHashLinear mainObject = new MyHashLinear(20);
        mainObject.saveData("DaveLee", "01022223333");
        mainObject.saveData("fun-coding", "01033334444");
        mainObject.saveData("David", "01044445555");
        mainObject.saveData("Dave", "01055556666");
        System.out.println(mainObject.getDate("Dave")); 
    }

}
