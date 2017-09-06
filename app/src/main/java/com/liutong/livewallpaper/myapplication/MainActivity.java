package com.liutong.livewallpaper.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.liutong.livewallpaper.myapplication.AniSurfaceView.TestSurfaceView;
import com.orhanobut.logger.Logger;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TestSurfaceView ts = new TestSurfaceView(this);
        setContentView(ts);
    }
}
