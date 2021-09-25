package com.example.news.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.news.fragments.BusinessFragment;
import com.example.news.fragments.HealthFragment;
import com.example.news.fragments.HomeFragment;
import com.example.news.fragments.SportsFragment;
import com.example.news.fragments.TechnologyFragment;

public class NewsPagerAdapter extends FragmentStateAdapter {

    public NewsPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position)
        {
            case 0: return new HomeFragment();
            case 1: return new BusinessFragment();
            case 2: return new HealthFragment();
            case 3: return new TechnologyFragment();
            case 4: return new SportsFragment();
            default: return null;
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
