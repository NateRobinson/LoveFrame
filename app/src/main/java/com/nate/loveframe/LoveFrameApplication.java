package com.nate.loveframe;

import android.app.Application;
import android.graphics.Typeface;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by Nate on 2015/8/13. 应用的Application，在里面进行一些第三方框架的初始化工作
 */
public class LoveFrameApplication extends Application
{
    private static final String CANARO_EXTRA_BOLD_PATH = "fonts/AdobeKaitiStd-Regular.otf";
    
    public static Typeface canaroExtraBold;
    
    @Override
    public void onCreate()
    {
        super.onCreate();
        // 初始化Fresco加载框架
        Fresco.initialize(this);
        // 自定义文字字体
        initTypeface();
    }

    /**
     * 自定义文字字体
     */
    private void initTypeface()
    {
        canaroExtraBold = Typeface.createFromAsset(getAssets(), CANARO_EXTRA_BOLD_PATH);
    }
}
