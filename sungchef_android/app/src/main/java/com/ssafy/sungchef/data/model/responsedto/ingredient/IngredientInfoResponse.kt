package com.ssafy.sungchef.data.model.responsedto.ingredient

import com.google.gson.annotations.SerializedName

data class IngredientInfoResponse(
    @SerializedName("recipeIngredientDTOList", alternate = ["recipeIngredientList"])
    val recipeIngredientList: List<IngredientResponse>,
    val recipeIngredientType: String
)