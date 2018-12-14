import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class Pole extends Circle {
	Pole(int kolor){
		this.setRadius(19);
		switch (kolor) {
		case 0: this.setFill(Color.WHITE); break;
		case 1: this.setFill(Color.LIGHTGREEN); break;
		case 2: this.setFill(Color.LIGHTSALMON); break;
		case 3: this.setFill(Color.LIGHTPINK); break;
		case 4: this.setFill(Color.GREY); break;
		case 5: this.setFill(Color.LIGHTCORAL); break;
		case 6: this.setFill(Color.LIGHTSLATEGRAY); break;
		case 8: this.setFill(Color.LIGHTYELLOW); break;
	}
}
	
}
