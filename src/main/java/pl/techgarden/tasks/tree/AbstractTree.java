package pl.techgarden.tasks.tree;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import pl.techgarden.tasks.tree.domain.Age;
import pl.techgarden.tasks.tree.domain.Location;
import pl.techgarden.tasks.tree.domain.Name;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
abstract class AbstractTree implements Tree {
    private final Name name;
    private final Location location;

    private Age age = Age.ZERO;

    @Override
    public Age age() {
        return age;
    }

    @Override
    public Name name() {
        return name;
    }

    @Override
    public Location location() {
        return location;
    }

}
