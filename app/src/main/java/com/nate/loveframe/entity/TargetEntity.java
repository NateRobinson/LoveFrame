package com.nate.loveframe.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Nate on 2015/8/18. 用来进行targetgroup测试的实体类
 */
public class TargetEntity implements Parcelable
{
    private int id;
    
    private String tag;
    
    public String getTag()
    {
        return tag;
    }
    
    public void setTag(String tag)
    {
        this.tag = tag;
    }
    
    public int getId()
    {
        return id;
    }
    
    public void setId(int id)
    {
        this.id = id;
    }
    
    @Override
    public int describeContents()
    {
        return 0;
    }
    
    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeInt(this.id);
        dest.writeString(this.tag);
    }
    
    public TargetEntity()
    {
    }
    
    public TargetEntity(String tag)
    {
        this.tag = tag;
    }
    
    protected TargetEntity(Parcel in)
    {
        this.id = in.readInt();
        this.tag = in.readString();
    }
    
    public static final Parcelable.Creator<TargetEntity> CREATOR = new Parcelable.Creator<TargetEntity>()
    {
        public TargetEntity createFromParcel(Parcel source)
        {
            return new TargetEntity(source);
        }
        
        public TargetEntity[] newArray(int size)
        {
            return new TargetEntity[size];
        }
    };
}
