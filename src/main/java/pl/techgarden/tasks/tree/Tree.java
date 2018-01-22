package pl.techgarden.tasks.tree;

import pl.techgarden.tasks.tree.domain.Age;
import pl.techgarden.tasks.tree.domain.Location;
import pl.techgarden.tasks.tree.domain.Name;

public interface Tree {
    Age age();
    Name name();
    Location location();
}
