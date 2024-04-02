# Pagination with OFFLINE Support

Pagination with Paging3 covered in previous  [` repository `](https://github.com/ArjunGupta08/Paging3-with-MVVM)!

Here we will continue to implement offline pagination.

## Project SetUp -
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

<p align="center">
  <img alt="Light" src="https://github.com/ArjunGupta08/Paging3-with-offline-support-using-remote-mediator/assets/85922120/62a44738-a50f-4ed6-97bf-ae3dfc62feb0" width="48%">
&nbsp; &nbsp; &nbsp; &nbsp;
  <img alt="Dark" src="https://github.com/ArjunGupta08/Paging3-with-offline-support-using-remote-mediator/assets/85922120/2e48feaf-ee2b-4029-a9f7-e60999cbf644" width="48%">
</p>

## Room DB SetUp
  - Write Logic to fetch the page.
  - Create the Room DB Module inside di package.
  - Save the quotes in DB.
    - Define quotes table to manage quotes inside room databse. 
  - Another table is needed to maintain the ` Previous Keys ` and ` Next Keys `. (With quote id)
    - Define remote keys table to manage keys inside room database.
    - This table is used to calculate the ` Previous Keys ` and ` Next Keys `.
  - We will use this remote keys table to fetch the data from quote table in pages. 
![Screenshot 2024-04-01 125141](https://github.com/ArjunGupta08/Paging3-with-offline-support-using-remote-mediator/assets/85922120/99b2d220-d183-460e-a0bf-ce8ec89ad11a)

Now We have implemented required setup of Room Database for Paging. 

## Remote Mediator 
Now create the Remote Mediator class to handle different states - 
 - PREPEND,
 - APPEND,
 - REFRESH.

Calculate the current page based on the loadType.
 - Using paging state you can calculate the page number.
 - For append, you can get the last page's last record to get the next key.
 - For prepend, you can use first page in paging state for previous key.
 - Refresh and First Time Load is similar - it uses Anchor Position to evaluate the current page number.

## [` RESOURCE `](https://youtu.be/9YhaPvAnBb8?si=tQewxr9nEMsW-ngE)
 
