package com.hg.activityresultlauncher

/**
 * 权限请求结果监听
 * <p>
 * Created by Hollow Goods on 2021-08-09.
 */
interface OnRequestPermissionsResultListener {

    /**
     * 权限请求结果
     *
     * @param result Map
     * @param isAgreeAll Boolean
     */
    fun onRequestPermissionsResult(result: Map<String, Boolean>, isAgreeAll: Boolean)

}