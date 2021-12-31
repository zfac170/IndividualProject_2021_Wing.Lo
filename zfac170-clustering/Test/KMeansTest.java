import algorithm.clustering.distance.DistanceMethod;
import algorithm.clustering.distance.EuclideanDistance;
import com.opencsv.exceptions.CsvException;
import org.junit.Test;
import algorithm.clustering.KMeans;
import parser.MicroArray;

import java.io.IOException;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class KMeansTest {

    @Test
    public void KMeansTest(){
        KMeans kmeans = new KMeans(3, 4, 111, new EuclideanDistance());
        assertEquals(3, kmeans.getK());
        assertEquals(4, kmeans.getMaxIter());

    }

    @Test
    public void KMeansFitTest() throws IOException, CsvException {
        KMeans kmeans = new KMeans(3, 4, 111, new EuclideanDistance());
        MicroArray array = new MicroArray("hm_cot.csv");
        Map<String, double[]> data = array.getData();
        double[][] data_array = data.values().toArray(new double[0][0]);
        kmeans.fit(data_array);
    }
}
