package com.example.rlam17.dpsproject;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.opencsv.CSVReader;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
            ArrayList<String[]> temp= new ArrayList<>();
            try {

                File tmpdir = new File("/var/tmp");
                File file = new File(tmpdir,"hero.csv");

                FTPClient ftpClient = new FTPClient();
                ftpClient.connect("matrix.senecac.on.ca", 22);
                ftpClient.login("rlam17", "Ray998051575@");
                ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

                BufferedInputStream buffIn;
                buffIn = new BufferedInputStream(new FileInputStream(file));
                ftpClient.enterLocalPassiveMode();
                //ftpClient.storeFile("herodata.csv", buffIn);

                BufferedReader reader = new BufferedReader(new InputStreamReader(buffIn));

                reader.readLine();

                String line;

                try{
                    while ((line = reader.readLine()) != null) {
                        String[] tokens = line.split(",");
                        temp.add(tokens);
                    }

                } catch(IOException e){
                    System.err.println("Something went wrong with FTP");
                    System.err.println(e);
                }finally{
                    reader.close();
                }



                buffIn.close();
                ftpClient.logout();
                ftpClient.disconnect();
            } catch (IOException e) {
                System.out.println("Something went wrong with FTP");
                e.printStackTrace();
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
        System.out.println("I am here");
        System.out.println(heroSlot);

        AssetManager assetManager = HeroPage.this.getAssets();


        ArrayList<String[]> heroDirectory = null;
        try {
            heroDirectory = new DownloadFromMatrix().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        /*
        try {
            InputStream csvStream = assetManager.open("/var/tmp/hero.csv");
            InputStreamReader csvStreamReader = new InputStreamReader(csvStream);
            CSVReader csvReader = new CSVReader(csvStreamReader);
            String[] line;

            // throw away the header
            csvReader.readNext();

            while ((line = csvReader.readNext()) != null) {
                heroDirectory.add(line);
            }
        } catch (IOException e) {
            System.out.println("Something went wrong with CSV reading");
            e.printStackTrace();
        }
        */
        System.out.println(heroDirectory.get(heroSlot)[0]);


        //FTPClient ftpClient = new FTPClient();


        // Table colums are:
        //Heroname lmb1	lmb1pc	lmb1ps	rmb	rmbpc	rmbps	shift	shiftps	e	epc	q	qps	health	armor	shield	lmb2	lmb2pc	lmb2ps





        /*
        int intLCTotal, intRCTotal, intShiftTotal, intETotal, intQTotal, intL2Total, intHealth, intArmor, intShield, intLC2Total;
        //grab left click numbers
        int intLeftBase = genji[0];
        int intLeftPerClick = genji[1];
        intLCTotal = intLeftBase*intLeftPerClick;
        int intLeftPerSecond = genji[2];

        //grab right click totals
        int intRightBase = genji[3];
        int intRightPerClick = genji[4];
        intRCTotal = intRightBase*intRightPerClick;
        int intRightPerSecond = genji[5];

        //grab shift total
        int intShiftBase = genji[6];
        int intShiftSecond = genji[7];
        intShiftTotal = intShiftBase;

        //grab e totals
        int intEBase = genji[8];
        int intETicks = genji[9];
        intETotal = intEBase*intETicks;

        //grab q totals
        int intQBase = genji[10];
        int intQPerSecond = genji[11];
        intQTotal = intQBase*intQPerSecond;

        //grab health
        intHealth = genji[12];

        //grab armor
        intArmor = genji[13];

        //grab shield
        intShield = genji[14];

        //grab left click 2 (will not be displayed if 0)
        int intLC2Base = genji[15];
        int intLC2PerClick = genji[16];
        intLC2Total = intLC2Base*intLC2PerClick;
        int intLC2PerSecond = genji[17];
        //spreadsheet parsed
        */

    }

}
