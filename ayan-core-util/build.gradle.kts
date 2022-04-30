plugins {
    id(Plugins.androidLibrary)
    kotlin(KotlinPlugins.android)
    id(KotlinPlugins.maven)
}

android {
    compileSdk = Application.compileSdk

    defaultConfig {
        minSdk = Application.minSdk
        targetSdk = Application.targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName("release") {
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
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Compose.composeVersion
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

}

dependencies {
    implementation(AndroidX.coreKtx)
    implementation(AndroidX.appCompatActivity)
    implementation(AndroidX.lifecycleRuntimeKtx)
    implementation(AndroidX.androidxAnnotation)

    //Compose
    implementation(Compose.ui)
    implementation(Compose.material)
    implementation(Compose.preview)
    implementation(Compose.activity)
    debugImplementation(Compose.uiTooling)
    implementation(Compose.uiUtils)
    implementation(Compose.runtime)
    implementation(Compose.runtimeLiveData)
    implementation(Compose.foundation)
    implementation(Compose.compiler)

    implementation(ayan.Core.ayanCore)

}

afterEvaluate {
    publishing {
        publications {
            // Creates a Maven publication called "release".
            register("release", MavenPublication::class) {
                // Applies the component for the release build variant.
                // NOTE : Delete this line code if you publish Native Java / Kotlin Library
                from(components["release"])


                // Library Package Name (Example : "com.frogobox.androidfirstlib")
                // NOTE : Different GroupId For Each Library / Module, So That Each Library Is Not Overwritten
                groupId = "com.github.alitafreshi"

                // Library Name / Module Name (Example : "androidfirstlib")
                // NOTE : Different ArtifactId For Each Library / Module, So That Each Library Is Not Overwritten
                artifactId = "ayan-core-util"

                // Version Library Name (Example : "1.0.0")
                version = "0.0.10"
            }
        }
    }
}