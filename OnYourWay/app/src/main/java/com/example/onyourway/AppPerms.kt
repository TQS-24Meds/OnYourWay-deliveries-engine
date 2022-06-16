package com.example.onyourway

import android.app.Activity


abstract class AppPerms(
    protected val activity: Activity?,
    protected val permission: String,
    protected val requestCode: Int
) {
    abstract fun askPermission()
    abstract fun askFinePermission()
    abstract fun askCoarsePermission()
    abstract val isGranted: Boolean

}