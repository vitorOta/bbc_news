plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.vitorota.news"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.vitorota.news"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        buildConfig = true
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

    flavorDimensions("provider", "country")
    productFlavors {
        create("bbc") {
            dimension = "provider"
            applicationIdSuffix = ".bbc"
            versionNameSuffix = "-bbc"

            buildConfigField("String", "PROVIDER", "\"bbc\"")
        }

        create("us") {
            dimension = "country"
            applicationIdSuffix = ".us"
            versionNameSuffix = "-us"

            buildConfigField("String", "COUNTRY", "\"us\"")
        }
    }

}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    implementation(project(":features:news"))

    testImplementation(libs.junit)
    testImplementation(libs.koin.test)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}