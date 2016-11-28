package com.example.rlam17.dpsproject;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;



import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by rlam17 on 11/14/2016.
 *
 * Uses opencsv module to read csv
 * Uses apache commons for FTPclient
 */


public class HeroPage extends AppCompatActivity {

    private class DownloadFromMatrix extends AsyncTask<Void, String, ArrayList<String[]>>{
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            System.out.println("Hold on to your nutsack, we're going in!");
        }

        @Override
        protected ArrayList<String[]> doInBackground(Void... f_url){


            ArrayList<String[]> temp = new ArrayList<>();


            JSch jsch = new JSch();
            Session session;
            try{
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


    public List<Integer> intTotalStats = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hero_page);


        //grab entire spreadsheet

        Intent intent = getIntent();
        int heroSlot = intent.getIntExtra("heroChosen",0);



        ArrayList<String[]> heroDirectory = null;
        try {
            heroDirectory = new DownloadFromMatrix().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }




        String[] heroListing = heroDirectory.get(heroSlot);

        for(int i = 0; i< heroListing.length; i++)
        System.out.println(heroListing[i]);


        //FTPClient ftpClient = new FTPClient();


        // Table colums are:
        //Heroname lmb1	lmb1pc	lmb1ps	rmb	rmbpc	rmbps	shift	shiftps	e	epc	q	qps	health	armor	shield	lmb2	lmb2pc	lmb2ps







    }

}
