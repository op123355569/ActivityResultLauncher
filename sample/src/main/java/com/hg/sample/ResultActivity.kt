package com.hg.sample

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity

/**
 * 返回结果界面
 * <p>
 * Created by Hollow Goods on 2021-08-17.
 */
class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        title = "返回结果界面"

        setResult(RESULT_OK, Intent().apply {
            putExtra("name", "Mike")
            putExtra("age", 18)
        })
    }

}