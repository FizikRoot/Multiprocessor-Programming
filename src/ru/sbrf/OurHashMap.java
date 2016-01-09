package ru.sbrf;

public class OurHashMap<K, V> {

    private Entry[] table;
    private int capacity = 8;


    static class Entry<K, V> {
        K key;
        V value;
        Entry next;

        public Entry(K key, V value, Entry<K,V> next){
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public OurHashMap(){
        table = new Entry[capacity];
    }

    public void clear(){
        table = new Entry[capacity];
    }

    private int hash(K key){
        return Math.abs(key.hashCode()) % capacity;
    }

    public int size(){

        int size = 0;

        for (int i = 0 ; i < capacity ; i++){
            if (table[i] != null){
                Entry entry = table[i];
                while(entry!=null){
                    size++;
                    entry=entry.next;
                }
            }
        }

        return size;
    }

    public boolean put(K newKey, V data){

        if (newKey == null)
            return false;

        int hash = hash(newKey);
        Entry<K,V> newEntry = new Entry<>(newKey, data, null);

        if(table[hash] == null){
            table[hash] = newEntry;
        }
        else{
            Entry previous = null;
            Entry current = table[hash];

            while(current != null){
                if(current.key.equals(newKey)){
                    if(previous == null){
                        newEntry.next = current.next;
                        table[hash] = newEntry;
                        return true;
                    }
                    else{
                        newEntry.next = current.next;
                        previous.next = newEntry;
                        return true;
                    }
                }
                previous = current;
                current = current.next;
            }
            if (previous != null) {
                previous.next = newEntry;
            }
        }
        return false;
    }

    public Object get(K key){

        int hash = hash(key);

        if(table[hash] == null){
            return null;
        }
        else{
            Entry temp = table[hash];
            while(temp!= null){
                if(temp.key.equals(key))
                    return temp.value;
                temp = temp.next;
            }
            return null;
        }
    }

    public boolean remove(K deleteKey){

        int hash = hash(deleteKey);

        if(table[hash] == null){
            return false;
        }else{
            Entry previous = null;
            Entry current = table[hash];

            while(current != null){
                if(current.key.equals(deleteKey)){
                    if(previous==null){
                        table[hash]=table[hash].next;
                        return true;
                    }
                    else{
                        previous.next=current.next;
                        return true;
                    }
                }
                previous = current;
                current = current.next;
            }
            return false;
        }

    }


    public K contains(K key){
        int hash = hash(key);
        if(table[hash] == null){
            return null;
        }else{
            Entry temp = table[hash];
            while(temp != null){
                if(temp.key.equals(key))
                    return key;
                temp = temp.next;
            }
            return null;
        }
    }

    public String strSet(){
        String str = "[";

        for(int i = 0 ; i < capacity ; i++){
            if(table[i] != null){
                Entry entry = table[i];
                while (entry != null){
                    str = str + entry.key + ", ";
                    entry = entry.next;
                }
            }
        }
        str = str.substring(0, str.length()-2) + ']';

        return str;
    }


}
