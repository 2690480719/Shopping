package com.lqb.android.shopping.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.lqb.android.shopping.R;

public class WelcomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        //两秒延迟进入主页面
        new Handler().postDelayed(new Runnable() {
            @Override
            //启动主面
            public void run() {
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                finish();   //关闭当前页面
            }
        }, 2000);
    }
}
