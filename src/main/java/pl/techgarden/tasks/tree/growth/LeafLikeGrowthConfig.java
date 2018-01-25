package pl.techgarden.tasks.tree.growth;

import lombok.Builder;
import lombok.Value;
import lombok.experimental.Accessors;
import pl.techgarden.tasks.tree.domain.Age.Period;

public interface LeafLikeGrowthConfig<T extends GrowthTrait> extends BasicGrowthConfig<T> {
    boolean isNeedle();

    @Value
    @Accessors(fluent = true)
    @Builder
    class LengthLeafLikeGrowthConfig implements LeafLikeGrowthConfig<Length> {
        GrowingStrategy<Length> growthStrategy;
        Period increaseCountOfTreePartEvery;
        boolean isNeedle;
    }
}
