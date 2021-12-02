package algorithm.clustering.linkage;

import algorithm.clustering.distance.Distance;

import java.util.List;
/*
Complete
Single
Group Average
 */
public abstract class Linkage{
    protected Distance distance;

    public Linkage()  {
        this.distance = null;
    }

    public void setDistance(Distance distance) {
        this.distance = distance;
    }

    public abstract double clusterDistance(List<Integer> cluster1, List<Integer> cluster2);

}

