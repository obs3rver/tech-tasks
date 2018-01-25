package pl.techgarden.tasks.tree;

import lombok.val;
import pl.techgarden.tasks.tree.domain.Age;
import pl.techgarden.tasks.tree.domain.Age.Period;
import pl.techgarden.tasks.tree.domain.Location;
import pl.techgarden.tasks.tree.domain.Name;
import pl.techgarden.tasks.tree.growth.Length;
import pl.techgarden.tasks.tree.growth.TreeGrowthConfig;
import pl.techgarden.tasks.tree.growth.TreeGrowthInfo;
import pl.techgarden.tasks.tree.growth.TreeGrowthInfo.BranchGrowthInfo;
import pl.techgarden.tasks.tree.growth.TreeGrowthInfo.LeafLikeGrowthInfo;
import pl.techgarden.tasks.tree.growth.TreeGrowthInfo.RootGrowthInfo;
import pl.techgarden.tasks.tree.growth.TreeGrowthInfo.StemGrowthInfo;
import pl.techgarden.tasks.tree.growth.TreeGrowthInfo.TreePartGrowthInfo;

import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;

abstract class AbstractTree implements Tree {
    private final Name name;
    private final Location location;

    private final Root mainRoot;
    private final Stem mainStem;

    private Age age = Age.ZERO;

    AbstractTree(Name name, Location location, TreeGrowthConfig<Length> growthConfig) {
        this.name = name;
        this.location = location;

        this.mainRoot = Root.of(growthConfig);
        this.mainStem = Stem.of(growthConfig);
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
        val mainRootPartsGrowthInfo = mainRoot.collectAllGrowthInfo();
        val mainStemPartsGrowthInfo = mainStem.collectAllGrowthInfo();

        val rootsInfo = collectRootsGrowthInfoFrom(mainRootPartsGrowthInfo);
        val stemsInfo = collectStemsGrowthInfoFrom(mainStemPartsGrowthInfo);
        val branchesInfo = collectBranchesGrowthInfoFrom(mainStemPartsGrowthInfo);
        val leavesInfo = collectLeavesGrowthInfoFrom(mainStemPartsGrowthInfo);

        return TreeGrowthInfo.builder()
                .age(age)
                .rootsInfo(rootsInfo)
                .stemsInfo(stemsInfo)
                .branchesInfo(branchesInfo)
                .leavesInfo(leavesInfo)
                .build();
    }

    private static TreePartGrowthInfo collectLeavesGrowthInfoFrom(
            final List<TreePartGrowthInfo> mainStemPartsGrowthInfo
    ) {
        return collectGrowthInfosFrom(
                mainStemPartsGrowthInfo,
                LeafLikeGrowthInfo.class::isInstance,
                LeafLikeGrowthInfo.ZERO,
                TreePartGrowthInfo::add
        );
    }

    private static TreePartGrowthInfo collectBranchesGrowthInfoFrom(
            final List<TreePartGrowthInfo> mainStemPartsGrowthInfo
    ) {
        return collectGrowthInfosFrom(
                mainStemPartsGrowthInfo,
                BranchGrowthInfo.class::isInstance,
                BranchGrowthInfo.ZERO,
                TreePartGrowthInfo::add
        );
    }

    private static TreePartGrowthInfo collectStemsGrowthInfoFrom(
            final List<TreePartGrowthInfo> mainStemPartsGrowthInfo
    ) {
        return collectGrowthInfosFrom(
                mainStemPartsGrowthInfo,
                StemGrowthInfo.class::isInstance,
                StemGrowthInfo.ZERO,
                TreePartGrowthInfo::add
        );
    }

    private static TreePartGrowthInfo collectRootsGrowthInfoFrom(
            final List<TreePartGrowthInfo> mainRootPartsGrowthInfo
    ) {
        return collectGrowthInfosFrom(
                mainRootPartsGrowthInfo,
                RootGrowthInfo.class::isInstance,
                RootGrowthInfo.ZERO,
                TreePartGrowthInfo::add
        );
    }

    private static TreePartGrowthInfo collectGrowthInfosFrom(
            final List<TreePartGrowthInfo> treeGrowthInfos,
            Predicate<Object> instancePredicate,
            TreePartGrowthInfo identity,
            BinaryOperator<TreePartGrowthInfo> accumulator
    ) {
        return treeGrowthInfos.stream()
                .filter(instancePredicate)
                .reduce(identity, accumulator);
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
