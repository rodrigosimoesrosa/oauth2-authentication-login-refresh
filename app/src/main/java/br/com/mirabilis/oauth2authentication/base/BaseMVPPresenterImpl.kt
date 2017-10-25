package br.com.mirabilis.oauth2authentication.base

import android.app.Activity
import android.content.Context
import android.support.v4.app.Fragment

/**
 * Created by rodrigosimoesrosa
 */
open class BaseMVPPresenterImpl<V : BaseMVPView> : BaseMVPPresenter<V> {

    protected var view: V? = null

    override fun attachView(view: V) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }

    protected fun call(view: V, function:() -> Unit){
        when(view){
            is Fragment -> (view as Fragment).activity.runOnUiThread { function() }
            is Activity -> (view as Activity).runOnUiThread { function() }
        }
    }

    protected fun <T> call(view: V, parameter: T, function:(parameter:T) -> Unit){
        when(view){
            is Fragment -> (view as Fragment).activity.runOnUiThread { function(parameter) }
            is Activity -> (view as Activity).runOnUiThread { function(parameter) }
        }
    }

    protected val context: Context = when(view){
        is Fragment -> (view as Fragment).activity
        is Activity -> (view as Activity)
        else -> throw Exception()
    }
}