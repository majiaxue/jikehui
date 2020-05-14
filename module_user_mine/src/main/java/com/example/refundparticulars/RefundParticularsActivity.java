package com.example.refundparticulars;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.bean.AlterationBean;
import com.example.bean.RefundParticularsBean;
import com.example.module_user_mine.R;
import com.example.module_user_mine.R2;
import com.example.mvp.BaseActivity;

import java.util.List;

import butterknife.BindView;

/**
 * 退款详情
 */
@Route(path = "/module_user_mine/RefundParticularsActivity")
public class RefundParticularsActivity extends BaseActivity<RefundParticularsView, RefundParticularsPresenter> implements RefundParticularsView {

    @BindView(R2.id.include_back)
    ImageView includeBack;
    @BindView(R2.id.include_title)
    TextView includeTitle;
    @BindView(R2.id.refund_particulars_status)
    TextView refundParticularsStatus;
    @BindView(R2.id.refund_particulars_time)
    TextView refundParticularsTime;
    @BindView(R2.id.refund_particulars_name)
    TextView refundParticularsName;
    @BindView(R2.id.refund_particulars_phone)
    TextView refundParticularsPhone;
    @BindView(R2.id.refund_particulars_address)
    TextView refundParticularsAddress;
    @BindView(R2.id.refund_particulars_total_money)
    TextView refundParticularsTotalMoney;
    @BindView(R2.id.refund_particulars_price)
    TextView refundParticularsPrice;
    @BindView(R2.id.refund_succeed_total)
    LinearLayout refundSucceedTotal;
    @BindView(R2.id.refund_particulars_path)
    TextView refundParticularsPath;
    @BindView(R2.id.refund_particulars_price1)
    TextView refundParticularsPrice1;
    @BindView(R2.id.refund_succeed_path)
    LinearLayout refundSucceedPath;
    @BindView(R2.id.waiting_refund)
    LinearLayout waitingRefund;
    @BindView(R2.id.refund_particulars_reason)
    TextView refundParticularsReason;
    @BindView(R2.id.refund_particulars_amount)
    TextView refundParticularsAmount;
    @BindView(R2.id.refund_particulars_time_application)
    TextView refundParticularsTimeApplication;
    @BindView(R2.id.refund_particulars_contact_seller)
    LinearLayout refundParticularsContactSeller;
    @BindView(R2.id.refund_particulars_dial)
    LinearLayout refundParticularsDial;
    @BindView(R2.id.refund_particulars_consult_customer_service)
    LinearLayout refundParticularsConsultCustomerService;
    @BindView(R2.id.refund_particulars_rec)
    RecyclerView refundParticularsRec;

    @Autowired(name = "orderSn")
    String orderSn;
    @Autowired(name = "position")
    int position;


    @Override
    public int getLayoutId() {
        return R.layout.activity_refund_particulars;
    }

    @Override
    public void initData() {
        includeTitle.setText("退款详情");
        ARouter.getInstance().inject(this);
        presenter.initView(orderSn);
    }

    @Override
    public void initClick() {
        includeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //联系卖家
        refundParticularsContactSeller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call("18818814558");
            }
        });
        //咨询客服
        refundParticularsConsultCustomerService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call("40083312345");
            }
        });
    }

    @Override
    public RefundParticularsView createView() {
        return this;
    }

    @Override
    public RefundParticularsPresenter createPresenter() {
        return new RefundParticularsPresenter(this);
    }

    //调起电话
    private void call(String call) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + call));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //开启系统拨号器
        startActivity(intent);

    }

    @Override
    public void initView(RefundParticularsBean list) {

        if (0 == list.getBackStatus()) {
            refundParticularsStatus.setText("等待商家处理");
            refundSucceedTotal.setVisibility(View.GONE);
            refundSucceedPath.setVisibility(View.GONE);
            waitingRefund.setVisibility(View.VISIBLE);
        } else if (1 == list.getBackStatus()) {
            refundParticularsStatus.setText("退货中");
        } else if (2 == list.getBackStatus()) {
            refundParticularsStatus.setText("退款成功");
            refundSucceedTotal.setVisibility(View.VISIBLE);
            refundSucceedPath.setVisibility(View.VISIBLE);
            waitingRefund.setVisibility(View.GONE);
        } else if (3 == list.getBackStatus()) {
            refundParticularsStatus.setText("商家已拒绝");
        } else if (4 == list.getBackStatus()) {
            refundParticularsStatus.setText("退款申请已取消");
        }

        refundParticularsTime.setText(list.getReceiveTime());
        refundParticularsName.setText(list.getReceiverName());
        refundParticularsPhone.setText(list.getReceiverPhone());
        refundParticularsAddress.setText(list.getReceiverRegion() + list.getReceiverCity() + list.getReceiverProvince() + list.getOrderAddress());
        refundParticularsPrice.setText(list.getReturnAmount() + "");
        if ("1".equals(list.getPayWay())) {
            refundParticularsPath.setText("退回支付宝");
        } else if ("2".equals(list.getPayWay())) {
            refundParticularsPath.setText("退回微信");
        }

        refundParticularsPrice1.setText(list.getReturnAmount() + "");
        refundParticularsReason.setText(list.getReason());
        refundParticularsAmount.setText(list.getReturnAmount() + "");
        refundParticularsTimeApplication.setText(list.getCreateTime());

        List<RefundParticularsBean.ItemlistBean> itemList = list.getItemlist();
        presenter.goodsList(refundParticularsRec, itemList);

    }
}
