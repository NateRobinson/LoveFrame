package com.nate.loveframe.ui.activity.test;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

import android.os.CountDownTimer;
import android.widget.TextView;

import butterknife.Bind;

import com.nate.loveframe.R;
import com.nate.loveframe.ui.activity.base.BaseActivity;

/**
 * Created by Nate on 2015/8/19. discreteSeekBar 使用
 */
public class SeekBarActivity extends BaseActivity
{
    
    @Bind(R.id.dSeekBar)
    DiscreteSeekBar dSeekBar;
    
    @Bind(R.id.valueTv)
    TextView valueTv;
    
    @Override
    public void initContentLayout()
    {
        setContentView(R.layout.seekbar_layout);
    }
    
    @Override
    public void initListener()
    {
        
    }
    
    @Override
    public void initData()
    {
        setCustomToolbar(ToolbarType.NOMENU, "SeekBar Test");
        dSeekBar.setOnProgressChangeListener(new DiscreteSeekBar.OnProgressChangeListener()
        {
            @Override
            public void onProgressChanged(DiscreteSeekBar discreteSeekBar, int i, boolean b)
            {
                valueTv.setText("seek bar value==>" + i + "");
            }
            
            @Override
            public void onStartTrackingTouch(DiscreteSeekBar discreteSeekBar)
            {
                
            }
            
            @Override
            public void onStopTrackingTouch(DiscreteSeekBar discreteSeekBar)
            {
                
            }
        });
        dSeekBar.setNumericTransformer(new DiscreteSeekBar.NumericTransformer()
        {
            @Override
            public int transform(int value)
            {
                return value * 100;
            }
        });
        new CountDownTimer(10000, 1000)
        {
            int count = 0;
            
            /**
             * Callback fired on regular interval.
             *
             * @param millisUntilFinished The amount of time until finished.
             */
            @Override
            public void onTick(long millisUntilFinished)
            {
                count++;
                dSeekBar.setProgress(count);
            }
            
            /**
             * Callback fired when the time is up.
             */
            @Override
            public void onFinish()
            {
                
            }
        }.start();
    }
    
    @Override
    public void bindClick(int viewId)
    {
        
    }
}
