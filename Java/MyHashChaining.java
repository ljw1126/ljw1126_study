
import java.util.*;

public class MyHashChaining {
    // LinkedList를 이용한 오픈 해싱법 
    Slot[] hashTable;

    public MyHashChaining(int size){
        this.hashTable = new Slot[size];
    }

    public class Slot{
        String key;
        String value;
        Slot next; 

        public Slot(String key,String value){
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    public int hashFunc(String key){
        return (int)(key.charAt(0)) % this.hashTable.length;
    }

    public boolean saveData(String key, String value){
        Integer hash = this.hashFunc(key);
        
            if(this.hashTable[hash] != null){
                
                Slot findSlot = this.hashTable[hash];
                Slot prevSlot = this.hashTable[hash];

                while(findSlot != null){
                    if(findSlot.key == key){
                        findSlot.value = value; 
                        return true; // 여기 틀림 
                    }

                    prevSlot = findSlot;
                    findSlot = findSlot.next;
                      
                }

                prevSlot.next = new Slot(key, value);     // 여기 틀림
            }else{
                this.hashTable[hash] = new Slot(key, value);
            }

        return true;
    }

    public String getDate(String key){
        Integer hash = this.hashFunc(key);

        if(this.hashTable[hash] != null){
            Slot findSlot = this.hashTable[hash];
            while(findSlot != null){ // while 대신 if라 해서 틀렸음..
                if(findSlot.key == key){
                    return findSlot.value;
                }
                findSlot = findSlot.next;
            }            
        }
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
