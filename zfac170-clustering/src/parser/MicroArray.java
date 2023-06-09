package parser;

import java.io.FileReader;
import java.io.IOException;
import java.net.Inet4Address;
import java.util.*;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

/**
 * @author winkielo
 * parse MicroArray data in csv format
 */

public class MicroArray {
    int numOfGenes;
    int numOfSamples;
    Map<String, Integer> sampleNames = new HashMap<>();
    Map<String, Integer> geneNames = new HashMap<>();

    public Map<String, double[]> getData() {
        return data;
    }

    Map<String, double[]> data;

    double[] mean(ArrayList<double[]> list) {
        int n = list.get(0).length;
        int m = list.size();
        double[] result = new double[n];

        for (double[] doubles : list) {
            for (int j = 0; j < n; ++j) {
                result[j] += doubles[j] / m;
            }
        }

        return result;
    }

    public MicroArray(String file) throws IOException, CsvException {
        CSVReader reader = new CSVReader(new FileReader(file));
        List<String[]> myEntries = reader.readAll();
        numOfGenes = myEntries.size() - 1;
        numOfSamples = myEntries.get(0).length - 1;
        Map<String, ArrayList<double[]>> temp = new HashMap<>();
        data = new HashMap<>();

        for (int j = 0; j < numOfSamples; ++j) {
            sampleNames.put(myEntries.get(0)[j + 1], j);
        }

        for (int i = 0; i < numOfGenes; ++i) {
            String[] row =  myEntries.get(i + 1);
            String geneName = row[0];
            double[] d = new double[numOfSamples];
            for (int j = 0; j < numOfSamples; ++j) {
                d[j] = Double.parseDouble(row[j + 1]);
            }

            if (geneNames.containsKey(geneName)) {
                temp.get(geneName).add(d);
            }
            else {
                ArrayList<double[]> list = new ArrayList<>();
                list.add(d);
                temp.put(geneName, list);
            }
            geneNames.put(geneName, i);
        }
        for (Map.Entry<String, ArrayList<double[]>> pair : temp.entrySet()) {
            String geneName = pair.getKey();
            ArrayList<double[]> array = pair.getValue();
            if (array.size() > 1) {
                data.put(geneName, mean(array));
            } else {
                data.put(geneName, array.get(0));
            }
        }
    }

    public List<String> getGeneNames() {
        Map<String, double[]> data = getData();
        return new ArrayList<>(data.keySet());
    }

    public double[][] toArray() {
        Map<String, double[]> data = getData();
        return data.values().toArray(new double[0][0]);
    }

    public int numOfGenes() {
        return this.numOfGenes;
    }

    public int numOfSamples() {
        return this.numOfSamples;
    }

    @Override
    public String toString() {
        return Arrays.deepToString(toArray()).replace("],", "],\n");
    }
}
