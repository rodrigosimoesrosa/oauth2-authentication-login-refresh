package br.com.mirabilis.oauth2authentication.base

/**
 * Created by rodrigosimoesrosa
 */
interface BaseMVPPresenter<in V : BaseMVPView> {
    fun attachView(view: V)
    fun detachView()
}