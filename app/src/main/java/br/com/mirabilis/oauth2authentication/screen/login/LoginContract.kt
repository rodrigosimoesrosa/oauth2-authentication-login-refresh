package br.com.mirabilis.oauth2authentication.screen.login

import br.com.mirabilis.oauth2authentication.base.BaseMVPPresenter
import br.com.mirabilis.oauth2authentication.base.BaseMVPView

/**
 * Created by rodrigosimoesrosa
 */
object LoginContract {

    interface LoginView : BaseMVPView {
        fun onSuccess()
        fun onFailed(e: String)
        fun onError(e: Throwable)
    }

    interface LoginPresenter : BaseMVPPresenter<LoginView> {
        fun login(email: String, password: String)
        fun cancel()
    }
}