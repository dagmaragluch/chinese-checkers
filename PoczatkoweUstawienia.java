import java.util.ArrayList;

public class PoczatkoweUstawienia extends Plansza {


    public void poczatkoweUstawienia() {

        //Plansza plansza = new Plansza();


        int iluGraczy = 2;

        ArrayList<ParaWspolrzednych> wierzcholek1 = new ArrayList<>();
        ArrayList<ParaWspolrzednych> wierzcholek2 = new ArrayList<>();
        ArrayList<ParaWspolrzednych> wierzcholek3 = new ArrayList<>();
        ArrayList<ParaWspolrzednych> wierzcholek4 = new ArrayList<>();
        ArrayList<ParaWspolrzednych> wierzcholek5 = new ArrayList<>();
        ArrayList<ParaWspolrzednych> wierzcholek6 = new ArrayList<>();


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
                for (int i = 0; i < wierzcholek1.size(); i++) {
                  setZawartoscTablicyOdInt(wierzcholek1.get(i).getX(), wierzcholek1.get(i).getY(), 1);
                  setZawartoscTablicyOdInt(wierzcholek4.get(i).getX(), wierzcholek4.get(i).getY(), 2);
                }

                ArrayList<ParaWspolrzednych> pionki1= new ArrayList<>();
                for (int i = 0; i < wierzcholek1.size(); i++){
                    pionki1.add(wierzcholek1.get(i));
                }
                Gracz gracz1 = new Gracz(1, pionki1 );

                ArrayList<ParaWspolrzednych> pionki2= new ArrayList<>();
                for (int i = 0; i < wierzcholek4.size(); i++){
                    pionki2.add(wierzcholek1.get(i));
                }
                Gracz gracz2 = new Gracz(2, pionki2 );


                break;
//
//            case 3:
//                for (int i = 0; i < wierzcholek1.size(); i++) {
//                    plansza[wierzcholek1.get(i).getX()][wierzcholek1.get(i).getY()] = 1;
//                    plansza[wierzcholek3.get(i).getX()][wierzcholek3.get(i).getY()] = 2;
//                    plansza[wierzcholek5.get(i).getX()][wierzcholek5.get(i).getY()] = 3;
//                }
//                break;
//
//            case 4:
//                for (int i = 0; i < wierzcholek1.size(); i++) {
//                    plansza[wierzcholek1.get(i).getX()][wierzcholek1.get(i).getY()] = 1;
//                    plansza[wierzcholek3.get(i).getX()][wierzcholek3.get(i).getY()] = 2;
//                    plansza[wierzcholek4.get(i).getX()][wierzcholek4.get(i).getY()] = 3;
//                    plansza[wierzcholek6.get(i).getX()][wierzcholek6.get(i).getY()] = 4;
//                }
//                break;
//
//            case 6:
//                for (int i = 0; i < wierzcholek1.size(); i++) {
//                    plansza[wierzcholek1.get(i).getX()][wierzcholek1.get(i).getY()] = 1;
//                    plansza[wierzcholek2.get(i).getX()][wierzcholek2.get(i).getY()] = 2;
//                    plansza[wierzcholek3.get(i).getX()][wierzcholek3.get(i).getY()] = 3;
//                    plansza[wierzcholek4.get(i).getX()][wierzcholek4.get(i).getY()] = 4;
//                    plansza[wierzcholek5.get(i).getX()][wierzcholek5.get(i).getY()] = 5;
//                    plansza[wierzcholek6.get(i).getX()][wierzcholek6.get(i).getY()] = 6;

             //       System.out.println("KSDNJFKJSBCVKSBSD MFLKWEN");

              //  }



        }


    }

}
