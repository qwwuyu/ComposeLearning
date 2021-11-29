@file:Suppress("ObjectPropertyName", "ObjectPropertyName", "unused")

import java.io.File
import java.io.FileInputStream
import java.util.*

private val properties: Properties = Properties().apply {
    val propertyFile = File("gradle.properties")
    if (propertyFile.exists()) {
        FileInputStream(propertyFile).use {
            load(it)
        }
    }
}

private fun getPropertyByName(key: String, defValue: String?): String? {
    var value = properties.getProperty(key)
    if (value == null) value = System.getenv(key)
    return value ?: defValue
}

object Apps {
    private const val major = 1
    private const val minor = 0
    private const val revision = 0

    const val version = "${major}.${minor}.${revision}"
    const val code = major * 10000 + minor * 100 + revision

    const val group = "com.qwwuyu.server"
    const val applicationId = group
    const val compileSdkVersion = 31
    const val minSdkVersion = 23
    const val targetSdkVersion = 30
}

object Versions {
    const val kotlin = "1.5.31"
    const val compose = "1.0.0-beta5"
    const val androidTools = "4.2.0"

    const val appcompat = "1.3.1"
    const val activityCompose = "1.3.0"

    const val MVIKotlin = "3.0.0-alpha01"
    const val decompose = "0.4.0"
    const val reaktive = "1.1.22"
    const val SQLDelight = "1.5.0"

    const val okhttp3 = "3.14.9"
    const val retrofit = "2.9.0"
}

fun kotlinx(id: String, version: String) = "org.jetbrains.kotlinx:kotlinx-$id:$version"

object Deps {
    object JetBrains {
        object Kotlin {
            const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
        }

        object Compose {
            const val gradlePlugin = "org.jetbrains.compose:compose-gradle-plugin:${Versions.compose}"
        }
    }

    object Android {
        object Tools {
            const val gradlePlugin = "com.android.tools.build:gradle:${Versions.androidTools}"
        }
    }

    object AndroidX {
        object AppCompat {
            const val appCompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
        }

        object Activity {
            const val activityCompose = "androidx.activity:activity-compose:${Versions.activityCompose}"
        }
    }

    object ArkIvanov {
        object MVIKotlin {
            const val rx = "com.arkivanov.mvikotlin:rx:${Versions.MVIKotlin}"
            const val mvikotlin = "com.arkivanov.mvikotlin:mvikotlin:${Versions.MVIKotlin}"
            const val mvikotlinMain = "com.arkivanov.mvikotlin:mvikotlin-main:${Versions.MVIKotlin}"
            const val logging = "com.arkivanov.mvikotlin:mvikotlin-logging:${Versions.MVIKotlin}"
            const val timeTravel = "com.arkivanov.mvikotlin:mvikotlin-timetravel:${Versions.MVIKotlin}"
            const val extensionsReaktive = "com.arkivanov.mvikotlin:mvikotlin-extensions-reaktive:${Versions.MVIKotlin}"
        }

        object Decompose {
            const val decompose = "com.arkivanov.decompose:decompose:${Versions.decompose}"
            const val extensionsCompose = "com.arkivanov.decompose:extensions-compose-jetbrains:${Versions.decompose}"
        }
    }

    object Badoo {
        object Reaktive {
            const val reaktive = "com.badoo.reaktive:reaktive:${Versions.reaktive}"
            const val reaktiveTesting = "com.badoo.reaktive:reaktive-testing:${Versions.reaktive}"
            const val utils = "com.badoo.reaktive:utils:${Versions.reaktive}"
            const val coroutinesInterop = "com.badoo.reaktive:coroutines-interop:${Versions.reaktive}"
        }
    }

    object Squareup {
        object SQLDelight {
            const val gradlePlugin = "com.squareup.sqldelight:gradle-plugin:${Versions.SQLDelight}"
            const val androidDriver = "com.squareup.sqldelight:android-driver:${Versions.SQLDelight}"
            const val sqliteDriver = "com.squareup.sqldelight:sqlite-driver:${Versions.SQLDelight}"
            const val nativeDriver = "com.squareup.sqldelight:native-driver:${Versions.SQLDelight}"
        }
    }
}
