package com.nate.loveframe.utils;

import android.content.Context;
import android.widget.Toast;

import com.github.johnpersano.supertoasts.SuperToast;

/**
 * Created by Nate on 2015/8/12. Toast弹出统一工具类
 */
public class ToastUtils
{
    public static Toast mToast;
    
    public static SuperToast mSuperToast;
    
    /**
     * 采用单列模式，避免多次弹出Toast的时候，出现不停弹出Toast的问题
     * 
     * @param context
     * @param text
     * @param duration
     */
    public static void showToast(Context context, CharSequence text, int duration)
    {
        if (null == mToast)
        {
            mToast = Toast.makeText(context, text, duration);
        }
        else
        {
            mToast.setText(text);
            mToast.show();
        }
    }
    
    /**
     * 自定义的supertoast，加入了动画飞入的效果，增加应用体验可玩性 采用单例模式，避免重复创建对象，提高代码性能
     * 
     * @param context
     * @param text
     */
    public static void showSuperToast(Context context, CharSequence text)
    {
        if (null == mSuperToast)
        {
            mSuperToast = new SuperToast(context);
            mSuperToast.setAnimations(SuperToast.Animations.FLYIN);
            mSuperToast.setDuration(SuperToast.Duration.SHORT);
            mSuperToast.setBackground(SuperToast.Background.GRAY);
            mSuperToast.setTextSize(SuperToast.TextSize.SMALL);
        }
        else
        {
            mSuperToast.setText(text);
            mSuperToast.show();
        }
    }
    
}
