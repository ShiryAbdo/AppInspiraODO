package com.Inspira.odo.sellerUi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.Inspira.odo.R;

public class ContinuingRegSeler extends AppCompatActivity {
    Button Campany_acount_Countio ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_continuing_reg_seler);
        Campany_acount_Countio = (Button)findViewById(R.id.Campany_acount_Countio);
        Campany_acount_Countio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContinuingRegSeler.this,WorkingOnOne.class);
                startActivity(intent);
            }
        });

    }
}
