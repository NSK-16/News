package com.example.news.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.news.R;


public class MainActivity extends AppCompatActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public boolean notOnline() {
		ConnectivityManager conMgr = (ConnectivityManager) getApplicationContext()
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

		return netInfo == null || !netInfo.isConnected() || !netInfo.isAvailable();
	}

	public void homeClicked(View view) {
		if (notOnline()) {
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
		else{
			Intent intent = new Intent(MainActivity.this, NewsActivity.class);
			intent.putExtra("type",'D');
			startActivity(intent);
		}

	}

	public void techClicked(View view) {
		if (notOnline()) {
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
		else{
			Intent intent = new Intent(MainActivity.this, NewsActivity.class);
			intent.putExtra("type",'T');
			startActivity(intent);
		}
	}

	public void entertainmentClicked(View view) {
		if (notOnline()) {
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
		else{
			Intent intent = new Intent(MainActivity.this, NewsActivity.class);
			intent.putExtra("type",'E');
			startActivity(intent);
		}
	}

	public void sportsClicked(View view) {
		if (notOnline()) {
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
		else{
			Intent intent = new Intent(MainActivity.this, NewsActivity.class);
			intent.putExtra("type",'S');
			startActivity(intent);
		}
	}

	public void healthClicked(View view) {
		if (notOnline()) {
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
		else{
			Intent intent = new Intent(MainActivity.this, NewsActivity.class);
			intent.putExtra("type",'H');
		}
	}

	public void businessClicked(View view) {
		if (notOnline()) {
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
		else{
			Intent intent = new Intent(MainActivity.this, NewsActivity.class);
			intent.putExtra("type",'B');
			startActivity(intent);
		}
	}

	public void bookMarksClicked(View view) {
		if (notOnline()) {
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
		else{
			Intent intent = new Intent(MainActivity.this, NewsActivity.class);
			intent.putExtra("type",'A');
			startActivity(intent);
		}
	}
}