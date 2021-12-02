plugins {
    kotlin("jvm")
}

kotlin {
    sourceSets {
        dependencies {
            compileOnly(project(":tool:libk"))
            compileOnly("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")
            implementation(project(":common:comm"))
            api(Deps.Network.gson)
            api(Deps.Network.OkHttp.core)
            api(Deps.Network.OkHttp.urlconnection)
            api(Deps.Network.OkHttp.logging)
            api(Deps.Network.Retrofit.core)
            api(Deps.Network.Retrofit.converterGson)
        }
    }
}
