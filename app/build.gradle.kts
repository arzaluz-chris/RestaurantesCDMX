/*
 * Configuración de build del módulo app
 * Define dependencias, configuración de compilación y características
 */
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize") // ✅ AGREGAR ESTA LÍNEA IMPORTANTE
}

android {
    namespace = "com.example.restaurantescdmx"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.restaurantescdmx"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    // Habilitar View Binding para facilitar el acceso a vistas
    buildFeatures {
        viewBinding = true
        // ✅ NO HABILITAR COMPOSE
    }
}

dependencies {
    // AndroidX Core
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // Material Design Components
    implementation("com.google.android.material:material:1.11.0")

    // RecyclerView para listas
    implementation("androidx.recyclerview:recyclerview:1.3.2")

    // ViewPager2 para pestañas
    implementation("androidx.viewpager2:viewpager2:1.0.0")

    // Fragment KTX para manejo de fragmentos
    implementation("androidx.fragment:fragment-ktx:1.6.2")

    // Activity KTX
    implementation("androidx.activity:activity-ktx:1.8.2")

    // Lifecycle components
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")

    // Testing
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}