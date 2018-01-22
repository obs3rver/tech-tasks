package pl.techgarden.tasks.tree

import pl.techgarden.tasks.tree.domain.Location
import pl.techgarden.tasks.tree.domain.Name

import java.time.Period

trait TreeData {
    static final Location LOCATION = Location.of("Warsaw")
    static final Name LEAVED_TREE_NAME = Name.of("Maple")

    static final Period ONE_YEAR_PERIOD = Period.ofYears(1)
}