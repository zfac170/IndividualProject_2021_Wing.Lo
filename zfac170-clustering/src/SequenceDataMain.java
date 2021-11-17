import java.util.*;
import java.io.*;

public class SequenceDataMain {
    public static Map<String, String> SequenceDataReader(String fileName) throws IOException{
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
    public static void main(String[] args){
        try {
            System.out.print(SequenceDataReader("/Users/winkielo/Documents/GitHub/IndividualProject_2021_Wing.Lo/zfac170-clustering/xxx.fasta"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
