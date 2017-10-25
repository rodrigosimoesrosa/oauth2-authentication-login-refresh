package br.com.mirabilis.oauth2authentication.screen.home

import br.com.mirabilis.oauth2authentication.base.BaseMVPPresenterImpl
import br.com.mirabilis.oauth2authentication.util.Authentication

class HomePresenterImpl : BaseMVPPresenterImpl<HomeContract.HomeView>(),
        HomeContract.HomePresenter {

    override fun signOut() {
        Authentication.delete(getContext())

        view?.let { view -> call(view, view::onLogout)}
    }
}