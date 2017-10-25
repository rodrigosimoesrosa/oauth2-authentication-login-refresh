package br.com.mirabilis.oauth2authentication.api.auth

import br.com.mirabilis.oauth2authentication.model.oauth.Token
import br.com.mirabilis.oauth2authentication.model.oauth.request.Auth
import br.com.mirabilis.oauth2authentication.model.oauth.request.Refresh
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by rodrigosimoesrosa
 */
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