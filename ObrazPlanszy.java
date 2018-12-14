import java.util.HashMap;

import javafx.scene.layout.ColumnConstraints;

public class ObrazPlanszy extends HashMap<ParaWspolrzednych, Pole>{	
	
	ObrazPlanszy(Plansza plansza){
		for (int i = 0; i < 33; i++) {
            for (int j = 0; j < 25; j++) {
                if(plansza.getZawartoscTablicy(i,j)!=9) {
                	this.put(new ParaWspolrzednych(i,j), new Pole(plansza.getZawartoscTablicy(i,j)));
                }
            }
		}
	}
}
