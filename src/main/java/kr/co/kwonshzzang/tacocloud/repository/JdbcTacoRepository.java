package kr.co.kwonshzzang.tacocloud.repository;

import kr.co.kwonshzzang.tacocloud.domain.Ingredient;
import kr.co.kwonshzzang.tacocloud.domain.Taco;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class JdbcTacoRepository implements TacoRepository {
    private final JdbcTemplate jdbc;

    @Override
    public Taco save(Taco taco) {
        long tacoId = saveTacoInfo(taco);
        taco.setId(tacoId);
        for(Ingredient ingredient: taco.getIngredients()) {
            saveIngredientToTaco(ingredient, tacoId);
        }
        return taco;
    }

    private long saveTacoInfo(Taco taco) {
        taco.setCreatedAt(new Date());

        PreparedStatementCreatorFactory pscFactory = new PreparedStatementCreatorFactory(
                "insert into Taco(name, createdAt) values (?, ?)", Types.VARCHAR, Types.TIMESTAMP
        );
        pscFactory.setReturnGeneratedKeys(true);

        PreparedStatementCreator psc = pscFactory.newPreparedStatementCreator(
                Arrays.asList(
                        taco.getName(),
                        new Timestamp(taco.getCreatedAt().getTime())
                )
        );

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(psc, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    private void saveIngredientToTaco(Ingredient ingredient, long tacoId) {
        jdbc.update(
                "insert into Taco_Ingredients(taco, ingredient) values (?, ?)",
                tacoId, ingredient.getId()
        );
    }

}
