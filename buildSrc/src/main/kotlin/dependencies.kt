

object Versions {
    const val KOTLIN = "1.5.10"
    const val COROUTINES = "1.5.0"
    const val RETROFIT = "2.9.0"
    const val GSON = "2.8.6"
    const val ROOM = "2.2.6"
    const val DAGGER_HILT = "2.38.1"
    const val LIFECYCLE = "2.4.0-alpha02"
    const val DATASTORE = "1.0.0-alpha08"
    const val NAVIGATION = "2.4.0-alpha04"
    const val GLIDE = "4.9.0"
    const val CIRCLE_IMAGEVIEW = "3.0.0"
}

object Libs {
    const val ANDROID_GRADLE_PLUGIN = "com.android.tools.build:gradle:7.1.0-alpha03"
    const val KOTLIN_GRADLE_PLUGIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN}"
    const val HILT_GRADLE_PLUGIN = "com.google.dagger:hilt-android-gradle-plugin:${Versions.DAGGER_HILT}"
    const val SAFE_ARGS_PLUGIN = "androidx.navigation:navigation-safe-args-gradle-plugin:2.4.0-alpha04"
    const val GOOGLE_SERVICES = "com.google.gms:google-services:4.3.5"

    const val ANDROID_CORE = "androidx.core:core-ktx:1.3.2"
    const val ANDROID_APPCOMPAT = "androidx.appcompat:appcompat:1.3.0-rc01"
    const val ANDROID_MATERIAL = "com.google.android.material:material:1.3.0"
    const val ANDROID_FRAGMENT = "androidx.fragment:fragment-ktx:1.3.3"


    const val COROUTINES_CORE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.COROUTINES}"
    const val COROUTINES_ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.COROUTINES}"

    const val RETROFIT = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT}"
    const val RETROFIT_GSON_CONVERTER = "com.squareup.retrofit2:converter-gson:${Versions.RETROFIT}"
    const val GSON = "com.google.code.gson:gson:${Versions.GSON}"
    const val LOGGING_INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor:4.9.1"

    const val LIFECYCLE_VIEWMODEL = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.LIFECYCLE}"
    const val LIFECYCLE_RUNTIME = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.LIFECYCLE}"
    const val LIFECYCLE_LIVEDATA = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.LIFECYCLE}"
    const val LIFECYCLE_COMPILER = "androidx.lifecycle:lifecycle-compiler:${Versions.LIFECYCLE}"

    const val ROOM_RUNTIME = "androidx.room:room-runtime:${Versions.ROOM}"
    const val ROOM_KTX = "androidx.room:room-ktx:${Versions.ROOM}"
    const val ROOM_COMPILER = "androidx.room:room-compiler:${Versions.ROOM}"

    const val DAGGER_HILT = "com.google.dagger:hilt-android:${Versions.DAGGER_HILT}"
    const val DAGGER_HILT_COMPILER = "com.google.dagger:hilt-compiler:${Versions.DAGGER_HILT}"

    const val DATASTORE = "androidx.datastore:datastore-preferences:${Versions.DATASTORE}"

    const val NAVIGATION_FRAGMENT = "androidx.navigation:navigation-fragment-ktx:${Versions.NAVIGATION}"
    const val NAVIGATION_UI = "androidx.navigation:navigation-ui-ktx:${Versions.NAVIGATION}"

    const val GLIDE = "com.github.bumptech.glide:glide:${Versions.GLIDE}"
    const val GLIDE_COMPILER = "com.github.bumptech.glide:compiler:${Versions.GLIDE}"
    const val CIRCLE_IMAGEVIEW = "de.hdodenhof:circleimageview:${Versions.CIRCLE_IMAGEVIEW}"

    const val FIREBASE_BOM = "com.google.firebase:firebase-bom:27.1.0"
    const val FIREBASE_MESSAGING = "com.google.firebase:firebase-messaging"


}