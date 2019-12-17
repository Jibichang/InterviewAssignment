package com.wongnai.android.assignment.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.facebook.drawee.view.SimpleDraweeView
import com.wongnai.android.assignment.R
import com.wongnai.android.assignment.model.Coin
import com.wongnai.android.assignment.utils.load

class CoinsAdapter(private var data: MutableList<Coin>) : RecyclerView.Adapter<CoinsAdapter.ViewHolder>(){
    override fun getItemCount() = data.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])

        holder.itemView.setOnClickListener {view : View ->
            view.findNavController().navigate(MainFragmentDirections.actionMainFragmentToCoinInfoFragment(data[position].id!!.toLong()))
            Navigation.createNavigateOnClickListener(R.id.action_mainFragment_to_coinInfoFragment)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_item_coin, parent, false))
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val nameTextView = view.findViewById<TextView>(R.id.nameTextView)
        private val descriptionTextView = view.findViewById<TextView>(R.id.descriptionTextView)
        private val priceTextView = view.findViewById<TextView>(R.id.priceTextView)
        private val iconImageView = view.findViewById<SimpleDraweeView>(R.id.iconImageView)

        fun bind(item :Coin?){
            apply {
                nameTextView.text = item?.name
                descriptionTextView.text = item?.description
                priceTextView.text = item?.price
                iconImageView.load(item?.iconUrl)
            }
        }

        companion object {

        }
    }
}