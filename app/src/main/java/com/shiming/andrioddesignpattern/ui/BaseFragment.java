package com.shiming.andrioddesignpattern.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.shiming.andrioddesignpattern.R;


/**
 * Created by shiming on 2015/9/29.
 *
 *  你来打我啊
 */
public abstract class BaseFragment extends Fragment {
    private View titleBarLayout;
    private FrameLayout containerLayout;
    private boolean isHidden;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //此baseFramgent的根布局，其子类的布局将添加到此布局的containerLayout当中
        View rootView = inflater.inflate(R.layout.library_base_layout, container, false);
        titleBarLayout = rootView.findViewById(R.id.lib_titlebar_layout);
        containerLayout = (FrameLayout) rootView.findViewById(R.id.lib_container_layout);
        //子类的布局，将添加到根布局中当
        View contentView = createView(inflater, container, savedInstanceState);
        if (contentView != null) {
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
            containerLayout.addView(contentView, lp);
        }
        showTitleBar(false);
        return rootView;
    }

    public void setTitleBarBackground(int color){
        titleBarLayout.setBackgroundColor(color);
    }



    protected void showTitleBar(boolean isShow) {
        if (isShow) {
            titleBarLayout.setVisibility(View.VISIBLE);
        } else {
            titleBarLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        InputMethodManager manager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (getActivity().getCurrentFocus() != null) {
            manager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
        if (!isHidden) {
            onPauseFragment();
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        if (!isHidden) {
            onResumeFragment();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (!isHidden) {
            onStopFragment();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!isHidden) {
            onStartFragment();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        onDestroyFragment();
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        isHidden = hidden;
        if (hidden) {
            onPauseFragment();
            onStopFragment();
        } else {
            onStartFragment();
            onResumeFragment();
        }
        Log.d("BaseFragment", getClass().getSimpleName() + "---------onHinddenChanged");
    }


    protected void showTitleBarUnderline(boolean showUnderline){
        titleBarLayout.findViewById(R.id.titlebar_underline).setVisibility(showUnderline? View.VISIBLE : View.GONE);
    }


    protected void showRedDot(boolean isShow) {
        if (isShow) {
            titleBarLayout.findViewById(R.id.shiming_lib_titlebar_right_red_dot).setVisibility(View.VISIBLE);
        } else {
            titleBarLayout.findViewById(R.id.shiming_lib_titlebar_right_red_dot).setVisibility(View.GONE);
        }
    }


    public abstract View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    public  void onStartFragment(){};

    public void onResumeFragment(){};

    public void onPauseFragment(){};

    public  void onStopFragment(){};

    public void onDestroyFragment(){};


}
