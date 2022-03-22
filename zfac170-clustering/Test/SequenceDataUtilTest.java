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
public class SequenceDataUtilTest {
    @Test
    public void testFileFormat() {
        SequenceFileFormat format = SequenceDataUtil.setSeqFormat("xxx.fasta");
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
     * @throws IOException
     */
    @Test
    public void testSequenceData() throws IOException {
        SequenceData testSeq = new SequenceData("SEQUENCE_1", "GTAATCTAAC");
        Map<String, String> testMap = SequenceDataUtil.parseSequenceData("xxx.fasta");
        assertEquals(testSeq.getSeq_String(), testMap.get(testMap.keySet().toArray()[0]));
        assertEquals(testSeq.getSeq_Id(),testMap.keySet().iterator().next());
        assertEquals(null, testSeq.getSeq_Desc());
    }
}
