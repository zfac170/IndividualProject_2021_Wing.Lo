package algorithm.clustering.linkage;

import algorithm.clustering.distance.DistanceMethod;

import java.util.List;
/*
Complete
Single
Group Average
 */
public abstract class Linkage{
    protected DistanceMethod distanceMethod;

    public Linkage()  {
        this.distanceMethod = null;
    }

    public void setDistance(DistanceMethod distanceMethod) {
        this.distanceMethod = distanceMethod;
    }

    public abstract double clusterDistance(List<Integer> cluster1, List<Integer> cluster2);

}

