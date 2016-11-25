package com.example.rlam17.dpsproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private AdapterView.OnItemClickListener heroClickHandler = new AdapterView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            //get data from the view that was clicked
            //String data = parent.getItemAtPosition(position).toString();
            //show a toast for now.. or do whatever!
            //Toast.makeText(view.getContext(), data, Toast.LENGTH_SHORT).show();
            Intent hero_selected = new Intent(MainActivity.this, HeroPage.class);
            hero_selected.putExtra("heroChosen", position);
            startActivity(hero_selected);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] heroesTemp = getResources().getStringArray(R.array.heroesTemp);
        ListView tempHeroes = (ListView)findViewById(R.id.heroSelect);
        ArrayAdapter<String> heroList = new ArrayAdapter<String>(this, R.layout.hero_slot, heroesTemp);

        tempHeroes.setAdapter(heroList);
        tempHeroes.setOnItemClickListener(heroClickHandler);

    }



}
