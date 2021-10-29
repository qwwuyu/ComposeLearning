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
                implementation(project(":common:main"))
            }
        }
    }
}

compose.desktop {
    application {
        mainClass = "com.qwwuyu.server.MainKt"

        nativeDistributions {
            modules("java.sql")

            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.qwwuyu.server"
            packageVersion = "1.0.0"
            vendor = "qwwuyu"
            description = "description"
            copyright = "copyright"

            windows {
                menu = true
                shortcut = true
                upgradeUuid = "334DE3FC-5D76-4A62-9E93-84E7ECC8B2F8"
                installationPath = "D:/qwwuyu"
            }
        }
    }
}
