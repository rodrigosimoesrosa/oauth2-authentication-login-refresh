package br.com.mirabilis.oauth2authentication.screen.login

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.TextInputEditText
import android.view.View
import br.com.mirabilis.oauth2authentication.R
import br.com.mirabilis.oauth2authentication.base.BaseMVPActivity
import br.com.mirabilis.oauth2authentication.screen.home.HomeActivity
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
            if(isValid()){
                loading(true)

                presenter.login(txtEmail.text.toString(), txtPassword.text.toString())
            }
        }
    }

    private fun isValid(): Boolean {
        if(txtEmail?.text.toString().isNullOrEmpty()) return false
        if(txtPassword?.text.toString().isNullOrEmpty()) return false
        return true
    }

    override fun onSuccess() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun loading(value: Boolean){
        if(value) progressBar.visibility = View.VISIBLE
        else progressBar.visibility = View.GONE

        if(!value){
            txtEmail.text.clear()
            txtPassword.text.clear()
        }

        if(!value) inputLayoutEmail.visibility = View.VISIBLE
        else inputLayoutEmail.visibility = View.GONE

        if(!value) inputLayoutPassword.visibility = View.VISIBLE
        else inputLayoutPassword.visibility = View.GONE
    }

    override fun onFailed(e: String) {
        loading(false)
        Snackbar.make(container, e, Snackbar.LENGTH_LONG)
    }

    override fun onError(e: Throwable) {
        loading(false)
        Snackbar.make(container, e.message.toString(), Snackbar.LENGTH_LONG)
    }
}
