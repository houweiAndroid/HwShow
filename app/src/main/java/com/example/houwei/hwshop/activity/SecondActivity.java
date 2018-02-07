package com.example.houwei.hwshop.activity;

import android.os.Bundle;
import android.os.Looper;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.example.houwei.hwshop.EventMessage.Event;
import com.example.houwei.hwshop.R;
import com.example.houwei.hwshop.app.C;
import com.example.houwei.hwshop.bean.News;
import com.example.houwei.hwshop.bean.User;
import com.example.houwei.hwshop.utils.EventBusUtils;

/**
 * 作者： lcw on 2016/7/4.
 * 博客： http://blog.csdn.net/lsyz0021/
 */
public class SecondActivity extends FragmentActivity {

	private TextView tv_text1;
	private TextView tv_text2;
	private Button btn_send;
	public Button btn_open;
	private String tag = " SecondActivity  ";
	static Demo sDemo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		if (sDemo == null) {
		       sDemo = new Demo();
			      }
		        finish();

//		tv_text1 = (TextView) findViewById(R.id.tv_secondActivity_message1);
//		tv_text2 = (TextView) findViewById(R.id.tv_secondActivity_message2);

//		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//		ft.add(R.id.fl_second, new FristFragment());
//		ft.commit();

	}
	class Demo {
    }
	public void sendEventA(View view) {
		EventBusUtils.post(new Event(C.EventCode.A));
	}

	public void sendEventB(View view) {
		EventBusUtils.post(new Event(C.EventCode.B));
	}

	public void sendEventC(View view) {
		Event<User> event = new Event<>(C.EventCode.C, new User());
		EventBusUtils.post(event);
	}


	public void sendEventD(View view) {
		Event<News> event = new Event<>(C.EventCode.D, new News());
		EventBusUtils.post(event);
	}




	@Override
	protected void onStart() {
		super.onStart();

	}

	@Override
	protected void onStop() {
		super.onStop();

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		System.out.println("SecondActivty退出了");
	}
//
//	/**
//	 * 事件从哪个线程发布出来的，onMessageEventPost()方法就会在该线程中运行，
//	 * <p/>
//	 * 如果发送事件的线程是UI线程，则在UI线程执行，
//	 * 如果发送事件的线程是子线程，则在该子线程中执行
//	 */
//	@Subscribe(threadMode = ThreadMode.POSTING, priority = 2, sticky = true)
//	public void onMessageEventPost(MyEvent.Message event) {
//		if (isMainThread()) {
//			tv_text2.setText(event.msg);
//			LogUtils.v(tag, event.msg + " priority = 2 ……UI线程 POSTING id = " + Thread.currentThread().getId());
//		} else {
//			LogUtils.v(tag, event.msg + " priority = 2 ……非UI线程 POSTING id = " + Thread.currentThread().getId());
//		}
//	}
//
//	/**
//	 * 判断当前线程是否为主线程
//	 */
	public boolean isMainThread() {

		return Looper.myLooper() == Looper.getMainLooper();
	}

}
