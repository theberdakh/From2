## From2 Android Library
An android library to integrate translation service for your app easily. Uses  <a href="https://www.from-to.uz/en/api-docs">From-to.uz</a> free API service for translation. Supports translations to Karakalpak and 140+ other languages
<pre>
<img src="/media/img.png" alt="Screenshot from sample app"/>
</pre>


### 1. Adding From2 to your project

* Include jitpack in your root `settings.gradle` file.

```gradle
pluginManagement {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

* And add it's dependency to your app level `build.gradle` file:

```gradle
dependencies {
    implementation 'com.github.theberdakh:From2:1.0.1'
}
```

#### Sync your project, and :scream: boom :fire: you have added From2 successfully:

### 2. Usage

* You can call to translate from any screen or class:

```kt
         val text = "What a wonderful morning!"

lifecycleScope.launch {
    translate(TranslateLanguage.ENGLISH_LATIN,
        TranslateLanguage.KARAKALPAK,
        text = text,
        onSuccess = { text ->
            //Successfully translated
            binding.editTextBottomInput.setText(text)
        },
        onMessage = { message ->
            //Message returned from server
            binding.editTextBottomInput.setText(message)
        },
        onError = { error ->
            //Error happened
            error.printStackTrace()
        })
}
```

* make sure you've added permissions for android.permission.INTERNET to your app manifest, before translating text.

Note: You can run the sample project in the repo, to see how it works!

:pushpin: Please, feel free to give me a star :star2:, I also love sparkles :sparkles: :relaxed:
<div align="center">
    <sub>Developed with :sparkling_heart: by
        <a href="https://github.com/theberdakh">Berdakh Tlepov</a>
    </sub>
</div>





## Authors

- [@theberdakh](https://www.github.com/theberdakh)

## Download

- [Google Play Store](https://play.google.com/store/apps/details?id=com.theberdakh.from2)


