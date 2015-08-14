package com.nate.loveframe.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
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
import android.widget.Toast;

import butterknife.Bind;

import com.astuetz.PagerSlidingTabStrip;
import com.nate.loveframe.R;
import com.nate.loveframe.ui.activity.base.BaseActivity;
import com.nate.loveframe.ui.fragment.SuperAwesomeCardFragment;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.yalantis.guillotine.animation.GuillotineAnimation;

public class MainActivity extends BaseActivity
{
    private static final long RIPPLE_DURATION = 250;
    
    // @Bind(R.id.bottomMenu)
    // Button bottomMenu;
    //
    // @Bind(R.id.overlayMenu)
    // Button overlayMenu;
    
    @Bind(R.id.root)
    FrameLayout root;
    
    @Bind(R.id.tabs)
    PagerSlidingTabStrip tabs;
    
    @Bind(R.id.pager)
    ViewPager pager;
    
    private MyPagerAdapter adapter;
    
    private Drawable oldBackground = null;
    
    private int currentColor;
    
    // 状态栏颜色沉浸效果
    private SystemBarTintManager mTintManager;
    
    @Override
    public void initContentLayout()
    {
        setContentView(R.layout.activity_main);
    }
    
    @Override
    public void initListener()
    {
        // bottomMenu.setOnClickListener(this);
        // overlayMenu.setOnClickListener(this);
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
        // create our manager instance after the content view is set
        mTintManager = new SystemBarTintManager(this);
        // enable status bar tint
        mTintManager.setStatusBarTintEnabled(true);
        mTintManager.setStatusBarTintEnabled(true);
        adapter = new MyPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
        tabs.setViewPager(pager);
        final int pageMargin =
            (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics());
        pager.setPageMargin(pageMargin);
        pager.setCurrentItem(1);
        changeColor(getResources().getColor(R.color.green));
        tabs.setOnTabReselectedListener(new PagerSlidingTabStrip.OnTabReselectedListener()
        {
            @Override
            public void onTabReselected(int position)
            {
                Toast.makeText(MainActivity.this, "Tab reselected: " + position, Toast.LENGTH_SHORT).show();
            }
        });
    }
    
    @Override
    public void bindClick(int viewId)
    {
        Intent intent = new Intent();
        switch (viewId)
        {
            // case R.id.bottomMenu:
            // intent.setClass(MainActivity.this, BottomMenuActivity.class);
            // startActivity(intent);
            // break;
            // case R.id.overlayMenu:
            // intent.setClass(MainActivity.this, OverlayMenuActivity.class);
            // startActivity(intent);
            // break;
            default:
                break;
        }
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
    
    private void changeColor(int newColor)
    {
        tabs.setBackgroundColor(newColor);
        mTintManager.setTintColor(newColor);
        // change ActionBar color just if an ActionBar is available
        Drawable colorDrawable = new ColorDrawable(newColor);
        Drawable bottomDrawable = new ColorDrawable(getResources().getColor(android.R.color.transparent));
        LayerDrawable ld = new LayerDrawable(new Drawable[] {colorDrawable, bottomDrawable});
        if (oldBackground == null)
        {
            getSupportActionBar().setBackgroundDrawable(ld);
        }
        else
        {
            TransitionDrawable td = new TransitionDrawable(new Drawable[] {oldBackground, ld});
            getSupportActionBar().setBackgroundDrawable(td);
            td.startTransition(200);
        }
        oldBackground = ld;
        currentColor = newColor;
    }
    
    public void onColorClicked(View v)
    {
        int color = Color.parseColor(v.getTag().toString());
        changeColor(color);
    }
    
    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putInt("currentColor", currentColor);
    }
    
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);
        currentColor = savedInstanceState.getInt("currentColor");
        changeColor(currentColor);
    }
    
    public class MyPagerAdapter extends FragmentPagerAdapter
    {
        
        private final String[] TITLES =
            {"Categories", "Home", "Top Paid", "Top Free", "Top Grossing", "Top New Paid", "Top New Free", "Trending"};
            
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
            return TITLES.length;
        }
        
        @Override
        public Fragment getItem(int position)
        {
            return SuperAwesomeCardFragment.newInstance(position);
        }
    }
}
