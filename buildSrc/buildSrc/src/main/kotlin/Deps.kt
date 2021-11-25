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

    const val okhttp3 = "3.14.9"
    const val retrofit = "2.9.0"
}

fun kotlinx(id: String, version: String) = "org.jetbrains.kotlinx:kotlinx-$id:$version"

object Libs {
    const val junit = "junit:junit:4.13.1"
}

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
            const val appCompat = "androidx.appcompat:appcompat:1.3.1"
        }

        object Activity {
            const val activityCompose = "androidx.activity:activity-compose:1.3.0"
        }
    }

    object ArkIvanov {
        object MVIKotlin {
            private const val VERSION = "3.0.0-alpha01"
            const val rx = "com.arkivanov.mvikotlin:rx:$VERSION"
            const val mvikotlin = "com.arkivanov.mvikotlin:mvikotlin:$VERSION"
            const val mvikotlinMain = "com.arkivanov.mvikotlin:mvikotlin-main:$VERSION"
            const val mvikotlinLogging = "com.arkivanov.mvikotlin:mvikotlin-logging:$VERSION"
            const val mvikotlinTimeTravel = "com.arkivanov.mvikotlin:mvikotlin-timetravel:$VERSION"
            const val mvikotlinExtensionsReaktive = "com.arkivanov.mvikotlin:mvikotlin-extensions-reaktive:$VERSION"
        }

        object Decompose {
            private const val VERSION = "0.3.1"
            const val decompose = "com.arkivanov.decompose:decompose:$VERSION"
            const val extensionsCompose = "com.arkivanov.decompose:extensions-compose-jetbrains:$VERSION"
        }
    }

    object Badoo {
        object Reaktive {
            private const val VERSION = "1.1.22"
            const val reaktive = "com.badoo.reaktive:reaktive:$VERSION"
            const val reaktiveTesting = "com.badoo.reaktive:reaktive-testing:$VERSION"
            const val utils = "com.badoo.reaktive:utils:$VERSION"
            const val coroutinesInterop = "com.badoo.reaktive:coroutines-interop:$VERSION"
        }
    }

    object Squareup {
        object SQLDelight {
            private const val VERSION = "1.5.0"

            const val gradlePlugin = "com.squareup.sqldelight:gradle-plugin:$VERSION"
            const val androidDriver = "com.squareup.sqldelight:android-driver:$VERSION"
            const val sqliteDriver = "com.squareup.sqldelight:sqlite-driver:$VERSION"
            const val nativeDriver = "com.squareup.sqldelight:native-driver:$VERSION"
        }
    }
}
