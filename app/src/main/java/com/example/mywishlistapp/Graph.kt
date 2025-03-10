package com.example.mywishlistapp

import android.content.Context
import androidx.room.Room
import com.example.mywishlistapp.Data.WishDatabase
import com.example.mywishlistapp.Data.WishRepository

object Graph {
    lateinit var database: WishDatabase

    val wishRepository by lazy { WishRepository(wishDAO = database.wishdao()) }

    fun provide(context: Context){
        database = Room.databaseBuilder(context,WishDatabase::class.java,"wishlist.db").build()
    }
}