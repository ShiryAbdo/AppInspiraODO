package com.Inspira.odo.buyerUi;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.Inspira.odo.R;

public class NavigationActivity extends AppCompatActivity {

    ListView mDrawerList = null;
    DrawerLayout mDrawerLayout = null;
    ActionBarDrawerToggle mDrawerToggle = null;
    String mTitle = "";
    String mDrawerTitle = "";
    String[] data_list = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayUseLogoEnabled(true);
 
         init();

    }

    private void init() {
        // Initialize Listview
        mDrawerList = (ListView) findViewById(R.id.list);
        // Initialize drawer layout
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        // creating color string array for our drawer listview
        data_list = new String[] { "Red", "Yellow", "Blue", "Gray", "Black",
                "White" };

        // creating adatper for listview
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, data_list);

        // providing adapter to listview
        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        // Initializing title
        mTitle = getResources().getString(R.string.app_name);
        mDrawerTitle = getResources().getString(R.string.drawer_open);

        actionbarToggleHandler();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressLint("NewApi")
    private void actionbarToggleHandler() {

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.menu, R.string.drawer_open,
                R.string.drawer_close) {

            @Override
            public void onDrawerClosed(View drawerView) {
                getSupportActionBar().setTitle(mTitle);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(mDrawerTitle);
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    class DrawerItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView adapter, View view, int position,
                                long id) {
            FrameLayout frameLayout = (FrameLayout) findViewById(R.id.frame_container);
            frameLayout.setBackgroundColor(Color
                    .parseColor(data_list[position]));
            mDrawerLayout.closeDrawers();
        }
    }


}
