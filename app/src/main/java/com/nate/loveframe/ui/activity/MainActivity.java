package com.nate.loveframe.ui.activity;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import butterknife.Bind;

import com.astuetz.PagerSlidingTabStrip;
import com.nate.loveframe.R;
import com.nate.loveframe.ui.activity.base.BaseActivity;
import com.nate.loveframe.ui.fragment.FragmentA;
import com.nate.loveframe.ui.fragment.FragmentB;
import com.nate.loveframe.ui.fragment.FragmentC;
import com.nate.loveframe.ui.fragment.FragmentD;
import com.yalantis.guillotine.animation.GuillotineAnimation;

public class MainActivity extends BaseActivity
{
    private static final long RIPPLE_DURATION = 250;
    
    @Bind(R.id.root)
    FrameLayout root;
    
    @Bind(R.id.tabs)
    PagerSlidingTabStrip tabs;
    
    @Bind(R.id.pager)
    ViewPager pager;
    
    private MyPagerAdapter adapter;
    
    private List<Fragment> fragments = new ArrayList<>();
    
    @Override
    public void initContentLayout()
    {
        setContentView(R.layout.activity_main);
    }
    
    @Override
    public void initListener()
    {
    }
    
    @Override
    public void initData()
    {
        // 设置toolbar
        setCustomToolbar(ToolbarType.WITHMENU, "首页");
        // 设置menu 可以更具每个页面自己定义
        View mainToolbarMenu = LayoutInflater.from(this).inflate(R.layout.main_toolbar_menu, null);
        root.addView(mainToolbarMenu);
        GuillotineAnimation mainToolbarMenuAnimation = new GuillotineAnimation.GuillotineBuilder(mainToolbarMenu,
            mainToolbarMenu.findViewById(R.id.guillotine_hamburger), contentHamburger).setStartDelay(RIPPLE_DURATION)
                .setActionBarViewForAnimation(toolbar)
                .build();
        mainToolbarMenuAnimation.close();
        // menu中菜单项的点击测试
        mainToolbarMenu.findViewById(R.id.profile_group).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                showSuperToast("profile_group");
            }
        });
        // 设置tablayout
        fragments.add(new FragmentA());
        fragments.add(new FragmentB());
        fragments.add(new FragmentC());
        fragments.add(new FragmentD());
        adapter = new MyPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
        tabs.setViewPager(pager);
        // 设置每个page之间的间距
        final int pageMargin =
            (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics());
        pager.setPageMargin(pageMargin);
        // 设置一开始初始化时，展现哪个页面
        pager.setCurrentItem(0);
    }
    
    @Override
    public void bindClick(int viewId)
    {
    
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        return super.onOptionsItemSelected(item);
    }
    
    /**
     * FragmentPagerAdapter 用于配置ViewPager展示Fragment
     */
    public class MyPagerAdapter extends FragmentPagerAdapter
    {
        private final String[] TITLES = getResources().getStringArray(R.array.tabmenu);
        
        public MyPagerAdapter(FragmentManager fm)
        {
            super(fm);
        }
        
        @Override
        public CharSequence getPageTitle(int position)
        {
            return TITLES[position];
        }
        
        @Override
        public int getCount()
        {
            return fragments.size();
        }
        
        @Override
        public Fragment getItem(int position)
        {
             return fragments.get(position);
        }
    }
}
