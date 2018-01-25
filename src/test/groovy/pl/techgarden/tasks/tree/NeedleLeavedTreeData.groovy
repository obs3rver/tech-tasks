package pl.techgarden.tasks.tree

import pl.techgarden.tasks.tree.growth.LeafLikeGrowthConfig
import pl.techgarden.tasks.tree.growth.Length
import pl.techgarden.tasks.tree.growth.LengthTreeGrowthConfig
import pl.techgarden.tasks.tree.growth.TreeGrowthConfig

import static pl.techgarden.tasks.tree.growth.LengthTreeGrowthConfig.builder

trait NeedleLeavedTreeData extends LeavedTreeData {

    static TreeGrowthConfig<Length> aDefaultNeedleLeavedTreeGrowthConfig() {
        builder()
                .rootsGrowthConfig(aRootNodeGrowthConfig())
                .stemsGrowthConfig(aStemNodeGrowthConfig())
                .branchesGrowthConfig(aBranchNodeGrowthConfig())
                .build()
    }

    static LengthTreeGrowthConfig.LengthTreeGrowthConfigBuilder aDefaultNeedleLeavedTreeGrowthConfigWith() {
        builder()
                .rootsGrowthConfig(aRootNodeGrowthConfig())
                .stemsGrowthConfig(aStemNodeGrowthConfig())
                .branchesGrowthConfig(aBranchNodeGrowthConfig())
    }

    static LeafLikeGrowthConfig<Length> aNeedleLeavedTreeLeafGrowthConfig() {
        LeafLikeGrowthConfig.LengthLeafLikeGrowthConfig.builder()
                .increaseCountOfTreePartEvery(ONE_YEAR_PERIOD)
                .growthStrategy { it + Length.of(0.1) }
                .isNeedle(true)
                .build()
    }

}