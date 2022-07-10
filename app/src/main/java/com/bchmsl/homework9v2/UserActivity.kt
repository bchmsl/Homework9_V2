package com.bchmsl.homework9v2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import com.bchmsl.homework9v2.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserBinding
    private lateinit var user: User
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        val position = intent.getIntExtra("itemPosition", -1)
        if (position == -1) {
            addItem()
        } else {
            user = intent.getParcelableExtra<User>("user") as User
            editItem(position, user)
        }
    }

    private fun editItem(position: Int, user: User) {
        binding.etFirstName.setText(user.firstName)
        binding.etLastName.setText(user.lastName)
        binding.etEmail.setText(user.email)

        binding.btnSave.setOnClickListener {
            newUsersList = usersList.toMutableList()
            d("TAG", newUsersList.toString())
            newUsersList[position].apply {
                firstName = binding.etFirstName.text.toString()
                lastName = binding.etLastName.text.toString()
                email = binding.etEmail.text.toString()
            }
            d("TAG", newUsersList.toString())

            finish()
        }
    }


    private fun addItem() {
        binding.btnSave.setOnClickListener {
            val newUser = User(
                usersList[usersList.lastIndex].id + 1,
                binding.etFirstName.text.toString(),
                binding.etLastName.text.toString()
            )
            newUsersList = usersList.toMutableList()
            newUsersList.add(newUser)
            finish()
        }
    }
}