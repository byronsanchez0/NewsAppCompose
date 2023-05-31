package com.example.movieappcompose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.movieappcompose.LoginViewModel
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    navHostController: NavHostController,
    viewModel: LoginViewModel,
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val scaffoldState = rememberScaffoldState()
    val snackbarCoroutineScope = rememberCoroutineScope()
    Scaffold(scaffoldState = scaffoldState) {
        Box(modifier = Modifier.padding(it))
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
                    val error = viewModel.login(username, password)
                    if (error != null) {
                        snackbarCoroutineScope.launch {
                            scaffoldState.snackbarHostState.showSnackbar(error)
                        }
                    } else {
                        navHostController.navigate("mainscreen")
                    }
//                val userLogged = performLogin(username, password)
//                if (userLogged != null) {
//                    onLoginSuccess(context, userLogged)
//                    navHostController.navigate("mainscreen")
//                } else {
//                    // Show an error message or handle login failure
//                }
                },
                modifier = Modifier.padding(16.dp)
            ) {
                Text("Login")
            }
            Button(
                onClick = {
                    navHostController.navigate("signup")
                },
                modifier = Modifier.padding(10.dp)
            ) {
                Text("Sign Up")
            }
        }
    }
}

fun performLogin(username: String, password: String): String? {
    var userLogged: String? = null
    // Replace this with your own authentication logic
    if (username == "admin" && password == "password") {
        userLogged = username
    }
    if (username == "manager" && password == "password") {
        userLogged = username
    }
    return userLogged
}
