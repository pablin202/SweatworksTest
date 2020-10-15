package com.pdm.sweatworkstest.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pdm.sweatworkstest.core.domain.FavoriteUser
import com.pdm.sweatworkstest.databinding.ItemFavoriteUserBinding

class FavoritesUsersAdapter (
    private val onUserSelected: (FavoriteUser, ItemFavoriteUserBinding) -> Unit
) :
    RecyclerView.Adapter<FavoritesUsersAdapter.ViewHolder>() {
    private val users: MutableList<FavoriteUser> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = ItemFavoriteUserBinding.inflate(
            LayoutInflater.from(parent.context), parent,
            false
        )
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = users[position]
        holder.bind(user, onUserSelected)
    }

    override fun getItemCount(): Int = users.size

    fun addUsers(users: List<FavoriteUser>) {
        this.users.clear()
        this.users.addAll(users)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemFavoriteUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: FavoriteUser, listener: (FavoriteUser, ItemFavoriteUserBinding) -> Unit) {
            Glide.with(binding.root.context)
                .load(user.thumbnailImage)
                .into(binding.image)
            binding.root.setOnClickListener { onUserSelected(user, binding) }
        }
    }
}