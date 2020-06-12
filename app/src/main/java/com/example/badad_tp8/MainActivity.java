package com.example.badad_tp8;

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
import androidx.appcompat.app.AppCompatActivity;

import com.example.badad_tp8.R;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "ListViewExample";

    private ListView listView;
    private Button button;
    Answer[] answers;
    ImageView goodA;
    ImageView wrongA;
    Integer resultat = 0;
    public static final String EXTRA_MESSAGE = "com.example.badad_tp7.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.listView = (ListView) findViewById(R.id.listView);
        this.button = (Button) findViewById(R.id.button);
        this.goodA = (ImageView) findViewById(R.id.goodA);
        this.wrongA = (ImageView) findViewById(R.id.wrongA);


        this.listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

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
        Answer q1Blanc = new Answer("Blanc", false, true);
        Answer q1Rouge = new Answer("Rouge", false, false);
        Answer q1Iridescent = new Answer("Iridescente, marbrée de rayures mûres et à la robe châtoyante", false, false);
        Answer q1Aucune = new Answer("Aucune des réponses précédentes", true, false);

        answers = new Answer[]{q1Blanc,q1Rouge,q1Iridescent,q1Aucune};

        ArrayAdapter<Answer> arrayAdapter = new ArrayAdapter<Answer>(this, android.R.layout.simple_list_item_checked , answers);

        this.listView.setAdapter(arrayAdapter);

        for(int i=0;i< answers.length; i++ )  {
            this.listView.setItemChecked(i,answers[i].getActive());
        }

    }

    public void checkAnswer()  {

        SparseBooleanArray sp = listView.getCheckedItemPositions();
        Answer answer = answers[listView.getCheckedItemPosition()];
        listView.setEnabled(false);
        button.setText("Question suivante");
        button.setOnClickListener(questionSuivante);



        for(int i=0;i<sp.size();i++){
            if(sp.valueAt(i)==true){

                if(answer.isAnswerIsCorrect == true){
                    Toast toast = Toast.makeText(MainActivity.this, "Bravo !", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER_HORIZONTAL,0,0);
                    toast.show();
                    goodA.setVisibility(View.VISIBLE);
                    listView.setEnabled(false);
                    resultat += 1;
                }else if (answer.isAnswerIsCorrect == false){
                    Toast toast2 = Toast.makeText(MainActivity.this, "Quel dommage ! La bonne réponse est : "+ (Answer)answers[3], Toast.LENGTH_LONG);
                    toast2.setGravity(Gravity.CENTER_HORIZONTAL,0,0);
                    toast2.show();
                    wrongA.setVisibility(View.VISIBLE);
                    listView.setItemChecked(3,true);
                    listView.setEnabled(false);
                }
            }
        }
    }

    private View.OnClickListener questionSuivante = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent =  new Intent(MainActivity.this, QuestionDeuxActivity.class);
            String message = String.valueOf(resultat);
            intent.putExtra(EXTRA_MESSAGE,message);
            startActivity(intent);
        }
    };

}
