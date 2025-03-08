package com.example.mywishlistapp.Data

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

class WishRepository(private val wishDAO: WishDAO) {

    suspend fun addAWish(wish: Wish){
        wishDAO.addAWish(wish)
    }

    fun getAllWish(): Flow<List<Wish>> = wishDAO.getAllWish()

    suspend fun deleteAWish(wish: Wish){
        wishDAO.deleteAWish(wish)
    }

    suspend fun updateAWish(wish: Wish){
        wishDAO.updateAWish(wish)
    }

    fun getAWishById(id:Long): Flow<Wish> = wishDAO.getAWishById(id)
}