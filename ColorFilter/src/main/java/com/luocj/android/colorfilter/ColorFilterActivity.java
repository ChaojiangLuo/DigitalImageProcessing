package com.luocj.android.colorfilter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ColorFilterActivity extends AppCompatActivity {

    private ImageProcessView mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mView = new ImageProcessView(this);
        setContentView(mView);
    }
}
