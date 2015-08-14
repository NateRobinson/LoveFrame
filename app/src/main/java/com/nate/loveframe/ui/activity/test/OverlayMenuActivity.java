package com.nate.loveframe.ui.activity.test;

import android.view.View;

import butterknife.Bind;

import com.nate.loveframe.R;
import com.nate.loveframe.ui.activity.base.BaseActivity;

import it.sephiroth.android.library.overlaymenu.OverMenuView;

/**
 * Created by Nate on 2015/8/14. overlayMenu 测试页面
 */
public class OverlayMenuActivity extends BaseActivity
    implements OverMenuView.OnSelectionChangeListener, OverMenuView.OnMenuVisibilityChangeListener
{
    private int position = -1;
    
    private int lastPosition = -1;
    
    @Bind(R.id.overmenu)
    OverMenuView overMenuView;
    
    @Override
    public void initContentLayout()
    {
        setContentView(R.layout.overlay_menu_layout);
    }
    
    @Override
    public void initListener()
    {
        overMenuView.setOnSelectionChangedListener(this);
        overMenuView.setOnMenuVisibilityChangeListener(this);
    }
    
    @Override
    public void initData()
    {
        setCustomToolbar(ToolbarType.NOMENU, "OverlayMenuTest");
    }
    
    @Override
    public void bindClick(int viewId)
    {
    
    }
    
    @Override
    public void onSelectionChanged(int position)
    {
        lastPosition = this.position;
        this.position = position;
    }
    
    @Override
    public void onVisibilityChanged(View view, boolean visible)
    {
        // 每次打开菜单的时候，将上一次的position记下
        if (visible)
        {
            lastPosition = this.position;
        }
        // 只有当菜单变为不可见，并且上一次的position和这一次不一样才进行菜单改变的处理
        if (!visible && (lastPosition != position))
        {
            showSuperToast("position:" + position);
        }
    }
    
}
