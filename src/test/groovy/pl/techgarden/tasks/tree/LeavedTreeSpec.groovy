package pl.techgarden.tasks.tree

import pl.techgarden.tasks.tree.domain.Age
import pl.techgarden.tasks.tree.factory.TreeFactory
import pl.techgarden.tasks.tree.growth.Length
import pl.techgarden.tasks.tree.growth.LengthTreeGrowthConfig
import pl.techgarden.tasks.tree.growth.TreeGrowthConfig
import spock.lang.Specification

import static pl.techgarden.tasks.tree.domain.Age.ONE_YEAR
import static pl.techgarden.tasks.tree.factory.TreeFactoryProducer.leavedTreeFactory
import static pl.techgarden.tasks.tree.growth.TreeGrowthInfo.*

class LeavedTreeSpec extends Specification implements TreeData {

    def "tree builder should create LeavedTree object"() {
        when:
        Tree leavedTree = LeavedTree.builder()
                .name(LEAVED_TREE_NAME)
                .location(LOCATION)
                .treeGrowthConfig(aDefaultTreeGrowthConfig())
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
        TreeFactory leavedTreeFactory = leavedTreeFactory()

        and: 'a leaved tree instance'
        Tree leavedTree = aDefaultLeavedTree(leavedTreeFactory)

        when: 'grow procedure was started for 1 year'
        leavedTree.growFor(ONE_YEAR_PERIOD)

        then: 'growth statistics for age should get updated'
        leavedTree.collectGrowthInfo().age == ONE_YEAR
    }

    def "LeavedTree should be able to grow its roots"() {
        given: 'LeavedTreeFactory instance'
        TreeFactory leavedTreeFactory = leavedTreeFactory()

        and: 'a leaved tree instance'
        Tree leavedTree = aDefaultLeavedTree(leavedTreeFactory)

        when: 'grow procedure was started for 1 year'
        leavedTree.growFor(ONE_YEAR_PERIOD)

        then: 'growth statistics for roots should get updated'
        leavedTree.collectGrowthInfo().rootsInfo == expectedRootsGrowthInfo()
    }

    def "LeavedTree should be able to grow its stems"() {
        given: 'LeavedTreeFactory instance'
        TreeFactory leavedTreeFactory = leavedTreeFactory()

        and: 'a leaved tree instance'
        Tree leavedTree = aDefaultLeavedTree(leavedTreeFactory)

        when: 'grow procedure was started for 1 year'
        leavedTree.growFor(ONE_YEAR_PERIOD)

        then: 'growth statistics for stems should get updated'
        leavedTree.collectGrowthInfo().stemsInfo == expectedStemsGrowthInfo()
    }

    def "LeavedTree should be able to grow its branches"() {
        given: 'LeavedTreeFactory instance'
        TreeFactory leavedTreeFactory = leavedTreeFactory()

        and: 'a leaved tree instance'
        Tree leavedTree = aLeavedTreeWithBranches(leavedTreeFactory)

        when: 'grow procedure was started for 1 year'
        leavedTree.growFor(ONE_YEAR_PERIOD)

        then: 'growth statistics for branches should get updated'
        leavedTree.collectGrowthInfo().branchesInfo == expectedBranchesGrowthInfo()
    }

    private Tree aDefaultLeavedTree(TreeFactory leavedTreeFactory) {
        leavedTreeFactory.createTree(
                LEAVED_TREE_NAME,
                LOCATION,
                aDefaultLeavedTreeGrowthConfig()
        )
    }

    private Tree aLeavedTreeWithBranches(TreeFactory leavedTreeFactory) {
        leavedTreeFactory.createTree(
                LEAVED_TREE_NAME,
                LOCATION,
                aDefaultLeavedTreeGrowthConfigWith()
                        .stemsGrowthConfig(aStemNodeGrowthConfigWithCreationOfOtherTypes())
                        .build()
        )
    }

    private static TreePartGrowthInfo expectedRootsGrowthInfo() {
        RootGrowthInfo.builder()
                .treePartCounter(2)
                .value(Length.of(1.0))
                .build()
    }

    private static TreePartGrowthInfo expectedStemsGrowthInfo() {
        StemGrowthInfo.builder()
                .treePartCounter(2)
                .value(Length.of(2.0))
                .build()
    }

    private static expectedBranchesGrowthInfo() {
        BranchGrowthInfo.builder()
                .treePartCounter(1)
                .value(Length.of(0.5))
                .build()
    }

    private static TreeGrowthConfig<Length> aDefaultTreeGrowthConfig() {
        LengthTreeGrowthConfig.builder().build()
    }
}
