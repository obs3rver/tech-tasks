package pl.techgarden.tasks.tree.domain;

import lombok.Value;

@Value(staticConstructor = "of")
public class Age {
    Period value;

    public static final Age ZERO = Age.of(Period.ZERO);

    public static final Age ONE_YEAR = Age.of(Period.of(1));

    public Age plus(Period timePeriod) {
        return Age.of(value.plus(timePeriod));
    }

    @Value(staticConstructor = "of")
    public static class Period {
        int seasons;

        public static final Period ZERO = Period.of(0);

        public Period plus(Period other) {
            return Period.of(seasons + other.seasons);
        }

        public int asInt() {
            return seasons;
        }
    }
}
