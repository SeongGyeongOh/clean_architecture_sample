package com.gyeong.architecturekotlin.presenter.main

import android.os.Bundle
import com.gyeong.architecturekotlin.databinding.ActivityMainBinding
import com.gyeong.architecturekotlin.presenter.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>({
    ActivityMainBinding.inflate(it)
}) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}