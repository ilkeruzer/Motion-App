package com.ilkeruzer.motionApp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ilkeruzer.motionApp.data.local.model.MotionEntity
import com.ilkeruzer.motionApp.data.repository.MotionRepository
import com.ilkeruzer.motionApp.utils.Constants.HOME_PAGING_SIZE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MotionRepository
): ViewModel() {


    fun getMotions(): Flow<PagingData<MotionEntity>> {
        return Pager(PagingConfig(HOME_PAGING_SIZE)) {
            repository.getAllMotion()
        }.flow.cachedIn(viewModelScope)
    }
}