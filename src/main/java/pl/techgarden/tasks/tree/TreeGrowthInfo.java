package pl.techgarden.tasks.tree;

import lombok.Builder;
import lombok.Value;
import pl.techgarden.tasks.tree.domain.Age;

@Value
@Builder
public class TreeGrowthInfo {
    Age age;

    public static TreeGrowthInfo EMPTY =
            TreeGrowthInfo.builder()
                    .age(Age.ZERO)
                    .build();
}
