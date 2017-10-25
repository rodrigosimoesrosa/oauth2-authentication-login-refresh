package br.com.mirabilis.oauth2authentication.screen.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import br.com.mirabilis.oauth2authentication.R
import br.com.mirabilis.oauth2authentication.base.BaseMVPActivity
import br.com.mirabilis.oauth2authentication.screen.login.LoginActivity
import kotlinx.android.synthetic.main.activity_home.*

/**
 * A home screen that offers session for logged user.
 */
class HomeActivity : BaseMVPActivity<HomeContract.HomeView, HomeContract.HomePresenter>(),
        HomeContract.HomeView {

    override var presenter: HomeContract.HomePresenter = HomePresenterImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_home)

        btnSignOut.setOnClickListener {
            btnSignOut.visibility = View.GONE
            progressBar.visibility = View.VISIBLE
            recyclerView.visibility = View.GONE

            presenter.signOut()
        }
    }

    override fun onLogout() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}