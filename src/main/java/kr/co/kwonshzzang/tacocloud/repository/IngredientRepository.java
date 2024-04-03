package kr.co.kwonshzzang.tacocloud.repository;

import kr.co.kwonshzzang.tacocloud.domain.Ingredient;

public interface IngredientRepository {
    Iterable<Ingredient> findAll();
    Ingredient findById(String id);
    Ingredient save(Ingredient ingredient);
}
