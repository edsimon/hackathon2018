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
        test(arr);
        arr.scramble(arr.guessingSentence);
        test(arr);
    }

    public void test(ScrambledWords arr){
        int size = arr.rightSentence.size();
        for (int i = 0; i < size; i++) {
            System.out.print(arr.rightSentence.get(i).getBlockID() + " ");
        }
        System.out.println();
        for (int j = 0; j < size; j++) {
            System.out.print(arr.guessingSentence.get(j).getBlockID() + " ");
        }
    }
}
