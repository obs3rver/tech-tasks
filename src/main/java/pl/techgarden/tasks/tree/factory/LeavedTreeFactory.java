package pl.techgarden.tasks.tree.factory;

import pl.techgarden.tasks.tree.LeavedTree;
import pl.techgarden.tasks.tree.Tree;
import pl.techgarden.tasks.tree.domain.Age.Period;
import pl.techgarden.tasks.tree.domain.Location;
import pl.techgarden.tasks.tree.domain.Name;
import pl.techgarden.tasks.tree.growth.Length;
import pl.techgarden.tasks.tree.growth.LengthTreeGrowthConfig;
import pl.techgarden.tasks.tree.growth.TreeGrowthConfig;
import pl.techgarden.tasks.tree.growth.TreePartGrowthConfig;

public class LeavedTreeFactory implements TreeFactory {
    private static final Period ONE_YEAR_PERIOD = Period.of(1);

    @Override
    public Tree createTree(Name name, Location location) {
        return LeavedTree.builder()
                .location(location)
                .name(name)
                .treeGrowthConfig(aLeavedTreeGrowthConfig())
                .build();
    }

    private static TreeGrowthConfig<Length> aLeavedTreeGrowthConfig() {
        return LengthTreeGrowthConfig.builder()
                .rootsGrowthConfig(aMainRootNodeGrowthConfig())
                .stemsGrowthConfig(aMainStemNodeGrowthConfig())
                .build();
    }

    private static TreePartGrowthConfig<Length> aMainRootNodeGrowthConfig() {
        return LengthTreeGrowthConfig.LengthTreePartGrowthConfig.builder()
                .increaseCountOfTreePartBy(1)
                .increaseCountOfTreePartEvery(ONE_YEAR_PERIOD)
                .chooseIdenticalTypeOfNewChildTreePart(true)
                .depthCount(1)
                .growthStrategy(length -> length.plus(Length.of(0.5)))
                .build();
    }

    private static TreePartGrowthConfig<Length> aMainStemNodeGrowthConfig() {
        return LengthTreeGrowthConfig.LengthTreePartGrowthConfig.builder()
                .increaseCountOfTreePartBy(1)
                .increaseCountOfTreePartEvery(ONE_YEAR_PERIOD)
                .chooseIdenticalTypeOfNewChildTreePart(true)
                .depthCount(1)
                .growthStrategy(length -> length.plus(Length.of(1.0)))
                .build();
    }

}
