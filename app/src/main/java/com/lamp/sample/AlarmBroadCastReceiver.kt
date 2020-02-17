package com.lamp.sample

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.lamp.sample.appstate.AppState
import com.lamp.sample.repository.LampForegroundService
import com.lamp.sample.utils.LampLog

/**
 * Created by ZCO Engineering Dept. on 06,February,2020
 */
class AlarmBroadCastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val action: String? = intent.action
        if(Intent.ACTION_BOOT_COMPLETED == action){
            LampLog.e("Receiver Triggered From Boot")
            //Start Service Directly
            if(AppState.session.isLoggedIn) {
                val serviceIntent = Intent(context, LampForegroundService::class.java).apply {
                    putExtra("inputExtra", "Foreground Service Example in Android")
                    putExtra("set_alarm", true)

                }
                ContextCompat.startForegroundService(context, serviceIntent)
            }
        }else{
            LampLog.e("Receiver Triggered ")
            if(AppState.session.isLoggedIn) {
                val serviceIntent = Intent(context, LampForegroundService::class.java).apply {
                    putExtra("inputExtra", "Foreground Service Example in Android")
                    putExtra("set_alarm", false)
                }
                ContextCompat.startForegroundService(context, serviceIntent)
            }
        }
    }
}