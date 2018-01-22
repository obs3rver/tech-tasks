package pl.techgarden.tasks.tree;

import pl.techgarden.tasks.tree.domain.Age.Period;
import pl.techgarden.tasks.tree.growth.Length;
import pl.techgarden.tasks.tree.growth.TreeGrowthInfo.TreePartGrowthInfo;

interface LengthGrowableNodeTreePart extends LengthGrowableTreePart {
    TreePartGrowthInfo<Length> growInnerTreePartsFor(Period timePeriod, int depthLevel);
}
