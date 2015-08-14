package com.nate.loveframe.ui.activity;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import butterknife.Bind;

import com.nate.loveframe.R;
import com.nate.loveframe.ui.activity.base.BaseActivity;
import com.nate.loveframe.ui.activity.test.BottomMenuActivity;
import com.nate.loveframe.ui.activity.test.OverlayMenuActivity;
import com.yalantis.guillotine.animation.GuillotineAnimation;

public class MainActivity extends BaseActivity
{
    private static final long RIPPLE_DURATION = 250;
    
    @Bind(R.id.bottomMenu)
    Button bottomMenu;
    @Bind(R.id.overlayMenu)
    Button overlayMenu;

    @Bind(R.id.root)
    FrameLayout root;
    
    @Override
    public void initContentLayout()
    {
        setContentView(R.layout.activity_main);
    }
    
    @Override
    public void initListener()
    {
        bottomMenu.setOnClickListener(this);
        overlayMenu.setOnClickListener(this);
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
    }
    
    @Override
    public void bindClick(int viewId)
    {
        Intent intent = new Intent();
        switch (viewId)
        {
            case R.id.bottomMenu:
                intent.setClass(MainActivity.this, BottomMenuActivity.class);
                startActivity(intent);
                break;
            case R.id.overlayMenu:
                intent.setClass(MainActivity.this, OverlayMenuActivity.class);
                startActivity(intent);
                break;
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
    
}
