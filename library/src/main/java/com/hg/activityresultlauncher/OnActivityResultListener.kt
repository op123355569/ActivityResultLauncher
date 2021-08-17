package com.hg.activityresultlauncher

import androidx.activity.result.ActivityResult

/**
 * 界面返回结果鉴定
 * <p>
 * Created by Hollow Goods on 2021-08-09.
 */
interface OnActivityResultListener {

    /**
     * 界面返回结果
     *
     * @param result ActivityResult
     */
    fun onActivityResult(result: ActivityResult?)

}