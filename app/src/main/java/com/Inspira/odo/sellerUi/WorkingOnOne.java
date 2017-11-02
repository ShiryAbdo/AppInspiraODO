package com.Inspira.odo.sellerUi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.Inspira.odo.R;

import java.util.ArrayList;

public class WorkingOnOne extends AppCompatActivity {
    ArrayList<String> categories;
    Spinner SpinnerOne ,SpinnerTwo ,SpinnerThree  ,SpinnerCarFoure;
    ImageView competRegister ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_working_on_one);


        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
//        setTitle(Html.fromHtml("<small>Working on</small>"));
//        getSupportActionBar().setDisplayUseLogoEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
        SpinnerOne = (Spinner)findViewById(R.id.SpinnerOne);
        SpinnerTwo=(Spinner)findViewById(R.id.SpinnerTwo);
        SpinnerThree =(Spinner)findViewById(R.id.SpinnerThree);
        SpinnerCarFoure =(Spinner)findViewById(R.id.SpinnerCarFoure);
        competRegister=(ImageView)findViewById(R.id.competRegister);
        competRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WorkingOnOne.this,NavigationDrawerSeler.class);
                startActivity(intent);
                finish();

            }
        });


        // Spinner Drop down elements
        categories= new ArrayList<>();
        categories.add("Automobile");
        categories.add("Business Services");
        categories.add("Computers");
        categories.add("Education");
        categories.add("Personal");
        categories.add("Travel");


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinnerOne.setAdapter(dataAdapter);
        SpinnerTwo.setAdapter(dataAdapter);
        SpinnerThree.setAdapter(dataAdapter);
        SpinnerCarFoure.setAdapter(dataAdapter);



    }
}
