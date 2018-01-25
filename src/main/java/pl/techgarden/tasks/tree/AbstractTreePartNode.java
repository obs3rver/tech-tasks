package pl.techgarden.tasks.tree;

import lombok.val;
import pl.techgarden.tasks.tree.domain.Age.Period;
import pl.techgarden.tasks.tree.growth.Length;
import pl.techgarden.tasks.tree.growth.TreeGrowthConfig;
import pl.techgarden.tasks.tree.growth.TreeGrowthInfo.StemGrowthInfo;
import pl.techgarden.tasks.tree.growth.TreeGrowthInfo.TreePartGrowthInfo;
import pl.techgarden.tasks.tree.growth.TreePartGrowthConfig;
import pl.techgarden.tasks.tree.growth.BasicGrowthConfig.GrowingStrategy;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;
import static pl.techgarden.tasks.tree.growth.Length.ZERO;
import static pl.techgarden.tasks.utils.CollectionUtils.emptyMutableHashSet;

abstract class AbstractTreePartNode implements LengthGrowableNodeTreePart {
    private final Set<LengthGrowableTreePart> childTreeParts;
    final TreePartGrowthConfig<Length> growthConfig;
    final TreeGrowthConfig<Length> treeGrowthConfig;

    private Length length = ZERO;

    AbstractTreePartNode(TreeGrowthConfig<Length> treeGrowthConfig) {
        this.treeGrowthConfig = treeGrowthConfig;
        this.growthConfig = determineGrowthConfigType();
        childTreeParts = emptyMutableHashSet();
    }

    @Override
    public Length length() {
        return length;
    }

    @Override
    public void growFor(Period timePeriod) {
        int seasonIterations = determineNumberOfIterationsFor(timePeriod, growthConfig.increaseCountOfTreePartEvery());

        for (int i = 0; i < seasonIterations; i++) {
            growInnerTreePartsFor(timePeriod, growthConfig.depthCount());
        }
    }

    @Override
    public void growInnerTreePartsFor(Period timePeriod, int depthLevelEachIteration) {
        length = growLengthOfThisTreePart();

        if (isAllowedToGrowChildParts(depthLevelEachIteration)) {
            addNewChildParts();
            growChildrenParts(timePeriod, --depthLevelEachIteration);
        }
    }

    @Override
    public TreePartGrowthInfo growthInfo() {
        return currentGrowthInfo();
    }

    @Override
    public List<TreePartGrowthInfo> collectAllGrowthInfo() {
        val growthList = collectGrowthInfoFromChildren();
        growthList.add(currentGrowthInfo());
        return Collections.unmodifiableList(growthList);
    }

    TreePartGrowthInfo collectAllGrowthInfoSum() {
        return collectAllGrowthInfo().stream()
                .reduce(StemGrowthInfo.ZERO, TreePartGrowthInfo::add);
    }

    abstract TreePartGrowthInfo currentGrowthInfo();

    abstract LengthGrowableTreePart createChildTreePart(TreeGrowthConfig<Length> treeGrowthConfig);

    abstract TreePartGrowthConfig<Length> determineGrowthConfigType();

    private List<TreePartGrowthInfo> collectGrowthInfoFromChildren() {
        val growthInfos = collectGrowthInfosFromNodeChildren();
        growthInfos.addAll(collectGrowthInfosFromNonNodeChildren());

        return growthInfos;
    }

    private List<TreePartGrowthInfo> collectGrowthInfosFromNonNodeChildren() {
        return childTreeParts.stream()
                .filter(obj -> !LengthGrowableNodeTreePart.class.isInstance(obj))
                .map(LengthGrowableTreePart::growthInfo)
                .collect(toList());
    }

    private List<TreePartGrowthInfo> collectGrowthInfosFromNodeChildren() {
        return childTreeParts.stream()
                .filter(LengthGrowableNodeTreePart.class::isInstance)
                .map(LengthGrowableNodeTreePart.class::cast)
                .map(LengthGrowableNodeTreePart::collectAllGrowthInfo)
                .flatMap(List::stream)
                .collect(toList());
    }

    private int determineNumberOfIterationsFor(Period passedPeriod, Period expectedPeriod) {
        return (expectedPeriod.asInt() == 0) ?
                passedPeriod.asInt() :
                passedPeriod.asInt() / expectedPeriod.asInt();
    }

    private boolean isAllowedToGrowChildParts(int depthLevel) {
        return depthLevel > 0;
    }

    private Length growLengthOfThisTreePart() {
        return growingStrategy().grow(length);
    }

    private void addNewChildParts() {
        for (int i = 0; i < growthConfig.increaseCountOfTreePartBy(); i++)
            addChildTreePart(createChildTreePart(treeGrowthConfig));
    }

    private void addChildTreePart(LengthGrowableTreePart child) {
        childTreeParts.add(child);
    }

    private void growChildrenParts(Period timePeriod, int depthLevel) {
        growChildrenNodeParts(timePeriod, depthLevel);
        growChildrenNonNodeParts(timePeriod);
    }

    private void growChildrenNonNodeParts(Period timePeriod) {
        childTreeParts.stream()
                .filter(LeafLike.class::isInstance)
                .map(LeafLike.class::cast)
                .forEach(it -> it.growFor(timePeriod)
                );
    }

    private void growChildrenNodeParts(Period timePeriod, int depthLevel) {
        childTreeParts.stream()
                .filter(LengthGrowableNodeTreePart.class::isInstance)
                .map(LengthGrowableNodeTreePart.class::cast)
                .forEach(it ->
                        it.growInnerTreePartsFor(
                                timePeriod,
                                depthLevel
                        )
                );
    }

    private GrowingStrategy<Length> growingStrategy() {
        return growthConfig.growthStrategy();
    }
}
