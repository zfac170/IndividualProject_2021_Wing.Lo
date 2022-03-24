package algorithm.clustering.linkage;

import algorithm.clustering.distance.PairwiseDistance;

import java.util.Collection;

/*
Complete
Single
Group Average
 */
public abstract class Linkage{
    protected PairwiseDistance distanceMethod;

    public Linkage()  {
        this.distanceMethod = null;
    }

    public void setDistance(PairwiseDistance distanceMethod) {
        this.distanceMethod = distanceMethod;
    }

    public abstract double clusterDistance(Collection<Integer> cluster1, Collection<Integer> cluster2);

}

