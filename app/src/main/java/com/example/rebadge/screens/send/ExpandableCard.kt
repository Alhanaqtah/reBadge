package com.example.rebadge.screens.send

import android.annotation.SuppressLint
import android.bluetooth.BluetoothDevice
import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rebadge.R
import com.example.rebadge.btAdapter

@SuppressLint("MissingPermission")
@Composable
fun ExpandableCard() {
    var expandedState by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 500,
                    easing = LinearOutSlowInEasing
                )
            ),
        onClick = {
            expandedState = !expandedState
        }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Информация об устройстве
            Column(
                modifier = Modifier
                    .weight(6f)
                    .padding(10.dp, 0.dp)
            ) {
                Text(
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    text = "Название устройств",
                )
                Text(
                    fontSize = MaterialTheme.typography.bodySmall.fontSize,
                    text = "Подключено",
                )
            }

            IconButton(
                modifier = Modifier.weight(1f),
                onClick = {
                    expandedState = !expandedState
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_track_changes_24),
                    contentDescription = "Find available bluetooth devices"
                )
            }
        }

        var device: Set<BluetoothDevice> = btAdapter.bondedDevices
        for (d in device)
            Log.d("Log", d.address.toString())
        if (expandedState) {
            BtDeviceInfoCard(deviceName = "Device", info = "connected")
            BtDeviceInfoCard(deviceName = "Device", info = "connected")
            BtDeviceInfoCard(deviceName = "Device", info = "connected")
            BtDeviceInfoCard(deviceName = "Device", info = "connected")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ExpandableCardPreview() {
    ExpandableCard()
}