package com.example.androidsubmission

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListSukuAdapter(private val listSuku: ArrayList<Suku>) : RecyclerView.Adapter<ListSukuAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallBack : OnItemClickCallback

    fun setOnItemClickCallBack(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallBack = onItemClickCallback
    }

    interface OnItemClickCallback{
        abstract fun onItemClicked(data: Suku)
    }

    class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val imgPhoto: ImageView = itemView.findViewById(R.id.imageView)
        val tvName : TextView = itemView.findViewById(R.id.textTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder{
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ListViewHolder(view)

    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (id, name, description, photo) = listSuku[position]
        holder.tvName.text = name
        holder.imgPhoto.setImageResource(photo)
        holder.itemView.setOnClickListener {
            onItemClickCallBack.onItemClicked(listSuku[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = listSuku.size

}