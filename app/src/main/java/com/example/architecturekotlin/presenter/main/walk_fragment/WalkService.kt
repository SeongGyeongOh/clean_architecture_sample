package com.example.architecturekotlin.presenter.main.walk_fragment

import android.app.*
import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Build
import android.os.IBinder
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.example.architecturekotlin.R
import com.example.architecturekotlin.domain.model.WalkModel
import com.example.architecturekotlin.domain.usecase.UpsertWalkUseCase
import com.example.architecturekotlin.presenter.main.MainActivity
import com.example.architecturekotlin.util.common.Logger
import com.example.architecturekotlin.util.common.Pref
import com.example.architecturekotlin.util.common.getCurrentDate
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class WalkService @Inject constructor(): Service(), SensorEventListener {

    @Inject
    lateinit var upsertWalkUseCase: UpsertWalkUseCase

    @Inject
    lateinit var pref: Pref

    private var sensorManager: SensorManager? = null
    private var sensor: Sensor? = null
    private var noti: Notification? = null
    var defaultStep: Int = 0
    var sCounterSteps: Int = 0
    var stepType: StepType = StepType.INIT
    var storedCount: Int = 0
    var dateInit: Boolean = false

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Logger.d("[서비스] - onStartCommand")

        checkServiceCondition(intent)

        /** 포그라운드 서비스 돌리기 */
        /** 아래 notification을 띄우지 않는 경우 앱이 죽음 */
        createNotificationChannel()
        setupNotification()
        setupSensorManager()

        return START_STICKY
    }

    private fun checkServiceCondition(intent: Intent?) {
        pref.setBoolValue("isServiceRunning", true)
        pref.setBoolValue("needWorker", true)

        val isReboot = intent?.getBooleanExtra("isReboot", false) ?: false
        val isRestart = intent?.getBooleanExtra("isRestart", false) ?: false
        val isDateInit = pref.getStringValue("today") != System.currentTimeMillis().getCurrentDate()
        val isNotFirstRun = pref.getBoolVal("isNotFirstRun")
        val isServicePause = pref.getBoolVal("isServicePause")

        if (isServicePause) {
            Logger.d("일시정지를 눌렀다가 다시 실행할 때")
            stepType = StepType.FIRST
            storedCount = pref.getIntValue("rebootDefault")
            pref.setBoolValue("isServicePause", false)
        } else if (isRestart) {
            Logger.d("서비스가 혼자 꺼졌다가 다시 실행될 때")
            sCounterSteps = pref.getIntValue("defaultSCounterSteps")
            storedCount = pref.getIntValue("rebootDefault")
        } else if ((isReboot && isDateInit) || !isNotFirstRun){
            Logger.d("핸드폰을 재실행했거나 앱 최초 실행일 때")
            stepType = StepType.FIRST
            pref.setBoolValue("isNotFirstRun", true)
        } else if (isReboot && !isDateInit) {
            Logger.d("핸드폰을 재실행했고 날짜가 아직 리셋되지 않았을 때")
            sCounterSteps = 0
            storedCount = pref.getIntValue("rebootDefault")
        } else if (isDateInit) {
            stepType = StepType.DATE_INIT
        } else {
            Logger.d("앱 최초 실행이 아니고, 일시정지를 눌렀다가 다시 실행할 때")
            sCounterSteps = pref.getIntValue("defaultSCounterSteps")
            storedCount = pref.getIntValue("rebootDefault")
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "Channel_AndroidArchitecture",
                "StepCounterNotification",
                NotificationManager.IMPORTANCE_DEFAULT
            )

            channel.vibrationPattern = longArrayOf(0)
            channel.enableVibration(true)
            channel.setSound(null, null)

            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }
    }

    private fun setupNotification() {
        val notiIntent = Intent(this, MainActivity::class.java)
        val pIntent = PendingIntent.getActivity(this, 0, notiIntent, 0)

        noti = NotificationCompat.Builder(this, "Channel_AndroidArchitecture")
            .setContentTitle("안드로이드 아키텍쳐")
            .setContentText("만보기 돌아가는중")
            .setSmallIcon(R.drawable.icon_walk)
            .setContentIntent(pIntent)
            .build()

        startForeground(1, noti)
    }

    private fun setupSensorManager() {
        sensorManager = this.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensor = sensorManager?.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)

        if (sensor == null) {
            Toast.makeText(this, "실행할 수 있는 센서가 없습니다", Toast.LENGTH_SHORT).show()
        } else {
            try {
                sensorManager?.registerListener(
                    this,
                    sensor,
                    SensorManager.SENSOR_DELAY_FASTEST
                )
            } catch (e: Exception) {
                Logger.e("서비스 - 리스너 실패 ${e.cause} : ${e.message}")
            }
        }
    }

    override fun onDestroy() {
        Logger.d("[서비스] - onDestroy")
        pref.setIntValue("defaultStep", defaultStep)

        if (pref.getBoolVal("isServiceRunning")) {
            Logger.d("[서비스] - 혼자 죽음")
            val intent = Intent(this, MyReceiver::class.java)
            intent.action = "ACTION_RESTART"
            sendBroadcast(intent)
        } else {
            pref.setBoolValue("needWorker", false)
            pref.setBoolValue("isServicePause", true)
        }

        stopForeground(true)
        sensorManager?.unregisterListener(this)

        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onSensorChanged(event: SensorEvent?) {
        CoroutineScope(Dispatchers.IO).launch {
            if (event?.sensor?.type == Sensor.TYPE_STEP_COUNTER) {
                val isDateRecorded = pref.getStringValue("today").isNullOrBlank()
                val date = System.currentTimeMillis().getCurrentDate()
                dateInit = pref.getStringValue("today") != date

                if (stepType == StepType.DATE_INIT) {
                    Logger.d("일시정지를 눌렀다가 다시 시작했는데 날짜가 리셋됐을 때")
                    sCounterSteps = event.values[0].toInt()
                    pref.setIntValue("defaultStep2", sCounterSteps)
                    stepType = StepType.INIT
                }
                else if (dateInit && !isDateRecorded) {
                    Logger.d("날짜가 리셋됐을 때/앱 최초 실행이 아닐 때${pref.getIntValue("defaultSCounterSteps")}")
                    sCounterSteps = pref.getIntValue("defaultSCounterSteps")
                    storedCount = 0
                    pref.setStringValue("today", date)
                    stepType = StepType.INIT
                } else if (dateInit && isDateRecorded) {
                    Logger.d("날짜가 리셋된 후 최초 실행일 때")
                    sCounterSteps = event.values[0].toInt()
                    storedCount = 0
                    pref.setStringValue("today", date)
                    stepType = StepType.INIT
                } else if (stepType == StepType.FIRST) {
                    Logger.d("일시정지를 눌렀다가 다시 실행할 때 $sCounterSteps")
                    sCounterSteps = event.values[0].toInt()
                    pref.setIntValue("defaultStep2", sCounterSteps)
                    stepType = StepType.INIT
                }

                defaultStep = sCounterSteps
                val addedVal = event.values[0].toInt() - sCounterSteps + storedCount

                pref.setIntValue("rebootDefault", addedVal)
                pref.setIntValue("defaultSCounterSteps", event.values[0].toInt())

                insertData(date, addedVal)

                Logger.d("디비에 추가되는 데이터[${addedVal}] : 이벤트[${event.values[0].toInt()}] " +
                        ": 초기스텝[$sCounterSteps] : 저장된 스텝[$storedCount]")
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    private fun insertData(
        date: String,
        addedVal: Int
    ) = CoroutineScope(Dispatchers.IO).launch {
        upsertWalkUseCase.buildUseCase(WalkModel(date = date, count = addedVal))
    }
}

enum class StepType {
    INIT, FIRST, DATE_INIT
}