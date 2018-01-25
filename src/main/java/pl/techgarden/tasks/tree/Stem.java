package pl.techgarden.tasks.tree;

import pl.techgarden.tasks.tree.growth.Length;
import pl.techgarden.tasks.tree.growth.TreeGrowthConfig;
import pl.techgarden.tasks.tree.growth.TreeGrowthInfo.StemGrowthInfo;
import pl.techgarden.tasks.tree.growth.TreeGrowthInfo.TreePartGrowthInfo;
import pl.techgarden.tasks.tree.growth.TreePartGrowthConfig;

class Stem extends AbstractTreePartNode {
    private Stem(TreeGrowthConfig<Length> treeGrowthConfig) {
        super(treeGrowthConfig);
    }

    static Stem of(TreeGrowthConfig<Length> treeGrowthConfig) {
        return new Stem(treeGrowthConfig);
    }

    @Override
    TreePartGrowthConfig<Length> determineGrowthConfigType() {
        return treeGrowthConfig.stemsGrowthConfig();
    }

    TreePartGrowthInfo currentGrowthInfo() {
        return new StemGrowthInfo(length());
    }

    @Override
    LengthGrowableNodeTreePart createChildTreePart(TreeGrowthConfig<Length> treeGrowthConfig) {
        if (growthConfig.chooseIdenticalTypeOfNewChildTreePart()) {
            return Stem.of(treeGrowthConfig);
        } else {
            return Branch.of(treeGrowthConfig);
        }
    }

}
