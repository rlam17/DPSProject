package com.example.rlam17.dpsproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by rlam17 on 12/3/2016.
 */

public class HeroAdd extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hero_add);

        setTitle("Add Hero");

        Button button = (Button)findViewById(R.id.submitButton);
        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                processForm();
            }
        });
    }

    void processForm(){
        String[] heroDetails = new String[19];

        EditText heroName = (EditText)findViewById(R.id.addName);
        if(heroName.getText().toString() == null || (heroName.getText().toString().isEmpty())){
            Toast.makeText(getApplicationContext(), "You need to enter a name!", Toast.LENGTH_LONG).show();
        } else {
            boolean clean = true;
            heroDetails[0] = heroName.getText().toString();

            EditText healthIn = (EditText)findViewById(R.id.addHealth);
            String health = healthIn.getText().toString();

            EditText armorIn = (EditText)findViewById(R.id.addArmor);
            String armor = armorIn.getText().toString();

            EditText shieldIn = (EditText)findViewById(R.id.addShield);
            String shield = shieldIn.getText().toString();

            EditText lmb1In = (EditText)findViewById(R.id.lmbAdd);
            String lmb1 = lmb1In.getText().toString();

            EditText lmb2In = (EditText)findViewById(R.id.lmb2Add);
            String lmb2 = lmb2In.getText().toString();

            EditText rmbIn = (EditText)findViewById(R.id.rmbAdd);
            String rmb = rmbIn.getText().toString();

            EditText eIn = (EditText)findViewById(R.id.eAdd);
            String e = eIn.getText().toString();

            EditText shiftIn = (EditText)findViewById(R.id.shiftAdd);
            String shift = shiftIn.getText().toString();

            EditText qIn = (EditText)findViewById(R.id.qAdd);
            String q = qIn.getText().toString();

            if(clean){
                // Table columns are:
                //Heroname lmb1	lmb1pc	lmb1ps	rmb	rmbpc	rmbps	shift	shiftps	e	epc	q	qps	health	armor	shield	lmb2	lmb2pc	lmb2ps

                heroDetails[13] = health;
                heroDetails[14] = armor;
                heroDetails[15] = shield;
                heroDetails[1] = lmb1;
                heroDetails[16] = lmb2;
                heroDetails[4] = rmb;
                heroDetails[7] = shift;
                heroDetails[9] = e;
                heroDetails[11] = q;

                String line = heroName.getText().toString();

                for(int z = 1; z < heroDetails.length;z++){
                    if(heroDetails[z] == null){
                        heroDetails[z] = "0";
                    }
                    line = line + "," +heroDetails[z];
                }
                System.out.println(line);
            }
        }
    }


}
