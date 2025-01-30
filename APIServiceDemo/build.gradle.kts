plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    // OkHTTP
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    // Moshi
    implementation("com.squareup.moshi:moshi-kotlin:1.15.2")
    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.11.0")
    //Corrutinas
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.1")

}