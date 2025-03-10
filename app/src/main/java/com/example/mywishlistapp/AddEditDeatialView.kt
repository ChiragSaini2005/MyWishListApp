package com.example.mywishlistapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.Shapes
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mywishlistapp.Data.Wish
import kotlinx.coroutines.launch

@Composable
fun AddEditDetailScreen(id: Long, viewModel: WishViewModel, navController: NavController) {

    val snackMessage = remember {
        mutableStateOf("")
    }
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    if(id != 0L){
        val wish = viewModel.getAWishById(id).collectAsState(initial = Wish(0L,"",""))
        viewModel.wishTitleState = wish.value.title
        viewModel.wishDescriptionState = wish.value.description
    }else{
        viewModel.wishTitleState = ""
        viewModel.wishDescriptionState = ""
    }

    Scaffold(
        topBar = {
            AppBarView(
                title = if (id != 0L) stringResource(id = R.string.update_wish) else stringResource(id = R.string.add_wish),
                {navController.navigateUp()})
        },
        scaffoldState = scaffoldState
    ) {
        Column(modifier = Modifier
            .padding(it)
            .wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            Spacer(modifier = Modifier.height(10.dp))
            WishTextField(label = "Title", value = viewModel.wishTitleState, onValueChanged = {viewModel.onWishTitleChanged(it)})
            Spacer(modifier = Modifier.height(10.dp))
            WishTextField(label = "Description", value = viewModel.wishDescriptionState, onValueChanged = {viewModel.onWishDescription(it)})
            Button(onClick = {
                if (viewModel.wishTitleState.isNotEmpty() && viewModel.wishDescriptionState.isNotEmpty()){
                    if(id != 0L){
                        viewModel.updateWish(Wish(id = id, title = viewModel.wishTitleState.trim(), description = viewModel.wishDescriptionState.trim()))
                    }else{
                        viewModel.addWish(Wish(title = viewModel.wishTitleState, description = viewModel.wishDescriptionState))
//                        snackMessage.value = "Wish has been created"
                    }

                }else{
//                    snackMessage.value = "Enter data to create a wish"
                }
                scope.launch {
//                    scaffoldState.snackbarHostState.showSnackbar(snackMessage.value)
                    navController.navigateUp()
                }
            }, modifier = Modifier.fillMaxWidth().padding(32.dp),
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.app_bar_color))
            ) {
                Text(
                    text = if (id != 0L) stringResource(id = R.string.update_wish)
                    else stringResource(id = R.string.add_wish),
                    style = TextStyle(fontSize = 18.sp)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WishTextField(
    label: String,
    value: String,
    onValueChanged: (String)->Unit
){
    androidx.compose.material.OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = value,
        onValueChange = onValueChanged,
        label = {Text(text = label,color = Color.Black)},
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        colors = androidx.compose.material.TextFieldDefaults.outlinedTextFieldColors(
            focusedLabelColor = colorResource(id = R.color.black),
            unfocusedLabelColor = colorResource(id = R.color.black),
            focusedBorderColor = colorResource(id = R.color.black),
            unfocusedBorderColor = colorResource(id = R.color.black),
            cursorColor = colorResource(id = R.color.black),
//            focusedTextColor = colorResource(id = R.color.black),
//            unfocusedTextColor = colorResource(id = R.color.black)
        )
    )
}

@Preview
@Composable
fun WishTextFieldPreview(){
    WishTextField(label = "Title", value = "ABC") {
        
    }
}