package com.nate.loveframe.ui.fragment.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by Nate on 2015/8/12. 整个应用中所有Fragment的基类，里面类似BaseActivity集成了很多公用的方法 提高代码的可读性
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        initListener();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    //初始化话数据
    protected abstract void initData();

    //初始化监听事件
    protected abstract void initListener();
}
