package com.example.chunzhi;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.module_mine.R;
import com.example.module_mine.R2;
import com.example.mvp.BaseActivity;
import com.example.utils.LogUtil;
import com.example.utils.PopUtils;
import com.example.utils.ProcessDialogUtil;

import butterknife.BindView;

//购物金充值界面（新增功能）
public class ChunZhiActivity extends BaseActivity<ChunZhiView, ChunZhiPresenter> implements ChunZhiView {
    @BindView(R2.id.include_back)
    ImageView includeBack;
    @BindView(R2.id.include_title)
    TextView includeTitle;
    @BindView(R2.id.include_right)
    ImageView includeRight;
    @BindView(R2.id.include_right_btn)
    TextView includeRightBtn;
    @BindView(R2.id.chunzhi_hao)
    EditText chunzhiHao;
    @BindView(R2.id.ll_kahao)
    LinearLayout llKahao;
    @BindView(R2.id.chunzhi_psw)
    EditText chunzhiPsw;
    @BindView(R2.id.ll_mima)
    LinearLayout llMima;
    @BindView(R2.id.btn_jihuo)
    Button btnJihuo;
    private String hao;//卡号
    private String psw;//密码


    @Override
    public int getLayoutId() {
        return R.layout.activity_chunzhi;
    }

    @Override
    public void initData() {
        includeTitle.setText("购物金充值");
    }

    @Override
    public void initClick() {
        //返回键
        includeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //充值键
        btnJihuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hao = chunzhiHao.getText().toString();
                psw = chunzhiPsw.getText().toString();
                ProcessDialogUtil.showProcessDialog(ChunZhiActivity.this);
                presenter.getData(hao,psw);
            }
        });
    }

    @Override
    public ChunZhiView createView() {
        return this;
    }

    @Override
    public ChunZhiPresenter createPresenter() {
        return new ChunZhiPresenter(this);
    }
}
