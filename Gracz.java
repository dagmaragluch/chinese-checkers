import java.util.ArrayList;

public class Gracz {

    private final int kolorGracza;
    ArrayList<ParaWspolrzednych> listaPionkow;
    ArrayList<ParaWspolrzednych> docelowyWierzcholek;
    boolean czyJuzWygral;

    public Gracz( int kolorGracza, ArrayList<ParaWspolrzednych> listaPionkow, ArrayList<ParaWspolrzednych> docelowyWierzcholek, boolean czyJuzWygral) {
        this.kolorGracza = kolorGracza;
        this.listaPionkow = listaPionkow;
        this.docelowyWierzcholek = docelowyWierzcholek;
        this.czyJuzWygral = czyJuzWygral;
    }

    public int getKolorGracza() {
        return kolorGracza;
    }
}
