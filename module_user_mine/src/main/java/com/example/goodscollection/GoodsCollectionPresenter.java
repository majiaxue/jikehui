package com.example.goodscollection;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.example.adapter.BaseRecStaggeredAdapter;
import com.example.adapter.MyRecyclerAdapter;
import com.example.bean.HotSaleBean;
import com.example.common.CommonResource;
import com.example.goodscollection.adapter.GoodsCollectionRecAdapter;
import com.example.bean.GoodsCollectionRecBean;
import com.example.module_user_mine.R;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.utils.DisplayUtil;
import com.example.utils.LogUtil;
import com.example.utils.ProcessDialogUtil;
import com.example.utils.SPUtil;
import com.example.utils.SpaceItemDecorationLeftAndRight;
import com.kongzue.dialog.v3.WaitDialog;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * Created by cuihaohao on 2019/5/23
 * Describe:
 */
public class GoodsCollectionPresenter extends BasePresenter<GoodsCollectionView> {

    private List<GoodsCollectionRecBean.RecordsBean> recBeanList = new ArrayList<>();
    private boolean isCompile = false;
    //    private boolean isAllCheck;
    private boolean flag = true;
    private GoodsCollectionRecAdapter goodsCollectionRecAdapter;
    private List<HotSaleBean.DataBean> commendList = new ArrayList<>();

    public GoodsCollectionPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void setGoodsCollectionRec(final RecyclerView goodsCollectionRec) {
//        WaitDialog.show((AppCompatActivity) mContext, null);
        ProcessDialogUtil.showProcessDialog(mContext);

        Observable<ResponseBody> dataWithout = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHeadWithout(CommonResource.GOODSCOLLECTION, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(dataWithout, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("setGoodsCollectionRec----->" + result);
                LogUtil.e("setGoodsCollectionRec----->" + msg);

                GoodsCollectionRecBean goodsCollectionRecBean = JSON.parseObject(result, new TypeReference<GoodsCollectionRecBean>() {
                }.getType());

                if (goodsCollectionRecBean != null) {
                    recBeanList.clear();
                    recBeanList.addAll(goodsCollectionRecBean.getRecords());

                    getView().empty(false);

                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
                    goodsCollectionRec.setLayoutManager(linearLayoutManager);

                    goodsCollectionRecAdapter = new GoodsCollectionRecAdapter(mContext, recBeanList, R.layout.item_goods_collection_rec);
                    if (getView() != null) {
                        getView().loadUI(goodsCollectionRecAdapter);
                    }
                    goodsCollectionRecAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(RecyclerView parent, View view, int position) {
//                            Toast.makeText(mContext, "position:" + position, Toast.LENGTH_SHORT).show();
                            ARouter.getInstance()
                                    .build("/module_user_store/GoodsDetailActivity")
                                    .withString("id", recBeanList.get(position).getId() + "")
                                    .withString("sellerId", "" + recBeanList.get(position).getSellerId())
                                    .withString("commendId", recBeanList.get(position).getProductCategoryId() + "")
                                    .navigation();
                        }
                    });

                    goodsCollectionRecAdapter.setViewTwoOnClickListener(new MyRecyclerAdapter.ViewTwoOnClickListener() {
                        @Override
                        public void ViewTwoOnClick(View view1, View view2, final int position) {
                            //点击选中
                            view1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    flag = true;
                                    check(position);
                                }
                            });
                            //进入店铺
                            view2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
//                                    Toast.makeText(mContext, "position:" + position, Toast.LENGTH_SHORT).show();

                                }
                            });
                        }
                    });

                } else {
                    getView().empty(true);
                }


            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("setGoodsCollectionRecError------->" + errorCode);
                LogUtil.e("setGoodsCollectionRecError------->" + errorMsg);
                getView().empty(true);
            }
        }));


    }

    public void setGoodsCollectionBottomRec(final RecyclerView goodsCollectionBottomRec) {
//        Map map = MapUtil.getInstance().addParms("searchInfo", "两件套").build();
        Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).getDataWithout(CommonResource.HOTNEWSEARCH);
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("result----->" + result);
                HotSaleBean hotSaleBean = JSON.parseObject(result, new TypeReference<HotSaleBean>() {
                }.getType());
                commendList.clear();
                commendList.addAll(hotSaleBean.getData());
                StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                //添加间距
                SpaceItemDecorationLeftAndRight spaceItemDecorationLeftAndRight = new SpaceItemDecorationLeftAndRight(DisplayUtil.dip2px(mContext, 15), DisplayUtil.dip2px(mContext, 15));
                if (goodsCollectionBottomRec.getItemDecorationCount() == 0) {
                    goodsCollectionBottomRec.addItemDecoration(spaceItemDecorationLeftAndRight);
                }
                goodsCollectionBottomRec.setLayoutManager(staggeredGridLayoutManager);
                BaseRecStaggeredAdapter baseRecStaggeredAdapter = new BaseRecStaggeredAdapter(mContext, commendList, R.layout.item_base_rec_staggered_grid);

                if (getView() != null) {
                    getView().loadCommend(baseRecStaggeredAdapter);
                }
                baseRecStaggeredAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(RecyclerView parent, View view, int position) {
                        ARouter.getInstance()
                                .build("/module_user_store/GoodsDetailActivity")
                                .withString("id", commendList.get(position).getId() + "")
                                .withString("sellerId", commendList.get(position).getSellerId())
                                .withString("commendId", commendList.get(position).getProductCategoryId() + "")
                                .navigation();
                    }
                });
                baseRecStaggeredAdapter.setViewOnClickListener(new MyRecyclerAdapter.ViewOnClickListener() {
                    @Override
                    public void ViewOnClick(View view, final int index) {
                        view.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(mContext, "position:" + index, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("result------->" + errorMsg);
            }
        }));


    }

    //选中recycler的item
    private void check(int position) {

        if (recBeanList.get(position).isCheck()) {
            recBeanList.get(position).setCheck(false);
        } else {
            recBeanList.get(position).setCheck(true);
        }

        goodsCollectionRecAdapter.notifyDataSetChanged();

        for (int i = 0; i < recBeanList.size(); i++) {
            if (!recBeanList.get(i).isCheck()) {
//                isAllCheck = false;
                flag = false;
            }

        }

        if (getView() != null) {
            getView().isCheckAll(flag);
        }

    }

    //编辑
    public void compile() {
        if (recBeanList.size() == 0) {
            if (isCompile) {
                isCompile = false;
                getView().isCompile(isCompile);
            } else {
                isCompile = true;
                getView().isCompile(isCompile);
            }
        } else {
            if (isCompile) {
                isCompile = false;
                getView().isCompile(isCompile);
            } else {
                isCompile = true;
                getView().isCompile(isCompile);
            }
            goodsCollectionRecAdapter.setCompile(isCompile);
        }

    }

    //点击选中全部
    public void checkAll(boolean isCheckAll) {
//        this.isAllCheck = isCheckAll;
        if (isCheckAll) {
            for (int i = 0; i < recBeanList.size(); i++) {
                recBeanList.get(i).setCheck(false);
            }
        } else {
            for (int i = 0; i < recBeanList.size(); i++) {
                recBeanList.get(i).setCheck(true);
            }
        }
        goodsCollectionRecAdapter.notifyDataSetChanged();
    }

    //删除
    public void deleteList() {
        List<String> deleteList = new ArrayList<>();
        for (int i = 0; i < recBeanList.size(); i++) {
            if (recBeanList.get(i).isCheck()) {
                deleteList.add(recBeanList.get(i).getId() + "");
            }
        }

        Observable<ResponseBody> deleteGoodsCollection = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).postDelete(CommonResource.FAVORITEDELETE, deleteList, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(deleteGoodsCollection, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("deleteGoodsCollection----->" + msg);
                for (int i = recBeanList.size() - 1; i >= 0; i--) {
                    if (recBeanList.get(i).isCheck()) {
                        recBeanList.remove(i);
                    }
                }

                goodsCollectionRecAdapter.notifyDataSetChanged();

                if (recBeanList.size() == 0) {
                    getView().empty(true);
                    getView().isCheckAll(false);
                    getView().isCompile(false);
                } else {
                    getView().empty(false);
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("deleteGoodsCollection----->" + errorMsg);
            }
        }));


    }
}
