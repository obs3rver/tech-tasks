package pl.techgarden.tasks.tree

import pl.techgarden.tasks.tree.domain.Location
import pl.techgarden.tasks.tree.domain.Name

import static pl.techgarden.tasks.tree.domain.Age.Period

trait TreeData {
    static final Location LOCATION = Location.of("Warsaw")

    static final Period ONE_YEAR_PERIOD = Period.of(1)
    static final Period TWO_YEARS_PERIOD = Period.of(2)

    static final Name LEAVED_TREE_NAME = Name.of("Maple")
    static final Name NEEDLE_LEAVED_TREE_NAME = Name.of("Pine")
}