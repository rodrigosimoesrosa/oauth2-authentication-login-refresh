package br.com.mirabilis.oauth2authentication.login

import br.com.mirabilis.oauth2authentication.base.BaseMVPPresenter
import br.com.mirabilis.oauth2authentication.base.BaseMVPView

/**
 * Created by rodrigosimoesrosa
 */
object LoginContract {

    interface LoginView : BaseMVPView {
        fun onLogged()
        fun onLoginError(e: Exception)
    }

    interface LoginPresenter : BaseMVPPresenter<LoginView> {
        fun login(email: String, password: String)
        fun cancel()
    }
}