plugins {
    id("com.android.library")
    id("kotlin-android")
    kotlin("android")
}

android {
    compileSdk = AppConfig.compileSdk
    buildToolsVersion = "30.0.3"

    buildFeatures {
        viewBinding = true
    }

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
    api(fileTree(mapOf("include" to listOf("*.jar"), "dir" to "libs")))

    api("androidx.core:core-ktx:1.3.2")
    api("androidx.appcompat:appcompat:1.2.0")
    api("com.google.android.material:material:1.3.0")
    api("androidx.constraintlayout:constraintlayout:2.0.4")
    api("androidx.test.ext:junit:1.1.2")
    api("androidx.test.espresso:espresso-core:3.3.0")
    api("org.greenrobot:eventbus:3.3.1")
    api("androidx.recyclerview:recyclerview:1.2.0")
    api("com.squareup.retrofit2:retrofit:2.9.0")
    api("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.github.getActivity:XXPermissions:13.5")

    // ARouter
    api("com.alibaba:arouter-api:1.5.2")

    api(project(":lib_voice"))
    api(project(":lib_network"))
}