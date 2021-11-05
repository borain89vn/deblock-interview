plugins {
    id("android-library-base")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

dependencies {
    implementation(project(":core:datastore"))
    implementation(project(":core:utils"))


    implementation(Libs.ANDROID_CORE)

    implementation(Libs.COROUTINES_CORE)
    implementation(Libs.COROUTINES_ANDROID)

    implementation(Libs.RETROFIT)
    implementation(Libs.RETROFIT_GSON_CONVERTER)
    implementation(Libs.GSON)
    implementation(Libs.LOGGING_INTERCEPTOR)

    implementation ("androidx.paging:paging-runtime-ktx:3.0.0-alpha13")

    implementation(Libs.DAGGER_HILT)
    kapt(Libs.DAGGER_HILT_COMPILER)
}