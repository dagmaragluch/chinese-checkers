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
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class Main extends Application {
	Stage primaryStage;
	Scene start, gra;
	GridPane plan;
	Gra grac;

	@Override
	public void start(Stage primaryStage) throws Exception {

		primaryStage.setTitle("Trylma");

		Label podajlg = new Label("Liczba graczy:");
		Label podajlb = new Label("Liczba botów:");
		ObservableList<Integer> liczbagr = FXCollections.observableArrayList(2,3,4,6);
		ObservableList<Integer> liczbabot = FXCollections.observableArrayList(0,1,2,3,4,5);
		ChoiceBox liczbagraczy=new ChoiceBox<Integer>(liczbagr);
		ChoiceBox liczbabotow=new ChoiceBox<>(liczbabot);
		liczbagraczy.getSelectionModel().select(3);
		liczbabotow.getSelectionModel().select(0);


		Button dalej = new Button("Przejdz do gry");
		dalej.setOnAction(e -> {
			
			grac = new Gra((int)liczbagraczy.getValue());
			for (int i = 0; i < 17; i++) {
				for (int j = 0; j < 25; j++) {
					ColumnConstraints column = new ColumnConstraints(22);
					plan.getColumnConstraints().add(column);
					try {
						plan.add(grac.betaSerwer.plansza.tablica[i][j], j, i, 1, 1);
						grac.betaSerwer.plansza.tablica[i][j].addEventHandler(MouseEvent.MOUSE_CLICKED, new MyEventHandler());
					}
					catch (NullPointerException n) {}
				}
				RowConstraints row = new RowConstraints(37);
				plan.getRowConstraints().add(row);
			}
			primaryStage.setScene(gra);
		});

		VBox wybor = new VBox(podajlg, liczbagraczy, podajlb, liczbabotow, dalej);
		wybor.setPadding(new Insets(10,10,10,10));
		wybor.setSpacing(10);
		wybor.setAlignment(Pos.CENTER);

		start = new Scene(wybor, 190,190);



		Button zakonczture = new Button("Zakoncz Ture");
		zakonczture.setOnAction(e -> {
			grac.skonczylem();
		});
		
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

			Pole temp = (Pole)evt.getSource();
			grac.wykonaj_ruch(plan.getRowIndex(temp), plan.getColumnIndex(temp));
			
		}
	}

}