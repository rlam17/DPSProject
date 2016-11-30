package com.example.rlam17.dpsproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

/**
 * Created by rlam17 on 11/29/2016.
 */

public class HeroEdit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hero_edit);

        Intent intent = getIntent();
        final String[] heroListing = intent.getStringArrayExtra("editThisHero");

        //for(String i : heroListing) System.out.println(i);

        setTitle(heroListing[0] + " edit page");

        // Table columns are:
        //Heroname lmb1	lmb1pc	lmb1ps	rmb	rmbpc	rmbps	shift	shiftps	e	epc	q	qps	health	armor	shield	lmb2	lmb2pc	lmb2ps

        EditText heroHealth = (EditText)findViewById(R.id.editHealth);
        heroHealth.setText(heroListing[13]);

        EditText heroArmor = (EditText)findViewById(R.id.editArmor);
        heroArmor.setText(heroListing[14]);

        EditText heroShield = (EditText)findViewById(R.id.editShield);
        heroShield.setText(heroListing[15]);

        EditText heroLMB1 = (EditText)findViewById(R.id.lmbEdit);
        heroLMB1.setText(heroListing[1]);

        EditText heroLMB2 = (EditText)findViewById(R.id.lmb2edit);
        heroLMB2.setText(heroListing[16]);

        EditText heroRMB = (EditText)findViewById(R.id.rmbEdit);
        heroRMB.setText(heroListing[4]);

        EditText heroShift = (EditText)findViewById(R.id.shiftEdit);
        heroShift.setText(heroListing[7]);

        EditText heroE = (EditText)findViewById(R.id.eEdit);
        heroE.setText(heroListing[9]);

        EditText heroQ = (EditText)findViewById(R.id.qEdit);
        heroQ.setText(heroListing[11]);


    }

}
