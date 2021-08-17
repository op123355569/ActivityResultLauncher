package com.hg.sample;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.result.ActivityResult;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hg.activityresultlauncher.OnActivityResultListener;
import com.hg.activityresultlauncher.OnRequestPermissionsResultListener;
import com.hg.activityresultlauncher.ProxyRequestPermission;
import com.hg.activityresultlauncher.ProxyStartActivityForResult;

import java.util.Map;

/**
 * Java用法
 * <p>
 * Created by Hollow Goods on 2021-08-17.
 */
public class JavaActivity extends AppCompatActivity {

    private final ProxyStartActivityForResult startActivityForResult1 = new ProxyStartActivityForResult(this);
    private final ProxyStartActivityForResult startActivityForResult2 = new ProxyStartActivityForResult();
    private final ProxyRequestPermission requestPermission1 = new ProxyRequestPermission(this);
    private final ProxyRequestPermission requestPermission2 = new ProxyRequestPermission();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);

        setTitle("Java用法");

        startActivityForResult2.register(this);
        requestPermission2.register(this);

        findViewById(R.id.btn_safr1).setOnClickListener(v -> {
            startActivityForResult1.launch(new Intent(JavaActivity.this, ResultActivity.class), result -> {
                if (result != null) {
                    int resultCode = result.getResultCode();
                    String name = "";
                    int age = 0;

                    if (result.getData() != null) {
                        name = result.getData().getStringExtra("name");
                        age = result.getData().getIntExtra("age", age);
                    }

                    Log.e("Java", "ResultCode:" + resultCode + ",name:" + name + ",age:" + age);
                }
            });
        });
        findViewById(R.id.btn_safr2).setOnClickListener(v -> {
            startActivityForResult2.launch(new Intent(JavaActivity.this, ResultActivity.class), result -> {
                if (result != null) {
                    int resultCode = result.getResultCode();
                    String name = "";
                    int age = 0;

                    if (result.getData() != null) {
                        name = result.getData().getStringExtra("name");
                        age = result.getData().getIntExtra("age", age);
                    }

                    Log.e("Java", "ResultCode:" + resultCode + ",name:" + name + ",age:" + age);
                }
            });
        });

        findViewById(R.id.btn_rp1).setOnClickListener(v -> requestPermission1.launch(
                (result, isAgreeAll) -> Log.e("Java", "权限是否全部同意：" + isAgreeAll),
                Manifest.permission.CAMERA
        ));
        findViewById(R.id.btn_rp2).setOnClickListener(v -> {
            requestPermission2.launch(
                    (result, isAgreeAll) -> {
                        Log.e("Java", "权限是否全部同意：" + isAgreeAll);
                    },
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
            );
        });
    }
}
