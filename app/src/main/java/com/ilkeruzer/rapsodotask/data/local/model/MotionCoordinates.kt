package com.ilkeruzer.rapsodotask.data.local.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MotionCoordinates(
    val positionX: Float,
    val positionY: Float
) : Parcelable
