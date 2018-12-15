import java.util.ArrayList;

public class Gracz {

    private final int kolorGracza;
    ArrayList<ParaWspolrzednych> listaPionkow;
    private ArrayList<ParaWspolrzednych> docelowyWierzcholek;

    public Gracz( int kolorGracza, ArrayList<ParaWspolrzednych> listaPionkow, ArrayList<ParaWspolrzednych> docelowyWierzcholek) {
        this.kolorGracza = kolorGracza;
        this.listaPionkow = listaPionkow;
        this.docelowyWierzcholek = docelowyWierzcholek;
    }

    public int getKolorGracza() {
        return kolorGracza;
    }
}
