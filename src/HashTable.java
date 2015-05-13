/**
 * HashTable.java
 *
 * A class that represents the HashTable Data Structure
 * using hashing as a means of inserting and removing
 * Some of the code is from  the Data Structures book
 * with major modifications by Gus Silva and Anil Jethani
 * Using IntelliJ IDEA 14
 * On 5/4/14
 */

import java.util.Random;

//Original Class was a template
// I decided to remove generic types and use
// specific types so I can create a better
// hash function based on the type.

public class HashTable {

    private int SIZE;
    private int hashType;
    private final int LINEAR = 1, QUADRATIC = 2, PSEUDORANDOM = 3;
    private int accessCount = 1;
    private float count = 0;
    float alpha;
    KVPair HT[];
    int randArr[];


    HashTable()    //Default Constructor
    {
        SIZE = 10;
        HT = new KVPair[SIZE];
    }

    HashTable(int sz, int hshTyp)
    {
        SIZE = sz;
        HT = new KVPair[SIZE];

        if(hshTyp < 1 || hshTyp > 3)
        {
            hashType = LINEAR;
            System.out.println("Invalid Hash Type. Set to Linear.");
        }

        hashType = hshTyp;

        //For Pseudo Random Probing
        randArr = new int[SIZE];
        Random rand = new Random();
        for(int i =0; i < SIZE; i++)
        {
            randArr[i] = rand.nextInt(50);
        }
    }

    int h(int key)
    {
        return Math.abs(key % SIZE);
    }

    int linProbe(int key, int i) //Linear Probing
    {
        return i;
    }

    int quadProbe(int key, int i) //Quadratic Probing
    {
        return (i*i + i)/2;
    }

    int pRandProbe(int ket, int i) //Pseudo Random Probing
    {
        i %= SIZE; //Prevent Index out of Bounds error
        return randArr[i];
    }

    void hashInsert(int k, String r) {
        int home; // Home position for r
        int pos = home = h(k); // Initial position
        if(HT[pos] != null) { //If first position is not available begin probing
            int i = 1;
            do {
                accessCount = i;

                if (hashType == PSEUDORANDOM)
                    pos = (home + pRandProbe(k, i)) % SIZE; // Next probe slot using pseudo random probing
                else if (hashType == QUADRATIC)
                    pos = (home + quadProbe(k, i)) % SIZE; // Next probe slot using quadratic probing
                else
                    pos = (home + linProbe(k, i)) % SIZE; // Next probe slot using linear probing

                if (HT[pos] != null) {
                    if (HT[pos].key() == k) {
                        System.out.println("Duplicates not allowed");
                        return;
                    }
                }
                i++;
            } while (HT[pos] != null);
        }

        HT[pos] = new KVPair(k, r); // Insert R
        count++;
        alpha = count / (float)SIZE;
        System.out.println("N: " + accessCount + "  A: " + alpha);
    }

    /** Search in hash table HT for the record with key k */
    int hashSearch(int k) {
        int home; // Home position for k
        int pos = home = h(k); // Initial position
        int i = 1;
        if(HT[pos] != null && HT[pos].key() == k)
        {
            return pos;
        }
        do {
            if(hashType == PSEUDORANDOM)
                pos = (home + pRandProbe(k, i)) % SIZE; // Next probe slot using pseudo random probing
            else if(hashType == QUADRATIC)
                pos = (home + quadProbe(k, i)) % SIZE; // Next probe slot using quadratic probing
            else
                pos = (home + linProbe(k, i)) % SIZE; // Next probe slot using linear probing
            i++;

            if(HT[pos] != null && HT[pos].key() == k)
                return pos;
        }while(i < SIZE);

        if (HT[pos] == null) {
            System.out.println("HT[" + pos + "] == null");
            return -1; // Key not in hash table
        }
        else
            return pos;

    }

    String hashFetch(int k)
    {
        int pos = hashSearch(k);
        if(pos == -1)
            return null;
        else
            return HT[pos].value();
    }

    String hashRemove(int k)
    {
        int pos = hashSearch(k);
        if (pos == -1 )
        {
            System.out.println("Key: " + k + " NOT found");
            return null;
        }
        String temp = HT[pos].value();
        HT[pos] = null; //Remove Value
        System.out.println("Key: " + k + " removed.");
        count--;
        return temp;
    }

    String hashRemoveAny()
    {
        Random rand = new Random();
        int pos = rand.nextInt(SIZE);
        while(HT[pos] == null)
        {
            pos = rand.nextInt(SIZE);
        }
        String temp = HT[pos].value();
        HT[pos] = null; //Remove Value
        count--;

        alpha = count / (float)SIZE;
        System.out.println("N: " + accessCount + "  A: " + alpha);
        return temp;
    }

    void print()
    {
        System.out.printf("%-5s | %-5s | %s \n", "Pos: ", "Key: ", "Value: ");
        for(int i = 0; i < HT.length; i++)
        {
            if(HT[i] == null)
                System.out.printf("%-5s | %-5s | %s \n", i, "null", "null");
            else
                System.out.printf("%-5s | %-5s | %s \n", i, HT[i].key(), HT[i].value());

        }
    }
}
