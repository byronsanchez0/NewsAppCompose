package com.example.movieappcompose

import android.content.Context
import android.preference.PreferenceManager
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun LoginScreen(
    navHostController: NavHostController
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier.padding(16.dp)
        )
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.padding(16.dp)
        )
        Button(
            onClick = {
                val userLogged = performLogin(username, password)
                if (userLogged != null) {
                    onLoginSuccess(context, userLogged)
                    navHostController.navigate("main")
                } else {
                    // Show an error message or handle login failure
                }
            },
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Login")
        }
    }
}

fun performLogin(username: String, password: String): String? {
    var userLogged : String? = null
    // Replace this with your own authentication logic
     if (username == "admin" && password == "password") {
         userLogged = username
     }
    if (username == "manager" && password == "password") {
        userLogged = username
    }
    return userLogged
}

private fun onLoginSuccess(context: Context, username: String) {
    val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    // Save the login state using Shared Preferences
    sharedPreferences.edit().putBoolean("isLoggedIn", true).apply()
    if (username == "admin"){
        sharedPreferences.edit().putInt("userId", 1).apply()
    }
    if (username == "manager"){
        sharedPreferences.edit().putInt("userId", 2).apply()
    }
    // Start the main activity or perform any other action
    // intended after successful login
}