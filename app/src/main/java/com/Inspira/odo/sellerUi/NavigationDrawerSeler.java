package com.Inspira.odo.sellerUi;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.Inspira.odo.R;
import com.Inspira.odo.buyerUi.HomeBuper;
import com.Inspira.odo.buyerUi.MaKArequest;
import com.Inspira.odo.buyerUi.MyRequest;
import com.Inspira.odo.buyerUi.NavigationDrawerBuyer;
import com.Inspira.odo.helper.NavigationDrawerHelper;
import com.Inspira.odo.mainLuncher.ChangeLanguage;
import com.Inspira.odo.mainLuncher.EditProfile;
import com.Inspira.odo.mainLuncher.LogInActivity;
import com.Inspira.odo.model.ObjectDrawerItem;

import java.util.ArrayList;

public class NavigationDrawerSeler extends AppCompatActivity  implements ListView.OnItemClickListener{

    private NavigationDrawerHelper mNavigationDrawerHelper;

    private Fragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer_seler);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Define  and initialize our NavigationDrawerHelper Class Constant
        // The First parameters is the Activity (this)
        // The Second is the ListView.OnItemClickListener (this), as our Activity implements it

        ArrayList<ObjectDrawerItem> drawerItem = new ArrayList<>();
        drawerItem.add(new ObjectDrawerItem(0, "Order Requestes"));
        drawerItem.add(new ObjectDrawerItem(1, "My Favorites"));
        drawerItem.add(new ObjectDrawerItem(2, ""));
        drawerItem.add(new ObjectDrawerItem(3, "Edit Profile"));
        drawerItem.add(new ObjectDrawerItem(4, "Change Language"));
        drawerItem.add(new ObjectDrawerItem(5, "Log Out"));
        mNavigationDrawerHelper = new NavigationDrawerHelper();
        mNavigationDrawerHelper.init(this,toolbar ,this,drawerItem);

        // We define our Fragment Constant
        mFragment = new SellerHome();

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
            case 1:
                mFragment = new OrderRequestes();
                break;
            case 2:
                mFragment = new MyFavorites();
                break;
             case 3:
                break;
            case 4:
                mFragment = new EditProfile();
                break;
            case  5 :
                mFragment = new ChangeLanguage();
                break;
            case  6:
                Intent intent = new Intent(NavigationDrawerSeler.this, LogInActivity.class);
                startActivity(intent);
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (Integer.parseInt(android.os.Build.VERSION.SDK) > 5
                && keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
             onBackPressed();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void onBackPressed() {
        Intent setIntent = new Intent(Intent.ACTION_MAIN);
        setIntent.addCategory(Intent.CATEGORY_HOME);
        setIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(setIntent);
    }

}
