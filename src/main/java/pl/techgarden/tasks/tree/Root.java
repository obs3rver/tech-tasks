package pl.techgarden.tasks.tree;

import pl.techgarden.tasks.tree.growth.Length;
import pl.techgarden.tasks.tree.growth.TreePartGrowthConfig;

class Root extends AbstractTreePartNode {

    private Root(TreePartGrowthConfig<Length> growthConfig) {
        super(growthConfig);
    }

    static Root of(TreePartGrowthConfig<Length> growthConfig) {
        return new Root(growthConfig);
    }

    @Override
    void addNewChildPart(TreePartGrowthConfig<Length> growthConfig) {
        final Root childRoot = new Root(growthConfig);
        addChildTreePart(childRoot);
    }
}
