package com.example.demo01.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * 创建日期：2018/5/6 on 下午10:45
 * 描述:封装工具类为网络图片设置高斯模糊效果
 * 作者:yangliang
 */
public class BitmapUtils {
    /**
     * 对Bitmap进行高斯模糊
     * RenderScript是Google在Android 3.0(API 11)中引入的一个高性能图片处理框架。
     * 使用RenderScriprt实现高斯模糊:
     * 首先在在build.gradle的defaultConfig中添加RenderScript的使用配置
     * renderscriptTargetApi 24
     * renderscriptSupportModeEnabled true
     * renderscriptTargetApi :
     * 指定要生成的字节码版本。我们(Goole官方)建议您将此值设置为最低API级别能够提供所有的功能，你使用和设置renderscriptSupportModeEnabled为true。此设置的有效值是从11到
     * 最近发布的API级别的任何整数值。
     * renderscriptSupportModeEnabled:
     * 指定生成的字节码应该回落到一个兼容的版本，如果运行的设备不支持目标版本。
     *
     * @param bitmap  图片资源
     * @param context 上下文
     * @return
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static Bitmap blurBitmap(Bitmap bitmap, Context context) {

        //Let's create an empty bitmap with the same size of the bitmap we want to blur
        //用需要创建高斯模糊bitmap创建一个空的bitmap
        Bitmap outBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);

        //Instantiate a new Renderscript
        // 初始化Renderscript，该类提供了RenderScript context，创建其他RS类之前必须先创建这个类，
        // 其控制RenderScript的初始化，资源管理及释放
        RenderScript rs = RenderScript.create(context);

        //Create an Intrinsic Blur Script using the Renderscript
        // 创建高斯模糊对象
        ScriptIntrinsicBlur blurScript = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));

        //Create the Allocations (in/out) with the Renderscript and the in/out bitmaps
        // 创建Allocations，此类是将数据传递给RenderScript内核的主要方 法，并制定一个后备类型存储给定类型
        Allocation allIn = Allocation.createFromBitmap(rs, bitmap);
        Allocation allOut = Allocation.createFromBitmap(rs, outBitmap);

        //Set the radius of the blur: 0 < radius <= 25,设置模糊度(在0.0到25.0之间)，默认”25"
        //设定模糊度(注：Radius最大只能设置25.f)
        blurScript.setRadius(12.0f);//这个是设置模糊度，取值在0 < radius <= 25，值越大越模糊

        //Perform the Renderscript
        //执行渲染脚本
        blurScript.setInput(allIn);
        blurScript.forEach(allOut);

        //Copy the final bitmap created by the out Allocation to the outBitmap
        //复制由out分配到outBitmap的最终位图。
        allOut.copyTo(outBitmap);

        //recycle the original bitmap
        //回收原始位图
        bitmap.recycle();

        //After finishing everything, we destroy the Renderscript.
        //fter完成了所有任务，我们销毁了Renderscript。
        rs.destroy();

        return outBitmap;
    }

    /**
     * 网络图片，用url生成Bitmap的方法
     * 根据图片url获取图片对象
     * 这个方法不能在UI线程中执行,用Thread+Handler实现
     *
     * @param urlpath
     * @return
     */
    public static Bitmap getBitMBitmap(String urlpath) {
        Bitmap map = null;
        try {
            URL url = new URL(urlpath);
            URLConnection conn = url.openConnection();
            conn.connect();
            InputStream in;
            in = conn.getInputStream();
            map = BitmapFactory.decodeStream(in);
            // TODO Auto-generated catch block
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

}
