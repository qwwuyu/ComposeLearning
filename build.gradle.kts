allprojects {
    repositories {
        google()
        mavenCentral()
        mavenLocal()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
    afterEvaluate {
        configureEncoding()
    }

    tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class.java).all {
        kotlinOptions {
            jvmTarget = "1.8"
            freeCompilerArgs = freeCompilerArgs + "-Xjvm-default=enable"
        }
    }
}

fun Project.configureEncoding() {
    tasks.withType<JavaCompile>() {
        options.encoding = "UTF8"
    }
}

