plugins {
    id("com.android.application")
    id("kotlin-android")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = AppConfig.compileSdk

    buildFeatures {
        viewBinding = true
    }

    defaultConfig {
        applicationId = AppConfig.applicationId
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // ARouter
        kapt {
            arguments {
                arg("AROUTER_MODULE_NAME", project.name)
            }
        }
    }

    // 签名配置
    signingConfigs {
        register("release") {
            // 别名
            keyAlias = "TongJi"
            // 别名密码
            keyPassword = "123456"
            // 路径
            storeFile = file("/src/main/jks/aivoice.jks")
            // 密码
            storePassword = "123456"
        }
    }

    buildTypes {
        getByName("debug") {

        }

        getByName("release") {
            isMinifyEnabled = false
            // 自动签名打包
            signingConfig = signingConfigs.getByName("release")
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    // 输出类型
    android.applicationVariants.all {
        // 编译类型、
        val buildType = this.buildType.name
        outputs.all {
            // 输出APK
            if (this is com.android.build.gradle.internal.api.ApkVariantOutputImpl) {
                this.outputFileName = "AI_V${defaultConfig.versionName}_$buildType.apk"
            }
        }
    }

    // 依赖操作
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(project(":lib_base"))
    implementation("com.github.getActivity:XXPermissions:13.5")
    implementation("com.ToxicBakery.viewpager.transforms:view-pager-transforms:2.0.24")

    if (!ModuleConfig.isApp) {
        implementation(project(":module_app_manager"))
        implementation(project(":module_weather"))
        implementation(project(":module_setting"))
        implementation(project(":module_joke"))
        implementation(project(":module_map"))
        implementation(project(":module_voice_setting"))
        implementation(project(":module_developr"))
        implementation(project(":module_constellation"))
    }

    // 运行时注解
    kapt("com.alibaba:arouter-compiler:1.5.2")
}