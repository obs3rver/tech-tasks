package pl.techgarden.tasks.tree;

import java.time.Period;

interface GrowableTreePart {
    TreeGrowthInfo growFor(Period timePeriod);
    TreeGrowthInfo growthInfo();
}
