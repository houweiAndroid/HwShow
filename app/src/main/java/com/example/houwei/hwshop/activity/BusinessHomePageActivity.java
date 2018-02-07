package com.example.houwei.hwshop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.houwei.hwshop.EventMessage.Event;
import com.example.houwei.hwshop.EventMessage.OtherMessage;
import com.example.houwei.hwshop.R;
import com.example.houwei.hwshop.app.C;
import com.example.houwei.hwshop.appbar.CommonAppBar;
import com.example.houwei.hwshop.bean.News;
import com.example.houwei.hwshop.bean.User;
import com.example.houwei.hwshop.widget.CircleBarView;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BusinessHomePageActivity extends BaseActivity {


    @BindView(R.id.btn)
    Button mBtn;
    @BindView(R.id.tv)
    TextView mTv;
    @BindView(R.id.circle_view)
    CircleBarView circleView;


    @Override
    public int getLayoutResId() {
        return R.layout.activity_business_home_page;
    }

    @Override
    public void initView() {
        super.initView();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    protected void receiveEvent(Event event) {
        // 接受到Event后的相关逻辑
        switch (event.getCode()) {
            case C.EventCode.A:
                Log.d("EventBus", "接收到A类型的Event");
                break;
            case C.EventCode.B:
                Log.d("EventBus", "接收到B类型的Event");
                break;
            case C.EventCode.C:
                Log.d("EventBus", "接收到B类型的Event，携带User");
                User user = (User) event.getData();
                break;
            case C.EventCode.D:
                Log.d("EventBus", "接收到D类型的Event，携带News");
                News news = (News) event.getData();
                break;
        }
    }

    @Override
    public void setTopBar() {
        super.setTopBar();
        CommonAppBar appBar = getAppBar();
        appBar.setTitle("店铺详情");

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }


    @OnClick(R.id.btn)
    public void onViewClicked() {
        EventBus.getDefault().post(new OtherMessage("MessageEvent发布SecondActivity消息了"));
        Intent intent = new Intent(BusinessHomePageActivity.this, SecondActivity.class);
        startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
