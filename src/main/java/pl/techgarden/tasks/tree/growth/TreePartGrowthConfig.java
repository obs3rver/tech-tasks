package pl.techgarden.tasks.tree.growth;

import pl.techgarden.tasks.tree.domain.Age.Period;

public interface TreePartGrowthConfig<T extends GrowthTrait> {
    GrowingStrategy<T> growthStrategy();
    int increaseCountOfTreePartBy();
    int depthCount();
    Period increaseCountOfTreePartEvery();

    @FunctionalInterface
    interface GrowingStrategy<T extends GrowthTrait> {
        T grow(T growingType);
    }
}
