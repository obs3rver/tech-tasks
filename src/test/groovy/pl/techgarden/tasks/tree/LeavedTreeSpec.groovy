package pl.techgarden.tasks.tree

import pl.techgarden.tasks.tree.domain.Age
import pl.techgarden.tasks.tree.factory.TreeFactory
import pl.techgarden.tasks.tree.factory.TreeFactoryProducer
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

    def "LeavedTree should be able to grow age"() {
        given: 'LeavedTreeFactory instance'
        TreeFactory leavedTreeFactory = TreeFactoryProducer.leavedTreeFactory()

        and: 'a leaved tree instance'
        Tree leavedTree = leavedTreeFactory.createTree(LEAVED_TREE_NAME, LOCATION)

        when: 'grow procedure was started for 1 year'
        TreeGrowthInfo growthInfo = leavedTree.growFor(ONE_YEAR_PERIOD)

        then: 'growth statistics should get updated'
        leavedTree.growthInfo().age == Age.ONE_YEAR
        growthInfo.age == Age.ONE_YEAR
    }
}
