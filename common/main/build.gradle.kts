plugins {
    id("multiplatform-compose-setup")
    id("android-setup")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(project(":tool:libc"))
                api(project(":tool:imageloader"))
                api(project(":common:comm"))
                api(project(":common:database"))
                api(project(":common:router"))
                implementation(Deps.ArkIvanov.Decompose.extensionsCompose)
            }
        }

        androidMain {
            dependencies {
                api(project(":common:network"))
            }
        }

        desktopMain {
            dependencies {
                api(project(":common:network"))
            }
        }
    }
}