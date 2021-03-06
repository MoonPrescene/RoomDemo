package com.example.roomdemo

import android.annotation.SuppressLint
import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdemo.database.UserDataBase
import com.example.roomdemo.databinding.ActivityMainBinding
import com.example.roomdemo.ui.adapters.ItemAdapter
import com.example.roomdemo.ui.models.User

class MainActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding

    private lateinit var useradapter: ItemAdapter

    private lateinit var items: List<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        items = UserDataBase.getUserDatabase(this@MainActivity)?.userDao()?.getListUser()!!

        setupAdapter()

        binding.apply {
            buttonAddUser.setOnClickListener {
                addUser()
            }

            buttonDeleteUser.setOnClickListener {
                deleteUser()
            }
        }

    }

    private fun deleteUser() {
        binding.apply {
            UserDataBase.getUserDatabase(this@MainActivity)?.userDao()?.deleteAll()!!
            useradapter.notifyDataSetChanged()
        }
    }


    @SuppressLint("NotifyDataSetChanged")
    private fun addUser() {
        binding.apply {
            val strUserName: String = editTextUsername.text.toString().trim()
            val strUserAddress: String = editTextUserAddress.text.toString().trim()

            if (strUserName.isEmpty() || strUserAddress.isEmpty()) return

            val user = User(0, strUserName, strUserAddress)
            UserDataBase.getUserDatabase(this@MainActivity)?.userDao()?.insertUser(user)

            Toast.makeText(this@MainActivity,
                "Add user successfully",
                Toast.LENGTH_SHORT).show()

            editTextUsername.text.clear()
            editTextUserAddress.text.clear()

            hideSoftKeyboard()

            // l???y gi?? tr??? cho items
            items = UserDataBase.getUserDatabase(this@MainActivity)?.userDao()?.getListUser()!!
            useradapter.notifyDataSetChanged()
        }

    }

    private fun hideSoftKeyboard() {
        try {
            val inputMethodManager: InputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE)
                    as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        }catch(ex: NullPointerException){
            ex.printStackTrace()
        }

    }

    private fun setupAdapter() {
        useradapter = ItemAdapter(items)
        binding.recyclerViewUser.apply {
            adapter = useradapter
            layoutManager = LinearLayoutManager(context)
        }

    }
}