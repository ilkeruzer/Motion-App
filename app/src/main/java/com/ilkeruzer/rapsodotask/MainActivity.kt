package com.ilkeruzer.rapsodotask

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.ilkeruzer.rapsodotask.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var binding: ActivityMainBinding

    private var manager: SensorManager? = null
    private var accel: Sensor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        manager = getSystemService(SENSOR_SERVICE) as SensorManager
        accel = manager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        manager!!.registerListener(
            this, accel,
            SensorManager.SENSOR_DELAY_GAME
        )
    }


    override fun onSensorChanged(event: SensorEvent) {
        Log.d("event", "${event.values[0]}  ${event.values[1]}")
        binding.shakeBallView.move(event.values[0], event.values[1])
        binding.shakeBallView.invalidate()
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {}

    override fun onResume() {
        super.onResume()
        manager!!.registerListener(
            this, accel,
            SensorManager.SENSOR_DELAY_GAME
        )
    }

    override fun onPause() {
        super.onPause()
        manager!!.unregisterListener(this)
    }

    fun delayFunction(function: ()-> Unit, delay: Long) {
        Handler().postDelayed(function, delay)
    }
}