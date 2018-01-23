package pl.techgarden.tasks.tree

import pl.techgarden.tasks.tree.growth.Length
import pl.techgarden.tasks.tree.growth.TreePartGrowthConfig
import spock.lang.Specification

import static pl.techgarden.tasks.tree.growth.LengthTreeGrowthConfig.LengthTreePartGrowthConfig

class StemSpec extends Specification implements TreeData {

    def "Stem node should increase length after one season"() {
        given: 'Stem node growth config'
        TreePartGrowthConfig<Length> stemsGrowthConfig = aStemsNodeGrowthConfigWithoutChildCreation()

        and: 'Stem node'
        LengthGrowableTreePart stemsNode = Stem.of(stemsGrowthConfig)

        when: 'grow process was requested for 1 year period'
        stemsNode.growFor(ONE_YEAR_PERIOD)

        then: 'summary length should equal 1m'
        def growthInfo = stemsNode.collectSummaryGrowthInfo()
        growthInfo.treePartCounter() == 1
        growthInfo.value() == Length.of(1.0)
    }

    def "Stem node should grow a new stem after one season"() {
        given: 'Stem node growth config'
        TreePartGrowthConfig<Length> stemsGrowthConfig = aStemsNodeGrowthConfigWithChildCreation()

        and: 'Stem node'
        LengthGrowableTreePart stemsNode = Stem.of(stemsGrowthConfig)

        when: 'grow process was requested for 1 year period'
        stemsNode.growFor(ONE_YEAR_PERIOD)
        def growthInfo = stemsNode.collectSummaryGrowthInfo()

        then: 'there are 2 stems now'
        growthInfo.treePartCounter() == 2
        growthInfo.value() == Length.of(2.0)
    }

    def "Stem node should grow 2 new stems after two seasons"() {
        given: 'Stem node growth config'
        TreePartGrowthConfig<Length> stemsGrowthConfig = aStemsNodeGrowthConfigWithChildCreation()

        and: 'Stem node'
        LengthGrowableTreePart stemsNode = Stem.of(stemsGrowthConfig)

        when: 'grow process was requested for 1 year period'
        stemsNode.growFor(TWO_YEARS_PERIOD)
        def growthInfo = stemsNode.collectSummaryGrowthInfo()

        then: 'there are 3 stems now'
        growthInfo.treePartCounter() == 3
        growthInfo.value() == Length.of(5.0)
    }

    private TreePartGrowthConfig<Length> aStemsNodeGrowthConfigWithoutChildCreation() {
        LengthTreePartGrowthConfig.builder()
                .increaseCountOfTreePartBy(0)
                .increaseCountOfTreePartEvery(ONE_YEAR_PERIOD)
                .chooseIdenticalTypeOfNewChildTreePart(true)
                .depthCount(0)
                .growthStrategy { it + Length.of(1.0) }
                .build()
    }

    private TreePartGrowthConfig<Length> aStemsNodeGrowthConfigWithChildCreation() {
        LengthTreePartGrowthConfig.builder()
                .increaseCountOfTreePartBy(1)
                .increaseCountOfTreePartEvery(ONE_YEAR_PERIOD)
                .chooseIdenticalTypeOfNewChildTreePart(true)
                .depthCount(1)
                .growthStrategy { it + Length.of(1.0) }
                .build()
    }

}
