import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    id("multiplatform-compose-setup")
    id("android-setup")
    id("kotlin-parcelize")
}

kotlin {
    sourceSets {
        named("commonMain") {
            dependencies {
                api(project(":common:database"))
                api(project(":common:fun:home"))
                api(project(":common:fun:widget"))
                api(project(":common:fun:tkv"))
                api(Deps.ArkIvanov.Decompose.decompose)
                api(Deps.ArkIvanov.MVIKotlin.mvikotlin)
                api(Deps.ArkIvanov.MVIKotlin.extensionsReaktive)
                api(Deps.Badoo.Reaktive.reaktive)
            }
        }
    }
}
