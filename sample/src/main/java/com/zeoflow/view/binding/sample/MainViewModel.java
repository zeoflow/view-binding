package com.zeoflow.view.binding.sample;

import android.Manifest;
import android.widget.Toast;

import androidx.databinding.ObservableField;

import com.zeoflow.view.binding.ViewModel;

public class MainViewModel extends ViewModel
{
    public ObservableField<String> name = new ObservableField<>();
    public ObservableField<String> txt = new ObservableField<>();

    @Override
    public void onViewModelCreated()
    {
        super.onViewModelCreated();
        // Do API calls etc.
        getPermissionsManager().checkOrRequestPermissions(Manifest.permission.ACCESS_FINE_LOCATION, permissionsResult ->
        {
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
        Toast.makeText(getApplicationContext(), String.valueOf(txt.get()), Toast.LENGTH_SHORT).show();
    }
}
