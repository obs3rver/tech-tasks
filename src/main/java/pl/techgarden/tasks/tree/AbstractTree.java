package pl.techgarden.tasks.tree;

import pl.techgarden.tasks.tree.domain.Age;
import pl.techgarden.tasks.tree.domain.Age.Period;
import pl.techgarden.tasks.tree.domain.Location;
import pl.techgarden.tasks.tree.domain.Name;
import pl.techgarden.tasks.tree.growth.Length;
import pl.techgarden.tasks.tree.growth.TreeGrowthConfig;
import pl.techgarden.tasks.tree.growth.TreeGrowthInfo;
import pl.techgarden.tasks.tree.growth.TreeGrowthInfo.RootGrowthInfo;
import pl.techgarden.tasks.tree.growth.TreeGrowthInfo.StemGrowthInfo;
import pl.techgarden.tasks.tree.growth.TreeGrowthInfo.TreePartGrowthInfo;

abstract class AbstractTree implements Tree {
    private final Name name;
    private final Location location;
    private final TreeGrowthConfig<Length> treeGrowthConfig;

    private final Root mainRoot;
    private final Stem mainStem;

    private Age age = Age.ZERO;

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
    public TreeGrowthInfo collectGrowthInfo() {
        final TreePartGrowthInfo rootsInfo = collectRootsGrowthInfo();
        final TreePartGrowthInfo stemsInfo = collectStemsGrowthInfo();

        return TreeGrowthInfo.builder()
                .age(age)
                .rootsInfo(rootsInfo)
                .stemsInfo(stemsInfo)
                .build();
    }

    private TreePartGrowthInfo collectStemsGrowthInfo() {
        return mainStem.collectAllGrowthInfo().stream()
                .filter(StemGrowthInfo.class::isInstance)
                .reduce(RootGrowthInfo.ZERO, TreePartGrowthInfo::add);
    }

    private TreePartGrowthInfo collectRootsGrowthInfo() {
        return mainRoot.collectAllGrowthInfo().stream()
                .filter(RootGrowthInfo.class::isInstance)
                .reduce(RootGrowthInfo.ZERO, TreePartGrowthInfo::add);
    }

    @Override
    public void growFor(Period timePeriod) {
        age = increaseAge(timePeriod);

        mainRoot.growFor(timePeriod);
        mainStem.growFor(timePeriod);
    }

    private Age increaseAge(Period timePeriod) {
        return age.plus(timePeriod);
    }

}
