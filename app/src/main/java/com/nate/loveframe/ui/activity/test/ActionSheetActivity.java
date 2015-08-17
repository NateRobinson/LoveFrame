package com.nate.loveframe.ui.activity.test;

import android.os.CountDownTimer;
import android.widget.Button;

import butterknife.Bind;

import com.baoyz.actionsheet.ActionSheet;
import com.mingle.widget.ShapeLoadingDialog;
import com.nate.loveframe.R;
import com.nate.loveframe.ui.activity.base.BaseActivity;

/**
 * Created by Nate on 2015/8/13.
 */
public class ActionSheetActivity extends BaseActivity implements ActionSheet.ActionSheetListener {
    @Bind(R.id.ios6)
    Button ios6;

    @Bind(R.id.ios7)
    Button ios7;
    @Bind(R.id.wait_dialog)
    Button wait_dialog;

    @Override
    public void initContentLayout() {
        setContentView(R.layout.action_sheet_layout);
    }

    @Override
    public void initListener() {
        ios7.setOnClickListener(this);
        ios6.setOnClickListener(this);
        wait_dialog.setOnClickListener(this);
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
            showActionSheet();
        } else if (viewId == R.id.ios6) {
            setTheme(R.style.ActionSheetStyleiOS6);
            showActionSheet();
        }else if (viewId == R.id.wait_dialog) {
            final ShapeLoadingDialog shapeLoadingDialog=new ShapeLoadingDialog(this);
            shapeLoadingDialog.setLoadingText("加载中...");
            shapeLoadingDialog.show();
            new CountDownTimer(3000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {

                }

                @Override
                public void onFinish() {
                    shapeLoadingDialog.dismiss();
                }
            }.start();
        }

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
