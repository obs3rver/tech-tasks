package pl.techgarden.tasks.tree

import pl.techgarden.tasks.tree.growth.Length
import pl.techgarden.tasks.tree.growth.TreePartGrowthConfig
import spock.lang.Specification

import static pl.techgarden.tasks.tree.growth.LengthTreeGrowthConfig.LengthTreePartGrowthConfig
import static pl.techgarden.tasks.tree.growth.TreeGrowthInfo.TreePartGrowthInfo

class RootSpec extends Specification implements TreeData {

    def "Roots node should grow 2 roots after 1 year with 1 level of depth update"() {
        given: 'Root node growth config'
        TreePartGrowthConfig<Length> rootsGrowthConfig = aRootsNodeGrowthConfig()

        and: 'Root node'
        LengthGrowableTreePart rootsNode = Root.of(rootsGrowthConfig)

        when: 'grow process was requested for 1 year period'
        TreePartGrowthInfo<Length> rootsGrowthInfo = rootsNode.growFor(ONE_YEAR_PERIOD)

        then: 'two roots were grown with summary length of 1m'
        rootsGrowthInfo.treePartCounter() == 2
        rootsGrowthInfo.traitSum() == Length.of(1.0)
    }

    def "Roots node should grow 3 roots after 2 years with 1 level of depth"() {
        given: 'Root node growth config'
        TreePartGrowthConfig<Length> rootsGrowthConfig = aRootsNodeGrowthConfig()

        and: 'Root node'
        LengthGrowableTreePart rootsNode = Root.of(rootsGrowthConfig)

        when: 'grow process was requested for 1 year period'
        TreePartGrowthInfo<Length> rootsGrowthInfo = rootsNode.growFor(TWO_YEARS_PERIOD)

        then: '3 roots were grown with summary length of 4m'
        rootsGrowthInfo.treePartCounter() == 3
        rootsGrowthInfo.traitSum() == Length.of(4.0)
    }

    private TreePartGrowthConfig<Length> aRootsNodeGrowthConfig() {
        LengthTreePartGrowthConfig.builder()
                .increaseCountOfTreePartBy(1)
                .increaseCountOfTreePartEvery(ONE_YEAR_PERIOD)
                .depthCount(1)
                .growthStrategy { it + Length.of(0.5) }
                .build()
    }

}
