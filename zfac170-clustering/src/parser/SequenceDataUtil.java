package parser;

import algorithm.alignment.AlignmentAlgo;
import algorithm.alignment.BLOSUM;
import algorithm.alignment.NeedlemanWunschAlgo;
import algorithm.alignment.ScoringMatrix;
import algorithm.clustering.AgglomerativeClustering;
import algorithm.clustering.BinaryTree;
import algorithm.clustering.HierarchicalClustering;
import algorithm.clustering.distance.MatrixDistance;
import algorithm.clustering.linkage.SingleLinkage;

import java.util.*;
import java.io.*;


/**
 * Utility function for sequence data
 */

public class SequenceDataUtil {

    public static Map<String, String> parseSequenceData(String fileName) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader(fileName));

        String line;
        String seq_id = null;
        Map<String, String> seqMap = new LinkedHashMap<>();

        while((line = br.readLine()) != null){
            line = line.trim();
            if(line.startsWith(">")){
                seq_id = line.substring(1);
            } else{
                seqMap.put(seq_id, line);
                new SequenceData(seq_id, line);
            }
        }
        return seqMap;
    }

    public static BinaryTree runExample(String fileName) throws IOException{
        ScoringMatrix matrix = new BLOSUM();
        double penalty = -5;
        AlignmentAlgo algo = new NeedlemanWunschAlgo(matrix, penalty);
        Map<String, String> map = parseSequenceData(fileName);
        int n = map.size();
        String[] sequences = map.values().toArray(new String[0]);

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
        List<List<Integer>> clusters = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            clusters.add(new ArrayList<>());
            clusters.get(i).add(i);
        }

        MatrixDistance matrixDistance = new MatrixDistance(distance);
        HierarchicalClustering clustering = new AgglomerativeClustering(new SingleLinkage());
        return clustering.fit(matrixDistance, clusters);
    }

    public static SequenceFileFormat setSeqFormat(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            if((line = br.readLine()) != null){
                line = line.trim();
                if (line.startsWith(">")){
                    return SequenceFileFormat.FASTA;
                }
            }
        } catch (IOException ex) {
            return SequenceFileFormat.UNKNOWN;
        }
        return SequenceFileFormat.UNKNOWN;
    }
}
