package algorithm.alignment;

import algorithm.alignment.ScoringMatrix;

import java.util.HashMap;
import java.util.Map;

public class BLOSUM extends ScoringMatrix {
    private static final double[][] matrix = {
            {10, -1, -3, -4},
            {-1, 7, -5, -3},
            {-3, -5, 9 , 0},
            {-4, -3, 0 , 8},
    };
    private static final Map<Character, Integer> map = new HashMap<>(){{
        put('A', 0);
        put('G', 1);
        put('C', 2);
        put('T', 3);
    }};
    public BLOSUM() {
        super(map, matrix);
    }
}
