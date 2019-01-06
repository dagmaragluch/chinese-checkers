import java.util.ArrayList;
import java.util.Random;

public class Gra extends PoczatkoweUstawienia implements MetodyDoGry {

    Random random = new Random();
    Sterowanie sterowanie = new Sterowanie();

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
        this.sterowanie.plansza = this;
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
                    return;
                }
            }
            for (ParaWspolrzednych para : sterowanie.listaPodswietlanychPol2) {
                if (x == para.getX() && y == para.getY()) { //sprawdzamy, czy wybrano przeskok

                	zmiany.add(staryX);
                	zmiany.add(staryY);
                	zmiany.add(x);
                	zmiany.add(y);

                    for (ParaWspolrzednych pw : sterowanie.listaPodswietlanychPol) { //wymazanie pod�wietle�
                        sterowanie.plansza.setZawartoscTablicyOdInt(pw.getX(), pw.getY(), 0);
                    }
                    sterowanie.wyczysc();

                    sterowanie.gdzie_mozna_przeskoczyc(x, y);
                    for (ParaWspolrzednych pw : sterowanie.listaPodswietlanychPol) { //nowe pod�wietlanie
                        sterowanie.plansza.setZawartoscTablicyOdInt(pw.getX(), pw.getY(), 8);
                    }

                    staryX = x; //mozemy dalej sie ruszac, wiec zapisujemy nowe wspolrzedne
                    staryY = y;
                    ruch++;
                    return;
                }
            }
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
    public void ruch_bota() {
        int i;

        do {             //wybor pionka tak dlugo az tym pionkiem mozna sie ruszyc
            i = random.nextInt(10);
            sterowanie.wyczysc();
            sterowanie.gdzie_mozna_przesunac(tengracz.pionki.get(i).getX(), tengracz.pionki.get(i).getY());
            sterowanie.gdzie_mozna_przeskoczyc(tengracz.pionki.get(i).getX(), tengracz.pionki.get(i).getY());
        }
        while (sterowanie.listaPodswietlanychPol.isEmpty());

        //implementacja jednego, domyslnego podruchu bota

        setZawartoscTablicyOdInt(tengracz.pionki.get(i).getX(), tengracz.pionki.get(i).getY(), 0);
        setZawartoscTablicyOdInt(sterowanie.listaPodswietlanychPol.get(0).getX(), sterowanie.listaPodswietlanychPol.get(0).getY(), tengracz.getKolorGracza());
        tengracz.pionki.set(i, new ParaWspolrzednych(sterowanie.listaPodswietlanychPol.get(0).getX(), sterowanie.listaPodswietlanychPol.get(0).getY()));

//            staryX = tengracz.pionki.get(i).getX();
//            staryY = tengracz.pionki.get(i).getY();

    }


}