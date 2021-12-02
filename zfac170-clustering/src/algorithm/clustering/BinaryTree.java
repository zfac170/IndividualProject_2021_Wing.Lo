package algorithm.clustering;

import java.util.List;

public class BinaryTree {
    private Node root;
    public BinaryTree(Node root) {
        this.root = root;
    }

    public int numOfLeaves() {
        return countLeaves(root);
    }

    public int numOfLevels() {
        return countLevels(root);
    }

    public Node getRoot() {
        return root;
    }

    private static <T> int countLeaves(Node<T> node)
    {
        List<Node<T>> children = node.getChildren();
        if (children.size() == 0)
        {
            return 1;
        }
        Node<T> left = children.get(0);
        Node<T> right = children.get(1);
        return countLeaves(left) + countLeaves(right);
    }

    private static <T> int countLevels(Node<T> node)
    {
        List<Node<T>> children = node.getChildren();
        if (children.size() == 0)
        {
            return 1;
        }
        Node<T> left = children.get(0);
        Node<T> right = children.get(1);
        return 1+Math.max(countLevels(left), countLevels(right));
    }
}
