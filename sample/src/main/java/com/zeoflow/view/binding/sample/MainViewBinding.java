package com.zeoflow.view.binding.sample;

import android.Manifest;
import android.graphics.Color;
import android.widget.Toast;

import androidx.databinding.ObservableField;

import com.zeoflow.view.binding.ViewBinding;

public class MainViewBinding extends ViewBinding
{
    public ObservableField<String> name = new ObservableField<>("");
    public ObservableField<String> text = new ObservableField<>("");
    public ObservableField<Integer> colorRes = new ObservableField<>(0);

    @Override
    public void onViewModelCreated()
    {
        super.onViewModelCreated();
        colorRes.set(Color.parseColor("#ffffff"));
        getPermissionsManager().checkOrRequestPermissions(Manifest.permission.ACCESS_FINE_LOCATION, permissionsResult ->
        {
            assert permissionsResult != null;
            if (permissionsResult.isGranted())
            {
                //granted
                //action here
            }
        });
    }

    @Override
    public void onViewDetached(boolean finalDetachment)
    {
        super.onViewDetached(finalDetachment);
    }

    @Override
    public void onViewModelDestroyed()
    {
        super.onViewModelDestroyed();
        // Cancel API calls
    }

    public void showDialog()
    {
        colorRes.set(Color.parseColor("#E8E8E8"));
        Toast.makeText(getApplicationContext(), String.valueOf(text.get()), Toast.LENGTH_SHORT).show();
    }

}
