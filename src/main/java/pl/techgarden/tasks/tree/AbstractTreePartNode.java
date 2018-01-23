package pl.techgarden.tasks.tree;

import lombok.val;
import pl.techgarden.tasks.tree.domain.Age.Period;
import pl.techgarden.tasks.tree.growth.Length;
import pl.techgarden.tasks.tree.growth.TreeGrowthInfo;
import pl.techgarden.tasks.tree.growth.TreeGrowthInfo.TreePartGrowthInfo;
import pl.techgarden.tasks.tree.growth.TreePartGrowthConfig;
import pl.techgarden.tasks.tree.growth.TreePartGrowthConfig.GrowingStrategy;
import pl.techgarden.tasks.utils.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;
import static pl.techgarden.tasks.tree.growth.Length.ZERO;

abstract class AbstractTreePartNode implements LengthGrowableNodeTreePart {
    private final Set<LengthGrowableNodeTreePart> childTreeParts;
    private final TreePartGrowthConfig<Length> growthConfig;

    private Length length = ZERO;

    AbstractTreePartNode(TreePartGrowthConfig<Length> growthConfig) {
        this.growthConfig = growthConfig;
        childTreeParts = CollectionUtils.emptyMutableHashSet();
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

    TreePartGrowthInfo collectSummaryGrowthInfo() {
        return collectAllGrowthInfo().stream()
                .reduce(TreeGrowthInfo.RootGrowthInfo.ZERO, TreePartGrowthInfo::add);
    }

    abstract TreePartGrowthInfo currentGrowthInfo();

    abstract LengthGrowableNodeTreePart createChildTreePart(TreePartGrowthConfig<Length> growthConfig);

    private List<TreePartGrowthInfo> collectGrowthInfoFromChildren() {
        return childTreeParts.stream()
                .map(LengthGrowableTreePart::growthInfo)
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
            addChildTreePart(createChildTreePart(growthConfig));
    }

    private void addChildTreePart(LengthGrowableNodeTreePart child) {
        childTreeParts.add(child);
    }

    private void growChildrenParts(Period timePeriod, int depthLevel) {
        childTreeParts.forEach(it ->
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
