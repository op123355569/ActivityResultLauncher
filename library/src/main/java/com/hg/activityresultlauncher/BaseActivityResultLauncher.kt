package com.hg.activityresultlauncher

import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultCaller
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

/**
 * 基Launcher
 * <p>
 * Created by Hollow Goods on 2021-08-17.
 */
open class BaseActivityResultLauncher<I, O> : DefaultLifecycleObserver {

    private val contract: ActivityResultContract<I, O>
    private var launcher: ActivityResultLauncher<I>? = null
    private var callback: ActivityResultCallback<O>? = null
    private var isAlive = true

    constructor(contract: ActivityResultContract<I, O>) {
        this.contract = contract
    }

    constructor(caller: ActivityResultCaller, contract: ActivityResultContract<I, O>) {
        this.contract = contract
        register(caller)
    }

    /**
     * 注册 必须在[onResume]之前调用
     *
     * @param caller [ActivityResultCaller]
     */
    fun register(caller: ActivityResultCaller) {

        if (caller is ComponentActivity) {
            caller.lifecycle.addObserver(this)
        } else if (caller is Fragment) {
            caller.lifecycle.addObserver(this)
        }

        this.launcher = caller.registerForActivityResult(contract) {
            callback?.onActivityResult(it)
        }
    }

    protected fun launch(input: I?, callback: ActivityResultCallback<O>) {
        if (isAlive) {
            this.callback = callback
            launcher?.launch(input)
        }
    }

    override fun onDestroy(owner: LifecycleOwner) {
        isAlive = false
        owner.lifecycle.removeObserver(this)
    }

}