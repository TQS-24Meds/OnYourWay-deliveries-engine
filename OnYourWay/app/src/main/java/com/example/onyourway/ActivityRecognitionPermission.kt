package com.example.onyourway

import android.Manifest
import android.Manifest.permission
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import android.os.Build.VERSION_CODES
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import android.util.Log

class ActivityRecognitionPermission(activity: Activity?) :
    AppPerms(activity, permission.CAMERA, 1) {
    @RequiresApi(VERSION_CODES.M)
    override fun askPermission() {
        if (!isGranted) {
            activity?.requestPermissions(arrayOf<String>(permission), 1)
        }
    }
    @RequiresApi(VERSION_CODES.M)
    override fun askFinePermission() {
        if (isGranted) {
            activity?.requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),2)
        }
    }
    @RequiresApi(VERSION_CODES.M)
    override fun askCoarsePermission() {
        if (isGranted) {
            activity?.requestPermissions(arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),3)
        }
    }

    override val isGranted: Boolean
        get() = activity?.let {
            ContextCompat.checkSelfPermission(
                it,
                permission
            )
        } == PackageManager.PERMISSION_GRANTED
}