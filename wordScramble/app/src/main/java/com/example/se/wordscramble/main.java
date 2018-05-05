package com.example.se.wordscramble;

import java.util.Arrays;

public class main {
    public static void main(String[] args) {
        main main = new main();
        main.run();
    }
    public void run(){
        ScrambledWords arr = new ScrambledWords("Hej lilla du");
        arr.scramble();
        test(arr);
    }

    public void test(ScrambledWords arr){
        System.out.println(Arrays.toString(arr.arr));
        System.out.println(Arrays.toString(arr.indice));
    }
}
