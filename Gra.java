import java.util.TreeSet;

public class Gra extends PoczatkoweUstawienia implements MetodyDoGry {

    Gra(int a, int kto) {
        ustawPionki(a);
        this.betaSerwer.plansza = this;
        this.iluGraczy = a;
        this.tengracz=this.listaGraczy.get(kto-1);
    }

    BetaSerwer betaSerwer = new BetaSerwer();

    private int staryX;
    private int staryY;
    private int ruch = 0;
    private boolean czyostatni = false;
    Gracz tengracz;
    int liczbaPoprawnieZajetychPol;

    @Override
    public void skonczylem() {
        czy_wygral();
        for (ParaWspolrzednych pw : betaSerwer.listaPodswietlanychPol) {            //p�tla wymazuj�ca pod�wietlone pola na planszy ZASTAPIC
            betaSerwer.plansza.setZawartoscTablicyOdInt(pw.getX(), pw.getY(), 0);
        }
        betaSerwer.wyczysc();
        ruch = 0;
        czyostatni = false;


    }

    @Override
    public void wykonaj_ruch(int x, int y) {

        if (!tengracz.czyJuzWygral) {
            if (!czyostatni) {
                if (ruch == 0) {
                    if (tengracz.getKolorGracza() == getZawartoscTablicy(x, y)) { //p�tla licz�ca mo�liwo�ci
                        for (ParaWspolrzednych pw : betaSerwer.listaPodswietlanychPol) { //wymazanie pod�wietle�
                            betaSerwer.plansza.setZawartoscTablicyOdInt(pw.getX(), pw.getY(), 0);
                        }
                        betaSerwer.wyczysc();
                        betaSerwer.gdzie_mozna_przesunac(x, y); //dwa rodzaje ruch�w s� dostepne
                        betaSerwer.gdzie_mozna_przeskoczyc(x, y);
                        for (ParaWspolrzednych pw : betaSerwer.listaPodswietlanychPol) { //pod�wietlanie p�l
                            betaSerwer.plansza.setZawartoscTablicyOdInt(pw.getX(), pw.getY(), 8);
                        }
                        staryX = x; //zapamietujemy wspolrzedne (pocz�tek pierwszego podruchu)
                        staryY = y;
                        return;
                    }
                }
                for (ParaWspolrzednych para : betaSerwer.listaPodswietlanychPol2) {
                    if (x == para.getX() && y == para.getY()) { //sprawdzamy, czy wybrano przeskok

                        betaSerwer.plansza.setZawartoscTablicyOdInt(staryX, staryY, 0);

                        for (ParaWspolrzednych pw : betaSerwer.listaPodswietlanychPol) { //wymazanie pod�wietle�
                            betaSerwer.plansza.setZawartoscTablicyOdInt(pw.getX(), pw.getY(), 0);
                        }
                        betaSerwer.wyczysc();

                        betaSerwer.gdzie_mozna_przeskoczyc(x, y);
                        for (ParaWspolrzednych pw : betaSerwer.listaPodswietlanychPol) { //nowe pod�wietlanie
                            betaSerwer.plansza.setZawartoscTablicyOdInt(pw.getX(), pw.getY(), 8);
                        }

                        betaSerwer.plansza.setZawartoscTablicyOdInt(x, y, tengracz.getKolorGracza());

                        staryX = x; //mo�emy dalej si� ruszac, wi�c zapisujemy nowe wspolrzedne
                        staryY = y;
                        ruch++;
                        return;
                    }
                }
                for (ParaWspolrzednych para : betaSerwer.listaPodswietlanychPol1) {
                    if (x == para.getX() && y == para.getY()) { //je�li wybrano pojedynczy ruch

                        for (ParaWspolrzednych pw : betaSerwer.listaPodswietlanychPol) { //wymazujemy podswietlenia i nie szukamy dalszych ruch�w
                            betaSerwer.plansza.setZawartoscTablicyOdInt(pw.getX(), pw.getY(), 0);
                        }
                        betaSerwer.wyczysc();

                        betaSerwer.plansza.setZawartoscTablicyOdInt(staryX, staryY, 0);
                        betaSerwer.plansza.setZawartoscTablicyOdInt(x, y, tengracz.getKolorGracza());
                        ruch++;
                        czyostatni = true;
                        return;
                    }
                }
            }
        } else {
            skonczylem();
        }
    }

    @Override
    public void czy_wygral() {
        liczbaPoprawnieZajetychPol = 0;
        for (ParaWspolrzednych para : tengracz.docelowyWierzcholek) {

            if (this.getZawartoscTablicy(para.getX(), para.getY()) == tengracz.kolorGracza) {
                liczbaPoprawnieZajetychPol++;
            }
        }
        if (liczbaPoprawnieZajetychPol == tengracz.docelowyWierzcholek.size()) {
            tengracz.czyJuzWygral = true;
        }
    }


}