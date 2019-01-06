import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class GraTest extends PoczatkoweUstawienia {

    //Plansza plansza;
    Sterowanie sterowanie = new Sterowanie();
    private Gra gra;
    private int staryX;
    private int staryY;
    private int ruch = 0;
    private boolean czyostatni = false;
    Gracz tengracz;
    // private int mojkolor;
    int liczbaPoprawnieZajetychPol;
    public ArrayList<Integer> zmiany = new ArrayList<Integer>();


    int mojkolor = 1;

    @org.junit.Test
    public void init() {
        gra = new Gra(6, 0, mojkolor);  //później zmienić b na coś
        //ustawPionki(6);

//        for (int i = 0; i < 17; i++)
//            for (int j = 0; j < 25; j++) {
//                System.out.println("getTaB = " + getZawartoscTablicy(i, j));
//            }
    }


    @org.junit.Test
    public void wczytanie_wspolrzednych_test() {
        gra = new Gra(6, 0, mojkolor);
        ustawPionki(6);
        this.sterowanie.plansza = this;
        int x = 3;
        int y = 11;

        if (mojkolor == getZawartoscTablicy(x, y)) { //petla liczaca mozliwosci
            for (ParaWspolrzednych pw : sterowanie.listaPodswietlanychPol) { //wymazanie podswietlen
                sterowanie.plansza.setZawartoscTablicyOdInt(pw.getX(), pw.getY(), 0);
            }
            sterowanie.wyczysc();
            sterowanie.gdzie_mozna_przesunac(x, y); //dwa rodzaje ruchow sa dostepne
            sterowanie.gdzie_mozna_przeskoczyc(x, y);
            for (ParaWspolrzednych pw : sterowanie.listaPodswietlanychPol) { //podswietlanie pol
                sterowanie.plansza.setZawartoscTablicyOdInt(pw.getX(), pw.getY(), 8);
            }
            staryX = x; //zapamietujemy wspolrzedne (poczatek pierwszego podruchu)
            staryY = y;

            Assert.assertEquals(staryX, 3);
            Assert.assertEquals(staryY, 11);
        }
    }

    /********/

    @org.junit.Test
    public void przesuniecie_test() {
        int x = 4;
        int y = 10;
        wczytanie_wspolrzednych_test();

        for (ParaWspolrzednych para : sterowanie.listaPodswietlanychPol1) {
            if (x == para.getX() && y == para.getY()) { //jezli wybrano pojedynczy ruch
                zmiany.add(staryX);
                zmiany.add(staryY);
                zmiany.add(x);
                zmiany.add(y);

                for (ParaWspolrzednych pw : sterowanie.listaPodswietlanychPol) { //wymazujemy podswietlenia i nie szukamy dalszych ruchow
                    sterowanie.plansza.setZawartoscTablicyOdInt(pw.getX(), pw.getY(), 0);
                }
                sterowanie.wyczysc();

                System.out.println("zawartoscTablicy(" + staryX + "," + staryY + ") = " + getZawartoscTablicy(staryX, staryY));
                System.out.println("zawartoscTablicy(" + x + "," + y + ") = " + getZawartoscTablicy(x, y));
                //dodać assercie
            }
        }
    }



    @org.junit.Test
    public void przeskok_test() {
        gra = new Gra(6, 0, mojkolor);
        ustawPionki(6);
        this.sterowanie.plansza = this;
        int x = 2;
        int y = 12;

        if (mojkolor == getZawartoscTablicy(x, y)) {
            sterowanie.wyczysc();
            sterowanie.gdzie_mozna_przeskoczyc(x, y);

            staryX = x;
            staryY = y;
        }

        x = 4;  //symulacja drugiego klikniecia
        y = 14;

        for (ParaWspolrzednych para : sterowanie.listaPodswietlanychPol2) {
            if (x == para.getX() && y == para.getY()) { //sprawdzamy, czy wybrano przeskok
                zmiany.add(staryX);
                zmiany.add(staryY);
                zmiany.add(x);
                zmiany.add(y);

                for (ParaWspolrzednych pw : sterowanie.listaPodswietlanychPol) { //wymazanie podswietlen
                    sterowanie.plansza.setZawartoscTablicyOdInt(pw.getX(), pw.getY(), 0);
                }
               // sterowanie.wyczysc();
                sterowanie.gdzie_mozna_przeskoczyc(x, y);

//                for (ParaWspolrzednych pw : sterowanie.listaPodswietlanychPol) { //nowe podswietlanie
//                    sterowanie.plansza.setZawartoscTablicyOdInt(pw.getX(), pw.getY(), 8);
//                }

                System.out.println("zawartoscTablicy(" + staryX + "," + staryY + ") = " + getZawartoscTablicy(staryX, staryY));
                System.out.println("zawartoscTablicy(" + x + "," + y + ") = " + getZawartoscTablicy(x, y));
            }
        }
    }


    @org.junit.Test
    public void czy_wygral() {
    }

    @org.junit.Test
    public void ruch_bota() {
    }
}