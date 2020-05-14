package com.taobaoke.jikehui.jpush;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.PopupWindow;

import com.alibaba.fastjson.JSON;
import com.example.bean.OperatorBean;
import com.example.operator.OperatorActivity;
import com.example.upgrade.UpGradeActivity;
import com.example.utils.LogUtil;
import com.example.utils.OnClearCacheListener;
import com.example.utils.PopUtils;
import com.taobaoke.jikehui.bean.VipUpBean;

import org.json.JSONException;
import org.json.JSONObject;

import cn.jpush.android.api.CmdMessage;
import cn.jpush.android.api.CustomMessage;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.JPushMessage;
import cn.jpush.android.api.NotificationMessage;
import cn.jpush.android.service.JPushMessageReceiver;

public class PushMessageReceiver extends JPushMessageReceiver {

    @Override
    public void onMessage(Context context, CustomMessage customMessage) {
        LogUtil.e("[onMessage] " + customMessage);
        String jsonString = JSON.toJSONString(customMessage);
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            String title = jsonObject.optString("title");
            if ("会员升级".equals(title)) {
                String extra = jsonObject.optString("extra");
                String s = extra.replaceAll("\\\\", "");
                LogUtil.e("推送消息：" + s);
                VipUpBean upBean = JSON.parseObject(s, VipUpBean.class);
                String nextLevel = upBean.getNextLevel();

                String[] flag = {""};

                if ("=".indexOf(nextLevel) != -1) {
                    String[] split = nextLevel.split("=");
                    upBean.setNextLevel(split[1].replace("}", ""));
                    flag[0] = split[0].replace("{", "");
                }
                OperatorBean operatorBean = new OperatorBean();
                operatorBean.setNextLevel(upBean.getNextLevel());
                operatorBean.setId(upBean.getId());
                operatorBean.setName(upBean.getName());
                operatorBean.setPerCashs(upBean.getPerCashs());
                operatorBean.setSharePercent(upBean.getSharePercent());

                PopUtils.popUpSuccess(context, operatorBean, new OnClearCacheListener() {
                    @Override
                    public void setOnClearCache(PopupWindow pop, View confirm) {
                        confirm.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pop.dismiss();
                                if ("0".equals(flag[0])) {
                                    context.startActivity(new Intent(context, UpGradeActivity.class));
                                } else if ("1".equals(flag[0])) {
                                    context.startActivity(new Intent(context, OperatorActivity.class));
                                } else if ("3".equals(flag[0])) {

                                }
                            }
                        });
                    }
                });

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        processCustomMessage(context, customMessage);
    }

    @Override
    public void onNotifyMessageOpened(Context context, NotificationMessage message) {
        LogUtil.e("[onNotifyMessageOpened] " + message);
//        try {
//            //打开自定义的Activity
//            Intent i = new Intent(context, TestActivity.class);
//            Bundle bundle = new Bundle();
//            bundle.putString(JPushInterface.EXTRA_NOTIFICATION_TITLE, message.notificationTitle);
//            bundle.putString(JPushInterface.EXTRA_ALERT, message.notificationContent);
//            i.putExtras(bundle);
//            //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            context.startActivity(i);
//        } catch (Throwable throwable) {
//
//        }
    }

    @Override
    public void onMultiActionClicked(Context context, Intent intent) {
        LogUtil.e("[onMultiActionClicked] 用户点击了通知栏按钮");
        String nActionExtra = intent.getExtras().getString(JPushInterface.EXTRA_NOTIFICATION_ACTION_EXTRA);

        //开发者根据不同 Action 携带的 extra 字段来分配不同的动作。
        if (nActionExtra == null) {
            LogUtil.e("ACTION_NOTIFICATION_CLICK_ACTION nActionExtra is null");
            return;
        }
        if (nActionExtra.equals("my_extra1")) {
            LogUtil.e("[onMultiActionClicked] 用户点击通知栏按钮一");
        } else if (nActionExtra.equals("my_extra2")) {
            LogUtil.e("[onMultiActionClicked] 用户点击通知栏按钮二");
        } else if (nActionExtra.equals("my_extra3")) {
            LogUtil.e("[onMultiActionClicked] 用户点击通知栏按钮三");
        } else {
            LogUtil.e("[onMultiActionClicked] 用户点击通知栏按钮未定义");
        }
    }

    @Override
    public void onNotifyMessageArrived(Context context, NotificationMessage message) {
        LogUtil.e("[onNotifyMessageArrived] " + message);
    }

    @Override
    public void onNotifyMessageDismiss(Context context, NotificationMessage message) {
        LogUtil.e("[onNotifyMessageDismiss] " + message);
    }

    @Override
    public void onRegister(Context context, String registrationId) {
        LogUtil.e("[onRegister] " + registrationId);
    }

    @Override
    public void onConnected(Context context, boolean isConnected) {
        LogUtil.e("[onConnected] " + isConnected);
    }

    @Override
    public void onCommandResult(Context context, CmdMessage cmdMessage) {
        LogUtil.e("[onCommandResult] " + cmdMessage);
    }

    @Override
    public void onTagOperatorResult(Context context, JPushMessage jPushMessage) {
//        TagAliasOperatorHelper.getInstance().onTagOperatorResult(context, jPushMessage);
        super.onTagOperatorResult(context, jPushMessage);
    }

    @Override
    public void onCheckTagOperatorResult(Context context, JPushMessage jPushMessage) {
//        TagAliasOperatorHelper.getInstance().onCheckTagOperatorResult(context, jPushMessage);
        super.onCheckTagOperatorResult(context, jPushMessage);
    }

    @Override
    public void onAliasOperatorResult(Context context, JPushMessage jPushMessage) {
//        TagAliasOperatorHelper.getInstance().onAliasOperatorResult(context, jPushMessage);
        super.onAliasOperatorResult(context, jPushMessage);
    }

    @Override
    public void onMobileNumberOperatorResult(Context context, JPushMessage jPushMessage) {
//        TagAliasOperatorHelper.getInstance().onMobileNumberOperatorResult(context, jPushMessage);
        super.onMobileNumberOperatorResult(context, jPushMessage);
    }

    //send msg to MainActivity
    private void processCustomMessage(Context context, CustomMessage customMessage) {
//        if (MainActivity.isForeground) {
//            String message = customMessage.message;
//            String extras = customMessage.extra;
//            Intent msgIntent = new Intent(MainActivity.MESSAGE_RECEIVED_ACTION);
//            msgIntent.putExtra(MainActivity.KEY_MESSAGE, message);
//            if (!ExampleUtil.isEmpty(extras)) {
//                try {
//                    JSONObject extraJson = new JSONObject(extras);
//                    if (extraJson.length() > 0) {
//                        msgIntent.putExtra(MainActivity.KEY_EXTRAS, extras);
//                    }
//                } catch (JSONException e) {
//
//                }
//
//            }
//            LocalBroadcastManager.getInstance(context).sendBroadcast(msgIntent);
//        }
    }
}
