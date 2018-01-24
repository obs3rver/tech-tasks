package pl.techgarden.tasks.tree;

import pl.techgarden.tasks.tree.growth.Length;
import pl.techgarden.tasks.tree.growth.TreeGrowthConfig;
import pl.techgarden.tasks.tree.growth.TreeGrowthInfo.RootGrowthInfo;
import pl.techgarden.tasks.tree.growth.TreeGrowthInfo.TreePartGrowthInfo;
import pl.techgarden.tasks.tree.growth.TreePartGrowthConfig;

class Root extends AbstractTreePartNode {

    private Root(TreeGrowthConfig<Length> treeGrowthConfig) {
        super(treeGrowthConfig);
    }

    static Root of(TreeGrowthConfig<Length> treeGrowthConfig) {
        return new Root(treeGrowthConfig);
    }

    @Override
    TreePartGrowthConfig<Length> determineGrowthConfigType() {
        return treeGrowthConfig.rootsGrowthConfig();
    }

    TreePartGrowthInfo currentGrowthInfo() {
        return new RootGrowthInfo(length());
    }

    @Override
    LengthGrowableNodeTreePart createChildTreePart(TreeGrowthConfig<Length> treeGrowthConfig) {
        return Root.of(treeGrowthConfig);
    }

}
