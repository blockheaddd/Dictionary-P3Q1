/**
 * HashDictionaryTester.java
 *
 * A simple class to test the HashDictionary class
 * Created by Gus Silva and Anil Jethani
 * Using IntelliJ IDEA 14
 * On 5/4/14
 *
 */

import java.util.Random;
import java.util.Scanner;

public class HashDictionaryTester {

    public static void main(String args[]) {

        int SIZE = 32;
        int hashType = 1;
        final int LINEAR = 1, QUADRATIC = 2, PSEUDORANDOM = 3;

        Scanner in = new Scanner(System.in);

        //Size must be a power of two so that quadratic probing reaches all spots
        System.out.println("Please enter size of HashDictionary (Must be Power of 2): ");
        try
        {
            SIZE = in.nextInt();
        }
        catch(Exception e)
        {
            System.out.println("Error with input. Size set to: " + SIZE);
        }

        //If size is not a power of two find closest power of two that is greater than input size
        if(Math.sqrt(SIZE)%2 != 0)
        {
            int tempSz = 2;
            while(tempSz < SIZE)
                tempSz *= 2;
            SIZE = tempSz;
            System.out.println("Size must be a power of two. Size set to: " + SIZE);
        }

        //Create an array of KVPairs with random ID's and elements
        KVpair entries[] = new KVpair[SIZE];
        Random rand = new Random(100);
        for(int i = 0; i < SIZE; i ++)
        {
            KVpair temp = new KVpair(rand.nextInt(100), (char)(i+65) + "");
            entries[i] = temp;
        }

        HashDictionary linHashDict = new HashDictionary(SIZE, LINEAR);
        HashDictionary quadHashDict = new HashDictionary(SIZE, QUADRATIC);
        HashDictionary pRandHashDict = new HashDictionary(SIZE, PSEUDORANDOM);

        Integer key; String value;

        System.out.println("============= INSERT ============");
        //Insert into Hash Dictionary using Linear Probing
        System.out.println("\n= 1. Linear Probing =\n");
        for(int i=0; i < SIZE; i++) {
            key = entries[i].key();
            value = entries[i].value();
            linHashDict.insert(key, value);
        }
        //Insert into Hash Dictionary using Quadratic Probing
        System.out.println("\n= 2. Quadratic Probing =\n");
        for(int i=0; i < SIZE; i++) {
            key = entries[i].key();
            value = entries[i].value();
            quadHashDict.insert(key, value);
        }
        //Insert into Hash Dictionary using Linear Probing
        System.out.println("\n= 3. Pseudo Random Probing =\n");
        for(int i=0; i < SIZE-1; i++) {
            key = entries[i].key();
            value = entries[i].value();
            pRandHashDict.insert(key, value);
        }

        linHashDict.print();

        //Remove from Hash Dictionaries
        System.out.println("\n=========== DELETE ===========\n");
        String temp;
        //Delete from Hash Dictionary using Linear Probing
        System.out.println("\n= 1. Linear Probing =\n");
        for(int i=0; i < SIZE; i++) {
            key = entries[i].key();
            linHashDict.remove(key);
        }
        //Delete from Hash Dictionary using Quadratic Probing
        System.out.println("\n= 2. Quadratic Probing =\n");
        for(int i=0; i < SIZE; i++) {
            key = entries[i].key();
            quadHashDict.remove(key);
        }
        //Delete from Hash Dictionary using Linear Probing
        System.out.println("\n= 3. Pseudo Random Probing =\n");
        for(int i=0; i < SIZE-1; i++) {
            key = entries[i].key();
            pRandHashDict.remove(key);
        }

        linHashDict.print();

    }
}
