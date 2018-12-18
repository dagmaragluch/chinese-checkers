import java.util.ArrayList;

public class Gracz {

    final int kolorGracza;
    ArrayList<ParaWspolrzednych> docelowyWierzcholek;
    boolean czyJuzWygral;

    public Gracz( int kolorGracza, ArrayList<ParaWspolrzednych> docelowyWierzcholek, boolean czyJuzWygral) {
        this.kolorGracza = kolorGracza;
        this.docelowyWierzcholek = docelowyWierzcholek;
        this.czyJuzWygral = czyJuzWygral;
    }

    public int getKolorGracza() {
        return kolorGracza;
    }
}
