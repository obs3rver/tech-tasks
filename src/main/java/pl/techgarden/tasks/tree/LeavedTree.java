package pl.techgarden.tasks.tree;

import lombok.Builder;
import pl.techgarden.tasks.tree.domain.Location;
import pl.techgarden.tasks.tree.domain.Name;
import pl.techgarden.tasks.tree.growth.Length;
import pl.techgarden.tasks.tree.growth.TreeGrowthConfig;

public class LeavedTree extends AbstractTree {

    @Builder
    LeavedTree(Name name, Location location, TreeGrowthConfig<Length> treeGrowthConfig) {
        super(name, location, treeGrowthConfig);
    }

}
