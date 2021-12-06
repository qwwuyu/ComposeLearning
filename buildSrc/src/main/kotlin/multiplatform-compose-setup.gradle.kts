import org.jetbrains.compose.compose

plugins {
    id("com.android.library")
    id("kotlin-multiplatform")
    id("org.jetbrains.compose")
}

kotlin {
    jvm("desktop")
    android()

    sourceSets {
        named("commonMain") {
            dependencies {
                api(compose.runtime)
                api(compose.foundation)
                api(compose.material)
                api(compose.materialIconsExtended)
            }
        }

        named("androidMain") {
            dependencies {
                api(Deps.AndroidX.AppCompat.appCompat)
                api("androidx.core:core-ktx:1.3.1")
            }
        }

        named("desktopMain") {
            dependencies {
                api(compose.desktop.common)
                api(compose.desktop.currentOs)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                api(compose.desktop.components.splitPane)
            }
        }
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }
}
