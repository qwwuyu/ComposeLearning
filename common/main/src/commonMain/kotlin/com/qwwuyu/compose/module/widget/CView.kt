package com.qwwuyu.compose.module.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LibraryMusic
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.qwwuyu.compose.platform.Res
import com.qwwuyu.base.platform.imageResource

class CView {
    companion object {
        @Composable
        fun main() {
            CViewMain()
        }
    }
}

@Composable
fun CViewMain() {
    Column(modifier = Modifier.verticalScroll(state = rememberScrollState())) {
        Title("Spacer")
        Spacer(modifier = Modifier.padding(8.dp))

        Title("Divider")
        Divider()

        Title("Snackbar")
        Snackbar(
            modifier = Modifier.padding(4.dp),
            actionOnNewLine = false,
            action = { TextButton(onClick = {}) { Text(text = "Remove") } }
        ) {
            Text(text = "Snackbar with action item below text")
        }

        Title("TopAppBar")
        TopAppBar(
            title = { Text(text = "Instagram") },
            /*title = {
                Icon(imageResource(Res.drawable.icon), contentDescription = "icon", modifier = Modifier.fillMaxWidth())
            },*/
            backgroundColor = MaterialTheme.colors.surface,
            contentColor = MaterialTheme.colors.onSurface,
            elevation = 8.dp,
            navigationIcon = {
                Image(
                    imageResource(Res.drawable.icon),
                    contentDescription = "",
                    modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp).requiredSize(32.dp)
                        .clip(CircleShape)
                )
            },
            actions = {
                IconButton(onClick = {}) { Icon(Icons.Default.StarBorder, contentDescription = "icon") }
            }
        )

        Title("BottomAppBar")
        BottomAppBar(cutoutShape = CircleShape) {
            IconButton(onClick = {}) { Icon(Icons.Default.MoreHoriz, contentDescription = "") }
            Text("123")
        }

        Title("BottomNavigation")
        val navItemState = remember { mutableStateOf(0) }
        BottomNavigation(backgroundColor = MaterialTheme.colors.surface) {
            BottomNavigationItem(
                icon = { Icon(Icons.Outlined.Home, contentDescription = "Home") },
                selected = navItemState.value == 0,
                onClick = { navItemState.value = 0 },
                label = { Text(text = "Home") },
            )
            BottomNavigationItem(
                icon = { Icon(Icons.Outlined.Search, contentDescription = "Search") },
                selected = navItemState.value == 1,
                onClick = { navItemState.value = 1 },
                label = { Text(text = "Search") }
            )
            BottomNavigationItem(
                icon = { Icon(Icons.Outlined.LibraryMusic, contentDescription = "LibraryMusic") },
                selected = navItemState.value == 2,
                onClick = { navItemState.value = 2 },
                label = { Text(text = "LibraryMusic") }
            )
        }

        Title("Card")
        Card(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            elevation = 4.dp
        ) {
            Row {
                Image(
                    imageResource(Res.drawable.icon),
                    contentDescription = null,
                    modifier = Modifier.requiredSize(60.dp)
                )
                Text(text = "text", modifier = Modifier.padding(16.dp))
            }
        }

        Title("ListItem")
        @OptIn(ExperimentalMaterialApi::class)
        ListItem(text = { Text("title") }, secondaryText = { Text("subtitle") })

        @OptIn(ExperimentalMaterialApi::class)
        ListItem(
            text = { Text("title") }, secondaryText = { Text("subtitle") },
            icon = { Image(imageResource(Res.drawable.icon), contentDescription = null) },
            trailing = { Icon(Icons.Default.ShoppingCart, contentDescription = null) },
            overlineText = { Text("Overline text") },
            singleLineSecondaryText = false
        )
    }
}


@Composable
private fun Title(text: String) {
    Spacer(modifier = Modifier.padding(8.dp))
    Text(text, Modifier.padding(8.dp), color = Color.Gray, fontSize = 12.sp)
}
