plugins {
    id("multiplatform-compose-setup")
    id("android-setup")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(project(":tool:libk"))
                api(Deps.ArkIvanov.MVIKotlin.rx)
                api(Deps.ArkIvanov.MVIKotlin.mvikotlin)
                api(Deps.ArkIvanov.Decompose.decompose)
                api(Deps.ArkIvanov.MVIKotlin.extensionsReaktive)
                api(Deps.Badoo.Reaktive.reaktive)
            }
        }
    }
}