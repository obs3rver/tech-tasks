package pl.techgarden.tasks.tree.growth;

import lombok.AllArgsConstructor;
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

    TreePartGrowthInfo rootsInfo;
    TreePartGrowthInfo stemsInfo;

    public static TreeGrowthInfo EMPTY =
            TreeGrowthInfo.builder()
                    .age(ZERO)
                    .rootsInfo(RootGrowthInfo.ZERO)
                    .stemsInfo(StemGrowthInfo.ZERO)
                    .build();

    @ToString
    @Getter
    @Accessors(fluent = true)
    @EqualsAndHashCode
    @AllArgsConstructor
    public static abstract class TreePartGrowthInfo {
        protected long treePartCounter = 0;
        protected GrowthTrait value;

        public TreePartGrowthInfo(GrowthTrait value) {
            this.value = value;
        }

        public abstract TreePartGrowthInfo add(TreePartGrowthInfo other);
    }

    public static class RootGrowthInfo extends TreePartGrowthInfo {
        public static final RootGrowthInfo ZERO = new RootGrowthInfo(0, Length.ZERO);

        public RootGrowthInfo(GrowthTrait value) {
            super(1, value);
        }

        @Builder
        public RootGrowthInfo(long treePartCounter, GrowthTrait value) {
            super(treePartCounter, value);
        }

        public TreePartGrowthInfo add(TreePartGrowthInfo other) {
            return new RootGrowthInfo(
                    treePartCounter + other.treePartCounter,
                    value.plus(other.value)
            );
        }

        @Override
        public String toString() {
            return "RootGrowthInfo " + super.toString();
        }
    }

    public static class StemGrowthInfo extends TreePartGrowthInfo {
        public static final StemGrowthInfo ZERO = new StemGrowthInfo(0, Length.ZERO);

        public StemGrowthInfo(GrowthTrait value) {
            super(1, value);
        }

        @Builder
        public StemGrowthInfo(long treePartCounter, GrowthTrait value) {
            super(treePartCounter, value);
        }

        public TreePartGrowthInfo add(TreePartGrowthInfo other) {
            return new StemGrowthInfo(
                    treePartCounter + other.treePartCounter,
                    value.plus(other.value)
            );
        }

        @Override
        public String toString() {
            return "StemGrowthInfo " + super.toString();
        }
    }
}
