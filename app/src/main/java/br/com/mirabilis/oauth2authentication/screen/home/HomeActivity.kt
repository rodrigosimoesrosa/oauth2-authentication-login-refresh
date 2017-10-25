package br.com.mirabilis.oauth2authentication.screen.home

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import br.com.mirabilis.oauth2authentication.R
import br.com.mirabilis.oauth2authentication.base.BaseMVPActivity
import br.com.mirabilis.oauth2authentication.model.card.Card
import br.com.mirabilis.oauth2authentication.screen.home.adapter.CardListAdapter
import br.com.mirabilis.oauth2authentication.screen.login.LoginActivity
import kotlinx.android.synthetic.main.activity_home.*

/**
 * A home screen that offers session for logged user.
 */
class HomeActivity : BaseMVPActivity<HomeContract.HomeView, HomeContract.HomePresenter>(),
        HomeContract.HomeView {

    override var presenter: HomeContract.HomePresenter = HomePresenterImpl()

    private lateinit var adapter: CardListAdapter
    private val list: MutableList<Card> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_home)

        build()
        btnSignOut.setOnClickListener {
            btnSignOut.visibility = View.GONE
            progressBar.visibility = View.VISIBLE
            recyclerView.visibility = View.GONE

            presenter.signOut()
        }
        presenter.loadCards()
    }

    private fun build() {
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = CardListAdapter(this, list, {
            Snackbar.make(container, "Card clicked $it.balance.number", Snackbar.LENGTH_LONG)
        })

        recyclerView.adapter = adapter
    }

    override fun onSuccess(list: MutableList<Card>) {
        progressBar.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
        this.list.addAll(list)
        adapter.notifyDataSetChanged()
    }

    override fun onError(e: Throwable) {
        Snackbar.make(container, e.message.toString(), Snackbar.LENGTH_LONG)
    }

    override fun onLogout() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}