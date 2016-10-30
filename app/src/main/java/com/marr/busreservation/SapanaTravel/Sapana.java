package com.marr.busreservation.SapanaTravel;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.TabHost;

import com.marr.busreservation.R;
import com.marr.busreservation.fragment.FragmentPageAdapter;

import java.util.ArrayList;
import java.util.List;

public class Sapana extends AppCompatActivity implements ViewPager.OnPageChangeListener,TabHost.OnTabChangeListener {
ViewPager viewPager;
    TabHost tabhost;
    FragmentPagerAdapter ViewPagerAdapter;
    int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sapana);
        Sapana.this.setTitle("Sapana Travel");
        initViewPager();
        initTabHost();
    }
    private void initTabHost() {
        tabhost=(TabHost)findViewById(R.id.tabHost);
        tabhost.setup();
        String[] tabnames={
            "Birgunj A/C","Birgunj Non-A/C","Kathmandu A/C","Kathmandu   Non-A/C","Information "
        };
        for(int i=0;i<tabnames.length;i++){
            TabHost.TabSpec tabspec;
            tabspec=tabhost.newTabSpec(tabnames[i]);
            tabspec.setIndicator(tabnames[i]);
            tabspec.setContent(new FakeContent(getApplicationContext()));
            tabhost.addTab(tabspec);

        }
        tabhost.setOnTabChangedListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int SelectedItem) {
        tabhost.setCurrentTab(SelectedItem);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onTabChanged(String tabId) {
        int SelectedItem=tabhost.getCurrentTab();
        viewPager.setCurrentItem(SelectedItem);
        HorizontalScrollView scrollView=(HorizontalScrollView)findViewById(R.id.h_scrollview);
        View tabView=tabhost.getCurrentTabView();
        int scrollPos=tabView.getLeft()-(scrollView.getWidth()-tabView.getWidth())/2;
        scrollView.smoothScrollTo(scrollPos,0);
    }

    public class FakeContent implements TabHost.TabContentFactory{
    Context context;
        public FakeContent(Context mcontext){
            context=mcontext;
        }

        @Override
        public View createTabContent(String tag) {
            View fakeview=new View(context);
            fakeview.setMinimumHeight(0);
            fakeview.setMinimumWidth(0);
            return fakeview;
        }
    }

    private void initViewPager() {
        viewPager=(ViewPager) findViewById(R.id.view_pager);
        List<Fragment> fragmentList=new ArrayList<Fragment>();
        fragmentList.add(new Brj_AC());
        fragmentList.add(new Brj_NonAC());
        fragmentList.add(new KTMA_C());
        fragmentList.add(new KTMNon_AC());
        fragmentList.add(new Information());
        FragmentPagerAdapter pagerAdapter=new FragmentPageAdapter(getSupportFragmentManager(),fragmentList);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOnPageChangeListener(this);
    }
}
