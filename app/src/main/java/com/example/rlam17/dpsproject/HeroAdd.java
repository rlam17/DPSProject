package com.example.rlam17.dpsproject;

import android.app.Activity;
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

import java.util.concurrent.ExecutionException;

/**
 * Created by rlam17 on 12/3/2016.
 */

public class HeroAdd extends AppCompatActivity {

    private class AddToCSV extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            System.out.println("Hold on to your buttcheeks, we're going in!");
        }

        @Override
        protected String doInBackground(String... in){


            //ArrayList<String[]> temp = new ArrayList<>(); //For return


            JSch jsch = new JSch();
            Session session;
            try{



                String s = in[0];

                String command = "echo " + "\"" + s + "\"" + " >> herodata.csv";



                //Black magic
                session = jsch.getSession("rlam17", "matrix.senecac.on.ca", 22);
                session.setConfig("StrictHostKeyChecking", "no");
                session.setPassword("Ray998051575@");
                session.connect();

                Channel channel = session.openChannel("sftp");
                channel.connect();
                ChannelSftp sftpChannel = (ChannelSftp) channel;

                ChannelExec channelExec = (ChannelExec)session.openChannel("exec");

                channelExec.setCommand(command);
                channelExec.connect();



                sftpChannel.exit();
                channelExec.disconnect();
                session.disconnect();
            }catch(Exception e){
                System.err.println("Something went wrong with adding");
                System.err.println(e);
            }

            return "Added!";
        }
    }



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

                try {
                    String response = new AddToCSV().execute(line).get();
                    System.out.println(response);
                } catch (InterruptedException y) {
                    y.printStackTrace();
                } catch (ExecutionException y) {
                    y.printStackTrace();
                }

                Intent returnIntent = new Intent();
                setResult(Activity.RESULT_OK ,returnIntent);
                finish();
            } else {
                //TODO: Something...
            }
        }
    }


}
