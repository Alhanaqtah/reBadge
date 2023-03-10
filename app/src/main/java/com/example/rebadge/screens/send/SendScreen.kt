package com.example.rebadge.screens.send

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

lateinit var client: BluetoothClient

@Composable
fun SendScreen() {
    var name       by remember { mutableStateOf("") }
    var surname    by remember { mutableStateOf("") }
    var patronymic by remember { mutableStateOf("") }
    var position   by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = 25.dp,
                top = 25.dp,
                end = 25.dp,
                bottom = 10.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.weight(1.25f)
        ) {
            name       = personTextField(fieldLabel = "Name")
            surname    = personTextField(fieldLabel = "Surname")
            patronymic = personTextField(fieldLabel = "Patronymic")
            position   = personTextField(fieldLabel = "Position")
        }

        Box(
            modifier = Modifier
                .weight(2f)
        ) {
            ExpandableCard()
        }

        Button(
            onClick = {
                val message: String = "${name}#${surname}#${patronymic}#${position}"
                    .replace('ё', 'е')
                    .replace('Ё', 'Е')
                client.connect(
                    message
                )
            },
            modifier = Modifier
                .width(150.dp)
        ) {
            Text("SEND")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SendScreenPreview() {
    SendScreen()
}