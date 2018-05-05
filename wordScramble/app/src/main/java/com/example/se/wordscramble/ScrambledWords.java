package com.example.se.wordscramble;
import java.util.ArrayList;
import java.util.Random;

public class ScrambledWords {

    Random rand = new Random();
    ArrayList<Tuple> rightSentence = new ArrayList<>();
    ArrayList<Tuple> guessingSentence = new ArrayList<>();

    ScrambledWords(String correct, String nativeLang) {
        rightSentence    = cutString(correct, rightSentence);
        guessingSentence = cutString(nativeLang, guessingSentence);
    }

    public ArrayList<Tuple> cutString(String str, ArrayList<Tuple> arr) {
        String[] temp = str.split(" ");
        for (int i = 0; i < temp.length; i++) {
            Tuple t = new Tuple(temp[i], i);
            arr.add(t);
        }
        return arr;
    }

    public ArrayList<Tuple> scramble(ArrayList<Tuple> arr){
        ArrayList<Tuple> temp = new ArrayList<Tuple>();
        int size = arr.size();
        for (int i = 0; i < size; i++) {
            int randomIndex = rand.nextInt(arr.size());
            temp.add(arr.get(randomIndex));
            arr.remove(randomIndex);
            guessingSentence = temp;
            System.out.println();
        }
        return temp;
    }


}