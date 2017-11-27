package com.Inspira.odo.buyerUi;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
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

import com.Inspira.odo.database.SharedPreferencesManager;
import com.Inspira.odo.helper.NavigationDrawerHelper;
import com.Inspira.odo.R;
import com.Inspira.odo.mainLuncher.ChangeLanguage;
import com.Inspira.odo.mainLuncher.EditProfile;
import com.Inspira.odo.helper.LocaleHelper;
import com.Inspira.odo.mainLuncher.LogInActivity;
import com.Inspira.odo.model.ObjectDrawerItem;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;

import java.util.ArrayList;


public class NavigationDrawerBuyer extends AppCompatActivity implements ListView.OnItemClickListener {

private NavigationDrawerHelper mNavigationDrawerHelper;

private Fragment mFragment;
    SharedPreferencesManager sharedPreferencesManager ;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase));
    }

@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_main);
    FacebookSdk.sdkInitialize(getApplicationContext());
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    sharedPreferencesManager= new SharedPreferencesManager(this);

        // Define  and initialize our NavigationDrawerHelper Class Constant
        // The First parameters is the Activity (this)
        // The Second is the ListView.OnItemClickListener (this), as our Activity implements it

    ArrayList<ObjectDrawerItem> drawerItem = new ArrayList<>();
    drawerItem.add(new ObjectDrawerItem(0, getString(R.string.MyRequest)));
    drawerItem.add(new ObjectDrawerItem(1, "     "+getString(R.string.SparepartsRequests)));
    drawerItem.add(new ObjectDrawerItem(2, "     "+getString(R.string.AccesoriesRequests)));
    drawerItem.add(new ObjectDrawerItem(3, "     "+getString(R.string.TyreBattereyRequests)));
    drawerItem.add(new ObjectDrawerItem(4, getString(R.string.MaK_Arequest)));
    drawerItem.add(new ObjectDrawerItem(5, getString(R.string.My_Favorites)));
    drawerItem.add(new ObjectDrawerItem(6, getString(R.string.Edit_Profile)));
    drawerItem.add(new ObjectDrawerItem(7, getString(R.string.Change_Language)));
    drawerItem.add(new ObjectDrawerItem(8, getString(R.string.Loge_Out)));
        mNavigationDrawerHelper = new NavigationDrawerHelper();
        mNavigationDrawerHelper.init(this,toolbar ,this,drawerItem);

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
            case 1:
                 break;
            case 2:
                mFragment = new SparepartsRequests();
                break;
            case 3:
                mFragment = new AccesoriesRequests();
                 break;
            case 4:
                mFragment = new TyreBattereyRequests();
                break;
            case  5 :
                mFragment = new MaKArequest();
                break;
            case  6:
                mFragment = new MyFavourites();
                break;
            case  7:
                mFragment = new EditProfile();
                break;
            case  8:
                mFragment = new ChangeLanguage();
                break;
            case  9:
//                boolean check =  sharedPreferencesManager.isCheckFacebookLogin();
//                if(check){
                    LoginManager.getInstance().logOut();
                    Intent intent = new Intent(NavigationDrawerBuyer.this, LogInActivity.class);
                    sharedPreferencesManager.clearShared();
                    startActivity(intent);
//                }else {
////                    Intent intent = new Intent(NavigationDrawerBuyer.this, LogInActivity.class);
////                    sharedPreferencesManager.clearShared();
////                    startActivity(intent);
//                }



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
