import algorithm.clustering.distance.EuclideanDistance;

import com.github.sh0nk.matplotlib4j.NumpyUtils;
import com.github.sh0nk.matplotlib4j.Plot;
import com.github.sh0nk.matplotlib4j.PythonConfig;

import com.github.sh0nk.matplotlib4j.PythonExecutionException;
import com.opencsv.exceptions.CsvException;
import org.junit.Test;
import algorithm.clustering.KMeans;
import parser.MicroArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static algorithm.dimension.PCA.principalComponentAnalysis;
import static org.junit.Assert.assertEquals;

public class KMeansTest {

    @Test
    public void KMeansConstructionTest(){
        KMeans kmeans = new KMeans(3, 100, new EuclideanDistance(), 111, "kmeans++");
        assertEquals(3, kmeans.getK());
        assertEquals(100, kmeans.getMaxIteration());
    }

    @Test
    public void KMeansFitTest() throws IOException, CsvException {
        KMeans kmeans = new KMeans(3, 100, new EuclideanDistance(), 111, "kmeans++");
        MicroArray array = new MicroArray("hm_cot.csv");
        Map<String, double[]> data = array.getData();
        double[][] data_array = data.values().toArray(new double[0][0]);
        kmeans.fit(data_array);
    }


    @Test
    public void KMeansElbowTest() throws IOException, CsvException, PythonExecutionException {
        MicroArray array = new MicroArray("hm_cot.csv");
        Map<String, double[]> data = array.getData();
        double[][] data_array = data.values().toArray(new double[0][0]);

        List<Double> inertia = new ArrayList<>();
        for (int k = 2; k <= 9; ++k) {
            KMeans kmeans = new KMeans(k, 100, new EuclideanDistance(), 5, "kmeans++");
            KMeans.KMeansResult result = kmeans.fit(data_array);
            inertia.add(result.inertia);
        }

        List<Double> x = NumpyUtils.arange(2, 9, 1);
        Plot plt = Plot.create(PythonConfig.pythonBinPathConfig("C:\\Users\\Wind\\Anaconda3\\python.exe"));

        plt.plot().add(x, inertia);
        plt.xlabel("k");
        plt.ylabel("inertia");
        plt.title("Search k using Elbow method");
        plt.legend();
        plt.show();
    }

    @Test
    public void KMeansClusteringPlotTest() throws IOException, CsvException, PythonExecutionException {
        MicroArray array = new MicroArray("hm_cot.csv");
        double[][] data_array = array.toArray();
        int n = data_array.length;
        double[][] data2D = principalComponentAnalysis(data_array, 2);
        int k = 4;
        KMeans kmeans = new KMeans(k, 100, new EuclideanDistance(), 5, "kmeans++");
        KMeans.KMeansResult result = kmeans.fit(data2D);

        Plot plt = Plot.create(PythonConfig.pythonBinPathConfig("C:\\Users\\Wind\\Anaconda3\\python.exe"));

        for (int i = 0; i < k; ++i) {
            List<Double> x = new ArrayList<>();
            List<Double> y = new ArrayList<>();
            for (int j = 0; j < n; ++j) {
                if (result.labels[j] == i) {
                    x.add(data2D[j][0]);
                    y.add(data2D[j][1]);
                }
            }
            plt.scatter().add(x, y);
        }
        plt.xlabel("x1");
        plt.ylabel("x2");
        plt.title(String.format("K means clustering (k=%d)", k));
        plt.legend();
        plt.show();
    }
}

