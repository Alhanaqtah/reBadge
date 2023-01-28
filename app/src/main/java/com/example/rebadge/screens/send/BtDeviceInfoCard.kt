package com.example.rebadge.screens.send

import android.annotation.SuppressLint
import android.bluetooth.BluetoothDevice
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rebadge.R
import com.example.rebadge.devices

@SuppressLint("MissingPermission")
@Composable
fun BtDeviceCard(device: BluetoothDevice) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable {
            client = BluetoothClient(device)
        }
    ) {
        // Информация об устройстве
        Column(
            modifier = Modifier
                .weight(6f)
                .padding(10.dp, 0.dp)
        ) {
            device.name?.toString()?.let {
                Text(
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    text = it,
                )
            }
//            Text(
//                fontSize = MaterialTheme.typography.bodySmall.fontSize,
//                text = info,
//            )
        }

        IconButton(
            modifier = Modifier.weight(1f),
            onClick = {}
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_bluetooth_24),
                contentDescription = "Find available bluetooth devices"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BtDeviceInfoCardPreview() {
    BtDeviceCard(devices.get(0))
}