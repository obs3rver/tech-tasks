package pl.techgarden.tasks.tree.factory

import pl.techgarden.tasks.tree.LeavedTree
import pl.techgarden.tasks.tree.LeavedTreeData
import pl.techgarden.tasks.tree.Tree
import pl.techgarden.tasks.tree.domain.Age
import spock.lang.Specification

class LeavedTreeFactorySpec extends Specification implements LeavedTreeData {

    def "LeavedTreeFactory should produce leaved tree instance"() {
        given: 'LeavedTreeFactory instance'
        TreeFactory leavedTreeFactory = TreeFactoryProducer.leavedTreeFactory()

        when: 'a new tree instance was requested'
        Tree leavedTree = aDefaultLeavedTree(leavedTreeFactory)

        then:
        leavedTree != null
        leavedTree in LeavedTree
        with(leavedTree) {
            age() == Age.ZERO
            name() == LEAVED_TREE_NAME
            location() == LOCATION
        }
    }

    private Tree aDefaultLeavedTree(TreeFactory leavedTreeFactory) {
        leavedTreeFactory.createTree(
                LEAVED_TREE_NAME,
                LOCATION,
                aDefaultLeavedTreeGrowthConfig()
        )
    }
}
