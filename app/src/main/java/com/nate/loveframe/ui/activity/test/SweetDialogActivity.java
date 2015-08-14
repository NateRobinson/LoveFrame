package com.nate.loveframe.ui.activity.test;

import android.os.CountDownTimer;

import cn.pedant.SweetAlert.SweetAlertDialog;

import com.nate.loveframe.R;
import com.nate.loveframe.ui.activity.base.BaseActivity;

/**
 * Created by Nate on 2015/8/14. SweetDialog 示范
 */
public class SweetDialogActivity extends BaseActivity
{
    private int i = -1;
    
    @Override
    public void initContentLayout()
    {
        setContentView(R.layout.sweet_dialog_layout);
    }
    
    @Override
    public void initListener()
    {
        findViewById(R.id.basic_test).setOnClickListener(this);
        findViewById(R.id.under_text_test).setOnClickListener(this);
        findViewById(R.id.error_text_test).setOnClickListener(this);
        findViewById(R.id.success_text_test).setOnClickListener(this);
        findViewById(R.id.warning_confirm_test).setOnClickListener(this);
        findViewById(R.id.warning_cancel_test).setOnClickListener(this);
        findViewById(R.id.custom_img_test).setOnClickListener(this);
        findViewById(R.id.progress_dialog).setOnClickListener(this);
    }
    
    @Override
    public void initData()
    {
        setCustomToolbar(ToolbarType.NOMENU, "SweetDialog");
    }
    
    @Override
    public void bindClick(int viewId)
    {
        switch (viewId)
        {
            case R.id.basic_test:
                // 默认展示的内容是"Here's a message!"
                SweetAlertDialog sd = new SweetAlertDialog(this);
                sd.setTitleText("消息");
                sd.setConfirmText("好的");
                // 可以按返回取消
                sd.setCancelable(true);
                // 可以点击外部取消
                sd.setCanceledOnTouchOutside(true);
                sd.show();
                break;
            case R.id.under_text_test:
                // 有内容，有标题的dialog
                new SweetAlertDialog(this).setTitleText("标题")
                    .setContentText("这个弹出框很漂亮，是不是？")
                    .setConfirmText("好的")
                    .show();
                break;
            case R.id.error_text_test:
                // 错误提示类型的弹出框
                new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE).setTitleText("标题")
                    .setContentText("这里出了点问题!")
                    .setConfirmText("好的")
                    .show();
                break;
            case R.id.success_text_test:
                // 成功类型的弹出框
                new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE).setTitleText("标题")
                    .setContentText("干的漂亮!")
                    .setConfirmText("好的")
                    .show();
                break;
            case R.id.warning_confirm_test:
                // 一个带了按钮监听的警告框
                new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE).setTitleText("确定删除?")
                    .setContentText("删除将不可恢复!")
                    .setConfirmText("确定!")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener()
                    {
                        @Override
                        public void onClick(SweetAlertDialog sDialog)
                        {
                            sDialog.setTitleText("已删除!")
                                .setContentText("文件已删除!")
                                .setConfirmText("好的")
                                .setConfirmClickListener(null)
                                .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                        }
                    })
                    .show();
                break;
            case R.id.warning_cancel_test:
                // 一个警告框，里面有两个按钮监听
                new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE).setTitleText("确定删除?")
                    .setContentText("删除不可恢复!")
                    .setCancelText("取消")
                    .setConfirmText("确定")
                    .showCancelButton(true)
                    .setCancelClickListener(null)
                    // .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener()
                    // {
                    // @Override
                    // public void onClick(SweetAlertDialog sDialog)
                    // {
                    // // reuse previous dialog instance, keep widget user state, reset them if you need
                    // sDialog.setTitleText("Cancelled!")
                    // .setContentText("Your imaginary file is safe :)")
                    // .setConfirmText("OK")
                    // .showCancelButton(false)
                    // .setCancelClickListener(null)
                    // .setConfirmClickListener(null)
                    // .changeAlertType(SweetAlertDialog.ERROR_TYPE);
                    // }
                    // })
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener()
                    {
                        @Override
                        public void onClick(SweetAlertDialog sDialog)
                        {
                            sDialog.setTitleText("已删除!")
                                .setContentText("文件已删除!")
                                .setConfirmText("好的")
                                .showCancelButton(false)
                                .setCancelClickListener(null)
                                .setConfirmClickListener(null)
                                .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                        }
                    })
                    .show();
                break;
            case R.id.custom_img_test:
                // 带自定义图片的弹出框 CUSTOM_IMAGE_TYPE
                new SweetAlertDialog(this, SweetAlertDialog.CUSTOM_IMAGE_TYPE).setTitleText("棒棒!")
                    .setContentText("干的漂亮")
                    .setCustomImage(R.mipmap.custom_img)
                    .show();
                break;
            case R.id.progress_dialog:
                final SweetAlertDialog pDialog =
                    new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE).setTitleText("Loading");
                pDialog.show();
                pDialog.setCancelable(false);
                // 使用CountDownTimer进行定时操作
                new CountDownTimer(800 * 7, 800)
                {
                    public void onTick(long millisUntilFinished)
                    {
                        // 你可以没800毫秒改变一次进度条的颜色
                        i++;
                        switch (i)
                        {
                            case 0:
                                pDialog.getProgressHelper()
                                    .setBarColor(getResources().getColor(R.color.blue_btn_bg_color));
                                break;
                            case 1:
                                pDialog.getProgressHelper()
                                    .setBarColor(getResources().getColor(R.color.material_deep_teal_50));
                                break;
                            case 2:
                                pDialog.getProgressHelper()
                                    .setBarColor(getResources().getColor(R.color.success_stroke_color));
                                break;
                            case 3:
                                pDialog.getProgressHelper()
                                    .setBarColor(getResources().getColor(R.color.material_deep_teal_20));
                                break;
                            case 4:
                                pDialog.getProgressHelper()
                                    .setBarColor(getResources().getColor(R.color.material_blue_grey_80));
                                break;
                            case 5:
                                pDialog.getProgressHelper()
                                    .setBarColor(getResources().getColor(R.color.warning_stroke_color));
                                break;
                            case 6:
                                pDialog.getProgressHelper()
                                    .setBarColor(getResources().getColor(R.color.success_stroke_color));
                                break;
                        }
                    }
                    
                    public void onFinish()
                    {
                        i = -1;
                        pDialog.setTitleText("成功!").setConfirmText("好的").changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                    }
                }.start();
                break;
        }
    }
}
