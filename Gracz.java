import java.util.ArrayList;

public class Gracz {

    final int kolorGracza;
    ArrayList<ParaWspolrzednych> docelowyWierzcholek;
    boolean czyJuzWygral;
    boolean czyBot;
    ArrayList<ParaWspolrzednych> pionki;

    public Gracz( int kolorGracza, ArrayList<ParaWspolrzednych> docelowyWierzcholek, boolean czyJuzWygral,  boolean czyBot, ArrayList<ParaWspolrzednych> pionki) {
        this.kolorGracza = kolorGracza;
        this.docelowyWierzcholek = docelowyWierzcholek;
        this.czyJuzWygral = czyJuzWygral;
        this.czyBot = czyBot;
        this.pionki = pionki;
    }

    public int getKolorGracza() {
        return kolorGracza;
    }

    public void setCzyBot(boolean czyBot) {
        this.czyBot = czyBot;
    }
}
