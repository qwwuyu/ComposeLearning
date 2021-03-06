import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

kotlin {
    jvm {
        withJava()
    }

    sourceSets {
        named("jvmMain") {
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation(project(":tool:libc"))
                implementation(project(":common:main"))

                implementation(Deps.ArkIvanov.Decompose.decompose)
                implementation(Deps.ArkIvanov.Decompose.extensionsCompose)
                implementation(Deps.ArkIvanov.MVIKotlin.mvikotlin)
                implementation(Deps.ArkIvanov.MVIKotlin.mvikotlinMain)
                implementation(Deps.Badoo.Reaktive.reaktive)
                implementation(Deps.Badoo.Reaktive.coroutinesInterop)
            }
        }
    }
}

compose.desktop {
    application {
        mainClass = "com.qwwuyu.compose.MainKt"

        nativeDistributions {
            modules(*jdkModules)

            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb, TargetFormat.Exe)
            packageName = "qwwuyu"
            packageVersion = "1.0.0"
            vendor = "qwwuyu"
            description = "qwwuyu compose"
            copyright = "qwwuyu copyright"

            windows {
//                menu = true
                shortcut = true
                // see https://wixtoolset.org/documentation/manual/v3/howtos/general/generate_guids.html
                upgradeUuid = "334DE3FC-5D76-4A62-9E93-84E7ECC8B2F8"
                installationPath = "qwwuyu"
                iconFile.set(project.file("icon.ico"))
            }
        }
    }
}
