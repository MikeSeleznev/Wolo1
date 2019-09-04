package com.wolo.a222.feature.auth.view.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.wolo.a222.R


class GamersAdapter(private val gamersList: MutableList<String>): RecyclerView.Adapter<ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.list_row, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return gamersList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (holder.title as AppCompatTextView).text = gamersList[position]

        val deleteUserOnClick = View.OnClickListener {
            gamersList.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, gamersList.size)
        }
        holder.buttonDelete.setOnClickListener(deleteUserOnClick)
    }
}

class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    var newUser = itemView.findViewById<View>(R.id.newUser)
    var title = itemView.findViewById<View>(R.id.title_row)!!
    var buttonDelete = itemView.findViewById<View>(R.id.delete_user)
}
