package com.example.adrian.ekolokator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        String text = "Ova aplikacija je kreirana u sklopu nastave kolegija Mobilne aplikacije, na odjelu za informacijske komunikacijske tehnologije Sveučilišta Jurja Dobrile u Puli.\n"
                + "Autori ove aplikacije su Adrian Drozina i Toni Bileta pod mentorstvom doc.dr.sc.Siniše Sovilja.\nOIKT, UNIPU, 2017.";

        TextView textV = (TextView) findViewById(R.id.About);
        textV.setText(text);

        Button back = (Button) findViewById(R.id.AboutBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoBack();
            }
        });
    }

        void GoBack()
    {
        finish();
    }
}
