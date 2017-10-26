# OAuth2 Authentication for Android

# Base URL
```kotlin
object APISettings {
    val base = "YOUR BASE URL"
}
```

# Auth and Refresh endpoint
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
