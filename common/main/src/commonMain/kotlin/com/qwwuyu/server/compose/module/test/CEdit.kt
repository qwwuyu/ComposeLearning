package com.qwwuyu.server.compose.module.test

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

class CEdit {
    companion object {
        @Composable
        fun main() {
            CEditMain()
        }
    }
}

@Composable
fun CEditMain() {
    Column(modifier = Modifier.verticalScroll(state = rememberScrollState())) {
        var text by remember { mutableStateOf(TextFieldValue("")) }
        TextField(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier.padding(8.dp).fillMaxWidth(),
            label = { Text("label") },
            placeholder = { Text("placeholder") }
        )

        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier.padding(8.dp).fillMaxWidth(),
            label = { Text(text = "Password") },
            placeholder = { Text(text = "123456") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )


        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier.padding(8.dp).fillMaxWidth(),
            label = { Text(text = "Email address") },
            placeholder = { Text(text = "Your email") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            leadingIcon = { Icon(Icons.Default.Email, contentDescription = "Email") },
            trailingIcon = {
                Icon(
                    Icons.Default.Delete,
                    contentDescription = "Delete",
                    modifier = Modifier.clickable { text = TextFieldValue("") })
            },
        )

        var numberText by remember { mutableStateOf(TextFieldValue("")) }
        OutlinedTextField(
            value = numberText,
            onValueChange = { numberText = it },
            modifier = Modifier.padding(8.dp).fillMaxWidth(),
            label = { Text(text = "Phone number") },
            placeholder = { Text(text = "88888888") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        )

        BasicTextField(
            value = text,
            onValueChange = { newValue -> text = newValue },
            maxLines = 3,
            enabled = true,
            modifier = Modifier.padding(8.dp) // margin
                .border(2.dp, Color.White) // outer border
                .padding(8.dp) // space between the borders
                .border(2.dp, Color.Green) // inner border
                .padding(8.dp), // padding,
        )
    }
}