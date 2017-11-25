package com.Inspira.odo.sellerUi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.Inspira.odo.R;

public class RespondtoaReques extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_respondtoa_reques);
        setTitle(R.string.Respond_to_aReques);
    }
    @Override
    protected void onResume() {
        super.onResume();
       setTitle(R.string.Respond_to_aReques);
    }
}
