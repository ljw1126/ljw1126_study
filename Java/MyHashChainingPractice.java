﻿import java.util.*;

public class MyHashChainingPractice { // 오픈 해쉬법, LinkedList 사용
    
    Slot[] hashTable;

    public MyHashChainingPractice(int size){
        this.hashTable = new Slot[size];
    }

    public class Slot{
        String key;
        String value;
        Slot next = null;
        public Slot(String key, String value){
            this.key = key;
            this.value = value;
        }
    }

    public int hashFunc(String key){
        return (int)(key.charAt(0))%this.hashTable.length;
    }

    public boolean saveData(String key, String value){
        int hash = this.hashFunc(key);

        if(this.hashTable[hash] == null){
            this.hashTable[hash] = new Slot(key, value);
            return true;
        }else{
            Slot slot = this.hashTable[hash];
            if(slot.key == key){
                slot.value = value;
                return true;
            }else{
                while(slot.next != null){
                    if(slot.next.key == key){
                        slot.next.value = value;
                        return true;
                    }
                    slot = slot.next;
                }
                
                slot.next = new Slot(key, value);
                return true;
            }     
        }

    }

    public String getData(String key){
        
        int hash = this.hashFunc(key);
        if(this.hashTable[hash] != null){

            Slot slot = this.hashTable[hash];
            if(slot.key == key){
                return slot.value;
            }

            while(slot.next != null){
                if(slot.next.key == key){
                    return slot.next.value;
                }
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
        System.out.println(mainObject.getData("David"));
        System.out.println(mainObject.getData("fun-coding"));
        System.out.println(mainObject.getData("DaveLee"));
    }
}
