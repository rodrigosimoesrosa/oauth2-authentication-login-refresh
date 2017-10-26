package br.com.mirabilis.oauth2authentication.model.oauth

import java.io.Serializable

/**
 * Created by rodrigosimoesrosa
 */
data class Token(val scope: String,
                 val token_type: String,
                 var expires_in: Long,
                 val refresh_token: String,
                 val id_token: String,
                 val access_token: String) : Serializable
