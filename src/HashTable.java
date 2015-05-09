import java.util.Random;

/**
 * Created by GusSilva on 5/4/15.
 */

//Original Class was a template
// I decided to remove generic types and use
// specific types so I can create a better
// hash function based on the type.

public class HashTable {

    private static int SIZE;
    private static int accessCount = 1;
    KVpair HT[];


    HashTable()    //Default Constructor
    {
        SIZE = 10;
        HT = new KVpair[SIZE];
    }

    HashTable(int sz)
    {
        SIZE = sz;
        HT = new KVpair[SIZE];
    }

    int h(int key)
    {
        return Math.abs(key % SIZE);
    }

    int linProbe(int key, int i) //Linear Probing
    {
        if(i == 1)
            System.out.println("Number of Accesses: " + accessCount);
        accessCount = i;
        return i;
    }

    int quadProbe(int key, int i) //Quadratic Probing
    {
        //System.out.println("Call quadProbe: " + i + "     TRYING POS: " + pos);
        return (i*i + i)/2;
    }

    void hashInsert(int k, String r) {
        int home; // Home position for r
        int pos = home = h(k); // Initial position
        for (int i=1; HT[pos] != null; i++) {
            pos = (home + quadProbe(k, i)) % SIZE; // Next probe slot
            System.out.println("Call quadProbe: " + i + "     TRYING POS: " + pos);
            assert HT[pos].key() != k :
                    "Duplicates not allowed";
        }
        HT[pos] = new KVpair(k, r); // Insert R
    }

    /** Search in hash table HT for the record with key k */
    int hashSearch(int k) {
        int home; // Home position for k
        int pos = home = h(k); // Initial position
        for (int i = 1; (HT[pos] != null) && (HT[pos].key() != k) ; i++)
            pos = (home + linProbe(k, i)) % SIZE; // Next probe position
        if (HT[pos] == null) return -1; // Key not in hash table
        else return pos; // Found it
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
        String temp = HT[pos].value();
        HT[pos] = null; //Remove Value
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
        return temp;
    }

    void print()
    {
        System.out.println("KEY:    |   VALUE:");
        for(int i = 0; i < HT.length; i++)
        {
            System.out.println(HT[i].key() + "  |   " + HT[i].value());
        }
    }
}
