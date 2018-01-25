package pl.techgarden.tasks.tree

import pl.techgarden.tasks.tree.domain.Location
import pl.techgarden.tasks.tree.domain.Name
import pl.techgarden.tasks.tree.growth.LeafLikeGrowthConfig
import pl.techgarden.tasks.tree.growth.Length
import pl.techgarden.tasks.tree.growth.TreeGrowthConfig
import pl.techgarden.tasks.tree.growth.TreePartGrowthConfig

import static pl.techgarden.tasks.tree.domain.Age.Period
import static pl.techgarden.tasks.tree.growth.LeafLikeGrowthConfig.LengthLeafLikeGrowthConfig
import static pl.techgarden.tasks.tree.growth.LengthTreeGrowthConfig.*

trait TreeData {
    static final Location LOCATION = Location.of("Warsaw")
    static final Name LEAVED_TREE_NAME = Name.of("Maple")

    static final Period ONE_YEAR_PERIOD = Period.of(1)
    static final Period TWO_YEARS_PERIOD = Period.of(2)

    static TreeGrowthConfig<Length> aDefaultLeavedTreeGrowthConfig() {
        return builder()
                .rootsGrowthConfig(aRootNodeGrowthConfig())
                .stemsGrowthConfig(aStemNodeGrowthConfig())
                .branchesGrowthConfig(aBranchNodeGrowthConfig())
                .build()
    }

    static LengthTreeGrowthConfigBuilder aDefaultLeavedTreeGrowthConfigWith() {
        return builder()
                .rootsGrowthConfig(aRootNodeGrowthConfig())
                .stemsGrowthConfig(aStemNodeGrowthConfig())
                .branchesGrowthConfig(aBranchNodeGrowthConfig())
    }

    static TreePartGrowthConfig<Length> aRootNodeGrowthConfig() {
        return LengthTreePartGrowthConfig.builder()
                .increaseCountOfTreePartBy(1)
                .increaseCountOfTreePartEvery(ONE_YEAR_PERIOD)
                .chooseIdenticalTypeOfNewChildTreePart(true)
                .depthCount(1)
                .growthStrategy { it + Length.of(0.5) }
                .build()
    }

    static TreePartGrowthConfig<Length> aStemNodeGrowthConfig() {
        return LengthTreePartGrowthConfig.builder()
                .increaseCountOfTreePartBy(1)
                .increaseCountOfTreePartEvery(ONE_YEAR_PERIOD)
                .chooseIdenticalTypeOfNewChildTreePart(true)
                .depthCount(1)
                .growthStrategy { it + Length.of(1.0) }
                .build()
    }

    static TreePartGrowthConfig<Length> aStemNodeGrowthConfigWithCreationOfOtherTypes() {
        return LengthTreePartGrowthConfig.builder()
                .increaseCountOfTreePartBy(1)
                .increaseCountOfTreePartEvery(ONE_YEAR_PERIOD)
                .chooseIdenticalTypeOfNewChildTreePart(false)
                .depthCount(1)
                .growthStrategy { it + Length.of(1.0) }
                .build()
    }

    static TreePartGrowthConfig<Length> aStemNodeGrowthConfigWithCreationOfOtherTypesAndIncreasedDepth() {
        return LengthTreePartGrowthConfig.builder()
                .increaseCountOfTreePartBy(1)
                .increaseCountOfTreePartEvery(ONE_YEAR_PERIOD)
                .chooseIdenticalTypeOfNewChildTreePart(false)
                .depthCount(2)
                .growthStrategy { it + Length.of(1.0) }
                .build()
    }

    static TreePartGrowthConfig<Length> aBranchNodeGrowthConfigWithCreationOfOtherTypes() {
        return LengthTreePartGrowthConfig.builder()
                .increaseCountOfTreePartBy(1)
                .increaseCountOfTreePartEvery(ONE_YEAR_PERIOD)
                .chooseIdenticalTypeOfNewChildTreePart(false)
                .depthCount(1)
                .growthStrategy { it + Length.of(0.5) }
                .build()
    }

    static TreePartGrowthConfig<Length> aBranchNodeGrowthConfig() {
        return LengthTreePartGrowthConfig.builder()
                .increaseCountOfTreePartBy(1)
                .increaseCountOfTreePartEvery(ONE_YEAR_PERIOD)
                .chooseIdenticalTypeOfNewChildTreePart(true)
                .depthCount(1)
                .growthStrategy { it + Length.of(0.5) }
                .build()
    }

    static LeafLikeGrowthConfig<Length> aLeafGrowthConfig() {
        return LengthLeafLikeGrowthConfig.builder()
                .increaseCountOfTreePartEvery(ONE_YEAR_PERIOD)
                .growthStrategy { it + Length.of(0.1) }
                .isNeedle(false)
                .build()
    }
}