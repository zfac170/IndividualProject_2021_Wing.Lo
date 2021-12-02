package algorithm.alignment;

public class AlignmentAlgo {
    protected double penalty;
    protected ScoringMatrix matrix;

    public AlignmentAlgo(ScoringMatrix m, double penalty){
        this.matrix = m;
        this.penalty = penalty;
    }

    public Alignment align(String s1, String s2) {
        return new Alignment("", "", 0);
    }

    public void setPenalty(double p) {
        penalty = p;
    }

    public double getPenalty() {
        return penalty;
    }
}
