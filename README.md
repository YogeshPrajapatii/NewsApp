# NewsApp 📰

A modern news application built entirely with Kotlin and Jetpack Compose. This project is a core part of my journey to master native Android development and showcases my ability to build a functional, single-activity app using modern tools and architecture.

## Project Goal

The primary goal of this project was to implement a clean MVVM architecture for an app that consumes a live web API. It demonstrates fetching, parsing, and displaying data asynchronously while maintaining a responsive and declarative UI.

## ✨ Features

* Browse the latest top headlines.
* A clean and intuitive user interface built with Jetpack Compose.
* Handles loading, success, and error states gracefully.
* Demonstrates navigation between a list screen and a detail screen.

## 🛠️ Tech Stack & Key Concepts

* **Language:** [Kotlin](https://kotlinlang.org/)
* **UI Toolkit:** [Jetpack Compose](https://developer.android.com/jetpack/compose) for a fully declarative UI.
* **Architecture:** MVVM (Model-View-ViewModel)
* **Asynchronous Programming:** Kotlin Coroutines for managing background threads.
* **Networking:** [Ktor Client](https://ktor.io/docs/client-overview.html) for type-safe and efficient API calls.
* **State Management:** `StateFlow` and `ViewModel` to manage and expose UI state.
* **Image Loading:** [Coil](https://coil-kt.github.io/coil/) for loading images from URLs.

## 📄 API Used

This application fetches news data from the [NewsAPI.org](https://newsapi.org/). An API key is required for the project to function.
