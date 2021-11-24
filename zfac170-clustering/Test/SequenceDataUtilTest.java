import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;
import java.util.*;

/**
 * @author WinkieLo
 *
 * To test SequenceDataMain reading.
 */
public class SequenceDataUtilTest {
    @Test
    public void testFilemat() {
        SequenceFileFormat format = SequenceDataUtil.setSeqFormat("./zfac170-clustering/xxx.fasta");
        assertEquals(format, SequenceFileFormat.FASTA);
    }

    @Test
    public void testReadFastaSequenceData() throws IOException {
        Map<String, String> seqMap = new LinkedHashMap<>();
        seqMap.put("SEQUENCE_1", "GTAATCTAAC");
        seqMap.put("SEQUENCE_2", "GATTACA");
        seqMap.put("SEQUENCE_3", "GTACTAATC");
        seqMap.put("SEQUENCE_4", "TTACATGGA");

        Map<String, String> parsedMap = SequenceDataUtil.parseSequenceData("./zfac170-clustering/xxx.fasta");
        assertEquals(seqMap, parsedMap);
    }
}
