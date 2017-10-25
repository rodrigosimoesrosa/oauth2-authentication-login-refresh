package br.com.mirabilis.oauth2authentication.screen.home

import br.com.mirabilis.oauth2authentication.base.BaseMVPPresenter
import br.com.mirabilis.oauth2authentication.base.BaseMVPView

/**
 * Created by rodrigosimoesrosa
 */
object HomeContract {

    interface HomeView : BaseMVPView {
        fun onLogout()
    }

    interface HomePresenter : BaseMVPPresenter<HomeView> {
        fun signOut()
    }
}