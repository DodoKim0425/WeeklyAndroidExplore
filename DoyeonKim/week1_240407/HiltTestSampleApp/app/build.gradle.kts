plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.dodo.hilttestapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.dodo.hilttestapp"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "com.dodo.hilttestapp.CustomTestRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    // hilt
    implementation("com.google.dagger:hilt-android:2.43.2")
    kapt("com.google.dagger:hilt-android-compiler:2.43.2")

    // hilt testing
    // For Robolectric tests.
    testImplementation("com.google.dagger:hilt-android-testing:2.43.2")
    // For Robolectric tests....with Kotlin.
    kaptTest("com.google.dagger:hilt-android-compiler:2.43.2")
    // For instrumented tests.
    androidTestImplementation("com.google.dagger:hilt-android-testing:2.43.2")
    // For instrumented tests....with Kotlin.
    kaptAndroidTest("com.google.dagger:hilt-android-compiler:2.43.2")

    // for testing (Robolectric)
    testImplementation ("com.google.truth:truth:1.0.1")

    // for testing (instrumented)
    androidTestImplementation ("com.google.truth:truth:1.0.1")

    // Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0")
    implementation ("com.squareup.retrofit2:converter-scalars:2.3.0") // String 처리시

}

kapt {
    correctErrorTypes = true
}

hilt {
    enableTransformForLocalTests = true
}