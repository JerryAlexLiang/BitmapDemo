package com.example.demo01;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.demo01.utils.BitmapUtils;

/**
 * 创建日期：2018/5/6 on 下午10:51
 * 描述: 封装工具类为网络图片设置高斯模糊效果
 * 作者: liangyang
 */
public class BitmapActivity extends AppCompatActivity {

    public static final String URL = "http://pic4.nipic.com/20090811/2608592_115151007_2.jpg";

    private ImageView imageView;
    private ImageView imageViewBlur;

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                //将图片进行模糊转换
                Bitmap blurBitmap = BitmapUtils.blurBitmap((Bitmap) msg.obj, getApplicationContext());
                imageViewBlur.setImageBitmap(blurBitmap);
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap);

        setTitle(getString(R.string.bitmap_image));

        imageView = (ImageView) findViewById(R.id.iv_bitmap);
        imageViewBlur = (ImageView) findViewById(R.id.iv_bitmap_blur);

        //普通模式
        Glide.with(this)
                .load(URL)
                .apply(RequestOptions.placeholderOf(R.drawable.icon))
                .apply(RequestOptions.errorOf(R.mipmap.ic_launcher))
                .into(imageView);

        //开启线程下载图片,用url生成Bitmap的方法
        new Thread(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = BitmapUtils.getBitMBitmap(URL);
                Message msg = Message.obtain();
                msg.what = 0;
                msg.obj = bitmap;
                handler.sendMessage(msg);
            }
        }).start();

    }

}
