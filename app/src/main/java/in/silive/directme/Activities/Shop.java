package in.silive.directme.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import silive.in.sampledirectme.Fragments.Ship1;
import silive.in.sampledirectme.Fragments.Ship2;
import silive.in.sampledirectme.Fragments.Ship3;
import silive.in.sampledirectme.R;

/**
 * Created by akriti on 16/5/16.
 */
public class Shop extends AppCompatActivity {
    PagerAdapter shopViewPager;
    Toolbar toolbar;
    ViewPager viewPager;
    TabLayout tabs;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        viewPager=(ViewPager)findViewById(R.id.viewPager);
        setupViewPager(viewPager);
        tabs=(TabLayout)findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

    }
    public void setupViewPager(ViewPager viewPager){
        shopViewPager=new PagerAdapter(getSupportFragmentManager());
        shopViewPager.addFragment(new Ship1(),"One");
        shopViewPager.addFragment(new Ship2(),"Two");
        shopViewPager.addFragment(new Ship3(),"Three");
        viewPager.setAdapter(shopViewPager);
    }

    class PagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> fragmentList=new ArrayList<>();
        private final List<String> title_list= new ArrayList<>();
        public PagerAdapter(FragmentManager manager){
            super(manager);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
        public void addFragment(Fragment fragment, String title) {
            fragmentList.add(fragment);
            title_list.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return title_list.get(position);
        }
    }


}

