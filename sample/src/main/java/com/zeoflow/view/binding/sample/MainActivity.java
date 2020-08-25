package com.zeoflow.view.binding.sample;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.zeoflow.view.binding.BindAppActivity;
import com.zeoflow.view.binding.sample.databinding.ActivityMainBinding;

public class MainActivity extends BindAppActivity<ActivityMainBinding, MainViewBinding>
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        setupViewBinding(R.layout.activity_main, MainViewBinding.class);
        super.onCreate(savedInstanceState);
        MainViewBinding mMainViewBinding = getViewBinding();
    }
}
