package br.com.mirabilis.oauth2authentication.login

import br.com.mirabilis.oauth2authentication.base.BaseMVPPresenter
import br.com.mirabilis.oauth2authentication.base.BaseMVPView

/**
 * Created by rodrigosimoesrosa
 */
object LoginContract {

    interface LoginView : BaseMVPView {
        fun onLogged(success: Boolean)
        fun onLoginError(e: Throwable?)
    }

    interface LoginPresenter : BaseMVPPresenter<LoginView> {
        fun login(email: String, password: String)
        fun cancel()
    }
}