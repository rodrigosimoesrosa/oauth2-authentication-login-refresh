package br.com.mirabilis.oauth2authentication.home

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.mirabilis.oauth2authentication.R

/**
 * A home screen that offers session for logged user.
 */
class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_home)
    }
}