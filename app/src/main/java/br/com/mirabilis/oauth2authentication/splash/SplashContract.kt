package br.com.mirabilis.oauth2authentication.splash

import br.com.mirabilis.oauth2authentication.base.BaseMVPPresenter
import br.com.mirabilis.oauth2authentication.base.BaseMVPView

/**
 * Created by rodrigosimoesrosa
 */
object SplashContract {

    interface SplashView : BaseMVPView {
        fun onSuccess()
        fun onMessage(e: String)
        fun onLogin()
        fun onError(e: Throwable)
    }

    interface SplashPresenter : BaseMVPPresenter<SplashView> {
        fun isAuthenticated()
        fun cancel()
    }
}