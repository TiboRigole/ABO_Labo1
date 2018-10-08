public class Interval implements Comparable<Interval> {

    //attributen
    private int lowInclusive;
    private int highExclusive;

    public Interval(int lowInclusive, int highExclusive) {
        this.lowInclusive = lowInclusive;
        this.highExclusive = highExclusive;
    }

    /**
     * Geeft de (inclusieve) ondergrens terug
     * @return
     */
    public int getLow() {
        return lowInclusive;
    }

    /**
     * Geeft de (exclusieve) bovengrens terug
     * @return
     */
    public int getHigh() {
        return highExclusive;
    }

    @Override
    /**
     * Te implementeren!
     * Returned -1 als dit interval een lagere ondergrens heeft, of een gelijke ondergrens en lagere bovengrens heeft dan Interval o
     * Returned 0 als dit interval gelijk is aan Interval o
     * Returnd 1 als dit interval een hogere ondergrens heeft, of een gelijke ondergrens en een hogere bovengrens heeft dan Interval o
     */
    public int compareTo(Interval o) {

        //System.out.println("compareto opgeroepen?"); //ja
        int verschilOnder = this.lowInclusive - o.lowInclusive;

        if( verschilOnder == 0){
            //gelijke ondergrens
            return Integer.signum(this.highExclusive-o.highExclusive);
        }
        else{
            return Integer.signum(verschilOnder);
        }

    }

    /**
     * Bereken de overlap met Interval b. Als dit interval niet overlapt met Interval b, dan return je 'null' . Anders
     * return je een nieuw Interval dat de overlap voorstelt.
     * @param b
     * @return
     */
    public Interval calculateOverlap(Interval b) {

        int lowInclusive = Math.max(this.lowInclusive, b.lowInclusive);
        int highExclusive = Math.min(this.highExclusive, b.highExclusive);

        return new Interval(lowInclusive, highExclusive);
    }

    @Override
    public String toString() {
        return String.format("[%d, %d)",getLow(),getHigh());
    }
}
