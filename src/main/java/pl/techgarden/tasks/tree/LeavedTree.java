package pl.techgarden.tasks.tree;

import lombok.Builder;
import pl.techgarden.tasks.tree.domain.Location;
import pl.techgarden.tasks.tree.domain.Name;

public class LeavedTree extends AbstractTree {

    @Builder
    LeavedTree(Name name, Location location) {
        super(name, location);
    }

}
