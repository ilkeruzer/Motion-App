package com.ilkeruzer.rapsodotask.ui.motion

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
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ilkeruzer.rapsodotask.data.local.model.MotionCoordinates
import com.ilkeruzer.rapsodotask.databinding.FragmentMotionBinding
import com.ilkeruzer.rapsodotask.utils.Constants.MOTION_COUNT_DOWN_TIMER_DELAY
import com.ilkeruzer.rapsodotask.utils.Constants.MOTION_REPLAY_DELAY
import com.ilkeruzer.rapsodotask.utils.extentions.setGone
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MotionFragment : Fragment(), SensorEventListener {

    private lateinit var binding: FragmentMotionBinding
    private val mViewModel: MotionViewModel by viewModels()

    private var manager: SensorManager? = null
    private var accel: Sensor? = null

    private val args: MotionFragmentArgs by navArgs()


    companion object {
        private const val TAG = "MotionFragment"
    }


    private fun setUpCountDownTimer() {
        lifecycleScope.launch {
            for (i in 10 downTo 0) {
                mViewModel.count.postValue(i)
                delay(MOTION_COUNT_DOWN_TIMER_DELAY)
            }
        }

        mViewModel.count.observe(viewLifecycleOwner) {
            binding.countTextview.text = it.toString()

            if (it == 0) {
                mViewModel.isNew = false
                manager!!.unregisterListener(this)
                mViewModel.saveMotions()
                findNavController().popBackStack()
            }
        }
    }


    private fun initSensor() {
        if (mViewModel.isNew) {
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
        replayMotion()
    }

    private fun replayMotion() {
        with(mViewModel) {
            lifecycleScope.launch {
                if (!mViewModel.isNew)
                    motionEntity?.coordinates?.forEachIndexed { index, motionCoordinates ->
                        delay(MOTION_REPLAY_DELAY)
                        binding.shakeBallView.move(
                            motionCoordinates.positionX,
                            motionCoordinates.positionY
                        )

                        if (index == motionEntity!!.coordinates!!.size - 1) {
                            findNavController().popBackStack()
                        }
                    }
            }
        }
    }

    private fun checkView() {
        if (!mViewModel.isNew) {
            binding.countTextview.setGone()
        } else {
            setUpCountDownTimer()
        }
    }

    private fun argIsNew() {
        mViewModel.motionEntity = args.motion
        mViewModel.isNew = mViewModel.motionEntity == null
    }

    override fun onSensorChanged(event: SensorEvent) {
        if (mViewModel.isNew) {
            mViewModel.motionCoordinates.add(MotionCoordinates(event.values[0], event.values[1]))
        }
        binding.shakeBallView.move(event.values[0], event.values[1])

    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {}


    override fun onStart() {
        super.onStart()
        Log.d(TAG,"onStart")
        if (mViewModel.isNew)
            manager!!.registerListener(
                this, accel,
                SensorManager.SENSOR_DELAY_GAME
            )
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG,"onPause")
        if (mViewModel.isNew) {
            manager!!.unregisterListener(this)
        }
    }





}