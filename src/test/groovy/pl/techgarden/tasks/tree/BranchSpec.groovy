package pl.techgarden.tasks.tree

import pl.techgarden.tasks.tree.growth.Length
import pl.techgarden.tasks.tree.growth.TreeGrowthConfig
import spock.lang.Specification

import static pl.techgarden.tasks.tree.growth.LengthTreeGrowthConfig.LengthTreePartGrowthConfig

class BranchSpec extends Specification implements TreeData {

    def "Branch node should increase length after one season"() {
        given: 'Branch node growth config'
        TreeGrowthConfig<Length> stemsGrowthConfig = aBranchNodeGrowthConfigWithoutChildCreation()

        and: 'Branch node'
        LengthGrowableTreePart stemsNode = Branch.of(stemsGrowthConfig)

        when: 'grow process was requested for 1 year period'
        stemsNode.growFor(ONE_YEAR_PERIOD)

        then: 'summary length should equal 0.5m'
        def growthInfo = stemsNode.collectAllGrowthInfoSum()
        growthInfo.treePartCounter() == 1
        growthInfo.value() == Length.of(0.5)
    }

    def "Branch node should grow a new stem after one season"() {
        given: 'Branch node growth config'
        TreeGrowthConfig<Length> stemsGrowthConfig = aBranchNodeGrowthConfigWithChildCreation()

        and: 'Branch node'
        LengthGrowableTreePart stemsNode = Branch.of(stemsGrowthConfig)

        when: 'grow process was requested for 1 year period'
        stemsNode.growFor(ONE_YEAR_PERIOD)
        def growthInfo = stemsNode.collectAllGrowthInfoSum()

        then: 'there are 2 stems now'
        growthInfo.treePartCounter() == 2
        growthInfo.value() == Length.of(1.0)
    }

    def "Branch node should grow 2 new stems after two seasons"() {
        given: 'Branch node growth config'
        TreeGrowthConfig<Length> stemsGrowthConfig = aBranchNodeGrowthConfigWithChildCreation()

        and: 'Branch node'
        LengthGrowableTreePart stemsNode = Branch.of(stemsGrowthConfig)

        when: 'grow process was requested for 1 year period'
        stemsNode.growFor(TWO_YEARS_PERIOD)
        def growthInfo = stemsNode.collectAllGrowthInfoSum()

        then: 'there are 3 stems now'
        growthInfo.treePartCounter() == 3
        growthInfo.value() == Length.of(2.5)
    }

    private def aBranchNodeGrowthConfigWithoutChildCreation() {
        aDefaultLeavedTreeGrowthConfigWith()
                .branchesGrowthConfig(
                LengthTreePartGrowthConfig.builder()
                        .increaseCountOfTreePartBy(0)
                        .increaseCountOfTreePartEvery(ONE_YEAR_PERIOD)
                        .chooseIdenticalTypeOfNewChildTreePart(true)
                        .depthCount(0)
                        .growthStrategy { it + Length.of(0.5) }
                        .build()
        ).build()
    }

    private def aBranchNodeGrowthConfigWithChildCreation() {
        aDefaultLeavedTreeGrowthConfigWith()
                .branchesGrowthConfig(
                LengthTreePartGrowthConfig.builder()
                        .increaseCountOfTreePartBy(1)
                        .increaseCountOfTreePartEvery(ONE_YEAR_PERIOD)
                        .chooseIdenticalTypeOfNewChildTreePart(true)
                        .depthCount(1)
                        .growthStrategy { it + Length.of(0.5) }
                        .build()
        ).build()
    }

}
