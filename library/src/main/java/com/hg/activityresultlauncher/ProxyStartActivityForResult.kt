package com.hg.activityresultlauncher

import android.content.Intent
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCaller
import androidx.activity.result.contract.ActivityResultContracts

/**
 * 代理界面返回结果
 * <p>
 * Created by Hollow Goods on 2021-08-09.
 */
class ProxyStartActivityForResult : BaseActivityResultLauncher<Intent, ActivityResult> {

    /**
     * 构造方法
     *
     * 在[launch]前必须先[register]
     */
    constructor() : super(ActivityResultContracts.StartActivityForResult())

    /**
     * 构造方法
     *
     * 无需再调用[register]，但是必须在[onResume]前初始化
     *
     * @param caller [ActivityResultCaller]
     */
    constructor(caller: ActivityResultCaller) : super(caller, ActivityResultContracts.StartActivityForResult())

    /**
     * 执行
     *
     * @param intent [Intent]
     * @param onActivityResultListener [OnActivityResultListener]
     */
    fun launch(intent: Intent, onActivityResultListener: OnActivityResultListener) {
        launch(intent) { result ->
            onActivityResultListener.onActivityResult(result)
        }
    }

}