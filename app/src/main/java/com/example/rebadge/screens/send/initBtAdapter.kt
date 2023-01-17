package com.example.rebadge.screens.send

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.Context.BLUETOOTH_SERVICE

fun initBtAdapter(context: Context): BluetoothAdapter {
    val btManager = context.getSystemService(BLUETOOTH_SERVICE) as BluetoothManager
    return btManager.adapter
}