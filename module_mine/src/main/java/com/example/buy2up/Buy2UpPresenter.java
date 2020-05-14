package com.example.buy2up;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.example.adapter.PopFlowLayoutAdapter;
import com.example.adapter.SecondFlowAdapter;
import com.example.bean.BannerBean;
import com.example.bean.ChooseInsideBean;
import com.example.bean.OperatorBean;
import com.example.bean.OrderConfirmBean;
import com.example.bean.UserGoodsDetail;
import com.example.common.CommonResource;
import com.example.module_mine.R;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.OnTripartiteCallBack;
import com.example.net.RetrofitUtil;
import com.example.utils.LogUtil;
import com.example.utils.MapUtil;
import com.example.utils.OnClearCacheListener;
import com.example.utils.PopUtils;
import com.example.utils.SPUtil;
import com.example.view.flowLayout.FlowLayout;
import com.example.view.flowLayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public class Buy2UpPresenter extends BasePresenter<Buy2UpView> {
    //选择商品列表
    private List<UserGoodsDetail.SkuStockListBean> dataList;
    //流式布局--颜色
    private TagFlowLayout flow1;
    //流式布局--尺码
    private TagFlowLayout flow2;

    private TagFlowLayout flow3;
    //颜色选中下标
    private int sp1Position = -1;
    //尺码选中下标
    private int sp2Position = -1;

    private int sp3Position = -1;

    private boolean isChoose = false;

    private List<ChooseInsideBean> sp1List = new ArrayList<>();
    private List<ChooseInsideBean> sp2List = new ArrayList<>();
    private List<ChooseInsideBean> sp3List = new ArrayList<>();

    private List<Integer> canotClick1 = new ArrayList<>();
    private List<Integer> canotClick2 = new ArrayList<>();
    private List<Integer> canotClick3 = new ArrayList<>();

    private UserGoodsDetail userGoodsDetail;

    private PopFlowLayoutAdapter sp1Adapter;
    private SecondFlowAdapter sp2Adapter;
    private SecondFlowAdapter sp3Adapter;

    private int attrSize = 1;
    private List<BannerBean.RecordsBean> bannerList;
    private OperatorBean operatorBean;
    private List<OperatorBean> opList = new ArrayList<>();

    public Buy2UpPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void loadQuanyi(String levelId) {
        Map map = MapUtil.getInstance().addParms("id", levelId).build();
        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getData(CommonResource.GET_QUANYI, map);
        RetrofitUtil.getInstance().toSubscribe(observable, new OnTripartiteCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("权益：" + result);
                operatorBean = JSON.parseObject(result, OperatorBean.class);
                opList.add(operatorBean);
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e(errorCode + "-----------" + errorMsg);
            }
        }));
    }

    /**
     * flag     点击按钮   0:立即升级   1:支付(前端用)
     *
     * @param levelId
     */
    public void isCanUp(String levelId,String productId,String partnerCity) {
        //levelId=3  弹出城市
        LogUtil.e("这是levelId-------"+levelId);
        LogUtil.e("这是partnerCity-------"+partnerCity);
        LogUtil.e("这是productId-------"+productId);
        Map map =  MapUtil.getInstance().addParms("levelId", levelId).addParms("payType", "1").addParms("partnerCity", partnerCity).addParms("productId",productId).build();
        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHead(CommonResource.UP_JUSTNOW,  map, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("立即升级：" + result);
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("error:" + errorCode + "------------------" + errorMsg);
                if (errorCode.equals("9")) {
                    chooseAttr();
                } else {
                    Toast.makeText(mContext, "" + errorMsg, Toast.LENGTH_SHORT).show();
                }
            }
        }));
    }

    public void chooseAttr() {
        dataList = userGoodsDetail.getSkuStockList();
        LogUtil.e("zhishijidjfkdsfdf"+dataList);
        if (dataList != null && dataList.size() > 0) {
            sp1List.clear();
            sp2List.clear();
            sp3List.clear();

            //规格缩略图
            String imgTemp = "";
            for (int i = 0; i < dataList.size(); i++) {
                if (dataList.get(i).getSp1() != null && !imgTemp.equals(dataList.get(i).getSp1())) {
                    imgTemp = dataList.get(i).getSp1();
                    if (dataList.get(i).getStock() > 0) {
                        sp1List.add(new ChooseInsideBean(dataList.get(i).getSp1(), dataList.get(i).getPic(), dataList.get(i).getPrice(), dataList.get(i).getStock(), true));
                    } else {
                        sp1List.add(new ChooseInsideBean(dataList.get(i).getSp1(), dataList.get(i).getPic(), dataList.get(i).getPrice(), dataList.get(i).getStock(), false));
                    }
                }
            }

            if (dataList != null && dataList.size() > 0) {
                if (dataList.get(0).getSp2() == null) {
                    attrSize = 1;
                } else if (dataList.get(0).getSp3() == null) {
                    attrSize = 2;
                } else {
                    attrSize = 3;
                }
            }

            if (dataList.size() > 0 && attrSize > 1) {
                for (int i = 0; i < dataList.size(); i++) {
                    boolean isHas = false;
                    if (dataList.get(i).getSp1().equals(sp1List.get(0).getContent())) {
                        for (int j = 0; j < sp2List.size(); j++) {
                            if (sp2List.get(j).getContent().equals(dataList.get(i).getSp2())) {
                                isHas = true;
                            }
                        }
                        if (!isHas) {
                            sp2List.add(new ChooseInsideBean(dataList.get(i).getSp2(), dataList.get(i).getPic(), dataList.get(i).getPrice(), true));
                        }
                    }
                }

            }
            if (dataList.size() > 0 && attrSize > 2) {
                for (int i = 0; i < dataList.size(); i++) {
                    if (dataList.get(i).getSp1().equals(sp1List.get(0).getContent()) && dataList.get(i).getSp2().equals(sp2List.get(0).getContent())) {
                        sp3List.add(new ChooseInsideBean(dataList.get(i).getSp3(), dataList.get(i).getPic(), dataList.get(i).getPrice(), true));
                    }
                }

            }

            chooseGoodsPop(userGoodsDetail.getName());
        } else {
            OrderConfirmBean orderConfirmBean = new OrderConfirmBean();
            orderConfirmBean.setProductName(userGoodsDetail.getName());
            orderConfirmBean.setPic(userGoodsDetail.getPic());
            orderConfirmBean.setPrice(userGoodsDetail.getPrice());
            LogUtil.e("kkkkkk"+userGoodsDetail.getLevelId());
            ARouter.getInstance().build("/mine/upOrderConfirm")
                    .withSerializable("bean", orderConfirmBean)
                    .withString("name", userGoodsDetail.getName())
                    .withString("type", "operator")
                    .withString("levelId", userGoodsDetail.getLevelId())
                    .navigation();

        }
    }

    public void chooseGoodsPop(final String name) {
        try {

            if (userGoodsDetail != null && userGoodsDetail.getXsProductAttributes().size() > 0 && dataList.size() > 0) {
                View view = LayoutInflater.from(mContext).inflate(R.layout.pop2_choose_goods, null);
                final ImageView img = view.findViewById(R.id.pop2_choose_goods_img);
                final TextView price = view.findViewById(R.id.pop2_choose_goods_price);
                final TextView type = view.findViewById(R.id.pop2_choose_goods_type);
                ImageView cancel = view.findViewById(R.id.pop2_choose_goods_cancel);
                flow1 = view.findViewById(R.id.pop2_choose_goods_flow1);
                flow2 = view.findViewById(R.id.pop2_choose_goods_flow2);
                flow3 = view.findViewById(R.id.pop2_choose_goods_flow3);
                TextView buy = view.findViewById(R.id.pop2_choose_goods_buy);
                TextView title1 = view.findViewById(R.id.pop2_choose_goods_title1);
                TextView title2 = view.findViewById(R.id.pop2_choose_goods_title2);
                TextView title3 = view.findViewById(R.id.pop2_choose_goods_title3);

                if (attrSize == 1) {
                    title2.setVisibility(View.GONE);
                    title3.setVisibility(View.GONE);
                    flow2.setVisibility(View.GONE);
                    flow3.setVisibility(View.GONE);
                    title1.setText(userGoodsDetail.getXsProductAttributes().get(0).getName());
                } else if (attrSize == 2) {
                    title3.setVisibility(View.GONE);
                    flow3.setVisibility(View.GONE);
                    title2.setText(userGoodsDetail.getXsProductAttributes().get(1).getName());
                    title1.setText(userGoodsDetail.getXsProductAttributes().get(0).getName());
                } else {
                    title1.setText(userGoodsDetail.getXsProductAttributes().get(0).getName());
                    title2.setText(userGoodsDetail.getXsProductAttributes().get(1).getName());
                    title3.setText(userGoodsDetail.getXsProductAttributes().get(2).getName());
                }

                sp1Adapter = new PopFlowLayoutAdapter(sp1List, mContext);

                for (int i = 0; i < sp1List.size(); i++) {
                    if (sp1List.get(i).getStock() <= 0) {
                        canotClick1.add(i);
                    }
                }

                flow1.setAdapter(sp1Adapter);
                if (canotClick1.size() > 0) {
                    flow1.setNoCheckList(canotClick1);
                }
                flow1.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                    @Override
                    public boolean onTagClick(View view, int position, FlowLayout parent) {
                        boolean isCan = true;
                        for (int i = 0; i < canotClick1.size(); i++) {
                            if (canotClick1.get(i) == position) {
                                LogUtil.e("-------------->" + canotClick1.get(i));
                                isCan = false;
                            }
                        }
                        if (isCan) {

                            sp1Position = position;
                            Glide.with(mContext).load(sp1List.get(sp1Position).getPicUrl()).into(img);
                            if (attrSize == 1) {
                                price.setText("￥" + sp1List.get(sp1Position).getPrice());
                                isChoose = true;
                                type.setText("已选择：" + sp1List.get(sp1Position).getContent());
                            } else if (attrSize == 2) {
                                if (sp2Position == -1) {
                                    initSizeList(1);
                                    type.setText("已选择：" + sp1List.get(sp1Position).getContent());
                                } else {
                                    isChoose = true;
                                    initSizeList(1);
                                    price.setText("￥" + stock1(sp1List.get(sp1Position).getContent(), sp2Position).getPrice());
                                    type.setText("已选择：" + sp1List.get(sp1Position).getContent() + "、" + sp2List.get(sp2Position).getContent());
                                }
                            } else {
                                try {

                                    initThirdList(1);
                                    StringBuffer sb = new StringBuffer();
                                    sb.append("已选择：" + sp1List.get(sp1Position).getContent());
                                    if (sp2Position != -1) {
                                        sb.append("、" + sp2List.get(sp2Position).getContent());
                                    }
                                    if (sp3Position != -1) {
                                        sb.append("、" + sp3List.get(sp3Position).getContent());
                                    }
                                    if (sp2Position != -1 && sp3Position != -1) {
                                        isChoose = true;
                                        price.setText("￥" + stock23(sp3List.get(sp3Position).getContent(), sp2List.get(sp2Position).getContent(), sp1Position).getPrice());
                                    }
                                    type.setText(sb.toString());
                                } catch (Exception e) {
                                    Toast.makeText(mContext, "商品属性异常", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                        return false;
                    }
                });

                if (attrSize > 1) {
                    sp2Adapter = new SecondFlowAdapter(sp2List, mContext);
                    flow2.setAdapter(sp2Adapter);
                    flow2.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                        @Override
                        public boolean onTagClick(View view, int position, FlowLayout parent) {
                            boolean isCan = true;
                            for (int i = 0; i < canotClick2.size(); i++) {
                                if (position == canotClick2.get(i)) {
                                    isCan = false;
                                }
                            }
                            if (!isCan) {
                                return false;
                            }

                            sp2Position = position;
                            if (attrSize == 2) {
                                if (sp1Position == -1) {
                                    initSizeList(2);
                                    type.setText("已选择：" + sp2List.get(sp2Position).getContent());
                                } else {
                                    isChoose = true;
                                    initSizeList(2);
                                    price.setText("￥" + stock1(sp1List.get(sp1Position).getContent(), sp2Position).getPrice());
                                    type.setText("已选择：" + sp1List.get(sp1Position).getContent() + "、" + sp2List.get(sp2Position).getContent());
                                }
                            } else if (attrSize == 3) {
                                try {

                                    initThirdList(2);
                                    StringBuffer sb = new StringBuffer();
                                    sb.append("已选择：");
                                    if (sp1Position == -1) {
                                        sb.append(sp2List.get(sp2Position).getContent());
                                    } else {
                                        sb.append(sp1List.get(sp1Position).getContent() + "、" + sp2List.get(sp2Position).getContent());
                                    }
                                    if (sp3Position != -1) {
                                        sb.append("、" + sp3List.get(sp3Position).getContent());
                                    }
                                    if (sp1Position != -1 && sp3Position != -1) {
                                        isChoose = true;
                                        price.setText("￥" + stock13(sp1List.get(sp1Position).getContent(), sp3List.get(sp3Position).getContent(), sp2Position).getPrice());
                                    }
                                    type.setText(sb.toString());
                                } catch (Exception e) {
                                    Toast.makeText(mContext, "商品属性异常", Toast.LENGTH_SHORT).show();
                                }
                            }
                            return false;
                        }
                    });
                }
                if (attrSize > 2) {
                    sp3Adapter = new SecondFlowAdapter(sp3List, mContext);
                    flow3.setAdapter(sp3Adapter);
                    flow3.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                        @Override
                        public boolean onTagClick(View view, int position, FlowLayout parent) {
                            boolean isCan = true;
                            for (int i = 0; i < canotClick3.size(); i++) {
                                if (position == canotClick3.get(i)) {
                                    isCan = false;
                                }
                            }

                            if (!isCan) {
                                return false;
                            }
                            sp3Position = position;
                            initThirdList(3);
                            if (sp1Position != -1 && sp2Position == -1) {
                                type.setText("已选择：" + sp1List.get(sp1Position).getContent() + "、" + sp3List.get(sp3Position).getContent());
                            } else if (sp1Position == -1 && sp2Position != -1) {
                                type.setText("已选择：" + sp2List.get(sp2Position).getContent() + "、" + sp3List.get(sp3Position).getContent());
                            } else if (sp1Position != -1 && sp2Position != -1) {
                                isChoose = true;
                                type.setText("已选择：" + sp1List.get(sp1Position).getContent() + "、" + sp2List.get(sp2Position).getContent() + "、" + sp3List.get(sp3Position).getContent());
                                price.setText("￥" + stock12(sp1List.get(sp1Position).getContent(), sp2List.get(sp2Position).getContent(), sp3Position));
                            }

                            if (sp1Position != -1 && sp2Position != -1) {
                                isChoose = true;
                                sp3Position = position;
                                type.setText("已选择：" + sp1List.get(sp1Position).getContent() + "、" + sp2List.get(sp2Position).getContent() + "、" + sp3List.get(sp3Position).getContent());
                                price.setText("￥" + sp3List.get(position).getPrice());
                            }
                            return false;
                        }
                    });
                }


                final PopupWindow popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, (int) mContext.getResources().getDimension(R.dimen.dp_444), true);
                popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                popupWindow.setOutsideTouchable(true);
                popupWindow.setAnimationStyle(R.style.pop_bottom_anim);
                popupWindow.showAtLocation(new View(mContext), Gravity.BOTTOM, 0, 0);

                final StringBuffer sb = new StringBuffer();
                sb.append("请选择");
                for (int i = 0; i < attrSize; i++) {
                    sb.append(userGoodsDetail.getXsProductAttributes().get(i).getName() + "  ");
                }
                type.setText(sb);

                if (isChoose) {
                    if (attrSize == 1) {
                        sp1Adapter.setSelectedList(sp1Position);
                        type.setText("已选择：" + sp1List.get(sp1Position).getContent());
                        price.setText("￥" + sp1List.get(sp1Position).getPrice());
                    } else if (attrSize == 2) {
                        sp1Adapter.setSelectedList(sp1Position);
                        sp2Adapter.setSelectedList(sp2Position);
                        price.setText("￥" + sp2List.get(sp2Position).getPrice());
                        type.setText("已选择：" + sp1List.get(sp1Position).getContent() + "、" + sp2List.get(sp2Position).getContent());
                    } else {
                        sp1Adapter.setSelectedList(sp1Position);
                        sp2Adapter.setSelectedList(sp2Position);
                        sp3Adapter.setSelectedList(sp3Position);
                        type.setText("已选择：" + sp1List.get(sp1Position).getContent() + "、" + sp2List.get(sp2Position).getContent() + "、" + sp3List.get(sp3Position).getContent());
                        price.setText("￥" + sp3List.get(sp3Position).getPrice());
                    }
                    Glide.with(mContext).load(sp1List.get(sp1Position).getPicUrl()).into(img);
                } else {
                    Glide.with(mContext).load(userGoodsDetail.getPic()).into(img);
                }

                img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PopUtils.seeBigImg(mContext,sp1Position == -1 ? userGoodsDetail.getPic() : sp1List.get(sp1Position).getPicUrl());
                    }
                });

                PopUtils.setTransparency(mContext, 0.3f);
                popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        PopUtils.setTransparency(mContext, 1f);
                    }
                });


                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });


                buy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (attrSize == 1) {
                            if (sp1Position == -1) {
                                Toast.makeText(mContext, "请选择商品", Toast.LENGTH_SHORT).show();
                            } else {
                                popupWindow.dismiss();
                                toBuy(name);
                            }
                        } else if (attrSize == 2) {
                            if (sp1Position == -1 || sp2Position == -1) {
                                Toast.makeText(mContext, "请选择商品", Toast.LENGTH_SHORT).show();
                            } else {
                                popupWindow.dismiss();
                                toBuy(name);
                            }
                        } else {
                            if (sp1Position == -1 || sp2Position == -1 || sp3Position == -1) {
                                Toast.makeText(mContext, "请选择商品", Toast.LENGTH_SHORT).show();
                            } else {
                                popupWindow.dismiss();
                                toBuy(name);
                            }
                        }
                    }
                });
            }
        } catch (Exception e) {
            Toast.makeText(mContext, "商品属性异常", Toast.LENGTH_SHORT).show();
        }
    }

    private UserGoodsDetail.SkuStockListBean stock1(String str, int position) {
        List<UserGoodsDetail.SkuStockListBean> list = new ArrayList<>();
        if (str != null) {
            for (int i = 0; i < dataList.size(); i++) {
                if (str.equals(dataList.get(i).getSp1())) {
                    list.add(dataList.get(i));
                }
            }
            return list.get(position);
        } else {
            return new UserGoodsDetail.SkuStockListBean();
        }
    }

    private UserGoodsDetail.SkuStockListBean stock12(String str, String str2, int position) {
        List<UserGoodsDetail.SkuStockListBean> list = new ArrayList<>();
        if (str != null && str2 != null) {
            for (int i = 0; i < dataList.size(); i++) {
                if (str.equals(dataList.get(i).getSp1()) && str2.equals(dataList.get(i).getSp2())) {
                    list.add(dataList.get(i));
                }
            }
            return list.get(position);
        } else {
            return new UserGoodsDetail.SkuStockListBean();
        }
    }

    private UserGoodsDetail.SkuStockListBean stock13(String str, String str3, int position) {
        List<UserGoodsDetail.SkuStockListBean> list = new ArrayList<>();
        if (str != null && str3 != null) {
            for (int i = 0; i < dataList.size(); i++) {
                if (str.equals(dataList.get(i).getSp1()) && str3.equals(dataList.get(i).getSp3())) {
                    list.add(dataList.get(i));
                }
            }
            return list.get(position);
        } else {
            return new UserGoodsDetail.SkuStockListBean();
        }
    }

    private UserGoodsDetail.SkuStockListBean stock23(String str3, String str2, int position) {
        List<UserGoodsDetail.SkuStockListBean> list = new ArrayList<>();
        if (str2 != null && str3 != null) {
            for (int i = 0; i < dataList.size(); i++) {
                if (str3.equals(dataList.get(i).getSp3()) && str2.equals(dataList.get(i).getSp2())) {
                    list.add(dataList.get(i));
                }
            }
            return list.get(position);
        } else {
            return new UserGoodsDetail.SkuStockListBean();
        }
    }


    private void initSizeList(int clickPos) {
        try {


            if (clickPos == 1) {
                sp2List.clear();
                canotClick2.clear();
                int temp = 0;
                for (int i = 0; i < dataList.size(); i++) {
                    if (sp1List.get(sp1Position).getContent().equals(dataList.get(i).getSp1())) {
                        boolean isHas = false;
                        for (int j = 0; j < sp2List.size(); j++) {
                            if (sp2List.get(j).getContent().equals(dataList.get(i).getSp2())) {
                                isHas = true;
                            }
                        }
                        if (!isHas) {
                            if (dataList.get(i).getStock() > 0) {
                                sp2List.add(new ChooseInsideBean(dataList.get(i).getSp2(), dataList.get(i).getPrice(), true));
                            } else {
                                canotClick2.add(temp);
                                sp2List.add(new ChooseInsideBean(dataList.get(i).getSp2(), dataList.get(i).getPrice(), false));
                            }
                            temp++;
                        }
                    }
                }

                flow2.setNoCheckList(canotClick2);
                sp2Adapter.notifyDataChanged();
                if (sp2Position != -1) {
                    sp2Adapter.setSelectedList(sp2Position);
                }
            } else if (clickPos == 2) {
                sp1List.clear();
                canotClick1.clear();
                int temp = 0;
                for (int i = 0; i < dataList.size(); i++) {
                    if (dataList.get(i).getSp2().equals(sp2List.get(sp2Position).getContent())) {
                        if (dataList.get(i).getStock() > 0) {
                            sp1List.add(new ChooseInsideBean(dataList.get(i).getSp1(), dataList.get(i).getPic(), dataList.get(i).getPrice(), true));
                        } else {
                            canotClick1.add(temp);
                            sp1List.add(new ChooseInsideBean(dataList.get(i).getSp1(), dataList.get(i).getPic(), dataList.get(i).getPrice(), false));
                        }
                        temp++;
                    }
                }

                flow1.setNoCheckList(canotClick1);
                sp1Adapter.notifyDataChanged();
                if (sp1Position != -1) {
                    sp1Adapter.setSelectedList(sp1Position);
                }
            }
        } catch (Exception e) {
            Toast.makeText(mContext, "商品属性异常", Toast.LENGTH_SHORT).show();
        }
    }

    private void initThirdList(int clickPos) {
        try {

            if (clickPos == 1) {
                int temp2 = 0;
                int temp3 = 0;
                canotClick2.clear();
                canotClick3.clear();
                if (sp2Position == -1 && sp3Position == -1) {
                    sp2List.clear();
                    sp3List.clear();
                    for (int i = 0; i < dataList.size(); i++) {

                        if (dataList.get(i).getSp1().equals(sp1List.get(sp1Position).getContent())) {
                            if (sp2List.size() == 0 && dataList.get(i).getSp2() != null) {
                                sp2List.add(new ChooseInsideBean(dataList.get(i).getSp2(), dataList.get(i).getPic(), dataList.get(i).getPrice(), true));
                            } else {
                                boolean isHas = false;
                                for (int j = 0; j < sp2List.size(); j++) {
                                    if (dataList.get(i).getSp2().equals(sp2List.get(j).getContent())) {
                                        isHas = true;
                                        break;
                                    }
                                }
                                if (!isHas) {
                                    sp2List.add(new ChooseInsideBean(dataList.get(i).getSp2(), dataList.get(i).getPic(), dataList.get(i).getPrice(), true));
                                }
                            }

                            if (sp3List.size() == 0 && dataList.get(i).getSp3() != null) {
                                sp3List.add(new ChooseInsideBean(dataList.get(i).getSp3(), dataList.get(i).getPic(), dataList.get(i).getPrice(), true));
                            } else {
                                boolean isHas = false;
                                for (int j = 0; j < sp3List.size(); j++) {
                                    if (dataList.get(i).getSp3().equals(sp3List.get(j).getContent())) {
                                        isHas = true;
                                        break;
                                    }
                                }
                                if (!isHas) {
                                    sp3List.add(new ChooseInsideBean(dataList.get(i).getSp3(), dataList.get(i).getPic(), dataList.get(i).getPrice(), true));
                                }
                            }
                        }
                    }

                    for (int j = 0; j < sp2List.size(); j++) {
                        boolean hasStock = true;
                        for (int k = 0; k < dataList.size(); k++) {
                            if (dataList.get(k).getSp1().equals(sp1List.get(sp1Position).getContent()) && dataList.get(k).getSp2().equals(sp2List.get(j).getContent())) {
                                if (dataList.get(k).getStock() <= 0) {
                                    hasStock = false;
                                    break;
                                }
                            }
                        }
                        if (!hasStock) {
                            sp2List.get(j).setCanClick(hasStock);
                            canotClick2.add(j);
                        }
                    }

                    for (int j = 0; j < sp3List.size(); j++) {
                        boolean hasStock = true;
                        for (int k = 0; k < dataList.size(); k++) {
                            if (dataList.get(k).getSp1().equals(sp1List.get(sp1Position).getContent()) && dataList.get(k).getSp3().equals(sp3List.get(j).getContent())) {
                                if (dataList.get(k).getStock() <= 0) {
                                    hasStock = false;
                                    break;
                                }
                            }
                        }
                        if (!hasStock) {
                            sp3List.get(j).setCanClick(hasStock);
                            canotClick3.add(j);
                        }
                    }
                    flow2.setNoCheckList(canotClick2);
                    flow3.setNoCheckList(canotClick3);
                    sp2Adapter.notifyDataChanged();
                    sp3Adapter.notifyDataChanged();
                } else if (sp2Position != -1 && sp3Position == -1) {
                    sp3List.clear();
                    for (int i = 0; i < dataList.size(); i++) {
                        if (dataList.get(i).getSp1().equals(sp1List.get(sp1Position).getContent()) && dataList.get(i).getSp2().equals(sp2List.get(sp2Position).getContent())) {
                            sp3List.add(new ChooseInsideBean(dataList.get(i).getSp3(), dataList.get(i).getPic(), dataList.get(i).getPrice(), dataList.get(i).getStock() > 0));
                            if (dataList.get(i).getStock() <= 0) {
                                canotClick3.add(temp3);
                                temp3++;
                            }
                        }
                    }

                    for (int i = 0; i < sp2List.size(); i++) {
                        boolean isStock = false;
                        for (int j = 0; j < dataList.size(); j++) {
                            if (dataList.get(j).getSp1().equals(sp1List.get(sp1Position).getContent()) && dataList.get(j).getSp2().equals(sp2List.get(i).getContent())) {
                                if (dataList.get(j).getStock() > 0) {
                                    isStock = true;
                                }
                            }
                        }
                        sp2List.get(i).setCanClick(isStock);
                        if (!isStock) {
                            canotClick2.add(i);
                        }
                    }
                    flow2.setNoCheckList(canotClick2);
                    flow3.setNoCheckList(canotClick3);
                    sp3Adapter.notifyDataChanged();
                    sp2Adapter.notifyDataChanged();
                    sp2Adapter.setSelectedList(sp2Position);
                } else if (sp2Position == -1 && sp3Position != -1) {
                    sp2List.clear();
                    for (int i = 0; i < dataList.size(); i++) {
                        if (dataList.get(i).getSp1().equals(sp1List.get(sp1Position).getContent()) && dataList.get(i).getSp3().equals(sp3List.get(sp3Position).getContent())) {
                            sp2List.add(new ChooseInsideBean(dataList.get(i).getSp2(), dataList.get(i).getPic(), dataList.get(i).getPrice(), dataList.get(i).getStock() > 0));
                            if (dataList.get(i).getStock() <= 0) {
                                canotClick2.add(temp2);
                                temp2++;
                            }
                        }
                    }

                    for (int i = 0; i < sp3List.size(); i++) {
                        boolean isStock = false;
                        for (int j = 0; j < dataList.size(); j++) {
                            if (dataList.get(j).getSp3().equals(sp3List.get(i).getContent()) && dataList.get(j).getSp1().equals(sp1List.get(sp1Position).getContent())) {
                                if (dataList.get(j).getStock() > 0) {
                                    isStock = true;
                                }
                            }
                        }
                        sp3List.get(i).setCanClick(isStock);
                        if (!isStock) {
                            canotClick3.add(i);
                        }
                    }
                    flow2.setNoCheckList(canotClick2);
                    flow3.setNoCheckList(canotClick3);
                    sp2Adapter.notifyDataChanged();
                    sp3Adapter.notifyDataChanged();
                    sp3Adapter.setSelectedList(sp3Position);
                } else if (sp2Position != -1 && sp3Position != -1) {
                    for (int i = 0; i < sp2List.size(); i++) {
                        boolean isStock = false;
                        for (int j = 0; j < dataList.size(); j++) {
                            if (dataList.get(j).getSp2().equals(sp2List.get(i).getContent()) && dataList.get(j).getSp1().equals(sp1List.get(sp1Position).getContent())) {
                                if (dataList.get(j).getStock() > 0) {
                                    isStock = true;
                                }
                            }
                        }
                        sp2List.get(i).setCanClick(isStock);
                        if (!isStock) {
                            canotClick2.add(i);
                        }
                    }

                    for (int i = 0; i < sp3List.size(); i++) {
                        boolean isStock = false;
                        for (int j = 0; j < dataList.size(); j++) {
                            if (dataList.get(j).getSp3().equals(sp3List.get(i).getContent()) && dataList.get(j).getSp1().equals(sp1List.get(sp1Position).getContent())) {
                                if (dataList.get(j).getStock() > 0) {
                                    isStock = true;
                                }
                            }
                        }
                        sp3List.get(i).setCanClick(isStock);
                        if (!isStock) {
                            canotClick3.add(i);
                        }
                    }

                    flow2.setNoCheckList(canotClick2);
                    flow3.setNoCheckList(canotClick3);
                    sp2Adapter.notifyDataChanged();
                    sp3Adapter.notifyDataChanged();
                    sp2Adapter.setSelectedList(sp2Position);
                    sp3Adapter.setSelectedList(sp3Position);
                }


            } else if (clickPos == 2) {
                int temp1 = 0;
                int temp3 = 0;
                canotClick1.clear();
                canotClick3.clear();
                if (sp1Position == -1 && sp3Position == -1) {
                    sp1List.clear();
                    sp3List.clear();
                    for (int i = 0; i < dataList.size(); i++) {

                        if (dataList.get(i).getSp2().equals(sp2List.get(sp2Position).getContent())) {
                            if (sp1List.size() == 0 && dataList.get(i).getSp1() != null) {
                                sp1List.add(new ChooseInsideBean(dataList.get(i).getSp1(), dataList.get(i).getPic(), dataList.get(i).getPrice(), true));
                            } else {
                                boolean isHas = false;
                                for (int j = 0; j < sp1List.size(); j++) {
                                    if (dataList.get(i).getSp1().equals(sp1List.get(j).getContent())) {
                                        isHas = true;
                                        break;
                                    }
                                }
                                if (!isHas) {
                                    sp1List.add(new ChooseInsideBean(dataList.get(i).getSp1(), dataList.get(i).getPic(), dataList.get(i).getPrice(), true));
                                }
                            }

                            if (sp3List.size() == 0 && dataList.get(i).getSp3() != null) {
                                sp3List.add(new ChooseInsideBean(dataList.get(i).getSp3(), dataList.get(i).getPic(), dataList.get(i).getPrice(), true));
                            } else {
                                boolean isHas = false;
                                for (int j = 0; j < sp3List.size(); j++) {
                                    if (dataList.get(i).getSp3().equals(sp3List.get(j).getContent())) {
                                        isHas = true;
                                        break;
                                    }
                                }
                                if (!isHas) {
                                    sp3List.add(new ChooseInsideBean(dataList.get(i).getSp3(), dataList.get(i).getPic(), dataList.get(i).getPrice(), true));
                                }
                            }
                        }
                    }

                    for (int j = 0; j < sp1List.size(); j++) {
                        boolean hasStock = true;
                        for (int k = 0; k < dataList.size(); k++) {
                            if (dataList.get(k).getSp2().equals(sp2List.get(sp2Position).getContent()) && dataList.get(k).getSp1().equals(sp1List.get(j).getContent())) {
                                if (dataList.get(k).getStock() <= 0) {
                                    hasStock = false;
                                    break;
                                }
                            }
                        }
                        if (!hasStock) {
                            sp1List.get(j).setCanClick(hasStock);
                            canotClick1.add(j);
                        }
                    }

                    for (int j = 0; j < sp3List.size(); j++) {
                        boolean hasStock = true;
                        for (int k = 0; k < dataList.size(); k++) {
                            if (dataList.get(k).getSp2().equals(sp2List.get(sp2Position).getContent()) && dataList.get(k).getSp3().equals(sp3List.get(j).getContent())) {
                                if (dataList.get(k).getStock() <= 0) {
                                    hasStock = false;
                                    break;
                                }
                            }
                        }
                        if (!hasStock) {
                            sp3List.get(j).setCanClick(hasStock);
                            canotClick3.add(j);
                        }
                    }
                    flow1.setNoCheckList(canotClick1);
                    flow3.setNoCheckList(canotClick3);
                    sp1Adapter.notifyDataChanged();
                    sp3Adapter.notifyDataChanged();
                } else if (sp1Position != -1 && sp3Position == -1) {
                    sp3List.clear();
                    for (int i = 0; i < dataList.size(); i++) {
                        if (dataList.get(i).getSp2().equals(sp2List.get(sp2Position).getContent()) && dataList.get(i).getSp1().equals(sp1List.get(sp1Position).getContent())) {
                            sp3List.add(new ChooseInsideBean(dataList.get(i).getSp3(), dataList.get(i).getPic(), dataList.get(i).getPrice(), dataList.get(i).getStock() > 0));
                            if (dataList.get(i).getStock() <= 0) {
                                canotClick3.add(temp3);
                                temp3++;
                            }
                        }
                    }

                    for (int i = 0; i < sp1List.size(); i++) {
                        boolean isStock = false;
                        for (int j = 0; j < dataList.size(); j++) {
                            if (dataList.get(j).getSp2().equals(sp2List.get(sp2Position).getContent()) && dataList.get(j).getSp1().equals(sp1List.get(i).getContent())) {
                                if (dataList.get(j).getStock() > 0) {
                                    isStock = true;
                                }
                            }
                        }
                        sp1List.get(i).setCanClick(isStock);
                        if (!isStock) {
                            canotClick1.add(i);
                        }
                    }
                    flow1.setNoCheckList(canotClick1);
                    flow3.setNoCheckList(canotClick3);
                    sp3Adapter.notifyDataChanged();
                    sp1Adapter.notifyDataChanged();
                    sp1Adapter.setSelectedList(sp1Position);
                } else if (sp1Position == -1 && sp3Position != -1) {
                    sp1List.clear();
                    for (int i = 0; i < dataList.size(); i++) {
                        if (dataList.get(i).getSp2().equals(sp2List.get(sp2Position).getContent()) && dataList.get(i).getSp3().equals(sp3List.get(sp3Position).getContent())) {
                            sp1List.add(new ChooseInsideBean(dataList.get(i).getSp1(), dataList.get(i).getPic(), dataList.get(i).getPrice(), dataList.get(i).getStock() > 0));
                            if (dataList.get(i).getStock() <= 0) {
                                canotClick1.add(temp1);
                                temp1++;
                            }
                        }
                    }

                    for (int i = 0; i < sp3List.size(); i++) {
                        boolean isStock = false;
                        for (int j = 0; j < dataList.size(); j++) {
                            if (dataList.get(j).getSp3().equals(sp3List.get(i).getContent()) && dataList.get(j).getSp2().equals(sp2List.get(sp2Position).getContent())) {
                                if (dataList.get(j).getStock() > 0) {
                                    isStock = true;
                                }
                            }
                        }
                        sp3List.get(i).setCanClick(isStock);
                        if (!isStock) {
                            canotClick3.add(i);
                        }
                    }
                    flow1.setNoCheckList(canotClick1);
                    flow3.setNoCheckList(canotClick3);
                    sp1Adapter.notifyDataChanged();
                    sp3Adapter.notifyDataChanged();
                    sp3Adapter.setSelectedList(sp3Position);
                } else if (sp1Position != -1 && sp3Position != -1) {
                    for (int i = 0; i < sp1List.size(); i++) {
                        boolean isStock = false;
                        for (int j = 0; j < dataList.size(); j++) {
                            if (dataList.get(j).getSp1().equals(sp1List.get(i).getContent()) && dataList.get(j).getSp2().equals(sp2List.get(sp2Position).getContent())) {
                                if (dataList.get(j).getStock() > 0) {
                                    isStock = true;
                                }
                            }
                        }
                        sp1List.get(i).setCanClick(isStock);
                        if (!isStock) {
                            canotClick1.add(i);
                        }
                    }

                    for (int i = 0; i < sp3List.size(); i++) {
                        boolean isStock = false;
                        for (int j = 0; j < dataList.size(); j++) {
                            if (dataList.get(j).getSp3().equals(sp3List.get(i).getContent()) && dataList.get(j).getSp2().equals(sp2List.get(sp2Position).getContent())) {
                                if (dataList.get(j).getStock() > 0) {
                                    isStock = true;
                                }
                            }
                        }
                        sp3List.get(i).setCanClick(isStock);
                        if (!isStock) {
                            canotClick3.add(i);
                        }
                    }

                    flow1.setNoCheckList(canotClick1);
                    flow3.setNoCheckList(canotClick3);
                    sp1Adapter.notifyDataChanged();
                    sp3Adapter.notifyDataChanged();
                    sp1Adapter.setSelectedList(sp1Position);
                    sp3Adapter.setSelectedList(sp3Position);
                }
            } else if (clickPos == 3) {
                int temp2 = 0;
                int temp1 = 0;
                canotClick1.clear();
                canotClick2.clear();
                if (sp2Position == -1 && sp1Position == -1) {
                    sp2List.clear();
                    sp1List.clear();
                    for (int i = 0; i < dataList.size(); i++) {

                        if (dataList.get(i).getSp3().equals(sp3List.get(sp3Position).getContent())) {
                            if (sp2List.size() == 0 && dataList.get(i).getSp2() != null) {
                                sp2List.add(new ChooseInsideBean(dataList.get(i).getSp2(), dataList.get(i).getPic(), dataList.get(i).getPrice(), true));
                            } else {
                                boolean isHas = false;
                                for (int j = 0; j < sp2List.size(); j++) {
                                    if (dataList.get(i).getSp2().equals(sp2List.get(j).getContent())) {
                                        isHas = true;
                                        break;
                                    }
                                }
                                if (!isHas) {
                                    sp2List.add(new ChooseInsideBean(dataList.get(i).getSp2(), dataList.get(i).getPic(), dataList.get(i).getPrice(), true));
                                }
                            }

                            if (sp1List.size() == 0 && dataList.get(i).getSp1() != null) {
                                sp1List.add(new ChooseInsideBean(dataList.get(i).getSp1(), dataList.get(i).getPic(), dataList.get(i).getPrice(), true));
                            } else {
                                boolean isHas = false;
                                for (int j = 0; j < sp1List.size(); j++) {
                                    if (dataList.get(i).getSp1().equals(sp1List.get(j).getContent())) {
                                        isHas = true;
                                        break;
                                    }
                                }
                                if (!isHas) {
                                    sp1List.add(new ChooseInsideBean(dataList.get(i).getSp1(), dataList.get(i).getPic(), dataList.get(i).getPrice(), true));
                                }
                            }
                        }
                    }

                    for (int j = 0; j < sp2List.size(); j++) {
                        boolean hasStock = true;
                        for (int k = 0; k < dataList.size(); k++) {
                            if (dataList.get(k).getSp3().equals(sp3List.get(sp3Position).getContent()) && dataList.get(k).getSp2().equals(sp2List.get(j).getContent())) {
                                if (dataList.get(k).getStock() <= 0) {
                                    hasStock = false;
                                    break;
                                }
                            }
                        }
                        if (!hasStock) {
                            sp2List.get(j).setCanClick(hasStock);
                            canotClick2.add(j);
                        }
                    }

                    for (int j = 0; j < sp1List.size(); j++) {
                        boolean hasStock = true;
                        for (int k = 0; k < dataList.size(); k++) {
                            if (dataList.get(k).getSp3().equals(sp3List.get(sp3Position).getContent()) && dataList.get(k).getSp1().equals(sp1List.get(j).getContent())) {
                                if (dataList.get(k).getStock() <= 0) {
                                    hasStock = false;
                                    break;
                                }
                            }
                        }
                        if (!hasStock) {
                            sp1List.get(j).setCanClick(hasStock);
                            canotClick1.add(j);
                        }
                    }
                    flow2.setNoCheckList(canotClick2);
                    flow1.setNoCheckList(canotClick1);
                    sp2Adapter.notifyDataChanged();
                    sp1Adapter.notifyDataChanged();
                } else if (sp2Position != -1 && sp1Position == -1) {
                    sp1List.clear();
                    for (int i = 0; i < dataList.size(); i++) {
                        if (dataList.get(i).getSp3().equals(sp3List.get(sp3Position).getContent()) && dataList.get(i).getSp2().equals(sp2List.get(sp2Position).getContent())) {
                            sp1List.add(new ChooseInsideBean(dataList.get(i).getSp1(), dataList.get(i).getPic(), dataList.get(i).getPrice(), dataList.get(i).getStock() > 0));
                            if (dataList.get(i).getStock() <= 0) {
                                canotClick1.add(temp1);
                                temp1++;
                            }
                        }
                    }

                    for (int i = 0; i < sp2List.size(); i++) {
                        boolean isStock = false;
                        for (int j = 0; j < dataList.size(); j++) {
                            if (dataList.get(j).getSp3().equals(sp3List.get(sp3Position).getContent()) && dataList.get(j).getSp2().equals(sp2List.get(i).getContent())) {
                                if (dataList.get(j).getStock() > 0) {
                                    isStock = true;
                                }
                            }
                        }
                        sp2List.get(i).setCanClick(isStock);
                        if (!isStock) {
                            canotClick2.add(i);
                        }
                    }
                    flow2.setNoCheckList(canotClick2);
                    flow1.setNoCheckList(canotClick1);
                    sp1Adapter.notifyDataChanged();
                    sp2Adapter.notifyDataChanged();
                    sp2Adapter.setSelectedList(sp2Position);
                } else if (sp2Position == -1 && sp1Position != -1) {
                    sp2List.clear();
                    for (int i = 0; i < dataList.size(); i++) {
                        if (dataList.get(i).getSp3().equals(sp3List.get(sp3Position).getContent()) && dataList.get(i).getSp1().equals(sp1List.get(sp1Position).getContent())) {
                            sp2List.add(new ChooseInsideBean(dataList.get(i).getSp2(), dataList.get(i).getPic(), dataList.get(i).getPrice(), dataList.get(i).getStock() > 0));
                            if (dataList.get(i).getStock() <= 0) {
                                canotClick2.add(temp2);
                                temp2++;
                            }
                        }
                    }

                    for (int i = 0; i < sp1List.size(); i++) {
                        boolean isStock = false;
                        for (int j = 0; j < dataList.size(); j++) {
                            if (dataList.get(j).getSp1().equals(sp1List.get(i).getContent()) && dataList.get(j).getSp3().equals(sp3List.get(sp3Position).getContent())) {
                                if (dataList.get(j).getStock() > 0) {
                                    isStock = true;
                                }
                            }
                        }
                        sp1List.get(i).setCanClick(isStock);
                        if (!isStock) {
                            canotClick1.add(i);
                        }
                    }
                    flow2.setNoCheckList(canotClick2);
                    flow1.setNoCheckList(canotClick1);
                    sp2Adapter.notifyDataChanged();
                    sp1Adapter.notifyDataChanged();
                    sp1Adapter.setSelectedList(sp1Position);
                } else if (sp2Position != -1 && sp1Position != -1) {
                    for (int i = 0; i < sp2List.size(); i++) {
                        boolean isStock = false;
                        for (int j = 0; j < dataList.size(); j++) {
                            if (dataList.get(j).getSp2().equals(sp2List.get(i).getContent()) && dataList.get(j).getSp3().equals(sp3List.get(sp3Position).getContent())) {
                                if (dataList.get(j).getStock() > 0) {
                                    isStock = true;
                                }
                            }
                        }
                        sp2List.get(i).setCanClick(isStock);
                        if (!isStock) {
                            canotClick2.add(i);
                        }
                    }

                    for (int i = 0; i < sp1List.size(); i++) {
                        boolean isStock = false;
                        for (int j = 0; j < dataList.size(); j++) {
                            if (dataList.get(j).getSp1().equals(sp1List.get(i).getContent()) && dataList.get(j).getSp3().equals(sp3List.get(sp3Position).getContent())) {
                                if (dataList.get(j).getStock() > 0) {
                                    isStock = true;
                                }
                            }
                        }
                        sp1List.get(i).setCanClick(isStock);
                        if (!isStock) {
                            canotClick1.add(i);
                        }
                    }

                    flow2.setNoCheckList(canotClick2);
                    flow1.setNoCheckList(canotClick1);
                    sp2Adapter.notifyDataChanged();
                    sp1Adapter.notifyDataChanged();
                    sp2Adapter.setSelectedList(sp2Position);
                    sp1Adapter.setSelectedList(sp1Position);
                }
            }
        } catch (Exception e) {
            Toast.makeText(mContext, "商品属性异常", Toast.LENGTH_SHORT).show();
        }
    }

    private void toBuy(String name) {
        OrderConfirmBean orderConfirmBean = new OrderConfirmBean();

        if (attrSize == 1) {
            orderConfirmBean.setSellerId(userGoodsDetail.getSellerId() + "");
            orderConfirmBean.setSellerName(userGoodsDetail.getSellerName());
            orderConfirmBean.setProductSkuId(dataList.get(sp1Position).getId() + "");
            orderConfirmBean.setQuantity(1);
            orderConfirmBean.setSp1(sp1List.get(sp1Position).getContent());
            orderConfirmBean.setPrice(sp1List.get(sp1Position).getPrice());
            orderConfirmBean.setSourceType(1);
            orderConfirmBean.setPic(userGoodsDetail.getPic());
            orderConfirmBean.setProductName(userGoodsDetail.getName());
            orderConfirmBean.setFeightTemplateId((long) userGoodsDetail.getFeightTemplateId());
            orderConfirmBean.setStock(dataList.get(sp1Position).getStock());
            orderConfirmBean.setProductId(userGoodsDetail.getId() + "");
            orderConfirmBean.setProductCategoryId(userGoodsDetail.getProductCategoryId() + "");
            orderConfirmBean.setProductPrice(userGoodsDetail.getPrice());
            orderConfirmBean.setProductSn(userGoodsDetail.getProductSn());
            orderConfirmBean.setPromotionPrice(sp1List.get(sp1Position).getPrice());
            orderConfirmBean.setProductAttr(userGoodsDetail.getXsProductAttributes().get(0).getName() + ":" + sp1List.get(sp1Position).getContent());

        } else if (attrSize == 2) {
            orderConfirmBean.setSellerId(userGoodsDetail.getSellerId() + "");
            orderConfirmBean.setSellerName(userGoodsDetail.getSellerName());
            orderConfirmBean.setProductSkuId(stock1(sp1List.get(sp1Position).getContent(), sp2Position).getId() + "");
            orderConfirmBean.setQuantity(1);
            orderConfirmBean.setSp1(sp1List.get(sp1Position).getContent());
            orderConfirmBean.setSp2(sp2List.get(sp2Position).getContent());
            orderConfirmBean.setPrice(stock1(sp1List.get(sp1Position).getContent(), sp2Position).getPrice());
            orderConfirmBean.setSourceType(1);
            orderConfirmBean.setPic(stock1(sp1List.get(sp1Position).getContent(), sp2Position).getPic());
            orderConfirmBean.setProductName(userGoodsDetail.getName());
            orderConfirmBean.setFeightTemplateId((long) userGoodsDetail.getFeightTemplateId());
            orderConfirmBean.setStock(stock1(sp1List.get(sp1Position).getContent(), sp2Position).getStock());
            orderConfirmBean.setProductId(userGoodsDetail.getId() + "");
            orderConfirmBean.setProductCategoryId(userGoodsDetail.getProductCategoryId() + "");
            orderConfirmBean.setProductPrice(userGoodsDetail.getPrice());
            orderConfirmBean.setProductSn(userGoodsDetail.getProductSn());
            orderConfirmBean.setPromotionPrice(stock1(sp1List.get(sp1Position).getContent(), sp2Position).getPrice());
            orderConfirmBean.setProductAttr(userGoodsDetail.getXsProductAttributes().get(0).getName() + "：" + sp1List.get(sp1Position).getContent() + "、" + userGoodsDetail.getXsProductAttributes().get(1).getName() + "：" + sp2List.get(sp2Position).getContent());

        } else {
            orderConfirmBean.setSellerId(userGoodsDetail.getSellerId() + "");
            orderConfirmBean.setSellerName(userGoodsDetail.getSellerName());
            orderConfirmBean.setProductSkuId(stock12(sp1List.get(sp1Position).getContent(), sp2List.get(sp2Position).getContent(), sp3Position).getId() + "");
            orderConfirmBean.setQuantity(1);
            orderConfirmBean.setSp1(sp1List.get(sp1Position).getContent());
            orderConfirmBean.setSp2(sp2List.get(sp2Position).getContent());
            orderConfirmBean.setSp2(sp3List.get(sp3Position).getContent());
            orderConfirmBean.setPrice(stock12(sp1List.get(sp1Position).getContent(), sp2List.get(sp2Position).getContent(), sp3Position).getPrice());
            orderConfirmBean.setSourceType(1);
            orderConfirmBean.setPic(stock12(sp1List.get(sp1Position).getContent(), sp2List.get(sp2Position).getContent(), sp3Position).getPic());
            orderConfirmBean.setProductName(userGoodsDetail.getName());
            orderConfirmBean.setFeightTemplateId((long) userGoodsDetail.getFeightTemplateId());
            orderConfirmBean.setStock(stock12(sp1List.get(sp1Position).getContent(), sp2List.get(sp2Position).getContent(), sp3Position).getStock());
            orderConfirmBean.setProductId(userGoodsDetail.getId() + "");
            orderConfirmBean.setProductCategoryId(userGoodsDetail.getProductCategoryId() + "");
            orderConfirmBean.setProductPrice(userGoodsDetail.getPrice());
            orderConfirmBean.setProductSn(userGoodsDetail.getProductSn());
            orderConfirmBean.setPromotionPrice(stock12(sp1List.get(sp1Position).getContent(), sp2List.get(sp2Position).getContent(), sp3Position).getPrice());
            orderConfirmBean.setProductAttr(userGoodsDetail.getXsProductAttributes().get(0).getName() + "：" + sp1List.get(sp1Position).getContent() + "、" + userGoodsDetail.getXsProductAttributes().get(1).getName() + "：" + sp2List.get(sp2Position).getContent() + "、" + userGoodsDetail.getXsProductAttributes().get(2).getName() + ":" + sp3List.get(sp3Position).getContent());

        }

        ARouter.getInstance().build("/mine/upOrderConfirm")
                .withSerializable("bean", orderConfirmBean)
                .withString("name", name)
                .withString("type", "operator")
                .withString("levelId", userGoodsDetail.getLevelId())
                .navigation();
    }

    public void popQuanYi() {
        PopUtils.popLiBaoQuanYi(mContext, operatorBean, new OnClearCacheListener() {
            @Override
            public void setOnClearCache(final PopupWindow pop, View confirm) {
                confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pop.dismiss();
                    }
                });
            }
        });

    }

    public void loadData(int id, String levelId) {
        Map map = MapUtil.getInstance().addParms("levelId", levelId).build();
        Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).getHead(CommonResource.GETGOODSDETAIL + "/" + id, map, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("详情：" + result);
                try {

                    userGoodsDetail = JSON.parseObject(result, UserGoodsDetail.class);
                    String albumPics = userGoodsDetail.getAlbumPics();
                    String[] split = albumPics.split(",");
                    List<BannerBean.RecordsBean> banner = new ArrayList<>();
                    for (int i = 0; i < split.length; i++) {
                        banner.add(new BannerBean.RecordsBean(split[i]));
                    }
                    getView().loadBanner(banner);
                    LogUtil.e("这是P层的banner---------"+banner.toString());
                    getView().loadUI(userGoodsDetail);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {

            }
        }));
    }
}
