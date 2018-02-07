package com.example.houwei.hwshop.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;


import com.example.houwei.hwshop.R;
import com.example.houwei.hwshop.bean.shopAndcard.CardlistBean;
import com.example.houwei.hwshop.bean.shopAndcard.IndexShopListBean;
import com.example.houwei.hwshop.https.Transformation.PicassoRoundTransform;
import com.example.houwei.hwshop.recyclerview.BaseViewHolder;
import com.example.houwei.hwshop.utils.LogUtils;
import com.example.houwei.hwshop.utils.ViewSizeHelper;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by houwei on 2017/9/10.
 */

public class HandleListAdapter extends GroupedRecyclerViewAdapter {
    private ArrayList<IndexShopListBean> mShopAndCardBeanlist;

    private boolean showMore = false;
    private Context mcontext;
    private int screenWidth;

    public HandleListAdapter(Context context, ArrayList<IndexShopListBean> shopAndCardBean) {
        super(context);
        mcontext=context;
        mShopAndCardBeanlist = shopAndCardBean;
        screenWidth = context.getResources().getDisplayMetrics().widthPixels;
        for (IndexShopListBean indexShopListBean : mShopAndCardBeanlist) {
            indexShopListBean.setExpand(false);
        }

    }

    @Override
    public int getGroupCount() {
        return mShopAndCardBeanlist == null ? 0 : mShopAndCardBeanlist.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        //如果当前组收起，就直接返回0，否则才返回子项数。这是实现列表展开和收起的关键。
        if (!isExpand(groupPosition)) {
            if (mShopAndCardBeanlist.get(groupPosition).getCardlistBeen().size() == 1) {
                return 1;
            } else if (mShopAndCardBeanlist.get(groupPosition).getCardlistBeen().size() == 2) {
                return 2;
            } else {
                return 2;
            }

        }
        ArrayList<CardlistBean> cardlistBeens = (ArrayList<CardlistBean>) mShopAndCardBeanlist.get(groupPosition).getCardlistBeen();

        return cardlistBeens == null ? 0 : cardlistBeens.size();
    }

    @Override
    public boolean hasHeader(int groupPosition) {
        return true;
    }

    @Override
    public boolean hasFooter(int groupPosition) {
        if (mShopAndCardBeanlist.get(groupPosition).getCardlistBeen().size() <= 2) {
            return false;

        } else {
            return true;
        }


    }
//    private ArrayList<IndexShopListBean> getdata(ArrayList<IndexShopListBean> indexbeenlst){
//        List<IndexShopListBean> cardList=indexbeenlst.
//
////        ArrayList<IndexShopListBean> been=
//
//
//    return
//
//    };


    @Override
    public int getHeaderLayout(int viewType) {
        return R.layout.item_head;
    }

    @Override
    public int getFooterLayout(int viewType) {
        return R.layout.item_foot;
    }

    @Override
    public int getChildLayout(int viewType) {
        return R.layout.item_content;
    }

    @Override
    public void onBindHeaderViewHolder(BaseViewHolder holder, int groupPosition) {

        ViewSizeHelper.getInstance(mcontext).setWidth(holder.get(R.id.imageview1), screenWidth * 135 / 500, 150, 93);
        IndexShopListBean bean = mShopAndCardBeanlist.get(groupPosition);
        holder.setText(R.id.textview1, bean.getStoreSign());//招牌名称
        holder.setText(R.id.textview2, "(" + bean.getSellerCustName() + ")");//分店名称
        holder.setText(R.id.textview3, bean.getDistance());//距离
//        holder.setText(R.id.textview4, bean.getBuyerNum());////购卡人数

        ((RatingBar)holder.get(R.id.shop_ratingbar)).setRating(Float.parseFloat(bean.getSellerStarNum()) / 2f);
        String textStr = "已有" + bean.getBuyerNum() + "人购买";
        SpannableStringBuilder builder = new SpannableStringBuilder(textStr);
        builder.setSpan(new RelativeSizeSpan(1.4f), 2, (textStr.length() - 3), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ((TextView)holder.get(R.id.textview4)).setText(builder);////购卡人数
        Picasso.with(mcontext).load(bean.getSellerImage1()).placeholder(R.mipmap.img_addefault).transform(new PicassoRoundTransform(mContext))
               .fit().into((ImageView) holder.get(R.id.imageview1));



    }

    @Override
    public void onBindFooterViewHolder(BaseViewHolder holder, int groupPosition) {
//        IndexShopListBean bean=mShopAndCardBeanlist.get(groupPosition);
        holder.setText(R.id.tv_more, "收起");
    }

    @Override
    public void onBindChildViewHolder(BaseViewHolder holder, int groupPosition, int childPosition) {
        CardlistBean cardlistBean = mShopAndCardBeanlist.get(groupPosition).getCardlistBeen().get(childPosition);
        holder.setText(R.id.one_price_now, "￥"+cardlistBean.getUnitProduct());
        ((TextView)holder.get(R.id.one_price_past)).getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        holder.setText(R.id.one_price_past,"￥"+cardlistBean.getUnitPrice());
        holder.setText(R.id.one_card_name, cardlistBean.getProductName());
        LogUtils.i("--------childPosition", "" + childPosition);
        String textStr2 = "已售" + cardlistBean.getCardNum() + "份";
        SpannableStringBuilder builder2 = new SpannableStringBuilder(textStr2);
        builder2.setSpan(new RelativeSizeSpan(1.4f), 2, (textStr2.length() - 1), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ((TextView)(holder.get(R.id.one_selled))).setText(builder2);

//        if (childPosition >= 2) {
//            LogUtils.i("--------childPosition", "" + childPosition);
////            holder.setVisible(getChildLayout(getChildViewType(groupPosition, childPosition)),false);
//            holder.setVisible(R.id.layout_one, false);
//
//        }

    }


    /**
     * 判断当前组是否展开
     *
     * @param groupPosition
     * @return
     */
    public boolean isExpand(int groupPosition) {
        if ( mShopAndCardBeanlist.get(groupPosition).getCardlistBeen().size()< 3) {
            return false;

        }
        IndexShopListBean entity = mShopAndCardBeanlist.get(groupPosition);
        return entity.isExpand();
    }
//   public  ArrayList<IndexShopListBean> setShowMore(ArrayList<IndexShopListBean>  ShopAndCardBeanlist) {
//       ArrayList<IndexShopListBean> indexShopListBeenlist=ShopAndCardBeanlist;
//       ArrayList<IndexShopListBean> indexShopListBeenlist2=new ArrayList<>();
//
//                   for (int i=0; i<indexShopListBeenlist.size();i++){
//                       if (indexShopListBeenlist.getCardlistBeen().size()<2){
//                           break indexShopListBeenlist;
//                       }else {
//                           indexShopListBeenlist2.
//
//                       }
//
//                   }
//                   return indexShopListBeenlist;
//
//   }

    /**
     * 展开一个组
     *
     * @param groupPosition
     */
    public void expandGroup(int groupPosition) {
        expandGroup(groupPosition, false);
    }

    /**
     * 展开一个组
     *
     * @param groupPosition
     * @param animate
     */
    public void expandGroup(int groupPosition, boolean animate) {
        IndexShopListBean entity = mShopAndCardBeanlist.get(groupPosition);
        entity.setExpand(true);
        if (animate) {
            insertChildren(groupPosition);
        } else {
            changeDataSet();
        }
    }


    /**
     * 收起一个组
     *
     * @param groupPosition
     */
    public void collapseGroup(int groupPosition) {
        collapseGroup(groupPosition, false);
    }

    /**
     * 收起一个组
     *
     * @param groupPosition
     * @param animate
     */
    public void collapseGroup(int groupPosition, boolean animate) {
        IndexShopListBean entity = mShopAndCardBeanlist.get(groupPosition);
        entity.setExpand(false);
        if (animate) {
            removeChildren(groupPosition);

        } else {
            changeDataSet();
        }
    }

    public void refreshData(ArrayList<IndexShopListBean> list){
        removeAll();
        mShopAndCardBeanlist=list;
        insertGroup(0);
    }
    public void loadMoreData(ArrayList<IndexShopListBean> list){
        mShopAndCardBeanlist.addAll(list);
        int lastPosition = getGroupCount();
//        changeGroup(lastPosition);
        insertRangeGroup(lastPosition,list.size());
        changeDataSet();

    }


}
