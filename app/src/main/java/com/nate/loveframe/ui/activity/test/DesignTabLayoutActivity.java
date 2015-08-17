package com.nate.loveframe.ui.activity.test;

import java.util.ArrayList;
import java.util.List;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import butterknife.Bind;

import com.nate.loveframe.R;
import com.nate.loveframe.ui.activity.base.BaseActivity;
import com.nate.loveframe.ui.fragment.FragmentB;
import com.nate.loveframe.ui.fragment.FragmentC;
import com.nate.loveframe.ui.fragment.FragmentD;

/**
 * Created by Nate on 2015/8/17.
 */
public class DesignTabLayoutActivity extends BaseActivity
{
    private SimpleFragmentPagerAdapter pagerAdapter;
    
    @Bind(R.id.viewpager)
    ViewPager viewPager;
    
    @Bind(R.id.sliding_tabs)
    TabLayout tabLayout;
    
    private List<Fragment> fragments = new ArrayList<>();
    
    @Override
    public void initContentLayout()
    {
        setContentView(R.layout.design_tab_layout);
    }
    
    @Override
    public void initListener()
    {
        
    }
    
    @Override
    public void initData()
    {
        setCustomToolbar(ToolbarType.NOMENU, "Design TabLayout");
        fragments.add(new FragmentB());
        fragments.add(new FragmentC());
        fragments.add(new FragmentD());
        pagerAdapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
    }
    
    @Override
    public void bindClick(int viewId)
    {
        
    }
    
    private class SimpleFragmentPagerAdapter extends FragmentPagerAdapter
    {
        
        final int PAGE_COUNT = 3;
        
        private String tabTitles[] = new String[] {"tab1", "tab2", "tab3"};
        
        public SimpleFragmentPagerAdapter(FragmentManager fm)
        {
            super(fm);
        }
        
        @Override
        public Fragment getItem(int position)
        {
            return fragments.get(position);
        }
        
        @Override
        public int getCount()
        {
            return PAGE_COUNT;
        }
        
        @Override
        public CharSequence getPageTitle(int position)
        {
            return tabTitles[position];
        }

    }
}
