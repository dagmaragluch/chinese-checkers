public class Plansza {

    private final int rzedy = 33;
    private final int kol = 25;

    private int[][] plansza = new int[rzedy][kol];


    public void uzupelnianie_planszy() {
        for (int i = 0; i < rzedy; i++) {
            for (int j = 0; j < kol; j++) {
                plansza[i][j] = 9;
            }
        }

        plansza[0][12] = 0;
        plansza[2][11] = 0;
        plansza[2][13] = 0;
        plansza[4][10] = 0;
        plansza[4][12] = 0;
        plansza[4][14] = 0;
        plansza[6][9] = 0;
        plansza[6][11] = 0;
        plansza[6][13] = 0;
        plansza[6][15] = 0;

        for(int k=0; k < kol; k=k+2){
            plansza[8][k] = 0;
            plansza[24][k] = 0;
        }
        for(int k=1; k < kol; k=k+2){
            plansza[10][k] = 0;
            plansza[22][k] = 0;
        }
        for(int k=2; k < kol-2; k=k+2){
            plansza[12][k] = 0;
            plansza[20][k] = 0;
        }
        for(int k=3; k < kol-2; k=k+2){
            plansza[14][k] = 0;
            plansza[18][k] = 0;
        }
        for(int k=4; k < kol-4; k=k+2) plansza[16][k] = 0;

        plansza[32][12] = 0;
        plansza[30][11] = 0;
        plansza[30][13] = 0;
        plansza[28][10] = 0;
        plansza[28][12] = 0;
        plansza[28][14] = 0;
        plansza[26][9] = 0;
        plansza[26][11] = 0;
        plansza[26][13] = 0;
        plansza[26][15] = 0;


        for (int i = 0; i < rzedy; i++) {
            for (int j = 0; j < kol; j++) {
                System.out.print(plansza[i][j]);
            }
            System.out.print("\n");
        }
    }


    int getZawartoscTablicy(int x, int y){
        return plansza[x][y];
    }

    int getZawartoscTablicyOdParyWspolrzednych(ParaWspolrzednych paraWspolrzednych){
        return plansza[paraWspolrzednych.getY()][paraWspolrzednych.getY()];
    }

    void setZawartoscTablicyOdParyWspolrzednych(ParaWspolrzednych paraWspolrzednych, int NaCoZmieniamy){
        //if(getZawartoscTablicy(ParaWspolrzednych paraWspolrzednych)!=9)   //jakieś zabezpieczenie dodać żeby nie dało się zmienić pól z 9
        plansza[paraWspolrzednych.getX()][paraWspolrzednych.getY()] = NaCoZmieniamy;
    }

    void setZawartoscTablicyOdInt(int x, int y, int NaCoZmieniamy){
        if(plansza[x][y] == 9 ) {  // zabezpieczenie żeby nie dało się zmienić pól z 9
            plansza[x][y] = NaCoZmieniamy;
        }

    }


}

