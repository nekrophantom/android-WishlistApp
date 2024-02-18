package dev.nekro.wishlistapp.data.repository

import dev.nekro.wishlistapp.data.local.WishDao
import dev.nekro.wishlistapp.domain.models.Wish
import kotlinx.coroutines.flow.Flow

class WishRepository (private val wishDao: WishDao) {

    suspend fun createWish(wish: Wish){
        wishDao.addWish(wish)
    }

    fun getAllWish(): Flow<List<Wish>> = wishDao.getAllWishes()

    fun getWishById(id: Long): Flow<Wish> {
        return wishDao.getWishById(id)
    }

    suspend fun updateWish(wish: Wish){
        wishDao.updateWish(wish)
    }

    suspend fun deleteWish(wish: Wish){
        wishDao.deleteWish(wish)
    }

}