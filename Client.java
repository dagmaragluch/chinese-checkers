import java.io.*;

import java.net.InetAddress;
import java.net.Socket;
import java.util.StringTokenizer;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Client extends Application{
	
	private static DataInputStream dis;
    private static DataOutputStream dos;    
    private final static int ServerPort = 12370;
    private Socket s;
    private Thread readMessage;
    private String info;
    private StringTokenizer st;
    public Gra board;
    private static Integer mojkolor;
    public static Integer czyjaTura;
    private Integer ilegraczy, ilebotow;
    
    static Stage primaryStage;
	private Scene start, gra;
	private GridPane plan;
	
	Client(){
		try {
            InetAddress ip = InetAddress.getByName("localhost");
            s = new Socket(ip, ServerPort);
            dis = new DataInputStream(s.getInputStream()); 
            dos = new DataOutputStream(s.getOutputStream());

        } catch (IOException e) {
            System.out.print("Server is Offline!");
            System.exit(1);
        }
        createreadthread();
        readMessage.start();
        System.out.println("Connected");
	}


    public void createreadthread(){
        readMessage = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        info = dis.readUTF();
                    } catch (IOException e) {
                        try {
                        	e.printStackTrace();
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
    public void send(String msg){
        try {
            dos.writeUTF(msg);
            dos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void inputhandler(){
    	System.out.println(info);
    	if(info != null){
			if(info.startsWith("NOWA")){
				mojkolor = 1;
			}
			else if(info.startsWith("START")){
                st = new StringTokenizer(info,";");
                st.nextToken();
                mojkolor = Integer.parseInt(st.nextToken());
                ilegraczy = Integer.parseInt(st.nextToken());
                
            }
			else if (info.startsWith("RUCH")) {
				nowyruch(info);
			}
			else if (info.startsWith("TURA")) {
				st = new StringTokenizer(info,";");
                st.nextToken();
                czyjaTura = Integer.parseInt(st.nextToken());
			}
		}
    }


	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Client c = new Client();
        
		primaryStage.setTitle("Trylma");
		
		//Zawartosc okna startowego
		Label podajlg = new Label("Liczba graczy:");
		Label podajlb = new Label("Liczba botow:");
		ObservableList<Integer> liczbagr = FXCollections.observableArrayList(2,3,4,6);
		ObservableList<Integer> liczbabot = FXCollections.observableArrayList(0,1,2,3,4,5);
		ChoiceBox<Integer> liczbagraczy=new ChoiceBox<Integer>(liczbagr);
		ChoiceBox<Integer> liczbabotow=new ChoiceBox<>(liczbabot);
		liczbagraczy.getSelectionModel().select(0);
		liczbabotow.getSelectionModel().select(0);

		Button dalej = new Button("Przejdz do gry");
		dalej.setOnAction(e -> { //konstrukcja nowej gry i budowanie planszy
			send("OPEN;" + (int)liczbagraczy.getValue() + ";PLAYERS;" + (int)liczbabotow.getValue() +";BOTS");
			mojkolor = 1;
			ilegraczy = (int)liczbagraczy.getValue();
			zbudujplansze();
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
			send("TURA");
			
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

		if (mojkolor != null && ilegraczy != null) {
			zbudujplansze();
    		primaryStage.setScene(gra);
        }else primaryStage.setScene(start);
		
		Client.primaryStage=primaryStage;
		primaryStage.setResizable(false);
		primaryStage.show();
		
		
	}
	
	public void zbudujplansze() {
		this.board = new Gra(ilegraczy, mojkolor);
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
	}
	
	public void nowyruch(String msg) {
		st = new StringTokenizer(msg,";");
        st.nextToken();
        board.betaSerwer.plansza.setZawartoscTablicyOdInt(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0);
        board.betaSerwer.plansza.setZawartoscTablicyOdInt(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
	}
	
	private class MyEventHandler implements EventHandler<Event>{ //co robi klikniecie NA POLE

		@Override
		public void handle(Event evt) {

			if(mojatura()) {
				Pole temp = (Pole)evt.getSource();
				ParaWspolrzednych ruch = board.ruch(plan.getRowIndex(temp), plan.getColumnIndex(temp));
				send("RUCH;" + board.staryX + ";" + board.staryY + ";" + ruch.getX() + ";" + ruch.getY() + ";" + mojkolor);
			}

		}
	}
	
	private static boolean mojatura() {
		System.out.println(czyjaTura + "" + mojkolor);
		if(czyjaTura == mojkolor) return true;
		return false;
	}
	
	public static void main(String[] args) throws Exception {
		launch();
	}
			  
}
