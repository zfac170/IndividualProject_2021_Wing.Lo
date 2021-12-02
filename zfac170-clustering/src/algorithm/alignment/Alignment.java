package algorithm.alignment;

public class Alignment {
    private String queryAlignment;
    private String targetAlignment;
    private double similarity;

    public Alignment(String queryAlignment, String targetAlignment, double similarity) {
        this.queryAlignment = queryAlignment;
        this.targetAlignment = targetAlignment;
        this.similarity = similarity;
    }

    public String getQueryAlignment() {
        return queryAlignment;
    }

    public String getTargetAlignment() {
        return targetAlignment;
    }

    public double getSimilarity() {
        return similarity;
    }
}
