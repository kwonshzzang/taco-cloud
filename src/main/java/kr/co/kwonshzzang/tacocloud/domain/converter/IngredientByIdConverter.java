package kr.co.kwonshzzang.tacocloud.domain.converter;

import kr.co.kwonshzzang.tacocloud.domain.Ingredient;
import kr.co.kwonshzzang.tacocloud.repository.IngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IngredientByIdConverter implements Converter<String, Ingredient> {
    private final IngredientRepository ingredientRepository;

    @Override
    public Ingredient convert(String id) {
        return ingredientRepository.findById(id);
    }
}
