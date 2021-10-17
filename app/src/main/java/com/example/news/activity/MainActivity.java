package com.example.news.activity;

//TODO: * Add a progress bar
//      * Show "No Internet Connection" message when applicable

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import android.app.AlertDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.news.R;
import com.example.news.adapters.NewsPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class MainActivity extends AppCompatActivity{
	Toolbar toolbar;
	TabLayout tabLayout;
	ViewPager2 viewPager2;
	NewsPagerAdapter newsPagerAdapter;
	TabLayoutMediator tabLayoutMediator;
	String[] tabTitles = new String[]{"HOME","BUSINESS","HEALTH","TECHNOLOGY","SPORTS"};


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (!isOnline()) {
			try {
				new AlertDialog.Builder(MainActivity.this)
						.setTitle("Error")
						.setMessage("Internet not available, Please check your internet connectivity and try again later...")
						.setCancelable(false)
						.setIcon(android.R.drawable.ic_dialog_alert)
						.setNeutralButton("OK", (dialog, which) -> finish()).show();
			} catch (Exception e) {
				Log.d("Main Activity", "Show Dialog: " + e.getMessage());
			}
		}


	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		tabLayoutMediator.detach();
		viewPager2.setAdapter(null);
	}

	public boolean isOnline() {
		ConnectivityManager conMgr = (ConnectivityManager) getApplicationContext()
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

		if(netInfo == null || !netInfo.isConnected() || !netInfo.isAvailable()){
			return false;
		}
		return true;
	}

	public void homeClicked(View view) {
		Toast.makeText(this, "You have clicked Home Category", Toast.LENGTH_LONG).show();
	}

	public void techClicked(View view) {
		Toast.makeText(this, "You have clicked Technology Category", Toast.LENGTH_LONG).show();
	}

	public void internationalClicked(View view) {
		Toast.makeText(this, "You have clicked International Category", Toast.LENGTH_LONG).show();
	}

	public void sportsClicked(View view) {
		Toast.makeText(this, "You have clicked Sports Category", Toast.LENGTH_LONG).show();
	}

	public void healthClicked(View view) {
		Toast.makeText(this, "You have clicked Health Category", Toast.LENGTH_LONG).show();
	}

	public void businessClicked(View view) {
		Toast.makeText(this, "You have clicked Business Category", Toast.LENGTH_LONG).show();
	}
}