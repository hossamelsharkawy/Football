plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.hossam.football"
    compileSdk = 34


    defaultConfig {
        applicationId = "com.hossam.football"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"


        testInstrumentationRunner = "com.hossam.football.CustomTestRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        buildConfigField("String", "apiUrl", "\"https://api.football-data.org/v4/\"")
        buildConfigField("String", "apiToken", "\"6dfe921ddf3e47c1b2020913c7d73ca6\"")
        buildConfigField("String", "domain", "\"api.football-data.org\"")
        buildConfigField(
            "String",
            "certificate",
            "\"sha256/lhGIeYoZZGZh2Izcjw4ed6f0v8Fd0//jyiC/dV8bJVo=\""
        )
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            isDebuggable= false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isMinifyEnabled = false
            isDebuggable= false
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
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.9"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

}


dependencies {

    implementation(libs.androidx.security.crypto.ktx)

    ksp(libs.hilt.android.compiler)

    implementation(libs.androidx.hilt.navigation.compose)

    implementation(libs.hilt.android)
    implementation(libs.retrofit.logging)

    implementation(libs.converter.gson)
    implementation(libs.retrofit)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(libs.coil)
    implementation(libs.coil.svg)

    implementation(libs.material3)
    implementation(libs.androidx.lifecycle.runtime.compose)





    testImplementation(libs.google.hilt.android.testing)
    // ...with Kotlin.
    kspTest(libs.hilt.android.compiler)


    // For instrumented tests.
    androidTestImplementation(libs.google.hilt.android.testing)
    // ...with Kotlin.
    kspAndroidTest(libs.hilt.android.compiler)



/*
    androidTestImplementation(libs.mockk.mockk.android)
    androidTestImplementation(libs.mockk.agent)
*/




}