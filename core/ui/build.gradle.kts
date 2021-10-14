plugins {
    id("android-library-base")
    id("androidx.navigation.safeargs.kotlin")
    id("dagger.hilt.android.plugin")
}

dependencies {
    implementation(Libs.ANDROID_CORE)
    implementation(Libs.ANDROID_APPCOMPAT)
    implementation(Libs.ANDROID_MATERIAL)
    implementation(Libs.GLIDE)
    implementation(Libs.GLIDE_COMPILER)
    implementation(Libs.ANDROID_FRAGMENT)
    implementation(Libs.LIFECYCLE_VIEWMODEL)


    implementation(Libs.DAGGER_HILT)
    kapt(Libs.DAGGER_HILT_COMPILER)
}