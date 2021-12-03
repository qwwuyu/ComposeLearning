package com.qwwuyu.compose.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import com.qwwuyu.base.platform.isSystemInDarkTheme
import com.qwwuyu.compose.theme.ColorPallet.*
import kotlin.jvm.JvmInline

// dark palettes
private val DarkGreenColorPalette = darkColors(
    primary = green200,
    primaryVariant = green700,
    secondary = teal200,
    background = Color.Black,
    surface = Color.Black,
    onPrimary = Color.Black,
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White,
    error = Color.Red,
)

private val DarkPurpleColorPalette = darkColors(
    primary = purple200,
    primaryVariant = purple700,
    secondary = teal200,
    background = Color.Black,
    surface = Color.Black,
    onPrimary = Color.Black,
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White,
    error = Color.Red,
)

private val DarkBlueColorPalette = darkColors(
    primary = blue200,
    primaryVariant = blue700,
    secondary = teal200,
    background = Color.Black,
    surface = Color.Black,
    onPrimary = Color.Black,
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White,
    error = Color.Red,
)

private val DarkOrangeColorPalette = darkColors(
    primary = orange200,
    primaryVariant = orange700,
    secondary = teal200,
    background = Color.Black,
    surface = Color.Black,
    onPrimary = Color.Black,
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White,
    error = Color.Red,
)

// Light pallets
private val LightGreenColorPalette = lightColors(
    primary = green500,
    primaryVariant = green700,
    secondary = teal200,
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black
)

private val LightPurpleColorPalette = lightColors(
    primary = purple,
    primaryVariant = purple700,
    secondary = teal200,
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black
)

private val LightBlueColorPalette = lightColors(
    primary = blue500,
    primaryVariant = blue700,
    secondary = teal200,
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black
)

private val LightOrangeColorPalette = lightColors(
    primary = orange500,
    primaryVariant = orange700,
    secondary = teal200,
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black
)

enum class ColorPallet {
    PURPLE, GREEN, ORANGE, BLUE
}

@Composable
fun AppTheme(content: @Composable () -> Unit) {
    val systemInDarkTheme = isSystemInDarkTheme()
    val appThemeState = remember { mutableStateOf(AppThemeState(systemInDarkTheme, BLUE)) }

    val colors = when (appThemeState.value.colorPallet) {
        GREEN -> if (appThemeState.value.darkTheme) DarkGreenColorPalette else LightGreenColorPalette
        PURPLE -> if (appThemeState.value.darkTheme) DarkPurpleColorPalette else LightPurpleColorPalette
        ORANGE -> if (appThemeState.value.darkTheme) DarkOrangeColorPalette else LightOrangeColorPalette
        BLUE -> if (appThemeState.value.darkTheme) DarkBlueColorPalette else LightBlueColorPalette
    }
    val wColors = if (appThemeState.value.darkTheme) DarkWColor else LightWColor

    WColorLocalProvider(wColors) {
        CompositionLocalProvider(localTheme provides { darkTheme, colorPallet ->
            if (darkTheme != null) appThemeState.value = appThemeState.value.copy(darkTheme = darkTheme)
            if (colorPallet != null) appThemeState.value = appThemeState.value.copy(colorPallet = colorPallet)
        }) {
            MaterialTheme(
                colors = colors,
                typography = typography,
                shapes = shapes,
                content = content
            )
        }
    }
}

/* ======================== theme change ======================== */
data class AppThemeState(
    var darkTheme: Boolean = false,
    var colorPallet: ColorPallet = BLUE
)

@Composable
fun localTheme(): ((Boolean?, ColorPallet?) -> Unit) = localTheme.current

private val localTheme = ThemeProvidableCompositionLocal()

@JvmInline
private value class ThemeProvidableCompositionLocal(
    private val delegate: ProvidableCompositionLocal<((Boolean?, ColorPallet?) -> Unit)?> =
        staticCompositionLocalOf { null }
) {
    val current: ((Boolean?, ColorPallet?) -> Unit) @Composable get() = delegate.current!!
    infix fun provides(value: ((Boolean?, ColorPallet?) -> Unit)) = delegate provides value
}

/* ========================  custom color ======================== */
private val DarkWColor = WColors(
    color1 = Color.Blue,
    color2 = Color.Red,
)

private val LightWColor = WColors(
    color1 = Color.Green,
    color2 = Color.Yellow,
)

@Stable
class WColors(
    color1: Color,
    color2: Color
) {
    var color1 by mutableStateOf(color1)
        internal set
    var color2 by mutableStateOf(color2)
        internal set

    fun copy() = WColors(color1, color2)
}

fun WColors.updateColorsFrom(other: WColors) {
    color1 = other.color1
    color2 = other.color2
}

@Composable
fun WColorLocalProvider(colors: WColors, content: @Composable () -> Unit) {
    val rememberedColors = remember { colors.copy() }.apply { updateColorsFrom(colors) }
    CompositionLocalProvider(LocalWColors provides rememberedColors, content = content)
}

val MaterialTheme.wColors: WColors
    @Composable
    @ReadOnlyComposable
    get() = LocalWColors.current


private val LocalWColors = WColorProvidableCompositionLocal()

@JvmInline
private value class WColorProvidableCompositionLocal(
    private val delegate: ProvidableCompositionLocal<WColors?> =
        staticCompositionLocalOf { null }
) {
    val current: WColors @Composable @ReadOnlyComposable get() = delegate.current!!
    infix fun provides(value: WColors) = delegate provides value
}