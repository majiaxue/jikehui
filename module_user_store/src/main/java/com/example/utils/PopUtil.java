package com.example.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.bean.ParmsBean;
import com.example.bean.UserCouponBean;
import com.example.goods_detail.adapter.PopLingQuanAdapter;
import com.example.goods_detail.adapter.PopParmsAdapter;
import com.example.user_store.R;
import com.example.user_store.UserActivity;

import java.util.List;

public class PopUtil {
    public static void setTransparency(Context context, float value) {
        Activity activity = (Activity) context;
        Window window = activity.getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.alpha = value;
        window.setAttributes(params);
    }

    public static void lingquanPop(final Context context, final List<UserCouponBean> dataList, OnAdapterListener listener) {
        View view = LayoutInflater.from(context).inflate(R.layout.pop_lingquan, null);
        RecyclerView rv = view.findViewById(R.id.pop_lingquan_rv);
        TextView btn = view.findViewById(R.id.pop_lingquan_btn);
        final PopupWindow popupWindow = new PopupWindow(view, RelativeLayout.LayoutParams.MATCH_PARENT, (int) context.getResources().getDimension(R.dimen.dp_569), true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setAnimationStyle(R.style.pop_bottom_anim);
        popupWindow.showAtLocation(new View(context), Gravity.BOTTOM, 0, 0);


        setTransparency(context, 0.3f);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setTransparency(context, 1f);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(layoutManager);
        final PopLingQuanAdapter adapter = new PopLingQuanAdapter(context, dataList, R.layout.rv_pop_lingquan);
        rv.setAdapter(adapter);

        listener.setOnAdapterListener(popupWindow, adapter);
    }

    public static void ensurePop(final Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.pop_ensure, null);
        TextView btn = view.findViewById(R.id.pop_ensure_btn);
        TextView txt = view.findViewById(R.id.pop_ensure_txt);
        TxtUtil.txtJianbian(txt, "#feb60e", "#fb4419");

        final PopupWindow popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, (int) context.getResources().getDimension(R.dimen.dp_371), true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setAnimationStyle(R.style.pop_bottom_anim);
        popupWindow.showAtLocation(new View(context), Gravity.BOTTOM, 0, 0);

        setTransparency(context, 0.3f);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setTransparency(context, 1f);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }

    public static void parmsPop(final Context context, List<ParmsBean> dataList) {
        View view = LayoutInflater.from(context).inflate(R.layout.pop_parms, null);
        TextView title = view.findViewById(R.id.pop_parms_title);
        RecyclerView rv = view.findViewById(R.id.pop_parms_rv);
        TextView btn = view.findViewById(R.id.pop_parms_btn);
        TxtUtil.txtJianbian(title, "#feb60e", "#fb4419");

        final PopupWindow popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, (int) context.getResources().getDimension(R.dimen.dp_371), true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setAnimationStyle(R.style.pop_bottom_anim);
        popupWindow.showAtLocation(new View(context), Gravity.BOTTOM, 0, 0);

        setTransparency(context, 0.3f);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setTransparency(context, 1f);
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(layoutManager);
        PopParmsAdapter adapter = new PopParmsAdapter(context, dataList, R.layout.rv_pop_parms);
        rv.setAdapter(adapter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }

    public static void showMore(final Context context, View view, OnClearCacheListener listener) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.pop_shop_more, null);
        final RelativeLayout msg = inflate.findViewById(R.id.pop_shop_more_msg);
        final RelativeLayout home = inflate.findViewById(R.id.pop_shop_more_home);
        final RelativeLayout share = inflate.findViewById(R.id.pop_shop_more_share);
        ImageView msgImg = inflate.findViewById(R.id.pop_shop_more_msg_img);
        final PopupWindow popupWindow = new PopupWindow(inflate, (int) context.getResources().getDimension(R.dimen.dp_81), (int) context.getResources().getDimension(R.dimen.dp_117), true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAsDropDown(view, -(int) context.getResources().getDimension(R.dimen.dp_55), 0);

        setTransparency(context, 0.5f);
        listener.setOnClearCache(popupWindow, share);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setTransparency(context, 1f);
            }
        });

        msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/mine/messagecenter").navigation();
                popupWindow.dismiss();
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                context.startActivity(new Intent(context, UserActivity.class));
            }
        });
    }
}
