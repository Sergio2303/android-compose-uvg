package com.zezzi.eventzezziapp.data.networking
import com.google.gson.annotations.SerializedName
import com.zezzi.eventzezziapp.data.networking.response.MealResponse

data class ResponseS(val meals: List<Meal>) {

}

data class Meal(
    @SerializedName("idCategory") val id: String,
    @SerializedName("strCategory") val name: String,
    @SerializedName("strCategoryDescription") val description: String,
    @SerializedName("strCategoryThumb") val imageUrl: String
)