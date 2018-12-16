import java.util.ArrayList;

public class BetaSerwer implements MetodySerwera {

    Plansza plansza;
    ArrayList<ParaWspolrzednych> listaPodswietlanychPol = new ArrayList<>();
    ArrayList<ParaWspolrzednych> listaPodswietlanychPol1 = new ArrayList<>();
    ArrayList<ParaWspolrzednych> listaPodswietlanychPol2 = new ArrayList<>();


    @Override
    public void gdzie_mozna_przesunac(int x, int y) {  //czy rozdzielić Arraylisty podswitlanych pol na dwie ???

        if(plansza.getZawartoscTablicy(x+1, y+1) == 0){
            listaPodswietlanychPol1.add(new ParaWspolrzednych(x+1, y+1));
        }
        if(plansza.getZawartoscTablicy(x, y+2) == 0){
            listaPodswietlanychPol1.add(new ParaWspolrzednych(x, y+2));
        }
        if(plansza.getZawartoscTablicy(x+1, y-1) == 0){
            listaPodswietlanychPol1.add(new ParaWspolrzednych(x+1, y-1));
        }
        if(plansza.getZawartoscTablicy(x-1, y-1) == 0){
            listaPodswietlanychPol1.add(new ParaWspolrzednych(x-1, y-1));
        }
        if(plansza.getZawartoscTablicy(x, y-2) == 0){
            listaPodswietlanychPol1.add(new ParaWspolrzednych(x, y-2));
        }
        if(plansza.getZawartoscTablicy(x-1, y+1) == 0){
            listaPodswietlanychPol1.add(new ParaWspolrzednych(x-1, y+1));
        }
        listaPodswietlanychPol.addAll(listaPodswietlanychPol1);
    }

    @Override
    public void gdzie_mozna_przeskoczyc(int x, int y) {

        if(plansza.getZawartoscTablicy(x+1, y+1) > 0 && plansza.getZawartoscTablicy(x+1, y+1) !=9 ){    //spr czy pole jest zajęte
            if(plansza.getZawartoscTablicy(x+2, y+2) == 0)    //spr czy nastepne pole jest wolne
                listaPodswietlanychPol2.add(new ParaWspolrzednych(x+2, y+2));
        }
        if(plansza.getZawartoscTablicy(x+1, y-1) > 0 && plansza.getZawartoscTablicy(x+1, y-1) !=9 ){
            if(plansza.getZawartoscTablicy(x+2, y-2) == 0)
                listaPodswietlanychPol2.add(new ParaWspolrzednych(x+2, y-2));
        }
        if(plansza.getZawartoscTablicy(x, y-2) > 0 && plansza.getZawartoscTablicy(x, y-2) !=9 ){
            if(plansza.getZawartoscTablicy(x, y-4) == 0)
                listaPodswietlanychPol2.add(new ParaWspolrzednych(x, y-4));
        }
        if(plansza.getZawartoscTablicy(x-1, y-1) > 0 && plansza.getZawartoscTablicy(x-1, y-1) !=9 ){
            if(plansza.getZawartoscTablicy(x-2, y-2) == 0)
                listaPodswietlanychPol2.add(new ParaWspolrzednych(x-2, y-2));
        }
        if(plansza.getZawartoscTablicy(x-1, y+1) > 0 && plansza.getZawartoscTablicy(x-1, y+1) !=9 ){
            if(plansza.getZawartoscTablicy(x-2, y+2) == 0)
                listaPodswietlanychPol2.add(new ParaWspolrzednych(x-2, y+2));
        }
        if(plansza.getZawartoscTablicy(x, y+2) > 0 && plansza.getZawartoscTablicy(x, y+2) !=9 ){
            if(plansza.getZawartoscTablicy(x, y+4) == 0)
                listaPodswietlanychPol2.add(new ParaWspolrzednych(x, y+4));
        }
        listaPodswietlanychPol.addAll(listaPodswietlanychPol2);
    }
    
    public void wyczysc() {
    	listaPodswietlanychPol.clear();
    	listaPodswietlanychPol1.clear();
    	listaPodswietlanychPol2.clear();
    }
}
