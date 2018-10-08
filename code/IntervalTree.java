import java.util.List;

public class IntervalTree {

    private Node root;


    /**
     * Constructor
     *
     * Opgave 3.1
     * Bouw in de constructor de gebalanceerde boom op adhv de ongesorteerde lijst van intervals
     * @param intervals
     */
    public IntervalTree(List<Interval> intervals) {

        root = new Node( intervals.get(0) /*eerste interval?*/ ,null);

        for(int i=1 ; i<intervals.size() ; i++){
            root.voegNodeToe(intervals.get(i));
        }
    }


    /**
     * Opgave 3.2
     */
    public void printIntervals() {

        root.LWRPrint();
        //Eerst linkerKind
        //daarna zelf,
        //daarna rechterKind
    }


    /**
     * Opgave 3.3
     */
    public List<Interval> findOverlapping(int x) {
        throw new UnsupportedOperationException("Nog te implementeren!");
    }

    /**
     * Opgave 3.4
     */

    public List<Interval> findOverlapping(Interval ab) {
        throw new UnsupportedOperationException("Nog te implementeren!");
    }
}
