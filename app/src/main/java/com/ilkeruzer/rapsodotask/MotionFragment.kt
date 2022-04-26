package com.ilkeruzer.rapsodotask

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
import com.ilkeruzer.rapsodotask.databinding.FragmentMotionBinding


class MotionFragment : Fragment(), SensorEventListener {

    private lateinit var binding: FragmentMotionBinding

    private var manager: SensorManager? = null
    private var accel: Sensor? = null

    companion object {
        private const val TAG = "MotionFragment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        manager = context?.getSystemService(AppCompatActivity.SENSOR_SERVICE) as SensorManager
        accel = manager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMotionBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onSensorChanged(event: SensorEvent) {
        binding.shakeBallView.move(event.values[0], event.values[1])
        binding.shakeBallView.invalidate()
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {}

    override fun onResume() {
        super.onResume()
        Log.d(TAG,"onResume")
        manager!!.registerListener(
            this, accel,
            SensorManager.SENSOR_DELAY_GAME
        )
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG,"onPause")
        manager!!.unregisterListener(this)
    }





}