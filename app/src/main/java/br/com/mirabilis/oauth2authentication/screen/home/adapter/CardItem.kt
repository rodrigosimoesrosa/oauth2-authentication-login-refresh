package br.com.mirabilis.oauth2authentication.screen.home.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import br.com.mirabilis.oauth2authentication.model.card.Card
import kotlinx.android.synthetic.main.card_list_item.view.*

/**
 * Created by rodrigosimoesrosa
 */
class CardItem(itemView: View,
               private val itemClick: (Card) -> Unit) : RecyclerView.ViewHolder(itemView) {

    fun bind(data: Card) {
        itemView.txtCardBin.text = data.balance?.bin
        itemView.txtCardNickName.text = data.balance?.nickName
        itemView.txtCardNumber.text = data.balance?.number
        itemView.txtCardValue.text = "R$ ${data.balance?.valueParsed}"
        itemView.setOnClickListener { itemClick(data) }
    }
}