object KotlinConstants {
    // Gradle 版本
    const val gradle_version = "7.0.1"
    // Kotlin 版本
    const val kotlin_version = "1.6.10"
}

object AppConfig {
    const val compileSdk = 30
    const val applicationId = "com.example.kotlintest"
    const val minSdk = 22
    const val targetSdk = 30
    const val versionCode = 1
    const val versionName = "1.0"
}

object ModuleConfig {
    var isApp = false

    const val MODULE_APP_MANAGER = "com.example.module_app_manager"
    const val MODULE_CONSTELLATION = "com.example.module_constellation"
    const val MODULE_DEVELOPR = "com.example.module_developr"
    const val MODULE_JOKE = "com.example.module_joke"
    const val MODULE_MAP = "com.example.module_map"
    const val MODULE_SETTING = "com.example.module_setting"
    const val MODULE_VOICE_SETTING = "com.example.module_voice_setting"
    const val MODULE_WEATHER = "com.example.module_weather"
}