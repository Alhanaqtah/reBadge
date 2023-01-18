package com.example.rebadge.screens.send

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rebadge.R

@Composable
fun BtDeviceInfoCard(deviceName: String, info: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable {  }
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
                text = deviceName,
            )
            Text(
                fontSize = MaterialTheme.typography.bodySmall.fontSize,
                text = info,
            )
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
    BtDeviceInfoCard(deviceName = "Device", info = "Info")
}