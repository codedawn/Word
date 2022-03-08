package com.codedawn.word.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.codedawn.word.R;
import com.codedawn.word.fragment.MineFragment;
import com.codedawn.word.fragment.ReviewFragment;
import com.codedawn.word.fragment.WordFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomeActivity extends BasicActivity {


    private int currentFragment = 0;


    private BottomNavigationView bottomNavigationView;


    private Fragment[] fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
        initFragment();
    }


    private void initFragment() {
        WordFragment wordFragment = new WordFragment();
        ReviewFragment reviewFragment = new ReviewFragment();
        MineFragment mineFragment = new MineFragment();
        fragments = new Fragment[]{wordFragment, reviewFragment, mineFragment};
        currentFragment = currentFragment % fragments.length;
        getSupportFragmentManager().beginTransaction().replace(R.id.linear_frag_container, fragments[currentFragment]).show(fragments[currentFragment]).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.bnavigation_word:
                        if (currentFragment != 0) {
                            switchFragment(currentFragment,0);
                            currentFragment = 0;
                        }
                        break;
                    case R.id.bnavigation_review:
                        if (currentFragment != 1) {
                            switchFragment(currentFragment,1);
                            currentFragment = 1;
                        }
                        break;
                    case R.id.bnavigation_me:
                        if (currentFragment != 2) {
                            switchFragment(currentFragment,2);
                            currentFragment = 2;
                        }
                        break;
                }
                return true;
            }
        });
    }

    /**
     *先隐藏当前fragment，然后显示下一个fragment
     */
    private void switchFragment(int currentFragment,int index) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.hide(fragments[currentFragment]);
        currentFragment = (index) % fragments.length;
        //没有被添加就先添加
        if (!fragments[currentFragment].isAdded()) {
            transaction.add(R.id.linear_frag_container, fragments[currentFragment]);
        }
        transaction.show(fragments[currentFragment]).commitAllowingStateLoss();
    }

    private void init() {
        bottomNavigationView = findViewById(R.id.bottom_nav);
    }



}