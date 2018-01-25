package pl.techgarden.tasks.tree.factory

import pl.techgarden.tasks.tree.NeedleLeavedTree
import pl.techgarden.tasks.tree.NeedleLeavedTreeData
import pl.techgarden.tasks.tree.Tree
import pl.techgarden.tasks.tree.domain.Age
import spock.lang.Specification

class NeedleLeavedTreeFactorySpec extends Specification implements NeedleLeavedTreeData {

    def "NeedleLeavedTreeFactory should produce needle leaved tree instance"() {
        given: 'NeedleLeavedTreeFactory instance'
        TreeFactory needleLeavedTreeFactory = TreeFactoryProducer.needleLeavedTreeFactory()

        when: 'a new tree instance was requested'
        Tree needleLeavedTree = aDefaultNeedleLeavedTree(needleLeavedTreeFactory)

        then:
        needleLeavedTree != null
        needleLeavedTree in NeedleLeavedTree
        with(needleLeavedTree) {
            age() == Age.ZERO
            name() == NEEDLE_LEAVED_TREE_NAME
            location() == LOCATION
        }
    }

    private Tree aDefaultNeedleLeavedTree(TreeFactory leavedTreeFactory) {
        leavedTreeFactory.createTree(
                NEEDLE_LEAVED_TREE_NAME,
                LOCATION,
                aDefaultLeavedTreeGrowthConfig()
        )
    }
}
