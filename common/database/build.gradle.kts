plugins {
    id("multiplatform-compose-setup")
    id("android-setup")
    id("com.squareup.sqldelight")
}

sqldelight {
    database("BaseDatabase") {
        packageName = "com.qwwuyu.database"
    }
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(project(":tool:libc"))
                api(Deps.Badoo.Reaktive.reaktive)
            }
        }

        androidMain {
            dependencies {
                api(project(":tool:libc"))
                api(Deps.Squareup.SQLDelight.androidDriver)
                api(Deps.Squareup.SQLDelight.sqliteDriver)
            }
        }

        desktopMain {
            dependencies {
                api(project(":tool:libc"))
                api(Deps.Squareup.SQLDelight.sqliteDriver)
            }
        }
    }
}