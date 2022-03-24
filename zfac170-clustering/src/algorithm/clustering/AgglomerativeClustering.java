package algorithm.clustering;

import algorithm.clustering.distance.PairwiseDistance;
import algorithm.clustering.linkage.Linkage;

import java.util.*;

public class AgglomerativeClustering extends HierarchicalClustering {

    public AgglomerativeClustering(Linkage linkage){
        super(linkage);
    }

    class Cluster {

        private Set<Integer> elements;

        int iterationID;
        // create cluster with single element;
        Cluster(int i) {
            iterationID = i;
            elements = new HashSet<>();
            elements.add(i);
        }

        public Set<Integer> getElements() {
            return elements;
        }

        int size() {
            return elements.size();
        }

        int getIterationID() {
            return iterationID;
        }

        void addAll(Cluster another) {
            elements.addAll(another.elements);
        }
    }

    @Override
    public List<List<Double>> fit(PairwiseDistance distanceMethod) {

        List<Cluster> clusters = new ArrayList<>();
        int n = distanceMethod.size();

        // {{1}. {2}, ....}
        for (int i = 0; i < n; ++i) {
            clusters.add(new Cluster(i));
        }

        List<List<Double>> encoding = new ArrayList<>();

        this.linkage.setDistance(distanceMethod);
        int iter = 0;
        while (clusters.size() > 1) {
            int index_i = 0;
            int index_j = 0;
            double min_dist = Double.POSITIVE_INFINITY;
            for (int i = 0; i < clusters.size(); ++i) {
                for (int j = i + 1; j < clusters.size(); ++j) {
                    // a defined distance measurement for two clusters - linkage method
                    // distance between two clusters
                    // a kind of aggregation
                    double d = this.linkage.clusterDistance(clusters.get(i).getElements(), clusters.get(j).getElements());
                    if (d < min_dist) {
                        min_dist = d;
                        index_i = i;
                        index_j = j;
                    }
                }
            }

            Cluster c_i = clusters.get(index_i);
            Cluster c_j = clusters.get(index_j);

            c_i.addAll(c_j);

            clusters.remove(index_j);

            List<Double> line = Arrays.asList((double)c_i.iterationID, (double)c_j.iterationID, (double)min_dist, (double)c_i.size());
            encoding.add(line);
            // System.out.printf("[%d, %d, %f, %d]\n", c_i.iterationID, c_j.iterationID, min_dist, c_i.size());

            c_i.iterationID = iter + n;
            iter++;
        }

        return encoding;
    }
}
