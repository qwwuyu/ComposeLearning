plugins {
    id("multiplatform-compose-setup")
    id("android-setup")
}

kotlin {
    sourceSets {
        named("commonMain") {
            dependencies {
                api(project(":common:base"))
                api(project(":common:database"))
                api(project(":common:router"))
                implementation(Deps.ArkIvanov.Decompose.extensionsCompose)
            }
        }
    }
}