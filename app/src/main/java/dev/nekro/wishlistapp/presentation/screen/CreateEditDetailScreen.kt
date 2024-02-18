package dev.nekro.wishlistapp.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import dev.nekro.wishlistapp.domain.models.Wish
import dev.nekro.wishlistapp.presentation.components.AppBar
import dev.nekro.wishlistapp.presentation.components.WishTextField
import dev.nekro.wishlistapp.presentation.viewmodel.WishViewModel
import kotlinx.coroutines.launch

@Composable
fun CreateEditDetailScreen(
    id: Long,
    viewModel: WishViewModel,
    navController: NavController
) {

    val snackMessage = remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    if (id != 0L) {
        val wish = viewModel.getWishById(id).collectAsState(initial = Wish(0L, "", ""))
        viewModel.onWishTitleChanged(wish.value.title)
        viewModel.onWishDescriptionChanged(wish.value.description)
    } else {
        viewModel.onWishTitleChanged("")
        viewModel.onWishDescriptionChanged("")
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AppBar(title = if (id != 0L) "Edit Wish" else "Add Wish")
            {
                navController.navigateUp()
            }
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(10.dp))

            WishTextField(
                label = "Title",
                value = viewModel.wishTitleState.value,
                onValueChanged = { newTitle ->
                    viewModel.onWishTitleChanged(newTitle)
                }
            )

            Spacer(modifier = Modifier.height(10.dp))

            WishTextField(
                label = "Description",
                value = viewModel.wishDescriptionState.value,
                onValueChanged = { newDescription ->
                    viewModel.onWishDescriptionChanged(newDescription)
                }
            )

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = {
                    if (viewModel.wishTitleState.value.isNotEmpty() && viewModel.wishDescriptionState.value.isNotEmpty()) {
                        if (id != 0L) {
                            // Todo Update
                            viewModel.updateWish(
                                Wish(
                                    id = id,
                                    title = viewModel.wishTitleState.value.trim(),
                                    description = viewModel.wishDescriptionState.value.trim()
                                )
                            )
                        } else {
                            // Todo Create
                            viewModel.createWish(
                                Wish(
                                    title = viewModel.wishTitleState.value.trim(),
                                    description = viewModel.wishDescriptionState.value.trim()
                                )
                            )
                            snackMessage.value = "Wish has been created"
                        }
                    } else {
                        snackMessage.value = "Enter fields to create a wish"
                    }

                    scope.launch {
                        scaffoldState.snackbarHostState.showSnackbar(snackMessage.value)
                        navController.navigateUp()
                    }
                }
            ) {
                Text(
                    text = if (id != 0L) "Edit Wish" else "Add Wish",
                    style = TextStyle(
                        fontSize = 18.sp
                    )
                )
            }
        }
    }

}