package pl.techgarden.tasks.tree;

import pl.techgarden.tasks.tree.domain.Age;
import pl.techgarden.tasks.tree.domain.Age.Period;
import pl.techgarden.tasks.tree.domain.Location;
import pl.techgarden.tasks.tree.domain.Name;
import pl.techgarden.tasks.tree.growth.Length;
import pl.techgarden.tasks.tree.growth.TreeGrowthConfig;
import pl.techgarden.tasks.tree.growth.TreeGrowthInfo;
import pl.techgarden.tasks.tree.growth.TreeGrowthInfo.TreePartGrowthInfo;

abstract class AbstractTree implements Tree {
    private final Name name;
    private final Location location;
    private final TreeGrowthConfig<Length> treeGrowthConfig;

    private final Root mainRoot;
    private final Stem mainStem;

    private Age age = Age.ZERO;
    private TreeGrowthInfo treeGrowthInfo = TreeGrowthInfo.EMPTY;

    AbstractTree(Name name, Location location, TreeGrowthConfig<Length> treeGrowthConfig) {
        this.name = name;
        this.location = location;
        this.treeGrowthConfig = treeGrowthConfig;

        this.mainRoot = Root.of(treeGrowthConfig.rootsGrowthConfig());
        this.mainStem = Stem.of(treeGrowthConfig.stemsGrowthConfig());
    }

    @Override
    public Age age() {
        return age;
    }

    @Override
    public Name name() {
        return name;
    }

    @Override
    public Location location() {
        return location;
    }

    @Override
    public TreeGrowthInfo growthInfo() {
        return treeGrowthInfo;
    }

    @Override
    public TreeGrowthInfo growFor(Period timePeriod) {
        age = increaseAge(timePeriod);
        TreePartGrowthInfo<Length> rootsGrowthInfo = mainRoot.growFor(timePeriod);
        TreePartGrowthInfo<Length> stemsGrowthInfo = mainStem.growFor(timePeriod);

        return prepareTreeGrowthInfo(
                age,
                rootsGrowthInfo,
                stemsGrowthInfo
        );
    }

    private TreeGrowthInfo prepareTreeGrowthInfo(
            Age updatedAge,
            TreePartGrowthInfo<Length> rootsGrowthInfo,
            TreePartGrowthInfo<Length> stemsGrowthInfo
    ) {
        treeGrowthInfo = TreeGrowthInfo.builder()
                .age(updatedAge)
                .rootsInfo(rootsGrowthInfo)
                .stemsInfo(stemsGrowthInfo)
                .build();

        return treeGrowthInfo;
    }

    private Age increaseAge(Period timePeriod) {
        return age.plus(timePeriod);
    }

}
