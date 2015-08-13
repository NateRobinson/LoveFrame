package com.nate.loveframe.utils;

import android.content.Context;
import android.net.Uri;

import com.facebook.drawee.drawable.ProgressBarDrawable;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by Nate on 2015/8/13. 自定义的Fresco使用工具类
 */
public class FrescoUtils
{
    /**
     * 展示有进度条的图片  默认以图片的中心裁剪
     * 
     * @param iv SimpleDraweeView图片展示控件
     * @param context 应用上下文
     * @param path 图片地址
     */
    public static void showProgressBarPic(SimpleDraweeView iv, Context context, String path)
    {
        GenericDraweeHierarchyBuilder builder = new GenericDraweeHierarchyBuilder(context.getResources());
        GenericDraweeHierarchy hierarchy = builder.setProgressBarImage(new ProgressBarDrawable()).build();
        iv.setHierarchy(hierarchy);
        Uri uri = Uri.parse(path);
        iv.setImageURI(uri);
    }


    //TODO  根据自己需求添加个性化图片展示

}
