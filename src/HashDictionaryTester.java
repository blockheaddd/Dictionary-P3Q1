/**
 * Created by GusSilva on 5/4/15.
 */

import java.util.Random;
import java.util.Scanner;

public class HashDictionaryTester {

    public static void main(String args[]) {

        int SIZE = 25;

        //Using variables from book
        int N; // N is number in HashDictionary
        int M; //M is the max size of the HashDictionary
        float alpha;//Alpha is N/M
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter size of HashDictionary: ");

        try{
            SIZE = in.nextInt();
        }
        catch(Exception e)
        {
            System.out.println("Error with input. Size set to: " + SIZE);
        }

        //Create an array of KVPairs with random ID's and elements
        KVpair entries[] = new KVpair[SIZE];
        Random rand = new Random(100);
        for(int i = 0; i < SIZE; i ++)
        {
            KVpair<Integer, String> temp = new KVpair<>(rand.nextInt(), (char)(i+65) + "");
            entries[i] = temp;
        }


        //Insert into HashDictionary
        System.out.println("INSERT \n------");
        HashDictionary<Integer, String> hashDict = new HashDictionary<>(SIZE);
        Integer key; String value;
        for(int i=0; i < SIZE; i++) {
            key = (Integer)entries[i].key();
            value = (String)entries[i].value();

            hashDict.insert(key, value);
            alpha = (float)hashDict.size() / (float)SIZE;
            System.out.println("alpha: " + alpha);
        }

        //Remove from HashDictionary
        System.out.println("REMOVE \n------");
        for(int i = 0; i < SIZE; i++) {
            String temp;
            temp = hashDict.removeAny();
            alpha = (float)hashDict.size() / (float)SIZE;
            System.out.println(temp + "    alpha: " + alpha);
        }


    }
}
