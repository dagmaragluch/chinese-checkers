import java.util.ArrayList;
import java.util.Random;

public class Gra extends PoczatkoweUstawienia implements MetodyDoGry {

    Random random = new Random();
    BetaSerwer betaSerwer = new BetaSerwer();

    private int staryX;
    private int staryY;
    private int ruch = 0;
    private boolean czyostatni = false;
    Gracz tengracz;
    private int mojkolor;
    int liczbaPoprawnieZajetychPol;
    public ArrayList<Integer> zmiany = new ArrayList<Integer>();

    Gra(int a, int b, int mojkolor) {       //później przerobić na Gra(int a, int b, int kto) gdzie b = liczbaBoow
        ustawPionki(a+b);
        wprowadzBoty(b);
        this.mojkolor = mojkolor;
        this.betaSerwer.plansza = this;
        this.iluGraczy = a;
        this.ileBotow = b;  //zamienić na b
        this.tengracz = listaGraczy.get(mojkolor-1);
    }

    @Override
    public void nowatura() {
        ruch = 0;
        czyostatni = false;
    }

    @Override
    public void wykonaj_ruch(int x, int y) {

        if (!czyostatni) {
            if (ruch == 0) {
                if (mojkolor == getZawartoscTablicy(x, y)) { //petla liczaca mozliwosci
                    for (ParaWspolrzednych pw : betaSerwer.listaPodswietlanychPol) { //wymazanie podswietlen
                        betaSerwer.plansza.setZawartoscTablicyOdInt(pw.getX(), pw.getY(), 0);
                    }
                    betaSerwer.wyczysc();
                    betaSerwer.gdzie_mozna_przesunac(x, y); //dwa rodzaje ruchow sa dostepne
                    betaSerwer.gdzie_mozna_przeskoczyc(x, y);
                    for (ParaWspolrzednych pw : betaSerwer.listaPodswietlanychPol) { //podswietlanie pol
                        betaSerwer.plansza.setZawartoscTablicyOdInt(pw.getX(), pw.getY(), 8);
                    }
                    staryX = x; //zapamietujemy wspolrzedne (poczatek pierwszego podruchu)
                    staryY = y;
                    return;
                }
            }
            for (ParaWspolrzednych para : betaSerwer.listaPodswietlanychPol2) {
                if (x == para.getX() && y == para.getY()) { //sprawdzamy, czy wybrano przeskok

                	zmiany.add(staryX);
                	zmiany.add(staryY);
                	zmiany.add(x);
                	zmiany.add(y);

                    for (ParaWspolrzednych pw : betaSerwer.listaPodswietlanychPol) { //wymazanie pod�wietle�
                        betaSerwer.plansza.setZawartoscTablicyOdInt(pw.getX(), pw.getY(), 0);
                    }
                    betaSerwer.wyczysc();

                    betaSerwer.gdzie_mozna_przeskoczyc(x, y);
                    for (ParaWspolrzednych pw : betaSerwer.listaPodswietlanychPol) { //nowe pod�wietlanie
                        betaSerwer.plansza.setZawartoscTablicyOdInt(pw.getX(), pw.getY(), 8);
                    }

                    staryX = x; //mozemy dalej sie ruszac, wiec zapisujemy nowe wspolrzedne
                    staryY = y;
                    ruch++;
                    return;
                }
            }
            for (ParaWspolrzednych para : betaSerwer.listaPodswietlanychPol1) {
                if (x == para.getX() && y == para.getY()) { //jezli wybrano pojedynczy ruch
                	
                	zmiany.add(staryX);
                	zmiany.add(staryY);
                	zmiany.add(x);
                	zmiany.add(y);


                    for (ParaWspolrzednych pw : betaSerwer.listaPodswietlanychPol) { //wymazujemy podswietlenia i nie szukamy dalszych ruchow
                        betaSerwer.plansza.setZawartoscTablicyOdInt(pw.getX(), pw.getY(), 0);
                    }
                    betaSerwer.wyczysc();
                    ruch++;
                    czyostatni = true;
                    return;
                }
            }
        }
    }

    @Override
    public boolean czy_wygral() {
        liczbaPoprawnieZajetychPol = 0;
        for (ParaWspolrzednych para : tengracz.docelowyWierzcholek) {

            if (this.getZawartoscTablicy(para.getX(), para.getY()) == tengracz.kolorGracza) {
                liczbaPoprawnieZajetychPol++;
            }
        }
        if (liczbaPoprawnieZajetychPol == tengracz.docelowyWierzcholek.size()) {
            return true;
        }
        return false;
    }

    @Override
    public void ruch_bota(int kolor) {
        int i;
        Gracz bot = listaGraczy.get(kolor-1);

        do {             //wybor pionka tak dlugo az tym pionkiem mozna sie ruszyc
            i = random.nextInt(10);
            betaSerwer.wyczysc();
            betaSerwer.gdzie_mozna_przesunac(bot.pionki.get(i).getX(), bot.pionki.get(i).getY());
            betaSerwer.gdzie_mozna_przeskoczyc(bot.pionki.get(i).getX(), bot.pionki.get(i).getY());
        }
        while (betaSerwer.listaPodswietlanychPol.isEmpty());

        //implementacja jednego, domyslnego podruchu bota

        zmiany.add(bot.pionki.get(i).getX());
        zmiany.add(bot.pionki.get(i).getY());
        zmiany.add(betaSerwer.listaPodswietlanychPol.get(1).getX());
        zmiany.add(betaSerwer.listaPodswietlanychPol.get(1).getY());
        
        
        bot.pionki.set(i, new ParaWspolrzednych(betaSerwer.listaPodswietlanychPol.get(1).getX(), betaSerwer.listaPodswietlanychPol.get(1).getY()));

//            staryX = tengracz.pionki.get(i).getX();
//            staryY = tengracz.pionki.get(i).getY();

    }


}