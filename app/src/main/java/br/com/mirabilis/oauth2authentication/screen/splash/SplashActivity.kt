package br.com.mirabilis.oauth2authentication.screen.splash

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import br.com.mirabilis.oauth2authentication.R
import br.com.mirabilis.oauth2authentication.base.BaseMVPActivity
import br.com.mirabilis.oauth2authentication.screen.home.HomeActivity
import br.com.mirabilis.oauth2authentication.screen.login.LoginActivity
import kotlinx.android.synthetic.main.activity_splash.*

/**
 * A Splash screen that offers loading of session for user.
 */
class SplashActivity: BaseMVPActivity<SplashContract.SplashView,
        SplashContract.SplashPresenter>(), SplashContract.SplashView {

    override var presenter: SplashContract.SplashPresenter = SplashPresenterImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)

        presenter.isAuthenticated()
    }

    override fun onSuccess() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onMessage(e: String) {
        txtMessage.text = e
    }

    override fun onLogin() {
        showLogin()
    }

    private fun showLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onError(e: Throwable) {
        Snackbar.make(container, e.message.toString(), Snackbar.LENGTH_LONG)
        showLogin()
    }
}