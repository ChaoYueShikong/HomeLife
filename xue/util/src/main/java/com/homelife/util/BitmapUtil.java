/*
 * Copyright (c) 2012, Xue Corporation, All Rights Reserved
 */
package com.homelife.util;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;

import com.homelife.util.binary.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Description 处理图片类的公共应用
 * @Author Xue
 * @CreateDate
 */
public final class BitmapUtil {
    /**
     * @Description 图层叠加合并
     * @author Xue
     * @createDate 使用imageView.setImageDrawable(la);
     */
    public static LayerDrawable getLayerDrawable(int drawable1, int drawable2) {
        Drawable[] layers = new Drawable[2];
        if (drawable1 != 0) {
            layers[0] = Static.CONTEXT.getResources().getDrawable(drawable1);
        }
        if (drawable2 != 0) {
            layers[1] = Static.CONTEXT.getResources().getDrawable(drawable2);
            // layers[1] = new PaintDrawable(Color.BLACK);
        }
        LayerDrawable la = new LayerDrawable(layers);
        la.setLayerInset(0, 0, 0, 0, 0);
        la.setLayerInset(1, 0, 0, 0, 0);
        return la;
    }

    /**
     * @Description 图层叠加合并 使用imageView.setImageDrawable(la);
     * @author Xue
     * @createDate
     */
    public static LayerDrawable getLayerDrawable(Bitmap bitmap, int drawable2) {
        Drawable[] layers = new Drawable[2];
        if (bitmap != null) {
            layers[0] = new BitmapDrawable(bitmap);
        }
        if (drawable2 != 0) {
            layers[1] = Static.CONTEXT.getResources().getDrawable(drawable2);
        }
        LayerDrawable la = new LayerDrawable(layers);
        la.setLayerInset(0, 0, 0, 0, 0);
        la.setLayerInset(1, 0, 0, 0, 0);
        return la;
    }

    /**
     * @Description 字节流转Bitmap
     * @author Xue
     * @createDate
     */
    public static Bitmap bytes2Bimap(byte[] b) {
        Bitmap bitmap = null;
        InputStream imput = byte2Inputstream(b);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;
        if (imput != null) {
            bitmap = BitmapFactory.decodeStream(imput, null, options);
        }
        return bitmap;
    }

    /**
     * @Description 字节流转Bitmap
     * @author Xue
     * @createDate
     */
    public static Bitmap bytes2Bimap(String str) {
        byte[] bytes = base64Str2Byte(str);
        return bytes2Bimap(bytes);
    }

    /**
     * @Description base64解密图片流
     * @Author Xue
     * @CreateDate
     */
    public static byte[] base64Str2Byte(String str) {
        Base64 decoder = new Base64();
        byte[] bytes = decoder.decode(str); // Base64解码
        return bytes;
    }


    /**
     * @Description Bitmap-字节流
     * @author Xue
     * @createDate
     */
    public static byte[] bitmap2Bytes(Bitmap bitmap) {
        ByteArrayOutputStream baos = null;
        if (bitmap == null)
            return null;
        try {
            baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
            byte[] b = baos.toByteArray();
            return b;
        } catch (Exception e) {
            if (baos != null)
                try {
                    baos.close();
                } catch (IOException e1) {
                }
        }
        return null;
    }

    /**
     * @Description byte --> hex
     * @author Xue
     * @createDate
     */
    @SuppressLint("DefaultLocale")
    public static String byte2hex(byte[] b) {
        StringBuffer sb = new StringBuffer(b.length * 2);
        String tmp = "";
        for (int n = 0; n < b.length; n++) {
            tmp = (Integer.toHexString(b[n] & 0XFF));
            if (tmp.length() == 1) {
                sb.append("0");
            }
            sb.append(tmp);
        }
        return sb.toString().toUpperCase();
    }

    /**
     * @Description Byte-Imputstream
     * @author Xue
     * @createDate 2015年8月15日
     */
    public static InputStream byte2Inputstream(byte[] b) {
        InputStream input = null;
        if (b != null && b.length != 0) {
            input = new ByteArrayInputStream(b);
        }
        return input;
    }

    /**
     * @Description Bitmap --> file
     * @author Xue
     * @createDate
     */
    public static void bitmapToFile(Bitmap bm, String path) {
        //File file = new File(path);
        try {
            //file.createNewFile();
            File file = FileUtil.getFile(path);
            FileOutputStream out = new FileOutputStream(file);
            if (bm.compress(Bitmap.CompressFormat.JPEG, 70, out)) {
                out.flush();
                out.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description 缩放到指定大小图片 压缩图片至 100K 800*600
     * @author Xue
     * @createDate
     */
    public static Bitmap scaleFile(String filepath, int maxWidth, int maxHeight) {
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            Bitmap bitmap = BitmapFactory.decodeFile(filepath, options); // 此时返回bm为空
            options.inJustDecodeBounds = false;
            options.inSampleSize = computeSampleSize(options, -1, maxWidth * maxHeight);
            // 重新读入图片，注意这次要把options.inJustDecodeBounds 设为 false
            bitmap = BitmapFactory.decodeFile(filepath, options);
            return bitmap;
        } catch (OutOfMemoryError e) {
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * @Description 压缩
     * @author Xue
     * @createDate
     */
    public static int computeSampleSize(BitmapFactory.Options options, int minSideLength, int maxNumOfPixels) {
        int initialSize = computeInitialSampleSize(options, minSideLength, maxNumOfPixels);
        int roundedSize;
        if (initialSize <= 8) {
            roundedSize = 1;
            while (roundedSize < initialSize) {
                roundedSize <<= 1;
            }
        } else {
            roundedSize = (initialSize + 7) / 8 * 8;
        }
        return roundedSize;
    }

    /**
     * @Description 压缩
     * @author Xue
     * @createDate
     */
    private static int computeInitialSampleSize(BitmapFactory.Options options, int minSideLength, int maxNumOfPixels) {
        double w = options.outWidth;
        double h = options.outHeight;

        int lowerBound = (maxNumOfPixels == -1) ? 1 : (int) Math.ceil(Math.sqrt(w * h / maxNumOfPixels));
        int upperBound = (minSideLength == -1) ? 128 : (int) Math.min(Math.floor(w / minSideLength), Math.floor(h / minSideLength));

        if (upperBound < lowerBound) {
            // return the larger one when there is no overlapping zone.
            return lowerBound;
        }

        if ((maxNumOfPixels == -1) && (minSideLength == -1)) {
            return 1;
        } else if (minSideLength == -1) {
            return lowerBound;
        } else {
            return upperBound;
        }
    }

    /**
     * @Description 缩放图片
     * @author Xue
     * @createDate
     */
    public static Bitmap zoomImg(Bitmap bmp, float scaleWidth, float scaleHeight) {
        int width = bmp.getWidth();// 获取这个图片的宽和高
        int height = bmp.getHeight();
        Matrix matrix = new Matrix();// 创建操作图片用的matrix对象
        matrix.postScale(scaleWidth, scaleHeight);// 缩放图片动作
        Bitmap resizedBitmap = Bitmap.createBitmap(bmp, 0, 0, width, height, matrix, true);// 创建新的图片
        return resizedBitmap;
    }

    /**
     * @Description 从新设置图片大小
     * @author Xue
     * @createDate
     */
    public static Bitmap resizeBitmap(String filepath, int maxWidth, int maxHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(filepath, options);
        options.inJustDecodeBounds = false;
        int originWidth = options.outWidth;
        int originHeight = options.outHeight;
        // no need to resize
        if (originWidth < maxWidth && originHeight < maxHeight) {
            return bitmap;
        }
        float wb = originWidth / maxWidth;
        float hb = originHeight / maxHeight;
        // 若图片过宽, 则保持长宽比缩放图片
        if (originWidth > maxWidth || originHeight > maxHeight) {
            if (wb >= hb) {
                int i = (int) Math.floor(originWidth * 1.0 / maxWidth);
                options.inSampleSize = i;

            } else {
                int i = (int) Math.floor(originHeight * 1.0 / maxHeight);
                options.inSampleSize = i;
            }
        }
        bitmap = BitmapFactory.decodeFile(filepath, options);
        return bitmap;
    }

    /**
     * @Description 从新设置图片大小
     * @author Xue
     * @createDate
     */
    public static Bitmap resizeBitmap(Bitmap bitmap, int maxWidth, int maxHeight) {
        int originWidth = bitmap.getWidth();
        int originHeight = bitmap.getHeight();
        // no need to resize
        if (originWidth < maxWidth && originHeight < maxHeight) {
            return bitmap;
        }
        int width = originWidth;
        int height = originHeight;
        float wb = originWidth / maxWidth;
        float hb = originHeight / maxHeight;
        // 若图片过宽, 则保持长宽比缩放图片
        if (originWidth > maxWidth || originHeight > maxHeight) {
            if (wb > hb) {
                width = maxWidth;
                double i = originWidth * 1.0 / maxWidth;
                height = (int) Math.floor(originHeight / i);
                bitmap = Bitmap.createScaledBitmap(bitmap, width, height, false);
            } else {
                height = maxHeight;
                double i = originHeight * 1.0 / maxHeight;
                width = (int) Math.floor(originWidth / i);
                bitmap = Bitmap.createScaledBitmap(bitmap, width, height, false);
            }
        }
        return bitmap;
    }

    /**
     * @Description
     * @author Xue
     * @createDate
     */
    public static Bitmap addBitmap(Bitmap src, Bitmap watermark, float left, float top) {
        int w = src.getWidth();
        int h = src.getHeight();
        Bitmap dst = Bitmap.createBitmap(w, h, Config.ARGB_8888);//ARGB_8888
        Canvas canvas = new Canvas(dst);
        canvas.drawBitmap(setAlpha(src, 50), 0, 0, null);
        canvas.drawBitmap(scale(watermark, src.getWidth(), (int) (src.getHeight() / 3.0f)), left, top, null);
//        canvas.save(Canvas.ALL_SAVE_FLAG);
        canvas.save();
        canvas.restore();
        return dst;
    }

    /**
     * @Description
     * @author Xue
     * @createDate
     */
    public static Bitmap addProgress(Bitmap src, Bitmap pro, int progress) {
        int w = pro.getWidth();
        int h = pro.getHeight();
        Bitmap dst = Bitmap.createBitmap(w, h, Config.ARGB_8888);//ARGB_8888
        Canvas canvas = new Canvas(dst);
        canvas.drawBitmap(src, 0, 0, null);
        canvas.drawBitmap(pro, 0, pro.getHeight() * (100 - progress) / 100.0f, null);
//        canvas.save(Canvas.ALL_SAVE_FLAG);
        canvas.save();
        canvas.restore();
        return dst;
    }

    /**
     * @Description
     * @author Xue
     * @createDate
     */
    public static Bitmap setAlpha(Bitmap sourceImg, int number) {
        int[] argb = new int[sourceImg.getWidth() * sourceImg.getHeight()];
        sourceImg.getPixels(argb, 0, sourceImg.getWidth(), 0, 0, sourceImg.getWidth(), sourceImg.getHeight());
        number = number * 255 / 100;
        for (int i = 0; i < argb.length; i++) {
            argb[i] = (number << 24) | (argb[i] & 0x00FFFFFF);
        }
        sourceImg = Bitmap.createBitmap(argb, sourceImg.getWidth(), sourceImg.getHeight(), Config.ARGB_8888);//ARGB_8888

        return sourceImg;
    }

    /**
     * @Description
     * @author Xue
     * @createDate
     */
    public static Bitmap addWatermark(Bitmap src, float left, float top, String content, int alpha) {
        int w = src.getWidth();
        int h = src.getHeight();
        Bitmap dst = Bitmap.createBitmap(w, h, Config.ARGB_8888);//ARGB_8888
        Canvas canvas = new Canvas(dst);
        Paint p = new Paint();
        String fontName = "sans";
        Typeface font = Typeface.create(fontName, Typeface.BOLD);
        p.setColor(Color.RED);
        p.setTypeface(font);
        p.setTextSize(22);
        p.setAlpha(alpha * 255 / 100);
        canvas.drawText(content, left, top, p);
//        canvas.save(Canvas.ALL_SAVE_FLAG);
        canvas.save();
        canvas.restore();
        return dst;
    }

    /**
     * @Description
     * @author Xue
     * @createDate
     */
    public static Bitmap addWatermark(Bitmap src, float left, float top, Bitmap img, int alpha) {
        int w = src.getWidth();
        int h = src.getHeight();
        Bitmap dst = Bitmap.createBitmap(w, h, Config.ARGB_8888);//Canvas中不能使用RGB_565
        Canvas canvas = new Canvas(dst);
        Paint p = new Paint();
        canvas.drawBitmap(src, 0, 0, p);
        p.setAlpha(alpha * 255 / 100);
        canvas.drawBitmap(img, left, top, p);
//        canvas.save(Canvas.ALL_SAVE_FLAG);
        canvas.save();
        canvas.restore();
        return dst;
    }

    /**
     * @Description
     * @author Xue
     * @createDate
     */
    public static Bitmap scale(Bitmap bitmap, float scaleWidth, float scaleHeight) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
    }

    /**
     * @Description
     * @author Xue
     * @createDate
     */
    public static Bitmap scale(Bitmap bitmap, int newWidth, int newHeight) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
    }

    /**
     * @Description 把图片转为圆角
     * @author Xue
     * @createDate
     */
    public static Bitmap roundedCornerBitmap(Bitmap bitmap, float roundPx) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);//RGB_565 Xue 无法出圆角效果
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }

    /**
     * @param bitmap 正方形图
     * @return
     * @Description 正方形转圆形图标
     * @author Xue
     * @createDate
     */
    public static Bitmap toRoundBitmap(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
//		int width = bitmap.getWidth();// 圆形图片宽高
//		int height = bitmap.getHeight();// 正方形的边长
//		int r = 0;
//		if (width > height) {// 取最短边做边长
//			r = height;
//		} else {
//			r = width;
//		}
//		Bitmap backgroundBmp = Bitmap.createBitmap(width, height, Config.ARGB_8888);// 构建一个bitmap
//		Canvas canvas = new Canvas(backgroundBmp);// new一个Canvas，在backgroundBmp上画图
//		Paint paint = new Paint();
//		paint.setAntiAlias(true);// 设置边缘光滑，去掉锯齿
//		RectF rect = new RectF(0, 0, r, r);// 宽高相等，即正方形
//		canvas.drawRoundRect(rect, r / 2, r / 2, paint);// 通过制定的rect画一个圆角矩形，当圆角X轴方向的半径等于Y轴方向的半径时，// 且都等于r/2时，画出来的圆角矩形就是圆形
//		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));// 设置当两个图形相交时的模式，SRC_IN为取SRC图形相交的部分，多余的将被去掉
//		canvas.drawBitmap(bitmap, null, rect, paint);// canvas将bitmap画在backgroundBmp上
//		return backgroundBmp;// 返回已经绘画好的backgroundBmp

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int left = 0, top = 0, right = width, bottom = height;
        float roundPx = height / 2;
        if (width > height) {
            left = (width - height) / 2;
            top = 0;
            right = left + height;
            bottom = height;
        } else if (height > width) {
            left = 0;
            top = (height - width) / 2;
            right = width;
            bottom = top + width;
            roundPx = width / 2;
        }

        Bitmap output = Bitmap.createBitmap(width, height, Config.ARGB_8888);//Xue 使用RGB_565无法画出圆形
        Canvas canvas = new Canvas(output);//产生一个同样大小的画布
        int color = 0xff424242;
        Paint paint = new Paint();
        Rect rect = new Rect(left, top, right, bottom);
        RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }

    /**
     * @Description
     * @author Xue
     * @createDate
     */
    public static Bitmap createReflectionImageWithOrigin(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        final int reflectionGap = 4;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        Matrix matrix = new Matrix();
        matrix.preScale(1, -1);

        Bitmap reflectionImage = Bitmap.createBitmap(bitmap, 0, height / 2, width, height / 2, matrix, false);

        Bitmap bitmapWithReflection = Bitmap.createBitmap(width, (height + height / 2), Config.ARGB_8888);//ARGB_8888

        Canvas canvas = new Canvas(bitmapWithReflection);
        canvas.drawBitmap(bitmap, 0, 0, null);
        Paint deafalutPaint = new Paint();
        canvas.drawRect(0, height, width, height + reflectionGap, deafalutPaint);
        canvas.drawBitmap(reflectionImage, 0, height + reflectionGap, null);
        Paint paint = new Paint();
        LinearGradient shader = new LinearGradient(0, bitmap.getHeight(), 0, bitmapWithReflection.getHeight() + reflectionGap, 0x70ffffff, 0x00ffffff,
                TileMode.CLAMP);
        paint.setShader(shader);
        // Set the Transfer mode to be porter duff and destination in
        paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
        // Draw a rectangle using the paint with our linear gradient
        canvas.drawRect(0, height, width, bitmapWithReflection.getHeight() + reflectionGap, paint);
        return bitmapWithReflection;
    }

    /**
     * @Description
     * @author Xue
     * @createDate
     */
    public static Bitmap createReflectedImage(Bitmap originalImage) {
        final int reflectionGap = 4; // 倒影和原图片间的距离
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();

        Matrix matrix = new Matrix();
        matrix.preScale(1, -1);

        // 倒影部分
        Bitmap reflectionImage = Bitmap.createBitmap(originalImage, 0, height / 2, width, height / 2, matrix, false);
        // 要返回的倒影图片
        Bitmap bitmapWithReflection = Bitmap.createBitmap(width, (height + height / 2), Config.ARGB_8888);//ARGB_8888

        Canvas canvas = new Canvas(bitmapWithReflection);
        // 画原来的图片
        canvas.drawBitmap(originalImage, 0, 0, null);

        Paint defaultPaint = new Paint();
        // 倒影和原图片间的距离
        canvas.drawRect(0, height, width, height + reflectionGap, defaultPaint);
        // 画倒影部分
        canvas.drawBitmap(reflectionImage, 0, height + reflectionGap, null);

        Paint paint = new Paint();
        LinearGradient shader = new LinearGradient(0, originalImage.getHeight(), 0, bitmapWithReflection.getHeight() + reflectionGap, 0x70ffffff, 0x00ffffff,
                TileMode.MIRROR);
        paint.setShader(shader);
        paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
        canvas.drawRect(0, height, width, bitmapWithReflection.getHeight() + reflectionGap, paint);
        return bitmapWithReflection;
    }

    /**
     * @Description bitmap 内存回收
     * @author Xue
     * @createDate
     */
    public static void recycle(Bitmap bmp) {
        if (bmp != null && !bmp.isRecycled()) {
            bmp.recycle();
            System.gc();
        }
    }

    /**
     * @Description 根据宽度等比缩放
     * @author Xue
     * @createDate
     * @paramisRecycle是否内存清除
     */
    public static Bitmap getScaleBitmap(Bitmap bitmap, int dstWidth, boolean isRecycle) {
        if (null == bitmap) {
            return null;
        }
        float temp = (float) bitmap.getHeight() / (float) bitmap.getWidth();
        int dstHeight = (int) (temp * dstWidth);

        Bitmap scaleBitmap = Bitmap.createScaledBitmap(bitmap, dstWidth, dstHeight, true);
        if (isRecycle) {
            recycle(bitmap);
        }
        return scaleBitmap;
    }

    /**
     * @Description 图片解码和缩放减少内存消耗 缩小1/4下载
     * @author Xue
     * @createDate
     */
    public static Bitmap decodeFile2(File f) {
        try {
            // decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(new FileInputStream(f), null, o);

            // Find the correct scale value. It should be the power of 2.
            final int REQUIRED_SIZE = 100;
            int width_tmp = o.outWidth, height_tmp = o.outHeight;
            int scale = 1;
            while (true) {
                if (width_tmp / 2 < REQUIRED_SIZE || height_tmp / 2 < REQUIRED_SIZE) {
                    break;
                }
                width_tmp /= 2;
                height_tmp /= 2;
                scale *= 2;
            }
            // decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            return BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
        } catch (FileNotFoundException e) {
            e.getStackTrace();
        }
        return null;
    }

    /**
     * @Description 根据已有的Drawable创建一个新的Bitmap
     * @author Xue
     * @createDate
     */
    public static Bitmap drawable2Bitamp(Drawable drawable) {
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();
        Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Config.ARGB_8888 : Config.RGB_565;
        Bitmap bitmap = Bitmap.createBitmap(w, h, config);
        // 注意，下面三行代码要用到，否在在View或者surfaceview里的canvas.drawBitmap会看不到图
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        drawable.draw(canvas);
        return bitmap;
    }

    /**
     * @param drawable
     * @Description 直接从现有的Drawable中取出Bitmap
     * @author Xue
     * @createDate
     */
    public static Bitmap drawableToBitamp(Drawable drawable) {
        BitmapDrawable bd = (BitmapDrawable) drawable;
        return bd.getBitmap();
    }

    /**
     * @Description Bitmap → Drawable
     * @Author Xue
     * @CreateDate
     */
    @SuppressWarnings("deprecation")
    public static Drawable convertBitmap2Drawable(Bitmap bitmap) {
        BitmapDrawable bd = new BitmapDrawable(bitmap);
        // 因为Btimap Drawable是Drawable的子类，最终直接使用bd对象即可。
        return bd;
    }

    /**
     * @Description Bitmap --> file
     * @author Xue
     * @createDate
     */
    public static File bitmap2File(Bitmap bm, String path) {
        File file = FileUtil.getFile(path);
        try {
            //file.createNewFile();
            FileOutputStream out = new FileOutputStream(file);
            if (bm.compress(Bitmap.CompressFormat.JPEG, 70, out)) {
                out.flush();
                out.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    /**
     * @Description 资源图片获取到对应的缩放Bitmap 默认100X100
     * @Author Xue
     * @CreateDate
     */
    public static Bitmap decodeBitmapFromResource(Resources res, int resId) {
        int sWidth = SharedUtil.getScreenWidth();
        return decodeBitmapFromResource(res, resId, sWidth, sWidth);
    }

    /**
     * @Description 资源图片获取到对应的缩放Bitmap
     * @Author Xue
     * @CreateDate
     */
    public static Bitmap decodeBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight) {
        // 首先设置 inJustDecodeBounds=true 来获取图片尺寸
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);
        options.inSampleSize = calInSampleSize(options, reqWidth, reqHeight);// 计算 inSampleSize 的值
        // 根据计算出的 inSampleSize 来解码图片生成Bitmap
        options.inJustDecodeBounds = false;
        options.inPreferredConfig = Config.RGB_565;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    /**
     * @Description 计算缩放率
     * @Author Xue
     * @CreateDate
     */
    public static int calInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // 原始图片的宽高
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;
            // 在保证解析出的bitmap宽高分别大于目标尺寸宽高的前提下，取可能的inSampleSize的最大值
            while ((halfHeight / inSampleSize) > reqHeight && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }


    /**
     * @param file
     * @return
     * @Description 文件转Bitmap
     * @author Xue
     */
    public static Bitmap decodeFile(File file) {// decodeFileRatio ,int width,int height
        try {
            int reqWidth = SharedUtil.getScreenWidth();
            int reqHeight = SharedUtil.getScreenHeight();
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(new FileInputStream(file), null, options);
            options.inSampleSize = calInSampleSize(options, reqWidth, reqHeight);// 计算 inSampleSize 的值
            // 根据计算出的 inSampleSize 来解码图片生成Bitmap
            options.inJustDecodeBounds = false;// 还原图片,分配内存
            options.inPreferredConfig = Config.RGB_565;
            // 忘了加上这句(重要)
            return BitmapFactory.decodeStream(new FileInputStream(file), null, options);
        } catch (FileNotFoundException e) {
            e.getStackTrace();
        } catch (OutOfMemoryError e) {
            e.getMessage();
        }
        return null;
    }

    /**
     * @Description 给二维码加上白色的底图, 处理二维码图片加文字
     * @Author Xue
     * @CreateDate
     */
    public static Bitmap setWhiteZingxBackground(Bitmap bitmapBefore, String str, int width) {
        Bitmap bitmap = Bitmap.createBitmap(width, width, Config.RGB_565);//width
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(Color.WHITE);
//        canvas.save(Canvas.ALL_SAVE_FLAG);
        canvas.save();
        Paint paint = new Paint();
        int x = width / 2 - bitmapBefore.getWidth() / 2;
        canvas.drawBitmap(bitmapBefore, x, x, paint);
        paint.setAntiAlias(true); //消除锯齿
        paint.setTextSize(35);
        paint.setColor(Color.parseColor("#000000"));
        int y = width / 2 + bitmapBefore.getWidth() / 2;
        canvas.drawText(str, x + 20, y + 35, paint);
        canvas.restore();
        return bitmap;
    }


    /**
     * 给图片加上白色的底图
     *
     * @param bitmapBefore
     * @return
     */
    public static Bitmap setWhiteBackground(Bitmap bitmapBefore, int width) {
        if (bitmapBefore == null) {
            return null;
        }
        int x = 0, y = 0;
        Bitmap bitmap = Bitmap.createBitmap(width, bitmapBefore.getHeight(), Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(Color.WHITE);
//        canvas.save(Canvas.ALL_SAVE_FLAG);
        canvas.save();
        Paint paint = new Paint();
        canvas.drawBitmap(bitmapBefore, x, y, paint);
        canvas.restore();
        return bitmap;
    }

    /**
     * @Description bitmap上写水印文字
     * @Author Xue
     * @CreateDate
     */
    public static Bitmap drawTextToBitmap(Bitmap bitmap, String gText, int width) {
        if (bitmap == null) {
            return null;
        }
        Config bitmapConfig = bitmap.getConfig();
        // set default bitmap config if none
        if (bitmapConfig == null) {
            bitmapConfig = Config.RGB_565;
        }
        // resource bitmaps are imutable,
        // so we need to convert it to mutable one
        bitmap = bitmap.copy(bitmapConfig, true);
        Canvas canvas = new Canvas(bitmap);
        // new antialised Paint
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        // text color - #3D3D3D
        paint.setColor(Color.parseColor("#FFFFFF"));
        // text size in pixels
        paint.setTextSize(65);
        // text shadow
        paint.setShadowLayer(1f, 0f, 1f, Color.WHITE);
        paint.setTypeface(Typeface.DEFAULT_BOLD);// 采用默认的宽度
        // draw text to the Canvas center
        Rect bounds = new Rect();
        paint.getTextBounds(gText, 0, gText.length(), bounds);
        int x = (bitmap.getWidth() - bounds.width()) / 2;
        int y = (bitmap.getHeight() + bounds.height()) - (bitmap.getHeight() + bounds.height()) / 3;
        drawText(canvas, myTextPaint(), gText, x, y, width);
        return bitmap;
    }

    //写入文字，自动换行的方法
    public static void drawText(Canvas canvas, TextPaint Paint, String textString, int x, int y, int width) {
        StaticLayout sl = new StaticLayout(textString, Paint, width - 8, Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
        canvas.translate(6, y);
        sl.draw(canvas);
    }

    public static TextPaint myTextPaint() {
        TextPaint textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG | Paint.DEV_KERN_TEXT_FLAG);// 设置画笔
        textPaint.setTextSize(65);// 字体大小
        textPaint.setTypeface(Typeface.DEFAULT_BOLD);// 采用默认的宽度
        textPaint.setColor(Color.parseColor("#FFFFFF"));// 采用的颜色
        return textPaint;
    }

    /**
     * 选择变换
     *
     * @param origin 原图
     * @param alpha  旋转角度，可正可负
     * @return 旋转后的图片
     */
    public static Bitmap rotateBitmap(Bitmap origin, float alpha) {
        if (origin == null) {
            return null;
        }
        int width = origin.getWidth();
        int height = origin.getHeight();
        Matrix matrix = new Matrix();
        matrix.setRotate(alpha);
        // 围绕原地进行旋转
        Bitmap newBM = Bitmap.createBitmap(origin, 0, 0, width, height, matrix, false);
        if (newBM.equals(origin)) {
            return newBM;
        }
        origin.recycle();
        return newBM;
    }
}
