package com.example.rlam17.dpsproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by rlam17 on 11/29/2016.
 */

public class HeroEdit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hero_edit);

        Intent intent = getIntent();
        final String[] heroListing = intent.getStringArrayExtra("heroChosen");

        for(String i : heroListing)
            System.out.println(i);

        setTitle(heroListing[0] + " edit page");

        // Table columns are:
        //Heroname lmb1	lmb1pc	lmb1ps	rmb	rmbpc	rmbps	shift	shiftps	e	epc	q	qps	health	armor	shield	lmb2	lmb2pc	lmb2ps

    }

}
