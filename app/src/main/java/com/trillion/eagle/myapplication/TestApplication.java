package com.trillion.eagle.myapplication;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

public class TestApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("MainActivity", "  #accountId :" + BuildConfig.ACCOUNT_ID);
        Toast.makeText(this, "id:" + BuildConfig.ACCOUNT_ID, Toast.LENGTH_LONG).show();

    }
}
