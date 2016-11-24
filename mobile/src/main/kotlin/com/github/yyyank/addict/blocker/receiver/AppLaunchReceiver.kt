package com.github.yyyank.addict.blocker.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

/**
 * Created by yy_yank on 2016/11/24.
 */
class AppLaunchReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if(Intent.ACTION_BOOT_COMPLETED !== intent?.getAction()) {
            return
        }
        resultData = if("twitter" == context?.applicationInfo?.name) {
            Toast.makeText(context, "Twitter使いすぎだこの廃人め！！", Toast.LENGTH_LONG).show()
            // 無効化
            null
        } else {
            resultData
        }
    }
}