package com.example.class3

import android.util.Log

object UserRepository {
    // Use a simple MutableList interface with a concrete implementation
    private val users: MutableList<User> = java.util.ArrayList()
    private var nextId = 1

    // Add a tag for logging
    private const val TAG = "UserRepository"

    // Initialize without any sample users
    init {
        Log.d(TAG, "UserRepository initialized")
    }

    fun addUser(user: User): User {
        val newUser = user.copy(id = nextId++)
        users.add(newUser)
        Log.d(TAG, "User added: $newUser, Total users: ${users.size}")
        return newUser
    }

    fun getAllUsers(): List<User> {
        Log.d(TAG, "Getting all users, count: ${users.size}")
        return users.toList()
    }

    fun getUserById(id: Int): User? {
        val user = users.find { it.id == id }
        Log.d(TAG, "Getting user by id: $id, found: ${user != null}")
        return user
    }

    // For debugging - clear all users
    fun clearAllUsers() {
        users.clear()
        nextId = 1
        Log.d(TAG, "All users cleared")
    }
}
