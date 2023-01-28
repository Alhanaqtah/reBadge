package com.example.rebadge.screens.send

import android.annotation.SuppressLint
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
import com.example.rebadge.devices

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
            if (!btAdapter.isEnabled) btAdapter.enable()
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

        if (expandedState && devices.isNotEmpty()) {
            devices.distinctBy {it.name}
            devices.distinctBy {it.name == null}
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                itemsIndexed(devices) { _, item ->
                    BtDeviceCard(device = item)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ExpandableCardPreview() {
    ExpandableCard()
}