import algorithm.alignment.AlignmentAlgo;
import algorithm.alignment.BLOSUM;
import algorithm.alignment.NeedlemanWunschAlgo;
import algorithm.alignment.ScoringMatrix;
import algorithm.clustering.AgglomerativeClustering;
import algorithm.clustering.HierarchicalClustering;
import algorithm.clustering.distance.MatrixDistance;
import algorithm.clustering.linkage.SingleLinkage;
import com.github.sh0nk.matplotlib4j.Plot;
import com.github.sh0nk.matplotlib4j.PythonConfig;
import com.github.sh0nk.matplotlib4j.PythonExecutionException;
import org.junit.Test;
import parser.SequenceData;
import parser.SequenceDataUtil;
import parser.SequenceFileFormat;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.*;

/**
 * @author WinkieLo
 *
 * To test SequenceDataMain reading.
 */
public class SequenceDataTest {
    @Test
    public void testFileFormat() {
        SequenceFileFormat format = SequenceDataUtil.inferSeqFormat("xxx.fasta");
        assertEquals(format, SequenceFileFormat.FASTA);
    }

    @Test
    public void testReadFastaSequenceData() throws IOException {
        Map<String, String> seqMap = new LinkedHashMap<>();
        seqMap.put("SEQUENCE_1", "GTAATCTAAC");
        seqMap.put("SEQUENCE_2", "GATTACA");
        seqMap.put("SEQUENCE_3", "GTACTAATC");
        seqMap.put("SEQUENCE_4", "TTACATGGA");

        Map<String, String> parsedMap = SequenceDataUtil.parseSequenceData("xxx.fasta");
        assertEquals(seqMap, parsedMap);
    }

    /**
     * To check SequenceData class
     *
     */
    @Test
    public void testSequenceData() throws IOException, PythonExecutionException {
        SequenceData sequenceData = new SequenceData("xxx.fasta");
        int n = sequenceData.size();
        String[] sequences = sequenceData.toArray();

        ScoringMatrix matrix = new BLOSUM();
        double penalty = -5;
        AlignmentAlgo algo = new NeedlemanWunschAlgo(matrix, penalty);
        double[][] distance = new double[n][n];
        // find pairwise distance ...
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {

                if (i == j) {
                    distance[i][j] = 0;
                }
                else if (j > i) {
                    // option 1 : distance = 1 - ( 1 / similarity) <= similarity = 1 / distance + 1
                    distance[i][j] =  100 * (1 - (1 / algo.align(sequences[i], sequences[j]).getSimilarity()));

                    // option 2 : Euclidean algorithm.clustering.distance.Distance
                    /* if (sequences[i].length = sequences[j].length)*/

                    // option 3 : Correlation algorithm.clustering.distance.Distance
                    // option 4 : Manhattan distance

                    // - similarity = distance => larger similarity shorter distance
                    //distance[i][j] = -algo.align(sequences[i], sequences[j]).getSimilarity();
                }
                else { // i > j
                    distance[i][j] = distance[j][i];
                }
            }
        }
        System.out.println(Arrays.deepToString(distance).replace("],", "],\n"));

        MatrixDistance matrixDistance = new MatrixDistance(distance);
        HierarchicalClustering clustering = new AgglomerativeClustering(new SingleLinkage());

        List<List<Double>> Z = clustering.fit(matrixDistance);

        Plot plt = Plot.create(PythonConfig.pythonBinPathConfig("/Users/winkielo/opt/anaconda3/bin/python"));

        plt.dendrogram().add(Z).labels(sequenceData.getNames()).leafRotation(90);
        plt.xlabel("gene");
        plt.ylabel("dist");
        plt.title("Dendrogram");
        plt.show();
    }
}
