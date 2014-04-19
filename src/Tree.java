import java.util.Arrays;
import java.util.List;

/**
 * Created by antonkov on 4/5/14.
 */
public class Tree {
    String node;

    List<Tree> children;
    String details;

    public Tree(String node, String details, Tree... children) {
        this.node = node;
        this.details = details;
        this.children = Arrays.asList(children);
    }

    public Tree(String node, Tree... children) {
        this.node = node;
        this.children = Arrays.asList(children);
    }

    public Tree(String node, String details) {
        this.node = node;
        this.details = details;
    }

    public Tree(String node) {
        this.node = node;
    }
}
