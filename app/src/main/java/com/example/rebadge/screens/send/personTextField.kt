package com.example.rebadge.screens.send

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

@Composable
fun personTextField(fieldLabel: String): String {
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        onValueChange = {
            if (it.length <= 20) text = it
            text = it
        },
        label = { Text(fieldLabel) },
        modifier = Modifier.fillMaxWidth(),

        // Иконка крестика для удаления текста
        trailingIcon = {
            Icon(
                Icons.Default.Clear,
                contentDescription = "clear text",
                modifier = Modifier
                    .clickable { text = "" }
            )
        }
    )

    return text
}