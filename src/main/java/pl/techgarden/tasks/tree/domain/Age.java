package pl.techgarden.tasks.tree.domain;

import lombok.Value;

import java.time.Period;

@Value(staticConstructor = "of")
public class Age {
    Period value;

    public static final Age ZERO = Age.of(Period.ZERO);
}
