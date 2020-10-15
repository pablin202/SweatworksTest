package com.pdm.sweatworkstest.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pdm.sweatworkstest.core.domain.User
import com.pdm.sweatworkstest.databinding.ItemUserCardBinding
import java.util.ArrayList

class UserAdapter(
    private val onUserSelected: (User, ItemUserCardBinding) -> Unit,
    private val onAddContactClick: (User) -> Unit
) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>(), Filterable {

    private val users: MutableList<User> = mutableListOf()
    private val usersFiltered: MutableList<User> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = ItemUserCardBinding.inflate(
            LayoutInflater.from(parent.context), parent,
            false
        )
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = usersFiltered[position]
        holder.bind(user, onUserSelected)
    }

    override fun getItemCount(): Int = usersFiltered.size

    fun addUsers(users: List<User>) {
        this.users.addAll(users)
        this.usersFiltered.addAll(users)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemUserCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User, listener: (User, ItemUserCardBinding) -> Unit) {
            binding.textName.text = "${user.name.first} ${user.name.last}"
            Glide.with(binding.root.context)
                .load(user.picture.thumbnail)
                .into(binding.imagePicture)
            binding.btnAddContact.setOnClickListener { onAddContactClick(user) }
            binding.root.setOnClickListener { onUserSelected(user, binding) }
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val charString = charSequence.toString()
                if (charString.isEmpty()) {
                    usersFiltered.clear()
                    usersFiltered.addAll(users)
                } else {
                    val filteredList = ArrayList<User>()
                    for (row in users) {
                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.name.last.toLowerCase().contains(charString.toLowerCase()) || row.name.first.toLowerCase().contains(
                                charString.toLowerCase()
                            )
                        ) {
                            filteredList.add(row)
                        }
                    }

                    usersFiltered.clear()
                    usersFiltered.addAll(filteredList)
                }

                val filterResults = FilterResults()
                filterResults.values = usersFiltered
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
                usersFiltered.addAll(filterResults.values as ArrayList<User>)
                notifyDataSetChanged()
            }
        }
    }
}