package pl.techgarden.tasks.tree

import pl.techgarden.tasks.tree.growth.LeafLikeGrowthConfig
import pl.techgarden.tasks.tree.growth.Length
import spock.lang.Specification

class LeafSpec extends Specification implements TreeData {

    def "Leaf should increase length after one season"() {
        given: 'Leaf growth config'
        LeafLikeGrowthConfig<Length> leavesGrowthConfig = aLeafGrowthConfig()

        and: 'a Leaf instance'
        LengthGrowableTreePart leaf = Leaf.of(leavesGrowthConfig)

        when: 'grow process was requested for 1 year period'
        leaf.growFor(ONE_YEAR_PERIOD)

        then: 'summary length should equal 0.1m'
        def growthInfo = leaf.growthInfo()
        growthInfo.treePartCounter() == 1
        growthInfo.value() == Length.of(0.1)
    }

}
