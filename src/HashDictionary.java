/** Dictionary implemented using hashing. */
class HashDictionary<Key extends Comparable<? super Key>, E>
        implements Dictionary<Key, E> {
    private static final int defaultSize = 10;
    private HashTable<Key,E> hashTbl; // The hash table
    private int count; // # of records now in table
    private int maxsize; // Maximum size of dictionary
    HashDictionary() { this(defaultSize); }
    HashDictionary(int sz) {
        hashTbl = new HashTable<Key,E>(sz);
        count = 0;
        maxsize = sz;
    }
    public void clear() { /** Reinitialize */
        hashTbl = new HashTable<Key,E>(maxsize);
        count = 0;
    }
    public void insert(Key k, E e) { /** Insert an element */
        assert count < maxsize : "Hash table is full";
        hashTbl.hashInsert(k, e);
        count++;
    }
    public E remove(Key k) { /** Remove an element */
        E temp = hashTbl.hashRemove(k);
        if (temp != null) count--;
        return temp;
    }
    public E removeAny() { /** Remove some element. */
        if (count != 0) {
            count--;
            return hashTbl.hashRemoveAny();
        }
        else return null;
    }
    /** Find a record with key value "k" */
    public E find(Key k) { return hashTbl.hashFetch(k); }
    /** Return number of values in the hash table */
    public int size() { return count; }
}