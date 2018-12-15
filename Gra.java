public class Gra extends PoczatkoweUstawienia implements MetodyDoGry {

    private int tura = 1;
    private Gracz aktualnyGracz;
    private BetaSerwer betaSerwer = new BetaSerwer();
    private boolean czySkonczylem;

    private ParaWspolrzednych pamietanaParaWsp;


    @Override
    public int ktora_tura() {
        if (tura > iluGraczy) {
            tura = 1;
        }
        return tura;
    }

    @Override
    public Gracz czyja_tura() {
        switch (ktora_tura()) {
            case 1:
                aktualnyGracz = gracz1;
            case 2:
                aktualnyGracz = gracz2;
            case 3:
                aktualnyGracz = gracz3;
            case 4:
                aktualnyGracz = gracz4;
            case 5:
                aktualnyGracz = gracz5;
            case 6:
                aktualnyGracz = gracz6;
        }
        return aktualnyGracz;
    }

    @Override
    public void skonczylem() {
        tura++;
        czySkonczylem = true;
        //czy_wygral();
    }


    @Override
    public ParaWspolrzednych wykonaj_pierwszy_podruch(int x1, int y1, int x2, int y2) {
        pamietanaParaWsp = new ParaWspolrzednych(x2, y2);

        //while z breakiem żeby klikał w plansze tak długo aż kliknie w swój pionek
        while (czyja_tura().getKolorGracza() == getZawartoscTablicy(x1, y1)) {     //czy kliknal w pole ze swoim pionkiem
            betaSerwer.gdzie_mozna_przesunac2(x1, y1);
            betaSerwer.gdzie_mozna_przeskoczyc(x1, y1);

            while (betaSerwer.listaPodswietlanychPol2.contains(new ParaWspolrzednych(x2, y2))) {
                setZawartoscTablicyOdInt(x1, y1, 0);
                setZawartoscTablicyOdInt(x2, y2, czyja_tura().getKolorGracza());
                break;
            }
            break;
        }
        return pamietanaParaWsp;
    }

    @Override
    public ParaWspolrzednych wykonaj_zwykly_podruch(ParaWspolrzednych stara, int x2, int y2) {

        stara = pamietanaParaWsp;

        int x1 = stara.getX();
        int y1 = stara.getY();

         pamietanaParaWsp = new ParaWspolrzednych(x2, y2);

        while (czyja_tura().getKolorGracza() == getZawartoscTablicy(x1, y1)) {
            betaSerwer.gdzie_mozna_przeskoczyc(x1, y1);

            while (betaSerwer.listaPodswietlanychPol2.contains(new ParaWspolrzednych(x2, y2))) {
                setZawartoscTablicyOdInt(x1, y1, 0);
                setZawartoscTablicyOdInt(x2, y2, czyja_tura().getKolorGracza());
                break;
            }
            break;
        }
        return pamietanaParaWsp;
    }


    @Override
    public void wykonaj_ruch() {

        int liczbaPodruchow = 0;
        czySkonczylem = false;

        while (!czySkonczylem) {
            if (liczbaPodruchow == 0) {
                //      wykonaj_pierwszy_podruch();       //trzeba dodać współrzędne z kliknięcia
            } else {
                //      wykonaj_zwykly_podruch();         //trzeba dodać współrzędne z kliknięcia
            }
            liczbaPodruchow++;
        }


    }


}
