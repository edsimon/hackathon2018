package com.example.se.wordscramble;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.print.PrintJob;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tView;

    private ScrambledWords sWords = new ScrambledWords("w1 w2 w3", "Everyone is welcome");
    private ArrayList<Button> buttons = new ArrayList<>();
    private ArrayList<Button> ansButtons =  new ArrayList<>();
    private ScrambledWords sCopyCorrect = new ScrambledWords("w1 w2 w3", "Everyone is welcome");

    private int pressCount = 0;

    //TextView assembled = findViewById(R.id.assembledString);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sWords.scramble(sWords.guessingSentence);


        startSeq();

        final Button leBaton = buttons.get(0);
        final Button leBaton2 = buttons.get(1);
        final Button leBaton3 = buttons.get(2);

        final Button leBaton4 = ansButtons.get(0);
        final Button leBaton5 = ansButtons.get(1);
        final Button leBaton6 = ansButtons.get(2);



        leBaton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setVisibility(View.INVISIBLE);
                ansButtons.get(pressCount).setText(leBaton.getText());
                ansButtons.get(pressCount).setBackground(getResources().getDrawable(R.drawable.buttons));
                ansButtons.get(pressCount).setVisibility(View.VISIBLE);
                pressCount++;


                System.out.println(sWords.rightSentence);

                if(isLast()) {
                    adjustColours();

                    if (shouldExecute()) {
                        finishGame();
                    } else resetInputs();
                }


            }
        });

        leBaton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setVisibility(View.INVISIBLE);
                ansButtons.get(pressCount).setText(leBaton2.getText());
                ansButtons.get(pressCount).setBackground(getResources().getDrawable(R.drawable.buttons));
                ansButtons.get(pressCount).setVisibility(View.VISIBLE);

                pressCount++;

                if(isLast()) {
                    adjustColours();

                    if (shouldExecute()) {
                        finishGame();
                    } else resetInputs();
                }


            }
        });

        leBaton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setVisibility(View.INVISIBLE);

                ansButtons.get(pressCount).setText(leBaton3.getText());
                ansButtons.get(pressCount).setBackground(getResources().getDrawable(R.drawable.buttons));
                ansButtons.get(pressCount).setVisibility(View.VISIBLE);

                pressCount++;

                if(isLast()) {
                    adjustColours();

                    if (shouldExecute()) {
                        finishGame();
                    } else resetInputs();
                }

            }
        });


    }

    public boolean shouldExecute(){
        for (Button ansButton : ansButtons) {
            if(ansButton.getBackground().getConstantState()
                    == getResources().getDrawable(R.drawable.failedbutton).getConstantState()){
                return false;
            }
        }
        return true;
    }

    private void startSeq() {




        Button b1 = findViewById(R.id.btnId0);
        Button b2 = findViewById(R.id.btnId1);
        Button b3 = findViewById(R.id.btnId2);

        Button b4 = findViewById(R.id.btnId3);
        Button b5 = findViewById(R.id.btnId4);
        Button b6 = findViewById(R.id.btnId5);

        b1.setText(sWords.guessingSentence.get(0).getBlockString().toString());
        b2.setText(sWords.guessingSentence.get(1).getBlockString().toString());
        b3.setText(sWords.guessingSentence.get(2).getBlockString().toString());

        b4.setVisibility(View.INVISIBLE);
        b5.setVisibility(View.INVISIBLE);
        b6.setVisibility(View.INVISIBLE);

        buttons.add(b1);
        buttons.add(b2);
        buttons.add(b3);
        ansButtons.add(b4);
        ansButtons.add(b5);
        ansButtons.add(b6);



    }

    public void adjustColours(){

        for (int i = 0; i<ansButtons.size();i++) {

            if (ansButtons.get(i).getText().equals(sCopyCorrect.guessingSentence.get(i).getBlockString())) {
                ansButtons.get(i).setBackground(getResources().getDrawable(R.drawable.correctbutton));
            } else
                ansButtons.get(i).setBackground(getResources().getDrawable(R.drawable.failedbutton));
            System.out.println(ansButtons.get(i).getText() + " " + sCopyCorrect.guessingSentence.get(i).getBlockString());
        }



    }

    public void finishGame(){
        for (Button button : buttons) {
            button.setVisibility(View.INVISIBLE);

        }
    }

    public boolean correctAnswer(){
        if(ansButtons.get(0).getText() == sWords.guessingSentence.get(0)
                && ansButtons.get(1).getText() == sWords.guessingSentence.get(1)
                && ansButtons.get(2).getText() == sWords.guessingSentence.get(2)){
            return true;
        }
        return false;
    }

    public void resetInputs() {
        sWords.scramble(sWords.guessingSentence);
        //TimeUnit.SECONDS.sleep(1);

        buttons.clear();
        ansButtons.clear();

        pressCount = 0;
        startSeq();

        for (Button button : buttons) {
            button.setVisibility(View.VISIBLE);
        }
        for(Button button : ansButtons){
            button.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.btnId0: buttons.get(0).setText("YOLO");
            case 1: v.setBackgroundColor(2);
            case 2: v.setBackgroundColor(7);
        }

    }

    public boolean isLast(){
        for (Button button : buttons) {
            if(button.getVisibility() == View.VISIBLE)
                return false;
        }
        return true;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}