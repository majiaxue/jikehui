package com.example.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.module_base.R;

/**
 * Created by cuihaohao on 2019/5/16
 * Describe:
 */
public class TitleBar extends LinearLayout implements View.OnClickListener {
    private TextView title;
    private Button leftBtn;
    private Button rightBtn;
    private LeftBackClickListener leftBackClickListener;
    private RightClickListener rightClickListener;

    public TitleBar(Context context) {
        super(context);
        initView(context, null);
    }

    public TitleBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        View inflate = inflate(context, R.layout.title_bar, this);
        title = inflate.findViewById(R.id.title_tv);
        leftBtn = inflate.findViewById(R.id.left_btn);
        rightBtn = inflate.findViewById(R.id.right_btn);
        leftBtn.setOnClickListener(this);
        rightBtn.setOnClickListener(this);
        if (attrs == null) {
            return;
        }
        initAttrs(context, attrs);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TitleBar);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.title_bar, this, true);

        title.setText(typedArray.getString(R.styleable.TitleBar_title_text));
        leftBtn.setText(typedArray.getString(R.styleable.TitleBar_left_btn_text));
        rightBtn.setText(typedArray.getString(R.styleable.TitleBar_right_btn_text));

        title.setTextColor(typedArray.getColor(R.styleable.TitleBar_title_text_color, Color.BLACK));
        leftBtn.setTextColor(typedArray.getColor(R.styleable.TitleBar_left_btn_text_color, Color.BLACK));
        rightBtn.setTextColor(typedArray.getColor(R.styleable.TitleBar_right_btn_text_color, Color.BLACK));

        title.setTextSize(typedArray.getDimension(R.styleable.TitleBar_title_text_size, 16));
        leftBtn.setTextSize(typedArray.getDimension(R.styleable.TitleBar_left_btn_text_size, 16));
        rightBtn.setTextSize(typedArray.getDimension(R.styleable.TitleBar_right_btn_text_size, 16));

        title.setBackgroundColor(typedArray.getColor(R.styleable.TitleBar_title_bg_color, Color.BLUE));

        boolean isHidedLeftImBtn = typedArray.getBoolean(R.styleable.TitleBar_hideLeftImBtn, false);
        if (isHidedLeftImBtn) {
            leftBtn.setVisibility(GONE);
        }

        boolean isHidedRightImBtn = typedArray.getBoolean(R.styleable.TitleBar_hideRightImBtn, false);
        if (isHidedRightImBtn) {
            rightBtn.setVisibility(GONE);
        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            leftBtn.setBackground(typedArray.getDrawable(R.styleable.TitleBar_left_btn_bg_color));
            rightBtn.setBackground(typedArray.getDrawable(R.styleable.TitleBar_right_btn_bg_color));
        } else {
            leftBtn.setBackgroundDrawable(typedArray.getDrawable(R.styleable.TitleBar_left_btn_bg_color));
            rightBtn.setBackgroundDrawable(typedArray.getDrawable(R.styleable.TitleBar_right_btn_bg_color));
        }

        typedArray.recycle();
    }

    @Override
    public void onClick(View v) {
        if (v.equals(leftBtn)) {
            leftBackClickListener.onLeftBtnClick();
        } else if (v.equals(rightBtn)) {
            rightClickListener.onRightClick();
        }
    }

    public void hindRight() {
        rightBtn.setVisibility(GONE);
    }

    public void hindLeft() {
        leftBtn.setVisibility(GONE);
    }

    public void showRight() {
        rightBtn.setVisibility(VISIBLE);
    }

    public void showLeft() {
        leftBtn.setVisibility(VISIBLE);
    }

    public void setOnLeftBackListener(LeftBackClickListener leftBackListener) {
        this.leftBackClickListener = leftBackListener;
    }

    public void setOnRightClickListener(RightClickListener rightClickListener) {
        this.rightClickListener = rightClickListener;
    }

    public interface LeftBackClickListener {
        void onLeftBtnClick();
    }

    public interface RightClickListener {
        void onRightClick();
    }


}
