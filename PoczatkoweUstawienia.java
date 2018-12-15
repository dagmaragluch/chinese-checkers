import java.util.ArrayList;

public class PoczatkoweUstawienia extends Plansza {

    final int iluGraczy;

    protected Gracz gracz1;
    protected Gracz gracz2;
    protected Gracz gracz3;
    protected Gracz gracz4;
    protected Gracz gracz5;
    protected Gracz gracz6;

    private ArrayList<ParaWspolrzednych> wierzcholek1 = new ArrayList<>();
    private ArrayList<ParaWspolrzednych> wierzcholek2 = new ArrayList<>();
    private ArrayList<ParaWspolrzednych> wierzcholek3 = new ArrayList<>();
    private ArrayList<ParaWspolrzednych> wierzcholek4 = new ArrayList<>();
    private ArrayList<ParaWspolrzednych> wierzcholek5 = new ArrayList<>();
    private ArrayList<ParaWspolrzednych> wierzcholek6 = new ArrayList<>();



    PoczatkoweUstawienia(int a) {

        iluGraczy = a ;

        wierzcholek1.add(new ParaWspolrzednych(0, 12));
        wierzcholek1.add(new ParaWspolrzednych(2, 11));
        wierzcholek1.add(new ParaWspolrzednych(2, 13));
        wierzcholek1.add(new ParaWspolrzednych(4, 10));
        wierzcholek1.add(new ParaWspolrzednych(4, 12));
        wierzcholek1.add(new ParaWspolrzednych(4, 14));
        wierzcholek1.add(new ParaWspolrzednych(6, 9));
        wierzcholek1.add(new ParaWspolrzednych(6, 11));
        wierzcholek1.add(new ParaWspolrzednych(6, 13));
        wierzcholek1.add(new ParaWspolrzednych(6, 15));

        wierzcholek2.add(new ParaWspolrzednych(8, 18));
        wierzcholek2.add(new ParaWspolrzednych(8, 20));
        wierzcholek2.add(new ParaWspolrzednych(8, 22));
        wierzcholek2.add(new ParaWspolrzednych(8, 24));
        wierzcholek2.add(new ParaWspolrzednych(10, 19));
        wierzcholek2.add(new ParaWspolrzednych(10, 21));
        wierzcholek2.add(new ParaWspolrzednych(10, 23));
        wierzcholek2.add(new ParaWspolrzednych(12, 20));
        wierzcholek2.add(new ParaWspolrzednych(12, 22));
        wierzcholek2.add(new ParaWspolrzednych(14, 21));

        wierzcholek3.add(new ParaWspolrzednych(24, 18));
        wierzcholek3.add(new ParaWspolrzednych(24, 20));
        wierzcholek3.add(new ParaWspolrzednych(24, 22));
        wierzcholek3.add(new ParaWspolrzednych(24, 24));
        wierzcholek3.add(new ParaWspolrzednych(22, 19));
        wierzcholek3.add(new ParaWspolrzednych(22, 21));
        wierzcholek3.add(new ParaWspolrzednych(22, 23));
        wierzcholek3.add(new ParaWspolrzednych(20, 20));
        wierzcholek3.add(new ParaWspolrzednych(20, 22));
        wierzcholek3.add(new ParaWspolrzednych(18, 21));

        wierzcholek4.add(new ParaWspolrzednych(32, 12));
        wierzcholek4.add(new ParaWspolrzednych(30, 11));
        wierzcholek4.add(new ParaWspolrzednych(30, 13));
        wierzcholek4.add(new ParaWspolrzednych(28, 10));
        wierzcholek4.add(new ParaWspolrzednych(28, 12));
        wierzcholek4.add(new ParaWspolrzednych(28, 14));
        wierzcholek4.add(new ParaWspolrzednych(26, 9));
        wierzcholek4.add(new ParaWspolrzednych(26, 11));
        wierzcholek4.add(new ParaWspolrzednych(26, 13));
        wierzcholek4.add(new ParaWspolrzednych(26, 15));

        wierzcholek5.add(new ParaWspolrzednych(24, 0));
        wierzcholek5.add(new ParaWspolrzednych(24, 2));
        wierzcholek5.add(new ParaWspolrzednych(24, 4));
        wierzcholek5.add(new ParaWspolrzednych(24, 6));
        wierzcholek5.add(new ParaWspolrzednych(22, 1));
        wierzcholek5.add(new ParaWspolrzednych(22, 3));
        wierzcholek5.add(new ParaWspolrzednych(22, 5));
        wierzcholek5.add(new ParaWspolrzednych(20, 2));
        wierzcholek5.add(new ParaWspolrzednych(20, 4));
        wierzcholek5.add(new ParaWspolrzednych(18, 3));

        wierzcholek6.add(new ParaWspolrzednych(8, 0));
        wierzcholek6.add(new ParaWspolrzednych(8, 2));
        wierzcholek6.add(new ParaWspolrzednych(8, 4));
        wierzcholek6.add(new ParaWspolrzednych(8, 6));
        wierzcholek6.add(new ParaWspolrzednych(10, 1));
        wierzcholek6.add(new ParaWspolrzednych(10, 3));
        wierzcholek6.add(new ParaWspolrzednych(10, 5));
        wierzcholek6.add(new ParaWspolrzednych(12, 2));
        wierzcholek6.add(new ParaWspolrzednych(12, 4));
        wierzcholek6.add(new ParaWspolrzednych(14, 3));


        uzupelnianie_planszy();

        switch (iluGraczy) {

            case 2:
                //tworzenie 2 graczy z uzupełnionymi arraylistami pionkow

                ArrayList<ParaWspolrzednych> pionki1 = new ArrayList<>();
                ArrayList<ParaWspolrzednych> pionki2 = new ArrayList<>();

                ArrayList<ParaWspolrzednych> wierzGracza1 = new ArrayList<>();
                ArrayList<ParaWspolrzednych> wierzGracza2 = new ArrayList<>();

                for (int i = 0; i < wierzcholek1.size(); i++) {
                    setZawartoscTablicyOdInt(wierzcholek1.get(i).getX(), wierzcholek1.get(i).getY(), 1);
                    setZawartoscTablicyOdInt(wierzcholek4.get(i).getX(), wierzcholek4.get(i).getY(), 2);

                    pionki1.add(wierzcholek1.get(i));
                    pionki2.add(wierzcholek4.get(i));

                    wierzGracza1.add(wierzcholek4.get(i));
                    wierzGracza2.add(wierzcholek1.get(i));
                }

                gracz1 = new Gracz(1, pionki1, wierzGracza1);
                gracz2 = new Gracz(2, pionki2, wierzGracza2);

                break;

            case 3:
                pionki1 = new ArrayList<>();
                pionki2 = new ArrayList<>();
                ArrayList<ParaWspolrzednych> pionki3 = new ArrayList<>();
                wierzGracza1 = new ArrayList<>();
                wierzGracza2 = new ArrayList<>();
                ArrayList<ParaWspolrzednych> wierzGracza3 = new ArrayList<>();

                for (int i = 0; i < wierzcholek1.size(); i++) {
                    setZawartoscTablicyOdInt(wierzcholek1.get(i).getX(), wierzcholek1.get(i).getY(), 1);
                    setZawartoscTablicyOdInt(wierzcholek3.get(i).getX(), wierzcholek3.get(i).getY(), 2);
                    setZawartoscTablicyOdInt(wierzcholek5.get(i).getX(), wierzcholek5.get(i).getY(), 3);

                    pionki1.add(wierzcholek1.get(i));
                    pionki2.add(wierzcholek3.get(i));
                    pionki3.add(wierzcholek5.get(i));

                    wierzGracza1.add(wierzcholek4.get(i));
                    wierzGracza2.add(wierzcholek6.get(i));
                    wierzGracza3.add(wierzcholek2.get(i));

                }

                gracz1 = new Gracz(1, pionki1, wierzGracza1);
                gracz2 = new Gracz(2, pionki2, wierzGracza2);
                gracz3 = new Gracz(3, pionki3, wierzGracza3);

                break;

            case 4:

                pionki1 = new ArrayList<>();
                pionki2 = new ArrayList<>();
                pionki3 = new ArrayList<>();
                ArrayList<ParaWspolrzednych> pionki4 = new ArrayList<>();
                wierzGracza1 = new ArrayList<>();
                wierzGracza2 = new ArrayList<>();
                wierzGracza3 = new ArrayList<>();
                ArrayList<ParaWspolrzednych> wierzGracza4 = new ArrayList<>();

                for (int i = 0; i < wierzcholek1.size(); i++) {
                    setZawartoscTablicyOdInt(wierzcholek1.get(i).getX(), wierzcholek1.get(i).getY(), 1);
                    setZawartoscTablicyOdInt(wierzcholek3.get(i).getX(), wierzcholek3.get(i).getY(), 2);
                    setZawartoscTablicyOdInt(wierzcholek4.get(i).getX(), wierzcholek4.get(i).getY(), 3);
                    setZawartoscTablicyOdInt(wierzcholek6.get(i).getX(), wierzcholek6.get(i).getY(), 4);

                    pionki1.add(wierzcholek1.get(i));
                    pionki2.add(wierzcholek3.get(i));
                    pionki3.add(wierzcholek4.get(i));
                    pionki4.add(wierzcholek6.get(i));

                    wierzGracza1.add(wierzcholek4.get(i));
                    wierzGracza2.add(wierzcholek6.get(i));
                    wierzGracza3.add(wierzcholek1.get(i));
                    wierzGracza4.add(wierzcholek3.get(i));
                }

                gracz1 = new Gracz(1, pionki1, wierzGracza1);
                gracz2 = new Gracz(2, pionki2, wierzGracza2);
                gracz3 = new Gracz(3, pionki3, wierzGracza3);
                gracz4 = new Gracz(4, pionki4, wierzGracza4);

                break;

            case 6:

                pionki1 = new ArrayList<>();
                pionki2 = new ArrayList<>();
                pionki3 = new ArrayList<>();
                pionki4 = new ArrayList<>();
                ArrayList<ParaWspolrzednych> pionki5 = new ArrayList<>();
                ArrayList<ParaWspolrzednych> pionki6 = new ArrayList<>();
                wierzGracza1 = new ArrayList<>();
                wierzGracza2 = new ArrayList<>();
                wierzGracza3 = new ArrayList<>();
                wierzGracza4 = new ArrayList<>();
                ArrayList<ParaWspolrzednych> wierzGracza5 = new ArrayList<>();
                ArrayList<ParaWspolrzednych> wierzGracza6 = new ArrayList<>();

                for (int i = 0; i < wierzcholek1.size(); i++) {
                    setZawartoscTablicyOdInt(wierzcholek1.get(i).getX(), wierzcholek1.get(i).getY(), 1);
                    setZawartoscTablicyOdInt(wierzcholek2.get(i).getX(), wierzcholek2.get(i).getY(), 2);
                    setZawartoscTablicyOdInt(wierzcholek3.get(i).getX(), wierzcholek3.get(i).getY(), 3);
                    setZawartoscTablicyOdInt(wierzcholek4.get(i).getX(), wierzcholek4.get(i).getY(), 4);
                    setZawartoscTablicyOdInt(wierzcholek5.get(i).getX(), wierzcholek5.get(i).getY(), 5);
                    setZawartoscTablicyOdInt(wierzcholek6.get(i).getX(), wierzcholek6.get(i).getY(), 6);

                    pionki1.add(wierzcholek1.get(i));
                    pionki2.add(wierzcholek2.get(i));
                    pionki3.add(wierzcholek3.get(i));
                    pionki4.add(wierzcholek4.get(i));
                    pionki5.add(wierzcholek5.get(i));
                    pionki6.add(wierzcholek6.get(i));

                    wierzGracza1.add(wierzcholek4.get(i));
                    wierzGracza2.add(wierzcholek5.get(i));
                    wierzGracza3.add(wierzcholek6.get(i));
                    wierzGracza4.add(wierzcholek1.get(i));
                    wierzGracza5.add(wierzcholek2.get(i));
                    wierzGracza6.add(wierzcholek3.get(i));
                }

                gracz1 = new Gracz(1, pionki1, wierzGracza1);
                gracz2 = new Gracz(2, pionki2, wierzGracza2);
                gracz3 = new Gracz(3, pionki3, wierzGracza3);
                gracz4 = new Gracz(4, pionki4, wierzGracza4);
                gracz5 = new Gracz(5, pionki5, wierzGracza5);
                gracz6 = new Gracz(6, pionki6, wierzGracza6);

                break;

        }

    }

}

