package com.example.houwei.hwshop.fragment;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;


import com.example.houwei.hwshop.R;
import com.example.houwei.hwshop.app.App;
import com.example.houwei.hwshop.utils.PrintUtil;
import com.example.houwei.hwshop.utils.Util;
import com.example.houwei.hwshop.widget.KeyBoard;
import com.example.houwei.hwshop.widget.MoneyTextView;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.houwei.hwshop.app.App.SCREEN_WIDTH;

/**
 * 收银台
 *
 * @author feng
 *
 */
public class ConsumeFragment extends Fragment implements OnClickListener {

	private KeyBoard mKeyBoard;
	private MoneyTextView showMoneyView;// mode1视图
	private TextView moneyView;// mode2视图
	private ListView mListView;// mode3视图
	private LinearLayout payInfoLayout;// mode3视图
	private TextView xfjeItemText, yhjeItemText, realAmtItemText;// 消费金额，优惠金额，应付金额
	private TextView xfjeValueText, yhjeValueText, realAmtValueText;
	private TextView changeClearAmt;// 改价抹零
//	private ShowPayListAdapter payAdapter;
//	private List<PayListModel> payList;

	private ScrollView scrollLayout;
	private LinearLayout scrollNumLayout;

	private String payAmount;

	private View.OnClickListener changeAmtListener;

//	private PayModelAnylize payModelAnylize;
//	private LoadDialog dialog;
	private int curtaskid;// 任务id
	private int currPayType;// 消费类型
	private int scrollLayoutHeight;

	private final String curActivityName = "ConsumeFragment";
	public static final int COMM_PAY_TYPE = 1;

	public static final int THREAD_PAY_MODE = 1;

	protected Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
				case 0x3333: {
//					doTitle();
//				Boolean if_open = (Boolean) msg.obj;
//				if (null != curfragment) {
//					curfragment.doSetTitle(if_open);// ��ǰ��ʾ����fragment���ñ���
//				} else {
//					if (if_open) {
//						if (null != titleInterface) {
//							titleInterface.setBackLeft(null);// ȡ�����8���˰�ť��Ӧ
//							doTitle();// ���ñ���
//						}
//					}
//
//				}
					break;
				}
			}
//			doHandleMessage(msg);
			super.handleMessage(msg);
		}

	};

	// private long latMeasureTime;
//
//	@Override
//	public void doTitle() {
//		if (titleInterface != null) {
//			titleInterface.setTitleTxt(getString(R.string.syt));
//		}
//	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.consume_layout, null);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// titleInterface.setLeftVisiable(false);
//		titleInterface.setTitleBgColor(getResources().getColor(
//				R.color.syt_bg_color));

		PrintUtil.printMsg("--------------------zhehe","sdsadas");
		mKeyBoard = new KeyBoard(getActivity(), mHandler);
		showMoneyView = (MoneyTextView) view.findViewById(R.id.showMoneyView);
		moneyView = (TextView) view.findViewById(R.id.moneyView);
		scrollLayout = (ScrollView) view.findViewById(R.id.scrollLayout);
		scrollNumLayout = (LinearLayout) view.findViewById(R.id.showNumLayout);
		mListView = (ListView) view.findViewById(R.id.mListView);
		payInfoLayout = (LinearLayout) view.findViewById(R.id.payInfoLayout);
		xfjeItemText = (TextView) view.findViewById(R.id.xfjeItemText);
		xfjeValueText = (TextView) view.findViewById(R.id.xfjeValueText);
		yhjeItemText = (TextView) view.findViewById(R.id.yfjeItemText);
		yhjeValueText = (TextView) view.findViewById(R.id.yfjeValueText);
		realAmtItemText = (TextView) view.findViewById(R.id.realAmtItemText);
		realAmtValueText = (TextView) view.findViewById(R.id.realAmtValueText);
		changeClearAmt = (TextView) view.findViewById(R.id.changeClearAmt);

		mKeyBoard.setKeyBoardEdit(showMoneyView, moneyView, 12, false);
		mKeyBoard.initKeyBoard(view, this);

		// titleInterface.setRightTxt(getString(R.string.change_currency),
		// changeCurrencyListener);
//		String chooseCurrency = CacheUtils.getString(getActivity(),
//				Constant.CHOOSE_CURRENCY, "");
//		if (chooseCurrency.length() > 0) {
//			String currencyItems[] = chooseCurrency.split("<br>");
//			if (currencyItems.length > 1) {
//				titleInterface.setRightTxt(getString(R.string.change_currency),
//						changeCurrencyListener);
//			} else {
//				titleInterface.setRightVisiable(false);
//			}
//		}

		initData();
		fitAllScreen(view);

		// 恢复默认币种
//		CacheUtils.putString(getActivity(), Constant.CURRENCY_SOMETIME, "");
//
//		PosService.sendCancelLock = true;
//		PosService.sendLock = true;

		super.onViewCreated(view, savedInstanceState);
	}

	private void initData() {

//		payList = new ArrayList<PayListModel>();
//
//		currPayType = COMM_PAY_TYPE;
////
//		payModelAnylize = new PayModelAnylize(getActivity(), curActivityName,
//				handler);

		mKeyBoard.setKeyListener(new KeyBoard.KeyOtherListenr() {

			@Override
			public void onKeySure(double amount) {
				// switchToFragment(1,null);
				// payMoney(amount + "", null, null);
				// openDialog("请稍后");
				payAmount = amount + "";
//				if (Double.parseDouble(payAmount) <= 9999999.99d) {
//					if (AppData.getInstance().payList != null) {
//						goToPay();
//					} else {
//
//						payMoney(payAmount, null, null);
//						if (dialog != null) {
//							dialog.show();
//						}
//					}
//				} else {
//					UIUtil.showToast(getActivity(),
//							getString(R.string.please_right_amt));
//				}
			}
		});
		showMoneyView.setListener(new MoneyTextView.OnMeasureListener() {

			@Override
			public void onMeasureOk() {
				// if(scrollLayoutHeight==0){
				// scrollLayoutHeight= scrollNumLayout.getMeasuredHeight();
				// }
				//
				//
				// if (System.currentTimeMillis() - latMeasureTime < 10) {
				// return;
				// }
				// latMeasureTime = System.currentTimeMillis();
				//
				// ViewGroup.LayoutParams params =
				// scrollLayout.getLayoutParams();
				// PrintUtil.printMsg("measuredHeight",
				// scrollLayout.getMeasuredHeight()+"");
				// if (showMoneyView.getMeasuredHeight() < scrollLayoutHeight) {
				// if (params.height != LayoutParams.WRAP_CONTENT) {
				// params.height = LayoutParams.WRAP_CONTENT;
				// // contentLayout.requestLayout();
				// scrollLayout.invalidate();
				// }
				// } else {
				// params.height = scrollLayoutHeight;
				scrollLayout.fullScroll(ScrollView.FOCUS_DOWN);
				// }

			}
		});
		showMoneyView.isShowLine(false);
		changeClearAmt.setOnClickListener(this);

//		dialog = new LoadDialog(getActivity(), R.style.dialogstyle2);
//		dialog.setOnCancelListener(new OnCancelListener() {
//
//			@Override
//			public void onCancel(DialogInterface arg0) {
//				// TODO Auto-generated method stub
//
//			}
//		});
	}


	protected void fitAllScreen(View view) {
		if (Util.isTablet(getActivity())) {
			scrollNumLayout.getLayoutParams().height = (int) (getActivity()
					.getWindowManager().getDefaultDisplay().getHeight() * 0.245f);

			// keyborad
			// txtSize=40sp
			// float txtSize = (int) (getActivity().getWindowManager()
			// .getDefaultDisplay().getHeight() * 0.065f);
			float txtSize = (int) (getActivity().getWindowManager()
					.getDefaultDisplay().getHeight() * 0.085f);
			mKeyBoard.setTextSize(txtSize);

		}

		xfjeItemText.measure(0, 0);
		yhjeItemText.measure(0, 0);
		realAmtItemText.measure(0, 0);

//		if (App.mTypeface != null) {
//			xfjeValueText.setTypeface(App.mTypeface);
//			yhjeValueText.setTypeface(App.mTypeface);
//			realAmtValueText.setTypeface(App.mTypeface);
//		}

		int maxItemSize = xfjeItemText.getMeasuredWidth() > yhjeItemText
				.getMeasuredWidth() ? xfjeItemText.getMeasuredWidth()
				: yhjeItemText.getMeasuredWidth();
		maxItemSize = maxItemSize > realAmtItemText.getMeasuredWidth() ? maxItemSize
				: realAmtItemText.getMeasuredWidth();

		maxItemSize += (int) (15 * SCREEN_WIDTH);

		View lineView1 = view.findViewById(R.id.lineView);
		MarginLayoutParams params = (MarginLayoutParams) lineView1
				.getLayoutParams();
		params.leftMargin = maxItemSize;
		lineView1 = view.findViewById(R.id.lineView2);
		params = (MarginLayoutParams) lineView1.getLayoutParams();
		params.leftMargin = maxItemSize;
		lineView1 = view.findViewById(R.id.lineView3);
		params = (MarginLayoutParams) lineView1.getLayoutParams();
		params.leftMargin = maxItemSize;

		changeClearAmt.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
		changeClearAmt.getPaint().setAntiAlias(true);

		// 26sp
		mKeyBoard.setAllClearTxtSize(Util.getTabletTxtSize(getActivity(),
				0.040f));
	}

	/*
	 * 消息回调
	 *
	 * @see
	 * com.ttg.smartphonepos.hzgdbank.base.BaseFragment#doHandleMessage(android.os.Message
	 * )
	 */
//	@Override
//	protected void doHandleMessage(Message msg) {
//		// TODO Auto-generated method stub
//		super.doHandleMessage(msg);
//	}
//
//	@Override
//	public void doTitle() {
//
//	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.changeClearAmt: {
			this.changeAmtListener.onClick(arg0);
			break;
		}
		}
		mKeyBoard.clickKeyBoard(arg0);
	}

	// 管理fragment

//	private PayConsumeFragment payConsumeFragment;
	private Fragment currFragment;

//	public void switchToFragment(int type, Bundle bundle) {
//		FragmentTransaction ft = getActivity().getSupportFragmentManager()
//				.beginTransaction();
//		ft.setCustomAnimations(R.anim.frag_up, R.anim.frag_out);
//
//		switch (type) {
//		case 1: {
//			clearFragment(ft);
//
//			if (payConsumeFragment == null) {
//				payConsumeFragment = new PayConsumeFragment();
//				ft.add(R.id.mContentLayout, payConsumeFragment,
//						"payConsumeFragment");
//			}
//
//			ft.commit();
//			currFragment = payConsumeFragment;
//			break;
//		}
//		}
//
//	}

	private void clearFragment(FragmentTransaction ft) {
		if (currFragment != null) {
			if (currFragment.getTag() != null
					&& "consumeFragment".equals(currFragment.getTag()
							.toString())) {
				ft.hide(currFragment);
			} else {
				ft.remove(currFragment);
			}
		}
	}

//	private Handler handler = new Handler() {
//		public void handleMessage(Message msg) {
//			try {
//				if (null == getActivity()) {
//					return;
//				}
//				switch (msg.what) {
//				case COMM_PAY_TYPE: {
//
//					String money = msg.obj.toString();
//
//					payMoney(money, null, null);
////					if (dialog != null) {
////						dialog.show();
////					}
//
//					break;
//				}
//
//				// case PayModelAnylize.Stop: {
//				// curtaskid = 0;
//				// if (dialog.isShowing()) {
//				// dialog.dismiss();
//				// }
//				// break;
//				// }
//				// case PayModelAnylize.Error: {
//				// curtaskid = 0;
//				// if (dialog.isShowing()) {
//				// dialog.dismiss();
//				// }
//				// if (msg.obj != null) {
//				// String remsg = (String) msg.obj;
//				//
//				// if (NetWorkInfo.isNetAvailable(getActivity(), true)) {
//				// remsg = getString(R.string.conn_err);
//				// } else {
//				// // 无网络
//				// remsg = getString(R.string.net_err);
//				// }
//				// DialogUtil.createTipDialog(remsg,
//				// SelectDialog2.DIALOG_RES_ERR, getActivity(),
//				// handler);
//				//
//				// }
//				// break;
//				// }
//				// // 订单成功回调
//				// case PayModelAnylize.Success: {
//				// curtaskid = 0;
//				// if (dialog.isShowing()) {
//				// dialog.dismiss();
//				// }
//				//
//				// if (msg.obj != null) {
//				// String modelStr = Base64.decode(msg.obj.toString());
//				// PrintUtil.printMsg("model", modelStr);
//				// PayModel model = null;
//				// try {
//				// model = AppData.gson.fromJson(modelStr,
//				// PayModel.class);
//				// } catch (Exception e) {
//				//
//				// }
//				// if (model == null) {
//				//
//				// DialogUtil.createTipDialog(
//				// getString(R.string.returnerror),
//				// SelectDialog2.DIALOG_RES_ERR,
//				// getActivity(), handler);
//				// } else {
//				// if (model.getRet() == 0) {
//				// gotoPay(model);
//				// } else {
//				//
//				// DialogUtil.createTipDialog(model.getMsg(),
//				// SelectDialog2.DIALOG_RES_ERR,
//				// getActivity(), handler);
//				// }
//				// }
//				//
//				// }
//				// break;
//				// }
//				// 收银订单回调
//				case PayModelAnylize.Stop: {
//					curtaskid = 0;
//					if (dialog.isShowing()) {
//						dialog.dismiss();
//					}
//					break;
//				}
//				case PayModelAnylize.Error: {
//					curtaskid = 0;
//					if (dialog.isShowing()) {
//						dialog.dismiss();
//					}
//					if (msg.obj != null) {
//						String remsg = (String) msg.obj;
//
//						if (NetWorkInfo.isNetAvailable(getActivity(), true)) {
//							remsg = getString(R.string.conn_err);
//						} else {
//							// 无网络
//							remsg = getString(R.string.net_err);
//						}
//						DialogUtil.createTipDialog(remsg,
//								SelectDialog2.DIALOG_RES_ERR, getActivity(),
//								handler);
//
//					}
//					break;
//				}
//				case PayModelAnylize.Success: {
//					curtaskid = 0;
//					if (dialog.isShowing()) {
//						dialog.dismiss();
//					}
//
//					if (msg.obj != null) {
//						String modelStr = msg.obj.toString();
//
//						Model model = null;
//						try {
//							Util.printMsg("result", modelStr);
//							model = AppData.gson
//									.fromJson(modelStr, Model.class);
//						} catch (Exception e) {
//
//						}
//						if (model == null) {
//
//							DialogUtil.createTipDialog(
//									getString(R.string.returnerror),
//									SelectDialog2.DIALOG_RES_ERR,
//									getActivity(), handler);
//						} else {
//							if (model.getRet() == 0) {
//
//								String json = AuthManager.checkAuth(
//										getActivity(), model);
//
//								if (json != null) {
//									Type listType = new TypeToken<List<PayTypeData>>() {
//									}.getType();
//									List<PayTypeData> payList = AppData.gson
//											.fromJson(json, listType);
//
//									// for(int i =0;i<payList.size();i++){
//									// if(!PayContent.PAY_TAG_WXTWXG.equals(payList.get(i).getPmt_tag())){
//									// payList.remove(i);
//									// i--;
//									// }
//									// }
//
//									AppData.getInstant().payList = payList;
//									Util.printMsg("jsonParse:",
//											AppData.gson.toJson(payList));
//
//									goToPay();
//								} else {
//									DialogUtil.createTipDialog(
//											getString(R.string.sign_err),
//											SelectDialog2.DIALOG_RES_ERR,
//											getActivity(), handler);
//								}
//
//							} else if (ModelCodeUtil.doWithResultCode(
//									getActivity(), model, mHandler)) {
//								// 特定的返回码需要退出登录
//							} else {
//								// gotoErrPay(model.getMsg());
//
//								DialogUtil.createTipDialog(model.getMsg(),
//										SelectDialog2.DIALOG_RES_ERR,
//										getActivity(), handler);
//							}
//						}
//
//					}
//					break;
//				}
//				}
//			} catch (Exception e) {
//				// TODO: handle exception
//				e.printStackTrace();
//			}
//		}
//	};

//	private void gotoPay(PayModel model) {
//		// ygbh_edit.setText("");
//		// mKeyBoard.resetKey();
//
//		// Intent it = new Intent(getActivity(), PayCashActivity.class);
//		// it.putExtra("model", AppData.gson.toJson(model));
//		// it.putExtra("type", currPayType);
//		//
//		// getActivity().startActivity(it);
//		Bundle bundle = new Bundle();
//		bundle.putString("model", AppData.gson.toJson(model));
//		bundle.putInt("type", currPayType);
//
//		((ConsumeActivity) getActivity()).switchToFragment(1, bundle, 0);
//	}
//
//	private void openDialog(String str) {
//		if (dialog != null) {
//			dialog.show();
//			if (str != null) {
//				dialog.setMessage(str);
//			}
//		}
//	}
//
//	public void reSet() {
//		setMode(1, null);
//		mKeyBoard.reState();

//	}

//	/**
//	 * 设置显示的
//	 *
//	 * @param model
//	 * @param payModel
//	 */
//	public void setMode(int model, PayOrderModel payModel) {
//		switch (model) {
//		case 1: {
//			moneyView.setVisibility(View.GONE);
//			scrollLayout.setVisibility(View.VISIBLE);
//			// mKeyBoard.resetKey();
//			mListView.setVisibility(View.GONE);
//			payInfoLayout.setVisibility(View.GONE);
//			break;
//		}
//		case 2: {
//			moneyView.setVisibility(View.VISIBLE);
//			scrollLayout.setVisibility(View.GONE);
//			mListView.setVisibility(View.GONE);
//			payInfoLayout.setVisibility(View.GONE);
//			break;
//		}
//		case 3: {
//			moneyView.setVisibility(View.GONE);
//			scrollLayout.setVisibility(View.GONE);
//			mListView.setVisibility(View.GONE);
//			payInfoLayout.setVisibility(View.VISIBLE);
//
//			xfjeValueText.setText(String.format("%s %.2f",
//					payModel.getCurrency_sign(),
//					Double.parseDouble(payModel.getOriginal_amount()) / 100));
//
//			yhjeValueText.setText(String.format("%s %.2f", payModel
//					.getCurrency_sign(), (Double.parseDouble(payModel
//					.getDiscount_amount()) + Double.parseDouble(payModel
//					.getIgnore_amount())) / 100));
//
//			realAmtValueText.setText(String.format("%s %.2f",
//					payModel.getCurrency_sign(),
//					Double.parseDouble(payModel.getTrade_amount()) / 100));
//
//			// if (payModel != null && payAdapter == null) {
//			// // payList.addAll(payModel.getList());
//			// allData(payModel);
//			// payAdapter = new ShowPayListAdapter(getActivity(), payList, 3,
//			// handler);
//			// } else {
//			// // payList.clear();
//			// // payList.addAll(payModel.getList());
//			// allData(payModel);
//			// }
//			//
//			// mListView.setAdapter(payAdapter);
//			// mListView.smoothScrollToPosition(0);
//			// payAdapter.notifyDataSetChanged();
//
//			break;
//		}
//		}
//	}
//
//	private void allData(PayModel payModel) {
//		payList.clear();
//		for (PayListModel item : payModel.getList()) {
//			if (item.getListname() != null) {
//				if (item.getListname().indexOf("订单编号") != -1) {
//
//				} else if (item.getListname().indexOf("原始金额") != -1) {
//
//					PayListModel item2 = new PayListModel();
//					item2.setListname("消费金额");
//					item2.setListcontent(item.getListcontent());
//					item2.setListcolor(item.getListcolor());
//					payList.add(item2);
//
//					try {
//
//						item2 = new PayListModel();
//						item2.setListname("优惠金额");
//						item2.setListcontent(String.format("%.02f",
//								Double.parseDouble(item.getListcontent())
//										- payModel.getRealamount()));
//						item2.setListcolor(item.getListcolor());
//						payList.add(item2);
//
//					} catch (Exception ex) {
//						ex.printStackTrace();
//					}
//
//				} else {
//					PayListModel item2 = new PayListModel();
//					item2.setListname(item.getListname());
//					item2.setListcontent(item.getListcontent());
//					item2.setListcolor(item.getListcolor());
//					payList.add(item2);
//				}
//			}
//		}
//	}

//	private void goToPay() {
//		// 重置金额为0
//		// mKeyBoard.resetKey();
//
//		PayOrderModel payModel = new PayOrderModel();
//		payModel.setOrd_no(AuthManager.getOrderId());
//		payModel.setOriginal_amount(String.format("%.0f",
//				Double.parseDouble(payAmount) * 100));
//
//		// SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//
//		// 设置日期格式
//
//		// 进入收银台
//		Bundle bundle = new Bundle();
//		bundle.putString("model", AppData.gson.toJson(payModel));
//		bundle.putInt("type", currPayType);
//
//		if (AppData.TRADE_LOG) {
//			// log
//			TradeFileUtil.write2Log(
//					getActivity(),
//					"普通收银订单创建: order_no:" + payModel.getOrd_no()
//							+ "   Original_amount:"
//							+ payModel.getOriginal_amount());
//		}
//		((ConsumeActivity) getActivity()).switchToFragment(1, bundle, 0);
//	}

//	private void payMoney(final String amount, final String uid,
//                          final String ucid) {
//		curtaskid = THREAD_PAY_MODE;
//		try {
//			List<NameValuePair> lt = Auth.auth();
//
//			Map<String, String> getData = new HashMap<String, String>();
//			getData.put("token",
//					CacheUtils.getString(getActivity(), "token", ""));
//
//			RequestAuthParams authParams = new RequestAuthParams(getActivity(),
//					getData, null);
//			lt.add(new BasicNameValuePair("sign", authParams.getSign()));
//
//			ResultDataProvider provider = new NetSingletonPostMultiFactory(lt);
//			DataThreadPoolManager datamanager = DataThreadPoolManager
//					.getSingleTon();
//			datamanager.doTaskQueue(getActivity(), AppData.Path + "/paylist?"
//					+ authParams.getRequestGetParams(),
//					android.os.Process.THREAD_PRIORITY_DEFAULT,
//					payModelAnylize, provider);
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			if (dialog != null && dialog.isShowing()) {
//				dialog.dismiss();
//			}
//			ModelCodeUtil.doWithException(getActivity(), ex, mHandler);
//		}
//	}

	@Override
	public void onResume() {
//		PrintUtil.printMsg("comsume onResume", "");
		super.onResume();
	}

	@Override
	public void onPause() {
//		PrintUtil.printMsg("comsume onPause", "");
		super.onPause();
	}

	@Override
	public void onStop() {
//		PrintUtil.printMsg("comsume onStop", "");
		super.onStop();
	}

//	@Override
//	public void onHiddenChanged(boolean hidden) {
//		PrintUtil.printMsg("comsume onhiddenChange", hidden + "");
//		if (!hidden) {
//			titleInterface.setRightTxt(getString(R.string.change_currency),
//					new View.OnClickListener() {
//
//						@Override
//						public void onClick(View arg0) {
//							// TODO Auto-generated method stub
//
//						}
//					});
//		} else {
//			titleInterface.setRightVisiable(false);
//		}
//		super.onHiddenChanged(hidden);
//	}

//	public void isShowFragment(int size) {
//		if (size == 0) {
//
//			String chooseCurrency = CacheUtils.getString(getActivity(),
//					Constant.CHOOSE_CURRENCY, "");
//			if (chooseCurrency.length() > 0) {
//				String currencyItems[] = chooseCurrency.split("<br>");
//				if (currencyItems.length > 1) {
//					titleInterface.setRightTxt(
//							getString(R.string.change_currency),
//							changeCurrencyListener);
//				} else {
//					titleInterface.setRightVisiable(false);
//				}
//			}
//
//		} else {
//			titleInterface.setRightVisiable(false);
//		}
//	}

//	private View.OnClickListener changeCurrencyListener = new OnClickListener() {
//
//		@Override
//		public void onClick(View arg0) {
//			final ChangeCurrencyDialog dialog = new ChangeCurrencyDialog(
//					getActivity(), R.style.dialog_upanim, 1, handler);
//
//			dialog.setCanceledOnTouchOutside(true);
//			dialog.show();
//		}
//	};

	public void setChangeAmtListener(String name, View.OnClickListener listener) {
		this.changeAmtListener = listener;

		if (changeClearAmt != null) {
			changeClearAmt.setText(name);
		}

	}

	@Override
	public void onDestroy() {

		super.onDestroy();
	}

}
