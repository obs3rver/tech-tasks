package pl.techgarden.tasks.tree

import pl.techgarden.tasks.tree.growth.*

import static pl.techgarden.tasks.tree.growth.LengthTreeGrowthConfig.builder

trait LeavedTreeData extends TreeData {

    static TreeGrowthConfig<Length> aDefaultLeavedTreeGrowthConfig() {
        builder()
                .rootsGrowthConfig(aRootNodeGrowthConfig())
                .stemsGrowthConfig(aStemNodeGrowthConfig())
                .branchesGrowthConfig(aBranchNodeGrowthConfig())
                .build()
    }

    static LengthTreeGrowthConfig.LengthTreeGrowthConfigBuilder aDefaultLeavedTreeGrowthConfigWith() {
        builder()
                .rootsGrowthConfig(aRootNodeGrowthConfig())
                .stemsGrowthConfig(aStemNodeGrowthConfig())
                .branchesGrowthConfig(aBranchNodeGrowthConfig())
    }

    static TreePartGrowthConfig<Length> aRootNodeGrowthConfig() {
        LengthTreeGrowthConfig.LengthTreePartGrowthConfig.builder()
                .increaseCountOfTreePartBy(1)
                .increaseCountOfTreePartEvery(ONE_YEAR_PERIOD)
                .chooseIdenticalTypeOfNewChildTreePart(true)
                .depthCount(1)
                .growthStrategy { it + Length.of(0.5) }
                .build()
    }

    static TreePartGrowthConfig<Length> aStemNodeGrowthConfig() {
        LengthTreeGrowthConfig.LengthTreePartGrowthConfig.builder()
                .increaseCountOfTreePartBy(1)
                .increaseCountOfTreePartEvery(ONE_YEAR_PERIOD)
                .chooseIdenticalTypeOfNewChildTreePart(true)
                .depthCount(1)
                .growthStrategy { it + Length.of(1.0) }
                .build()
    }

    static TreePartGrowthConfig<Length> aStemNodeGrowthConfigWithCreationOfOtherTypes() {
        LengthTreeGrowthConfig.LengthTreePartGrowthConfig.builder()
                .increaseCountOfTreePartBy(1)
                .increaseCountOfTreePartEvery(ONE_YEAR_PERIOD)
                .chooseIdenticalTypeOfNewChildTreePart(false)
                .depthCount(1)
                .growthStrategy { it + Length.of(1.0) }
                .build()
    }

    static TreePartGrowthConfig<Length> aStemNodeGrowthConfigWithCreationOfOtherTypesAndIncreasedDepth() {
        LengthTreeGrowthConfig.LengthTreePartGrowthConfig.builder()
                .increaseCountOfTreePartBy(1)
                .increaseCountOfTreePartEvery(ONE_YEAR_PERIOD)
                .chooseIdenticalTypeOfNewChildTreePart(false)
                .depthCount(2)
                .growthStrategy { it + Length.of(1.0) }
                .build()
    }

    static TreePartGrowthConfig<Length> aBranchNodeGrowthConfigWithCreationOfOtherTypes() {
        LengthTreeGrowthConfig.LengthTreePartGrowthConfig.builder()
                .increaseCountOfTreePartBy(1)
                .increaseCountOfTreePartEvery(ONE_YEAR_PERIOD)
                .chooseIdenticalTypeOfNewChildTreePart(false)
                .depthCount(1)
                .growthStrategy { it + Length.of(0.5) }
                .build()
    }

    static TreePartGrowthConfig<Length> aBranchNodeGrowthConfig() {
        LengthTreeGrowthConfig.LengthTreePartGrowthConfig.builder()
                .increaseCountOfTreePartBy(1)
                .increaseCountOfTreePartEvery(ONE_YEAR_PERIOD)
                .chooseIdenticalTypeOfNewChildTreePart(true)
                .depthCount(1)
                .growthStrategy { it + Length.of(0.5) }
                .build()
    }

    static LeafLikeGrowthConfig<Length> aLeavedTreeLeafGrowthConfig() {
        LeafLikeGrowthConfig.LengthLeafLikeGrowthConfig.builder()
                .increaseCountOfTreePartEvery(ONE_YEAR_PERIOD)
                .growthStrategy { it + Length.of(0.1) }
                .isNeedle(false)
                .build()
    }
}