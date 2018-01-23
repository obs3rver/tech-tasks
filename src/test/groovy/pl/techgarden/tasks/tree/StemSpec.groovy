package pl.techgarden.tasks.tree

import pl.techgarden.tasks.tree.growth.Length
import pl.techgarden.tasks.tree.growth.TreePartGrowthConfig
import spock.lang.Specification

import static pl.techgarden.tasks.tree.growth.LengthTreeGrowthConfig.LengthTreePartGrowthConfig
import static pl.techgarden.tasks.tree.growth.TreeGrowthInfo.TreePartGrowthInfo

class StemSpec extends Specification implements TreeData {

    def "Stem main node should increase length after one season"() {
        given: 'Stem node growth config'
        TreePartGrowthConfig<Length> stemsGrowthConfig = aStemsNodeGrowthConfigForOneYear()

        and: 'Stem node'
        LengthGrowableTreePart stemsNode = Stem.of(stemsGrowthConfig)

        when: 'grow process was requested for 1 year period'
        TreePartGrowthInfo<Length> stemsGrowthInfo = stemsNode.growFor(ONE_YEAR_PERIOD)

        then: 'summary length should equal 1m'
        stemsGrowthInfo.treePartCounter() == 1
        stemsGrowthInfo.traitSum() == Length.of(1.0)
    }

    def "Stem main node should grow a new stem after one season"() {
        given: 'Stem node growth config'
        TreePartGrowthConfig<Length> stemsGrowthConfig = aStemsNodeGrowthConfigWithCreation()

        and: 'Stem node'
        LengthGrowableTreePart stemsNode = Stem.of(stemsGrowthConfig)

        when: 'grow process was requested for 1 year period'
        TreePartGrowthInfo<Length> stemsGrowthInfo = stemsNode.growFor(ONE_YEAR_PERIOD)

        then: 'summary length should equal 8m'
        stemsGrowthInfo.treePartCounter() == 2
        stemsGrowthInfo.traitSum() == Length.of(2.0)
    }

    private TreePartGrowthConfig<Length> aStemsNodeGrowthConfigForOneYear() {
        LengthTreePartGrowthConfig.builder()
                .increaseCountOfTreePartBy(0)
                .increaseCountOfTreePartEvery(ONE_YEAR_PERIOD)
                .depthCount(0)
                .growthStrategy { it + Length.of(1.0) }
                .build()
    }

    private TreePartGrowthConfig<Length> aStemsNodeGrowthConfigWithCreation() {
        LengthTreePartGrowthConfig.builder()
                .increaseCountOfTreePartBy(1)
                .increaseCountOfTreePartEvery(ONE_YEAR_PERIOD)
                .depthCount(1)
                .growthStrategy { it + Length.of(1.0) }
                .build()
    }

}
