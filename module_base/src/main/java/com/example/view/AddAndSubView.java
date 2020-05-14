package com.example.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.module_base.R;
import com.example.module_base.R2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by cuihaohao on 2019/5/24
 * Describe:
 */
public class AddAndSubView extends LinearLayout implements View.OnClickListener {

    @BindView(R2.id.tv_sub)
    TextView tvSub;
    @BindView(R2.id.tv_number)
    TextView tvNumber;
    @BindView(R2.id.tv_add)
    TextView tvAdd;

    private int number = 1;

    public AddAndSubView(Context context) {
        super(context, null);
    }

    public AddAndSubView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View view = inflate(context, R.layout.item_adder_subtract, this);
        ButterKnife.bind(view);

        tvSub.setOnClickListener(this);
        tvAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_sub) {
            if (number > 1) {
                --number;
                tvNumber.setText(number + "");
                if (onNumberChangeListener != null) {
                    onNumberChangeListener.onNumberChange(number);
                }
            } else {
                Toast.makeText(getContext(), "最少购买一件哦!", Toast.LENGTH_LONG).show();
            }
        } else {
            ++number;
            tvNumber.setText(number + "");
            if (onNumberChangeListener != null) {
                onNumberChangeListener.onNumberChange(number);
            }
        }
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
        tvNumber.setText(number + "");
    }

    OnNumberChangeListener onNumberChangeListener;

    public void setOnNumberChangeListener(OnNumberChangeListener onNumberChangeListener) {
        this.onNumberChangeListener = onNumberChangeListener;
    }

    public interface OnNumberChangeListener {
        void onNumberChange(int num);
    }

}
