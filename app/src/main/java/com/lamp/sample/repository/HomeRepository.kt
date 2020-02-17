package com.lamp.sample.repository

import com.lamp.sample.network.model.SensorEventRequest
import com.lamp.sample.network.retrofit.ApiInterface
import com.lamp.sample.network.retrofit.RetrofitClient

/**
 * Created by ZCO Engineering Dept. on 05,February,2020
 */
class HomeRepository {

    private var apiInterface: ApiInterface = RetrofitClient.instance

    suspend fun addSensorData(participantId:String, sensorEventRequest: SensorEventRequest) = apiInterface.addSensorEvent(participantId,sensorEventRequest)

}