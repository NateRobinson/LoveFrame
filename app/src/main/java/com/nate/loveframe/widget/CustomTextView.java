package com.nate.loveframe.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.nate.loveframe.LoveFrameApplication;

/**
 * Created by Dmytro Denysenko on 5/6/15.
 */
public class CustomTextView extends TextView
{
    public CustomTextView(Context context)
    {
        this(context, null);
    }
    
    public CustomTextView(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }
    
    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        setTypeface(LoveFrameApplication.canaroExtraBold);
    }
    
}
