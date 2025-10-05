plugins {
    id("com.android.application")
    id("com.google.gms.google-services")


}

android {
    namespace = "com.example.absen"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.absen"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(platform("com.google.firebase:firebase-bom:34.3.0"))
    implementation("com.google.firebase:firebase-analytics")

    implementation ("com.google.firebase:firebase-firestore")
    implementation ("com.google.firebase:firebase-auth")
    implementation ("com.google.firebase:firebase-database")
    implementation("androidx.recyclerview:recyclerview:1.3.2")

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    implementation(libs.firebase.database)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

}