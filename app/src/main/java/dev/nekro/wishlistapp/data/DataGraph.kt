package dev.nekro.wishlistapp.data

import android.content.Context
import androidx.room.Room
import dev.nekro.wishlistapp.data.local.WishDatabase
import dev.nekro.wishlistapp.data.repository.WishRepository

object DataGraph {

    private lateinit var database: WishDatabase

    val wishRepository by lazy {
        WishRepository(database.wishDao())
    }

    fun provide(context: Context){
        database = Room.databaseBuilder(context, WishDatabase::class.java, "wishlist.db").build()
    }

}