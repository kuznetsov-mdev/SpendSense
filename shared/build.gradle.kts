plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.compose)
    alias(libs.plugins.android.library)
}

kotlin {
    jvm()
    androidTarget()

    sourceSets {
        //only common for all platform libs
        commonMain {
            dependencies {
                implementation(compose.ui)
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
            }
        }
    }
}

android {
    namespace = findProperty("app.namespace").toString()
    compileSdk = findProperty("android.compileSdk").toString().toInt()

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}
