import java.util.Random;

/**
 * Created by GusSilva on 5/4/15.
 */
public class HashTable<Key extends Comparable<? super Key>, E> {

    private static int SIZE;
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

    int h(Key k)
    {
        return Math.abs((Integer)k % SIZE);
    }

    int linProbe(Key k, int i) //Linear Probing
    {
        System.out.println("Call linProbe: " + i);
        return 3*i;
    }

    int quadProbe(Key k, int i) //Quadratic Probing
    {
        System.out.println("Call quadProbe: " + i);
        return ( i*i + 5*i + 6);
    }

    void hashInsert(Key k, E r) {
        int home; // Home position for r
        int pos = home = h(k); // Initial position
        for (int i=1; HT[pos] != null; i++) {
            pos = (home + linProbe(k, i)) % SIZE; // Next probe slot
            assert HT[pos].key().compareTo(k) != 0 :
                    "Duplicates not allowed";
        }
        HT[pos] = new KVpair<Key,E>(k, r); // Insert R
    }

    /** Search in hash table HT for the record with key k */
    int hashSearch(Key k) {
        int home; // Home position for k
        int pos = home = h(k); // Initial position
        for (int i = 1; (HT[pos] != null) &&
                (HT[pos].key().compareTo(k) != 0); i++)
            pos = (home + linProbe(k, i)) % SIZE; // Next probe position
        if (HT[pos] == null) return -1; // Key not in hash table
        else return pos; // Found it
    }

    E hashFetch(Key k)
    {
        int pos = hashSearch(k);
        if(pos == -1)
            return null;
        else
            return (E)HT[pos].value();
    }

    E hashRemove(Key k)
    {
        int pos = hashSearch(k);
        E temp = (E)HT[pos].value();
        HT[pos] = null; //Remove Value
        return temp;
    }

    E hashRemoveAny()
    {
        Random rand = new Random();
        int pos = rand.nextInt(SIZE);
        while(HT[pos] == null)
        {
            pos = rand.nextInt(SIZE);
        }
        E temp = (E)HT[pos].value();
        HT[pos] = null; //Remove Value
        return temp;
    }
}
