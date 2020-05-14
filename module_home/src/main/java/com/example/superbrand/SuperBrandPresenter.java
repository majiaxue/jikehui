package com.example.superbrand;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.example.adapter.MyRecyclerAdapter;
import com.example.bean.SuperBean;
import com.example.bean.SuperBrandBean;
import com.example.common.CommonResource;
import com.example.module_home.R;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.superbrand.adapter.SuperBrandRecAdapter;
import com.example.superbrand.rests.RestsFragment;
import com.example.utils.LogUtil;
import com.example.utils.SPUtil;
import com.google.gson.Gson;
import com.kongzue.dialog.v3.WaitDialog;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * Created by cuihaohao on 2019/6/5
 * Describe:
 */
public class SuperBrandPresenter extends BasePresenter<SuperBrandView> {


    private String[] strArray = new String[]{"精选", "女装", "男装", "食品", "居家", "鞋品", "母婴", "数码", "美妆", "百货"};
    private List<SuperBean> stringList = new ArrayList<>();
    private List<Fragment> fragmentList = new ArrayList<>();
    private Map map = new HashMap<>();


    public SuperBrandPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void initView(final TabLayout superBrandTab, FragmentManager fm, ViewPager viewPager) {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(mContext.getAssets().open("classify.json"), "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            bufferedReader.close();
            inputStreamReader.close();
            JSONObject jsonObject = new JSONObject(stringBuilder.toString());
            JSONArray jsonArray = jsonObject.getJSONArray("classify");
            for (int i = 0; i < jsonArray.length(); i++) {

                SuperBean superBean = new Gson().fromJson(jsonArray.get(i).toString(), SuperBean.class);
                stringList.add(new SuperBean(superBean.getName(), superBean.getId(), superBean.getListId()));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < stringList.size(); i++) {
            superBrandTab.addTab(superBrandTab.newTab().setText(stringList.get(i).getName()));
//            if (i == 0){
//                HandPickFragment handPickFragment = new HandPickFragment();
//                fragmentList.add(handPickFragment);
//            }else{
            Fragment fragment = RestsFragment.newInstance(stringList.get(i).getListId());
            fragmentList.add(fragment);
//            }
        }
        //设置tab
        initIndicator(superBrandTab);

        IndexPagerAdapter indexPagerAdapter = new IndexPagerAdapter(fm, stringList, fragmentList);
        viewPager.setOffscreenPageLimit(8);
        viewPager.setAdapter(indexPagerAdapter);

        superBrandTab.setupWithViewPager(viewPager);

        superBrandTab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                int id = stringList.get(position).getId();
                initList(id);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    public void initList(int index) {
        Observable<ResponseBody> dataWithout = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).getDataWithout(CommonResource.SUPERGRAND + "/" + index);
        RetrofitUtil.getInstance().toSubscribe(dataWithout, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("超级品牌------>" + result);
                final List<SuperBrandBean> superBrandBeans = JSON.parseArray(result, SuperBrandBean.class);

                SuperBrandRecAdapter superBrandRecAdapter = new SuperBrandRecAdapter(mContext, superBrandBeans, R.layout.item_super_brand_rec);
                if (getView() != null) {
                    getView().loadAdapter(superBrandRecAdapter);
                }

                superBrandRecAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(RecyclerView parent, View view, int position) {
                        if (SPUtil.getToken() != null && !"".equals(SPUtil.getToken())) {
                            String shop_url = superBrandBeans.get(position).getAddress();
                            LogUtil.e("shop_url---------->" + shop_url);
                            ARouter.getInstance()
                                    .build("/module_classify/tshop_home")
                                    .withString("url", shop_url)
                                    .navigation();
                        } else {
                            ARouter.getInstance().build("/mine/login").navigation();
                        }
                    }
                });

            }

            @Override
            public void onError(String errorCode, String errorMsg) {

            }
        }));
    }

    class IndexPagerAdapter extends FragmentPagerAdapter {
        private List<SuperBean> titleList;
        private List<Fragment> fragmentList1;

        public IndexPagerAdapter(FragmentManager fm, List<SuperBean> titleList, List<Fragment> fragmentList) {
            super(fm);
            this.titleList = titleList;
            this.fragmentList1 = fragmentList;
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList1.get(position);
        }

        @Override
        public int getCount() {
            return titleList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titleList.get(position).getName();
        }
    }

    private void initIndicator(final TabLayout superBrandTab) {
        superBrandTab.post(new Runnable() {
            @Override
            public void run() {
                try {
                    //了解源码得知 线的宽度是根据 tabView的宽度来设置的
                    LinearLayout mTabStrip = (LinearLayout) superBrandTab.getChildAt(0);

                    for (int i = 0; i < mTabStrip.getChildCount(); i++) {
                        View tabView = mTabStrip.getChildAt(i);

                        //拿到tabView的mTextView属性  tab的字数不固定一定用反射取mTextView
                        Field mTextViewField =
                                tabView.getClass().getDeclaredField("mTextView");
                        mTextViewField.setAccessible(true);

                        TextView mTextView = (TextView) mTextViewField.get(tabView);

                        tabView.setPadding(0, 0, 0, 0);

                        //因为我想要的效果是   字多宽线就多宽，所以测量mTextView的宽度
                        int width = 0;
                        width = mTextView.getWidth();
                        if (width == 0) {
                            mTextView.measure(0, 0);
                            width = mTextView.getMeasuredWidth();
                        }

                        //设置tab左右间距为10dp  注意这里不能使用Padding
                        // 因为源码中线的宽度是根据 tabView的宽度来设置的
                        LinearLayout.LayoutParams params =
                                (LinearLayout.LayoutParams) tabView.getLayoutParams();
                        params.width = width;
                        tabView.setLayoutParams(params);

                        tabView.invalidate();
                    }

                } catch (Exception e) {

                }
            }
        });
    }


}
