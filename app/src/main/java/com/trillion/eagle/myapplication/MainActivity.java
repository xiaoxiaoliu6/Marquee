package com.trillion.eagle.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
   StrongMarqueeView mMarqueeView1,mMarqueeView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMarqueeView1 = (StrongMarqueeView) findViewById(R.id.marqueeView1);
        mMarqueeView2 = (StrongMarqueeView) findViewById(R.id.marqueeView2);

        mMarqueeView1.setText("内容不足1行");
        mMarqueeView1.startScroll(1000);
//        mMarqueeView1.stopScroll();

        mMarqueeView2.setText("内容超过1行:后面的https链接地址换成你自己的仓库url地址，也就是上面红框中标出来的地址");
        mMarqueeView2.startScroll(1000);
//        mMarqueeView2.stopScroll();
    }
}
