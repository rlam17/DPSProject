package com.example.rlam17.dpsproject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 *
 * Created by rlam17 on 11/29/2016.
 *
 * Uses jsch for writing
 *
 */

public class HeroEdit extends AppCompatActivity {

    private class WriteToCSV extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            System.out.println("Hold on to your nutsack, we're going in!");
        }

        @Override
        protected String doInBackground(String... in){


            //ArrayList<String[]> temp = new ArrayList<>(); //For return


            JSch jsch = new JSch();
            Session session;
            try{



                String s = in[0];



                //Black magic
                session = jsch.getSession("rlam17", "matrix.senecac.on.ca", 22);
                session.setConfig("StrictHostKeyChecking", "no");
                session.setPassword("Ray998051575@");
                session.connect();

                Channel channel = session.openChannel("sftp");
                channel.connect();
                ChannelSftp sftpChannel = (ChannelSftp) channel;

                ChannelExec channelExec = (ChannelExec)session.openChannel("exec");

                channelExec.setCommand(s);
                channelExec.connect();



                sftpChannel.exit();
                channelExec.disconnect();
                session.disconnect();
            }catch(Exception e){
                System.err.println("Something went wrong with writing");
                System.err.println(e);
            }

            return "Finished!";
        }
    }





    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hero_edit);

        Intent intent = getIntent();
        final String[] heroListing = intent.getStringArrayExtra("editThisHero");
        final String[] heroInput = new String[heroListing.length];

        for(int k = 0; k < heroInput.length; k++){
            heroInput[k] = heroListing[k];
        }

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
                    String input = heroInput[0];
                    heroInput[13] = heroHealth.getText().toString();
                    heroInput[14] = heroArmor.getText().toString();
                    heroInput[15] = heroShield.getText().toString();
                    heroInput[1] = heroLMB1.getText().toString();
                    heroInput[16] = heroLMB2.getText().toString();
                    heroInput[4] = heroRMB.getText().toString();
                    heroInput[7] = heroShift.getText().toString();
                    heroInput[9] = heroE.getText().toString();
                    heroInput[11] = heroQ.getText().toString();

                    for(int i = 1; i < heroInput.length;i++){
                        input = input + "," + heroInput[i];
                    }
                    String input2 = heroListing[0];
                    for(int j = 1; j < heroListing.length;j++){
                        input2 = input2 + "," + heroListing[j];
                    }


                    //System.out.println(input);

                    String command;
                    command = "sed -i -e 's/" + input2 + "/" + input + "/g' herodata.csv";

                    try {
                        String response = new WriteToCSV().execute(command).get();
                        System.out.println(response);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

}
