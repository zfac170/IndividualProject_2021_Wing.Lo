package parser;


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
            }
        }
        return seqMap;
    }

    public static SequenceFileFormat inferSeqFormat(String fileName) {
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
