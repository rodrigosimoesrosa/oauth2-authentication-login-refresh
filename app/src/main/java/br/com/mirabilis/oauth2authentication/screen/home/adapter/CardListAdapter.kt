package br.com.mirabilis.oauth2authentication.screen.home.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import br.com.mirabilis.oauth2authentication.R
import br.com.mirabilis.oauth2authentication.model.card.Card

/**
 * Created by rodrigosimoesrosa
 */
class CardListAdapter(private var context: Context,
                      private var lists: List<Card>,
                      private val itemClick: (Card) -> Unit)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder? {
        return CardItem(LayoutInflater.from(context).
                inflate(R.layout.card_list_item, parent, false), itemClick)
    }

    override fun getItemCount(): Int {
        return lists.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        (holder as CardItem).bind(lists[position])
    }
}