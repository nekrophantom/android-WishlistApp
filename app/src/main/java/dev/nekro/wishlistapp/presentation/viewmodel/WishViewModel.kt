package dev.nekro.wishlistapp.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.nekro.wishlistapp.data.DataGraph
import dev.nekro.wishlistapp.data.repository.WishRepository
import dev.nekro.wishlistapp.domain.models.Wish
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class WishViewModel(private val wishRepository: WishRepository = DataGraph.wishRepository): ViewModel() {

    private val _wishTitleState = mutableStateOf("")
    val wishTitleState: State<String> = _wishTitleState

    private val _wishDescriptionState = mutableStateOf("")
    val wishDescriptionState: State<String> = _wishDescriptionState



    fun onWishTitleChanged(newString: String) {
        _wishTitleState.value = newString
    }

    fun onWishDescriptionChanged(newString: String) {
        _wishDescriptionState.value = newString
    }


    lateinit var getAllWish: Flow<List<Wish>>

    init {
        viewModelScope.launch {
            getAllWish = wishRepository.getAllWish()
        }
    }

    fun createWish(wish: Wish){
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.createWish(wish)
        }
    }

    fun getWishById(id: Long): Flow<Wish> {
        return wishRepository.getWishById(id)
    }

    fun updateWish(wish: Wish){
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.updateWish(wish)
        }
    }

    fun deleteWish(wish: Wish){
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.deleteWish(wish)
        }
    }

}