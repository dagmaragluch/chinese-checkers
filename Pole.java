import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class Pole extends Circle {
	
	private int kolor;
	
	Pole(int kolor){
		this.setRadius(19);
		setkolor(kolor);
	}
	
	void setkolor(int kolor) {
		switch (kolor) {
		case 0: this.setFill(Color.WHITE); break;
		case 1: this.setFill(Color.GREENYELLOW); break;
		case 2: this.setFill(Color.CORNFLOWERBLUE); break;
		case 3: this.setFill(Color.VIOLET); break;
		case 4: this.setFill(Color.RED); break;
		case 5: this.setFill(Color.ORANGE); break;
		case 6: this.setFill(Color.BROWN); break;
		case 8: this.setFill(Color.LIGHTYELLOW); break;
		}
		this.kolor = kolor;
	}
	
	int getkolor() {
		return this.kolor;
	}
}
