package com.hg.activityresultlauncher

import androidx.activity.result.ActivityResultCaller
import androidx.activity.result.contract.ActivityResultContracts

/**
 * 代理权限请求
 * <p>
 * Created by Hollow Goods on 2021-08-09.
 */
class ProxyRequestPermission : BaseActivityResultLauncher<Array<out String>, Map<String, Boolean>> {

    /**
     * 构造方法
     *
     * 在[launch]、[launch2]前必须先[register]
     */
    constructor() : super(ActivityResultContracts.RequestMultiplePermissions())

    /**
     * 构造方法
     *
     * 无需再调用[register]，但是必须在[onResume]前初始化
     *
     * @param caller [ActivityResultCaller]
     */
    constructor(caller: ActivityResultCaller) : super(caller, ActivityResultContracts.RequestMultiplePermissions())

    /**
     * 是否全部同意
     *
     * @param result [Map]
     */
    private fun isAgreeAll(result: Map<String, Boolean>): Boolean {

        var isAgreeAll = true

        run jump@{
            result.forEach { (_, isAgree) ->
                if (!isAgree) {
                    isAgreeAll = false
                    return@jump
                }
            }
        }

        return isAgreeAll
    }

    /**
     * 执行
     *
     * @param onRequestPermissionsResultListener [OnRequestPermissionsResultListener]
     * @param permissions [Array]
     */
    fun launch(onRequestPermissionsResultListener: OnRequestPermissionsResultListener, vararg permissions: String) {
        launch(permissions) { result ->
            onRequestPermissionsResultListener.onRequestPermissionsResult(result, isAgreeAll(result))
        }
    }

    /**
     * 执行
     *
     * @param permissions [Array]
     * @param onRequestPermissionsResultListener [OnRequestPermissionsResultListener]
     */
    fun launch2(permissions: Array<String>, onRequestPermissionsResultListener: OnRequestPermissionsResultListener) {
        launch(permissions) { result ->
            onRequestPermissionsResultListener.onRequestPermissionsResult(result, isAgreeAll(result))
        }
    }

}