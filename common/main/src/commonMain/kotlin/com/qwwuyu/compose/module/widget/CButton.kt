package com.qwwuyu.compose.module.widget

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.qwwuyu.base.platform.imageResource
import com.qwwuyu.compose.platform.Res
import com.qwwuyu.compose.theme.purple
import com.qwwuyu.compose.theme.purple200

class CButton {
    companion object {
        @Composable
        fun main() {
            CButtonMain()
        }
    }
}

@Composable
fun CButtonMain() {
    Column(modifier = Modifier.verticalScroll(state = rememberScrollState())) {
        Button(onClick = {}, modifier = Modifier.padding(8.dp)) { Text(text = "Button") }
        Button(onClick = {}, modifier = Modifier.padding(8.dp), enabled = false) { Text(text = "enabled = false") }
        TextButton(onClick = {}, modifier = Modifier.padding(8.dp)) { Text(text = "TextButton") }
        TextButton(onClick = {}, modifier = Modifier.padding(8.dp), enabled = false) { Text(text = "enabled = false") }
        OutlinedButton(onClick = {}, modifier = Modifier.padding(8.dp)) { Text(text = "OutlinedButton") }

        Button(onClick = {}, modifier = Modifier.padding(8.dp), elevation = ButtonDefaults.elevation()) {
            Text(text = "elevation")
        }
        Button(onClick = {}, modifier = Modifier.padding(8.dp), shape = RoundedCornerShape(12.dp)) {
            Text(text = "shape = RoundedCornerShape(12.dp)")
        }

        Button(onClick = {}, modifier = Modifier.padding(8.dp)) {
            Row {
                Text(text = "Icon")
                Icon(Icons.Default.FavoriteBorder, contentDescription = null, modifier = Modifier.padding(end = 4.dp))
            }
        }
        Button(onClick = {}, modifier = Modifier.padding(8.dp)) {
            Text(text = "Icon")
            Icon(Icons.Default.FavoriteBorder, contentDescription = null, modifier = Modifier.padding(start = 4.dp))
        }

        OutlinedButton(
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = purple200,
            ),
            onClick = {}, modifier = Modifier.padding(8.dp),
        ) {
            Text(text = "OutlinedButton colors")
        }
        Button(
            colors = ButtonDefaults.buttonColors(
                backgroundColor = purple,
                contentColor = MaterialTheme.colors.surface
            ), onClick = {}, modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "Button colors")
        }

        val horizontalGradient = Brush.horizontalGradient(
            colors = listOf(MaterialTheme.colors.primary, MaterialTheme.colors.primaryVariant)
        )
        val verticalGradient = Brush.verticalGradient(
            colors = listOf(Color.Blue, Color.Green),
            startY = 0f,
            endY = 30f
        )
        Text(
            text = "Horizontal gradient",
            style = typography.body2.copy(color = Color.White),
            modifier = Modifier.padding(12.dp).clickable(onClick = {})
                .clip(RoundedCornerShape(4.dp))
                .background(brush = horizontalGradient).padding(12.dp)
        )
        Text(
            text = "Vertical gradient",
            style = typography.body1.copy(color = Color.White),
            modifier = Modifier.padding(12.dp).clickable(onClick = {})
                .clip(RoundedCornerShape(4.dp))
                .background(brush = verticalGradient).padding(12.dp)
        )

        Button(onClick = {}, modifier = Modifier.padding(8.dp).clip(CircleShape)) {
            Text(text = "clip(CircleShape) button")
        }

        Surface(
            color = MaterialTheme.colors.primary,
            contentColor = MaterialTheme.colors.onPrimary,
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(
                width = 1.dp,
                color = MaterialTheme.colors.primary
            ),
            modifier = Modifier.clickable { }
        ) {
            Row(modifier = Modifier) {
                Image(
                    imageResource(Res.drawable.icon),
                    contentDescription = null,
                    modifier = Modifier.padding(8.dp).requiredSize(20.dp).clip(CircleShape)
                )
                Text(
                    text = "Surface",
                    style = typography.body2,
                    modifier = Modifier.padding(end = 8.dp, top = 8.dp, bottom = 8.dp)
                )
            }
        }
    }
}
