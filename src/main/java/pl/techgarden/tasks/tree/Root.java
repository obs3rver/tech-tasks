package pl.techgarden.tasks.tree;

import pl.techgarden.tasks.tree.growth.Length;
import pl.techgarden.tasks.tree.growth.TreeGrowthInfo.RootGrowthInfo;
import pl.techgarden.tasks.tree.growth.TreeGrowthInfo.TreePartGrowthInfo;
import pl.techgarden.tasks.tree.growth.TreePartGrowthConfig;

class Root extends AbstractTreePartNode {

    private Root(TreePartGrowthConfig<Length> growthConfig) {
        super(growthConfig);
    }

    static Root of(TreePartGrowthConfig<Length> growthConfig) {
        return new Root(growthConfig);
    }

    TreePartGrowthInfo currentGrowthInfo() {
        return new RootGrowthInfo(length());
    }

    @Override
    LengthGrowableNodeTreePart createChildTreePart(TreePartGrowthConfig<Length> growthConfig) {
        return new Root(growthConfig);
    }

}
