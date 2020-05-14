package com.example.main;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.example.bean.CheckUpBean;
import com.example.common.CommonResource;
import com.example.community.CommunityFragment;
import com.example.fix.Constants;
import com.example.fix.FileUtils;
import com.example.fix.FixDexUtils;
import com.example.home.HomeFragment;
import com.example.mine.MineFragment;
import com.example.module_home.R;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.operator_gain.OperatorGainFragment;
import com.example.superbrand.SuperBrandFragment;
import com.example.utils.AppManager;
import com.example.utils.LogUtil;
import com.example.utils.OnClearCacheListener;
import com.example.utils.PopUtils;
import com.example.utils.SPUtil;
import com.example.utils.net_change_util.NetStateChangeReceiver;
import com.example.view.SelfDialog;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public class MainPresenter extends BasePresenter<MainView> {
    //触碰标识
    private long exitTime = 0;

    private FragmentManager fragmentManager;
    private CommunityFragment communityFragment;
    private HomeFragment homeFragment;
    private MineFragment mineFragment;
//    private SuperBrandFragment superBrandFragment;
    private OperatorGainFragment operatorGainFragment;
    private ProgressBar mProgress;
    private AlertDialog alertDialog;
    private String clientVersion;
    private static final String savePath = "/sdcard/fltk/apk"; // apk保存到SD卡的路径
    private static final String saveFileName = savePath + "/fltk"; // 完整路径名

    private static final String patchPath = "/sdcard/fltk/patch"; // apk保存到SD卡的路径
    private static final String patchFileName = patchPath + "/classes_0.dex"; // 完整路径名
    private static final int DOWNLOADING = 1; // 表示正在下载
    private static final int DOWNLOADED = 2; // 下载完毕
    private static final int DOWNLOAD_FAILED = 3; // 下载失败
    private static final int PATCHEND = 4;

    private int progress; // 下载进度
    private boolean cancelFlag = false; // 取消下载标志位
    private CheckUpBean checkUpBean;
    private NetStateChangeReceiver receiver;
    private String newVersion;


    public MainPresenter(Context context) {
        super(context);
    }

    /**
     * 更新UI的handler
     */
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case DOWNLOADING:
                    mProgress.setProgress(progress);
                    break;
                case DOWNLOADED:
                    if (alertDialog != null)
                        alertDialog.dismiss();
                    initInstall();
                    break;
                case DOWNLOAD_FAILED:
                    Toast.makeText(mContext, "下载失败", Toast.LENGTH_LONG).show();
                    try {
                        String apkFile = saveFileName + newVersion + ".apk";
                        File file = new File(apkFile);
                        file.delete();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case PATCHEND:
                    hotFix();
//                    Beta.canAutoPatch = true;
//                    Beta.applyDownloadedPatch();
                    break;
                default:
                    break;
            }
        }
    };

    public void loadData(FragmentManager fragmentManager, int resId) {
        this.fragmentManager = fragmentManager;
        communityFragment = new CommunityFragment();
        homeFragment = new HomeFragment();
        mineFragment = new MineFragment();
//        superBrandFragment = new SuperBrandFragment();
        operatorGainFragment = new OperatorGainFragment();

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(resId, communityFragment)
                .add(resId, homeFragment)
                .add(resId, mineFragment)
                .add(resId, operatorGainFragment);
//                .add(resId, superBrandFragment);
        transaction.show(homeFragment)
                .hide(communityFragment)
                .hide(mineFragment)
//                .hide(superBrandFragment)
                .hide(operatorGainFragment)
                .commit();

    }

    public void click(int resId) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        if (resId == R.id.main_home) {
            transaction.show(homeFragment)
                    .hide(communityFragment)
                    .hide(mineFragment)
//                    .hide(superBrandFragment)
                    .hide(operatorGainFragment)
                    .commit();

        }
//        else if (resId == R.id.main_classify) {
//            transaction.show(superBrandFragment)
//                    .hide(communityFragment)
//                    .hide(homeFragment)
//                    .hide(mineFragment)
//                    .hide(operatorGainFragment)
//                    .commit();
//
//        }
        else if (resId == R.id.main_mine) {
            transaction.show(mineFragment)
                    .hide(communityFragment)
//                    .hide(superBrandFragment)
                    .hide(homeFragment)
                    .hide(operatorGainFragment)
                    .commit();

        } else if (resId == R.id.main_community) {
            transaction.show(communityFragment)
                    .hide(mineFragment)
//                    .hide(superBrandFragment)
                    .hide(homeFragment)
                    .hide(operatorGainFragment)
                    .commit();
        } else if (resId == R.id.main_operator) {
            if (TextUtils.isEmpty(SPUtil.getToken())) {
                getView().toHome();
                ARouter.getInstance().build("/mine/login").navigation();
            } else {
                transaction.show(operatorGainFragment)
                        .hide(mineFragment)
//                        .hide(superBrandFragment)
                        .hide(homeFragment)
                        .hide(communityFragment)
                        .commit();
            }
        }

    }

    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(mContext, "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            AppManager.getInstance().AppExit();
            System.exit(0);
        }
    }

    @Override
    protected void onViewDestroy() {
        mContext.unregisterReceiver(receiver);
        EventBus.getDefault().unregister(this);
        SPUtil.addParm(CommonResource.TAN_CONTENT, "");
    }

    public void registerReceiver() {
        receiver = new NetStateChangeReceiver();
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        mContext.registerReceiver(receiver, intentFilter);
    }

    private void initInstall() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            boolean hasInstallPermission = isHasInstallPermissionWithO(mContext);
            if (!hasInstallPermission) {
                startInstallPermissionSettingActivity(mContext);
            } else {
                installAPK();
            }
        }
    }

    /**
     * 开启设置安装未知来源应用权限界面
     *
     * @param context
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void startInstallPermissionSettingActivity(Context context) {
        if (context == null) {
            return;
        }
        final SelfDialog selfDialog = new SelfDialog(mContext);
        selfDialog.setTitle("提示");
        selfDialog.setMessage("未开启自动安装，无法更新程序");
        selfDialog.setNoOnclickListener("前往设置", new SelfDialog.onNoOnclickListener() {
            @Override
            public void onNoClick() {
                selfDialog.dismiss();
                Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES);
                ((Activity) mContext).startActivityForResult(intent, 0x111);
            }
        });
        selfDialog.setYesOnclickListener("取消", new SelfDialog.onYesOnclickListener() {
            @Override
            public void onYesClick() {
                selfDialog.dismiss();
            }
        });
        selfDialog.show();
        PopUtils.setTransparency(mContext, 0.3f);
        selfDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                PopUtils.setTransparency(mContext, 1.0f);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private boolean isHasInstallPermissionWithO(Context context) {
        if (context == null) {
            return false;
        }
        return context.getPackageManager().canRequestPackageInstalls();
    }

    public void checkUp() {
        getVersionInfo();
        Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9005).getDataWithout(CommonResource.CHECKUP);
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("检查更新：" + result);
                checkUpBean = JSON.parseObject(result, CheckUpBean.class);
                String[] split = clientVersion.split("\\.");
                newVersion = checkUpBean.getVersion();

                if (newVersion != null) {
                    String[] split1 = newVersion.split("\\.");
                    File apkFile = new File(saveFileName + newVersion + ".apk");
                    if ((Integer.valueOf(split[0]) < Integer.valueOf(split1[0])) || (Integer.valueOf(split[0]) == Integer.valueOf(split1[0]) && Integer.valueOf(split[1]) < Integer.valueOf(split1[1]))) {
                        if (apkFile.exists()) {
                            PopUtils.update(mContext, newVersion, checkUpBean.getAppDescribe(), checkUpBean.getIsForce(), true, new OnClearCacheListener() {
                                @Override
                                public void setOnClearCache(final PopupWindow pop, View confirm) {
                                    confirm.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            installAPK();
                                            pop.dismiss();
                                        }
                                    });
                                }
                            });
                        } else {
                            PopUtils.update(mContext, newVersion, checkUpBean.getAppDescribe(), checkUpBean.getIsForce(), false, new OnClearCacheListener() {
                                @Override
                                public void setOnClearCache(final PopupWindow pop, View confirm) {
                                    confirm.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            writeToDisk(checkUpBean.getUrl());
                                            showDialog();
                                            pop.dismiss();
                                        }
                                    });
                                }
                            });
                        }

                    } else if (Integer.valueOf(split[0]) == Integer.valueOf(split1[0]) && Integer.valueOf(split[1]) == Integer.valueOf(split1[1]) && Integer.valueOf(split[2]) < Integer.valueOf(split1[2])) {
                        if (apkFile.exists()) {
                            PopUtils.update(mContext, newVersion, checkUpBean.getAppDescribe(), checkUpBean.getIsForce(), true, new OnClearCacheListener() {
                                @Override
                                public void setOnClearCache(final PopupWindow pop, View confirm) {
                                    confirm.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            installAPK();
                                            pop.dismiss();
                                        }
                                    });
                                }
                            });
                        } else {
                            PopUtils.update(mContext, newVersion, checkUpBean.getAppDescribe(), checkUpBean.getIsForce(), false, new OnClearCacheListener() {
                                @Override
                                public void setOnClearCache(final PopupWindow pop, View confirm) {
                                    confirm.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            writeToDisk(checkUpBean.getUrl());
                                            showDialog();
                                            pop.dismiss();
                                        }
                                    });
                                }
                            });
                        }
                    }
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("更新：" + errorCode + "------------" + errorMsg);
            }
        }));
    }

    private void getVersionInfo() {

        PackageManager pm = mContext.getPackageManager();

        try {
            // 0代表拿所有的信息 packageInfo 是一个bean对象 是对整个清单文件的封装
            // ApplicationInfo是PackageInfo的子集
            PackageInfo packageInfo = pm.getPackageInfo(mContext.getPackageName(), 0);
            clientVersion = packageInfo.versionName;
            LogUtil.e("当前版本：" + clientVersion);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("正在更新");
        final LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(R.layout.dialog_update, null);
        mProgress = (ProgressBar) v.findViewById(R.id.update_progress);
        builder.setView(v);
        alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();
    }

    public void writeToDisk(final String apkUrl) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(apkUrl);
                    HttpURLConnection conn = (HttpURLConnection) url
                            .openConnection();
                    conn.connect();

                    int length = conn.getContentLength();
                    InputStream is = conn.getInputStream();

                    File file = new File(savePath);
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    String apkFile = saveFileName + newVersion + ".apk";
                    File ApkFile = new File(apkFile);
                    FileOutputStream fos = new FileOutputStream(ApkFile);

                    int count = 0;
                    byte buf[] = new byte[64];

                    do {
                        int numread = is.read(buf);
                        count += numread;
                        progress = (int) (((float) count / length) * 100);
                        // 更新进度
                        mHandler.sendEmptyMessage(DOWNLOADING);
                        if (numread <= 0) {
                            // 下载完成通知安装
                            mHandler.sendEmptyMessage(DOWNLOADED);
                            break;
                        }
                        fos.write(buf, 0, numread);
                    } while (!cancelFlag); // 点击取消就停止下载.

                    fos.close();
                    is.close();
                } catch (Exception e) {
                    mHandler.sendEmptyMessage(DOWNLOAD_FAILED);
                    LogUtil.e("------>" + e.getMessage());
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 下载完成后自动安装apk
     */
    public void installAPK() {
        File apkFile = new File(saveFileName + newVersion + ".apk");
        if (!apkFile.exists()) {
            apkFile.mkdirs();
        }

        //解决部分手机调用安装器的时候没有读写权限
        if (Build.VERSION.SDK_INT >= 24) {//判读版本是否在7.0以上
            String[] command = {"chmod", "777", apkFile.getPath()};
            ProcessBuilder builder = new ProcessBuilder(command);
            try {
                builder.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        //判断是否是AndroidN以及更高的版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(mContext, mContext.getPackageName(), apkFile);
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        } else {
            String path = apkFile.getAbsolutePath();
            Uri uri = Uri.parse("file://" + path);
           // intent.setDataAndType(Uri.fromFile(new File(mContext.getCacheDir(),"file://"+path)), "application/vnd.android.package-archive");
           intent.setDataAndType(uri, "application/vnd.android.package-archive");
        }
        mContext.startActivity(intent);
    }

    private void downLoadPatch(final String patchUrl) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(patchUrl);
                    HttpURLConnection conn = (HttpURLConnection) url
                            .openConnection();
                    conn.connect();

                    int length = conn.getContentLength();
                    InputStream is = conn.getInputStream();

                    File file = new File(patchPath);
                    if (!file.exists()) {
                        file.mkdir();
                    }
                    String apkFile = patchFileName;
                    File ApkFile = new File(apkFile);
                    FileOutputStream fos = new FileOutputStream(ApkFile);

                    int count = 0;
                    byte buf[] = new byte[64];

                    do {
                        int numread = is.read(buf);
                        count += numread;
                        progress = (int) (((float) count / length) * 100);
                        // 更新进度
                        mHandler.sendEmptyMessage(DOWNLOADING);
                        if (numread <= 0) {
                            // 下载完成通知安装
                            mHandler.sendEmptyMessage(PATCHEND);
                            break;
                        }
                        fos.write(buf, 0, numread);
                    } while (!cancelFlag); // 点击取消就停止下载.

                    fos.close();
                    is.close();
                } catch (Exception e) {
                    mHandler.sendEmptyMessage(DOWNLOAD_FAILED);
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void hotFix() {
        //修复包
        File sourceFile = new File(patchFileName);
        // 私有目录
        File targetFile = new File(mContext.getDir(Constants.DEX_DIR, Context.MODE_PRIVATE).getAbsolutePath() + File.separator + Constants.DEX_NAME);

        // 如果存在之前修复过的dex
        if (targetFile.exists()) {
            targetFile.delete();
            Toast.makeText(mContext, "删除成功", Toast.LENGTH_SHORT);
        }

        Toast.makeText(mContext, "开始修复", Toast.LENGTH_SHORT).show();
        // 将下载的修复包复制到私有目录，然后再解压
        try {
            FileUtils.copyFile(sourceFile, targetFile);

            // 开始修复
            FixDexUtils.loadFixedDex(mContext);
            Toast.makeText(mContext, "修复成功", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
