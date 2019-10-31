## Kotlin Sample App
[![CircleCI](https://circleci.com/gh/VMadalin/kotlin-sample-app/tree/master.svg?style=shield)](https://circleci.com/gh/VMadalin/kotlin-sample-app/tree/master)
[![Codecov](https://codecov.io/gh/VMadalin/kotlin-sample-app/coverage.svg)](https://codecov.io/gh/VMadalin/kotlin-sample-app)
[![Codacy](https://api.codacy.com/project/badge/Grade/5970b6648df0465588f9781ae6e3332e)](https://www.codacy.com/manual/VMadalin/kotlin-sample-app?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=VMadalin/kotlin-sample-app&amp;utm_campaign=Badge_Grade)
[![CodeFactor](https://www.codefactor.io/repository/github/vmadalin/kotlin-sample-app/badge)](https://www.codefactor.io/repository/github/vmadalin/kotlin-sample-app)
[![Kotlin Version](https://img.shields.io/badge/kotlin-1.3.50-blue.svg)](http://kotlinlang.org/)
[![Gradle](https://lv.binarybabel.org/catalog-api/gradle/latest.svg)](https://lv.binarybabel.org/catalog/gradle/latest)
[![API](https://img.shields.io/badge/API-21%2B-blue.svg?style=flat)](https://android-arsenal.com/api?level=21)
[![License](https://img.shields.io/badge/License-Apache%202.0-lightgrey.svg)](http://www.apache.org/licenses/LICENSE-2.0)

<img src="screenshots/demo/demo.gif" width="250" align="right" hspace="20">

Kotlin Sample App is a sample project that presents modern, 2019 approach to [Android](https://www.android.com/) application development using [Kotlin](https://kotlinlang.org/) and latest tech-stack.

The goal of the project is to demonstrate best practices, provide a set of guidelines, and present modern Android
application architecture that is modular, scalable, maintainable and testable. This application may look simple, but it
has all of these small details that will set the rock-solid foundation of the larger app suitable for bigger teams and
long application lifecycle management.

## Screenshots

| Mode | Characters list | Characters favorite | Character detail |
|------|-----------------|---------------------|------------------|
| Light | <img src="screenshots/phone/light_mode_characters_list.png" width="250"> | <img src="screenshots/phone/light_mode_characters_favorite.png" width="250"> | <img src="screenshots/phone/light_mode_character_detail.png" width="250"> |
| Dark | <img src="screenshots/phone/dark_mode_characters_list.png" width="250">  | <img src="screenshots/phone/dark_mode_characters_favorite.png" width="250"> | <img src="screenshots/phone/dark_mode_character_detail.png" width="250"> |

## Architecture

//TODO

## Libraries

This project takes advantage of many popular libraries and tools of the Android ecosystem. Most of the libraries are in the stable version, unless there is a good reason to use non-stable dependency.

* [Jetpack](https://developer.android.com/jetpack)
    * [Android KTX](https://developer.android.com/kotlin/ktx.html) - provide concise, idiomatic Kotlin to Jetpack and Android platform APIs.
    * [AndroidX](https://developer.android.com/jetpack/androidx) - major improvement to the original Android [Support Library](https://developer.android.com/topic/libraries/support-library/index), which is no longer maintained.
    * [Benchmark](https://developer.android.com/studio/profile/benchmark.html) - handles warmup, measures your code performance, and outputs benchmarking results to the Android Studio console.
    * [Data Binding](https://developer.android.com/topic/libraries/data-binding/) - allows you to bind UI components in your layouts to data sources in your app using a declarative format rather than programmatically.
    * [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) - perform actions in response to a change in the lifecycle status of another component, such as activities and fragments.
    * [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - lifecycle-aware, meaning it respects the lifecycle of other app components, such as activities, fragments, or services.
    * [Navigation](https://developer.android.com/guide/navigation/) - helps you implement navigation, from simple button clicks to more complex patterns, such as app bars and the navigation drawer.
    * [Paging](https://developer.android.com/topic/libraries/architecture/paging/) - helps you load and display small chunks of data at a time. Loading partial data on demand reduces usage of network bandwidth and system resources.
    * [Room](https://developer.android.com/topic/libraries/architecture/room) - persistence library provides an abstraction layer over SQLite to allow for more robust database access while harnessing the full power of SQLite.
    * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - designed to store and manage UI-related data in a lifecycle conscious way. The ViewModel class allows data to survive configuration changes such as screen rotations.
* [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - managing background threads with simplified code and reducing needs for callbacks.
* [Dagger2](https://dagger.dev/) - Dependency injector for replacement all FactoryFactory classes.
* [Retrofit](https://square.github.io/retrofit/) - Type-safe HTTP client.
* [Stetho](http://facebook.github.io/stetho/) - debug bridge for applications via Chrome Developer Tools

## Contributions

All contributions are welcome!
Please feel free to post questions, recommendations, ideas, bugs by create [new issue](https://github.com/VMadalin/kotlin-sample-app/issues/new) following the template or if you want create directly [new pull request](https://github.com/VMadalin/kotlin-sample-app/compare).

## More Inspiration

This is project is a sample, to inspire you and should handle most of the common cases, but obviously not all. If you need to take a look at additional resources to find solutions for your project, visit these interesting projects:

* [iosched](https://github.com/google/iosched) by [google](https://github.com/google) - official Android application from google IO 2019.
* [plaid](https://github.com/android/plaid) by [android](https://github.com/android) - app which provides design news & inspiration as well as being an example of implementing material design.
* [sunflower](https://github.com/android/sunflower) by [android](https://github.com/android) - a gardening app illustrating Android development best practices with Android Jetpack.
* [architecture-components-samples](https://github.com/android/architecture-components-samples) by [android](https://github.com/android) - collection of samples for Android Architecture Components.
* [architecture-sample](https://github.com/android/architecture-samples) by android](https://github.com/android) - collection of samples to discuss and showcase different architectural tools and patterns for Android apps.
* [android-clean-architecture-boilerplate](https://github.com/bufferapp/android-clean-architecture-boilerplate) by [bufferapp](https://github.com/bufferapp) - an android boilerplate project using clean architecture
* [android-kotlin-clean-architecture](https://github.com/sanogueralorenzo/Android-Kotlin-Clean-Architecture) by [sanogueralorenzo](https://github.com/sanogueralorenzo) - android sample Clean Architecture app written in Kotlin.
* [modularization-example](https://github.com/JeroenMols/ModularizationExample) by [JeroenMols] - easy to understand real-life example of a modularized Android app.
* [lego-catalog](https://github.com/Eli-Fox/LEGO-Catalog) by [Eli-Fox](https://github.com/Eli-Fox) - app illustrating current Android Architecture state using Android development best practices.
* [android-showcase](https://github.com/igorwojda/android-showcase) by [igorwojda](https://github.com/igorwojda) - app following best practices: Kotlin, coroutines, Clean Architecture, feature modules, tests, MVVM, static analysis.

## License
    Copyright 2019 vmadalin.com

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
