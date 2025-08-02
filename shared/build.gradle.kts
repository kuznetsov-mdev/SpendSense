plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.compose)
    alias(libs.plugins.android.library)
    alias(libs.plugins.cocoapods)
    alias(libs.plugins.libres)
    alias(libs.plugins.sqldelight)
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    jvm()
    androidTarget()

    listOf(
        iosArm64(),
        iosX64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "shared"
        }
    }

    cocoapods {
        summary = "SpendSense"
        homepage = "https://example.com/shared"
        version = "1.0"
        ios.deploymentTarget = "11.0"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
        }
    }

    sourceSets {
        val commonMain by getting
        val androidMain by getting

        //only common for all platform libs
        commonMain {
            dependencies {
                //Compose
                implementation(compose.ui)
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.material3)
                implementation(compose.materialIconsExtended)

                //Resources
                api(libs.libres.compose)

                //Settings
                implementation(libs.multiplatform.settings)

                //DI
                api(libs.koin.core)

                //Datetime
                implementation(libs.datetime)

                //Sqldelight
                implementation(libs.sqldelight.coroutines.extensions)

                //Network
                implementation(libs.ktor.client.core)
                implementation(libs.ktor.client.cio)
                implementation(libs.ktor.client.logging)
                implementation(libs.ktor.client.negotiation)
                implementation(libs.ktor.serialization.kotlinx.json)
                implementation(libs.kotlinx.serialization.core)

                //Logs
                api(libs.napier)
            }
        }

        androidMain {
            dependsOn(commonMain)

            dependencies {
                implementation(libs.sqldelight.android.driver)
                implementation(libs.ktor.client.android)
            }
        }

        jvmMain {
            dependsOn(commonMain)
            dependencies {
                api(compose.desktop.currentOs)
                implementation(libs.sqldelight.desktop.driver)
            }
        }

        val iosArm64Main by getting
        val iosX64Main by getting
        val iosSimulatorArm64Main by getting
        iosMain {
            dependsOn(commonMain)
            iosArm64Main.dependsOn(this)
            iosX64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)

            dependencies {
                implementation(libs.sqldelight.native.driver)
                implementation(libs.ktor.client.ios)
            }
        }
    }
}

android {
    namespace = findProperty("app.namespace").toString()
    compileSdk = findProperty("android.compileSdk").toString().toInt()

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
}

libres {
    generatedClassName = "MR" // "Res" by default
}

sqldelight {
    databases {
        create("AppDb") {
            packageName.set("ru.sbx.spend_sense.db")
            schemaOutputDirectory.set(file("src/commonMain/sqldelight/db"))
        }
    }
}
