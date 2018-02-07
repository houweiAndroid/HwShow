package com.example.houwei.hwshop.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.Poi;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.example.houwei.hwshop.R;
import com.example.houwei.hwshop.activity.BusinessHomePageActivity;
import com.example.houwei.hwshop.adapter.GroupedRecyclerViewAdapter;
import com.example.houwei.hwshop.adapter.HandleListAdapter;
import com.example.houwei.hwshop.app.App;
import com.example.houwei.hwshop.bean.shopAndcard.HandleBean2;
import com.example.houwei.hwshop.bean.shopAndcard.IndexAdListBean;
import com.example.houwei.hwshop.bean.shopAndcard.IndexShopListBean;
import com.example.houwei.hwshop.constant.Urls;
import com.example.houwei.hwshop.https.callback.JsonCallback;
import com.example.houwei.hwshop.https.callback.StringDialogCallback;
import com.example.houwei.hwshop.recyclerview.BaseViewHolder;
import com.example.houwei.hwshop.service.LocationService;
import com.example.houwei.hwshop.utils.LogUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.yanzhenjie.alertdialog.AlertDialog;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.PermissionListener;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by houwei on 2017/6/2.
 */

public class HomeFragment extends BaseFragment {


    @BindView(R.id.slider)
    SliderLayout sliderShow;
    @BindView(R.id.custom_indicator)
    PagerIndicator indicator;

    @BindView(R.id.btn_post)
    Button btnPost;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;


    @BindView(R.id.ptrFrameLayout)
    MaterialRefreshLayout mPtrFrameLayout;
    @BindView(R.id.classify_viewpager)
    ViewPager mClassifyViewpager;
    //    @BindView(R.id.classify_page1)
//    RadioButton mClassifyPage1;
//    @BindView(R.id.classify_page2)
//    RadioButton mClassifyPage2;
    @BindView(R.id.classify_radiogroup)
    RadioGroup mClassifyRadiogroup;
    @BindView(R.id.btn_startlcation)
    Button btnStartlcation;
    @BindView(R.id.btn_stoplocation)
    Button btnStoplocation;

    private HandleBean2 handleBean2;

    private ArrayList<IndexShopListBean> shopList = new ArrayList<>();
    //    private ArrayList<ShopAndCardBean> shopAndCardBeen=new ArrayList<>();
    ArrayList<IndexAdListBean> mindexAdList = new ArrayList<>();

    private HandleListAdapter adapter;
    private int pageNo = 1;
    private int pageSize = 10;
    private boolean isRefreshing;
    private Map<String, String> params = new HashMap<>();
    private Activity context;


    private LocationService locationService;


    //用于记录当前是何种状态，在请求完数据之后根据不同的状态进行不同的操作
    private static final int STATE_INIT = 0;
    private static final int STATE_REFRESH = 1;
    private static final int STATE_LOAD_MORE = 2;
    //用于记录当前的状态
    private int curState = 0;
    //用于记录总页数，在上拉的时候判断还有没有更多数据
    private int totalPage = 1;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = (Activity) context;
//        screenHeight = getResources().getDisplayMetrics().heightPixels;
    }

    private void initRefreshLayout() {
        mPtrFrameLayout.setLoadMore(true);
        mPtrFrameLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                curState = STATE_LOAD_MORE;
                pageNo = pageNo + 1;
                getindexData();
//                index++;
//               if (!isRefreshing) {
//                   isRefreshing = true;
//
//                   params.put("page", String.valueOf(++pageNo));
//                   OkGo.<HandleBean2>get(Urls.GET_SHOP_DATA)//
//                           .tag(this)//
//                           .params(params)
//                           .execute(new JsonCallback<HandleBean2>() {
//                               @Override
//                               public void onSuccess(Response<HandleBean2> response) {
//
//                                   LogUtils.i("-----", response.code() + "");
//                                   handleBean2 = response.body();
//                                   if (handleBean2.getError_code() == 200 && handleBean2.isStatus() == true) {
//                                       shopList.addAll(handleBean2.getIndexStorelist());
//                                       adapter.changeDataSet();
//                                   }
//                               }
//
//                               @Override
//                               public void onError(Response<HandleBean2> response) {
//                                   --pageNo;
//                               }
//
//                               @Override
//                               public void onFinish() {
//                                   isRefreshing = false;
//                                   if (mPtrFrameLayout != null) {
//                                       mPtrFrameLayout.refreshComplete();
//                                   }
//                               }
//                           });
//               }

//
            }

            @Override
            public void onRefresh(final MaterialRefreshLayout materialRefreshLayout) {
//               if (!isRefreshing) {
//                   isRefreshing = true;
                pageNo = 1;
                curState = STATE_REFRESH;
                getindexData();
//                   OkGo.<HandleBean2>get(Urls.GET_SHOP_DATA)//
//                           .tag(this)//
//                           .params(params)
//                           .execute(new JsonCallback<HandleBean2>() {
//                               @Override
//                               public void onSuccess(Response<HandleBean2> response) {
//                                   LogUtils.i("-----", response.code() + "");
//                                   handleBean2 = response.body();
//                                   if (handleBean2.getError_code() == 200 && handleBean2.isStatus() == true) {
//
//                                       shopList.clear();
//                                       shopList.addAll(handleBean2.getIndexStorelist());
//
//                                       adapter.changeDataSet();
//                                   }
//                               }
//
//                               @Override
//                               public void onError(Response<HandleBean2> response) {
//                                   --pageNo;
//                               }
//
//                               @Override
//                               public void onFinish() {
//                                   isRefreshing = false;
//                                   if (mPtrFrameLayout != null) {
//                                       mPtrFrameLayout.refreshComplete();
//                                   }
//                               }
//                           });
//               }


            }
        });


    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        initRefreshLayout();
        getindexData();
        getindexData2();


    }

    private void getindexData2() {
        HashMap params = new HashMap<>();
        params.put("page", "a");

        isRefreshing = true;
        OkGo.<String>post(Urls.abc)//
                .tag(this)//
                .params(params)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                    }
                });

    }


    /**
     * 初始化首页顶部两个广告
     *
     * @param indexAdList 广告列表
     */
    private void initTopAdvertisement(ArrayList<IndexAdListBean> indexAdList) {

        if (null != indexAdList && !"".equals(indexAdList)) {
            for (int i = 0; i < indexAdList.size(); i++) {
                DefaultSliderView textSliderView = new DefaultSliderView(this.getActivity());

                textSliderView.image(indexAdList.get(i).getImageAddress());
                textSliderView.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                    @Override
                    public void onSliderClick(BaseSliderView baseSliderView) {
                        Toast.makeText(HomeFragment.this.getActivity(), "新品推荐", Toast.LENGTH_LONG).show();
                    }
                });
                sliderShow.addSlider(textSliderView);
            }
            sliderShow.setCustomIndicator(indicator);
            sliderShow.setCustomAnimation(new DescriptionAnimation());
            sliderShow.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);      //设置下标的点，所在的位置在底部正中间

            sliderShow.setPresetTransformer(SliderLayout.Transformer.RotateUp);
            sliderShow.setDuration(3000);
        }

//            LogUtils.i("----indexAdList.size()",indexAdList.size()+"");


    }


    @Override
    public void initView() {

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(true);


    }

    private PermissionListener listener = new PermissionListener() {
        @Override
        public void onSucceed(int requestCode, List<String> grantedPermissions) {
            // 权限申请成功回调。

            // 这里的requestCode就是申请时设置的requestCode。
            // 和onActivityResult()的requestCode一样，用来区分多个不同的请求。
            if (requestCode == 110) {
                // TODO ...
                Toast.makeText(getContext(), "申请成功", Toast.LENGTH_SHORT).show();
                initLocationServer();
            }

        }

        @Override
        public void onFailed(int requestCode, List<String> deniedPermissions) {
            // 权限申请失败回调。
            if (requestCode == 110) {
                // TODO ...
                Toast.makeText(getContext(), "申请失败", Toast.LENGTH_SHORT).show();
            }
        }
    };

    /**
     * Rationale支持，这里自定义对话框。
     */
    private RationaleListener rationaleListener = new RationaleListener() {
        @Override
        public void showRequestPermissionRationale(int requestCode, final Rationale rationale) {
            AlertDialog.newBuilder(getContext())
                    .setTitle("友好提醒")
                    .setMessage("你已拒绝过定位权限，沒有定位定位权限无法为你推荐附近的妹子或帅哥，你看着办！")
                    .setPositiveButton("好，给你", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            rationale.resume();
                        }
                    })

                    .setNegativeButton("我拒绝", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            rationale.cancel();
                        }


                    }).show();
        }
    };


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);


    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_home;
    }

    @Override
    public void onDestroy() {

        super.onDestroy();

    }

    @Override
    public void onStop() {
//        locationService.unregisterListener(bdLocationListener); //注销掉监听
//        locationService.stop(); //停止定位服务
        super.onStop();
    }

    @OnClick({R.id.btn_post, R.id.btn_startlcation, R.id.btn_stoplocation})
    public void onViewClicked(Button button) {
        switch (button.getId()) {
            case R.id.btn_post:
//                getindexData();
                break;
            case R.id.btn_startlcation:
                LogUtils.i("----------", "btn_req");
                Toast.makeText(getContext(), "点击了", Toast.LENGTH_SHORT).show();
                AndPermission.with(getContext())
                        .requestCode(110)
                        .permission(Permission.LOCATION)
//                .permission(Manifest.permission.WRITE_CONTACTS)
                        .callback(listener)
                        .rationale(rationaleListener)
                        .start();
                break;
            case R.id.btn_stoplocation:
            if (locationService!=null){
                locationService.stop();
            }

                break;
            default:
                break;
        }


    }

    private void getindexData() {
        params = new HashMap<>();
        params.put("page", pageNo + "");
        params.put("nextPage", "10");
        params.put("areaName", "深圳市");
        params.put("lat", "22.546800");
        params.put("lng", "114.113800");
        isRefreshing = true;

        OkGo.<HandleBean2>get(Urls.GET_SHOP_DATA)//
                .tag(this)//
                .params(params)
                .execute(new JsonCallback<HandleBean2>() {
                    @Override
                    public void onSuccess(Response<HandleBean2> response) {
                        LogUtils.i("-----", response.code() + "");
                        handleBean2 = response.body();
                        if (handleBean2.getError_code() == 200 && handleBean2.isStatus() == true) {
                            mindexAdList = handleBean2.getIndexAdList();
                            if (handleBean2.getIndexStorelist().size() != 0) {

                                shopList = handleBean2.getIndexStorelist();
//                            totalPage=handleBean2.getIndexStorelist().size();
                                showData();
                            } else {
                                Toast.makeText(context, "已加载全部", Toast.LENGTH_SHORT).show();
                                mPtrFrameLayout.finishRefreshLoadMore();
                            }
//


                        }
                    }

                    @Override
                    public void onError(Response<HandleBean2> response) {

                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();

                    }
                });
    }

    private void showData() {
        switch (curState) {
            case STATE_INIT:
                //初始化状态，初始化列表
                initTopAdvertisement(mindexAdList);
                adapter = new HandleListAdapter(getContext(), shopList);
                mRecyclerView.setAdapter(adapter);
                mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                adapter.setOnHeaderClickListener(new GroupedRecyclerViewAdapter.OnHeaderClickListener()

                {
                    @Override
                    public void onHeaderClick(GroupedRecyclerViewAdapter adapter, BaseViewHolder holder,
                                              int groupPosition) {
                        Toast.makeText(getActivity(), "点击了店 " + groupPosition,
                                Toast.LENGTH_SHORT).show();
                       Intent intent=new Intent(getActivity(),BusinessHomePageActivity.class);
                       startActivity(intent);
                    }
                });
                adapter.setOnFooterClickListener(new GroupedRecyclerViewAdapter.OnFooterClickListener()

                {
                    @Override
                    public void onFooterClick(GroupedRecyclerViewAdapter adapter, BaseViewHolder holder,
                                              int groupPosition) {

                        Toast.makeText(getActivity(), "点击了底部" + groupPosition,
                                Toast.LENGTH_SHORT).show();
                        HandleListAdapter expandableAdapter = (HandleListAdapter) adapter;
                        if (expandableAdapter.isExpand(groupPosition)) {
                            expandableAdapter.collapseGroup(groupPosition);
                        } else {
                            expandableAdapter.expandGroup(groupPosition);
                        }
                    }
                });
                adapter.setOnChildClickListener(new GroupedRecyclerViewAdapter.OnChildClickListener()

                {
                    @Override
                    public void onChildClick(GroupedRecyclerViewAdapter adapter, BaseViewHolder holder,
                                             int groupPosition, int childPosition) {
                        Toast.makeText(getActivity(), "店" + groupPosition
                                        + ", 的哪个卡 = " + childPosition,
                                Toast.LENGTH_SHORT).show();
                    }
                });
                break;

            case STATE_REFRESH:
                //下拉刷新状态，刷新数据，列表回到最顶端，关闭下拉刷新

                adapter.refreshData(shopList);
                mRecyclerView.scrollToPosition(0);
                mPtrFrameLayout.finishRefresh();
                break;

            case STATE_LOAD_MORE:
                //上拉加载更多状态，追加数据，关闭上拉加载更多
                adapter.loadMoreData(shopList);
                mPtrFrameLayout.finishRefreshLoadMore();
                break;

        }
    }

    //定位
    private void initLocationServer() {
        locationService = ((App) context.getApplicationContext()).getLocationService();
        locationService.registerListener(bdLocationListener);
        locationService.setLocationOption(locationService.getDefaultLocationClientOption());
        locationService.start();//发起定位
    }


    /*****
     *
     * 定位结果回调，重写onReceiveLocation方法，可以直接拷贝如下代码到自己工程中修改
     *
     */
    private BDAbstractLocationListener bdLocationListener = new BDAbstractLocationListener() {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // TODO Auto-generated method stub
            if (null != location && location.getLocType() != BDLocation.TypeServerError) {
                StringBuffer sb = new StringBuffer(256);
                sb.append("time : ");
                /**
                 * 时间也可以使用systemClock.elapsedRealtime()方法 获取的是自从开机以来，每次回调的时间；
                 * location.getTime() 是指服务端出本次结果的时间，如果位置不发生变化，则时间不变
                 */
                sb.append(location.getTime());
                sb.append("\nlocType : ");// 定位类型
                sb.append(location.getLocType());
                sb.append("\nlocType description : ");// *****对应的定位类型说明*****
                sb.append(location.getLocTypeDescription());
                sb.append("\nlatitude : ");// 纬度
                sb.append(location.getLatitude());
                sb.append("\nlontitude : ");// 经度
                sb.append(location.getLongitude());
                sb.append("\nradius : ");// 半径
                sb.append(location.getRadius());
                sb.append("\nCountryCode : ");// 国家码
                sb.append(location.getCountryCode());
                sb.append("\nCountry : ");// 国家名称
                sb.append(location.getCountry());
                sb.append("\ncitycode : ");// 城市编码
                sb.append(location.getCityCode());
                sb.append("\ncity : ");// 城市
                sb.append(location.getCity());
                sb.append("\nDistrict : ");// 区
                sb.append(location.getDistrict());
                sb.append("\nStreet : ");// 街道
                sb.append(location.getStreet());
                sb.append("\naddr : ");// 地址信息
                sb.append(location.getAddrStr());
                sb.append("\nUserIndoorState: ");// *****返回用户室内外判断结果*****
                sb.append(location.getUserIndoorState());
                sb.append("\nDirection(not all devices have value): ");
                sb.append(location.getDirection());// 方向
                sb.append("\nlocationdescribe: ");
                sb.append(location.getLocationDescribe());// 位置语义化信息
                sb.append("\nPoi: ");// POI信息
                if (location.getPoiList() != null && !location.getPoiList().isEmpty()) {
                    for (int i = 0; i < location.getPoiList().size(); i++) {
                        Poi poi = (Poi) location.getPoiList().get(i);
                        sb.append(poi.getName() + ";");
                    }
                }
                if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
                    sb.append("\nspeed : ");
                    sb.append(location.getSpeed());// 速度 单位：km/h
                    sb.append("\nsatellite : ");
                    sb.append(location.getSatelliteNumber());// 卫星数目
                    sb.append("\nheight : ");
                    sb.append(location.getAltitude());// 海拔高度 单位：米
                    sb.append("\ngps status : ");
                    sb.append(location.getGpsAccuracyStatus());// *****gps质量判断*****
                    sb.append("\ndescribe : ");
                    sb.append("gps定位成功");
                } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
                    // 运营商信息
                    if (location.hasAltitude()) {// *****如果有海拔高度*****
                        sb.append("\nheight : ");
                        sb.append(location.getAltitude());// 单位：米
                    }
                    sb.append("\noperationers : ");// 运营商信息
                    sb.append(location.getOperators());
                    sb.append("\ndescribe : ");
                    sb.append("网络定位成功");
                } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
                    sb.append("\ndescribe : ");
                    sb.append("离线定位成功，离线定位结果也是有效的");
                } else if (location.getLocType() == BDLocation.TypeServerError) {
                    sb.append("\ndescribe : ");
                    sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
                } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                    sb.append("\ndescribe : ");
                    sb.append("网络不同导致定位失败，请检查网络是否通畅");
                } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                    sb.append("\ndescribe : ");
                    sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
                }
//                logMsg(sb.toString());

                Toast.makeText(getContext(), sb.toString(), Toast.LENGTH_SHORT).show();
            }
        }

        /**
         * 回调定位诊断信息，开发者可以根据相关信息解决定位遇到的一些问题
         * 自动回调，相同的diagnosticType只会回调一次
         *
         * @param locType           当前定位类型
         * @param diagnosticType    诊断类型（1~9）
         * @param diagnosticMessage 具体的诊断信息释义
         */
        public void onLocDiagnosticMessage(int locType, int diagnosticType, String diagnosticMessage) {

            if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_BETTER_OPEN_GPS) {

                //建议打开GPS

            } else if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_BETTER_OPEN_WIFI) {

                //建议打开wifi，不必连接，这样有助于提高网络定位精度！

            } else if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_NEED_CHECK_LOC_PERMISSION) {

                //定位权限受限，建议提示用户授予APP定位权限！

            } else if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_NEED_CHECK_NET) {

                //网络异常造成定位失败，建议用户确认网络状态是否异常！

            } else if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_NEED_CLOSE_FLYMODE) {

                //手机飞行模式造成定位失败，建议用户关闭飞行模式后再重试定位！

            } else if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_NEED_INSERT_SIMCARD_OR_OPEN_WIFI) {

                //无法获取任何定位依据，建议用户打开wifi或者插入sim卡重试！

            } else if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_NEED_OPEN_PHONE_LOC_SWITCH) {

                //无法获取有效定位依据，建议用户打开手机设置里的定位开关后重试！

            } else if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_SERVER_FAIL) {

                //百度定位服务端定位失败
                //建议反馈location.getLocationID()和大体定位时间到loc-bugs@baidu.com

            } else if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_FAIL_UNKNOWN) {

                //无法获取有效定位依据，但无法确定具体原因
                //建议检查是否有安全软件屏蔽相关定位权限
                //或调用LocationClient.restart()重新启动后重试！

            }
        }

    };
//    private BDLocationListener bdLocationListener = new BDLocationListener() {
//        @Override
//        public void onReceiveLocation(BDLocation bdLocation) {
////            lat = String.valueOf(bdLocation.getLatitude());
////            lng = String.valueOf(bdLocation.getLongitude());
////            city = bdLocation.getCity();
////            UIHandler.post(new Runnable() {
////                @Override
////                public void run() {
//////                    setLocation();
////                    SharedPreferences sp = context.getSharedPreferences("Pingan", Context.MODE_PRIVATE);
////                    SharedPreferences.Editor editor = sp.edit();
////                    if ("4.9E-324".equals(lat) || "4.9E-324".equals(lng)) {
////                        editor.putString("lat", "22.546800");
////                        editor.putString("lng", "114.113800");
////                    } else {
////                        editor.putString("lat", lat);
////                        editor.putString("lng", lng);
////                    }
////                    editor.putString("city", TextUtils.isEmpty(city) ? "深圳" : city);
////                    editor.commit();
////                }
////            });
//        }
//    };

    @Override
    public void onDestroyView() {
        sliderShow.stopAutoCycle();
        super.onDestroyView();
    }


}
