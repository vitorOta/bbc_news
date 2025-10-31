plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose.compiler)
}

android {
    namespace = "com.vitorota.features.news"
    compileSdk = 36

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        compose = true

    }

    flavorDimensions("provider", "country")

    buildTypes {
        debug {
            buildConfigField("String", "DATA_URL", "\"https://newsapi.org/v2/\"")
            buildConfigField("String", "API_KEY", "\"919d4fa510be4a3f897f6acbd5332734\"")
        }

        release {
            buildConfigField("String", "DATA_URL", "\"https://newsapi.org/v2/\"")
            buildConfigField("String", "API_KEY", "\"919d4fa510be4a3f897f6acbd5332734\"")
        }
    }
}

dependencies {
    implementation(project(":libraries:network"))
    implementation(project(":libraries:mvi"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.biometric)
    api(libs.koin)

    implementation(libs.androidx.navigation.compose)
    implementation(libs.koin.androidx.compose)

    // Compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    debugImplementation(libs.androidx.compose.ui.tooling)
    implementation(libs.coil.compose)

    testImplementation(libs.junit)
    testImplementation(libs.koin.test)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}