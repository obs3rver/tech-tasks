package pl.techgarden.tasks.tree;

import lombok.val;
import pl.techgarden.tasks.tree.growth.Length;
import pl.techgarden.tasks.tree.growth.TreeGrowthConfig;
import pl.techgarden.tasks.tree.growth.TreeGrowthInfo.BranchGrowthInfo;
import pl.techgarden.tasks.tree.growth.TreeGrowthInfo.TreePartGrowthInfo;
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

    TreePartGrowthInfo currentGrowthInfo() {
        return new BranchGrowthInfo(length());
    }

    @Override
    LengthGrowableTreePart createChildTreePart(TreeGrowthConfig<Length> treeGrowthConfig) {
        if (growthConfig.chooseIdenticalTypeOfNewChildTreePart()) {
            return Branch.of(treeGrowthConfig);
        } else {
            val leavesGrowthConfig = treeGrowthConfig.leavesGrowthConfig();

            if (leavesGrowthConfig.isNeedle()) {
                return NeedleLeaf.of(leavesGrowthConfig);
            } else {
                return Leaf.of(leavesGrowthConfig);
            }
        }
    }

}
