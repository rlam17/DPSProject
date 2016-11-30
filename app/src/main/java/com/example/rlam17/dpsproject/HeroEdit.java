package com.example.rlam17.dpsproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 *
 * Created by rlam17 on 11/29/2016.
 *
 * Uses jsch for writing
 *
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

        final EditText heroHealth = (EditText)findViewById(R.id.editHealth);
        heroHealth.setText(heroListing[13]);

        final EditText heroArmor = (EditText)findViewById(R.id.editArmor);
        heroArmor.setText(heroListing[14]);

        final EditText heroShield = (EditText)findViewById(R.id.editShield);
        heroShield.setText(heroListing[15]);

        final EditText heroLMB1 = (EditText)findViewById(R.id.lmbEdit);
        heroLMB1.setText(heroListing[1]);

        final EditText heroLMB2 = (EditText)findViewById(R.id.lmb2edit);
        heroLMB2.setText(heroListing[16]);

        final EditText heroRMB = (EditText)findViewById(R.id.rmbEdit);
        heroRMB.setText(heroListing[4]);

        final EditText heroShift = (EditText)findViewById(R.id.shiftEdit);
        heroShift.setText(heroListing[7]);

        final EditText heroE = (EditText)findViewById(R.id.eEdit);
        heroE.setText(heroListing[9]);

        final EditText heroQ = (EditText)findViewById(R.id.qEdit);
        heroQ.setText(heroListing[11]);

        Button button = (Button)findViewById(R.id.saveButton);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //Toast.makeText(v.getContext(), "The button is active", Toast.LENGTH_SHORT).show();
                boolean cleanInput = true;
                String regex = "\\d+";
                if(!heroHealth.getText().toString().matches(regex) ||
                        !heroArmor.getText().toString().matches(regex) ||
                        !heroShield.getText().toString().matches(regex) ||
                        !heroLMB1.getText().toString().matches(regex) ||
                        !heroLMB2.getText().toString().matches(regex) ||
                        !heroRMB.getText().toString().matches(regex) ||
                        !heroShift.getText().toString().matches(regex) ||
                        !heroE.getText().toString().matches(regex) ||
                        !heroQ.getText().toString().matches(regex)){
                    cleanInput = false;
                }

                if(!cleanInput){
                    Toast.makeText(v.getContext(), "Please use only numbers",
                            Toast.LENGTH_SHORT).show();
                } else {
                    // Table columns are:
                    //Heroname lmb1	lmb1pc	lmb1ps	rmb	rmbpc	rmbps	shift	shiftps	e	epc	q	qps	health	armor	shield	lmb2	lmb2pc	lmb2ps
                    String input = heroListing[0];
                    heroListing[13] = heroHealth.getText().toString();
                    heroListing[14] = heroArmor.getText().toString();
                    heroListing[15] = heroShield.getText().toString();
                    heroListing[1] = heroLMB1.getText().toString();
                    heroListing[16] = heroLMB2.getText().toString();
                    heroListing[4] = heroRMB.getText().toString();
                    heroListing[7] = heroShift.getText().toString();
                    heroListing[9] = heroE.getText().toString();
                    heroListing[11] = heroQ.getText().toString();

                    for(int i = 1; i < heroListing.length;i++){
                        input = input + "," + heroListing[i];
                    }
                    //System.out.println(input);
                }
            }
        });
    }

}
