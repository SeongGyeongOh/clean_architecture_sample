package com.example.architecturekotlin.presenter.main.walk_fragment

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.architecturekotlin.util.common.Logger
import com.example.architecturekotlin.util.common.Pref

class WalkWorker2 constructor(
    val context: Context,
    parameters: WorkerParameters
) : Worker(context, parameters) {

    var pref = Pref(context)

    @RequiresApi(Build.VERSION_CODES.O)
    override fun doWork(): Result {
        if (pref.getBoolVal("needWorker")) {
            Logger.d("워커2 실행")
            val intent = Intent(context, WalkService::class.java)
            ContextCompat.startForegroundService(context, intent)
        }

        return Result.success()
    }
}