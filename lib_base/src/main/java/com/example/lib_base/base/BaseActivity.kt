package com.example.lib_base.base

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hjq.permissions.OnPermissionCallback
import com.hjq.permissions.XXPermissions

abstract class BaseActivity: AppCompatActivity() {
    val CODE_WINDOW_PERMISSION: Int = 1000

    // 获取布局ID
    abstract fun getLayoutId(): Int

    // 获取标题
    abstract fun getTitleText(): String

    // 是否显示返回键
    abstract fun isShowBack(): Boolean

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }

        return true
    }

    // 检查窗口权限
    protected fun checkWindowPermission(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return Settings.canDrawOverlays(this)
        }

        return true
    }

    // 申请权限
    protected fun requestWindowPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            startActivityForResult(
                Intent(
                    Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                Uri.parse("package:$packageName")), CODE_WINDOW_PERMISSION)
        }
    }

    // 检查权限
    protected fun checkPermission(permission: String): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return checkSelfPermission(permission) == PackageManager.PERMISSION_DENIED
        }
        return true
    }

    // 请求权限
    protected fun requestPermission(permission: Array<String>) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            XXPermissions.with(this)
                .permission(permission)
                .request(object: OnPermissionCallback {
                    override fun onGranted(permissions: MutableList<String>?, all: Boolean) {
                        if (all) {
                            Toast.makeText(this@BaseActivity, "权限获取成功", Toast.LENGTH_SHORT).show()
                        }
                    }
                })
        }
    }
}