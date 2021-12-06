package com.qwwuyu.compose.module.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.qwwuyu.base.utils.WLog

class CText {
    companion object {
        @Composable
        fun main() {
            CTextMain()
        }
    }
}

@Composable
fun CTextMain() {
    Column(modifier = Modifier.verticalScroll(state = rememberScrollState())) {
        val m = Modifier.padding(horizontal = 8.dp)
        Text(modifier = m, text = "fontSize", fontSize = 10.sp)
        Text(modifier = m, text = "style = typography.body1.copy(fontWeight = FontWeight.Plain)")
        Text(modifier = m, text = "FontWeight.Medium", style = typography.body1.copy(fontWeight = FontWeight.Medium))
        Text(modifier = m, text = "FontWeight.Bold", style = typography.body1.copy(fontWeight = FontWeight.Bold))
        Text(modifier = m, text = "TextDecoration.Underline", textDecoration = TextDecoration.Underline)
        Text(modifier = m, text = "TextDecoration.LineThrough", textDecoration = TextDecoration.LineThrough)
        Text(
            modifier = m, text = "TextDecoration.combine",
            textDecoration = TextDecoration.combine(listOf(TextDecoration.Underline, TextDecoration.LineThrough))
        )
        Text(
            modifier = m, overflow = TextOverflow.Ellipsis, maxLines = 1,
            text = "TextOverflow.Ellipsis : This text is supposed to ellipsis with max 1 line allowed for this"
        )
        Text(
            modifier = m, overflow = TextOverflow.Clip, maxLines = 1,
            text = "TextOverflow.Clip: This text is supposed to clip with max 1 line allowed for this",
        )


        val annotatedString = buildAnnotatedString {
            pushStringAnnotation(
                tag = "URL",
                annotation = "pushStringAnnotation",
            )
            withStyle(
                style = SpanStyle(fontSize = 20.sp)
            ) {
                append("buildAnnotatedString")
            }
            withStyle(
                style = SpanStyle(fontWeight = FontWeight.Bold)
            ) {
                append("withStyle")
            }
            pop()
        }
        ClickableText(annotatedString) { offset ->
            annotatedString.getStringAnnotations(tag = "URL", start = offset, end = offset).firstOrNull()
                ?.let { annotation ->
                    WLog.i("click=${annotation}")
                }
        }
    }
}
