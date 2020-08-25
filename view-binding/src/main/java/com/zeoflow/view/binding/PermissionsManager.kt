package com.zeoflow.view.binding

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import java.util.*

class PermissionsManager internal constructor(var mViewBinding: ViewBinding) {
    private var mCallback: PermissionCallback? = null
    fun checkOrRequestPermissions(permission: String, callback: PermissionCallback) {
        checkOrRequestPermissions(arrayOf(permission), callback)
    }

    fun checkOrRequestPermissions(permissions: Array<String>, callback: PermissionCallback) {
        val result = checkPermissions(*permissions)
        if (result.isGranted) callback.onPermissionsResult(result) else {
            requestPermissions(permissions, callback)
        }
    }

    fun shouldShowRequestPermissionRationale(permission: String?): Boolean {
        return if (mViewBinding.view is Fragment) {
            (mViewBinding.view as Fragment).shouldShowRequestPermissionRationale(permission!!)
        } else {
            ActivityCompat.shouldShowRequestPermissionRationale(mViewBinding.activity, permission!!)
        }
    }

    fun checkPermissions(vararg permissions: String): PermissionsResult {
        val results: MutableMap<String, Boolean> = HashMap()
        for (permission in permissions) {
            val result = ContextCompat.checkSelfPermission(mViewBinding.applicationContext, permission)
            results[permission] = result == PackageManager.PERMISSION_GRANTED
        }
        return PermissionsResult(results)
    }

    fun requestPermissions(permission: String, callback: PermissionCallback?) {
        requestPermissions(arrayOf(permission), callback)
    }

    fun requestPermissions(permissions: Array<String>?, callback: PermissionCallback?) {
        mCallback = callback
        if (mViewBinding.view is Fragment) {
            (mViewBinding.view as Fragment).requestPermissions(permissions!!, RC_PERMISSIONS)
        } else {
            ActivityCompat.requestPermissions(mViewBinding.activity, permissions!!, RC_PERMISSIONS)
        }
    }

    val applicationPermissionSettingsIntent: Intent
        get() {
            val intent = Intent()
            intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            val uri = Uri.fromParts("package", mViewBinding.applicationContext.packageName, null)
            intent.data = uri
            return intent
        }

    fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == RC_PERMISSIONS) {
            if (mCallback != null) {
                mCallback!!.onPermissionsResult(PermissionsResult(permissions, grantResults))
                mCallback = null
            }
        }
    }

    interface PermissionCallback {
        fun onPermissionsResult(permissionsResult: PermissionsResult?)
    }

    class PermissionsResult {
        var mResult: MutableMap<String, Boolean>

        constructor(result: MutableMap<String, Boolean>) {
            mResult = result
        }

        constructor(permissions: Array<String>, grantResults: IntArray) {
            mResult = HashMap()
            for (i in permissions.indices) {
                mResult[permissions[i]] = grantResults[i] == PackageManager.PERMISSION_GRANTED
            }
        }

        val isGranted: Boolean
            get() {
                val permissionsArray = mResult.keys.toTypedArray()
                return isGranted(*permissionsArray)
            }

        fun isGranted(vararg permissions: String?): Boolean {
            for (permission in permissions) {
                if (!mResult[permission]!!) return false
            }
            return true
        }

        val resultsMap: Map<String, Boolean>
            get() = HashMap(mResult)
    }

    companion object {
        private const val RC_PERMISSIONS = 62538
    }

}