package pl.techgarden.tasks.tree.growth;

public interface TreePartGrowthConfig<T extends GrowthTrait> extends BasicGrowthConfig<T> {
    int increaseCountOfTreePartBy();
    int depthCount();
    boolean chooseIdenticalTypeOfNewChildTreePart();
}
