package com.bchmsl.homework9v2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import androidx.recyclerview.widget.LinearLayoutManager
import com.bchmsl.homework9v2.databinding.ActivityUsersBinding

class UsersActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUsersBinding
    private lateinit var adapter: UsersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    override fun onRestart() {
        super.onRestart()
        adapter.setData(newUsersList)
        d("TAG", "onRestart: $newUsersList")
    }

    private fun init() {
        setupRecycler()
    }

    private fun setupRecycler() {
        adapter = UsersAdapter()
        binding.rvUsers.adapter = adapter
        binding.rvUsers.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        listeners()
    }

    private fun listeners() {
        binding.btnAdd.setOnClickListener {
            addItem()
        }
        adapter.itemEditClick = { item, position ->
            editItem(position, item)
            d("TAG", "$position: $item")
        }
        adapter.itemRemoveClick = { item, position ->
            removeItem(position, item)
            d("TAG", "$position: $item")
        }

    }

    private fun addItem() {
        val intent = Intent(this, UserActivity::class.java)
        startActivity(intent)
    }

    private fun editItem(position: Int, user: User) {
        intent = Intent(this, UserActivity::class.java)
        intent.putExtra("itemPosition", position)
        intent.putExtra("user", user)
        startActivity(intent)
    }

    private fun removeItem(position: Int, item: User) {
        newUsersList = usersList.toMutableList()
        newUsersList.remove(item)
        adapter.setData(newUsersList)
    }
}