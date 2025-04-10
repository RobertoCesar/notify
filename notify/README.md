# NotifyScheduler

`NotifyScheduler` is a simple and reusable Android library that allows apps to schedule hourly local notifications within a user-defined time window.

## 📦 Features

- Schedule notifications every hour between a start and end time.
- Persist configuration using `SharedPreferences`.
- Designed to be lightweight and easy to integrate.
- Uses `WorkManager` to ensure compatibility with Android 12+ battery restrictions.

## 🚀 Installation

1. Add the module to your project or include it as a library.
2. Add `WorkManager` to your dependencies (if not already):

## Instalação

Adicione o JitPack ao seu `settings.gradle` ou `build.gradle` (nível de projeto):

```kotlin
dependencyResolutionManagement {
    repositories {
        ...
        maven { url = uri("https://jitpack.io") }
    }
}
```

Depois, adicione a dependência:

dependencies {
implementation("com.github.RobertoCesar:notify:TAG")
}



```kotlin
implementation("androidx.work:work-runtime-ktx:2.9.0")
```
3. Initialize the library in your app:

```kotlin
NotifyScheduler.configureAndScheduleNotifications(
    context = applicationContext,
    startHour = 9,
    endHour = 18
)
```
### 🛠️ How it works
The library saves the startHour and endHour in SharedPreferences.

It uses WorkManager to schedule a worker that triggers every hour.

If the current time is within the configured range, a notification is shown.

### 📋 Requirements
Android API 21+

Kotlin

WorkManager
```kotlin
// You can configure and schedule notifications like this:
NotifyScheduler.configureAndScheduleNotifications(
    context = applicationContext,
    startHour = 10,
    endHour = 17
)
```

### 📁 SharedPreferences
The preferences are stored in the application's private storage with the name:
notify_scheduler_prefs
Keys used:
`start_hour`
`end_hour`

# 📦 What's inside
- NotifyScheduler.kt – Pulic API to configure and schedule notifications
- HourlyNotificationWorker.kt – Worker that runs every hour and sends a notification
- NotificationPreferences.kt – Helper for saving and loading hour range

# 🙋‍♂️ Author
Developed by Roberto – feel free to contribute or fork the project.

# 📄 License
MIT License
