package com.nate.loveframe.ui.activity.test;

import java.util.ArrayList;
import java.util.List;

import me.gujun.android.taggroup.TagGroup;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.exception.DbException;
import com.nate.loveframe.R;
import com.nate.loveframe.entity.TargetEntity;
import com.nate.loveframe.ui.activity.base.BaseActivity;

/**
 * Created by Nate on 2015/8/18. targetgroup使用指南
 */
public class TargetGroupActivity extends BaseActivity
{
    @Bind(R.id.tv_prompt)
    TextView tvPrompt;
    
    @Bind(R.id.tag_group)
    TagGroup mDefaultTagGroup;
    
    @Bind(R.id.tag_group_beauty)
    TagGroup mBeautyTagGroup;
    
    @Bind(R.id.tag_group_beauty_inverse)
    TagGroup mBeautyInverseTagGroup;
    
    @Bind(R.id.tag_group_small)
    TagGroup mSmallTagGroup;
    
    @Bind(R.id.tag_group_large)
    TagGroup mLargeTagGroup;
    
    private DbUtils db = null;
    
    private List<String> showTags = new ArrayList<>();
    
    private TagGroup.OnTagClickListener mTagClickListener = new TagGroup.OnTagClickListener()
    {
        @Override
        public void onTagClick(String tag)
        {
            showToast(tag);
        }
    };
    
    @Override
    public void initContentLayout()
    {
        setContentView(R.layout.target_group_layout);
    }
    
    @Override
    public void initListener()
    {
        
    }
    
    @Override
    public void initData()
    {
        setCustomToolbar(ToolbarType.NOMENU, "Tag Group");
        db = DbUtils.create(this);
        String[] data = null;
        // 查询出数据库中的tag
        try
        {
            List<TargetEntity> tags = db.findAll(TargetEntity.class);
            if (null != tags && tags.size() != 0)
            {
                for (TargetEntity entity : tags)
                {
                    showTags.add(entity.getTag());
                }
                data = showTags.toArray(new String[] {});

                mDefaultTagGroup.setTags(data);
                mSmallTagGroup.setTags(data);
                mLargeTagGroup.setTags(data);
                mBeautyTagGroup.setTags(data);
                mBeautyInverseTagGroup.setTags(data);
                MyTagGroupOnClickListener tgClickListener = new MyTagGroupOnClickListener();
                
                mDefaultTagGroup.setOnClickListener(tgClickListener);
                mSmallTagGroup.setOnClickListener(tgClickListener);
                mLargeTagGroup.setOnClickListener(tgClickListener);
                mBeautyTagGroup.setOnClickListener(tgClickListener);
                mBeautyInverseTagGroup.setOnClickListener(tgClickListener);
                
                mDefaultTagGroup.setOnTagClickListener(mTagClickListener);
                mSmallTagGroup.setOnTagClickListener(mTagClickListener);
                mLargeTagGroup.setOnTagClickListener(mTagClickListener);
                mBeautyTagGroup.setOnTagClickListener(mTagClickListener);
                mBeautyInverseTagGroup.setOnTagClickListener(mTagClickListener);
            }
        }
        catch (DbException e)
        {
            e.printStackTrace();
        }
        tvPrompt.setVisibility((data == null || data.length == 0) ? View.VISIBLE : View.GONE);
        tvPrompt.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                launchTagEditorActivity();
            }
        });
    }
    
    @Override
    public void bindClick(int viewId)
    {
        
    }
    
    protected void launchTagEditorActivity()
    {
        Intent intent = new Intent(TargetGroupActivity.this, TagEditorActivity.class);
        startActivity(intent);
    }
    
    class MyTagGroupOnClickListener implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            launchTagEditorActivity();
        }
    }
}
