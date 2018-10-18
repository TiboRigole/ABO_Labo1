import java.util.List;

public class Node {

    private Interval interval;
    private Node vader;
    private Node linkerZoon;
    private Node rechterZoon;

    private int max; //waarde die het maximale eindpunt van de intervallen in deze sub boom bevat

    //constructor
    public Node(Interval interval, Node vader){

        this.interval = interval;

        linkerZoon = null;
        rechterZoon = null;

        this.vader = vader;
    }

    public Node(Interval interval, Node vader, Node linkerZoon, Node rechterZoon){
        this.interval = interval;
        this.linkerZoon = linkerZoon;
        this.rechterZoon = rechterZoon;
        this.vader = vader;
        //hier nog een maxWaarde aan toevoegen, door extra parameter van de lijst met intervallen
        //uit die parameter de maximumwaarde van de intervallen halen
    }

    public Node(Interval interval, Node vader, Node linkerZoon, Node rechterZoon, List<Interval> alleZonen){
        this.interval = interval;
        this.linkerZoon = linkerZoon;
        this.rechterZoon = rechterZoon;
        this.vader = vader;

        max = pakMax(alleZonen);
    }

    private int pakMax(List<Interval> alleZonen) {
        int maximum = 0;
        for(Interval zoon : alleZonen){
            if (zoon.getHigh() > maximum){
                maximum = zoon.getHigh();
            }
        }
        return maximum;
    }


    public void setLinks(Interval interval){ //nog niet zeker als dit wel gebruikt zal worden
        linkerZoon = new Node(interval, this);
    }
    public void setLinks(Node links){ linkerZoon = links;}

    public void setRechts(Interval interval) {
        rechterZoon = new Node(interval, this);
    }
    public void setRechts(Node rechts){ rechterZoon = rechts;}

    public Node getLinks(){
        return linkerZoon;
    }

    public Node getRechts(){
        return rechterZoon;
    }

    public void setMax(int high){
        max = high;
    }

    private int getMax() {
        return max;
    }

    private Interval getInterval() {
        return interval;
    }


    public void LWRPrint() {    // LINKS, WAARDE, RECHTS

        if(linkerZoon != null ){
            linkerZoon.LWRPrint();
        }

        System.out.println(interval.toString());

        if(rechterZoon != null ){
            rechterZoon.LWRPrint();
        }
    }

    public void zoekOverlappingMetGetalRecursief(int getal, List<Interval> returnLijst ) {
        //logica voor linkerdeel
        if(linkerZoon != null && getal <= linkerZoon.getMax() ) {
            linkerZoon.zoekOverlappingMetGetalRecursief(getal, returnLijst);
        }

        //logica voor huidige
        if(interval.getLow() <= getal && interval.getHigh() > getal){
            returnLijst.add(this.getInterval());
        }


        //logica voor rechterdeel
        if(rechterZoon != null && getal <= rechterZoon.getMax() ) {
            rechterZoon.zoekOverlappingMetGetalRecursief(getal, returnLijst);
        }
    }

    public void zoekOverlappingMetIntervalRecursief(Interval ab, List<Interval> returnLijst) {
        //logica voor linkerdeel

        //als interval.min >= node.maxiumonderliggen, dan niet meer verder zoeken
        //aka : als interval.min < node.maximumonderliggend, dan verder zoeken

        if(linkerZoon != null && ab.getLow() < linkerZoon.getMax()){ //voorwaarde wanneer we verder moeten zoeken
            linkerZoon.zoekOverlappingMetIntervalRecursief(ab, returnLijst); //niet zeker als juist
        }


        //logica voor huidige
        if(interval.overlaps(ab)){
            returnLijst.add(this.getInterval());
        }

        //logica voor rechterdeel
        if(rechterZoon != null && ab.getLow() < rechterZoon.getMax()){ //voorwaarde wanneer we verder moeten zoeken
            rechterZoon.zoekOverlappingMetIntervalRecursief(ab,returnLijst);
        }


    }


}
