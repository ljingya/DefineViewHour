package com.example.shuiai.defineviewhour;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author shuiai@dianjia.io
 * @Company 杭州木瓜科技有限公司
 * @date 2016/10/21
 */

public class DefineView extends View {
    /**
     * View的宽
     */
    private int width;
    /**
     * View的高
     */
    private int height;
    /**
     * 最外层的圆
     */
    private Paint circlePaint;
    /**
     * 刻度线的paint
     */
    private Paint linePaint;


    public DefineView(Context context) {
        this(context, null);
    }

    public DefineView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DefineView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 初始化资源
     */
    private void init() {
        circlePaint = new Paint();
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setColor(Color.GRAY);
        circlePaint.setStrokeWidth(10);
        circlePaint.setAntiAlias(true);
        /**
         * 刻度线
         */
        linePaint = new Paint();
        linePaint.setColor(Color.BLACK);
        linePaint.setAntiAlias(true);
        linePaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(widthMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            throw new IllegalArgumentException("请把宽写确定!");
        }
        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            throw new IllegalArgumentException("请把高写确定!");
        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int min = Math.min(width, height);
        int center = min / 2;
        int stroke = (int) (circlePaint.getStrokeWidth() / 2);
        int radious = min / 2 - stroke;
        canvas.drawCircle(center, center, radious, circlePaint);
        int degree = 360 / 24;
        for (int i = 0; i < 24; i++) {
            if (i == 0 || i == 6 || i == 12 || i == 18) {
                linePaint.setStrokeWidth(10);
                canvas.drawLine(min / 2, stroke, min / 2, min / 8, linePaint);
            } else {
                linePaint.setStrokeWidth(5);
//                float y = (float) (min / 2 - Math.cos(degree) * min / 2);
//                float x = (float) (min / 2 + Math.sin(degree) * min / 2);
//                float y1 = (float) (y+ Math.cos(degree) * min / 10);
//                float x1 = (float) (min / 2 + Math.sin(degree) * min / 10);
                canvas.drawLine(min / 2, stroke, min / 2, min / 12, linePaint);
            }
            canvas.rotate(degree, min / 2, min / 2);
        }
        canvas.save();//保存画布
        //将原点移到圆的中心，然后画时针和分针
        canvas.translate(min / 2, min / 2);
        canvas.drawLine(0, 0, 0, -min / 3, linePaint);
        canvas.drawLine(0, 0, min / 5, 0, circlePaint);
        canvas.restore();//合并图层
    }

}
