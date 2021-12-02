package algorithm.clustering;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Node<T>
{
    private final T data;
    private final List<Node<T>> children;

    // leaf node case with seq name data
    Node(T data)
    {
        this.data = data;
        this.children = Collections.emptyList();
    }

    // joining node with distance data
    Node(Node<T> child0, Node<T> child1, T data)
    {
        this.data = data;

        List<Node<T>> list = new ArrayList<Node<T>>();
        list.add(child0);
        list.add(child1);
        this.children = Collections.unmodifiableList(list);
    }

    public T getData()
    {
        return data;
    }

    public List<Node<T>> getChildren()
    {
        return children;
    }
}

