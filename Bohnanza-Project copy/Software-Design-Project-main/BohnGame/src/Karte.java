import java.lang.reflect.Array;
import java.util.ArrayList;

public class Karte {
    public Bohne typ;
    public int position; //position in PlayerHand; -1 if not in Hand.
    public ArrayList<Integer> bohnOMeter;

    public Karte(Bohne typ, ArrayList<Integer> bohnOMeter){
        this.bohnOMeter = bohnOMeter;
        this.typ = typ;
    }


}
