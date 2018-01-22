package pl.techgarden.tasks.tree.growth;

import lombok.Builder;
import lombok.Value;
import lombok.experimental.Accessors;
import pl.techgarden.tasks.tree.domain.Age.Period;

@Value
@Accessors(fluent = true)
@Builder
public class LengthTreeGrowthConfig implements TreeGrowthConfig<Length> {
    TreePartGrowthConfig<Length> rootsGrowthConfig;
    TreePartGrowthConfig<Length> stemsGrowthConfig;
    TreePartGrowthConfig<Length> branchesGrowthConfig;
    TreePartGrowthConfig<Length> leavesGrowthConfig;

    @Value
    @Accessors(fluent = true)
    @Builder
    public static class LengthTreePartGrowthConfig implements TreePartGrowthConfig<Length> {
        GrowingStrategy<Length> growthStrategy;
        int increaseCountOfTreePartBy;
        int depthCount;
        Period increaseCountOfTreePartEvery;
    }
}
