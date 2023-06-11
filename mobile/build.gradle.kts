@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.hiltAndroidPlugin) apply false
    alias(libs.plugins.kotlinKapt) apply false
    alias(libs.plugins.sqlDelight) apply false
}
true