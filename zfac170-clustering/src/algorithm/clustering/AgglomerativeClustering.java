package algorithm.clustering;

import algorithm.clustering.distance.PairwiseDistance;
import algorithm.clustering.linkage.Linkage;

import java.util.ArrayList;
import java.util.List;

public class AgglomerativeClustering extends HierarchicalClustering {

    public AgglomerativeClustering(Linkage linkage){
        super(linkage);
    }

    @Override
    public BinaryTree fit(PairwiseDistance distanceMethod) {

        List<List<Integer>> clusters = new ArrayList<>();
        int n = distanceMethod.size();
        for (int i = 0; i < n; ++i) {
            clusters.add(new ArrayList<>());
            clusters.get(i).add(i);
        }

        this.linkage.setDistance(distanceMethod);
        List<Node> nodes = new ArrayList<>();
        // {{1}. {2}, ....}
        for (List<Integer> c : clusters) {
            nodes.add(new Node(c.get(0)));
        }
        while (clusters.size() > 1) {
            int index_i = 0;
            int index_j = 0;
            double minimum = Double.MAX_VALUE;
            for (int i = 0; i < clusters.size(); ++i) {
                for (int j = i + 1; j < clusters.size(); ++j) {
                    // a defined distance measurement for two clusters - linkage method
                    // distance between two clusters
                    // a kind of aggregation
                    double d = this.linkage.clusterDistance(clusters.get(i), clusters.get(j));
                    if (d < minimum) {
                        minimum = d;
                        index_i = i;
                        index_j = j;
                    }
                }
            }

            Node left = nodes.get(index_i);
            Node right = nodes.get(index_j);
            nodes.set(index_i, new Node(left, right, minimum));
            nodes.remove(index_j);

            List<Integer> c_i = clusters.get(index_i);
            List<Integer> c_j = clusters.get(index_j);
            c_i.addAll(c_j);
            clusters.remove(index_j);
        }
        Node root = nodes.get(0);
        BinaryTree tree = new BinaryTree(root);
        return tree;
    }
}
