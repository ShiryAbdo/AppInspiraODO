package com.Inspira.odo.helper;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.Inspira.odo.R;
import com.Inspira.odo.adaptors.DrawerItemCustomAdapter;
import com.Inspira.odo.model.ObjectDrawerItem;

import java.util.ArrayList;


/**
 * Created by Andy on 10-Dec-14.
 */
public class NavigationDrawerHelper {

    DrawerLayout mDrawerLayout;
    ListView mDrawerListView;
    private android.support.v7.app.ActionBarDrawerToggle mDrawerToggle;
    ArrayList<ObjectDrawerItem> drawerItem = new ArrayList<>();

//    app:headerLayout="@layout/nav_header_main2"

    // Method to initialize our NavigationDrawer from the hosting Activity.
    // We pass the Activity and the OnItemClickListener
    public void init(Activity activity, Toolbar tool, ListView.OnItemClickListener listener ,ArrayList<ObjectDrawerItem> drawerItemCaming ) {

        mDrawerLayout = (DrawerLayout) activity.findViewById(R.id.drawer_layout);
        mDrawerListView = (ListView) activity.findViewById(R.id.left_drawer);
         View header = (View)activity.getLayoutInflater().inflate(R.layout.nav_header_main2,null);
        mDrawerListView.addHeaderView(header);

        // List the Drawer Items


        this.drawerItem=drawerItemCaming;



        // Declare a new instance of our Custom drawer Adapter
        DrawerItemCustomAdapter adapter = new DrawerItemCustomAdapter( activity,activity, R.layout.listview_drawer_item_row, drawerItem);

        // Set the Adapter and the Listener on the ListView
        mDrawerListView.setAdapter(adapter);
        mDrawerListView.setOnItemClickListener(listener);

        // Set shadow and the default item selected in the ListView to be the first one
        mDrawerLayout.setDrawerShadow(R.drawable.menu, GravityCompat.START);
          mDrawerListView.setItemChecked(0,true);


 //        mDrawerLayout.addView();

        // Call the next method

//        // We define our constan   DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        android.support.v7.app.ActionBarDrawerToggle toggle = new android.support.v7.app.ActionBarDrawerToggle(
//                activity, mDrawerLayout, tool, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        mDrawerLayout.setDrawerListener(toggle);
//        toggle.syncState();
        setupActionBar(activity,tool);
    }




    // This method to setup the ActionBar and enable the toggle
    // (opening/closing) of the drawer when the User taps the Menu Icon.
    private void setupActionBar(final Activity theActivity ,Toolbar tool) {
        final Activity activity = theActivity;

        ActionBar actionBar = theActivity.getActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(false);
//        MenuItem menuItem = navigationView.getMenu().getItem(0);
//        menuItem.setIcon(R.drawable.nav_new_drawable);

        mDrawerToggle = new android.support.v7.app.ActionBarDrawerToggle(
                activity, mDrawerLayout, tool, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        {
            @Override
            public void onDrawerOpened(View drawerView) {
                activity.invalidateOptionsMenu();
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                activity.invalidateOptionsMenu();
                super.onDrawerClosed(drawerView);
            }
        };

    }

    // To highlight at selection, the list item selected and close the Drawer
    // Will be called in the OnItemClick() method of the hosting Activity
    public void handleSelect(int option) {
        mDrawerListView.setItemChecked(option,true);
        mDrawerLayout.closeDrawer(mDrawerListView);

    }

    // It checks if the Drawer is Open and it loops through the Menu option and disables all of them.
    // If the Drawer is closed, it enables all of them
    public void handleOnPrepareOptionMenu(Menu menu) {
        boolean itemVisible = !mDrawerLayout.isDrawerOpen(mDrawerListView);
        for (int index = 0; index < menu.size(); index++) {
            MenuItem item = menu.getItem(index);
            item.setEnabled(itemVisible);
        }

    }

    // To delegate the Open/Close when the User taps the menu icon
    public void handleOnOptionsItemSelected(MenuItem menuItem) {
        mDrawerToggle.onOptionsItemSelected(menuItem);

    }

    // Tells the Drawer Toggle to check everything is checked and layed out the way it should be after configuration change
    public void syncState() {
        mDrawerToggle.syncState();
    }

    public void setSelection(int option) {
        mDrawerListView.setItemChecked(option,true);
    }

}
