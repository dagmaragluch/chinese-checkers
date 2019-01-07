import java.io.*;

import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
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
    private final static int ServerPort = 12372;
    private Socket s;
    private Thread readMessage;
    private String info;
    private StringTokenizer st;
    public Gra board;
    private Integer mojkolor;
    public static Integer czyjaTura = 0;
    private Integer ilegraczy, ilebotow = 0;
    
    static Stage stage;
	private Scene start, gra;
	private GridPane plan;
	
	@Override
	public void init(){
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
	}


    public void createreadthread(){
        readMessage = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        info = dis.readUTF();
                        System.out.println("Connected");
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
    
    public void inputhandler(){
    	System.out.println(info);
    	if(info != null){
			if(info.startsWith("START")){
                st = new StringTokenizer(info,";");
                st.nextToken();
                mojkolor = Integer.parseInt(st.nextToken());
                ilegraczy = Integer.parseInt(st.nextToken());
                ilebotow = Integer.parseInt(st.nextToken());
                System.out.println(mojkolor + "/" + ilegraczy);
                if(mojkolor == ilegraczy) send("TURA");
			}
			else if(info.startsWith("FULL")){
				System.out.println("Serwer pelny");
				try {
					s.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
                System.exit(1);
			}
			else if (info.startsWith("RUCH")) {
				nowyruch(info);
			}
			else if (info.startsWith("TURA")) {
				st = new StringTokenizer(info,";");
                st.nextToken();
                czyjaTura = Integer.parseInt(st.nextToken());
			}
			else if (info.startsWith("BOT")) {
				poruszboty();
			}
		}
    }


	private void poruszboty() {
		for (int i=ilegraczy+1; i<=ilegraczy+ilebotow; i++) {
			board.ruch_bota(i);
			if (!board.zmiany.isEmpty()) {
				send("RUCH;" + board.zmiany.get(0) + ";" + board.zmiany.get(1) + ";" + board.zmiany.get(2) + ";" + board.zmiany.get(3) + ";" + i);
				board.zmiany.clear();
			}
		}
		
	}


	@Override
	public void start(Stage primaryStage) throws Exception {
		        
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
			ilebotow = (int)liczbabotow.getValue();
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
			board.nowatura();
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
		
		if(ilegraczy != null) {
			zbudujplansze();
			primaryStage.setScene(gra);
		}
		else primaryStage.setScene(start);
		
		primaryStage.setResizable(false);
		primaryStage.show();
		
		
	}
	
	public void zbudujplansze() {
		this.board = new Gra(ilegraczy+ilebotow, mojkolor);
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
				board.wykonaj_ruch(plan.getRowIndex(temp), plan.getColumnIndex(temp));
				if (!board.zmiany.isEmpty()) {
					send("RUCH;" + board.zmiany.get(0) + ";" + board.zmiany.get(1) + ";" + board.zmiany.get(2) + ";" + board.zmiany.get(3) + ";" + mojkolor);
					board.zmiany.clear();
				}
				if(board.czy_wygral(mojkolor))send("KONIEC");
			}

		}
	}
	
	private boolean mojatura() {
		System.out.println(czyjaTura + " " + mojkolor);
		if(czyjaTura == mojkolor) return true;
		return false;
	}
	
	public static void main(String[] args) throws Exception {
		Client c = new Client();
		c.launch();
	}
			  
}
