# SharedPreferencesMultiplatform

`SharedPreferencesMultiplatform` is a Kotlin Multiplatform library that provides shared preference functionality across Android and iOS platforms. This library allows you to manage key-value storage in a platform-agnostic way, while using a dependency injection framework like Koin.

## Features
- **Kotlin Multiplatform support**: Use the same code for shared preferences across Android and iOS.
- **Simple DI Integration**: Easily inject the shared preference instance using Koin or any other DI framework.

## Installation

Add the library to your `commonMain` dependencies in your `build.gradle.kts` file:

```kotlin
kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation("io.github.firelord:sharedpreference-multiplatform:1.0.1")
        }
    }
}
```

## Usage

### Dependency Injection Setup

To use `SharedPreferencesMultiplatform`, you'll need to set up dependency injection (DI). In this example, we'll use [Koin](https://insert-koin.io/) as the DI framework.

### Common Module

In your `commonMain` source set, declare the expected shared preference module:

```kotlin
// commonMain/kotlin/com/firelord/samplecmp/util/SharedPreferenceModule.kt

import org.koin.core.module.Module

expect val sharedPreference: Module
```

### iOS Module

In the `iosMain` source set, define the actual implementation. You don't need to pass any context on iOS:

```kotlin
// iosMain/kotlin/com/firelord/samplecmp/util/SharedPreferenceModule.kt

import org.koin.dsl.module
import io.github.firelord.sharedpreference.SharedPreference

actual val sharedPreference = module {
    single { SharedPreference("") } // No context required for iOS
}
```

### Android Module

In the `androidMain` source set, pass the Android application context to the shared preference instance:

```kotlin
// androidMain/kotlin/com/firelord/samplecmp/util/SharedPreferenceModule.kt

import org.koin.dsl.module
import io.github.firelord.sharedpreference.SharedPreference
import org.koin.android.ext.koin.androidApplication

actual val sharedPreference = module {
    single {
        val context = androidApplication().applicationContext
        SharedPreference(context)
    }
}
```

### Injecting Shared Preferences

In your `ViewModel` or any other class where you want to use shared preferences, you can inject the instance using Koin.

Example in `commonMain`:

```kotlin
class MyViewModel(
    private val sharedPreference: SharedPreference
): ScreenModel {
    fun saveData(key: String, value: String) {
        sharedPreference.putString(key, value)
    }

    fun getData(key: String): String? {
        return sharedPreference.getString(key)
    }
}
```

## Sample Project

For a fully working setup, you can refer to the [SampleCMP repository](https://github.com/FireLord/SampleCMP), where Koin is used to manage dependencies and Voyager is used for navigation.

The sample includes:

- **Koin setup**: Integrating `SharedPreferencesMultiplatform` with Koin for DI.
- **Voyager navigation**: Using the library within a multiplatform navigation setup.
- **Platform-specific code**: How to handle shared preferences on both Android and iOS platforms.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.