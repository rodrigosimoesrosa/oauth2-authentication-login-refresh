# OAuth2 Authentication for Android
OAuth2 implementation for Android, I used some library's in this project like:

Retrofit - [square.github.io/retrofit/](square.github.io/retrofit/)

OkHttp - [square.github.io/okhttp/](square.github.io/okhttp/)

I used a SharedPreferences to save Token on device, but it's only example! You can use what you want to encrypt your token and save after that.

## Base URL
```kotlin
object APISettings {
    val base = "YOUR BASE URL"
}
```

## Auth and Refresh endpoint
```kotlin
interface AuthAPI {

    /**
     * Your endpoint of auth
     */
    @POST("auth")
    fun auth(@Body auth: Auth): Call<Token>

    /**
     * Your endpoint of refresh your token
     */
    @POST("refresh")
    fun refresh(@Body refreshAuth: Refresh): Call<Token>
}
```

## Token object
```kotlin
data class Token(val scope: String,
                 val token_type: String,
                 var expires_in: Long,
                 val refresh_token: String,
                 val id_token: String,
                 val access_token: String) : Serializable
``` 
