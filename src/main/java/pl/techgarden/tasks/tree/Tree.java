package pl.techgarden.tasks.tree;

import pl.techgarden.tasks.tree.domain.Age;
import pl.techgarden.tasks.tree.domain.Age.Period;
import pl.techgarden.tasks.tree.domain.Location;
import pl.techgarden.tasks.tree.domain.Name;
import pl.techgarden.tasks.tree.growth.TreeGrowthInfo;

public interface Tree {
    Age age();
    Name name();
    Location location();

    TreeGrowthInfo growFor(Period timePeriod);
    TreeGrowthInfo growthInfo();
}
