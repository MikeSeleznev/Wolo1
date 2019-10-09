package com.wolo.a222.feature.auth.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.wolo.a222.R


class GamersAdapter(private val gamersList: MutableList<String>, private val callback: OnItemCallback): RecyclerView.Adapter<ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_row, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return gamersList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fullText = gamersList[position]
        val shortText = fullText.toCharArray()[0]
        (holder.title as AppCompatTextView).text = fullText
        holder.newUser.text = shortText.toString()
        val deleteUserOnClick = View.OnClickListener {
            callback.onDeleteItem(position)
           /* gamersList.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, gamersList.size)*/
        }
        holder.buttonDelete.setOnClickListener(deleteUserOnClick)
    }
}

class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    var newUser: Button = itemView.findViewById(R.id.newUser)
    var title = itemView.findViewById<View>(R.id.title_row)!!
    var buttonDelete: ImageButton = itemView.findViewById(R.id.delete_user)
}

interface OnItemCallback{
    fun onDeleteItem(item: Int)
}