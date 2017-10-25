package br.com.mirabilis.oauth2authentication.login

import br.com.mirabilis.oauth2authentication.R
import br.com.mirabilis.oauth2authentication.api.APISettings
import br.com.mirabilis.oauth2authentication.api.auth.AuthAPI
import br.com.mirabilis.oauth2authentication.api.base.RetrofitCreator
import br.com.mirabilis.oauth2authentication.base.BaseMVPPresenterImpl
import br.com.mirabilis.oauth2authentication.model.oauth.Auth
import br.com.mirabilis.oauth2authentication.model.oauth.Token
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginPresenterImpl : BaseMVPPresenterImpl<LoginContract.LoginView>(),
        LoginContract.LoginPresenter {

    override fun login(email: String, password: String) {
        val authFetcher = RetrofitCreator(AuthAPI::class.java, APISettings.base).generate()
        val callback = authFetcher.auth(Auth(email, password))
        callback.enqueue(object : Callback<Token> {
            override fun onResponse(call: Call<Token>?, response: Response<Token>?) {
                if(response != null){
                    if(response.isSuccessful){
                        //TODO save token
                        view?.let { view -> call(view, true, view::onLogged)}
                    }else{
                        view?.let { view -> call(view, false, view::onLogged)}
                    }
                }else{
                    view?.let { view -> call(view,
                            Throwable(context.getString(R.string.auth_error)),
                            view::onLoginError)
                    }
                }
            }

            override fun onFailure(call: Call<Token>?, t: Throwable?) {
                val msg = context.getString(R.string.auth_error)
                view?.let { view -> call(view, Throwable("$msg : ${t?.message}"), view::onLoginError) }
            }
        })
    }

    override fun cancel() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}