plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    id("kotlin-kapt")
}

android {
    namespace = "com.example.movieappcompose"
    compileSdk = 33



    defaultConfig {

        buildConfigField(
            "String",
            "GUARDIAN_API_BASE_URL",
            project.property("guardianApiBaseUrl").toString()
        )
        applicationId = "com.example.movieappcompose"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }


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
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.2"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)

//    implementation(libs.retrofit)
//    implementation(libs.converter)
//
//    implementation(libs.coroutoines.core)
//    implementation(libs.coroutoines.android)
//
//    implementation(libs.lifecycle.viewmodel)
//    implementation(libs.lifecycle.livedata)
//
//    implementation(libs.coil.compose)

    implementation(libs.material3)
    implementation(libs.compose.material.icons.core)
    implementation(libs.compose.material.icons.extended)
    implementation(libs.lifecycle.viewmodel.compose)
    implementation(libs.runtime.rxjava2)
    implementation(libs.runtime.livedata)
    implementation(libs.coil.compose)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.navigation.compose)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    implementation(libs.material.v)
    implementation(libs.material.icons.v)

    implementation(libs.room)
    annotationProcessor(libs.room.compiler)
    kapt(libs.kapt)

    implementation(libs.compose.ui.util)
    implementation(libs.accompanist.pager)

    implementation(libs.bundles.ktor)
    implementation(libs.json.serialization)





}