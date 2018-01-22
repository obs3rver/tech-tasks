package pl.techgarden.tasks.tree.growth;

import lombok.Value;

@Value(staticConstructor = "of")
public class Length implements GrowthTrait {
    double value;

    public static final Length ZERO = Length.of(0.0);

    public Length plus(Length delta) {
        return Length.of(value + delta.value);
    }

    @Override
    public GrowthTrait plus(GrowthTrait delta) {
        return Length.of(value + delta.asDouble());
    }

    @Override
    public double asDouble() {
        return value;
    }
}
