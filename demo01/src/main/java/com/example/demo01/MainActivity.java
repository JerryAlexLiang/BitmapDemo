package com.example.demo01;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * 创建日期：2018/5/6 on 下午6:54
 * 描述: Android图片的处理
 * 作者: liangyang
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonOne;
    private Button buttonTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonOne = (Button) findViewById(R.id.btn_one);
        buttonTwo = (Button) findViewById(R.id.btn_two);

        buttonOne.setOnClickListener(this);
        buttonTwo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.btn_one:
                //Glide的拓展(高斯模糊、加载监听、圆角图片)
                intent.setClass(this, GlideActivity.class);
                break;

            case R.id.btn_two:
                //封装工具类为网络图片设置高斯模糊效果
                intent.setClass(this, BitmapActivity.class);
                break;

            default:
                break;
        }
        startActivity(intent);
    }
}
