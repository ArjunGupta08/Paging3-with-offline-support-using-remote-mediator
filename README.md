# Pagination with OFFLINE Support

Pagination with Paging3 covered in previous repository!

Here we will continue to implement offline pagination.

# Project SetUp -
Module Level -

    kotlin("kapt")
    id("com.google.dagger.hilt.android")

    // Hilt
    implementation("com.google.dagger:hilt-android:2.51")
    kapt("com.google.dagger:hilt-android-compiler:2.51")

    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    // LiveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")

    //Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

    //Room
    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    implementation("androidx.room:room-paging:2.6.1")
    // To use Kotlin annotation processing tool (kapt)
    kapt("androidx.room:room-compiler:2.6.1")

    //Coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    // Paging3
    implementation ("androidx.paging:paging-runtime:3.2.1")
    
    // Allow references to generated code
    kapt {
        correctErrorTypes = true
    }
Project Level -

    id("com.google.dagger.hilt.android") version "2.51" apply false
