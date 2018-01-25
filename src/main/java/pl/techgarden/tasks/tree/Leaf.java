package pl.techgarden.tasks.tree;

import pl.techgarden.tasks.tree.growth.LeafLikeGrowthConfig;
import pl.techgarden.tasks.tree.growth.Length;
import pl.techgarden.tasks.tree.growth.TreeGrowthInfo.LeafLikeGrowthInfo;
import pl.techgarden.tasks.tree.growth.TreeGrowthInfo.TreePartGrowthInfo;

class Leaf extends LeafLike {

    Leaf(LeafLikeGrowthConfig<Length> growthConfig) {
        super(growthConfig);
    }

    static Leaf of(LeafLikeGrowthConfig<Length> growthConfig) {
        return new Leaf(growthConfig);
    }

    @Override
    TreePartGrowthInfo currentGrowthInfo() {
        return new LeafLikeGrowthInfo(length());
    }
}
