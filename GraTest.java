import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;

public class GraTest extends PoczatkoweUstawienia {

    Sterowanie sterowanie = new Sterowanie();
    private Gra gra;
    private int staryX;
    private int staryY;
    private int ruch = 0;
    private boolean czyostatni = false;
    Gracz tengracz;
    //int liczbaPoprawnieZajetychPol;
    public ArrayList<Integer> zmiany = new ArrayList<Integer>();
    private int mojkolor = 1;




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
                //sterowanie.wyczysc();

                System.out.println("zawartoscTablicy(" + staryX + "," + staryY + ") = " + getZawartoscTablicy(staryX, staryY));
                System.out.println("zawartoscTablicy(" + x + "," + y + ") = " + getZawartoscTablicy(x, y));

                Assert.assertEquals((Object) zmiany.get(0), 3);
                Assert.assertEquals((Object) zmiany.get(1), 11);
                Assert.assertEquals((Object) zmiany.get(2), 4);
                Assert.assertEquals((Object) zmiany.get(3), 10);
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

                System.out.println("zawartoscTablicy(" + staryX + "," + staryY + ") = " + getZawartoscTablicy(staryX, staryY));
                System.out.println("zawartoscTablicy(" + x + "," + y + ") = " + getZawartoscTablicy(x, y));

                Assert.assertEquals((Object) zmiany.get(0), 2);
                Assert.assertEquals((Object) zmiany.get(1), 12);
                Assert.assertEquals((Object) zmiany.get(2), 4);
                Assert.assertEquals((Object) zmiany.get(3), 14);
            }
        }
    }


private void stworz_wierzcholek(){
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



    @org.junit.Test
    public void czy_wygral_test1() {
        gra = new Gra(3, 0, mojkolor);
        ustawPionki(3);

        stworz_wierzcholek();
        tengracz = gracz1;

        Assert.assertTrue(gra.czy_wygral());
        Assert.assertTrue("powinno zwrocic true", gra.czy_wygral());       //nie wyświetla message - czemu???
    }



    @org.junit.Test
    public void czy_wygral_test2() {
        gra = new Gra(3, 0, mojkolor);
        ustawPionki(3);
        tengracz = gracz1;

        stworz_wierzcholek();
        gra.setZawartoscTablicyOdInt(3, 15, 0);

        gra.czy_wygral();

        Assert.assertFalse(gra.czy_wygral());
        Assert.assertFalse("powinno zwrocic false", gra.czy_wygral());
    }


///*************************/
//public void wprowadzBoty2(int b){
//    int i = listaGraczy.size();
//    int j = 0; //ile botow juz wstswiono
//
//    System.out.println("i = " + i);
//
//    for(int a =0; a < listaGraczy.size(); a++){
//        System.out.println("ia = " + i);
//        //System.out.println("kolor gracz "+ a +" = " + listaGraczy.get(a).getKolorGracza());
//        System.out.println("lefhdrfbjsdgfwf");
//    }
//
////    while(j<b){     //boty wstawimy jako ostatnich graczy
////        listaGraczy.get(i-1).setCzyBot(true);
////        i--;
////        j++;
////    }
//}
//
///****************/
//
//    @org.junit.Test
//    public void ruch_bota_test() {
//        gra = new Gra(6, 0, mojkolor);
//
////        ustawPionki(3);
//        wprowadzBoty2(2);
//    }
//

}