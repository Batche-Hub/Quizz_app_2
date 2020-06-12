package com.example.badad_tp8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.badad_tp8.R;

public class ResultatActivity extends AppCompatActivity {

    String resultatStr;
    Integer resultat;
    TextView resultatText;
    TextView resultatBof;
    TextView resultatCool;
    TextView resultatNul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultat);

        resultatText = (TextView) findViewById(R.id.textViewResultat);
        resultatBof = (TextView) findViewById(R.id.textViewBof);
        resultatCool = (TextView) findViewById(R.id.textViewCool);
        resultatNul = (TextView) findViewById(R.id.textViewNull);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        resultat = Integer.valueOf(message);

        resultatText.setText(message+"/2");

        if (resultat == 0){
            resultatNul.setVisibility(View.VISIBLE);
        }
        if(resultat == 1){
            resultatBof.setVisibility(View.VISIBLE);
        }
        if(resultat == 2){
            resultatCool.setVisibility(View.VISIBLE);
        }
    }
}
