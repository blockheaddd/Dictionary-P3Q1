/**
 * HashDictionary.java
 *
 * A class that implements the Dictionary ADT
 * using hashing as a means of inserting and removing
 * Code is from  the Data Structures book
 * with slight modifications by
 * Gus Silva and Anil Jethani
 * Using IntelliJ IDEA 14
 * On 5/4/14
 */

class HashDictionary implements Dictionary{
    private static final int defaultSize = 10;
    private HashTable hashTbl; // The hash table
    private int count; // # of records now in table
    private int maxsize; // Maximum size of dictionary
    private int hashType; //Type of hashing to perform

    HashDictionary() { this(defaultSize, 1); }

    HashDictionary(int sz, int hshTyp) {
        maxsize = sz;
        hashType = hshTyp;
        hashTbl = new HashTable(maxsize, hashType);
        count = 0;
    }
    public void clear() { /** Reinitialize */
        hashTbl = new HashTable(maxsize, hashType);
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