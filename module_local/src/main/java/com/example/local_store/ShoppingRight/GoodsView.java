package com.example.local_store.ShoppingRight;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.example.module_local.R;


public class GoodsView extends View {

    //小红点开始坐标
    Point mCircleStartPoint = new Point();
    //小红点结束坐标
    Point mCircleEndPoint = new Point();
    //小红点控制点坐标
    Point mCircleConPoint = new Point();
    //小红点的移动坐标
    Point mCircleMovePoint = new Point();
    //小红点半径
    private int mRadius = 20;
    //小红点画笔
    private Paint mCirclePaint;

    private Context context;

    public GoodsView(Context context) {
        super(context);
        this.context = context;
    }


    public GoodsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public GoodsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mCirclePaint = new Paint();
        mCirclePaint.setFilterBitmap(true);
        mCirclePaint.setDither(true);

        BitmapDrawable drawable = (BitmapDrawable) getResources().getDrawable(R.drawable.local_jiahao);
        Bitmap bitmap = drawable.getBitmap();

        canvas.drawBitmap(bitmap, mCircleMovePoint.x, mCircleMovePoint.y, mCirclePaint);


//        drawCircle(canvas);
    }

    /**
     * 商品加入购物车的小红点
     */
    private void drawCircle(Canvas canvas) {
        canvas.drawCircle(mCircleMovePoint.x, mCircleMovePoint.y, mRadius, mCirclePaint);
    }

    /**
     * 设置开始点和移动点
     *
     * @param x
     * @param y
     */
    public void setCircleStartPoint(int x, int y) {
        this.mCircleStartPoint.x = x;
        this.mCircleStartPoint.y = y;
        this.mCircleMovePoint.x = x;
        this.mCircleMovePoint.y = y;
    }

    /**
     * 设置结束点
     *
     * @param x
     * @param y
     */
    public void setCircleEndPoint(int x, int y) {
        this.mCircleEndPoint.x = x;
        this.mCircleEndPoint.y = y;
    }


    /**
     * 开始动画
     */
    public void startAnimation() {
        if (mCircleStartPoint == null || mCircleEndPoint == null) {
            return;
        }


        //设置控制点
        mCircleConPoint.x = ((mCircleStartPoint.x + mCircleEndPoint.x) / 2);
        mCircleConPoint.y = (20);

        //设置值动画
        ValueAnimator valueAnimator = ValueAnimator.ofObject(new CirclePointEvaluator(), mCircleStartPoint, mCircleEndPoint);
        valueAnimator.setDuration(400);
        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Point goodsViewPoint = (Point) animation.getAnimatedValue();
                mCircleMovePoint.x = goodsViewPoint.x;
                mCircleMovePoint.y = goodsViewPoint.y;
                invalidate();
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                ViewGroup viewGroup = (ViewGroup) getParent();
                viewGroup.removeView(GoodsView.this);
            }
        });
        valueAnimator.start();
    }


    /**
     * 自定义Evaluator
     */
    public class CirclePointEvaluator implements TypeEvaluator {

        /**
         * @param t          当前动画进度
         * @param startValue 开始值
         * @param endValue   结束值
         * @return
         */
        @Override
        public Object evaluate(float t, Object startValue, Object endValue) {

            Point startPoint = (Point) startValue;
            Point endPoint = (Point) endValue;

            int x = (int) (Math.pow((1 - t), 2) * startPoint.x + 2 * (1 - t) * t * mCircleConPoint.x + Math.pow(t, 2) * endPoint.x);
            int y = (int) (Math.pow((1 - t), 2) * startPoint.y + 2 * (1 - t) * t * mCircleConPoint.y + Math.pow(t, 2) * endPoint.y);

            return new Point(x, y);
        }

    }

}
