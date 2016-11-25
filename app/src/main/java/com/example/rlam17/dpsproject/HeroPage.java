package com.example.rlam17.dpsproject;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rlam17 on 11/14/2016.
 */

public class HeroPage extends AppCompatActivity {
    public List<Integer> intTotalStats = new ArrayList<Integer>();
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hero_page);
        //grab entire spreadsheet
        int[][] intSpreadsheet = new int[2][2];
        //assume int called intPostition passed through to activity

        //this can go in a function
        List<Integer> intSelected = new ArrayList<Integer>();
        for(int i = 0; i<intSpreadsheet.length; i++){
            //intSelected.add(intSpreadsheet[intPosition][i]);
        }



        // Table colums are:
        //lmb1	lmb1pc	lmb1ps	rmb	rmbpc	rmbps	shift	shiftps	e	epc	q	qps	health	armor	shield	lmb2	lmb2pc	lmb2ps


        int[] genji = {28,	3,	3,	28,	3,	4,	50,	1,	0,	0,	120,	1,	200,	0,	0,	0,	0,	0};
        

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


    }
}
