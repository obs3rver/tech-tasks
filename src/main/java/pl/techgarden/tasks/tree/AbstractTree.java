package pl.techgarden.tasks.tree;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import pl.techgarden.tasks.tree.domain.Age;
import pl.techgarden.tasks.tree.domain.Location;
import pl.techgarden.tasks.tree.domain.Name;

import java.time.Period;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
abstract class AbstractTree implements Tree {
    private final Name name;
    private final Location location;

    private Age age = Age.ZERO;
    private TreeGrowthInfo treeGrowthInfo = TreeGrowthInfo.EMPTY;

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

    @Override
    public TreeGrowthInfo growthInfo() {
        return treeGrowthInfo;
    }

    @Override
    public TreeGrowthInfo growFor(Period timePeriod) {
        //template method
        age = increaseAge(timePeriod);

        return prepareTreeGrowthInfo(age);
    }

    private TreeGrowthInfo prepareTreeGrowthInfo(Age updatedAge) {
        treeGrowthInfo = TreeGrowthInfo.builder()
                .age(updatedAge)
                .build();

        return treeGrowthInfo;
    }

    private Age increaseAge(Period timePeriod) {
        return age.plus(timePeriod);
    }

}
