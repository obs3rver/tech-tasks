package pl.techgarden.tasks.tree;

import pl.techgarden.tasks.tree.growth.LeafLikeGrowthConfig;
import pl.techgarden.tasks.tree.growth.Length;
import pl.techgarden.tasks.tree.growth.TreeGrowthInfo;
import pl.techgarden.tasks.tree.growth.TreeGrowthInfo.TreePartGrowthInfo;

class NeedleLeaf extends LeafLike {

    NeedleLeaf(LeafLikeGrowthConfig<Length> growthConfig) {
        super(growthConfig);
    }

    static NeedleLeaf of(LeafLikeGrowthConfig<Length> growthConfig) {
        return new NeedleLeaf(growthConfig);
    }

    @Override
    TreePartGrowthInfo currentGrowthInfo() {
        return new TreeGrowthInfo.LeafLikeGrowthInfo(length());
    }

}
