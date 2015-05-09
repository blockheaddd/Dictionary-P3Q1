/** Dictionary implemented using hashing. */
class HashDictionary implements Dictionary{
    private static final int defaultSize = 10;
    private HashTable hashTbl; // The hash table
    private int count; // # of records now in table
    private int maxsize; // Maximum size of dictionary
    HashDictionary() { this(defaultSize); }
    HashDictionary(int sz) {
        maxsize = sz;
        hashTbl = new HashTable(maxsize);
        count = 0;
    }
    public void clear() { /** Reinitialize */
        hashTbl = new HashTable(maxsize);
        count = 0;
    }
    public void insert(int k, String e) { /** Insert an element */
        assert count < maxsize : "Hash table is full";
        hashTbl.hashInsert(k, e);
        count++;
    }
    public String remove(int k) { /** Remove an element */
        String temp = hashTbl.hashRemove(k);
        if (temp != null) count--;
        return temp;
    }
    public String removeAny() { /** Remove some element. */
        if (count != 0) {
            count--;
            return hashTbl.hashRemoveAny();
        }
        else return null;
    }
    /** Find a record with key value "k" */
    public String find(int k) { return hashTbl.hashFetch(k); }
    /** Return number of values in the hash table */
    public int size() { return count; }

    public void print()
    {
        hashTbl.print();
    }

}