import com.google.common.graph.*;

import java.io.File;
import java.io.IOException;
import java.util.*;


public class Labo1DirectedGraphs {

    public static void main(String[] args) throws IOException {

        // gerichte graaf inlezen (je mag er van uit gaan dat dit een gerichte graaf is, ie. graph.isDirected() == true
        // bestanden: tinyDG.txt, tinyDAG.txt, mediumDG.txt, mediumDAG.txt onder de folder data

        //print huidige werkmap (ter controle)
        //System.out.println("Working Directory = " + System.getProperty("user.dir"));

        //Graph<String> graph = Util.loadDiGraphFromFile(new File("code/data-lab1/testGraaf.txt"));
        Graph<String> graph = Util.loadDiGraphFromFile(new File("code/data-lab1/tinyDAG" +
                ".txt"));

        //System.out.println(Graphs.hasCycle(graph));
        boolean hasCycle = hasCycle(graph);

        if(!hasCycle) {
            System.out.println("Graaf heeft geen gerichte lus.");
            System.out.printf("Ordening van nodes volgens edges: %s\n",determinePrecedenceFeasibleOrdering(graph));
        } else {
            System.out.println("Graaf heeft een gerichte lus!");
        }

    }

    // Implementeer hier opgave 1
    public static boolean hasCycle(Graph<String> graph) {

        //dit moet gecheckt worden vanuit elk standpunt

        //we gaan telkens children zoeken van de children van de children
        //en deze children telkens toevoegen aan de verzameling
        //als de verzameling na "aantalnodes" de start bevat, dan is ze cyclisch

        //1) voor elke startnode: checkcyclic
        for(String startNode : graph.nodes()) {

            //per geval moeten we opnieuw starten met het bijhouden een legen arraylist
            boolean [] bezocht = new boolean [graph.nodes().size()]; //standaard allemaal false


            HashSet<String> reedsBereikbaar = new HashSet<String>();
            HashSet<String> bereikbare = vraagBereikbareOp(reedsBereikbaar, startNode, graph, startNode, bezocht);

            if (bereikbare.contains(startNode)) {
                return true;
            }

        }

        return false;




    }

    //opgave1 hulpmethodes
    private static HashSet<String> vraagBereikbareOp(HashSet<String> reedsBereikbaar, String startNode, Graph<String> graph, String passingNode, boolean[] bezocht) {

        //extra maatregel: de opvolgers maar 1 keer bezoeken --> sneller maken, minder iteraties
        //anders kreeg ik een stackoverflowerror bij 'mediumDAG.txt"

        //klopt --> voorwaarde om eruit te springen
        //hier komt de voorwaarde die ons deruit laat gaan
        if(reedsBereikbaar.contains(startNode)){return reedsBereikbaar;}


        Set<String> opvolgers = graph.successors(passingNode);
        //deze opvolgers moeten toegevoegd worden aan de lijst
        for(String opvolger: opvolgers){
            reedsBereikbaar.add(opvolger); //geen controle als het er al in zit want Set
        }

        //hier
        bezocht[Integer.parseInt(passingNode)] = true;

        //bezoek alle opvolgers, en zoek naar hun kinderen
        for(String opvolger: opvolgers){

            //enkel als de opvolger nog niet bezocht is
            if(!bezocht[Integer.parseInt(opvolger)]){
                vraagBereikbareOp(reedsBereikbaar, startNode, graph, opvolger, bezocht);
            }

        }

        return reedsBereikbaar;

    }

    //-----------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------

    // Implementeer hier opgave 2
    public static List<String> determinePrecedenceFeasibleOrdering(Graph<String> graph) {

        System.out.println("trying to get a volgorde");
        /*
            hoe dit aanpakken,
            zullen we enkel doen als er geen cyclische graaf is
            weer doorlopen met overal als startpunt, we mogen nooit meer een 'already visisted' tegenkome
            als visited == true -> dan mogen we direct naar een ander beginpunt gaan
            dus, we moeten nu de volgorde van bezoeken ook nog bijhhouden

            als we vast zitten (hasnosuccessors) , dan moeten we starten bij een nieuwe , pakt de eerstvolgende visited?
            als deze niet klopt, kan het zijn dat een tweede nieuwe wel juist is, dus moeten we wel alle mog checken
         */

        boolean oplossingGevonden = false;
        ArrayList<String> volgorde = new ArrayList<String>();

        //alle nodes toevoegen in de Volgorde
        for(String node : graph.nodes()){
            volgorde.add(node);
        }

        boolean klaar = false;

        while(!klaar) {

            //zoek de eerste fout
            //wissel deze van plek in de lijst

            for(EndpointPair<String> edge: graph.edges()){

                if( volgorde.indexOf(edge.nodeU()) > volgorde.indexOf(edge.nodeV()) ){

                    //node U en node V swappen
                    Collections.swap(volgorde, volgorde.indexOf(edge.nodeU()) , volgorde.indexOf(edge.nodeV()));

                }
            }

            //statement dat checkt als het klaar is
            klaar = checkIfVolgordeKlopt(volgorde, graph.edges());

        }

        return volgorde;
    }

    private static boolean checkIfVolgordeKlopt(ArrayList<String> volgorde, Set<EndpointPair<String>> edges) {

        //checkt als de pijlen in orde zijn
        for(EndpointPair<String> edge : edges){
            //van U naar V
            if(volgorde.indexOf(edge.nodeU()) > volgorde.indexOf(edge.nodeV())){
                return false;
            }
        }
        //als alle edges van richting kloppen
        return true;

    }


}



