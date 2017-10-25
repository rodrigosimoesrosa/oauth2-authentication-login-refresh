package br.com.mirabilis.oauth2authentication.login

import br.com.mirabilis.oauth2authentication.R
import br.com.mirabilis.oauth2authentication.api.auth.AuthFetcher
import br.com.mirabilis.oauth2authentication.base.BaseMVPPresenterImpl
import br.com.mirabilis.oauth2authentication.model.oauth.Token

class LoginPresenterImpl : BaseMVPPresenterImpl<LoginContract.LoginView>(),
        LoginContract.LoginPresenter {

    private var authFetcher: AuthFetcher.AuthFetcherImpl?= null

    override fun login(email: String, password: String) {
        authFetcher = AuthFetcher.AuthFetcherImpl(context, object : AuthFetcher.Listener {
            override fun onSuccess(token: Token?) {
                if(token == null) {
                    view?.let { view -> call(view,
                            context.getString(R.string.auth_invalid),
                            view::onFailed)
                    }
                } else {
                    //TODO save token
                    view?.let { view -> call(view, view::onSuccess)}
                }
            }
            override fun onError(throwable: Throwable) {
                view?.let { view -> call(view, throwable, view::onError) }
            }
        })
        authFetcher?.auth(email, password)
    }

    override fun cancel() {
        authFetcher?.cancel()
    }
}