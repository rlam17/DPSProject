package com.example.rlam17.dpsproject;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.jcraft.jsch.Channel;
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
 * Created by rlam17 on 11/14/2016.
 *
 * Uses jsch for FTPclient
 */

public class MainActivity extends AppCompatActivity {
    static public ArrayList<String[]> heroDirectory = null;
    private class DownloadFromMatrix extends AsyncTask<Void, String, ArrayList<String[]>> {
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            System.out.println("Hold on to your nutsack, we're going in!");
        }

        @Override
        protected ArrayList<String[]> doInBackground(Void... f_url){


            ArrayList<String[]> temp = new ArrayList<>(); //For return


            JSch jsch = new JSch();
            Session session;
            try{
                //Black magic
                session = jsch.getSession("rlam17", "matrix.senecac.on.ca", 22);
                session.setConfig("StrictHostKeyChecking", "no");
                session.setPassword("Ray998051575@");
                session.connect();

                Channel channel = session.openChannel("sftp");
                channel.connect();
                ChannelSftp sftpChannel = (ChannelSftp) channel;
                InputStream is = sftpChannel.get("herodata.csv");

                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                reader.readLine();
                String line;

                try{
                    while ((line = reader.readLine()) != null) {
                        String[] tokens = line.split(",");
                        temp.add(tokens);
                    }

                } catch(IOException e){
                    System.err.println("Something went wrong with reading file");
                    System.err.println(e);
                }finally{
                    reader.close();
                }

                is.close();

                sftpChannel.exit();
                session.disconnect();
            }catch(Exception e){
                System.err.println("Something went wrong with FTP");
                System.err.println(e);
            }

            return temp;
        }
    }
    private AdapterView.OnItemClickListener heroClickHandler = new AdapterView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            //get data from the view that was clicked
            //String data = parent.getItemAtPosition(position).toString();
            //show a toast for now.. or do whatever!
            //Toast.makeText(view.getContext(), data, Toast.LENGTH_SHORT).show();

            String[] heroListing = heroDirectory.get(position);

            Intent hero_selected = new Intent(MainActivity.this, HeroPage.class);
            hero_selected.putExtra("heroChosen", heroListing);
            startActivity(hero_selected);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //populateList();
        Button button = (Button)findViewById(R.id.addButton);
        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent addingHero = new Intent(MainActivity.this, HeroAdd.class);

                startActivityForResult(addingHero, 2);
            }
        });

    }

    void populateList(){
        try {
            heroDirectory = new DownloadFromMatrix().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        String[] heroNames = new String[heroDirectory.size()];
        for(int p = 0;p<heroNames.length;p++){
            heroNames[p] = heroDirectory.get(p)[0];
        }


        //String[] heroesTemp = getResources().getStringArray(R.array.heroesTemp);
        ListView tempHeroes = (ListView)findViewById(R.id.heroSelect);

        ArrayAdapter<String> heroList = new ArrayAdapter<>(this, R.layout.hero_slot, heroNames);

        tempHeroes.setAdapter(heroList);
        tempHeroes.setOnItemClickListener(heroClickHandler);
    }

    @Override
    protected void onResume(){
        super.onResume();
        ListView tempHeroes = (ListView)findViewById(R.id.heroSelect);
        tempHeroes.setAdapter(null);
        populateList();
    }

}
