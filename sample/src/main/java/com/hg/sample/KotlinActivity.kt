package com.hg.sample

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.appcompat.app.AppCompatActivity
import com.hg.activityresultlauncher.OnActivityResultListener
import com.hg.activityresultlauncher.OnRequestPermissionsResultListener
import com.hg.activityresultlauncher.ProxyRequestPermission
import com.hg.activityresultlauncher.ProxyStartActivityForResult

/**
 * Kotlin用法
 * <p>
 * Created by Hollow Goods on 2021-08-17.
 */
class KotlinActivity : AppCompatActivity() {

    private val startActivityForResult1 = ProxyStartActivityForResult(this)
    private val startActivityForResult2 = ProxyStartActivityForResult()
    private val requestPermission1 = ProxyRequestPermission(this)
    private val requestPermission2 = ProxyRequestPermission()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_code)

        title = "Kotlin用法"

        startActivityForResult2.register(this)
        requestPermission2.register(this)

        findViewById<View>(R.id.btn_safr1).setOnClickListener {
            startActivityForResult1.launch(Intent(this@KotlinActivity, ResultActivity::class.java), object : OnActivityResultListener {
                override fun onActivityResult(result: ActivityResult?) {
                    val resultCode = result?.resultCode
                    val name = result?.data?.getStringExtra("name")
                    val age = result?.data?.getIntExtra("age", 0)
                    Log.e("Kotlin", "ResultCode:$resultCode,name:$name,age:$age")
                }
            })
        }
        findViewById<View>(R.id.btn_safr2).setOnClickListener {
            startActivityForResult2.launch(Intent(this@KotlinActivity, ResultActivity::class.java), object : OnActivityResultListener {
                override fun onActivityResult(result: ActivityResult?) {
                    val resultCode = result?.resultCode
                    val name = result?.data?.getStringExtra("name")
                    val age = result?.data?.getIntExtra("age", 0)
                    Log.e("Kotlin", "ResultCode:$resultCode,name:$name,age:$age")
                }
            })
        }

        findViewById<View>(R.id.btn_rp1).setOnClickListener {
            requestPermission1.launch(
                object : OnRequestPermissionsResultListener {
                    override fun onRequestPermissionsResult(result: Map<String, Boolean>, isAgreeAll: Boolean) {
                        Log.e("Kotlin", "权限是否全部同意：$isAgreeAll")
                    }
                },
                Manifest.permission.CAMERA
            )
        }
        findViewById<View>(R.id.btn_rp2).setOnClickListener {
            requestPermission2.launch(
                object : OnRequestPermissionsResultListener {
                    override fun onRequestPermissionsResult(result: Map<String, Boolean>, isAgreeAll: Boolean) {
                        Log.e("Kotlin", "权限是否全部同意：$isAgreeAll")
                    }
                },
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        }
    }

}