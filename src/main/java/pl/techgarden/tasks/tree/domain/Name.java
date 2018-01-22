package pl.techgarden.tasks.tree.domain;

import lombok.Value;

@Value(staticConstructor = "of")
public class Name {
    String value;
}
