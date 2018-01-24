package pl.techgarden.tasks.tree

import pl.techgarden.tasks.tree.domain.Location
import pl.techgarden.tasks.tree.domain.Name
import pl.techgarden.tasks.tree.growth.Length
import pl.techgarden.tasks.tree.growth.LengthTreeGrowthConfig
import pl.techgarden.tasks.tree.growth.TreeGrowthConfig
import pl.techgarden.tasks.tree.growth.TreePartGrowthConfig

import static pl.techgarden.tasks.tree.domain.Age.Period

trait TreeData {
    static final Location LOCATION = Location.of("Warsaw")
    static final Name LEAVED_TREE_NAME = Name.of("Maple")

    static final Period ONE_YEAR_PERIOD = Period.of(1)
    static final Period TWO_YEARS_PERIOD = Period.of(2)

    static TreeGrowthConfig<Length> aDefaultLeavedTreeGrowthConfig() {
        return LengthTreeGrowthConfig.builder()
                .rootsGrowthConfig(aRootNodeGrowthConfig())
                .stemsGrowthConfig(aStemNodeGrowthConfig())
                .branchesGrowthConfig(aBranchNodeGrowthConfig())
                .build()
    }

    static LengthTreeGrowthConfig.LengthTreeGrowthConfigBuilder aDefaultLeavedTreeGrowthConfigWith() {
        return LengthTreeGrowthConfig.builder()
                .rootsGrowthConfig(aRootNodeGrowthConfig())
                .stemsGrowthConfig(aStemNodeGrowthConfig())
                .branchesGrowthConfig(aBranchNodeGrowthConfig())
    }

    static TreePartGrowthConfig<Length> aRootNodeGrowthConfig() {
        return LengthTreeGrowthConfig.LengthTreePartGrowthConfig.builder()
                .increaseCountOfTreePartBy(1)
                .increaseCountOfTreePartEvery(ONE_YEAR_PERIOD)
                .chooseIdenticalTypeOfNewChildTreePart(true)
                .depthCount(1)
                .growthStrategy { it + Length.of(0.5) }
                .build()
    }

    static TreePartGrowthConfig<Length> aStemNodeGrowthConfig() {
        return LengthTreeGrowthConfig.LengthTreePartGrowthConfig.builder()
                .increaseCountOfTreePartBy(1)
                .increaseCountOfTreePartEvery(ONE_YEAR_PERIOD)
                .chooseIdenticalTypeOfNewChildTreePart(true)
                .depthCount(1)
                .growthStrategy { it + Length.of(1.0) }
                .build()
    }

    static TreePartGrowthConfig aStemNodeGrowthConfigWithCreationOfOtherTypes() {
        return LengthTreeGrowthConfig.LengthTreePartGrowthConfig.builder()
                .increaseCountOfTreePartBy(1)
                .increaseCountOfTreePartEvery(ONE_YEAR_PERIOD)
                .chooseIdenticalTypeOfNewChildTreePart(false)
                .depthCount(1)
                .growthStrategy { it + Length.of(1.0) }
                .build()
    }

    static TreePartGrowthConfig<Length> aBranchNodeGrowthConfig() {
        return LengthTreeGrowthConfig.LengthTreePartGrowthConfig.builder()
                .increaseCountOfTreePartBy(1)
                .increaseCountOfTreePartEvery(ONE_YEAR_PERIOD)
                .chooseIdenticalTypeOfNewChildTreePart(true)
                .depthCount(1)
                .growthStrategy { it + Length.of(0.5) }
                .build()
    }
}