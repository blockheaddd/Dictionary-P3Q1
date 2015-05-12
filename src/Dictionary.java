/**
 * Dictionary.java
 *
 * A class that models the Dictionary ADT
 * Code is from  the Data Structures book
 * with slight modifications by
 * Gus Silva and Anil Jethani
 * Using IntelliJ IDEA 14
 * On 5/4/14
 */
public interface Dictionary {
    /** Reinitialize dictionary */
    public void clear();
    /** Insert a record
     @param k The key for the record being inserted.
     @param e The record being inserted. */
    public void insert(int k, String e);
    /** Remove and return a record.
     @param k The key of the record to be removed.
     @return A maching record. If multiple records match
     "k", remove an arbitrary one. Return null if no record
     with key "k" exists. */
    public String remove(int k);
    /** Remove and return an arbitrary record from dictionary.
     @return the record removed, or null if none exists. */
    public String removeAny();
    /** @return A record matching "k" (null if none exists).
    If multiple records match, return an arbitrary one.
     @param k The key of the record to find */
    public String find(int k);
    /** @return The number of records in the dictionary. */
    public int size();
};