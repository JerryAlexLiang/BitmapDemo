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
 * Glide是一个比较强大也是比较常用的一个图片加载库，Glide中的Transformations用于在图片显示前对图片进行处理。
 * glide-transformations 这个库为Glide提供了多种多样的 Transformations实现，
 * 其中就包括高斯模糊的实现BlurTransformation
 * 步骤：
 * 1、添加依赖：
 * compile 'com.github.bumptech.glide:glide:3.7.0'
 * compile 'jp.wasabeef:glide-transformations:2.0.1'
 * 2、通过这两个库的结合使用，就可以使用其中的BlurTransformation实现图片的高斯模糊
 * 其中radius的取值范围是1-25，radius越大，模糊度越高。
 * Glide.with(context).load(R.drawable.defalut_photo)
 * .bitmapTransform(new BlurTransformation(context, radius)).into(mImageView);
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
