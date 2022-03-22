package algorithm.clustering;

import algorithm.clustering.distance.DistanceMethod;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Arrays;
import java.util.Random;

public class KMeans {

    private int k;

    private int maxIteration;

    private DistanceMethod distanceMethod;
    private int seed;

    private String initialMethoid;

    public class KMeansResult {
        public int[] labels;
        public double[][] centroids;
        public double inertia;

        public KMeansResult(int[] labels, double[][] centroids, double inertia) {
            this.labels = labels;
            this.inertia = inertia;
            this.centroids = centroids;
        }

    }

    public KMeans(int k, int maxIteration, DistanceMethod distanceMethod, int seed, String initialMethoid) {
        this.k = k;
        this.maxIteration = maxIteration;
        this.distanceMethod = distanceMethod;
        this.seed = seed;
        // kmeans++ or random
        this.initialMethoid = initialMethoid;
    }

    private double[][] randomCentroids(double[][] data, int k) {
        int d = data[0].length;
        double[] mins = new double[d];
        double[] maxs = new double[d];
        Arrays.fill(mins, Double.POSITIVE_INFINITY);
        Arrays.fill(maxs, Double.NEGATIVE_INFINITY);

        for (double[] record : data) {
            for (int i = 0; i < d; ++i) {
                mins[i] = Math.min(mins[i], record[i]);
                maxs[i] = Math.max(maxs[i], record[i]);
            }
        }

        Random rand = new Random(seed);

        double[][] centroids = new double[k][d];
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < d; ++j) {
                centroids[i][j] = rand.nextDouble() * (maxs[j] - mins[j]) + mins[j];
            }
        }
        return centroids;
    }

    private double[][] KmeansPlusPlus(double[][] data, int k) {
        int d = data[0].length;
        int n = data.length;
        double[][] centroids = new double[k][d];
        double[] distToClosestCentroid = new double[n];
        double[] weightedDistribution  = new double[n];

        Random gen = new Random(seed);
        int choose = 0;

        for (int c = 0; c < k; c++) {
            if (c == 0)
                choose = gen.nextInt(n);
            else {
                for (int p = 0; p < n; p++) {
                    double tempDistance = distanceMethod.distance(data[p], centroids[c - 1]);

                    if (c == 1)
                        distToClosestCentroid[p] = tempDistance;

                    else {
                        if (tempDistance < distToClosestCentroid[p])
                            distToClosestCentroid[p] = tempDistance;
                    }

                    if (p == 0)
                        weightedDistribution[0] = distToClosestCentroid[0];
                    else
                        weightedDistribution[p] = weightedDistribution[p - 1] + distToClosestCentroid[p];

                }

                double rand = gen.nextDouble();
                for (int j = n - 1; j > 0; j--) {
                    if (rand > weightedDistribution[j - 1] / weightedDistribution[n - 1]) {
                        choose = j;
                        break;
                    }
                    else
                        choose = 0;
                }
            }

            System.arraycopy(data[choose], 0, centroids[c], 0, d);
        }
        return centroids;
    }

    private Pair<Integer, Double> nearestCentroid(double[][] centroids, double[] record) {
        double minDistance = Double.POSITIVE_INFINITY;
        int k = -1;
        for (int i = 0; i < centroids.length; ++i) {
            double dist = distanceMethod.distance(record, centroids[i]);
            if (dist < minDistance) {
                minDistance = dist;
                k = i;
            }
        }
        return new ImmutablePair<>(k, minDistance);
    }

    public KMeansResult fit(double[][] data) {
        int n = data.length;
        int d = data[0].length;

        double[][] centroids = (this.initialMethoid.equals("kmeans++"))? KmeansPlusPlus(data, k) : randomCentroids(data, k);

        int[] labels = new int[n];
        double inertia = 0;

        for (int iter = 0; iter < maxIteration; iter++) {
            inertia = 0;
            for (int i = 0; i < n; i++) {
                Pair<Integer, Double> pair = nearestCentroid(centroids, data[i]);
                int index = pair.getLeft();
                double minDist = pair.getRight();
                inertia += minDist * minDist;
                labels[i] = index;
            }

            int[] count = new int[k];
            // initialize centroid
            for (int i = 0; i < k; i++) {
                Arrays.fill(centroids[i], 0);
            }
            // relocate centroid
            for (int i = 0; i < n; i++) {
                int cluster = labels[i];
                for (int j = 0; j < d; j++) {
                    centroids[cluster][j] += data[cluster][j];
                }
                count[cluster] += 1;
            }

            for (int i = 0; i < k; i++) {
                for (int j = 0; j < d; j++) {
                    centroids[i][j] /= count[i];
                }
            }

            if (iter == maxIteration - 1) {
                break;
            }
        }
        return new KMeansResult(labels, centroids, inertia);
    }

    public int getK() {
        return k;
    }

    public void setK(int k) {
        this.k = k;
    }

    public int getMaxIteration() {
        return maxIteration;
    }

    public void setMaxIteration(int maxIteration) {
        this.maxIteration = maxIteration;
    }


    public void setDistanceMethod(DistanceMethod distanceMethod) {
        this.distanceMethod = distanceMethod;
    }
}
