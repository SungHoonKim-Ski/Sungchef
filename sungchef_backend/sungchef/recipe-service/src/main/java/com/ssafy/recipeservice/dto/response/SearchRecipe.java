package com.ssafy.recipeservice.dto.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SearchRecipe {
	int recipeId;
	String recipeName;
	String recipeImage;
	String recipeCookingTime;
	String recipeVolume;
	int recipeVisitCount;
	int recipeBookmarkCount;
	boolean isBookmark;
}
