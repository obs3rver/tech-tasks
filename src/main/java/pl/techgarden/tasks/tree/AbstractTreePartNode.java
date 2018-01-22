package pl.techgarden.tasks.tree;

import pl.techgarden.tasks.tree.domain.Age.Period;
import pl.techgarden.tasks.tree.growth.Length;
import pl.techgarden.tasks.tree.growth.TreeGrowthInfo.TreePartGrowthInfo;
import pl.techgarden.tasks.tree.growth.TreePartGrowthConfig;
import pl.techgarden.tasks.tree.growth.TreePartGrowthConfig.GrowingStrategy;
import pl.techgarden.tasks.utils.CollectionUtils;

import java.util.Set;

import static pl.techgarden.tasks.tree.growth.Length.ZERO;

abstract class AbstractTreePartNode implements LengthGrowableNodeTreePart {
    private final Set<LengthGrowableNodeTreePart> childTreeParts;
    private final TreePartGrowthConfig<Length> growthConfig;

    private TreePartGrowthInfo<Length> growthInfo = new TreePartGrowthInfo<>(ZERO);

    private Length length = ZERO;

    AbstractTreePartNode(TreePartGrowthConfig<Length> growthConfig) {
        this.growthConfig = growthConfig;
        childTreeParts = CollectionUtils.emptyMutableHashSet();
    }

    void addChildTreePart(LengthGrowableNodeTreePart child) {
        childTreeParts.add(child);
    }

    @Override
    public Length length() {
        return length;
    }

    @Override
    public TreePartGrowthInfo<Length> growFor(Period timePeriod) {
        return growInnerTreePartsFor(timePeriod, growthConfig.depthCount());
    }

    @Override
    public TreePartGrowthInfo<Length> growInnerTreePartsFor(Period timePeriod, int depthLevelEachIteration) {
        int iterations = determineNumberOfIterationsFor(timePeriod, growthConfig.increaseCountOfTreePartEvery());

        for (int i = 0; i < iterations; i++) {
            length = growLengthOfThisTreePart();

            if (isAllowedToGrowChildParts(depthLevelEachIteration)) {
                addNewChildParts();
                int nextDepthLevel = depthLevelEachIteration - 1;
                growChildrenParts(timePeriod, nextDepthLevel);
            }
        }

        updateGrowthInfo();

        return growthInfo;
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

    private void updateGrowthInfo() {
        growthInfo.clear();
        growthInfo.add(length);
        updateGrowthIntoForEveryChildPart();
    }

    private void updateGrowthIntoForEveryChildPart() {
        childTreeParts.stream()
                .map(LengthGrowableNodeTreePart::length)
                .forEach(growthInfo::add);
    }

    private void addNewChildParts() {
        for (int i = 0; i < growthConfig.increaseCountOfTreePartBy(); i++)
            addNewChildPart(growthConfig);
    }

    private void growChildrenParts(Period timePeriod, int depthLevel) {
        childTreeParts.forEach(it ->
                it.growInnerTreePartsFor(
                        timePeriod,
                        depthLevel
                )
        );
    }

    abstract void addNewChildPart(TreePartGrowthConfig<Length> growthConfig);

    private GrowingStrategy<Length> growingStrategy() {
        return growthConfig.growthStrategy();
    }
}
