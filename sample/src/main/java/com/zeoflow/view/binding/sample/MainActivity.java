package com.zeoflow.view.binding.sample;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.zeoflow.view.binding.ViewModelActivity;
import com.zeoflow.view.binding.sample.databinding.ActivityMainBinding;

public class MainActivity extends ViewModelActivity<ActivityMainBinding, MainViewModel>
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        setupViewModel(R.layout.activity_main, MainViewModel.class);
        super.onCreate(savedInstanceState);
    }
}
