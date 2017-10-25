package br.com.mirabilis.oauth2authentication.splash

import br.com.mirabilis.oauth2authentication.util.Authentication
import br.com.mirabilis.oauth2authentication.R
import br.com.mirabilis.oauth2authentication.api.auth.RefreshFetcher
import br.com.mirabilis.oauth2authentication.base.BaseMVPPresenterImpl
import br.com.mirabilis.oauth2authentication.model.oauth.request.Refresh
import br.com.mirabilis.oauth2authentication.model.oauth.Token

class SplashPresenterImpl : BaseMVPPresenterImpl<SplashContract.SplashView>(),
        SplashContract.SplashPresenter {

    private var refreshFetcher: RefreshFetcher.RefreshFetcherImpl? = null

    override fun isAuthenticated() {
        try {
            if(Authentication.isAuthenticated(context)){
                view?.let { view -> call(view, view::onSuccess) }
            } else {
                view?.let { view -> call(view,
                        context.getString(R.string.authenticating),
                        view::onMessage)
                }
                refreshFetcher = RefreshFetcher.RefreshFetcherImpl(context,
                        object : RefreshFetcher.Listener {

                            override fun onSuccess(token: Token?) {
                                if(token == null){
                                    view?.let { view -> call(view, view::onLogin) }
                                }else{
                                    /**
                                     * Do something like call api
                                     */
                                    Authentication.save(context, token)
                                    view?.let { view -> call(view, view::onSuccess) }
                                }
                            }

                            override fun onError(throwable: Throwable) {
                                view?.let { view -> call(view, throwable, view::onError) }
                            }
                        })
                refreshFetcher?.refresh(Refresh(Authentication.getRefresh(context)))
            }
        } catch (e: Authentication.WithoutAuthenticatedException) {
            view?.let { view -> call(view, view::onLogin) }
        }
    }

    override fun cancel() {
        refreshFetcher?.cancel()
    }
}