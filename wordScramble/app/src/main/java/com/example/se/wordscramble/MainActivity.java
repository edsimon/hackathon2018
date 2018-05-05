package com.example.se.wordscramble;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ScrambledWords sWords = new ScrambledWords("w1 w2 w3", "w11 w22 w33");
    ArrayList<Button> buttons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttons = startSeq(sWords);

        final Button leBaton = buttons.get(0);

        leBaton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setBackgroundColor(Color.GREEN);

            }
        });


    }

    private ArrayList<Button> startSeq(ScrambledWords sWords) {
        int k = sWords.guessingSentence.size();

        ArrayList<Button> buttons = new ArrayList<>();

        Button b1 = findViewById(R.id.btnId0);
        Button b2 = findViewById(R.id.btnId1);
        Button b3 = findViewById(R.id.btnId2);

        b1.setText(sWords.guessingSentence.get(0).getBlockString().toString());
        b2.setText(sWords.guessingSentence.get(1).getBlockString().toString());
        b3.setText(sWords.guessingSentence.get(2).getBlockString().toString());

        buttons.add(b1);
        buttons.add(b2);
        buttons.add(b3);
        return buttons;



    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.btnId0: buttons.get(0).setText("YOLO");


            case 1: v.setBackgroundColor(2);
            case 2: v.setBackgroundColor(7);
        }

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}