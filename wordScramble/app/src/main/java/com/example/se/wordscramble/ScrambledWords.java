package com.example.se.wordscramble;

import java.util.ArrayList;
import java.util.Random;

public class ScrambledWords {

    Random rand = new Random();
    ArrayList<String> wordsOnWrongSpot = new ArrayList<>();
    String[] arr;
    int[] indice;

    ScrambledWords(String str){
        arr = cutString(str);
        indice = new int[arr.length];
    }

    public String[] cutString(String str){
        arr = str.split(" ");
        return arr;
    }

    public int[] firstScramble(){
        for(int i = 0; i < arr.length; i++){
            indice[i] = -1;
        }
        return 
    }

    public void scramble(){
        updateWrongWords();
        for(int i = 0; i < indice.length; i++) {
            if (indice[i] == -1) {
                arr[i] = wordsOnWrongSpot.get(rand.nextInt(wordsOnWrongSpot.size()-1));
            }
        }
    }

    public void updateWrongWords(){
        for(int i = 0; i < arr.length; i++){
            if(indice[i] != -1){
                wordsOnWrongSpot.add(arr[i]);
            }
        }
    }
}
