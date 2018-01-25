package pl.techgarden.tasks.tree.factory;

import pl.techgarden.tasks.tree.LeavedTree;
import pl.techgarden.tasks.tree.Tree;
import pl.techgarden.tasks.tree.domain.Location;
import pl.techgarden.tasks.tree.domain.Name;
import pl.techgarden.tasks.tree.growth.Length;
import pl.techgarden.tasks.tree.growth.TreeGrowthConfig;

public class LeavedTreeFactory implements TreeFactory {

    @Override
    public Tree createTree(
            Name name,
            Location location,
            TreeGrowthConfig<Length> treeGrowthConfig
    ) {
        return LeavedTree.builder()
                .location(location)
                .name(name)
                .treeGrowthConfig(treeGrowthConfig)
                .build();
    }

}
