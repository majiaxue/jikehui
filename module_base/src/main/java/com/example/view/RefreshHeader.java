package com.example.view;

import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.view.View;

import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;

/**
 * Created by cuihaohao on 2019/5/17
 * Describe:
 */
public interface RefreshHeader extends com.scwang.smartrefresh.layout.api.RefreshHeader {
    /**
     * 获取真实视图（必须返回，不能为null）
     */
    @NonNull
    View getView();

    /**
     * 获取变换方式（必须指定一个：平移、拉伸、固定、全屏）
     */
    SpinnerStyle getSpinnerStyle();

    /**
     * 设置主题颜色 （如果自定义的Header没有注意颜色，本方法可以什么都不处理）
     * @param colors 对应Xml中配置的 srlPrimaryColor srlAccentColor
     */
    void setPrimaryColors(@ColorInt int... colors);

    /**
     * 尺寸定义初始化完成 （如果高度不改变（代码修改：setHeader），只调用一次, 在RefreshLayout#onMeasure中调用）
     * @param kernel RefreshKernel 核心接口（用于完成高级Header功能）
     * @param height HeaderHeight or FooterHeight
     * @param maxDragHeight 最大拖动高度
     */
    void onInitialized(RefreshKernel kernel, int height, int maxDragHeight);

    /**
     * 开始动画（开始刷新或者开始加载动画）
     * @param layout RefreshLayout
     * @param height HeaderHeight or FooterHeight
     * @param maxDragHeight 最大拖动高度
     */
    void onStartAnimator(RefreshLayout layout, int height, int maxDragHeight);

    /**
     * 动画结束
     * @param layout RefreshLayout
     * @param success 数据是否成功刷新或加载
     * @return 完成动画所需时间 如果返回 Integer.MAX_VALUE 将取消本次完成事件，继续保持原有状态
     */
    int onFinish(RefreshLayout layout, boolean success);

    /**
     * 手指拖动下拉（会连续多次调用，用于实时控制动画关键帧）
     * @param percent 下拉的百分比 值 = offset/headerHeight (0 - percent - (headerHeight+maxDragHeight) / headerHeight )
     * @param offset 下拉的像素偏移量  0 - offset - (headerHeight+maxDragHeight)
     * @param headerHeight Header的高度
     * @param maxDragHeight 最大拖动高度
     */
    void onPulling(float percent, int offset, int headerHeight, int maxDragHeight);

    /**
     * 手指释放之后的持续动画（会连续多次调用，用于实时控制动画关键帧）
     * @param percent 下拉的百分比 值 = offset/headerHeight (0 - percent - (headerHeight+maxDragHeight) / headerHeight )
     * @param offset 下拉的像素偏移量  0 - offset - (headerHeight+maxDragHeight)
     * @param headerHeight Header的高度
     * @param maxDragHeight 最大拖动高度
     */
    void onReleasing(float percent, int offset, int headerHeight, int maxDragHeight);

}
