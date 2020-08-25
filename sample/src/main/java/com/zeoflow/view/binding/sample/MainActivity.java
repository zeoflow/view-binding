package com.zeoflow.view.binding.sample;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.zeoflow.view.binding.ViewModelActivity;
import com.zeoflow.view.binding.sample.databinding.ActivityMainBinding;

public class MainActivity extends ViewModelActivity<ActivityMainBinding, MainViewBinding>
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        setupViewBinding(R.layout.activity_main, MainViewBinding.class);
        super.onCreate(savedInstanceState);
        MainViewBinding mMainViewBinding = getViewBinding();

        mMainViewBinding.txt.set("sdgdfhgfjg");

    }
}
