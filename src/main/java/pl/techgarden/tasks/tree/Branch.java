package pl.techgarden.tasks.tree;

import pl.techgarden.tasks.tree.growth.Length;
import pl.techgarden.tasks.tree.growth.TreeGrowthConfig;
import pl.techgarden.tasks.tree.growth.TreeGrowthInfo;
import pl.techgarden.tasks.tree.growth.TreeGrowthInfo.BranchGrowthInfo;
import pl.techgarden.tasks.tree.growth.TreePartGrowthConfig;

class Branch extends AbstractTreePartNode {
    private Branch(TreeGrowthConfig<Length> treeGrowthConfig) {
        super(treeGrowthConfig);
    }

    static Branch of(TreeGrowthConfig<Length> treeGrowthConfig) {
        return new Branch(treeGrowthConfig);
    }

    @Override
    TreePartGrowthConfig<Length> determineGrowthConfigType() {
        return treeGrowthConfig.branchesGrowthConfig();
    }

    TreeGrowthInfo.TreePartGrowthInfo currentGrowthInfo() {
        return new BranchGrowthInfo(length());
    }

    @Override
    LengthGrowableNodeTreePart createChildTreePart(TreeGrowthConfig<Length> treeGrowthConfig) {
        if (growthConfig.chooseIdenticalTypeOfNewChildTreePart())
            return Branch.of(treeGrowthConfig);

        throw new UnsupportedOperationException();
    }

}
