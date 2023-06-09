package parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Sequence Data Format
 */
public class SequenceData {
    private Map<String, String> sequenceMap;

    public SequenceData(String file) throws IOException {
        if (SequenceDataUtil.inferSeqFormat(file) == SequenceFileFormat.FASTA) {
            sequenceMap = SequenceDataUtil.parseSequenceData(file);
        }
    }

    public int size() {
        return sequenceMap.size();
    }

    public String[] toArray() {
        return sequenceMap.values().toArray(new String[0]);
    }

    public List<String> getNames() {
        return new ArrayList<>(sequenceMap.keySet());
    }
}
