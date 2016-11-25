package com.example.rlam17.dpsproject;

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

        /*


        int intLCTotal, intRCTotal, intShiftTotal, intETotal, intQTotal, intL2Total, intHealth, intArmor, intShield, intLC2Total;
        //grab left click numbers
        int intLeftBase = intSelected.get(0);
        int intLeftPerClick = intSelected.get(1);
        intLCTotal = intLeftBase*intLeftPerClick;
        int intLeftPerSecond = intSelected.get(2);

        //grab right click totals
        int intRightBase = intSelected.get(3);
        int intRightPerClick = intSelected.get(4);
        intRCTotal = intRightBase*intRightPerClick;
        int intRightPerSecond = intSelected.get(5);

        //grab shift total
        int intShiftBase = intSelected.get(6);
        int intShiftSecond = intSelected.get(7);
        intShiftTotal = intShiftBase;

        //grab e totals
        int intEBase = intSelected.get(8);
        int intETicks = intSelected.get(9);
        intETotal = intEBase*intETicks;

        //grab q totals
        int intQBase = intSelected.get(10);
        int intQPerSecond = intSelected.get(11);
        intQTotal = intQBase*intQPerSecond;

        //grab health
        intHealth = intSelected.get(12);

        //grab armor
        intArmor = intSelected.get(13);

        //grab shield
        intShield = intSelected.get(14);

        //grab left click 2 (will not be displayed if 0)
        int intLC2Base = intSelected.get(15);
        int intLC2PerClick = intSelected.get(16);
        intLC2Total = intLC2Base*intLC2PerClick;
        int intLC2PerSecond = intSelected.get(17);
        //spreadsheet parsed

        */
    }
}
