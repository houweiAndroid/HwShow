package com.example.houwei.hwshop.constant;

/**
 * 请求地址
 * <p/>
 * Created by zhong on 2016/3/17.
 */
public class Urls {
    //删除消息
    public static final String DELETEMES = Constants.getBaseUrl() + "/push/deletePushMsg";

    //======================================================
    // 办卡
    //======================================================
    //城市搜索页默认数据
    public static final String GET_SHOP_DATA = Constants.getBaseUrl() + "/index/getIndexShowByShop";


    //城市搜索页默认数据
    public static final String GET_CITY_DATA = Constants.getBaseUrl() + "/searchArea/getCityData";

    //搜索请求
    public static final String GET_CITY_SEARCH = Constants.getBaseUrl() + "/searchArea/getArea";

    //返回搜索城市记录给服务器
    public static final String GET_CITY_BACK = Constants.getBaseUrl() + "/searchArea/savaHistoryCity";


    //查询热门搜索
    public static final String GET_HOT_SELL = Constants.getBaseUrl() + "/index/getHotSell";

    //查询商户一级分类列表
    public static final String GET_ALL_ONE_SELL_CLASS = Constants.getBaseUrl() + "/index/getAllOneSellClass";

    //查询商户二级分类
    public static final String GET_ALL_TWO_SELL_CLASS = Constants.getBaseUrl() + "/index/getAllTwoSellClass";

    //查询按复合搜索框搜索会员卡列表
    public static final String SELECT_ALL_CARD = Constants.getBaseUrl() + "/cardSearch/selectAllCard";

    //查询按复合搜索框搜索店铺列表
    public static final String SELECT_ALL_STORE = Constants.getBaseUrl() + "/cardSearch/searchStore";

    //查询卡分类列表
    public static final String ALL_CARD_TYPE = Constants.getBaseUrl() + "/cardSearch/getAllCardType";

    //查询排序列表
    public static final String ORDER_BY_LIST = Constants.getBaseUrl() + "/cardSearch/getOrderByList";

    //查询筛选列表
    public static final String SCREEN_LIST = Constants.getBaseUrl() + "/cardSearch/getScreenList";

    //查询办卡首页默认数据
    public static final String GET_HANDLE = Constants.getBaseUrl() + "/index/getindexdata";

    //查询商户一个分类搜索卡页面数据
    public static final String GET_SELL_ONE_TYPE_CARD_DATA = Constants.getBaseUrl() + "/index/getSellOneTypeCardData";
    //查询商户一个分类搜索卡页面数据
    public static final String GET_SELL_ONE_TYPE_STORE_DATA = Constants.getBaseUrl() + "/index/getTypeCardDataSBS";


    //查询会员卡详情
//    public static final String GET_CARD_DETAIL = Constants.getBaseUrl() + "/cardSearch/getCardDetailPageData";
//    public static final String GET_CARD_DETAIL = Constants.getBaseUrl() + "/cardSearch/getCardDetailPageData1";
    public static final String GET_CARD_DETAIL = Constants.getBaseUrl() + "/cardSearch/getStoreCardDetail";

    //小蜜卡的详情页
    public static final String GET_CARD_DETAIL_FOR_SMALLMI = Constants.getBaseUrl() + "/cardSearch/getProdOrderDetail";



    //防重复交易
    public static final String GENRESUBMIT = Constants.getBaseUrl() + "/resumbittokenmanager/genReSubmitToken";

    //查询商户评价详情
    public static final String GET_CARD_DETAIL_COMMENT = Constants.getBaseUrl() + "/evaluation/getCardCommentListByProductId";

    //会员卡详情收藏会员卡
    public static final String ADD_CARD_COLLECTION = Constants.getBaseUrl() + "/my/addCardCollection";

    //立即购买
    public static final String BUY_NOW = Constants.getBaseUrl() + "/buycard/payNow";

    //确认支付
    public static final String COMFIRM_PAY = Constants.getBaseUrl() + "/buycard/saveOrder";



    //分店信息数据
//    public static final String SELLER_DETAIL_PAGE_DATA = Constants.getBaseUrl() + "/cardSearch/getSellerDetailPageData";

    //二期分店信息数据
    public static final String SELLER_DETAIL_PAGE_DATA_OF_STORE = Constants.getBaseUrl() + "/cardSearch/getStoreDetailPageData";

    //查询商家评价信息
    public static final String SELLER_COMMENT = Constants.getBaseUrl() + "/evaluation/getCardCommentListBySellerId";

    //根据会员卡的id查询所有适用门店
    public static final String SELLER_BRANCH_LIST = Constants.getBaseUrl() + "/seller/getSellerBranchList";
    //其他分店
    public static final String GET_OTHER_STORE = Constants.getBaseUrl() + "/cardSearch/getOtherStoreDetailPageData";

    //刚进店铺的时候查询是否店铺被收藏
    public static final String SELECT_SHOP_COLLECTION = Constants.getBaseUrl() + "/cardSearch/getSellerCollectioned";
    //刚进卡的时候查询是否店铺被收藏
    public static final String SELECT_CARD_COLLECTION = Constants.getBaseUrl() + "/cardSearch/getCardCollectioned";


    //收藏商家
    public static final String ADD_SELLER_COLLECTION = Constants.getBaseUrl() + "/my/addSellerCollection";

    //根据商户Id查询该商户下所有的会员卡列表
    public static final String GET_SELLER_CARD = Constants.getBaseUrl() + "/cardSearch/getSellerCard";

    //支付结果回调
    public static final String PAY_CALLBACK = Constants.getBaseUrl() + "/buycard/callBack";

    //版本更新
    public static final String checkVersion = Constants.getBaseUrl() + "/buyerHybVersion/versionDetail";




    //======================================================
    // 卡包
    //======================================================

    //卡包列表
    public static final String GET_CARD_LIST = Constants.getBaseUrl() + "/cardWrap/cardList";

    //会员卡卡面信息
    public static final String GET_CARD_INFO = Constants.getBaseUrl() + "/cardWrap/cardInfo";

    //会员卡基本信息
    public static final String GET_BAGCARD_DETAIL = Constants.getBaseUrl() + "/cardWrap/cardDetail";

    //会员卡交易明细
    public static final String CARD_TRADEDETAIL = Constants.getBaseUrl() + "/cardWrap/tradeDetail";

    //获取可用代金券
    public static final String CARD_USERABLE_VOUCHERS = Constants.getBaseUrl() + "/cardWrap/usableVouchers";

    //打折卡买单
    public static final String DISCOUNT_SPEND = Constants.getBaseUrl() + "/cardWrap/discountSpend";

    //次数卡买单
    public static final String FREQUENCY_SPEND = Constants.getBaseUrl() + "/cardWrap/frequencySpend";

    //时效卡买单
    public static final String AGING_SPEND = Constants.getBaseUrl() + "/cardWrap/agingSpend";

    //买送卡买单
    public static final String SENT_SPEND = Constants.getBaseUrl() + "/cardWrap/sentSpend";

    //获取退卡信息
    public static final String GET_REFUND_CARDDATA = Constants.getBaseUrl() + "/buycard/getRefundCardData";

    //退卡
    public static final String CARD_REFUND = Constants.getBaseUrl() + "/buycard/cardRefund";

    //买单后评价
    public static final String SUBMIT_EVALUATE = Constants.getBaseUrl() + "/evaluation/saveCardComment";

    //查看买单后的评价内容
    public static final String COMMENT_DETAIL = Constants.getBaseUrl() + "/evaluation/getCardCommentDetail";

    //买单之后推送消息
    public static final String SEND_MESSAGE = Constants.getBaseUrl() + "/cardWrap/spendPush";
    //买卡交易明细
    public static final String BUYCARDDETAIL = Constants.getBaseUrl() + "/cardWrap/findBuyCardTrade";
    //买单交易明细
    public static final String BUYORDERDETAIL = Constants.getBaseUrl() + "/cardWrap/findSpendTrade";
    //退卡交易明细
    public static final String REFUNDCARDDETAIL = Constants.getBaseUrl() + "/cardWrap/findReturnCardTrade";
    //退款交易明细
    public static final String REFUNDMONEYDETAIL = Constants.getBaseUrl() + "/cardWrap/findCancelSpendTrade";


    //======================================================
    // 我的
    //======================================================
    //图片验证码
    public static final String IMAGE_TOKEN = Constants.getBaseUrl() + "/imagetokenmanager/genImageToken";

    //登录
    public static final String LOGIN = Constants.getBaseUrl() + "/buyerlogin/login";

    //注销
    public static final String OUTLOGIN = Constants.getBaseUrl() + "/buyerlogin/outLogin";

    //注册
    public static final String REGISTER = Constants.getBaseUrl() + "/buyerlogin/registerInfo";

    //获取短信验证码
    public static final String GET_VERIFICATION_CODE = Constants.getBaseUrl() + "/buyerlogin/sendInfo";

    //获取短信验证码，带图片验证
    public static final String GET_VERIFICATION_WITHIMAGETOKEN = Constants.getBaseUrl() + "/buyerlogin/sendTPyzm";

    //获取安全问题列表
    public static final String GET_SECURITY_QUESTIONS = Constants.getBaseUrl() + "/buyerlogin/getSecurityQuestion";

    //设置安全问题
    public static final String SET_SECURITY_QUESTION = Constants.getBaseUrl() + "/buyerlogin/setSecurity";

    //设置支付密码
    public static final String SET_PAY_PASSWORD = Constants.getBaseUrl() + "/buyerlogin/setPayPw";

    //设置密保和支付密码
    public static final String SET_PAYANDQUESTION = Constants.getBaseUrl() + "/buyerlogin/setSecurityAndPayPw";

    //手机号找回登录密码
    public static final String FIND_PASSWORD = Constants.getBaseUrl() + "/buyerlogin/findLoginPw";

    //密保问题找回登录密码(去掉了此功能)
//    public static final String FIND_PASSWORDBY = Constants.getBaseUrl() + "/buyerlogin/findLoginPwByQuestion";

    //查询个人信息
    public static final String MY_DETAIL = Constants.getBaseUrl() + "/my/getBuyerDetail";

    //修改个人信息
    public static final String UPDATE_MY_DETAIL = Constants.getBaseUrl() + "/my/updateBuyer";

    //会员卡收藏列表
//    public static final String MY_COLLECTION = Constants.getBaseUrl() + "/my/getConnetionList";
    public static final String CARD_CONNECTION_LIST = Constants.getBaseUrl() + "/my/getCardConnetionList";

    //查询收藏商家
    public static final String SELLER_CONNECTION_LIST = Constants.getBaseUrl() + "/my/getSellerConnetionList";

    //取消收藏会员卡、门店
    public static final String CANCEL_COLLECTION = Constants.getBaseUrl() + "/my/cancelCollection";

    //意见反馈
    public static final String SAVE_FEED_BACK = Constants.getBaseUrl() + "/my/saveFeedBack";

    //我的钱包
    public static final String GET_MY_PURSE = Constants.getBaseUrl() + "/my/getMyPurse";

    //我的余额信息
    public static final String GET_BUYER_ACCT_BANK = Constants.getBaseUrl() + "/my/getBuyerAcctOrBank";

    //我要提现
    public static final String TAKE_CASH = Constants.getBaseUrl() + "/my/takeCash";

    //查看收支明细
    public static final String GET_BUYERINC_TRADE = Constants.getBaseUrl() + "/my/getBuyerIncomeTrade";

    //检查支付密码正确性
    public static final String CHECK_PAYPWD = Constants.getBaseUrl() + "/cardWrap/checkPayPw";

    //修改登录密码
    public static final String UPDATE_LOGINPWD = Constants.getBaseUrl() + "/buyerAcct/updateLoginPw";

    //修改支付密码
    public static final String UPDATE_PAYPWD = Constants.getBaseUrl() + "/buyerAcct/updatePayPw";

    //安全问题找回支付密码
    public static final String UPDATE_PAYPWDBYQUE = Constants.getBaseUrl() + "/buyerlogin/findPayPwByQuestion";

    //绑卡 修改卡
    public static final String TIED_CARD = Constants.getBaseUrl() + "/my/tiedCard";

    //解绑卡
    public static final String DELETE_CARD = Constants.getBaseUrl() + "/my/deleteTiedCard";

    //我的红包
    public static final String HB_COUPON = Constants.getBaseUrl() + "/my/getHBCoupon";

    //我的代金券
    public static final String GET_COUPON = Constants.getBaseUrl() + "/my/getAvailableCoupon";

    //查询银行名称
    public static final String GET_BANK_NAME = Constants.getBaseUrl() + "/my/getBankNameList";

    //获取安全问题
    public static final String GET_SECURITY_QUESTION = Constants.getBaseUrl() + "/buyerlogin/getBuyerQuestion";

    //验证安全问题
    public static final String CHECKOUT_SECURITY_QUESTION = Constants.getBaseUrl() + "/my/checkSecurityQue";

    //找回密码，校验验证码
    public static final String CHECKOUT_VERIFY_CODE = Constants.getBaseUrl() + "/buyerlogin/checkMobileVerify";

    //验证支付密码
    public static final String CHECKOUT_PAYWORD = Constants.getBaseUrl() + "/buyerAcct/checkPayPw";

    //验证登录密码
    public static final String CHECKOUT_PASSWORD = Constants.getBaseUrl() + "/buyerAcct/checkLoginPw";

    //验证登录和支付密码
    public static final String CHECKOUT_LOGINGPAYPWD = Constants.getBaseUrl() + "/buyerAcct/checkLoginOrPayPw";

    //信息状态
    public static final String GET_MESSAGE_STATE = Constants.getBaseUrl() + "/index/getMessage";

    //验证找回支付密码
    public static final String CHECKOUT_FINDPAYPWD = Constants.getBaseUrl() + "/buyerAcct/checkFindPayPw";

    //消息列表
    public static final String MESSAGELIST = Constants.getBaseUrl() + "/push/pushMsgList";

    //请求消息详情
    public static final String MESSAGEDETAIL = Constants.getBaseUrl() + "/push/pushMsgDetail";

    //注册极光设备用户
    public static final String JPUSHREGISTER = Constants.getBaseUrl() + "/push/savePushAudience";

    //请求时间戳
    public static final String REQUESTTIME = Constants.getBaseUrl() + "/buyerlogin/returnTime";

    //验证用户手机号是否注册
    public static final String REQUESTREGISTER = Constants.getBaseUrl() + "/buyerlogin/verificationUser";

    //修改手机号码
    public static final String UPDATEPHONE = Constants.getBaseUrl() + "/buyerAcct/updateMobile";

    //查看推荐人
    public static final String GETRECOMMENDER = Constants.getBaseUrl() + "/my/getRecommender";

    //设置推荐人
    public static final String SAVERECOMMENDER = Constants.getBaseUrl() + "/my/saveRecommender";

    //判断是否输错过密码
    public static final String IMAGEDISPLAYFLAG = Constants.getBaseUrl() + "/buyerlogin/ImageDisplayFlag";

    //查询客户买卡订单列表
    public static final String GETBUYCARDORDERINNER = Constants.getBaseUrl() + "/my/getBuycardOrderInner";

    //删除客户买卡订单
    public static final String HIDDENCARDORDERINNER = Constants.getBaseUrl() + "/my/hiddenMyOrder";


    //查询客户买卡订单详情
    public static final String GETORDERPAYDETAIL = Constants.getBaseUrl() + "/my/getOrderPayDetail";

    //客户申请退款
    public static final String REFUNDFEEAPPLY = Constants.getBaseUrl() + "/my/refundFeeApply";

    //订单支付前判断
    public static final String GETREPAYFLAG = Constants.getBaseUrl() + "/my/getRepayFlag";

    //查询客户旅游保证金的列表
    public static final String TRAVELDEPOSIT_ORDER = Constants.getBaseUrl() + "/bail/getDepositByList";

    //旅游保证金的订单详情
    public static final String DEPOSITDETAIL = Constants.getBaseUrl() + "/bail/getDepositDetail";

    //旅游保证金取消订单
    public static final String CANCELPAYDEPOSIT = Constants.getBaseUrl() + "/bail/cancelPayDeposit";

    //旅游保证金的客户详情
    public static final String TOURIISM_CUSTOMERINFO = Constants.getBaseUrl() + "/bail/getDepositCustomerList";

    //旅游保证金支付页面
    public static final String TOURIISM_BAIPAYNOW = Constants.getBaseUrl() + "/bail/bailPayNow";
    //保证金订单确认支付
    public static final String BAIL_PAY = Constants.getBaseUrl() + "/bail/saveBailOrder";
    //保证金订单支付结果回调
    public static final String BAIL_PAY_CALLBACK = Constants.getBaseUrl() + "/bail/bailCallBack";

    //新的更改登录密码的接口
    public static final String NEW_REST_LOGINPW = Constants.getBaseUrl() + "/buyerAcct/newRestLoginPw";
    //新的修改支付密码
    public static final String UPDATEPAYPW_NEW = Constants.getBaseUrl() +"/buyerAcct/updatePayPw_new";
    //新的验证密保问题
    public static final String SECURITYQUWCHECK = Constants.getBaseUrl() +"/my/securityQueCheck";
    //新的修改支付密码
    public static final String NEWUPDATEPAYPW = Constants.getBaseUrl() +"/buyerAcct/newUpdatePayPw";
    //新的修改手机号
    public static final String NEWUPDATEMOBILE = Constants.getBaseUrl() +"/buyerAcct/newUpdateMobile";
    //新设置安全问题
    public static final String SET_SECURITY_QUESTION_NEW = Constants.getBaseUrl() + "/buyerlogin/setSecurity_new";





    //==========================================
    //HTML5 URL

    //平安会员宝使用说明
    public static final String USE_INFO = Constants.getH5Url() + "/project/shuoming.html";

    //常见问题
    public static final String PROBLEM = Constants.getH5Url() + "/project/problem.html";

    //规则平台
    public static final String RULE = Constants.getH5Url() + "/project/rule.html?type=android";

    //购卡协议
    public static final String BUY_CARD_INFO = Constants.getH5Url() + "/project/card.html";

    //注册协议
    public static final String H5_REGISTER = Constants.getH5Url() + "/project/login.html";
    public static final String abc= "https://stg1.pahyb.com/app-hyb-h5-server/project/login.html";





}
