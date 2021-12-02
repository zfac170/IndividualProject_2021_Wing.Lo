import com.opencsv.exceptions.CsvException;
import org.junit.Test;
import parser.MicroArray;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class MicroArrayTest {
    @Test
    public void readMicroArrayTest() throws IOException, CsvException {
        MicroArray array = new MicroArray("hm_cot.csv");
        assertEquals(64, array.numOfGenes());
    }

}
