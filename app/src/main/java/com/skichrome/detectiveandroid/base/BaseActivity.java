package com.skichrome.detectiveandroid.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
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