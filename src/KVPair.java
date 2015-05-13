/**
 * Created by GusSilva on 5/13/15.
 */
public class KVPair {
    private int k;
    private String e;
    /** Constructors */
    KVPair(int kval, String eval)
    { k = kval; e = eval; }
    /** Data member access functions */
    public int key() { return k; }
    public String value() { return e; }
}
