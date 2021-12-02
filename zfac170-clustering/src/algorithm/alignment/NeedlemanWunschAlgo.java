package algorithm.alignment;

public class NeedlemanWunschAlgo extends AlignmentAlgo {

    public NeedlemanWunschAlgo(ScoringMatrix m, double penalty) {
        super(m, penalty);
    }

    private double[][] calculateF(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        double[][] F = new double[m + 1][n + 1];
        for (int i = 0; i <= m; ++i) {
            F[i][0] = i * penalty;
        }

        for (int j = 0; j <= n; ++j) {
            F[0][j] = j * penalty;
        }

        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                double score = this.matrix.score(s1.charAt(i - 1), s2.charAt(j - 1));
                // F[i][j] = max( F[i - 1][j - 1] + s(i, j), F[i - 1][j] + d , F[i][j - 1] + d)
                F[i][j] = Math.max(F[i - 1][j - 1] + score,
                        Math.max(F[i-1][j] + penalty,
                                F[i][j-1] + penalty));
            }
        }

        return F;
    }

    private void traceback(double[][] F, String s1, String s2,
                          StringBuilder alignment1, StringBuilder alignment2) {
        int i = s1.length();
        int j = s2.length();
        while (i > 0 || j > 0) {
            if (j == 0 || i > 1 && F[i][j] == F[i - 1][j] + penalty) {
                alignment1.append(s1.charAt(i - 1));
                alignment2.append('-');
                i -= 1;
            } else if (i == 0 || j > 1 && F[i][j] == F[i][j- 1] + penalty) {
                alignment1.append('-');
                alignment2.append(s2.charAt(j - 1));
                j -= 1;
            } else if (i >= 1 && j >= 1) {
                double score = this.matrix.score(s1.charAt(i - 1), s2.charAt(j - 1));
                if (F[i][j] == F[i - 1][j - 1] + score) {
                    alignment1.append(s1.charAt(i - 1));
                    alignment2.append(s2.charAt(j - 1));
                    i -= 1;
                    j -= 1;
                }
            }
        }
    }

    public Alignment align(String s1, String s2) {
        double[][] F = calculateF(s1, s2);
        // System.out.println(Arrays.deepToString(F).replace("],", "],\n"));
        StringBuilder alignment1 = new StringBuilder();
        StringBuilder alignment2 = new StringBuilder();
        traceback(F, s1, s2, alignment1, alignment2);
        String result1 = alignment1.reverse().toString();
        String result2 = alignment2.reverse().toString();
        return new Alignment(result1, result2, F[s1.length()][s2.length()]);
     }
}
