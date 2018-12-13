import java.util.ArrayList;

public class Gracz {

    private final int kolorGracza;
    ArrayList<ParaWspolrzednych> listaPionkow = new ArrayList<>();

    public Gracz( int kolorGracza, ArrayList<ParaWspolrzednych> listaPionkow) {
        this.kolorGracza = kolorGracza;
        this.listaPionkow = listaPionkow;
    }
}
