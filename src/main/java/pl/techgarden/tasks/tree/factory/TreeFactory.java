package pl.techgarden.tasks.tree.factory;

import pl.techgarden.tasks.tree.domain.Location;
import pl.techgarden.tasks.tree.domain.Name;
import pl.techgarden.tasks.tree.Tree;
import pl.techgarden.tasks.tree.growth.Length;
import pl.techgarden.tasks.tree.growth.TreeGrowthConfig;

public interface TreeFactory {
    Tree createTree(
            Name name,
            Location location,
            TreeGrowthConfig<Length> treeGrowthConfig
    );
}
