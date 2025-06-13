plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.google.service)
}

android {
    namespace = "app.santanu.love.munu"
    compileSdk = 35

    defaultConfig {
        applicationId = "app.santanu.love.munu"
        minSdk = 28
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            isCrunchPngs = false
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

    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)

    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    implementation(libs.androidx.browser)
    implementation(libs.androidx.splashscreen)

    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    implementation(libs.androidx.viewpager2)
    implementation(libs.androidx.recyclerview)

    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)

    implementation(libs.timber)
    implementation(libs.gson)

    implementation(platform(libs.firbase.bom))
    implementation(libs.firebase.analytics)

    implementation(libs.onesignal)

    implementation(libs.squareup.picasso)
    implementation(libs.tbuonomo.dotsindicator)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}