package com.example.architecturekotlin.presenter.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.architecturekotlin.R
import com.example.architecturekotlin.databinding.ActivityMainBinding
import com.example.architecturekotlin.presenter.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>({
    ActivityMainBinding.inflate(it)
}) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}