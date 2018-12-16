import java.util.TreeSet;

public class Gra extends PoczatkoweUstawienia implements MetodyDoGry {

    Gra(int a) {
    	ustawPionki(a);
    	this.betaSerwer.plansza=this;
    	this.iluGraczy=a;
    }

	private int tura=1;
    Gracz aktualnyGracz;
    BetaSerwer betaSerwer = new BetaSerwer();
    
    private int staryX;
    private int staryY;
    private int ruch=0;
    private boolean czyostatni=false;


    @Override
    public int ktora_tura() {
        if (tura < 1) {
            tura = this.iluGraczy;
        }
        return tura;
    }

    @Override
    public Gracz czyja_tura() {
        switch (ktora_tura()) {
            case 1:
                aktualnyGracz =  gracz1; return aktualnyGracz;
            case 2:
                aktualnyGracz =  gracz2; return aktualnyGracz;
            case 3:
                aktualnyGracz =  gracz3; return aktualnyGracz;
            case 4:
                aktualnyGracz =  gracz4; return aktualnyGracz;
            case 5:
                aktualnyGracz =  gracz5; return aktualnyGracz;
            case 6:
                aktualnyGracz =  gracz6; return aktualnyGracz;
            default: return aktualnyGracz;
        }
    }

    @Override
    public void skonczylem() {
    	for (ParaWspolrzednych pw : betaSerwer.listaPodswietlanychPol) {
			betaSerwer.plansza.setZawartoscTablicyOdInt(pw.getX(), pw.getY(), 0);
		}
		betaSerwer.wyczysc();
        tura--;
        ruch=0;
        czyostatni=false;
        
    }
    
    @Override
    public void policz_nowe_ruchy (int x, int y) {
    	for (ParaWspolrzednych pw : betaSerwer.listaPodswietlanychPol) {
			betaSerwer.plansza.setZawartoscTablicyOdInt(pw.getX(), pw.getY(), 0);
		}
		betaSerwer.wyczysc();
		betaSerwer.gdzie_mozna_przesunac(x, y);
        betaSerwer.gdzie_mozna_przeskoczyc(x, y);
        for (ParaWspolrzednych pw : betaSerwer.listaPodswietlanychPol) {
			betaSerwer.plansza.setZawartoscTablicyOdInt(pw.getX(), pw.getY(), 8);
		}
        staryX=x;
        staryY=y;
    }


    @Override
    public void wykonaj_ruch(int x, int y) {

        if (!czy_wygral()) {
        	if(!czyostatni) {
        		if (ruch == 0) {
        			if(czyja_tura().getKolorGracza() ==  getZawartoscTablicy(x, y)) {
        				policz_nowe_ruchy(x,y);
        				return;
        			}
        		}
        		for (ParaWspolrzednych para : betaSerwer.listaPodswietlanychPol2) {
        			if (x == para.getX() && y == para.getY()) {
        				betaSerwer.plansza.setZawartoscTablicyOdInt(staryX, staryY, 0);
        				policz_nowe_ruchy(x, y);
        				betaSerwer.plansza.setZawartoscTablicyOdInt(x, y, czyja_tura().getKolorGracza());
        				ruch++;
                        return;
        			}
        		}
        		for (ParaWspolrzednych para : betaSerwer.listaPodswietlanychPol1) {
        			if (x == para.getX() && y == para.getY()) {
        				for (ParaWspolrzednych pw : betaSerwer.listaPodswietlanychPol) {
        					betaSerwer.plansza.setZawartoscTablicyOdInt(pw.getX(), pw.getY(), 0);
        				}
        				betaSerwer.wyczysc();
        				betaSerwer.plansza.setZawartoscTablicyOdInt(staryX, staryY, 0);
        				betaSerwer.plansza.setZawartoscTablicyOdInt(x, y, czyja_tura().getKolorGracza());
        				ruch++;
        				czyostatni=true;
        				return;
        			}
        		} 
        	} return;
             
        } else {
            skonczylem();
        }
    }

    @Override
    public boolean czy_wygral() {
    	for (ParaWspolrzednych para : czyja_tura().docelowyWierzcholek) {
			if(this.getZawartoscTablicy(para.getX(), para.getY())==czyja_tura().kolorGracza);
			else return false;
		}
    	return true;
    }


}
