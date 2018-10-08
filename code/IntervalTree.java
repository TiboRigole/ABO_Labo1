import java.util.ArrayList;
import java.util.Collections;
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

        //sorteer de lijst op klein naar groot
        Collections.sort(intervals);

        root = sorteerdeArrayNaarBalansBoom(intervals, null);
    }

    private Node sorteerdeArrayNaarBalansBoom(List<Interval> intervals, Node vader){

        if(intervals.size() == 0 ){
            return null;
        }

        if(intervals.size() == 1 ){
            Node eind = new Node(intervals.get(0), vader, null, null);
            eind.setMax(intervals.get(0).getHigh());
        }


        int midden = intervals.size()/2;

        //linker en rechterLijst maken
        List<Interval> linkerLijst = intervals.subList(0, midden);
        List<Interval> rechterLijst = intervals.subList(midden+1, intervals.size());

        //recursief linker en rechterdeel invullen
        Node opperNode = new Node(intervals.get(midden), vader, null, null, intervals);
        Node linkerNode = sorteerdeArrayNaarBalansBoom(linkerLijst, opperNode);
        Node rechterNode = sorteerdeArrayNaarBalansBoom(rechterLijst, opperNode );

        opperNode.setLinks(linkerNode);
        opperNode.setRechts(rechterNode);

        return opperNode;




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
    public List<Interval> findOverlapping(int getal) {
        //LWR doorzoeken
        //vroegtijdig stoppen als we geen kans meer maken om nog een waarde te vinden

        //standaardprocedure voor als we een node hebben?
        //node.contains(x)

        //als getal > maxwaarde van die node --> niet meer verderzoeken
        //A.K.A als getal <= maxWaarde van die node --> wel verderzoeken
        List<Interval> legeLijst = new ArrayList<Interval>();

        root.zoekOverlappingMetGetalRecursief(getal, legeLijst);

        return legeLijst;
    }

    /**
     * Opgave 3.4
     */

    public List<Interval> findOverlapping(Interval ab) {
        throw new UnsupportedOperationException("Nog te implementeren!");
    }
}
