import java.sql.SQLOutput;

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

    public void setLinks(Interval interval){ //nog niet zeker als dit wel gebruikt zal worden
        linkerZoon = new Node(interval, this);
    }

    public void setRechts(Interval interval) {
        rechterZoon = new Node(interval, this);
    }

    public Node getLinks(){
        return linkerZoon;
    }

    public Node getRechts(){
        return rechterZoon;
    }

    public void setMax(){
        //bereken het maximum
        //geen idee hoe je dit doet
    }

    public void voegNodeToe(Interval toevoegen){


        //bepalen als links of rechts moet
        if(interval.getLow() > toevoegen.getLow() ){
            //links toevoegen
            if(linkerZoon == null){
                linkerZoon = new Node(toevoegen, this);
            }
            else{
                linkerZoon.voegNodeToe(toevoegen);
            }

        }
        else if(interval.getLow() < toevoegen.getLow()){

            if(rechterZoon == null){
                rechterZoon = new Node(toevoegen, this);
            }
            else{
                rechterZoon.voegNodeToe(toevoegen);
            }

        }
        else{ //wanner de ondergrenzen gelijk zijn

            if(interval.getHigh() > toevoegen.getHigh() ){
                //links toevoegen
                if(linkerZoon == null){
                    linkerZoon = new Node(toevoegen, this);
                }
                else{
                    linkerZoon.voegNodeToe(toevoegen);
                }

            }
            else if(interval.getHigh() < toevoegen.getHigh() ){

                if(rechterZoon == null){
                    rechterZoon = new Node(toevoegen, this);
                }
                else{
                    rechterZoon.voegNodeToe(toevoegen);
                }

            }
            else{
                //als er 2 identieke intervallen zijn???
                System.out.println("identieke intervallen");
                System.out.println("Node.java, lijn ongeveer 95");

                //waarschijnlijk niets doen, niet toevoegen ? toch?

            }






        }

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
}
