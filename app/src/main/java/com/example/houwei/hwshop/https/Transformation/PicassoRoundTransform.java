package com.example.houwei.hwshop.https.Transformation;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;

import com.example.houwei.hwshop.utils.DisplayUtil;
import com.squareup.picasso.Transformation;

/**
 * Created by houwei on 2017/9/14.
 */

public class PicassoRoundTransform implements Transformation {

    private Context mContext;

    public PicassoRoundTransform(Context context) {
        mContext = context;
    }

    @Override
    public Bitmap transform(Bitmap source) {
        int widthLight = source.getWidth();
        int heightLight = source.getHeight();
        int radius = DisplayUtil.getInstance(mContext).dip2px(mContext,8); // 圆角半径

        Bitmap output = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(output);
        Paint paintColor = new Paint();
        paintColor.setFlags(Paint.ANTI_ALIAS_FLAG);

        RectF rectF = new RectF(new Rect(0, 0, widthLight, heightLight));

        canvas.drawRoundRect(rectF, radius, radius, paintColor);
//        canvas.drawRoundRect(rectF, widthLight / 5, heightLight / 5, paintColor);

        Paint paintImage = new Paint();
        paintImage.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP));
        canvas.drawBitmap(source, 0, 0, paintImage);
        source.recycle();
        return output;
    }

    @Override
    public String key() {
        return "roundcorner";
    }
}
