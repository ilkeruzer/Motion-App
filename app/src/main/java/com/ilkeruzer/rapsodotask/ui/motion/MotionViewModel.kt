package com.ilkeruzer.rapsodotask.ui.motion

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ilkeruzer.rapsodotask.data.local.model.MotionCoordinates
import com.ilkeruzer.rapsodotask.data.local.model.MotionEntity
import com.ilkeruzer.rapsodotask.data.repository.MotionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MotionViewModel @Inject constructor(
    private val repository: MotionRepository
): ViewModel() {

    var count = MutableLiveData<Int>()
    var isNew = false;
    var motionEntity: MotionEntity? = null
    var motionCoordinates = arrayListOf<MotionCoordinates>()

    fun saveMotions() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertMotionData(MotionEntity(coordinates = motionCoordinates))
        }
    }
}