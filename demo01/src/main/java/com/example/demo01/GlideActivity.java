package com.example.demo01;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * 创建日期：2018/5/6 on 下午9:50
 * 描述: 关于Glide的拓展(高斯模糊、加载监听、圆角图片)
 * http://pic4.nipic.com/20090811/2608592_115151007_2.jpg
 * 作者: liangyang
 */
public class GlideActivity extends AppCompatActivity {

    public static final String URL = "http://pic4.nipic.com/20090811/2608592_115151007_2.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);

        setTitle(getString(R.string.glide_image));

        ImageView imageView = (ImageView) findViewById(R.id.iv);
        ImageView imageViewBlur = (ImageView) findViewById(R.id.iv_blur);

        //普通模式
        Glide.with(this)
                .load(URL)
                .apply(RequestOptions.placeholderOf(R.drawable.icon))
                .apply(RequestOptions.errorOf(R.mipmap.ic_launcher))
                .into(imageView);

        //高斯模糊
        Glide.with(this)
                .load(URL)
                .apply(RequestOptions.placeholderOf(R.drawable.icon))
                .apply(RequestOptions.errorOf(R.mipmap.ic_launcher))
                .apply(RequestOptions.bitmapTransform(new BlurTransformation(10)))//设置模糊度(在0.0到25.0之间)，默认”25"
                .into(imageViewBlur);

    }
}
