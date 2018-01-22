package pl.techgarden.tasks.tree.growth;

public interface TreeGrowthConfig<T extends GrowthTrait> {
    TreePartGrowthConfig<T> rootsGrowthConfig();
    TreePartGrowthConfig<T> stemsGrowthConfig();
    TreePartGrowthConfig<T> branchesGrowthConfig();
    TreePartGrowthConfig<T> leavesGrowthConfig();
}
