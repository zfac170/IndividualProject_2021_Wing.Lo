import algorithm.clustering.AgglomerativeClustering;
import algorithm.clustering.HierarchicalClustering;
import algorithm.clustering.distance.EuclideanDistance;
import algorithm.clustering.distance.PairwiseDistance;
import algorithm.clustering.linkage.SingleLinkage;
import com.github.sh0nk.matplotlib4j.NumpyUtils;
import com.github.sh0nk.matplotlib4j.Plot;
import com.github.sh0nk.matplotlib4j.PythonConfig;
import com.github.sh0nk.matplotlib4j.PythonExecutionException;
import com.opencsv.exceptions.CsvException;
import org.junit.Test;
import parser.MicroArray;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MicroArrayTest {
    @Test
    public void MicroArrayDendrogramTest() throws IOException, CsvException, PythonExecutionException {
        MicroArray array = new MicroArray("hm_cot.csv");
        assertEquals(64, array.numOfGenes());
        assertEquals(6, array.numOfSamples());


        PairwiseDistance distance = new EuclideanDistance(array.toArray());

        HierarchicalClustering clustering = new AgglomerativeClustering(new SingleLinkage());
        List<List<Double>> Z = clustering.fit(distance);

        Plot plt = Plot.create(PythonConfig.pythonBinPathConfig("C:\\Users\\Wind\\Anaconda3\\python.exe"));

        plt.dendrogram().add(Z).labels(array.getGeneNames()).leafRotation(90);
        plt.xlabel("gene");
        plt.ylabel("dist");
        plt.title("Dendrogram");
        plt.show();
    }
}
