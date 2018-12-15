import java.util.ArrayList;
import java.util.Map.Entry;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class Main extends Application {
	Stage primaryStage;
	Scene start, gra;
	BetaSerwer bserver=new BetaSerwer();
	GridPane plan;
	ObrazPlanszy stanplan;
	//to jest test gita
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		primaryStage.setTitle("Trylma");		
		
		Label podajlg = new Label("Liczba graczy:");
		Label podajlb = new Label("Liczba bot�w:");
		ObservableList<Integer> liczbagr = FXCollections.observableArrayList(2,3,4,6);
		ObservableList<Integer> liczbabot = FXCollections.observableArrayList(0,1,2,3,4,5);
		ChoiceBox liczbagraczy=new ChoiceBox<Integer>(liczbagr);
		ChoiceBox liczbabotow=new ChoiceBox<>(liczbabot);
		liczbagraczy.getSelectionModel().select(3);
		liczbabotow.getSelectionModel().select(0);
		
		
		Button dalej = new Button("Przejdz do gry");
		dalej.setOnAction(e -> {
			bserver.plansza=new PoczatkoweUstawienia((int)liczbagraczy.getValue());
			stanplan = new ObrazPlanszy(bserver.plansza);
			
			for (int i = 0; i < 33; i++) {
	            for (int j = 0; j < 25; j++) {
	            	ColumnConstraints column = new ColumnConstraints(20);
	            	plan.getColumnConstraints().add(column);                    	
	            }
	            RowConstraints row = new RowConstraints(20);
	            plan.getRowConstraints().add(row);
	        }
		
			for (ParaWspolrzednych key: stanplan.keySet()) {
				plan.add(stanplan.get(key), key.getY(), key.getX(), 1, 2);
				stanplan.get(key).addEventHandler(MouseEvent.MOUSE_CLICKED, new MyEventHandler());
			}
			primaryStage.setScene(gra); 
		});
		
		VBox wybor = new VBox(podajlg, liczbagraczy, podajlb, liczbabotow, dalej);
		wybor.setPadding(new Insets(10,10,10,10));
		wybor.setSpacing(10);
		wybor.setAlignment(Pos.CENTER);
		
		start = new Scene(wybor, 190,190);
		
	
		
		Button zakonczture = new Button("Zako�cz Tur�");
		plan = new GridPane();
		BorderPane trybgry = new BorderPane();
		trybgry.setPadding(new Insets(10,10,10,10));
		trybgry.setCenter(plan);
		trybgry.setAlignment(plan, Pos.CENTER);
		trybgry.setBottom(zakonczture);
		trybgry.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
	
		
		
		plan.setPadding(new Insets(10,20,10,20));
		//plan.getTransforms().add(new Rotate(180, Rotate.X_AXIS));
		gra = new Scene(trybgry, 610, 750);
		
		primaryStage.setScene(start);
		primaryStage.setResizable(false);
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);

	}
	
	private class MyEventHandler implements EventHandler<Event>{
		
        @Override
        public void handle(Event evt) {
        	for (ParaWspolrzednych temp : bserver.listaPodswietlanychPol2) {
       			//stanplan.get(temp).setFill(Color.WHITE);
       			} 
        	bserver.listaPodswietlanychPol2.clear();
        	//bserver.plansza.pokazTablice(bserver.plansza);
            for (Entry<ParaWspolrzednych, Pole> entry : stanplan.entrySet()) {
        	   //System.out.println(entry.getKey().getX() + " "+ entry.getKey().getY());
               if (entry.getValue()!=null && entry.getValue().equals((Pole)evt.getSource())) {
                   System.out.println(entry.getKey().getX() + " "+ entry.getKey().getY());
                   bserver.gdzie_mozna_przesunac2(entry.getKey().getX(), entry.getKey().getY());
                   //bserver.gdzie_mozna_przeskoczyc(entry.getKey().getX(), entry.getKey().getY());
               }
            }
            for (ParaWspolrzednych temp : bserver.listaPodswietlanychPol2) {
   			   stanplan.get(temp).setFill(Color.LIGHTYELLOW);
        	   System.out.println( "x=" +temp.getX() + ", y=" + temp.getY());   
   			}  
           
        }
    }

}
