import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class GraTest extends PoczatkoweUstawienia {

    private Sterowanie sterowanie = new Sterowanie();
    private Gra gra;
    private int staryX;
    private int staryY;
    private ArrayList<Integer> zmiany = new ArrayList<Integer>();
    private int mojkolor = 1;


    @Test
    public void init() {
        gra = new Gra(4, mojkolor);
        Assert.assertNotNull(gra);
    }


    @Test
    public void wczytanie_wspolrzednych_test() {
        gra = new Gra(6, mojkolor);
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

            Assert.assertEquals(3, staryX);
            Assert.assertEquals(11, staryY);
        }
    }


    @Test
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

                Assert.assertEquals(3, (Object) zmiany.get(0));
                Assert.assertEquals(11, (Object) zmiany.get(1));
                Assert.assertEquals(4, (Object) zmiany.get(2));
                Assert.assertEquals(10, (Object) zmiany.get(3));
            }
        }
    }


    @Test
    public void przeskok_test() {
        gra = new Gra(6, mojkolor);
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

                sterowanie.gdzie_mozna_przeskoczyc(x, y);

                Assert.assertEquals(2, (Object) zmiany.get(0));
                Assert.assertEquals(12, (Object) zmiany.get(1));
                Assert.assertEquals(4, (Object) zmiany.get(2));
                Assert.assertEquals(14, (Object) zmiany.get(3));
            }
        }
    }


    private void stworz_wierzcholek() {
        ArrayList<ParaWspolrzednych> wierzcholek1 = new ArrayList<>();

        wierzcholek1.add(new ParaWspolrzednych(0, 12));
        wierzcholek1.add(new ParaWspolrzednych(1, 11));
        wierzcholek1.add(new ParaWspolrzednych(1, 13));
        wierzcholek1.add(new ParaWspolrzednych(2, 10));
        wierzcholek1.add(new ParaWspolrzednych(2, 12));
        wierzcholek1.add(new ParaWspolrzednych(2, 14));
        wierzcholek1.add(new ParaWspolrzednych(3, 9));
        wierzcholek1.add(new ParaWspolrzednych(3, 11));
        wierzcholek1.add(new ParaWspolrzednych(3, 13));
        wierzcholek1.add(new ParaWspolrzednych(3, 15));

        for (int i = 0; i < wierzcholek1.size(); i++) {
            gra.setZawartoscTablicyOdInt(wierzcholek1.get(i).getX(), wierzcholek1.get(i).getY(), 1);
        }
    }


    @Test
    public void czy_wygral_test1() {
        gra = new Gra(3, mojkolor);
        stworz_wierzcholek();

        Assert.assertTrue("powinno zwrocic true", gra.czy_wygral(mojkolor));
    }


    @Test
    public void czy_wygral_test2() {
        gra = new Gra(3, mojkolor);
        stworz_wierzcholek();
        gra.setZawartoscTablicyOdInt(3, 15, 0);

        Assert.assertFalse("powinno zwrocic false", gra.czy_wygral(mojkolor));
    }


    @Test
    public void ruch_bota_test() {
        gra = new Gra(6, 5);

        gra.ruch_bota(5);

        Assert.assertFalse("lista zmian powinna byc uzupelniona", gra.zmiany.isEmpty());
    }


}