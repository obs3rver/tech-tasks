package pl.techgarden.tasks.tree.growth;

public interface GrowthTrait {
    GrowthTrait plus(GrowthTrait delta);
    double asDouble();
}
