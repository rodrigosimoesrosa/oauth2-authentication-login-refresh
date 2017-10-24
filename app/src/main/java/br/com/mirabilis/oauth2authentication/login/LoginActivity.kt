package br.com.mirabilis.oauth2authentication.login

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.TextInputEditText
import br.com.mirabilis.oauth2authentication.R
import br.com.mirabilis.oauth2authentication.base.BaseMVPActivity
import br.com.mirabilis.oauth2authentication.home.HomeActivity
import kotlinx.android.synthetic.main.activity_login.*

/**
 * A login screen that offers login via email/password.
 */
class LoginActivity : BaseMVPActivity<LoginContract.LoginView, LoginContract.LoginPresenter>(),
        LoginContract.LoginView {

    override var presenter: LoginContract.LoginPresenter = LoginPresenterImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        btnSign.setOnClickListener {
            if(isValid(txtEmail, txtPassword)){
                presenter.login(txtEmail.text.toString(), txtPassword.text.toString())
            }
        }
    }

    private fun isValid(txtEmail: TextInputEditText?, txtPassword: TextInputEditText?): Boolean {
        if(txtEmail?.text.toString().isNullOrEmpty()) return false
        if(txtPassword?.text.toString().isNullOrEmpty()) return false
        return true
    }

    override fun onLogged() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

    override fun onLoginError(e: Exception) {
        Snackbar.make(container, e.message.toString(), Snackbar.LENGTH_LONG)
    }
}
