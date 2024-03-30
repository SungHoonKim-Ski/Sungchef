package com.ssafy.ingredientservice.db.repository;


import com.ssafy.ingredientservice.db.entity.RecipeIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient, Integer> {
    List<RecipeIngredient> findRecipeIngredientsByRecipeId(Integer recipeId);
}
