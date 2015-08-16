package com.nate.loveframe.ui.activity.test;

import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import butterknife.Bind;

import com.baoyz.actionsheet.ActionSheet;
import com.nate.loveframe.R;
import com.nate.loveframe.ui.activity.base.BaseActivity;

/**
 * Created by Nate on 2015/8/13.
 */
public class BottomMenuActivity extends BaseActivity implements ActionSheet.ActionSheetListener {
    @Bind(R.id.ios6)
    Button ios6;

    @Bind(R.id.ios7)
    Button ios7;

    @Override
    public void initContentLayout() {
        setContentView(R.layout.bottom_menu_layout);
    }

    @Override
    public void initListener() {
        ios6.setOnClickListener(this);
        ios7.setOnClickListener(this);
    }

    @Override
    public void initData() {
        //设置toolbar
        setCustomToolbar(ToolbarType.NOMENU, "底部菜单测试");
    }

    @Override
    public void bindClick(int viewId) {
        if (viewId == R.id.ios7) {
            setTheme(R.style.ActionSheetStyleiOS7);
        } else if (viewId == R.id.ios6) {
            setTheme(R.style.ActionSheetStyleiOS6);
        }
        showActionSheet();
    }

    public void showActionSheet() {
        ActionSheet.createBuilder(this, getSupportFragmentManager())
                .setCancelButtonTitle("Cancel")
                .setOtherButtonTitles("Item1", "Item2", "Item3", "Item4")
                .setCancelableOnTouchOutside(true).setListener(this).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onOtherButtonClick(ActionSheet actionSheet, int index) {
        showSuperToast("click item index = " + index);
    }

    @Override
    public void onDismiss(ActionSheet actionSheet, boolean isCancle) {
        showSuperToast("dismissed isCancle = " + isCancle);
    }

}
