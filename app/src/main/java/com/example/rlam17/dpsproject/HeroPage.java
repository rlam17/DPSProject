package com.example.rlam17.dpsproject;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.opencsv.CSVReader;

import org.apache.commons.net.ftp.FTPClient;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rlam17 on 11/14/2016.
 * Uses opencsv module
 */

public class HeroPage extends AppCompatActivity {
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
        List<String[]> heroDirectory = new ArrayList<>();
        AssetManager assetManager = HeroPage.this.getAssets();

        try {
            InputStream csvStream = assetManager.open("herodata.csv");
            InputStreamReader csvStreamReader = new InputStreamReader(csvStream);
            CSVReader csvReader = new CSVReader(csvStreamReader);
            String[] line;

            // throw away the header
            csvReader.readNext();

            while ((line = csvReader.readNext()) != null) {
                heroDirectory.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(heroDirectory.get(heroSlot)[0]);


        FTPClient ftpClient = new FTPClient();


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
