package pl.techgarden.tasks.tree;

import pl.techgarden.tasks.tree.growth.Length;
import pl.techgarden.tasks.tree.growth.TreeGrowthInfo.StemGrowthInfo;
import pl.techgarden.tasks.tree.growth.TreeGrowthInfo.TreePartGrowthInfo;
import pl.techgarden.tasks.tree.growth.TreePartGrowthConfig;

class Stem extends AbstractTreePartNode {
    private Stem(TreePartGrowthConfig<Length> growthConfig) {
        super(growthConfig);
    }

    static Stem of(TreePartGrowthConfig<Length> growthConfig) {
        return new Stem(growthConfig);
    }

    TreePartGrowthInfo currentGrowthInfo() {
        return new StemGrowthInfo(length());
    }

    @Override
    LengthGrowableNodeTreePart createChildTreePart(TreePartGrowthConfig<Length> growthConfig) {
        if (growthConfig.chooseIdenticalTypeOfNewChildTreePart())
            return Stem.of(growthConfig);

        throw new UnsupportedOperationException();
    }

}
