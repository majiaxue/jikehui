package com.example.refund;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.module_user_mine.R;

/**
 * 退款申请成功
 */
@Route(path = "/module_user_mine/RefundSucceedActivity")
public class RefundSucceedActivity extends Activity {
    private ImageView refundSucceedImageBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refund_succeed);

        refundSucceedImageBack = findViewById(R.id.refund_succeed_image_back);

        refundSucceedImageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
