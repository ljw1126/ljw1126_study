public class MyHashLinear {
    Slot[] hashTable = null;

    public MyHashLinear(int size){
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
        Integer hash = hashFunc(key);
        
            if(this.hashTable[hash] != null){
                if(this.hashTable[hash].key == key){
                    this.hashTable[hash].value = value; 
                    return true;
                }else{
                    Integer otherHash = hash; 
                    while(this.hashTable[otherHash] != null){

                        if(this.hashTable[otherHash].key == key){
                            this.hashTable[otherHash].value = value;
                            return true;
                        }

                        otherHash++;
                        if(otherHash >= this.hashTable.length) return false; //배열 길이 초과 
                    }

                    this.hashTable[otherHash] = new Slot(key, value);
                    return true;
                }
            }else{
                this.hashTable[hash] = new Slot(key, value);
            }

        return true;
    }

    public String getDate(String key){
        Integer hash = hashFunc(key);
        if(this.hashTable[hash] != null){
            if(this.hashTable[hash].key == key){
                return this.hashTable[hash].value;
            }else{
                Integer otherHash = hash;
                while(this.hashTable[otherHash] != null){
                    if(this.hashTable[otherHash].key == key){
                        return this.hashTable[otherHash].value;
                    }
                    otherHash++;
                    if(otherHash >= this.hashTable.length) return null;
                }
            }
        }
        return null;
    }

    /*
        211129 좀더 깔끔 
        public boolean saveData(String key, String value){

        int hash = this.hashFunc(key);

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
                if(this.hashTable.length <= hash) return false;
            }
            
            this.hashTable[hash] = new Slot(key, value);
            return true;
                        
        }
    }

    public String getData(String key){
        int hash = this.hashFunc(key);

        if(this.hashTable[hash] != null){
            
            while(this.hashTable[hash] != null){
                if(this.hashTable[hash].key == key){
                    return this.hashTable[hash].value;
                }
                hash++;
                if(this.hashTable.length <= hash) return null;
            }

        }

        return null;
    }


    */

    public static void main(String[] args) {
        MyHashLinear mainObject = new MyHashLinear(20);
        mainObject.saveData("DaveLee", "01022223333");
        mainObject.saveData("fun-coding", "01033334444");
        mainObject.saveData("David", "01044445555");
        mainObject.saveData("Dave", "01055556666");
        System.out.println(mainObject.getDate("Dave")); 
    }
        
}
