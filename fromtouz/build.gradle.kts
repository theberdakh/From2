import com.android.build.gradle.internal.utils.createPublishingInfoForLibrary

plugins {
    id("org.jetbrains.kotlin.jvm")
    id("maven-publish")
}

kotlin {
    jvmToolchain(17)
}

dependencies {

    testImplementation("junit:junit:4.13.2")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.6")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.6")
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1") // Use core coroutines

}

publishing {
    publications {
        create("release", MavenPublication::class) {
            groupId = "com.github.theberdakh"
            artifactId = "From2"
            version = "1.0.0"

            afterEvaluate {
                from(components["java"])
            }
        }
    }
}


