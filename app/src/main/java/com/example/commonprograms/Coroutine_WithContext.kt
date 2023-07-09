package com.example.commonprograms

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

fun main() {
    fetchAndShowUser()
}

fun fetchAndShowUser() {
    GlobalScope.launch(Dispatchers.Main) {
        val user = fetchUser() // fetch on IO thread
        showUser(user) // back on UI thread
    }
}

suspend fun fetchUser(): String {
    return withContext(Dispatchers.IO) {
        return@withContext "Vishal"
    }
}

fun showUser(user: String) {
    // show user
    print("My name: $user")
}