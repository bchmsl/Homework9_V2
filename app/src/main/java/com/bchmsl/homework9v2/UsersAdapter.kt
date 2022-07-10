package com.bchmsl.homework9v2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bchmsl.homework9v2.databinding.LayoutUserBinding

typealias onEditClick = (user: User, position: Int) -> Unit
typealias onRemoveClick = (user: User, position: Int) -> Unit

class UsersAdapter : RecyclerView.Adapter<UsersAdapter.UsersViewHolder>() {

    lateinit var itemEditClick: onEditClick
    lateinit var itemRemoveClick: onRemoveClick

    inner class UsersViewHolder(val binding: LayoutUserBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder =
        UsersViewHolder(LayoutUserBinding.inflate(LayoutInflater.from(parent.context), parent, false))


    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        val currentItem = usersList[position]

        holder.binding.apply {
            tvId.text = currentItem.id.toString()
            val name = "${currentItem.firstName} ${currentItem.lastName}"
            tvName.text = name
            tvEmail.text = currentItem.email
        }
        holder.binding.btnEdit.setOnClickListener { itemEditClick(currentItem, position) }
        holder.binding.btnRemove.setOnClickListener { itemRemoveClick(currentItem, position) }
    }

    override fun getItemCount(): Int = usersList.size

    fun setData(newUsersList: MutableList<User>){
        val diffUtil = UsersDiffUtil(usersList, newUsersList)
        val diffResults = DiffUtil.calculateDiff(diffUtil)
        usersList = newUsersList
        diffResults.dispatchUpdatesTo(this@UsersAdapter)
    }
}