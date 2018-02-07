package com.example.houwei.hwshop;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.houwei.hwshop.activity.BaseActivity;
import com.example.houwei.hwshop.appbar.CommonAppBar;
import com.example.houwei.hwshop.bean.Tab;
import com.example.houwei.hwshop.fragment.CardbagFragment;
import com.example.houwei.hwshop.fragment.HomeFragment;
import com.example.houwei.hwshop.fragment.MineFragment;
import com.example.houwei.hwshop.utils.CommonToastUtils;
import com.example.houwei.hwshop.widget.FragmentTabHost;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends BaseActivity {

 
    private LayoutInflater mInflater;
    private FragmentTabHost mTabhost;
    private List<Tab> mTabs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initTab();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    public void setTopBar() {
        CommonAppBar toolbar = getAppBar();
//        toolbar.setVisibility(View.GONE);
        CoordinatorLayout.LayoutParams params = new CoordinatorLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setBehavior(new AppBarLayout.Behavior());
        params.height = 0;
        toolbar.setLayoutParams(params);

    }

    @Override
    public void removeAppBar() {

        super.removeAppBar();
    }



    private void initTab() {
        Tab tab_home = new Tab(HomeFragment.class, R.string.home, R.drawable.selector_icon_home);
        Tab tab_cardbag = new Tab(CardbagFragment.class, R.string.cardbag, R.drawable.selector_icon_cart);
        Tab tab_mine = new Tab(MineFragment.class, R.string.mine, R.drawable.selector_icon_mine);

        mTabs.add(tab_home);
        mTabs.add(tab_cardbag);
        mTabs.add(tab_mine);

        mInflater = LayoutInflater.from(this);
        mTabhost = (FragmentTabHost) this.findViewById(android.R.id.tabhost);
        mTabhost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        for (Tab tab : mTabs) {

            TabHost.TabSpec tabSpec = mTabhost.newTabSpec(getString(tab.getTitle()));

            tabSpec.setIndicator(buildIndicator(tab));

            mTabhost.addTab(tabSpec, tab.getFragment(), null);

        }
        mTabhost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);
        mTabhost.setCurrentTab(0);
    }

    private View buildIndicator(Tab tab) {


        View view = mInflater.inflate(R.layout.tab_indicator, null);
        ImageView img = (ImageView) view.findViewById(R.id.icon_tab);
        TextView text = (TextView) view.findViewById(R.id.txt_indicator);

        img.setBackgroundResource(tab.getIcon());
        text.setText(tab.getTitle());
        return view;
    }

    @Override
    public void onBackPressed() {
        CommonToastUtils.exitClient(getActivity(), true);
    }

}
