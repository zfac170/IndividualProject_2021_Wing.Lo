package algorithm.alignment;

import java.util.Map;

public class ScoringMatrix {
    private double[][] matrix;
    // map DNA character to index
    private Map<Character, Integer> nucleotides;

    public ScoringMatrix(Map<Character, Integer> map, double[][] matrix) {
        this.matrix = matrix;
        this.nucleotides = map;
    }

    public double score(char a, char b) {
        return matrix[nucleotides.get(a)][nucleotides.get(b)];
    }
}
