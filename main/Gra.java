import java.util.ArrayList;
import java.util.Random;

public class Gra extends PoczatkoweUstawienia implements MetodyDoGry {

    Random random = new Random();
    Sterowanie sterownik = new Sterowanie();

    private int staryX;
    private int staryY;
    protected int ruch = 0;
    protected boolean czyostatni = false;
    protected int mojkolor;
    int liczbaPoprawnieZajetychPol;
    public ArrayList<Integer> zmiany = new ArrayList<Integer>();

    Gra(int a, int mojkolor) {       //później przerobić na Gra(int a, int b, int kto) gdzie b = liczbaBoow
        ustawPionki(a);
        this.mojkolor = mojkolor;
        this.sterownik.plansza = this;
        this.iluGraczy = a;
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
                    for (ParaWspolrzednych pw : sterownik.listaPodswietlanychPol) { //wymazanie podswietlen
                        sterownik.plansza.setZawartoscTablicyOdInt(pw.getX(), pw.getY(), 0);
                    }
                    sterownik.wyczysc();
                    sterownik.gdzie_mozna_przesunac(x, y); //dwa rodzaje ruchow sa dostepne
                    sterownik.gdzie_mozna_przeskoczyc(x, y);
                    for (ParaWspolrzednych pw : sterownik.listaPodswietlanychPol) { //podswietlanie pol
                        sterownik.plansza.setZawartoscTablicyOdInt(pw.getX(), pw.getY(), 8);
                    }
                    staryX = x; //zapamietujemy wspolrzedne (poczatek pierwszego podruchu)
                    staryY = y;
                    return;
                }
            }
            for (ParaWspolrzednych para : sterownik.listaPodswietlanychPol2) {
                if (x == para.getX() && y == para.getY()) { //sprawdzamy, czy wybrano przeskok

                	zmiany.add(staryX);
                	zmiany.add(staryY);
                	zmiany.add(x);
                	zmiany.add(y);

                    for (ParaWspolrzednych pw : sterownik.listaPodswietlanychPol) { //wymazanie pod�wietle�
                        sterownik.plansza.setZawartoscTablicyOdInt(pw.getX(), pw.getY(), 0);
                    }
                    sterownik.wyczysc();

                    sterownik.gdzie_mozna_przeskoczyc(x, y);
                    sterownik.listaPodswietlanychPol2.add(new ParaWspolrzednych(staryX, staryY));
                    sterownik.listaPodswietlanychPol.add(new ParaWspolrzednych(staryX, staryY));
                    for (ParaWspolrzednych pw : sterownik.listaPodswietlanychPol) { //nowe pod�wietlanie
                        sterownik.plansza.setZawartoscTablicyOdInt(pw.getX(), pw.getY(), 8);
                    }

                    staryX = x; //mozemy dalej sie ruszac, wiec zapisujemy nowe wspolrzedne
                    staryY = y;
                    ruch++;
                    return;
                }
            }
            for (ParaWspolrzednych para : sterownik.listaPodswietlanychPol1) {
                if (x == para.getX() && y == para.getY()) { //jezli wybrano pojedynczy ruch
                	
                	zmiany.add(staryX);
                	zmiany.add(staryY);
                	zmiany.add(x);
                	zmiany.add(y);


                    for (ParaWspolrzednych pw : sterownik.listaPodswietlanychPol) { //wymazujemy podswietlenia i nie szukamy dalszych ruchow
                        sterownik.plansza.setZawartoscTablicyOdInt(pw.getX(), pw.getY(), 0);
                    }
                    sterownik.wyczysc();
                    ruch++;
                    czyostatni=true;
                    return;
                }
            }
        }
    }

    @Override
    public boolean czy_wygral(int kolor) {
    	Gracz gracz = listaGraczy.get(kolor-1);
        liczbaPoprawnieZajetychPol = 0;
        for (ParaWspolrzednych para : gracz.docelowyWierzcholek) {

            if (this.getZawartoscTablicy(para.getX(), para.getY()) == gracz.kolorGracza) {
                liczbaPoprawnieZajetychPol++;
            }
        }
        if (liczbaPoprawnieZajetychPol == gracz.docelowyWierzcholek.size()) {
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
            sterownik.wyczysc();
            sterownik.gdzie_mozna_przesunac(bot.pionki.get(i).getX(), bot.pionki.get(i).getY());
            sterownik.gdzie_mozna_przeskoczyc(bot.pionki.get(i).getX(), bot.pionki.get(i).getY());
        }
        while (sterownik.listaPodswietlanychPol.isEmpty());

        //implementacja jednego, domyslnego podruchu bota

        zmiany.add(bot.pionki.get(i).getX());
        zmiany.add(bot.pionki.get(i).getY());
        zmiany.add(sterownik.listaPodswietlanychPol.get(0).getX());
        zmiany.add(sterownik.listaPodswietlanychPol.get(0).getY());
        
        
        bot.pionki.set(i, new ParaWspolrzednych(sterownik.listaPodswietlanychPol.get(0).getX(), sterownik.listaPodswietlanychPol.get(0).getY()));
        sterownik.wyczysc();

    }


}