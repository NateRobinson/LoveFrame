package com.nate.loveframe.ui.activity.test;

import org.mitre.ascv.AndroidSegmentedControlView;

import android.graphics.Color;
import android.widget.LinearLayout;

import com.nate.loveframe.R;
import com.nate.loveframe.ui.activity.base.BaseActivity;

/**
 * Created by Nate on 2015/8/18. SegmentMenu使用测试
 */
public class SegmentMenuActivity extends BaseActivity
{
    @Override
    public void initContentLayout()
    {
        setContentView(R.layout.segment_menu_layout);
    }
    
    @Override
    public void initListener()
    {
    }
    
    @Override
    public void initData()
    {
        setCustomToolbar(ToolbarType.NOMENU, "Segment Menu");
        LinearLayout holder = (LinearLayout)findViewById(R.id.ascv_sample_holder);
        try
        {
            AndroidSegmentedControlView ascv = new AndroidSegmentedControlView(this);
            ascv.setColors(Color.parseColor("#0066CC"), Color.parseColor("#FFFFFF"));
            ascv.setItems(new String[] {"Test1", "Test2", "Test3"}, new String[] {"1", "2", "3"});
            ascv.setDefaultSelection(0);
            holder.addView(ascv);
            
            AndroidSegmentedControlView ascv2 = new AndroidSegmentedControlView(this);
            ascv2.setColors(Color.parseColor("#D24E4E"), Color.parseColor("#FFFFFF"));
            // 设置是否平铺
            ascv2.setStretch(true);
            ascv2.setItems(new String[] {"Test4", "Test5", "Test6"}, new String[] {"4", "5", "6"});
            ascv2.setDefaultSelection(2);
            holder.addView(ascv2);
            ascv2.setOnSelectionChangedListener(new AndroidSegmentedControlView.OnSelectionChangedListener()
            {
                @Override
                public void newSelection(String identifier, String value)
                {
                    showToast(identifier + "==" + value);
                }
            });
            
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
    @Override
    public void bindClick(int viewId)
    {
        
    }
    
}
