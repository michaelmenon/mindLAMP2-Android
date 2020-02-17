package com.lamp.sample.aware

import android.content.ContentValues
import android.content.Context
import android.os.Handler
import com.aware.Aware
import com.aware.Aware_Preferences
import com.aware.WiFi
import com.lamp.sample.network.model.DimensionData
import com.lamp.sample.network.model.SensorEventRequest
import com.lamp.sample.utils.LampLog

/**
 * Created by ZCO Engineering Dept. on 06,February,2020
 */
class WifiData constructor(awareListener: AwareListener, context: Context) {
    init{
        //Wifi sensor settings
        Aware.setSetting(
            context,
            Aware_Preferences.FREQUENCY_WIFI,
            5
        )
        Aware.setSetting(context, Aware_Preferences.STATUS_WIFI, true)
        Aware.startWiFi(context)//start sensor
        //Sensor Observer
        WiFi.setSensorObserver(object : WiFi.AWARESensorObserver {
            override fun onWiFiScanEnded() {
            }
            override fun onWiFiAPDetected(data: ContentValues?) {
                if(data != null){
                    val data = DimensionData(null,null,null,null,null,null,null,null,null,null,null,data.getAsString("bssid"),data.getAsString("ssid"),null,null,null)
                    val sensorEventRequest = SensorEventRequest(data,"lamp.wifi",System.currentTimeMillis())
                    awareListener.getWifiData(sensorEventRequest)
                    Handler().postDelayed({
                            Aware.stopWiFi(context)
                    }, 3000)
                }

            }
            override fun onWiFiScanStarted() {
                LampLog.e("Scan Started")

            }
            override fun onWiFiDisabled() {
            }
        })

    }
}