package com.github.yyyank.addict.blocker.receiver

import android.app.ActivityManager
import android.app.Service
import android.app.usage.UsageEvents
import android.app.usage.UsageStatsManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import android.widget.Toast

/**
 * Created by yy_yank on 2016/11/24.
 */
class AppLaunchObserver : Service() {

    //サービスに接続するためのBinder
    inner class MyServiceLocalBinder : Binder() {
        //サービスの取得
        internal val service: AppLaunchObserver
            get() = this@AppLaunchObserver
    }

    //Binderの生成
    private val binder = MyServiceLocalBinder()

    override fun onBind(intent: Intent?): IBinder = binder

    override fun onCreate() {
        super.onCreate()


        Log.i("AddictBlocker", "onCreate")
        val stats = getSystemService("usagestats") as UsageStatsManager
        val usageEvents = stats.queryEvents(0, 10)
        while (usageEvents.hasNextEvent()) {
            val event = UsageEvents.Event()
            usageEvents.getNextEvent(event);
            Log.d("AddictBlocker", event.packageName)
        }
    }
}
