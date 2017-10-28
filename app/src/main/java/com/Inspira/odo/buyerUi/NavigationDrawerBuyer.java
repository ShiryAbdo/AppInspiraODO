package com.Inspira.odo.buyerUi;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.Inspira.odo.helper.NavigationDrawerHelper;
import com.Inspira.odo.R;


public class NavigationDrawerBuyer extends AppCompatActivity implements ListView.OnItemClickListener {

private NavigationDrawerHelper mNavigationDrawerHelper;

private Fragment mFragment;

@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

        // Define  and initialize our NavigationDrawerHelper Class Constant
        // The First parameters is the Activity (this)
        // The Second is the ListView.OnItemClickListener (this), as our Activity implements it
        mNavigationDrawerHelper = new NavigationDrawerHelper();
        mNavigationDrawerHelper.init(this,toolbar ,this);

        // We define our Fragment Constant
        mFragment = new HomeBuper();

        // Call to our custom method to attach/replace any Fragment.
        attachFragment();
        }




@Override
public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
        return true;
        }

        // Call to the Drawer Toggle to Open/Close the drawer when menu Icon is tapped
        mNavigationDrawerHelper.handleOnOptionsItemSelected(item);
        return super.onOptionsItemSelected(item);
        }


// This is where the navigation really happens.
// We create a switch, based on the position of the Item clicked,
// and simply change our Fragment Constant accordingly.

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        mNavigationDrawerHelper.handleSelect(position);


        switch (position) {
            case 0:
                 mFragment = new HomeBuper();
                break;
            case 1:
                mFragment = new MyRequest();
                break;
            case 2:
                mFragment = new MaKArequest();
                break;
            case 3:
                mFragment = new HomeBuper();
                break;
        }
        attachFragment();

    }

    // Our custom method to attach/replace Fragments
    private void attachFragment() {
        if (mFragment != null) {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment_container, mFragment).commit();

        } else {
            Log.e("NavigationDrawerBuyer", "Error in creating fragment");
        }
    }


    // Call for the NavigationDrawerHelper to finish up laying out the ActionBar
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mNavigationDrawerHelper.syncState();
    }

    // We delegate the call to the NavigationDrawerHelper
    // so the actionBar menu item become disabled/enabled depending
    // if the Drawer is opened or not
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        mNavigationDrawerHelper.handleOnPrepareOptionMenu(menu);
        return super.onPrepareOptionsMenu(menu);
    }

    // If any configuration change, the NavigationDrawerHelper
    // will be laying up the ActionBAr
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        mNavigationDrawerHelper.syncState();
        super.onConfigurationChanged(newConfig);
    }


}
