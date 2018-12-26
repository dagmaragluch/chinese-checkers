import java.util.ArrayList;

public class PoczatkoweUstawienia extends Plansza {

    protected int iluGraczy;
    protected int ileBotow;

    protected Gracz gracz1;
    protected Gracz gracz2;
    protected Gracz gracz3;
    protected Gracz gracz4;
    protected Gracz gracz5;
    protected Gracz gracz6;
    
    ArrayList<Gracz> listaGraczy = new ArrayList<>();
    
    ArrayList<ParaWspolrzednych> pionki1;
    ArrayList<ParaWspolrzednych> pionki2;
    ArrayList<ParaWspolrzednych> pionki3;
    ArrayList<ParaWspolrzednych> pionki4;
    ArrayList<ParaWspolrzednych> pionki5;
    ArrayList<ParaWspolrzednych> pionki6;

    private ArrayList<ParaWspolrzednych> wierzcholek1 = new ArrayList<>();
    private ArrayList<ParaWspolrzednych> wierzcholek2 = new ArrayList<>();
    private ArrayList<ParaWspolrzednych> wierzcholek3 = new ArrayList<>();
    private ArrayList<ParaWspolrzednych> wierzcholek4 = new ArrayList<>();
    private ArrayList<ParaWspolrzednych> wierzcholek5 = new ArrayList<>();
    private ArrayList<ParaWspolrzednych> wierzcholek6 = new ArrayList<>();

    public void ustawPionki(int a) {

        this.iluGraczy = a;

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

        wierzcholek2.add(new ParaWspolrzednych(4, 18));
        wierzcholek2.add(new ParaWspolrzednych(4, 20));
        wierzcholek2.add(new ParaWspolrzednych(4, 22));
        wierzcholek2.add(new ParaWspolrzednych(4, 24));
        wierzcholek2.add(new ParaWspolrzednych(5, 19));
        wierzcholek2.add(new ParaWspolrzednych(5, 21));
        wierzcholek2.add(new ParaWspolrzednych(5, 23));
        wierzcholek2.add(new ParaWspolrzednych(6, 20));
        wierzcholek2.add(new ParaWspolrzednych(6, 22));
        wierzcholek2.add(new ParaWspolrzednych(7, 21));

        wierzcholek3.add(new ParaWspolrzednych(12, 18));
        wierzcholek3.add(new ParaWspolrzednych(12, 20));
        wierzcholek3.add(new ParaWspolrzednych(12, 22));
        wierzcholek3.add(new ParaWspolrzednych(12, 24));
        wierzcholek3.add(new ParaWspolrzednych(11, 19));
        wierzcholek3.add(new ParaWspolrzednych(11, 21));
        wierzcholek3.add(new ParaWspolrzednych(11, 23));
        wierzcholek3.add(new ParaWspolrzednych(10, 20));
        wierzcholek3.add(new ParaWspolrzednych(10, 22));
        wierzcholek3.add(new ParaWspolrzednych(9, 21));

        wierzcholek4.add(new ParaWspolrzednych(16, 12));
        wierzcholek4.add(new ParaWspolrzednych(15, 11));
        wierzcholek4.add(new ParaWspolrzednych(15, 13));
        wierzcholek4.add(new ParaWspolrzednych(14, 10));
        wierzcholek4.add(new ParaWspolrzednych(14, 12));
        wierzcholek4.add(new ParaWspolrzednych(14, 14));
        wierzcholek4.add(new ParaWspolrzednych(13, 9));
        wierzcholek4.add(new ParaWspolrzednych(13, 11));
        wierzcholek4.add(new ParaWspolrzednych(13, 13));
        wierzcholek4.add(new ParaWspolrzednych(13, 15));

        wierzcholek5.add(new ParaWspolrzednych(12, 0));
        wierzcholek5.add(new ParaWspolrzednych(12, 2));
        wierzcholek5.add(new ParaWspolrzednych(12, 4));
        wierzcholek5.add(new ParaWspolrzednych(12, 6));
        wierzcholek5.add(new ParaWspolrzednych(11, 1));
        wierzcholek5.add(new ParaWspolrzednych(11, 3));
        wierzcholek5.add(new ParaWspolrzednych(11, 5));
        wierzcholek5.add(new ParaWspolrzednych(10, 2));
        wierzcholek5.add(new ParaWspolrzednych(10, 4));
        wierzcholek5.add(new ParaWspolrzednych(9, 3));

        wierzcholek6.add(new ParaWspolrzednych(4, 0));
        wierzcholek6.add(new ParaWspolrzednych(4, 2));
        wierzcholek6.add(new ParaWspolrzednych(4, 4));
        wierzcholek6.add(new ParaWspolrzednych(4, 6));
        wierzcholek6.add(new ParaWspolrzednych(5, 1));
        wierzcholek6.add(new ParaWspolrzednych(5, 3));
        wierzcholek6.add(new ParaWspolrzednych(5, 5));
        wierzcholek6.add(new ParaWspolrzednych(6, 2));
        wierzcholek6.add(new ParaWspolrzednych(6, 4));
        wierzcholek6.add(new ParaWspolrzednych(7, 3));


        switch (iluGraczy) {

            case 2:
                //tworzenie 2 graczy z uzupełnionymi arraylistami pionkow

                pionki1 = new ArrayList<>();
                pionki2 = new ArrayList<>();

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

                gracz1 = new Gracz(1, wierzGracza1, false, false, pionki1);
                gracz2 = new Gracz(2, wierzGracza2, false, false, pionki2);
                listaGraczy.add(gracz1);
                listaGraczy.add(gracz2);

                break;

            case 3:
                pionki1 = new ArrayList<>();
                pionki2 = new ArrayList<>();
                pionki3 = new ArrayList<>();
                wierzGracza1 = new ArrayList<>();
                wierzGracza2 = new ArrayList<>();
                ArrayList<ParaWspolrzednych> wierzGracza3 = new ArrayList<>();

                for (int i = 0; i < wierzcholek1.size(); i++) {
                    setZawartoscTablicyOdInt(wierzcholek4.get(i).getX(), wierzcholek4.get(i).getY(), 1);
                    setZawartoscTablicyOdInt(wierzcholek6.get(i).getX(), wierzcholek6.get(i).getY(), 2);
                    setZawartoscTablicyOdInt(wierzcholek2.get(i).getX(), wierzcholek2.get(i).getY(), 3);

                    pionki1.add(wierzcholek1.get(i));
                    pionki2.add(wierzcholek3.get(i));
                    pionki3.add(wierzcholek5.get(i));

                    wierzGracza1.add(wierzcholek4.get(i));
                    wierzGracza2.add(wierzcholek6.get(i));
                    wierzGracza3.add(wierzcholek2.get(i));

                }

                listaGraczy.add(gracz1 = new Gracz(1, wierzGracza1, false, false, pionki1));
                listaGraczy.add(gracz2 = new Gracz(2, wierzGracza2, false, false, pionki2));
                listaGraczy.add(gracz3 = new Gracz(3, wierzGracza3, false, false, pionki3));

                break;

            case 4:

                pionki1 = new ArrayList<>();
                pionki2 = new ArrayList<>();
                pionki3 = new ArrayList<>();
                pionki4 = new ArrayList<>();
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

                listaGraczy.add(gracz1 = new Gracz(1, wierzGracza1, false, false, pionki1));
                listaGraczy.add(gracz2 = new Gracz(2, wierzGracza2, false, false, pionki2));
                listaGraczy.add(gracz3 = new Gracz(3, wierzGracza3, false, false, pionki3));
                listaGraczy.add(gracz4 = new Gracz(4, wierzGracza4, false, false, pionki4));

                break;

            case 6:

                pionki1 = new ArrayList<>();
                pionki2 = new ArrayList<>();
                pionki3 = new ArrayList<>();
                pionki4 = new ArrayList<>();
                pionki5 = new ArrayList<>();
                pionki6 = new ArrayList<>();
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

                listaGraczy.add(gracz1 = new Gracz(1, wierzGracza1, false, false, pionki1));
                listaGraczy.add(gracz2 = new Gracz(2, wierzGracza2, false, false, pionki2));
                listaGraczy.add(gracz3 = new Gracz(3, wierzGracza3, false, false, pionki3));
                listaGraczy.add(gracz4 = new Gracz(4, wierzGracza4, false, false, pionki4));
                listaGraczy.add(gracz5 = new Gracz(5, wierzGracza5, false, false, pionki5));
                listaGraczy.add(gracz6 = new Gracz(6, wierzGracza6, false, false, pionki6));

                break;

        }

    }

    public void wprowadzBoty(int b){
        int i = listaGraczy.size();
        int j = 0; //ile botow juz wstswiono

        while(j<b){     //boty wstawimy jako ostatnich graczy
            listaGraczy.get(i-1).setCzyBot(true);
            i--;
            j++;
        }
    }


}

