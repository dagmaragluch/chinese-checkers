import java.util.ArrayList;

public class Gracz {

    final int kolorGracza;
    ArrayList<Pionek> listaPionkow = new ArrayList<>();

    public Gracz(int kolorGracza, ArrayList<Pionek> listaPionkow) {
        this.kolorGracza = kolorGracza;
        this.listaPionkow = listaPionkow;
    }
}
