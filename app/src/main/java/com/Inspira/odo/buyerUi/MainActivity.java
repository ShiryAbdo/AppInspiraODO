package com.Inspira.odo.buyerUi;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.Inspira.odo.R;

public class MainActivity extends AppCompatActivity {

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

	@SuppressLint("NewApi")
	private void actionbarToggleHandler() {
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

	class DrawerItemClickListener implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView adapter, View view, int position,
				long id) {
			FrameLayout frameLayout = (FrameLayout) findViewById(R.id.frame_container);
			frameLayout.setBackgroundColor(Color
					.parseColor(data_list[position]));
			mDrawerLayout.closeDrawers();
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		return super.onContextItemSelected(item);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
	}
}
