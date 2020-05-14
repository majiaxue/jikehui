package com.example.order_assess;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.example.common.CommonResource;
import com.example.bean.MineOrderBean;
import com.example.module_user_mine.R;
import com.example.module_user_mine.R2;
import com.example.mvp.BaseActivity;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.order_assess.adapter.OrderAssessAdapter;
import com.example.bean.CommentVo;
import com.example.utils.ImageUtil;
import com.example.utils.LogUtil;
import com.example.utils.ProcessDialogUtil;
import com.example.utils.SPUtil;
import com.example.utils.SpaceItemDecoration;
import com.example.view.RatingBarView;
import com.facebook.drawee.view.SimpleDraweeView;

import org.apache.commons.lang3.StringUtils;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * 订单立即评价
 */
@Route(path = "/module_user_mine/OrderAssessActivity")
public class OrderAssessActivity extends BaseActivity<OrderAssessView, OrderAssessPresenter> implements OrderAssessView {
    @BindView(R2.id.include_back)
    ImageView includeBack;
    @BindView(R2.id.include_title)
    TextView includeTitle;
    @BindView(R2.id.order_assess_img)
    SimpleDraweeView orderAssessImg;
    @BindView(R2.id.order_assess_name)
    TextView orderAssessName;
    @BindView(R2.id.order_assess_edit)
    EditText orderAssessEdit;
    @BindView(R2.id.order_assess_rv)
    RecyclerView orderAssessRv;
    @BindView(R2.id.order_assess_depict)
    RatingBarView orderAssessDepict;
    @BindView(R2.id.order_assess_logistics)
    RatingBarView orderAssessLogistics;
    @BindView(R2.id.order_assess_service)
    RatingBarView orderAssessService;
    @BindView(R2.id.order_assess_star)
    RatingBarView orderAssessStar;
    @BindView(R2.id.order_assess_niming_img)
    CheckBox orderAssessNimingImg;
    @BindView(R2.id.order_assess_niming)
    LinearLayout orderAssessNiming;
    @BindView(R2.id.order_assess_btn)
    TextView orderAssessBtn;
    @BindView(R2.id.order_assess_addpic)
    ImageView mAddPic;

    @Autowired(name = "beanList")
    MineOrderBean.OrderListBean beanList;

    @Autowired(name = "position")
    int position;

    private final int TAKE_PHOTO_CODE = 0x111;
    private final int PHOTO_ALBUM_CODE = 0x222;
    private boolean isNiming = true;
    private List<String> images = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_order_assess;
    }

    @Override
    public void initData() {
        ARouter.getInstance().inject(this);
        includeTitle.setText("订单评价");

        orderAssessImg.setImageURI(beanList.getOrderItems().get(position).getProductPic());
        orderAssessName.setText(beanList.getOrderItems().get(position).getProductName());

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        orderAssessRv.setLayoutManager(layoutManager);
        orderAssessRv.addItemDecoration(new SpaceItemDecoration((int) getResources().getDimension(R.dimen.dp_10), 0, 0, 0));
        presenter.loadData();

    }

    @Override
    public void initClick() {
        includeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        orderAssessNiming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isNiming = !isNiming;
                orderAssessNimingImg.setChecked(isNiming);
            }
        });

        mAddPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.addPic();
            }
        });
        //立即评价
        orderAssessBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProcessDialogUtil.showProcessDialog(OrderAssessActivity.this);
                CommentVo commentVo = new CommentVo();
                commentVo.setIcon(SPUtil.getStringValue(CommonResource.USER_PIC));
                commentVo.setNickname(SPUtil.getStringValue(CommonResource.USER_NAME));
                commentVo.setSppf(orderAssessStar.getStarCount());
                commentVo.setInfo(orderAssessEdit.getText().toString());
                if (images.size() != 0) {
                    String[] strings = images.toArray(new String[images.size()]);
                    commentVo.setPics(StringUtils.join(strings));
                } else {
                    commentVo.setPics("");
                }
                commentVo.setProductId(beanList.getOrderItems().get(position).getProductId() + "");
                commentVo.setOrderSn(beanList.getOrderItems().get(position).getOrderSn());
                commentVo.setOrderItemId(beanList.getOrderItems().get(position).getId() + "");
                commentVo.setPjpf(orderAssessDepict.getStarCount());
                commentVo.setWlpf(orderAssessLogistics.getStarCount());
                commentVo.setFwpf(orderAssessService.getStarCount());
                if (orderAssessNimingImg.isChecked()) {
                    commentVo.setIsAnonymous(1);
                } else {
                    commentVo.setIsAnonymous(0);
                }
                commentVo.setAttr(beanList.getOrderItems().get(position).getProductAttr());
                String jsonString = JSON.toJSONString(commentVo);
                RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonString);
                Observable<ResponseBody> responseBodyObservable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9004).postDataWithBody(CommonResource.USERCOMMENT, requestBody);
                RetrofitUtil.getInstance().toSubscribe(responseBodyObservable, new OnMyCallBack(new OnDataListener() {
                    @Override
                    public void onSuccess(String result, String msg) {
                        LogUtil.e("评论成功----->" + result + msg);
                        Toast.makeText(OrderAssessActivity.this, "评论成功", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onError(String errorCode, String errorMsg) {
                        LogUtil.e("评论失败----->" + errorMsg);
                        Toast.makeText(OrderAssessActivity.this, errorMsg, Toast.LENGTH_SHORT).show();
                    }
                }));
            }
        });
    }

    @Override
    public void showAdd() {
        mAddPic.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideAdd() {
        mAddPic.setVisibility(View.GONE);
    }

    @Override
    public void showRv() {
        orderAssessRv.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRv() {
        orderAssessRv.setVisibility(View.GONE);
    }

    @Override
    public void loadRv(OrderAssessAdapter adapter) {
        orderAssessRv.setAdapter(adapter);
    }

    @Override
    public void takePhoto(Intent intent) {
        startActivityForResult(intent, TAKE_PHOTO_CODE);
    }

    @Override
    public void photoAlbum(Intent intent) {
        startActivityForResult(intent, PHOTO_ALBUM_CODE);
    }

    @Override
    public void listImage(List<String> listImage) {
        images.clear();
        images.addAll(listImage);
    }

    @Override
    public void imagePath(String path) {
        images.add(path);
    }

//    @Override
//    public void imageUri(Uri uri) {
//        LogUtil.e("图片--------->" + uri);
//        try {
//            Bitmap bitmap = BitmapFactory.decodeStream(this.getContentResolver().openInputStream(uri));
//            String base64 = ImageUtil.bitmapToBase64(bitmap);
//            LogUtil.e("image64--------->" + base64);
//            images.add(base64);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }

        switch (requestCode) {
            case TAKE_PHOTO_CODE:
                presenter.updateList();
                break;
            case PHOTO_ALBUM_CODE:
                presenter.parseUri(data);
                break;
        }
    }

    @Override
    public OrderAssessView createView() {
        return this;
    }

    @Override
    public OrderAssessPresenter createPresenter() {
        return new OrderAssessPresenter(this);
    }
}
