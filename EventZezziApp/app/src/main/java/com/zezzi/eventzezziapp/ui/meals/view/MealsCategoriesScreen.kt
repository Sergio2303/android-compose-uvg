package com.zezzi.eventzezziapp.ui.meals.view

import MealsCategoriesViewModel
import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.zezzi.eventzezziapp.data.networking.response.MealResponse
import com.zezzi.eventzezziapp.navigation.AppBar
import kotlinx.coroutines.launch

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.Divider
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.zezzi.eventzezziapp.navigation.Navigation

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MealsCategoriesScreen(
    navController: NavController,
    viewModel: MealsCategoriesViewModel = MealsCategoriesViewModel()
) {
    val rememberedMeals = remember { mutableStateListOf<MealResponse>() }
    val coroutineScope = rememberCoroutineScope()

    coroutineScope.launch {
        val response = viewModel.getMeals()
        val mealsFromTheApi = response?.categories
        rememberedMeals.addAll(mealsFromTheApi.orEmpty())
    }

    Scaffold(
        topBar = {
            AppBar(title = "Categorias", navController = navController)
        }
    ) {
        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            items(rememberedMeals) { meal ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(17.dp)
                        .background(Color(0xFFFBFBEC))
                        .aspectRatio(1f)
                        .clickable {
                            navController.navigate("${Navigation.FilteredMeals.route}/{category}")
                        }
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(17.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = meal.name,
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 19.sp
                            ),
                            modifier = Modifier.padding(top = 8.dp)
                        )
                        Image(
                            painter = rememberImagePainter(data = meal.imageUrl),
                            contentDescription = meal.name,
                            modifier = Modifier.size(120.dp)
                        )
                    }
                }
            }
        }
    }
}