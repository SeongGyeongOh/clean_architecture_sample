package com.example.architecturekotlin.presenter.main.walk_fragment

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.example.architecturekotlin.util.common.Logger

class MyReceiver : BroadcastReceiver() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onReceive(context: Context, intent: Intent) {
        Logger.d("브로드캐스트 리시버 - onReceive")

        when (intent.action) {
            Intent.ACTION_BOOT_COMPLETED -> {
                Logger.d("브로드캐스트 리시버 - ACTION_BOOT_COMPLETED, ACTION_RESTART")

                val workManager = WorkManager.getInstance(context)
                val startServiceRequest = OneTimeWorkRequest.Builder(WalkWorker::class.java)
                    .build()

                workManager.enqueue(startServiceRequest)
            }

            "ACTION_RESTART" -> {
                val workManager = WorkManager.getInstance(context)
                val startServiceRequest = OneTimeWorkRequest.Builder(WalkWorker2::class.java)
                    .build()

                workManager.enqueue(startServiceRequest)
            }
        }
    }

    val WORK_TAG = "StartServiceInFragment"
    val REPEAT_TAG = "REPEAT"
}
