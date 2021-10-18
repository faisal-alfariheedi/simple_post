package com.example.simple_post

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.simple_post.databinding.RvlistBinding
import java.util.ArrayList

class RVAdapter(var s: ArrayList<celep.dat>, var cont: Context): RecyclerView.Adapter<RVAdapter.ItemViewHolder>() {
    class ItemViewHolder(val binding: RvlistBinding): RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            RvlistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val rv = s[position]

        holder.binding.apply {
            rvlisting.text=rv.name
        }
    }
    override fun getItemCount() = s.size
}