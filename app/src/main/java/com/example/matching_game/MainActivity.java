package com.example.matching_game;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorInflater;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageView san1;
    ImageView san2;
    ImageView kiki1;
    ImageView kiki2;
    ImageView haku1;
    ImageView haku2;
    ImageView noface1;
    ImageView noface2;
    ImageView howl1;
    ImageView howl2;
    ImageView jiji1;
    ImageView jiji2;
    ImageView chihiro1;
    ImageView chihiro2;
    ImageView totoro1;
    ImageView totoro2;

    Drawable san;
    Drawable kiki;
    Drawable haku;
    Drawable noface;
    Drawable howl;
    Drawable jiji;
    Drawable chihiro;
    Drawable totoro;

    TextView score;
    int currentScore = 0;

    String opened = "";
    Drawable question;

    ImageView lastSelected;

    ArrayList<ImageView> flippedImages = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         san=getResources().getDrawable(R.drawable.san);
         kiki=getResources().getDrawable(R.drawable.kiki);
         haku=getResources().getDrawable(R.drawable.haku);
         noface=getResources().getDrawable(R.drawable.noface);
         howl=getResources().getDrawable(R.drawable.howl);
         jiji=getResources().getDrawable(R.drawable.jiji);
         chihiro=getResources().getDrawable(R.drawable.chihiro);
         totoro=getResources().getDrawable(R.drawable.totoro);

        san1=findViewById(R.id.san1);
        san2=findViewById(R.id.san2);
        kiki1=findViewById(R.id.kiki1);
        kiki2=findViewById(R.id.kiki2);
        haku1=findViewById(R.id.haku1);
        haku2=findViewById(R.id.haku2);
        noface1=findViewById(R.id.noface1);
        noface2=findViewById(R.id.noface2);
        howl1=findViewById(R.id.howl1);
        howl2=findViewById(R.id.howl2);
        jiji1=findViewById(R.id.jiji1);
        jiji2=findViewById(R.id.jiji2);
        chihiro1=findViewById(R.id.chihiro1);
        chihiro2=findViewById(R.id.chihiro2);
        totoro1=findViewById(R.id.totoro1);
        totoro2=findViewById(R.id.totoro2);

        score = findViewById(R.id.score);
        score.setText(String.valueOf(currentScore));

        coverAll();
        addListeners();

        ImageView  restart=findViewById(R.id.restart);

        restart.setOnClickListener(view->{
            flipCoverAll();
        });
    }

    protected void flipCover(ImageView theView){
        theView.animate().rotationYBy(-180).withEndAction(()->{
            theView.setImageDrawable(question);
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    protected void flipCoverAll() {
        currentScore = 0;
        score.setText(String.valueOf(currentScore));

        flippedImages.forEach((theView)->{
            flipCover(theView);
        });
        flippedImages.clear();
    }

    protected void coverAll() {
        question =getResources().getDrawable(R.drawable.question);

        san1.setImageDrawable(question);
        san2.setImageDrawable(question);
        kiki1.setImageDrawable(question);
        kiki2.setImageDrawable(question);
        haku1.setImageDrawable(question);
        haku2.setImageDrawable(question);
        noface1.setImageDrawable(question);
        noface2.setImageDrawable(question);
        howl1.setImageDrawable(question);
        howl2.setImageDrawable(question);
        jiji1.setImageDrawable(question);
        jiji2.setImageDrawable(question);
        chihiro1.setImageDrawable(question);
        chihiro2.setImageDrawable(question);
        totoro1.setImageDrawable(question);
        totoro2.setImageDrawable(question);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    protected void flipCard(String theString, ImageView theView, Drawable theDrawable){
        if(flippedImages.contains(theView)){
            return;
        }
        theView.animate().rotationYBy(180).withEndAction(() ->{
            flippedImages.add(theView);
            theView.setImageDrawable(theDrawable);
                if(opened.equals("")){
                    opened = theString;
                    lastSelected = theView;
                } else if(opened.equalsIgnoreCase(theString)){
                    currentScore++;
                    score.setText(String.valueOf(currentScore));
                    if(currentScore == 8){
                        flipCoverAll();
                        Toast.makeText(getApplicationContext(), "Completed!", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getApplicationContext(), "Correct!", Toast.LENGTH_SHORT).show();
                    }

                    opened = "";
                }else{
                    Toast.makeText(getApplicationContext(), "Wrong!", Toast.LENGTH_SHORT).show();
                    opened = "";

                    lastSelected.animate().rotationYBy(-180).withEndAction(()->{
                        lastSelected.setImageDrawable(question);
                    });

                    theView.animate().rotationYBy(-180).withEndAction(()->{
                        theView.setImageDrawable(question);
                    });

                    flippedImages.remove(theView);
                    flippedImages.remove(lastSelected);
                }
        } );
    }


    protected void addListeners() {
        san1.setOnClickListener(view -> flipCard("san", (ImageView)view, san));
        san2.setOnClickListener(view -> flipCard("san", (ImageView)view, san));
        kiki1.setOnClickListener(view -> flipCard("kiki", (ImageView)view, kiki));
        kiki2.setOnClickListener(view -> flipCard("kiki", (ImageView)view, kiki));
        haku1.setOnClickListener(view -> flipCard("haku", (ImageView)view, haku));
        haku2.setOnClickListener(view -> flipCard("haku", (ImageView)view, haku));
        noface1.setOnClickListener(view -> flipCard("noface", (ImageView)view, noface));
        noface2.setOnClickListener(view -> flipCard("noface", (ImageView)view, noface));
        howl1.setOnClickListener(view -> flipCard("howl", (ImageView)view, howl));
        howl2.setOnClickListener(view -> flipCard("howl", (ImageView)view, howl));
        jiji1.setOnClickListener(view -> flipCard("jiji", (ImageView)view, jiji));
        jiji2.setOnClickListener(view -> flipCard("jiji", (ImageView)view, jiji));
        chihiro1.setOnClickListener(view -> flipCard("chihiro", (ImageView)view, chihiro));
        chihiro2.setOnClickListener(view -> flipCard("chihiro", (ImageView)view, chihiro));
        totoro1.setOnClickListener(view -> flipCard("totoro", (ImageView)view, totoro));
        totoro2.setOnClickListener(view -> flipCard("totoro", (ImageView)view, totoro));
    }
}