plugins {
    id("com.android.library")
    id("kotlin-android")
    kotlin("android")
}

android {
    compileSdk = AppConfig.compileSdk
    buildToolsVersion = "30.0.3"

    defaultConfig {
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    api(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    // 百度TTS库
    api(files("libs/bdtts.jar"))
    // 百度AI库
    api(files("libs/bdasr.jar"))
}