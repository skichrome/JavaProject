package com.skichrome.detectiveandroid.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity
{
    protected abstract int getActivityLayout();
    protected abstract void configureActivity();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); // force landscape view
        setContentView(this.getActivityLayout());

        ButterKnife.bind(this);
        this.configureActivity();
    }
}