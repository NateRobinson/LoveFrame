package com.nate.loveframe.ui.activity.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

import com.nate.loveframe.R;
import com.nate.loveframe.utils.Logger;
import com.nate.loveframe.utils.ToastUtils;
import com.nate.loveframe.widget.CustomTextView;

/**
 * Created by Nate on 2015/8/12. 应用的Activity基类，在里面将页面所有Activity中公用的方法集中起来，提高代码可读性 方便代码管理
 */
public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener
{
    private String TAG;
    
    @Bind(R.id.content_hamburger)
    protected View contentHamburger;
    
    @Bind(R.id.toolbar)
    protected Toolbar toolbar;
    
    @Bind(R.id.title)
    CustomTextView customTextView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        TAG = this.getClass().getSimpleName();
        initContentLayout();
        ButterKnife.bind(this);
        initListener();
        initData();
    }
    
    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            default:
                // 页面处理点击事件
                bindClick(v.getId());
                break;
        }
    }
    
    // 初始化子类的布局文件
    public abstract void initContentLayout();
    
    // 监听回调的初始化设置
    public abstract void initListener();
    
    // 初始化数据
    public abstract void initData();
    
    // 子类实现，用来进行点击事件的监听
    public abstract void bindClick(int viewId);
    
    /**
     * 根据每个页面的需求设置toolbar
     * 
     * @param type toolbar 类型
     * @param title 标题
     */
    protected void setCustomToolbar(ToolbarType type, String title)
    {
        if (toolbar != null)
        {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(null);
            customTextView.setText(title);
        }
        switch (type)
        {
            case WITHMENU:
                contentHamburger.setVisibility(View.VISIBLE);
                break;
            case NOMENU:
                contentHamburger.setVisibility(View.GONE);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                break;
            default:
                contentHamburger.setVisibility(View.GONE);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                break;
        }
    }
    
    /**
     * Toast展示 供子类使用
     * 
     * @param msg Toast内容
     */
    protected void showToast(String msg)
    {
        ToastUtils.showToast(this, msg, Toast.LENGTH_SHORT);
    }
    
    /**
     * SuperToast展示 供子类使用
     * 
     * @param msg Toast内容
     */
    protected void showSuperToast(String msg)
    {
        ToastUtils.showSuperToast(this, msg);
    }
    
    /**
     * 日志打印 TAG为对应子类类名
     * 
     * @param msg 日志内容
     */
    protected void showLog(String msg)
    {
        Logger.show(TAG, msg);
    }
    
    // 2.sharedPreference集中处理
    
    /**
     * Toolbar类型 WITHMENU--带菜单；NOMENU--没有菜单，有返回功能
     */
    public enum ToolbarType
    {
        WITHMENU, NOMENU;
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
