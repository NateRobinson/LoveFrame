package com.nate.loveframe.ui.activity.test;

import android.view.MenuItem;
import android.widget.Button;

import butterknife.Bind;

import com.nate.loveframe.R;
import com.nate.loveframe.ui.activity.base.BaseActivity;

/**
 * Created by Nate on 2015/8/13.
 */
public class BottomMenuActivity extends BaseActivity
{
    @Bind(R.id.showBtn)
    Button showBtn;
    
    @Bind(R.id.hideBtn)
    Button hideBtn;
    
    @Override
    public void initContentLayout()
    {
        setContentView(R.layout.bottom_menu_layout);
    }
    
    @Override
    public void initListener()
    {
        showBtn.setOnClickListener(this);
        hideBtn.setOnClickListener(this);
    }
    
    @Override
    public void initData()
    {
        //设置toolbar
        setCustomToolbar(ToolbarType.NOMENU, "底部菜单测试");
    }
    
    @Override
    public void bindClick(int viewId)
    {
        if (viewId == R.id.hideBtn)
        {
        
        }
        else if (viewId == R.id.showBtn)
        {
        
        }
    }
    
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (item.getItemId() == android.R.id.home)
        {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
