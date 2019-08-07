package com.explample.sam.appinterview;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class Simple_question extends AppCompatActivity implements View.OnClickListener {

    TextView tvquestions, tvanswers, tvtotallength_yy, tvpresentindex_xx;
    Button bleft, bshow, bright;



    String[] simple_questions, simple_answers;
    int index;
    private static final String default_answer ="Press \"A\" Button for the Answer";

    TextToSpeech ttsobject;
    int result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_layout);



        //code to add custom tiltle bar
        LinearLayout questions_11 = (LinearLayout)findViewById(R.id.question_title_bar);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.question_title_bar);

        Button bspeak =(Button)findViewById(R.id.bspeak);
        Button bstop_mute =(Button)findViewById(R.id.bstop_mute);

        TextView tv_category = (TextView)findViewById(R.id.tv_question_titlebar);
        tv_category.setText("Simple Questions");


        tvquestions = (TextView) findViewById(R.id.tvquestion);
        tvanswers = (TextView) findViewById(R.id.tvanswer);
        tvpresentindex_xx = (TextView)findViewById(R.id.tvxx);
        tvtotallength_yy = (TextView)findViewById(R.id.tvyy);

        bleft = (Button)findViewById(R.id.bleft);
        bshow = (Button) findViewById(R.id.bshowanswer);
        bright = (Button) findViewById(R.id.bright);

        simple_questions = getResources().getStringArray(R.array.simple_ques);
        simple_answers = getResources().getStringArray(R.array.simple_ans);

        bleft.setOnClickListener(this);
        bshow.setOnClickListener(this);
        bright.setOnClickListener(this);
        bspeak.setOnClickListener(this);
        bstop_mute.setOnClickListener(this);

        index = 0;
        tvquestions.setText(simple_questions[index]);
        tvanswers.setText(default_answer);
        tvpresentindex_xx.setText(String.valueOf(index+1));
        tvtotallength_yy.setText( "/"+String.valueOf(simple_questions.length));

        // TTS object and Listener initialization

        ttsobject = new TextToSpeech(Simple_question.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    result =    ttsobject.setLanguage(Locale.US);

                } else {
                    Toast.makeText(getApplicationContext(), "this feature is not present in your smart phone", Toast.LENGTH_SHORT).show();

                }
            }
        });






    }

        @Override
        public void onClick(View v) {

        switch (v.getId()) {
            case R.id.bleft:
                tvanswers.setText(default_answer);
                index--;

                if(index == -1){

                    index = simple_questions.length-1;
                    tvquestions.setText(simple_questions[index]);
                    tvpresentindex_xx.setText(String.valueOf(index+1));

                }
                else{
                    tvquestions.setText(simple_questions[index]);
                    tvpresentindex_xx.setText(String.valueOf(index+1));

                }


                break;


            case R.id.bshowanswer:
                tvanswers.setText(simple_questions[index]);

            case R.id.bright:
                tvanswers.setText(default_answer);
                index++;

                if(index == simple_questions.length){

                    index=0;
                    tvquestions.setText(simple_questions[index]);
                    tvpresentindex_xx.setText(String.valueOf(index+1));

                }

                else{
                    tvquestions.setText(simple_questions[index]);
                    tvpresentindex_xx.setText(String.valueOf(index+1));
                }



                break;


            case R.id.bspeak :


                if(result == TextToSpeech.LANG_NOT_SUPPORTED || result == TextToSpeech.LANG_NOT_SUPPORTED){
                    Toast.makeText(getApplicationContext(), "this feature is not present in your smart phone", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(tvanswers.getText().toString().equals(default_answer)){

                    }
                    else {

                        ttsobject.speak(simple_questions[index], TextToSpeech.QUEUE_FLUSH, null);
                    }
                }


                break;

            case R.id.bstop_mute:

                if(ttsobject != null){
                    ttsobject.stop();
                }



                break;

        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(ttsobject !=null){
            ttsobject.stop();
            ttsobject.shutdown();
        }
    }
}