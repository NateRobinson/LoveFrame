package com.nate.loveframe.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;

import com.nate.loveframe.R;
import com.nate.loveframe.ui.activity.test.ActionSheetActivity;
import com.nate.loveframe.ui.activity.test.OverlayMenuActivity;
import com.nate.loveframe.ui.activity.test.PhotoSelectTestActivity;
import com.nate.loveframe.ui.activity.test.SweetDialogActivity;
import com.nate.loveframe.ui.fragment.base.BaseFragment;

/**
 * Created by Nate on 2015/8/12. FragmentA For Test
 */
public class FragmentA extends BaseFragment
{
    
    @Bind(R.id.bottomMenu)
    Button bottomMenu;
    
    @Bind(R.id.overlayMenu)
    Button overlayMenu;
    
    @Bind(R.id.sweetdialogMenu)
    Button sweetdialogMenu;
    @Bind(R.id.photoSelectMenu)
    Button photoSelectMenu;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_a_layout, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
    
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        initListener();
    }
    
    @Override
    protected void initData()
    {
    
    }
    
    @Override
    protected void initListener()
    {
        bottomMenu.setOnClickListener(this);
        overlayMenu.setOnClickListener(this);
        sweetdialogMenu.setOnClickListener(this);
        photoSelectMenu.setOnClickListener(this);
    }
    
    @Override
    public void onClick(View v)
    {
        Intent intent = new Intent();
        switch (v.getId())
        {
            case R.id.bottomMenu:
                intent.setClass(getActivity(), ActionSheetActivity.class);
                startActivity(intent);
                break;
            case R.id.overlayMenu:
                intent.setClass(getActivity(), OverlayMenuActivity.class);
                startActivity(intent);
                break;
            case R.id.sweetdialogMenu:
                intent.setClass(getActivity(), SweetDialogActivity.class);
                startActivity(intent);
                break;
            case R.id.photoSelectMenu:
                intent.setClass(getActivity(), PhotoSelectTestActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
    
}
