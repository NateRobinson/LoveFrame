package com.nate.loveframe.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Nate on 2015/8/12. Toast弹出统一工具类
 */
public class ToastUtils
{
    public static Toast mToast;
    
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
}
