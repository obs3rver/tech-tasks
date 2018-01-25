package pl.techgarden.tasks.tree;

import pl.techgarden.tasks.tree.domain.Age.Period;
import pl.techgarden.tasks.tree.growth.TreeGrowthInfo;
import pl.techgarden.tasks.tree.growth.TreeGrowthInfo.TreePartGrowthInfo;

import java.util.List;

interface LengthGrowableNodeTreePart extends LengthGrowableTreePart {
    void growInnerTreePartsFor(Period timePeriod, int depthLevel);
    List<TreePartGrowthInfo> collectAllGrowthInfo();
}
