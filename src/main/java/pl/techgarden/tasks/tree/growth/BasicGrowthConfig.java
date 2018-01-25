package pl.techgarden.tasks.tree.growth;

import pl.techgarden.tasks.tree.domain.Age.Period;

public interface BasicGrowthConfig<T extends GrowthTrait> {
    GrowingStrategy<T> growthStrategy();
    Period increaseCountOfTreePartEvery();

    @FunctionalInterface
    interface GrowingStrategy<T extends GrowthTrait> {
        T grow(T growingType);
    }
}
