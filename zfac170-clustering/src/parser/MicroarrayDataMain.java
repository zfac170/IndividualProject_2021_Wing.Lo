package parser;

import algorithm.clustering.AgglomerativeClustering;
import algorithm.clustering.HierarchicalClustering;
import algorithm.clustering.linkage.SingleLinkage;
import algorithm.clustering.distance.Distance;
import algorithm.clustering.distance.EuclideanDistance;
import algorithm.clustering.distance.MatrixDistance;
import algorithm.clustering.BinaryTree;

import java.io.*;
import java.util.*;
import java.util.List;

public class MicroarrayDataMain {
    public static void main(String[] args) {

    }

    public static BinaryTree runExample() throws IOException {

        // input microarray data

        // transform data to n vectors
        int n = 20;
        int d = 10;
        double[][] vectors = new double[n][d];

        // generate random vectors

        Distance euclidean = new EuclideanDistance(vectors);

        double[][] distance = new double[n][n];
        // find pairwise distance ...
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {

                if (i == j) {
                    distance[i][j] = 0;
                }
                else if (j > i) {
                    distance[i][j] = euclidean.distance(i, j);
                }
                else { // i > j
                    distance[i][j] = distance[j][i];
                }
            }
        }
        System.out.println(Arrays.deepToString(distance).replace("],", "],\n"));
        List<List<Integer>> clusters = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            clusters.add(new ArrayList<>());
            clusters.get(i).add(i);
        }

        MatrixDistance matrixDistance = new MatrixDistance(distance);
        HierarchicalClustering clustering = new AgglomerativeClustering(new SingleLinkage());
        return clustering.fit(matrixDistance, clusters);
    }
}
