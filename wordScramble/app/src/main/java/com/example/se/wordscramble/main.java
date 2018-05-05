package com.example.se.wordscramble;

import java.util.ArrayList;
import java.util.Arrays;

public class main {
    public static void main(String[] args) {
        main main = new main();
        main.run();
    }
    public void run(){
        ScrambledWords arr = new ScrambledWords("Hej lilla du", "Hello little you");
        ArrayList<Tuple> temp = new ArrayList<Tuple>();
        temp = arr.guessingSentence;
        test(arr.guessingSentence);
        temp = arr.scramble(temp);
        test(temp);
        
    }

    public void test(ArrayList<Tuple> arr){
        System.out.println();
        for (Tuple tuple : arr) {
            System.out.print(" " + tuple.getBlockString());
        }
    }
}
