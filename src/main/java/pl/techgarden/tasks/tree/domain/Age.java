package pl.techgarden.tasks.tree.domain;

import lombok.Value;

import java.time.Period;

@Value(staticConstructor = "of")
public class Age {
    Period value;

    public static final Age ZERO = Age.of(Period.ZERO);

    public static final Age ONE_YEAR = Age.of(Period.ofYears(1));

    public Age plus(Period timePeriod) {
        return Age.of(value.plus(timePeriod));
    }
}
