package com.example.tj.qq.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.tj.qq.R;

import java.util.Objects;

public class MainFragment extends Fragment implements BottomNavigationBar.OnTabSelectedListener {

    private View mainView;
    private Fragment[] fragments = new Fragment[]{new MessageFragment(), new ContactFragment(), new WatchFragment(), new RecentFragment()};


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView = View.inflate(getActivity(), R.layout.fragment_main, null);
        initView();
        return mainView;
    }

    private void initView() {

        Objects.requireNonNull(getFragmentManager()).beginTransaction().add(R.id.fl_content, fragments[0]).commit();

        BottomNavigationBar bottomNavigationBar = mainView.findViewById(R.id.bottom_navigation_bar);

        bottomNavigationBar.setTabSelectedListener(this)
                .setMode(BottomNavigationBar.MODE_FIXED)
                /**
                 *  setMode() 内的参数有三种模式类型：
                 *  MODE_DEFAULT 自动模式：导航栏Item的个数<=3 用 MODE_FIXED 模式，否则用 MODE_SHIFTING 模式
                 *  MODE_FIXED 固定模式：未选中的Item显示文字，无切换动画效果。
                 *  MODE_SHIFTING 切换模式：未选中的Item不显示文字，选中的显示文字，有切换动画效果。
                 */
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_DEFAULT)
                /**
                 *  setBackgroundStyle() 内的参数有三种样式
                 *  BACKGROUND_STYLE_DEFAULT: 默认样式 如果设置的Mode为MODE_FIXED，将使用BACKGROUND_STYLE_STATIC
                 *                                    如果Mode为MODE_SHIFTING将使用BACKGROUND_STYLE_RIPPLE。
                 *  BACKGROUND_STYLE_STATIC: 静态样式 点击无波纹效果
                 *  BACKGROUND_STYLE_RIPPLE: 波纹样式 点击有波纹效果
                 */
                .setActiveColor("#6F564B") //选中颜色
                .setInActiveColor("#ABADBB") //未选中颜色
                .setBarBackgroundColor("#FFFFFF");//导航栏背景色


        bottomNavigationBar//
                .addItem(new BottomNavigationItem(R.mipmap.icon_message, "消息"))//
                .addItem(new BottomNavigationItem(R.mipmap.icon_contact, "联系人"))//
                .addItem(new BottomNavigationItem(R.mipmap.icon_watch, "看点"))//
                .addItem(new BottomNavigationItem(R.mipmap.icon_recent, "动态"))//
                .setFirstSelectedPosition(0)//
                .initialise();


    }

    @Override
    public void onTabSelected(int position) {
        Objects.requireNonNull(getFragmentManager())//
                .beginTransaction()//
                .replace(R.id.fl_content, fragments[position])//
                .commit();
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
}
