package com.example.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
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
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.PopCouponAdapter;
import com.example.adapter.PopQuanyiAdapter;
import com.example.adapter.PopUpAdapter;
import com.example.bean.LocalStoreBean;
import com.example.bean.OperatorBean;
import com.example.bean.UserCouponBean;
import com.example.common.CommonResource;
import com.example.module_base.R;
import com.example.utils.adapter.VPBigPicAdapter;

import java.util.ArrayList;
import java.util.List;

public class PopUtils {
    public static void setTransparency(Context context, float value) {
        Activity activity = (Activity) context;
        Window window = activity.getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.alpha = value;
        window.setAttributes(params);
    }

    public static void createPop(final Context context, View view, int width, int height, OnPopListener listener) {
        PopupWindow popupWindow = new PopupWindow(view, width, height, true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setAnimationStyle(R.style.pop_bottom_anim);
        popupWindow.showAtLocation(new View(context), Gravity.BOTTOM, 0, 0);
        setTransparency(context, 0.3f);
        listener.setOnPop(popupWindow);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setTransparency(context, 1f);
            }
        });
    }

    public static void createPopCenter(final Context context, View view, int width, int height, OnPopListener listener) {
        PopupWindow popupWindow = new PopupWindow(view, width, height, true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setAnimationStyle(R.style.animScale);
        popupWindow.showAtLocation(new View(context), Gravity.CENTER, 0, 0);
        setTransparency(context, 0.3f);
        listener.setOnPop(popupWindow);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setTransparency(context, 1f);
            }
        });
    }

    public static void viewPopBottom(View text, final Context context, View view, int width, int height, OnPopListener listener) {
        PopupWindow popupWindow = new PopupWindow(view, width, height, true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        popupWindow.setAnimationStyle(R.style.animScale);
//        popupWindow.showAtLocation(new View(context), Gravity.BOTTOM, 0, 0);
        popupWindow.showAsDropDown(text, 0, 0, Gravity.BOTTOM);
        setTransparency(context, 0.3f);
        listener.setOnPop(popupWindow);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setTransparency(context, 1f);
            }
        });
    }


    public static void changeHeader(final Context mContext, OnChangeHeaderListener listener) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.pop_change_header, null);
        TextView takePhoto = view.findViewById(R.id.pop_change_header_camera);
        TextView photoAlbum = view.findViewById(R.id.pop_change_header_xiangce);
        TextView cancel = view.findViewById(R.id.pop_change_header_cancel);

        final PopupWindow popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.showAtLocation(new View(mContext), Gravity.BOTTOM, 0, 0);
        setTransparency(mContext, 0.3f);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setTransparency(mContext, 1f);
            }
        });

        listener.setOnChangeHeader(popupWindow, takePhoto, photoAlbum);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }

    public static void seeBigImg(final Context context, String url) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.pop_full_image, null);
        ImageView img = inflate.findViewById(R.id.pop_full_img);
        Glide.with(context).load(url).into(img);

        final PopupWindow popupWindow = new PopupWindow(inflate, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.showAtLocation(new View(context), Gravity.CENTER, 0, 0);
        PopUtils.setTransparency(context, 0.3f);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                PopUtils.setTransparency(context, 1f);
            }
        });

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }

    public static void popAssessBigPic(final Context context, List<String> list, int position) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.pop_vp_big_pic, null);
        ViewPager vp = inflate.findViewById(R.id.pop_vp_big_pic_vp);

        final PopupWindow popupWindow = new PopupWindow(inflate, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setAnimationStyle(R.style.animScale);
        popupWindow.showAtLocation(new View(context), Gravity.CENTER, 0, 0);

        List<View> viewList = new ArrayList<>();
        for (String url : list) {
            ImageView imageView = new ImageView(context);
            Glide.with(context).load(url).into(imageView);
            viewList.add(imageView);
        }
        vp.setOffscreenPageLimit(list.size() - 1);
        VPBigPicAdapter adapter = new VPBigPicAdapter(viewList, new OnViewListener() {
            @Override
            public void setOnViewListener(View view) {
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
            }
        });
        vp.setAdapter(adapter);
        vp.setCurrentItem(position);

        setTransparency(context, 0.3f);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setTransparency(context, 1f);
            }
        });

        inflate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }


    public static void popQuanYi(final Context mContext, final List<OperatorBean> beanList, final int position, OnClearCacheListener listener) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.pop_quanyi, null);
        TextView title = view.findViewById(R.id.pop_quanyi_level);
        RecyclerView rv = view.findViewById(R.id.pop_quanyi_rv);
        TextView btn = view.findViewById(R.id.pop_quanyi_btn);
        ImageView cancel = view.findViewById(R.id.pop_quanyi_cancel);

        final PopupWindow popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setAnimationStyle(com.example.module_base.R.style.animScale);
        popupWindow.showAtLocation(new View(mContext), Gravity.CENTER, 0, 0);
        PopUtils.setTransparency(mContext, 0.3f);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                PopUtils.setTransparency(mContext, 1f);
            }
        });

        AssetManager assets = mContext.getAssets();
        Typeface typeface = Typeface.createFromAsset(assets, "fonts/MyFont.TTF");
        title.setTypeface(typeface);

        TxtUtil.txtJianbian(title, "#a26335", "#c97d3b");
        if (beanList.get(position).getName().length() > 4) {
            title.setText(beanList.get(position).getName());
        } else {
            title.setText("升级为" + beanList.get(position).getName());
        }
        GridLayoutManager layoutManager = new GridLayoutManager(mContext, 3);
        rv.setLayoutManager(layoutManager);

        int temp = 0;
        for (int i = 0; i < beanList.size(); i++) {
            if (beanList.get(i).getId().equals(SPUtil.getStringValue(CommonResource.LEVELID))) {
                temp = i;
                break;
            }
        }

        String perCashs = beanList.get(position).getPerCashs();
        List<String> list = new ArrayList<>();
        list.add("自买返佣" + beanList.get(position).getSharePercent() + "%");
        list.add("分享返佣" + beanList.get(position).getSharePercent() + "%");
        if (perCashs == null) {
            list.add("直接会员出单奖励" + "0%");
            list.add("平级奖励" + "0%");
            if (temp == 0) {
                list.add("团队奖励" + beanList.get(position).getSharePercent() + "%");
            } else {
                list.add("团队奖励" + ArithUtil.sub(Double.valueOf(beanList.get(position).getSharePercent()), Double.valueOf(beanList.get(temp).getSharePercent())) + "%");
            }
            list.add("间接会员出单奖励" + "0%");
        } else {
            String[] split = perCashs.split(",");
            list.add("直接会员出单奖励" + split[0] + "%");
            list.add("平级奖励" + "0%");
            if (temp == 0) {
                list.add("团队奖励" + beanList.get(position).getSharePercent() + "%");
            } else {
                list.add("团队奖励" + ArithUtil.sub(Double.valueOf(beanList.get(position).getSharePercent()), Double.valueOf(beanList.get(temp).getSharePercent())) + "%");
            }
            if (split.length == 1) {
                list.add("间接会员出单奖励" + "0%");
            } else {
                list.add("间接会员出单奖励" + split[1] + "%");
            }
        }
        PopQuanyiAdapter quanyiAdapter = new PopQuanyiAdapter(mContext, list, R.layout.rv_pop_quanyi);
        rv.setAdapter(quanyiAdapter);


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        listener.setOnClearCache(popupWindow, btn);
    }

    public static void popUpSuccess(final Context context, OperatorBean bean, OnClearCacheListener listener) {
        View view = LayoutInflater.from(context).inflate(R.layout.pop_up_success, null);
        TextView level = view.findViewById(R.id.pop_up_level);
        TextView btn = view.findViewById(R.id.pop_up_btn);
        RecyclerView rv = view.findViewById(R.id.pop_up_rv);
        ImageView cancel = view.findViewById(R.id.pop_up_cancel);
        TextView txt = view.findViewById(R.id.pop_up_txt);

        final PopupWindow popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setAnimationStyle(com.example.module_base.R.style.animScale);
        popupWindow.showAtLocation(new View(context), Gravity.CENTER, 0, 0);
        PopUtils.setTransparency(context, 0.3f);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                PopUtils.setTransparency(context, 1f);
            }
        });

        AssetManager assets = context.getAssets();
        Typeface typeface = Typeface.createFromAsset(assets, "fonts/MyFont.TTF");
        level.setTypeface(typeface);
        txt.setTypeface(typeface);

        btn.setText("升级" + bean.getNextLevel() + "身份");
        TxtUtil.txtJianbian(level, "#a26335", "#c97d3b");
        level.setText("成为" + bean.getName());
        GridLayoutManager layoutManager = new GridLayoutManager(context, 3);
        rv.setLayoutManager(layoutManager);

        String perCashs = bean.getPerCashs();
        List<String> list = new ArrayList<>();
        list.add("自买返佣" + bean.getSharePercent() + "%");
        list.add("分享返佣" + bean.getSharePercent() + "%");
        if (perCashs == null) {
            list.add("直接会员出单奖励" + "0%");
        } else {
            String[] split = perCashs.split(",");
            list.add("直接会员出单奖励" + split[0] + "%");
        }

        PopUpAdapter adapter = new PopUpAdapter(context, list, R.layout.rv_pop_quanyi);
        rv.setAdapter(adapter);


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        listener.setOnClearCache(popupWindow, btn);
    }

    public static void popLiBaoQuanYi(final Context mContext, final OperatorBean bean, OnClearCacheListener listener) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.pop_quanyi, null);
        TextView title = view.findViewById(R.id.pop_quanyi_level);
        RecyclerView rv = view.findViewById(R.id.pop_quanyi_rv);
        TextView btn = view.findViewById(R.id.pop_quanyi_btn);
        ImageView cancel = view.findViewById(R.id.pop_quanyi_cancel);

        final PopupWindow popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setAnimationStyle(com.example.module_base.R.style.animScale);
        popupWindow.showAtLocation(new View(mContext), Gravity.CENTER, 0, 0);
        PopUtils.setTransparency(mContext, 0.3f);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                PopUtils.setTransparency(mContext, 1f);
            }
        });

        AssetManager assets = mContext.getAssets();
        Typeface typeface = Typeface.createFromAsset(assets, "fonts/MyFont.TTF");
        title.setTypeface(typeface);

        TxtUtil.txtJianbian(title, "#a26335", "#c97d3b");
        if (bean.getName().length() > 4) {
            title.setText(bean.getName());
        } else {
            title.setText("升级为" + bean.getName());
        }
        GridLayoutManager layoutManager = new GridLayoutManager(mContext, 3);
        rv.setLayoutManager(layoutManager);

        String perCashs = bean.getPerCashs();
        List<String> list = new ArrayList<>();
        list.add("自买返佣" + bean.getSharePercent() + "%");
        list.add("分享返佣" + bean.getSharePercent() + "%");
        if (perCashs == null) {
            list.add("直接会员出单奖励" + "0%");
            list.add("平级奖励" + "0%");

            list.add("团队奖励" + bean.getSharePercent() + "%");

            list.add("间接会员出单奖励" + "0%");
        } else {
            String[] split = perCashs.split(",");
            list.add("直接会员出单奖励" + split[0] + "%");
            list.add("平级奖励" + "0%");

            list.add("团队奖励" + bean.getSharePercent() + "%");

            if (split.length == 1) {
                list.add("间接会员出单奖励" + "0%");
            } else {
                list.add("间接会员出单奖励" + split[1] + "%");
            }
        }
        PopQuanyiAdapter quanyiAdapter = new PopQuanyiAdapter(mContext, list, R.layout.rv_pop_quanyi);
        rv.setAdapter(quanyiAdapter);


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        listener.setOnClearCache(popupWindow, btn);
    }

    public static void isLogin(final Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.pop_wei_deng_lu, null);
        TextView goToLogin = inflate.findViewById(R.id.pop_wei_deng_lu_go_to_login);
        TextView register = inflate.findViewById(R.id.pop_wei_deng_lu_register);
        ImageView cancelLogin = inflate.findViewById(R.id.pop_wei_deng_lu_cancel);

        final PopupWindow popupWindow = new PopupWindow(inflate, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.showAtLocation(new View(context), Gravity.CENTER, 0, 0);
        PopUtils.setTransparency(context, 0.3f);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                PopUtils.setTransparency(context, 1f);
            }
        });

        goToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/mine/login").navigation();
                popupWindow.dismiss();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/module_mine/RegisterActivity").navigation();
                popupWindow.dismiss();
            }
        });

        cancelLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }


    public static void update(final Context context, String versions, String message, final String isQiangzhi, boolean isHas, OnClearCacheListener listener) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.pop_geng_xin, null);
        TextView versionsText = inflate.findViewById(R.id.pop_gen_xin_versions);
        TextView messageText = inflate.findViewById(R.id.pop_gen_xin_text_message);
        TextView updateBottom = inflate.findViewById(R.id.pop_gen_xin_bottom);
        ImageView img = inflate.findViewById(R.id.pop_gen_xin_cancel);
        versionsText.setText("V" + versions);
        messageText.setText(message);
        if (isHas) {
            updateBottom.setText("立即安装");
        }
        final PopupWindow popupWindow = new PopupWindow(inflate, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
        popupWindow.setOutsideTouchable(false);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.showAtLocation(new View(context), Gravity.CENTER, 0, 0);
        PopUtils.setTransparency(context, 0.3f);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                PopUtils.setTransparency(context, 1f);
            }
        });

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("0".equals(isQiangzhi)) {
                    popupWindow.dismiss();
                } else {
                    popupWindow.dismiss();
                    AppManager.getInstance().AppExit();
                }
            }
        });

        listener.setOnClearCache(popupWindow, updateBottom);
    }

    public static void location(final Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.pop_ding_wei, null);
        ImageView image = inflate.findViewById(R.id.pop_ding_wei_image);
        TextView bottom = inflate.findViewById(R.id.pop_ding_wei_bottom);

        final PopupWindow popupWindow = new PopupWindow(inflate, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
        popupWindow.setOutsideTouchable(false);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.showAtLocation(new View(context), Gravity.CENTER, 0, 0);
        PopUtils.setTransparency(context, 0.3f);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                PopUtils.setTransparency(context, 1f);
            }
        });


        bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setAction(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//                intent.setData(Uri.parse("package:" + context.getPackageName()));
//                context.startActivity(intent);

                // 转到手机设置界面，用户设置GPS
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                context.startActivity(intent); // 设置完成后返回到原来的界面
                popupWindow.dismiss();
            }
        });

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

    }

    public static void popClipboard(final Context context, final String content) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.pop_clipboard, null);
        TextView txt = inflate.findViewById(R.id.pop_clipboard_content);
        ImageView tb = inflate.findViewById(R.id.pop_clipboard_tb);
        ImageView cancel = inflate.findViewById(R.id.pop_clipboard_cancel);
        final ImageView pdd = inflate.findViewById(R.id.pop_clipboard_pdd);
        final ImageView jd = inflate.findViewById(R.id.pop_clipboard_jd);

        txt.setText(content);

        final PopupWindow popupWindow = new PopupWindow(inflate, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setOutsideTouchable(false);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.showAtLocation(new View(context), Gravity.CENTER, 0, 0);
        PopUtils.setTransparency(context, 0.3f);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                PopUtils.setTransparency(context, 1f);
            }
        });

        tb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/module_classify/ClassificationDetailsActivity").withInt("position", 0).withString("searchContent", content).navigation();
                popupWindow.dismiss();
            }
        });

        pdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/module_classify/ClassificationDetailsActivity").withInt("position", 1).withString("searchContent", content).navigation();
                popupWindow.dismiss();
            }
        });

        jd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/module_classify/ClassificationDetailsActivity").withInt("position", 2).withString("searchContent", content).navigation();
                popupWindow.dismiss();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }

    public static void popChooseSpecs(final Context context, LocalStoreBean.ListBean commodity) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.pop_choose_specs, null);
        ImageView img = inflate.findViewById(R.id.pop_choose_specs_img);
        ImageView cancel = inflate.findViewById(R.id.pop_choose_specs_cancel);
        TextView name = inflate.findViewById(R.id.pop_choose_specs_name);
        TextView price = inflate.findViewById(R.id.pop_choose_specs_price);
        TextView btn = inflate.findViewById(R.id.pop_choose_specs_btn);
        RecyclerView rv = inflate.findViewById(R.id.pop_choose_specs_rv);

        String parameter = commodity.getParameter();
        String specification = commodity.getSpecification();
        String jsonString = JSON.toJSONString(parameter);
    }

    public static void youhuiquan(final Context context, List<UserCouponBean> couponBeanList) {
        View view = LayoutInflater.from(context).inflate(R.layout.pop_youhuiquan, null);
        ImageView couponClose = view.findViewById(R.id.pop_coupon_close);
        RecyclerView couponRec = view.findViewById(R.id.pop_coupon_rec);

        final PopupWindow popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, (int) context.getResources().getDimension(R.dimen.dp_410), true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setAnimationStyle(R.style.pop_bottom_anim);
        popupWindow.showAtLocation(new View(context), Gravity.BOTTOM, 0, 0);

        PopUtils.setTransparency(context, 0.3f);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                PopUtils.setTransparency(context, 1f);
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        couponRec.setLayoutManager(linearLayoutManager);
        PopCouponAdapter popCouponAdapter = new PopCouponAdapter(context, couponBeanList, R.layout.item_coupon_rec1);
        couponRec.setAdapter(popCouponAdapter);

        popCouponAdapter.setViewOnClickListener(new MyRecyclerAdapter.ViewOnClickListener() {
            @Override
            public void ViewOnClick(View view, int index) {
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "领取成功", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        couponClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }
}
