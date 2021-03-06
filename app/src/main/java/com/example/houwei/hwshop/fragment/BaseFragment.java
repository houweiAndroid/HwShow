package com.example.houwei.hwshop.fragment;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.houwei.hwshop.EventMessage.Event;
import com.example.houwei.hwshop.PermissionCallBack;
import com.example.houwei.hwshop.R;
import com.example.houwei.hwshop.activity.BaseActivity;
import com.example.houwei.hwshop.appbar.CommonAppBar;
import com.example.houwei.hwshop.utils.EventBusUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * 为了实现代码统一， 不至于你看不懂我的代码，所有界面都继承BaseFragment， 子类实现getLayoutResId()、
 * initView()、initData()、setTopBar()来处理所有事件。
 * @author zm
 *
 */
public abstract class BaseFragment extends Fragment {
	
	private View rootView = null;
	private int requestCode;
	private PermissionCallBack callBack;

	public View getRootView() {
		return rootView;
	}
	protected Unbinder unbinder;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		if (isRegisterEventBus()) {
			EventBusUtils.register(this);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
		rootView = inflater.inflate(getLayoutResId(), container, false);
		ButterKnife.bind(this,rootView);//绑定framgent
		rootView.setBackgroundResource(R.color.bg);
		rootView.setClickable(true);
		initView();
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initData(savedInstanceState);
	}

	/**
	 * 是否注册事件分发
	 *
	 * @return true绑定EventBus事件分发，默认不绑定，子类需要绑定的话复写此方法返回true.
	 */
	protected boolean isRegisterEventBus() {
		return false;
	}

	@Subscribe(threadMode = ThreadMode.MAIN)
	public void onEventBusCome(Event event) {
		if (event != null) {
			receiveEvent(event);
		}
	}

	@Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
	public void onStickyEventBusCome(Event event) {
		if (event != null) {
			receiveStickyEvent(event);
		}
	}

	/**
	 * 接收到分发到事件
	 *
	 * @param event 事件
	 */
	protected void receiveEvent(Event event) {

	}

	/**
	 * 接受到分发的粘性事件
	 *
	 * @param event 粘性事件
	 */
	protected void receiveStickyEvent(Event event) {

	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (isRegisterEventBus()) {
			EventBusUtils.unregister(this);
		}
	}

	/**
	 * 创建界面布局resId
	 * @return 布局resId
	 */
	public abstract int getLayoutResId(); 

	/**
	 *  初始化界面
	 */
	public void initView(){

	} 

	/**
	 * 初始化界面数据
	 */
	public void initData(Bundle savedInstanceState) {

	}

	/**
	 * 返回堆栈Fragment时，如果刷新界面，实现该方法就行。
	 * @param fromTagBack 从哪个Tag返回过来，为空代表前一个，其它则是那个Tag。
	 */
	public void onBackStackChanged(String fromTagBack){
		
	}

	/**
	 * 设置setTopBar 在Activity顶部导航栏
	 */
	public void setTopBar(){
		getAppBar().removeAllMenu();
	}
	
	/**
	 * 用于Fragment接受返回事件
	 * @return true Fragment处理返回事件，false 未处理返回事件。
	 */
	public boolean onBackPressed(){
		return false;
	}

	/**
	 * 获取公用ToolBar
	 * @return ToolBar
	 */
	public CommonAppBar getAppBar() {
		return getBaseActivity().getAppBar();
	}

	/**
	 * 添加头部组件
	 * @param view
     */
	public void addAppBar(View view) {
		getBaseActivity().addAppBar(view);
	}

	/**
	 * 删除头部组件
	 */
	public void removeAppBar(){
		getBaseActivity().removeAppBar();
	}

	/**
	 * 添加Fragment
	 * @param fragment
	 */
	public void addFragment(Fragment fragment){
		getBaseActivity().addFragment(fragment);
	}
	
	/**
	 * 返回前一堆栈Fragment
	 */
	public void backStackFragment() {
		getBaseActivity().backStackFragment();
	}

	/**
	 * 通过tag返回堆栈Fragment，tag本身也会销毁。
	 * @param className
	 */
	public void backStackFragment(String className) {
		getBaseActivity().backStackFragment(className);
	}
	
	/**
	 * 通过Id查找View， 为了和Activity统一。
	 * @param id
	 * @return view
	 */
	public View findViewById(int id) {
		if (getRootView() == null) {
			return null;
		}
		return rootView.findViewById(id);
	}
	
	@Override
	public void onDestroyView() {
		super.onDestroyView();
		rootView = null;
		getBaseActivity().hideInput();
		if (this.unbinder != null) {
			this.unbinder.unbind();
		}

	}
	
	/**
	 * 获取字符串数组
	 * @param resid
	 * @return
	 */
	public String[] getStringArray(int resid){
		return getResources().getStringArray(resid);
	}
	
	/**
	 * 获取BaseActivity
	 * @return
	 */
	public BaseActivity getBaseActivity(){
		return (BaseActivity)getActivity();
	}
	
	/**
	 * 获取布局
	 * @param resid
	 * @return
	 */
	public View getLayoutInflater(int resid) {
		return LayoutInflater.from(getActivity()).inflate(resid, null);
	}

	public void verifyPermissions(Activity activity, String permission, String[] reqestPermissions, int requestCode, PermissionCallBack callBack) {
		this.requestCode = requestCode;
		this.callBack = callBack;
		int checkPermission = ActivityCompat.checkSelfPermission(activity, permission);
		if (checkPermission != PackageManager.PERMISSION_GRANTED) {
			ActivityCompat.requestPermissions(
					activity,
					reqestPermissions,
					requestCode
			);
		}else {
			callBack.onGranted();
		}
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if(this.requestCode == requestCode){
			if (grantResults != null && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
				// Permission Granted
				callBack.onGranted();
			} else {
				// Permission Denied
				callBack.onDenied();
			}
		}
	}

	public void setStatusBarColor(int colorId) {
		getBaseActivity().setStatusBarColor(colorId);
	}

	public void setStatusBarResource(int colorId) {
		getBaseActivity().setStatusBarResource(colorId);
	}

	public int getColorValue(int color) {
		return ContextCompat.getColor(getActivity(), color);
	}

	public void setBackgroundResource(int resid){
		rootView.setBackgroundResource(resid);
	}

	public void setBackgroundColor(int resid){
		rootView.setBackgroundColor(resid);
	}

}
