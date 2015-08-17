package com.nate.loveframe.ui.activity.test;

import java.util.ArrayList;
import java.util.List;

import me.nereo.multi_image_selector.MultiImageSelectorActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import butterknife.Bind;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.nate.loveframe.R;
import com.nate.loveframe.ui.activity.base.BaseActivity;

/**
 * Created by Nate on 2015/8/17. 图片选择器测试页面
 */
public class PhotoSelectTestActivity extends BaseActivity
{
    private static final int REQUEST_IMAGE = 999;
    
    @Bind(R.id.selectOnePhotoBtn)
    Button selectOnePhotoBtn;
    
    @Bind(R.id.selectMorePhotoBtn)
    Button selectMorePhotoBtn;
    
    @Bind(R.id.photo_hs)
    HorizontalScrollView photoHs;
    
    @Bind(R.id.layoutPic)
    LinearLayout layoutPic;
    
    private List<String> urlList = new ArrayList<>();
    
    private ImageView item_delete;
    
    @Override
    public void initContentLayout()
    {
        setContentView(R.layout.photo_select_test_layout);
    }
    
    @Override
    public void initListener()
    {
        selectOnePhotoBtn.setOnClickListener(this);
        selectMorePhotoBtn.setOnClickListener(this);
    }
    
    @Override
    public void initData()
    {
        setCustomToolbar(ToolbarType.NOMENU, "图片选择测试");
    }
    
    @Override
    public void bindClick(int viewId)
    {
        Intent intent = new Intent(this, MultiImageSelectorActivity.class);
        if (viewId == R.id.selectOnePhotoBtn)
        {
            // 是否显示调用相机拍照
            intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, true);
            // 设置模式 (支持 单选/MultiImageSelectorActivity.MODE_SINGLE 或者 多选/MultiImageSelectorActivity.MODE_MULTI)
            intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, MultiImageSelectorActivity.MODE_SINGLE);
        }
        else if (viewId == R.id.selectMorePhotoBtn)
        {
            // 是否显示调用相机拍照
            intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, false);
            // 最大图片选择数量
            intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, 9);
            // 设置模式 (支持 单选/MultiImageSelectorActivity.MODE_SINGLE 或者 多选/MultiImageSelectorActivity.MODE_MULTI)
            intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, MultiImageSelectorActivity.MODE_MULTI);
        }
        startActivityForResult(intent, REQUEST_IMAGE);
    }
    
    /**
     * 根据url显示图片
     */
    private void setPicToView()
    {
        layoutPic.removeAllViews();
        photoHs.removeAllViews();
        for (int i = 0; i < urlList.size(); i++)
        {
            // 加载布局
            View view =
                ((LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.photo_select_item_layout,
                    null);
            SimpleDraweeView simpleDraweeView = (SimpleDraweeView)view.findViewById(R.id.simple_drawee_iv);
            // 加载图片
            Uri uri = Uri.parse(urlList.get(i));
            DraweeController controller = Fresco.newDraweeControllerBuilder().setUri(uri).setAutoPlayAnimations(true)// 自动播放gif
                .setOldController(simpleDraweeView.getController())
                .setControllerListener(null)
                .build();
            simpleDraweeView.setController(controller);
            item_delete = (ImageView)view.findViewById(R.id.item_delete);
            item_delete.setOnClickListener(new ImgDeleteOnClickListener(i));
            layoutPic.addView(view);
        }
        photoHs.addView(layoutPic);
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE)
        {
            if (resultCode == RESULT_OK)
            {
                urlList.clear();
                // 获取返回的图片列表
                List<String> path = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                // 处理你自己的逻辑 ....
                for (String str : path)
                {
                    showLog("-----" + str);
                    urlList.add("file://" + str);
                }
                setPicToView();
            }
        }
    }
    
    // 布局删除监听事件
    private class ImgDeleteOnClickListener implements View.OnClickListener
    {
        private int position;
        
        public ImgDeleteOnClickListener(int position)
        {
            this.position = position;
        }
        
        @Override
        public void onClick(View v)
        {
            switch (v.getId())
            {
                case R.id.item_delete:
                    urlList.remove(position);
                    setPicToView();
                    break;
                default:
                    break;
            }
        }
    }
}
