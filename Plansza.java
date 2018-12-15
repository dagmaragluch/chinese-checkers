public class Plansza{

    private final int rzedy = 17;
    private final int kolumny = 25;

    public Pole[][] tablica;


    Plansza() {
    	tablica = new Pole[rzedy][kolumny];
        
        tablica[0][12] = new Pole(0);
        tablica[1][11] = new Pole(0);
        tablica[1][13] = new Pole(0);
        tablica[2][10] = new Pole(0);
        tablica[2][12] = new Pole(0);
        tablica[2][14] = new Pole(0);
        tablica[3][9] = new Pole(0);
        tablica[3][11] = new Pole(0);
        tablica[3][13] = new Pole(0);
        tablica[3][15] = new Pole(0);

        for(int k=0; k < kolumny; k=k+2){
            tablica[4][k] = new Pole(0);
            tablica[12][k] = new Pole(0);
        }
        for(int k=1; k < kolumny; k=k+2){
            tablica[5][k] = new Pole(0);
            tablica[11][k] = new Pole(0);
        }
        for(int k=2; k < kolumny-2; k=k+2){
            tablica[6][k] = new Pole(0);
            tablica[10][k] = new Pole(0);
        }
        for(int k=3; k < kolumny-2; k=k+2){
            tablica[7][k] = new Pole(0);
            tablica[9][k] = new Pole(0);
        }
        for(int k=4; k < kolumny-4; k=k+2) tablica[8][k] = new Pole(0);

        tablica[16][12] = new Pole(0);
        tablica[15][11] = new Pole(0);
        tablica[15][13] = new Pole(0);
        tablica[14][10] = new Pole(0);
        tablica[14][12] = new Pole(0);
        tablica[14][14] = new Pole(0);
        tablica[13][9] = new Pole(0);
        tablica[13][11] = new Pole(0);
        tablica[13][13] = new Pole(0);
        tablica[13][15] = new Pole(0);
    }


    int getZawartoscTablicy(int x, int y) throws ArrayIndexOutOfBoundsException{
    	int i=9;
        try{
        	if(tablica[x][y] != null ) i= tablica[x][y].getkolor();
        } catch (ArrayIndexOutOfBoundsException e) {}
    	return i;
    }

    void setZawartoscTablicyOdInt(int x, int y, int NaCoZmieniamy){
        if(tablica[x][y] != null ) tablica[x][y].setkolor(NaCoZmieniamy);
        else tablica[x][y]=new Pole(NaCoZmieniamy);

    }

}