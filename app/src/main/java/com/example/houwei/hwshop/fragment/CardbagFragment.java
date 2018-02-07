package com.example.houwei.hwshop.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.example.houwei.hwshop.R;
import com.example.houwei.hwshop.adapter.ExamplePagerAdapter;
import com.example.houwei.hwshop.appbar.CommonAppBar;
import com.example.houwei.hwshop.bean.HeroBean;
import com.example.houwei.hwshop.constant.Urls;
import com.example.houwei.hwshop.widget.ColorFlipPagerTitleView;
import com.okhttplib.HttpInfo;
import com.okhttplib.OkHttpUtil;
import com.okhttplib.annotation.RequestType;
import com.okhttplib.callback.Callback;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;


/**
 * Created by houwei on 2017/6/2.
 */

public class CardbagFragment extends BaseFragment {


    @BindView(R.id.magic_indicator)
    MagicIndicator mMagicIndicator;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;


    //    private static final String[] CHANNELS = new String[]{"诸葛亮", "安其拉", "白起", "妲己", "狄仁杰", "典韦", "韩信", "老夫子", "刘邦", "刘禅", "鲁班七号"};
    private static final List<HeroBean> mDataList = new ArrayList<HeroBean>() {
    };

    private ExamplePagerAdapter mExamplePagerAdapter;


    @Override
    public int getLayoutResId() {
        return R.layout.fragment_cardbag;
    }


    @Override
    public void initView() {
        super.initView();


        HeroBean heroBean1 = new HeroBean("嬴政", "天上天下，唯朕独尊!");
        HeroBean heroBean2 = new HeroBean("杨戬", "尽情驰骋吧，纵使天地也太狭小!与其受制于人，不妨听命自己。");
        HeroBean heroBean3 = new HeroBean("吕布", "从此刻开始，战场由我一人主宰!可有人敢与我一战!");
        HeroBean heroBean4 = new HeroBean("赵云", "勇者之誓，甚于生死!心怀不惧，方能翱翔于天际!");
        HeroBean heroBean5 = new HeroBean("哪咤", "不能击败我的，会让我更强大!");
        HeroBean heroBean6 = new HeroBean("宫本武藏", "天下无双!告诉你个秘密：我，是无敌的!");
        HeroBean heroBean7 = new HeroBean("张飞", "修身，养性。心有猛虎。百万军中，取人首级如探囊取物!");
        HeroBean heroBean8 = new HeroBean("曹操", "宁教我负天下人!力量也会臣服于我!");
        HeroBean heroBean9 = new HeroBean("典韦", "身体里沉睡的野兽，觉醒了!");
        HeroBean heroBean10 = new HeroBean("白起", "身在黑暗，心向光明!");
        HeroBean heroBean11 = new HeroBean("刘备", "除暴安良是责任，行善积德是兴趣!出来混，最重要的是讲义气!");
        HeroBean heroBean12 = new HeroBean("雅典娜", "正视你的邪恶!畏惧信仰!畏惧力量!");
        HeroBean heroBean13 = new HeroBean("亚瑟", "永不背叛!王者背负，王者审判，王者不可阻挡!");
        HeroBean heroBean14 = new HeroBean("钟馗", "维持秩序!吾之内涵，有容乃大!吾之身躯，无欲则刚!");


        mDataList.add(heroBean1);
        mDataList.add(heroBean2);
        mDataList.add(heroBean3);
        mDataList.add(heroBean4);
        mDataList.add(heroBean5);
        mDataList.add(heroBean6);
        mDataList.add(heroBean7);
        mDataList.add(heroBean8);
        mDataList.add(heroBean9);
        mDataList.add(heroBean10);
        mDataList.add(heroBean11);
        mDataList.add(heroBean12);
        mDataList.add(heroBean13);
        mDataList.add(heroBean14);

        mExamplePagerAdapter = new ExamplePagerAdapter(mDataList);
        mViewPager.setAdapter(mExamplePagerAdapter);
        initMagicIndicator7();
    }

    private void initMagicIndicator7() {
        mMagicIndicator.setBackgroundColor(Color.parseColor("#fafafa"));
        CommonNavigator commonNavigator7 = new CommonNavigator(getActivity());
        commonNavigator7.setScrollPivotX(0.65f);
        commonNavigator7.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mDataList == null ? 0 : mDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ColorFlipPagerTitleView(context);
                simplePagerTitleView.setText(mDataList.get(index).getName());
                simplePagerTitleView.setNormalColor(Color.parseColor("#9e9e9e"));
                simplePagerTitleView.setSelectedColor(Color.parseColor("#00c853"));
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mViewPager.setCurrentItem(index);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                indicator.setLineHeight(UIUtil.dip2px(context, 6));
                indicator.setLineWidth(UIUtil.dip2px(context, 10));
                indicator.setRoundRadius(UIUtil.dip2px(context, 3));
                indicator.setStartInterpolator(new AccelerateInterpolator());
                indicator.setEndInterpolator(new DecelerateInterpolator(2.0f));
                indicator.setColors(Color.parseColor("#00c853"));
                return indicator;
            }
        });
        mMagicIndicator.setNavigator(commonNavigator7);
        ViewPagerHelper.bind(mMagicIndicator, mViewPager);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
//        async();
    }

    /**
     * 异步请求：回调方法可以直接操作UI
     */
    private void async() {
        Map<String, String> params = new HashMap<>();
        params.put("page", "1");
        params.put("nextPage", "10");
        params.put("areaName", "深圳市");
        params.put("lat", "22.546800");
        params.put("lng", "114.113800");
        OkHttpUtil.getDefault(this).doAsync(

                HttpInfo.Builder()
                        .setUrl(Urls.GET_SHOP_DATA)
                        .setRequestType(RequestType.GET)//设置请求方式
//                        .addHead("head", "test")//添加头参数
//                        .addParam("param", "test")//添加接口参数
                        .addParams(params)
//                        .setDelayExec(2, TimeUnit.SECONDS)//延迟2秒执行
                        .build(),
                new Callback() {
                    @Override
                    public void onFailure(HttpInfo info) throws IOException {
                        String result = info.getRetDetail();
//                        resultTV.setText("异步请求失败：" + result);
                    }

                    @Override
                    public void onSuccess(HttpInfo info) throws IOException {
                        String result = info.getRetDetail();
//                        resultTV.setText("异步请求成功：" + result);
                        //GSon解析
//                        TimeAndDate time = new Gson().fromJson(result, TimeAndDate.class);
//                        LogUtil.d("MainActivity", time.getResult().toString());
//                        setFromCacheTV(info);
                    }
                });
//        needDeleteCache(true);
    }

    @Override
    public void setTopBar() {
        super.setTopBar();
        CommonAppBar toolbar = getAppBar();
        toolbar.setTitle("卡包");
    }


}
