package pl.techgarden.tasks.tree.growth;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.Value;
import lombok.experimental.Accessors;
import pl.techgarden.tasks.tree.domain.Age;

import static pl.techgarden.tasks.tree.domain.Age.ZERO;

@Value
@Builder
public class TreeGrowthInfo {
    Age age;

    TreePartGrowthInfo<Length> rootsInfo;
    TreePartGrowthInfo<Length> stemsInfo;

    public static TreeGrowthInfo EMPTY =
            TreeGrowthInfo.builder()
                    .age(ZERO)
                    .rootsInfo(new TreePartGrowthInfo<>(Length.ZERO))
                    .build();

    @ToString
    @Getter
    @Accessors(fluent = true)
    @EqualsAndHashCode(exclude = {"initialTrait"})
    public static class TreePartGrowthInfo<T extends GrowthTrait> {
        private long treePartCounter = 0;
        private T traitSum;
        private T initialTrait;

        public TreePartGrowthInfo(T initialTrait) {
            this.traitSum = initialTrait;
            this.initialTrait = initialTrait;
        }

        @Builder
        TreePartGrowthInfo(long treePartCounter, T traitSum) {
            this.treePartCounter = treePartCounter;
            this.traitSum = traitSum;
        }

        @SuppressWarnings("unchecked")
        public TreePartGrowthInfo add(T trait) {
            treePartCounter++;
            traitSum = (T) traitSum.plus(trait);
            return this;
        }

        public void clear() {
            treePartCounter = 0;
            traitSum = initialTrait;
        }
    }
}
