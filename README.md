# 📱 Mobile App Developer Machine Task

## 🧾 Overview

A mobile application built with **Android (Kotlin)** following the **MVVM architecture**, integrating **Google Maps**, **Room for persistence**, and **Dagger Hilt** for dependency injection.

The app features a bottom navigation bar with three tabs:

- 🗺️ **Map**
- 📋 **List**
- 🔐 **Login**


## 🛠️ Tech Stack

- **Language:** Kotlin
- **UI:** XML Layouts
- **Architecture:** MVVM (Model-View-ViewModel)
- **Persistence:** Room (Local Database)
- **Dependency Injection:** Dagger Hilt
- **Asynchronous Programming:** Kotlin Coroutines & Flows
- **Maps:** Google Maps SDK

## Requirements

To run the samples, you will need:

- To [sign up with Google Maps Platform]
- A Google Maps Platform [project] with the **Maps SDK for Android** enabled
- An [API key] associated with the project above ... follow the [API key instructions] if you're new to the process
- See each sample for pre-requisites.
- All require up-to-date versions of the Android build tools and the Android support repository.


## 🚀 Features

### 🗺️ Map Screen

- Tapping on the map adds a **marker** at the tapped location.
- Markers are **persisted locally** using Room and are:
  - Retained across tab switches.
  - Preserved after closing and reopening the app.

### 📋 List Screen

- Displays a **list of all markers** added via the Map screen.
- Each list item includes a **Delete** button:
  - Removes the marker from both the **map** and the **database**.

### 🔐 Login Screen

- Focused on evaluating **UI/UX design**.
- No authentication logic — purely UI/UX based.


## 💾 Data Persistence

- **Room Database** is used to store marker data.
- Markers are represented by an entity and accessed through a DAO.
- Data flow is managed using Kotlin **Flow**, ensuring reactive updates.


## 🔄 State Management

- **State is preserved** across tab switches using ViewModels scoped to activity.
- Navigation does **not reload screens**.
- Marker data is retained even after app restarts due to Room persistence.

## 🧪 How to Run

1. Clone the repository.
2. Open in **Android Studio**.
3. Add your **Google Maps API key** to `local.properties` or directly in the `AndroidManifest.xml`.
4. Build and run on an emulator or real device.


## ✅ Evaluation Criteria

- Functional completeness.
- Smooth user experience and UI responsiveness.
- Code quality, architecture adherence (MVVM), and separation of concerns.
- Efficient use of Kotlin, Coroutines, and Flows.
- Clean implementation of dependency injection using Dagger Hilt.


## 📌 Notes

This project is intended for demonstration and evaluation purposes. Ensure all functionality works as expected and follows best practices in modern Android development.

[API key]: https://developers.google.com/maps/documentation/android-sdk/get-api-key
[API key instructions]: https://developers.google.com/maps/documentation/android-sdk/config#step_3_add_your_api_key_to_the_project
[project]: https://developers.google.com/maps/documentation/android-sdk/cloud-setup#enabling-apis
[Sign up with Google Maps Platform]: https://console.cloud.google.com/google/maps-apis/start




