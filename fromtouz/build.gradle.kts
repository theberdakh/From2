import com.android.build.gradle.internal.utils.createPublishingInfoForLibrary

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("maven-publish")
}

android {
    namespace = "com.theberdakh.fromtouz"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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

    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }

}

dependencies {

    implementation("androidx.core:core-ktx:1.13.1")
    testImplementation("junit:junit:4.13.2")

    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.6")
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.0")
}

publishing {
    publications {
        create("release", MavenPublication::class) {
            groupId = "com.github.theberdakh"
            artifactId = "From2"
            version = "1.0.0"

            afterEvaluate {
                from(components["release"])
            }
        }
    }
}


