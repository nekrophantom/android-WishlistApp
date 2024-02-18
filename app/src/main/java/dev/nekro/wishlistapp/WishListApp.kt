package dev.nekro.wishlistapp

import android.app.Application
import dev.nekro.wishlistapp.data.DataGraph

class WishListApp: Application() {

    override fun onCreate() {
        super.onCreate()
        DataGraph.provide(this)
    }

}