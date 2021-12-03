plugins {
    id("multiplatform-compose-setup")
    id("android-setup")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":tool:libc"))
            }
        }

        androidMain {
            dependencies {
                api(Deps.coil)
            }
        }

        desktopMain {
            dependencies {
                implementation(Deps.Network.OkHttp.core)
            }
        }
    }
}