package dev.nekro.wishlistapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.nekro.wishlistapp.domain.models.Wish

@Database(
    entities = [Wish::class],
    version = 1,
    exportSchema = false
)
abstract class WishDatabase: RoomDatabase() {
    abstract fun wishDao(): WishDao
}