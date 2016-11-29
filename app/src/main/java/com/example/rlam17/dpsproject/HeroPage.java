package com.example.rlam17.dpsproject;

import android.app.ActionBar;
import android.content.Intent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.data;


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
        final String[] heroListing = intent.getStringArrayExtra("heroChosen");

        for(String i : heroListing)
        System.out.println(i);

        setTitle(heroListing[0]);

        // Table columns are:
        //Heroname lmb1	lmb1pc	lmb1ps	rmb	rmbpc	rmbps	shift	shiftps	e	epc	q	qps	health	armor	shield	lmb2	lmb2pc	lmb2ps

        TextView has = (TextView)findViewById(R.id.has);
        String hasString = (String)has.getText();

        TextView health = (TextView)findViewById(R.id.health);
        health.setText(heroListing[13]);

        String healthString = (String)health.getText();



        if(!heroListing[14].equals("0")){ //has armor
            hasString = hasString + "/Armor";
            healthString = healthString + "/" + heroListing[14];
        }


        if(!heroListing[15].equals("0")){
            hasString = hasString + "/Shield";
            healthString = healthString + "/" + heroListing[15];
        }

        hasString = hasString + ": ";

        has.setText(hasString);
        health.setText(healthString);


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

        if(!heroListing[16].equals("0")){
            TextView lmb2label = (TextView)findViewById(R.id.lmb2label);
            TextView lmb2 = (TextView)findViewById(R.id.lmb2Value);
            lmb2.setText(heroListing[16]);
            lmb2.setVisibility(View.VISIBLE);
            lmb2label.setVisibility(View.VISIBLE);
        }


        Button button = (Button)findViewById(R.id.editButton);
        button.setOnClickListener(new View.OnClickListener(){
           public void onClick(View v){
               //Toast.makeText(v.getContext(), "The button is active", Toast.LENGTH_SHORT).show();
               Intent editCommence = new Intent(HeroPage.this, HeroEdit.class);
               editCommence.putExtra("editThisHero", heroListing);
               startActivity(editCommence);
           }
        });
    }


}
