package com.example.news.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.news.R;
import com.example.news.fragments.BusinessFragment;
import com.example.news.fragments.EntertainmentFragment;
import com.example.news.fragments.HealthFragment;
import com.example.news.fragments.HomeFragment;
import com.example.news.fragments.SportsFragment;
import com.example.news.fragments.TechnologyFragment;

public class NewsActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news);
		Fragment fragment;
		Bundle bundle = getIntent().getExtras();
		char type = bundle.getChar("type");

		switch (type){
			case 'H' : fragment = new HealthFragment();
						break;
			case 'T' : fragment = new TechnologyFragment();
						break;
			case 'S' : fragment = new SportsFragment();
						break;
			case 'B' : fragment = new BusinessFragment();
						break;
			case 'E' : fragment = new EntertainmentFragment();
						break;
			default: fragment = new HomeFragment();
		}


		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.add(R.id.frag, fragment);
		transaction.commit();
	}
}