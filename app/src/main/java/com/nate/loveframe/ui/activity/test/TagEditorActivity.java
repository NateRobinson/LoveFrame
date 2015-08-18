package com.nate.loveframe.ui.activity.test;

import java.util.ArrayList;
import java.util.List;

import me.gujun.android.taggroup.TagGroup;

import android.widget.Button;

import butterknife.Bind;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.exception.DbException;
import com.nate.loveframe.R;
import com.nate.loveframe.entity.TargetEntity;
import com.nate.loveframe.ui.activity.base.BaseActivity;

/**
 * tag编辑页面
 */
public class TagEditorActivity extends BaseActivity
{
    @Bind(R.id.tag_group)
    TagGroup tagGroup;
    
    @Bind(R.id.saveBtn)
    Button saveBtn;
    
    private List<String> showTags = new ArrayList<>();
    
    private DbUtils db;
    
    @Override
    public void initContentLayout()
    {
        setContentView(R.layout.activity_tag_editor);
    }
    
    @Override
    public void initListener()
    {
        saveBtn.setOnClickListener(this);
    }
    
    @Override
    public void initData()
    {
        setCustomToolbar(ToolbarType.NOMENU, "添加Tag");
        db = DbUtils.create(this);
        String[] data = null;
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
                tagGroup.setTags(data);
            }
        }
        catch (DbException e)
        {
            e.printStackTrace();
        }
    }
    
    @Override
    public void bindClick(int viewId)
    {
        if (viewId == R.id.saveBtn)
        {
            tagGroup.submitTag();
            save();
        }
    }
    
    /**
     * 保存数据
     */
    private void save()
    {
        try
        {
            db.deleteAll(TargetEntity.class);
            String mtags[] = tagGroup.getTags();
            if (null != mtags && mtags.length > 0)
            {
                for (int i = 0; i < mtags.length; i++)
                {
                    TargetEntity entity = new TargetEntity(mtags[i]);
                    db.save(entity);
                }
            }
        }
        catch (DbException e)
        {
            e.printStackTrace();
        }
    }
    
}