/**
 * Sequence Data Format
 */
public class SequenceData {
    private String seq_Id;
    private String seq_String;
    private String seq_Desc;

    private SequenceData() {
    }

    public SequenceData(SequenceData seq){
        this(seq.seq_Id, seq.seq_String, seq.seq_Desc);
    }

    public SequenceData(String seq_Id, String seq_String, String seq_Desc){
        this.seq_Id = seq_Id;
        this.seq_String = seq_String;
        this.seq_Desc = seq_Desc;
    }

    public String getSeq_Id(){
        return seq_Id;
    }

    public String getSeq_String(){
        return seq_String;
    }

    public String getSeq_Desc(){
        return seq_Desc;
    }


}
