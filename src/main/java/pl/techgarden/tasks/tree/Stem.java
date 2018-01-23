package pl.techgarden.tasks.tree;

import pl.techgarden.tasks.tree.growth.Length;
import pl.techgarden.tasks.tree.growth.TreePartGrowthConfig;

class Stem extends AbstractTreePartNode {
    private Stem(TreePartGrowthConfig<Length> growthConfig) {
        super(growthConfig);
    }

    static Stem of(TreePartGrowthConfig<Length> growthConfig) {
        return new Stem(growthConfig);
    }

    @Override
    void addNewChildPart(TreePartGrowthConfig<Length> growthConfig) {
        final Stem childStem = Stem.of(growthConfig);
        addChildTreePart(childStem);
    }
}
