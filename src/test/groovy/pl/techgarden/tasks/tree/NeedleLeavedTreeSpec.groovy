package pl.techgarden.tasks.tree

import pl.techgarden.tasks.tree.domain.Age
import pl.techgarden.tasks.tree.factory.TreeFactory
import pl.techgarden.tasks.tree.growth.Length
import pl.techgarden.tasks.tree.growth.LengthTreeGrowthConfig
import pl.techgarden.tasks.tree.growth.TreeGrowthConfig
import spock.lang.Specification

import static pl.techgarden.tasks.tree.domain.Age.ONE_YEAR
import static pl.techgarden.tasks.tree.factory.TreeFactoryProducer.needleLeavedTreeFactory
import static pl.techgarden.tasks.tree.growth.TreeGrowthInfo.*

class NeedleLeavedTreeSpec extends Specification implements NeedleLeavedTreeData {

    def "tree builder should create NeedleLeavedTree object"() {
        when:
        Tree needleLeavedTree = NeedleLeavedTree.builder()
                .name(NEEDLE_LEAVED_TREE_NAME)
                .location(LOCATION)
                .treeGrowthConfig(aDefaultTreeGrowthConfig())
                .build()

        then:
        needleLeavedTree != null
        needleLeavedTree instanceof NeedleLeavedTree
        with(needleLeavedTree) {
            age() == Age.ZERO
            name() == NEEDLE_LEAVED_TREE_NAME
            location() == LOCATION
        }
    }

    def "NeedleLeavedTree should be able to grow age"() {
        given: 'NeedleLeavedTreeFactory instance'
        TreeFactory needleLeavedTreeFactory = needleLeavedTreeFactory()

        and: 'a needle leaved tree instance'
        Tree needleLeavedTree = aDefaultNeedleLeavedTree(needleLeavedTreeFactory)

        when: 'grow procedure was started for 1 year'
        needleLeavedTree.growFor(ONE_YEAR_PERIOD)

        then: 'growth statistics for age should get updated'
        needleLeavedTree.collectGrowthInfo().age == ONE_YEAR
    }

    def "NeedleLeavedTree should be able to grow its roots"() {
        given: 'NeedleLeavedTreeFactory instance'
        TreeFactory needleLeavedTreeFactory = needleLeavedTreeFactory()

        and: 'a needle leaved tree instance'
        Tree needleLeavedTree = aDefaultNeedleLeavedTree(needleLeavedTreeFactory)

        when: 'grow procedure was started for 1 year'
        needleLeavedTree.growFor(ONE_YEAR_PERIOD)

        then: 'growth statistics for roots should get updated'
        needleLeavedTree.collectGrowthInfo().rootsInfo == expectedRootsGrowthInfo()
    }

    def "NeedleLeavedTree should be able to grow its stems"() {
        given: 'NeedleLeavedTreeFactory instance'
        TreeFactory needleLeavedTreeFactory = needleLeavedTreeFactory()

        and: 'a needle leaved tree instance'
        Tree needleLeavedTree = aDefaultNeedleLeavedTree(needleLeavedTreeFactory)

        when: 'grow procedure was started for 1 year'
        needleLeavedTree.growFor(ONE_YEAR_PERIOD)

        then: 'growth statistics for stems should get updated'
        needleLeavedTree.collectGrowthInfo().stemsInfo == expectedStemsGrowthInfo()
    }

    def "NeedleLeavedTree should be able to grow its branches"() {
        given: 'NeedleLeavedTreeFactory instance'
        TreeFactory needleLeavedTreeFactory = needleLeavedTreeFactory()

        and: 'a needle leaved tree instance'
        Tree needleLeavedTree = aNeedleLeavedTreeWithBranches(needleLeavedTreeFactory)

        when: 'grow procedure was started for 1 year'
        needleLeavedTree.growFor(ONE_YEAR_PERIOD)

        then: 'growth statistics for branches should get updated'
        needleLeavedTree.collectGrowthInfo().branchesInfo == expectedBranchesGrowthInfo()
    }

    def "NeedleLeavedTree should be able to grow its needle leaves"() {
        given: 'NeedleLeavedTreeFactory instance'
        TreeFactory needleLeavedTreeFactory = needleLeavedTreeFactory()

        and: 'a needle leaved tree instance'
        Tree needleLeavedTree = aNeedleLeavedTreeWithLeaves(needleLeavedTreeFactory)

        when: 'grow procedure was started for 1 year'
        needleLeavedTree.growFor(ONE_YEAR_PERIOD)

        then: 'growth statistics for leaves should get updated'
        def treeGrowthInfo = needleLeavedTree.collectGrowthInfo()
        treeGrowthInfo.leavesInfo == expectedLeavesGrowthInfo()
    }

    private Tree aDefaultNeedleLeavedTree(TreeFactory leavedTreeFactory) {
        leavedTreeFactory.createTree(
                NEEDLE_LEAVED_TREE_NAME,
                LOCATION,
                aDefaultNeedleLeavedTreeGrowthConfig()
        )
    }

    private Tree aNeedleLeavedTreeWithBranches(TreeFactory leavedTreeFactory) {
        leavedTreeFactory.createTree(
                NEEDLE_LEAVED_TREE_NAME,
                LOCATION,
                aDefaultNeedleLeavedTreeGrowthConfigWith()
                        .stemsGrowthConfig(aStemNodeGrowthConfigWithCreationOfOtherTypes())
                        .build()
        )
    }

    private Tree aNeedleLeavedTreeWithLeaves(TreeFactory leavedTreeFactory) {
        leavedTreeFactory.createTree(
                NEEDLE_LEAVED_TREE_NAME,
                LOCATION,
                aDefaultNeedleLeavedTreeGrowthConfigWith()
                        .stemsGrowthConfig(aStemNodeGrowthConfigWithCreationOfOtherTypesAndIncreasedDepth())
                        .branchesGrowthConfig(aBranchNodeGrowthConfigWithCreationOfOtherTypes())
                        .leavesGrowthConfig(aNeedleLeavedTreeLeafGrowthConfig())
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

    private static expectedLeavesGrowthInfo() {
        LeafLikeGrowthInfo.builder()
                .treePartCounter(1)
                .value(Length.of(0.1))
                .build()
    }

    private static TreeGrowthConfig<Length> aDefaultTreeGrowthConfig() {
        LengthTreeGrowthConfig.builder().build()
    }
}
