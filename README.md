# Note App 📒 (Jetpack Compose + MVVM + Room)

A **simple yet clean Note-taking app** built using **Jetpack Compose, MVVM architecture, Room Database, and clean code practices**.

---

## ✨ Features

✅ Add, edit, and delete notes with title, description, and date.

✅ Date picker with restriction on backdates.

✅ Notes listed in a **Card view with clean Compose UI**.

✅ MVVM architecture with clean separation of concerns.

✅ Room for local offline persistence.

✅ Navigation using Jetpack Navigation Compose.

✅ Theming with Material3 and Compose.

---

## 🏗️ Architecture

The project follows **MVVM + Clean Architecture principles:**

* **Model**: Contains `Note` data class representing your entity.
* **ViewModel**: `NoteViewModel` exposing flows and methods to the UI while handling lifecycle.
* **Repository**: `NoteRepository` to abstract data operations from the ViewModel.
* **Data Layer**: `NoteDao`, `NoteDatabase` using Room for local data.
* **UI Layer**: Jetpack Compose screens with theming and navigation.

### Dependency Flow:

`UI (Compose) → ViewModel → Repository → DAO → Room Database`

This ensures a **clean, testable, and maintainable codebase**.

---

## 🛠️ Tech Stack

* **Language:** Kotlin
* **UI:** Jetpack Compose, Material 3
* **Architecture:** MVVM, Clean Architecture
* **Local Storage:** Room Database
* **Navigation:** Jetpack Navigation Compose
* **State Management:** LiveData, Flow
* **Dependency Injection:** (You can add Hilt/Koin in the future)

---

## 📂 Project Structure

```
com.example.noteapp
│
├── data.local       # Room DAO and Database
│   ├── NoteDao
│   └── NoteDatabase
│
├── model            # Data models
│   └── Note
│
├── repository       # Repository for data mediation
│   └── NoteRepository
│
├── ui               # Compose screens and themes
│   ├── screens
│   │   ├── AddNoteScreen.kt
│   │   └── NoteListScreen.kt
│   └── theme
│       ├── Color.kt
│       ├── Theme.kt
│       └── Type.kt
│
├── viewmodel        # ViewModels and factories
│   ├── NoteViewModel
│   └── NoteViewModelFactory
│
└── MainActivity     # Entry point, sets up Compose and navigation
```


