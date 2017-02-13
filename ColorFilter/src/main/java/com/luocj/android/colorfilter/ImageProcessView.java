package com.luocj.android.colorfilter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by luocj on 2/10/17.
 */

public class ImageProcessView extends View implements View.OnClickListener {

    private Context mContext;

    private Paint mPaint;
    private int mClickCount;
    private Rect mBound;

    public ImageProcessView(Context context) {
        super(context);

        mContext = context;

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mClickCount = 0;
        mBound = new Rect();

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getRectSize(mBound);

        setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        mClickCount++;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        switch (mClickCount % 7) {
            case 0:
                drawOrignalRect(canvas);
                break;
            case 1:
                drawOrignalColorCircle(canvas);
                break;
            case 2:
                drawFilterColorCircle(canvas);
                break;
            case 3:
                drawOrignalImage(canvas);
                break;
            case 4:
                drawFilterImage(canvas);
                break;
            case 5:
                drawLightFilterImage(canvas);
                break;
            case 6:
                drawPorterDuffColorFilterImage(canvas);
                break;
            default:
                break;
        }
    }

    private void drawOrignalRect(Canvas canvas) {
        mPaint.setColor(Color.WHITE);

        canvas.drawRect(mBound, mPaint);
    }

    private void drawOrignalColorCircle(Canvas canvas) {
        // 生成色彩矩阵
        ColorMatrix colorMatrix = new ColorMatrix(new float[]{
                1, 0, 0, 0, 0,
                0, 1, 0, 0, 0,
                0, 0, 1, 0, 0,
                0, 0, 0, 1, 0,
        });
        mPaint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));

        // 设置画笔颜色为自定义颜色
        mPaint.setColor(Color.argb(255, 255, 128, 103));

        // 绘制圆环 (x坐标，y坐标，半径，画笔)
        canvas.drawCircle(240, 600 / 2, 200, mPaint);
    }

    private void drawFilterColorCircle(Canvas canvas) {
        // 生成色彩矩阵
        ColorMatrix colorMatrix = new ColorMatrix(new float[]{
                0.5F, 0, 0, 0, 0,
                0, 0.5F, 0, 0, 0,
                0, 0, 0.5F, 0, 0,
                0, 0, 0, 1, 0,
        });
        mPaint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));

        // 设置画笔颜色为自定义颜色
        mPaint.setColor(Color.argb(255, 255, 128, 103));

        // 绘制圆环 (x坐标，y坐标，半径，画笔)
        canvas.drawCircle(240, 600 / 2, 200, mPaint);
    }

    private void drawOrignalImage(Canvas canvas) {
        // 生成色彩矩阵
        ColorMatrix colorMatrix = new ColorMatrix(new float[]{
                1, 0, 0, 0, 0,
                0, 1, 0, 0, 0,
                0, 0, 1, 0, 0,
                0, 0, 0, 1, 0,
        });
        mPaint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));

        Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.kale);
        canvas.drawBitmap(bitmap, 240, 600, mPaint);
    }

    private void drawFilterImage(Canvas canvas) {
        // 生成色彩矩阵
        ColorMatrix colorMatrix = new ColorMatrix(new float[]{
                -1, 0, 0, 1, 1,
                0, -1, 0, 1, 1,
                0, 0, -1, 1, 1,
                0, 0, 0, 1, 0,
        });
        mPaint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));

        Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.kale);
        canvas.drawBitmap(bitmap, 240, 600, mPaint);
    }

    private void drawLightFilterImage(Canvas canvas) {
        // 设置颜色过滤
        mPaint.setColorFilter(new LightingColorFilter(0xFFFF00FF, 0x00000000));

        Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.kale);
        canvas.drawBitmap(bitmap, 240, 600, mPaint);
    }

    private void drawPorterDuffColorFilterImage(Canvas canvas) {
        // 设置颜色过滤
        mPaint.setColorFilter(new PorterDuffColorFilter(Color.RED, PorterDuff.Mode.DARKEN));

        Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.kale);
        canvas.drawBitmap(bitmap, 240, 600, mPaint);
    }
}
