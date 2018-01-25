package pl.techgarden.tasks.tree

import pl.techgarden.tasks.tree.growth.Length
import pl.techgarden.tasks.tree.growth.TreeGrowthConfig
import spock.lang.Specification

import static pl.techgarden.tasks.tree.growth.LengthTreeGrowthConfig.LengthTreePartGrowthConfig

class RootSpec extends Specification implements LeavedTreeData {

    def "Roots node should grow 2 roots after 1 year with 1 level of depth update"() {
        given: 'Roots node growth config'
        TreeGrowthConfig<Length> rootsGrowthConfig = aRootsNodeGrowthConfig()

        and: 'Root node'
        LengthGrowableTreePart rootsNode = Root.of(rootsGrowthConfig)

        when: 'grow process was requested for 1 year period'
        rootsNode.growFor(ONE_YEAR_PERIOD)
        def growthInfo = rootsNode.collectAllGrowthInfoSum()

        then: '2 roots were grown'
        growthInfo.treePartCounter() == 2
        growthInfo.value() == Length.of(1.0)
    }

    def "Roots node should grow 5 roots after 2 years with 1 level of depth"() {
        given: 'Roots node growth config'
        TreeGrowthConfig<Length> rootsGrowthConfig = aRootsNodeGrowthConfig()

        and: 'Root node'
        LengthGrowableTreePart rootsNode = Root.of(rootsGrowthConfig)

        when: 'grow process was requested for 1 year period'
        rootsNode.growFor(TWO_YEARS_PERIOD)
        def growthInfo = rootsNode.collectAllGrowthInfoSum()

        then: '3 roots were grown'
        growthInfo.treePartCounter() == 3
        growthInfo.value() == Length.of(2.5)
    }

    private def aRootsNodeGrowthConfig() {
        aDefaultLeavedTreeGrowthConfigWith()
                .rootsGrowthConfig(
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
