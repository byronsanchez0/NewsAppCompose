package com.example.movieappcompose.screens.login

import android.content.Context
import android.icu.text.SimpleDateFormat
import androidx.lifecycle.ViewModel
import com.example.movieappcompose.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.Date

class LoginViewModel(private val context: Context) : ViewModel() {

    fun login(username: String, password: String): String? {
        if (username.isBlank() || password.isBlank()) {
            return "Username or Password is empty"
        }
        val sharedPreferences = context.getSharedPreferences("sp", Context.MODE_PRIVATE)
        val usersJson = sharedPreferences?.getString("users", "")
        if (usersJson.isNullOrBlank()) {
            return "There are not User Register"
        }
        val listType = object : TypeToken<ArrayList<User>>() {}.type
        val gson = Gson()
        val users: ArrayList<User> = gson.fromJson(usersJson, listType)
        users.forEach { user ->
            if (user.username == username && user.password == password) {
                onLoginSuccess(user)
                return null
            }
        }
        return "Username or Password are incorrect"
    }

    private fun onLoginSuccess(user: User) {
        val sharedPreferences = context.getSharedPreferences("sp", Context.MODE_PRIVATE)
        sharedPreferences.edit().putInt("userId", user.id).apply()
        sharedPreferences.edit().putBoolean("isLoggedIn", true).apply()
        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val currentDate = sdf.format(Date())
        sharedPreferences.edit().putString("connection", currentDate).apply()
    }
}
