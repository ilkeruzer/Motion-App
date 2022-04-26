package com.ilkeruzer.rapsodotask

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import com.ilkeruzer.rapsodotask.data.MotionCoordinates
import com.ilkeruzer.rapsodotask.data.MotionDatabase
import com.ilkeruzer.rapsodotask.data.MotionEntity
import com.ilkeruzer.rapsodotask.databinding.FragmentMotionBinding
import com.ilkeruzer.rapsodotask.extentions.setGone
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class MotionFragment : Fragment(), SensorEventListener {

    private lateinit var binding: FragmentMotionBinding

    private var manager: SensorManager? = null
    private var accel: Sensor? = null

    private var count = MutableLiveData<Int>()
    private var isNew = false;

    companion object {
        private const val TAG = "MotionFragment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initMotionDb()
    }

    private fun setUpCountDownTimer() {
        lifecycleScope.launch {
            for (i in 10 downTo 0) {
                count.postValue(i)
                delay(1000L)
            }
        }

        count.observe(viewLifecycleOwner) {
            binding.countTextview.text = it.toString()
        }
    }

    private fun initMotionDb() {
        val db = MotionDatabase.create(requireContext())

        val motionDao = db.motionDao()

        runBlocking {
            launch(Dispatchers.IO) {
                motionDao.insertMotion(MotionEntity(coordinates = arrayListOf(MotionCoordinates(1,2))))
                val motions: List<MotionEntity> = motionDao.getAllMotions()
                Log.d(TAG, motions.size.toString())
            }
        }

    }

    private fun initSensor() {
        if (isNew) {
            manager = context?.getSystemService(AppCompatActivity.SENSOR_SERVICE) as SensorManager
            accel = manager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        argIsNew()
        initSensor()
        binding = FragmentMotionBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkView()
    }

    private fun checkView() {
        if (!isNew) {
            binding.countTextview.setGone()
        } else {
            setUpCountDownTimer()
        }
    }

    private fun argIsNew() {
        arguments?.let {
            isNew = it.getBoolean("isNew")
        }

    }

    override fun onSensorChanged(event: SensorEvent) {
        binding.shakeBallView.move(event.values[0], event.values[1])
        binding.shakeBallView.invalidate()
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {}


    override fun onStart() {
        super.onStart()
        Log.d(TAG,"onStart")
        if (isNew)
            manager!!.registerListener(
                this, accel,
                SensorManager.SENSOR_DELAY_GAME
            )
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG,"onPause")
        if (isNew) {
            manager!!.unregisterListener(this)
        }
    }





}