package pl.techgarden.tasks.tree.factory;

import pl.techgarden.tasks.tree.domain.Location;
import pl.techgarden.tasks.tree.domain.Name;
import pl.techgarden.tasks.tree.Tree;

public interface TreeFactory {
    Tree createTree(Name name, Location location);
}
