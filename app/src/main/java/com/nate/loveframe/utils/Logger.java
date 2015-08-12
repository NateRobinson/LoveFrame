package com.nate.loveframe.utils;

import android.util.Log;

import com.nate.loveframe.constant.Constants;

/**
 * Created by Nate on 2015/8/12.打印日志的工具类
 */
public class Logger
{
    /**
     * 默认使用INFO等级的日志展示
     * 
     * @param TAG
     * @param msg
     */
    public static void show(String TAG, String msg)
    {
        if (!Constants.isShowLog)
        {
            return;
        }
        show(TAG, msg, Log.INFO);
    }
    
    /**
     * 自定义日志展示等级
     * 
     * @param TAG 日志标记
     * @param msg 日志内容
     * @param level 日志等级
     */
    public static void show(String TAG, String msg, int level)
    {
        if (!Constants.isShowLog)
        {
            return;
        }
        switch (level)
        {
            case Log.VERBOSE:
                Log.v(TAG, msg);
                break;
            case Log.DEBUG:
                Log.d(TAG, msg);
                break;
            case Log.INFO:
                Log.i(TAG, msg);
                break;
            case Log.WARN:
                Log.w(TAG, msg);
                break;
            case Log.ERROR:
                Log.e(TAG, msg);
                break;
            default:
                Log.i(TAG, msg);
                break;
        }
    }
    
}
