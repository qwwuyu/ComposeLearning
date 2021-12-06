plugins {
    kotlin("jvm")
}

kotlin {
    sourceSets {
        dependencies {
            api(Deps.Network.gson)
        }
    }
}
