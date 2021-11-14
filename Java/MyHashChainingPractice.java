import java.util.*;

public class MyHashChainingPractice { // 오픈 해쉬법, LinkedList 사용
    
    Slot[] hashTable = null;

    public MyHashChainingPractice(int size){
        this.hashTable = new Slot[size];
    }

    public class Slot{
       String key;
       String value;
       Slot next;

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
            Slot prevSlot = this.hashTable[hash];
            Slot CurntSlot = this.hashTable[hash];
            
            while(CurntSlot != null){
                if(CurntSlot.key == key){
                    CurntSlot.value = value;
                    return true; 
                }
                prevSlot = CurntSlot;
                CurntSlot = CurntSlot.next;
            }

            prevSlot.next = new Slot(key, value);
        
            return true;
        }
    }

    public String getDate(String key){

        int hash = hashFunc(key);
        if(this.hashTable[hash] == null){
            return null;
        }else{
            Slot slot = this.hashTable[hash];

            while(slot != null){
                if(slot.key == key)
                    return slot.value;

                slot = slot.next;    
            }
        }

        return null;
    }

    public static void main(String[] args) {
        MyHashChainingPractice mainObject = new MyHashChainingPractice(20);
        mainObject.saveData("DaveLee", "01022223333");
        mainObject.saveData("fun-coding", "01033334444");
        mainObject.saveData("David", "01044445555");
        mainObject.saveData("Dave", "01055556666");
        System.out.println(Arrays.toString(mainObject.hashTable)); 
        System.out.println(mainObject.getDate("David"));
        System.out.println(mainObject.getDate("fun-coding"));
    }
}
