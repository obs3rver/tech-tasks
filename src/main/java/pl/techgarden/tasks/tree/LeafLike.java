package pl.techgarden.tasks.tree;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import pl.techgarden.tasks.tree.domain.Age.Period;
import pl.techgarden.tasks.tree.growth.LeafLikeGrowthConfig;
import pl.techgarden.tasks.tree.growth.Length;
import pl.techgarden.tasks.tree.growth.TreeGrowthInfo.TreePartGrowthInfo;
import pl.techgarden.tasks.tree.growth.BasicGrowthConfig.GrowingStrategy;

import static pl.techgarden.tasks.tree.growth.Length.ZERO;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
abstract class LeafLike implements LengthGrowableTreePart {
    private final LeafLikeGrowthConfig<Length> growthConfig;

    private Length length = ZERO;

    @Override
    public Length length() {
        return length;
    }

    @Override
    public void growFor(Period timePeriod) {
        length = growLengthOfThisTreePart();
    }

    @Override
    public TreePartGrowthInfo growthInfo() {
        return currentGrowthInfo();
    }

    abstract TreePartGrowthInfo currentGrowthInfo();

    private Length growLengthOfThisTreePart() {
        return growingStrategy().grow(length);
    }

    private GrowingStrategy<Length> growingStrategy() {
        return growthConfig.growthStrategy();
    }
}
