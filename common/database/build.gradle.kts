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
                api(project(":common:base"))
                api(Deps.Badoo.Reaktive.reaktive)
            }
        }

        androidMain {
            dependencies {
                api(project(":common:base"))
                api(Deps.Squareup.SQLDelight.androidDriver)
                api(Deps.Squareup.SQLDelight.sqliteDriver)
            }
        }

        desktopMain {
            dependencies {
                api(project(":common:base"))
                api(Deps.Squareup.SQLDelight.sqliteDriver)
            }
        }
    }
}