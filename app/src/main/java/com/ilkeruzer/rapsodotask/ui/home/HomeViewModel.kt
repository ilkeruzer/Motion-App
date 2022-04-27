package com.ilkeruzer.rapsodotask.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ilkeruzer.rapsodotask.data.local.model.MotionEntity
import com.ilkeruzer.rapsodotask.data.repository.MotionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MotionRepository
): ViewModel() {


    fun getMotions(): Flow<PagingData<MotionEntity>> {
        return Pager(PagingConfig(20)) {
            repository.getAllMotion()
        }.flow.cachedIn(viewModelScope)
    }
}