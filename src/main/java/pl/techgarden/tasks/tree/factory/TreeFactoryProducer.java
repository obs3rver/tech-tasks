package pl.techgarden.tasks.tree.factory;

public class TreeFactoryProducer {

    public static TreeFactory leavedTreeFactory() {
        return new LeavedTreeFactory();
    }

    public static TreeFactory needleLeavedTreeFactory() {
        return new NeedleLeavedTreeFactory();
    }

}
