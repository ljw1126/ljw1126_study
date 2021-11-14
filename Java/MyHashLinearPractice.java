
import java.util.*;

public class MyHashLinearPractice { // 닫힌 해쉬법 , 고정길이 배열만 사용, 동일한 hash주소에 값있을시 hash 를 증가 시키면서 빈 곳 찾음
    
    Slot[] hashTable = null;

    public MyHashLinearPractice(int size){
        this.hashTable = new Slot[size];
    }

    public class Slot{
        String key;
        String value;
        
        public Slot(String key, String value){
            this.key = key;
            this.value = value; 
        }
    }

    public int hashFunc(String key){
        return (int)(key.charAt(0)) % this.hashTable.length;
    }

    public boolean saveData(String key, String value){
        int hash = hashFunc(key);

        if(this.hashTable[hash] == null){
            this.hashTable[hash] = new Slot(key, value);
            return true;
        }else{

            while(this.hashTable[hash] != null){
                if(this.hashTable[hash].key == key){
                    this.hashTable[hash].value = value;
                    return true;
                }
                hash++;
                if(hash >= this.hashTable.length) return false; 
            }
  
            this.hashTable[hash] = new Slot(key, value);
            return true;
            
        }

    }

    public String getDate(String key){

        int hash = hashFunc(key);

        if(this.hashTable[hash] == null){
            return null;
        }else{
            while(this.hashTable[hash] != null){
                if(this.hashTable[hash].key == key){
                    return this.hashTable[hash].value;
                }

                hash++;
                if(hash >= this.hashTable.length) return null;
            }
            return null;
        }
    }

    public static void main(String[] args) {
        MyHashLinear mainObject = new MyHashLinear(20);
        mainObject.saveData("DaveLee", "01022223333");
        mainObject.saveData("fun-coding", "01033334444");
        mainObject.saveData("David", "01044445555");
        mainObject.saveData("Dave", "01055556666");
        System.out.println(mainObject.getDate("Dave")); 
        System.out.println(mainObject.getDate("DaveLee")); 
        System.out.println(mainObject.getDate("Developer"));
        System.out.println(Arrays.toString(mainObject.hashTable)); 
    }

}
