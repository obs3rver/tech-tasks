package pl.techgarden.tasks.tree

import pl.techgarden.tasks.tree.domain.Age
import spock.lang.Specification

class LeavedTreeSpec extends Specification implements TreeData {

    def "tree builder should create LeavedTree object"() {
        when:
        Tree leavedTree = LeavedTree.builder()
                .name(LEAVED_TREE_NAME)
                .location(LOCATION)
                .build()

        then:
        leavedTree != null
        leavedTree instanceof LeavedTree
        with(leavedTree) {
            age() == Age.ZERO
            name() == LEAVED_TREE_NAME
            location() == LOCATION
        }
    }
}
