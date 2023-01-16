@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package com.example.rebadge.screens.send

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

var nameTextField = ""
var surnameTextField = ""
var patronymicTextField = ""
var positionTextField = ""

@Composable
fun SendScreen() {


    var name by remember { mutableStateOf("") }
    var surname by remember { mutableStateOf("") }
    var patronymic by remember { mutableStateOf("") }
    var position by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = 25.dp,
                top = 25.dp,
                bottom = 10.dp,
                end = 25.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // NameField
        OutlinedTextField(
            value = name,
            onValueChange = {
                if (it.length <= 20) name = it
                nameTextField = it
            },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth(),

            // Иконка крестика для удаления текста
            trailingIcon = {
                Icon(
                    Icons.Default.Clear,
                    contentDescription = "clear text",
                    modifier = Modifier
                        .clickable { name = "" }
                )
            }
        )

        // SurnameField
        OutlinedTextField(
            value = surname,
            onValueChange = {
                if (it.length <= 20) surname = it
                surnameTextField = it
            },
            label = { Text("Surname") },
            modifier = Modifier.fillMaxWidth(),

            // Иконка крестика для удаления текста
            trailingIcon = {
                Icon(
                    Icons.Default.Clear,
                    contentDescription = "clear text",
                    modifier = Modifier
                        .clickable { name = "" }
                )
            }
        )

        // PatronymicField
        OutlinedTextField(
            value = patronymic,
            onValueChange = {
                if (it.length <= 20) patronymic = it
                patronymicTextField = it
            },
            label = { Text("Patronymic") },
            modifier = Modifier.fillMaxWidth(),

            // Иконка крестика для удаления текста
            trailingIcon = {
                Icon(
                    Icons.Default.Clear,
                    contentDescription = "clear text",
                    modifier = Modifier
                        .clickable { name = "" }
                )
            }
        )

        // PositionField
        OutlinedTextField(
            value = position,
            onValueChange = {
                if (it.length <= 20) position = it
                positionTextField = it
            },
            label = { Text("Position") },
            modifier = Modifier.fillMaxWidth(),

            // Иконка крестика для удаления текста
            trailingIcon = {
                Icon(
                    Icons.Default.Clear,
                    contentDescription = "clear text",
                    modifier = Modifier
                        .clickable { name = "" }
                )
            }
        )

        Button(
            onClick = {},
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