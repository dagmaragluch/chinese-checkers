import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Arrays;
import java.util.StringTokenizer;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Client extends Application{
	
	private static DataInputStream dis;
    private static DataOutputStream dos;    
    private final static int ServerPort = 12349;
    private Socket s;
    private Thread sendMessage;
    private Thread readMessage;
    private String info = "";
    private String msg;
    private StringTokenizer st;
    public Gra board;
    static int ktory = 0;
    int czyjaTura;
    
    private Stage primaryStage;
	private Scene start, gra;
	private GridPane plan;

    public Client() {
        try {
            InetAddress ip = InetAddress.getByName("localhost");
            s = new Socket(ip, ServerPort);
            dis = new DataInputStream(s.getInputStream()); 
            dos = new DataOutputStream(s.getOutputStream());
        } catch (IOException e) {
            System.out.print("Server is Offline!");
            System.exit(1);
        }
        ktory=ktory+1;
        createreadthread();
        readMessage.start();
    }

    public void createreadthread(){
        readMessage = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        msg = dis.readUTF();
                    } catch (IOException e) {
                        try {
                            s.close();
                            System.exit(1);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                    inputhandler();
                }
            }
        });
    }
    
    private void inputhandler(){
        /*if(msg != null){
            if(msg.startsWith("START")){
                st = new StringTokenizer(msg,";");
                st.nextToken();
                czyjaTura = Integer.parseInt(st.nextToken());
                }
            } else if(msg.startsWith("Wrng")){
                st = new StringTokenizer(msg,";");
                st.nextToken();
                info = st.nextToken();
                if(info != null){
                    //serverinfo.setText("WRONG "+info);
                }
            } else if(msg.startsWith("MOVE")){
                    //move(msg);
            } else if(msg.startsWith("RESTART")){
                    //restart();
            } else if(msg.startsWith("TURN")){
                    //turn(msg);
            } else if(msg.startsWith("WIN")){
                    //win(msg);
            } else if(msg.startsWith("COLOR")){
                //colormsg(msg);
            }*/
        }


	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Trylma");
		
		//Zawartoœc okna startowego

		Label podajlg = new Label("Liczba graczy:");
		Label podajlb = new Label("Liczba botow:");
		ObservableList<Integer> liczbagr = FXCollections.observableArrayList(2,3,4,6);
		ObservableList<Integer> liczbabot = FXCollections.observableArrayList(0,1,2,3,4,5);
		ChoiceBox liczbagraczy=new ChoiceBox<Integer>(liczbagr);
		ChoiceBox liczbabotow=new ChoiceBox<>(liczbabot);
		liczbagraczy.getSelectionModel().select(0);
		liczbabotow.getSelectionModel().select(0);


		Button dalej = new Button("Przejdz do gry");
		dalej.setOnAction(e -> { //konstrukcja nowej gry i budowanie planszy
			Client c = new Client();
			this.board = new Gra((int)liczbagraczy.getValue(), ktory);
			for (int i = 0; i < 17; i++) {
				for (int j = 0; j < 25; j++) {
					ColumnConstraints column = new ColumnConstraints(22);
					plan.getColumnConstraints().add(column);
					try {
						plan.add(board.betaSerwer.plansza.tablica[i][j], j, i, 1, 1);
						board.betaSerwer.plansza.tablica[i][j].addEventHandler(MouseEvent.MOUSE_CLICKED, new MyEventHandler());
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


		//Zawartosc okna trzymajacego plansze

		Button zakonczture = new Button("Zakoncz Ture");
		zakonczture.setOnAction(e -> {
			board.skonczylem();
		});
		
		plan = new GridPane();
		BorderPane trybgry = new BorderPane();
		trybgry.setPadding(new Insets(10,10,10,10));
		trybgry.setCenter(plan);
		trybgry.setAlignment(plan, Pos.CENTER);
		trybgry.setBottom(zakonczture);
		trybgry.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);

		plan.setPadding(new Insets(10,20,10,20));
		gra = new Scene(trybgry, 610, 750);

		if(ktory==1) {
			primaryStage.setScene(start);
		}
		else primaryStage.setScene(gra);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	private class MyEventHandler implements EventHandler<Event>{ //co robi klikniêcie NA POLE

		@Override
		public void handle(Event evt) {

			Pole temp = (Pole)evt.getSource();
			board.wykonaj_ruch(plan.getRowIndex(temp), plan.getColumnIndex(temp));

		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
    
}
