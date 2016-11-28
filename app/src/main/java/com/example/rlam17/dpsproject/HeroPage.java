package com.example.rlam17.dpsproject;

import android.app.ActionBar;
import android.content.Intent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


/**
 * Created by rlam17 on 11/14/2016.
 *
 *
 *
 */


public class HeroPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hero_page);


        Intent intent = getIntent();
        String[] heroListing = intent.getStringArrayExtra("heroChosen");

        for(String i : heroListing)
        System.out.println(i);

        setTitle(heroListing[0]);

        // Table columns are:
        //Heroname lmb1	lmb1pc	lmb1ps	rmb	rmbpc	rmbps	shift	shiftps	e	epc	q	qps	health	armor	shield	lmb2	lmb2pc	lmb2ps

        TextView lmb = (TextView)findViewById(R.id.lmbValue);
        lmb.setText(heroListing[1]);

        TextView rmb = (TextView)findViewById(R.id.rmbValue);
        rmb.setText(heroListing[4]);

        TextView shift = (TextView)findViewById(R.id.shiftValue);
        shift.setText(heroListing[7]);

        TextView e = (TextView)findViewById(R.id.eValue);
        e.setText(heroListing[9]);

        TextView q = (TextView)findViewById(R.id.qValue);
        q.setText(heroListing[11]);
    }

}
