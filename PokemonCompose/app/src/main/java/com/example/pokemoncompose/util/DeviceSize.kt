package com.example.pokemoncompose.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration

data class DeviceSize(
    val width: DeviceType,
    val height: DeviceType
)

enum class DeviceType { Compact, Medium, Expanded }

/**
 * This utility is built around Jetpack Composes
 * LocalConfiguration device configuration interface
 */
@Composable
fun calculateDeviceSize(): DeviceSize {
    val configuration = LocalConfiguration.current
    val screenWidth by remember(key1 = configuration) {
        mutableStateOf(configuration.screenWidthDp)
    }
    val screenHeight by remember(key1 = configuration) {
        mutableStateOf(configuration.screenHeightDp)
    }

    return DeviceSize(
        width = getScreenWidth(screenWidth),
        height = getScreenHeight(screenHeight)
    )
}

fun getScreenWidth(width: Int): DeviceType = when {
    width < 600 -> DeviceType.Compact
    width < 840 -> DeviceType.Medium
    else -> DeviceType.Expanded
}

fun getScreenHeight(height: Int): DeviceType = when {
    height < 480 -> DeviceType.Compact
    height < 900 -> DeviceType.Medium
    else -> DeviceType.Expanded
}