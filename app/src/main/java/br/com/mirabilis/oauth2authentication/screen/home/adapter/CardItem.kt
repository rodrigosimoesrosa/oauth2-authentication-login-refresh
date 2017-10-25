package br.com.mirabilis.oauth2authentication.screen.home.adapter

import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.view.View
import br.com.mirabilis.androidmvpkotlin.R
import br.com.mirabilis.androidmvpkotlin.extensions.toCalendar
import br.com.mirabilis.androidmvpkotlin.model.GameData
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.game_list_item.view.*
import java.util.*

/**
 * Created by rodrigosimoesrosa
 */
class GameItem(itemView: View, private val itemClick: (GameData.Game) -> Unit) : RecyclerView.ViewHolder(itemView) {

    fun bind(data: GameData.Game) {
        Glide.with(itemView.context)
                .load(data.image?.mediumUrl)
                .apply(RequestOptions.errorOf(R.drawable.no_image))
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(e: GlideException?,
                                              model: Any?,
                                              target: Target<Drawable>?,
                                              isFirstResource: Boolean): Boolean {


                        itemView.progress.visibility = View.GONE
                        return false
                    }

                    override fun onResourceReady(resource: Drawable?,
                                                 model: Any?,
                                                 target: Target<Drawable>?,
                                                 dataSource: DataSource?,
                                                 isFirstResource: Boolean): Boolean {

                        itemView.progress.visibility = View.GONE
                        return false
                    }

                }).into(itemView.imgGame)

        itemView.txtGameName.text = data.name
        itemView.txtGameYear.text = when (data.originalReleaseDate) {
            null -> "-"
            else -> data.originalReleaseDate?.toCalendar()?.get(Calendar.YEAR).toString()
        }
        itemView.setOnClickListener { itemClick(data) }
    }
}