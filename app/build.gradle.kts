plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
    id("dagger.hilt.android.plugin")
}

android {
    defaultConfig {
        applicationId = "com.devblock.interview"
        versionCode = 1
        versionName = "1.0"

        minSdkVersion(23)
        targetSdkVersion(30)
        compileSdkVersion(30)

            // defining the google map key

    }

    kotlinOptions {
        useIR = true
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled  = false
        }
    }

    buildFeatures {
        dataBinding = true
    }


    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }


}

dependencies {
    implementation(project(":features:splash"))
    implementation(project(":features:login"))
    implementation(project(":features:welcome"))
    implementation(project(":features:contact"))
    implementation(project(":core:ui"))
    implementation(project(":core:utils"))
    implementation(project(":core:network"))
    implementation(project(":core:database"))
    implementation(project(":core:datastore"))
    implementation(project(":navigation"))


    implementation(Libs.ANDROID_MATERIAL)



    implementation(Libs.COROUTINES_CORE)
    implementation(Libs.COROUTINES_ANDROID)

    implementation(Libs.DAGGER_HILT)
    kapt(Libs.DAGGER_HILT_COMPILER)


    implementation(Libs.RETROFIT)

    implementation(Libs.NAVIGATION_FRAGMENT)
    implementation(Libs.NAVIGATION_UI)
    
}