package com.nate.loveframe.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nate.loveframe.R;
import com.nate.loveframe.ui.fragment.base.BaseFragment;

/**
 * Created by Nate on 2015/8/12. FragmentD For Test
 */
public class FragmentD extends BaseFragment
{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_d_layout, container, false);
        return view;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    public void onClick(View v) {

    }
}
