package com.explample.sam.appinterview;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class FrontPage extends AppCompatActivity implements View.OnClickListener {
    Button bsimple,btough,bseeotherapps,brateappps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.front_page);

        //code to add custom tiltle bar
        LinearLayout front11 = (LinearLayout)findViewById(R.id.frontpage_title_bar);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.frontpage_title_bar);

        bsimple=(Button)findViewById(R.id.bsq);
        btough=(Button)findViewById(R.id.btq);
        bseeotherapps=(Button)findViewById(R.id.bseeotherapp);
        brateappps=(Button)findViewById(R.id.brateapp);

        bsimple.setOnClickListener(this);
        btough.setOnClickListener(this);
        bseeotherapps.setOnClickListener(this);
        brateappps.setOnClickListener(this);

    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.bsq:
                Intent i = new Intent(FrontPage.this, Simple_question.class);
                startActivity(i);


            break;
            case R.id.btq:
                Intent j = new Intent(FrontPage.this, Tough_question.class);
                startActivity(j);
            break;
            case R.id.bseeotherapp:
                try{
                    Uri uri1 =Uri.parse("market://search?q=Sam");
                    Intent goToMarket1 = new Intent(Intent.ACTION_VIEW,uri1);
                    startActivity(goToMarket1);
                }
                catch (ActivityNotFoundException e){
                    Uri uri1 =Uri.parse("https://play.google.com/store/apps/search?q=Sam");
                    Intent goToMarket1 = new Intent(Intent.ACTION_VIEW,uri1);
                    startActivity(goToMarket1);
                }




                break;
            case R.id.brateapp:

                try{
                    Uri uri1 =Uri.parse("market://details?id="+getPackageName());
                    Intent goToMarket1 = new Intent(Intent.ACTION_VIEW,uri1);
                    startActivity(goToMarket1);
                }
               catch (ActivityNotFoundException e){
                   Uri uri1 =Uri.parse("https://play.google.com/store/apps/details?id="+getPackageName());
                   Intent goToMarket1 = new Intent(Intent.ACTION_VIEW,uri1);
                   startActivity(goToMarket1);
               }
                break;

        }

    }
}
