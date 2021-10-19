public class MyHash {
    
    Slot[] hashTable = null;

    public MyHash(int size){
        this.hashTable = new Slot[size];
    }

    public class Slot{
        String value;
        public Slot(String value){
            this.value = value;
        }
    }

    public int hashFunc(String key){
        return (int)(key.charAt(0)) % this.hashTable.length;
    }

    public boolean saveData(String key, String value){
        Integer hash = hashFunc(key);
        
            if(this.hashTable[hash] != null){
                this.hashTable[hash].value = value;
            }else{
                this.hashTable[hash] = new Slot(value);
            }

        return true;
    }

    public String getDate(String key){
        Integer hash = hashFunc(key);
        if(this.hashTable[hash] != null){
            return this.hashTable[hash].value;
        }else{
            return null;
        }
    }

    public static void main(String[] args) {
        
    }
}
