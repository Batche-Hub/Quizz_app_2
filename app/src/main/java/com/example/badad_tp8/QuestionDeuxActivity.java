package com.example.badad_tp8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.badad_tp8.R;

import java.util.ArrayList;
import java.util.List;

public class QuestionDeuxActivity extends AppCompatActivity {

    public static final String TAG = "ListViewExample";
    private ListView listView;
    private Button button;
    List<Answer> listeA = new ArrayList<>();
    Answer[] answers;
    ImageView goodA;
    ImageView wrongA;
    Integer resultat;
    public static final String EXTRA_MESSAGE = "com.example.badad_tp7.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_deux);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        resultat = Integer.valueOf(message);

        this.listView = (ListView) findViewById(R.id.listView);
        this.button = (Button) findViewById(R.id.button);
        this.goodA = (ImageView) findViewById(R.id.goodA);
        this.wrongA = (ImageView) findViewById(R.id.wrongA);


        this.listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i(TAG, "onItemClick: " +position);
                CheckedTextView v = (CheckedTextView) view;
                boolean currentCheck = v.isChecked();
                Answer answer = (Answer) listView.getItemAtPosition(position);
                answer.setActive(currentCheck);
            }
        });
        //

        this.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
            }
        });

        this.initListViewData();
    }

    private void initListViewData()  {
        Answer q2Nombre1 = new Answer("3557", true, true);
        Answer q2Nombre2 = new Answer("57", false, false);
        Answer q3Nombre3 = new Answer("997", true, false);
        Answer q4Nombre4 = new Answer("1011", false, false);

        answers = new Answer[]{q2Nombre1,q2Nombre2,q3Nombre3,q4Nombre4};

        ArrayAdapter<Answer> arrayAdapter = new ArrayAdapter<Answer>(this, android.R.layout.simple_list_item_checked , answers);

        this.listView.setAdapter(arrayAdapter);

        for(int i=0;i< answers.length; i++ )  {
            this.listView.setItemChecked(i,answers[i].getActive());
        }
    }

    public void checkAnswer()  {

        SparseBooleanArray sp = listView.getCheckedItemPositions();
        List<Boolean> listA = new ArrayList<>();

        listView.setEnabled(false);
        button.setText("Aller au résultat");
        button.setOnClickListener(resultatListener);


        for(int i=0;i<sp.size();i++){
            if(sp.valueAt(i)==true){
                Answer answer = (Answer) listView.getItemAtPosition(i);
                listA.add(answer.getAnswerIsCorrect());
            }
        }

        if(listA.contains(true) && listA.size()==2){
            System.out.println("Vrai");
            Toast toast = Toast.makeText(QuestionDeuxActivity.this, "Bravo !", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER_HORIZONTAL,0,0);
            toast.show();
            goodA.setVisibility(View.VISIBLE);
            listView.setEnabled(false);
            resultat += 1;
        }else{
            System.out.println("faux");
            Toast toast2 = Toast.makeText(QuestionDeuxActivity.this, "Quel dommage ! Les bonnes réponses sont : "+ (Answer)answers[0]+" et "+ (Answer)answers[2], Toast.LENGTH_LONG);
            toast2.setGravity(Gravity.CENTER_HORIZONTAL,0,0);
            toast2.show();
            wrongA.setVisibility(View.VISIBLE);
            listView.setItemChecked(0,true);
            listView.setItemChecked(1,false);
            listView.setItemChecked(2,true);
            listView.setItemChecked(3,false);
            listView.setEnabled(false);
        }
    }

    private View.OnClickListener resultatListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent =  new Intent(QuestionDeuxActivity.this, ResultatActivity.class);
            String message = String.valueOf(resultat);
            intent.putExtra(EXTRA_MESSAGE,message);
            startActivity(intent);
        }
    };

}
