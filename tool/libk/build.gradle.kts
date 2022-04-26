plugins {
    kotlin("jvm")
}

kotlin {
    sourceSets {
        dependencies {
            api(Deps.Network.gson)
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:1.5.1")
        }
    }
}
