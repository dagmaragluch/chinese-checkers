import java.io.*;

import java.net.InetAddress;
import java.net.Socket;
import java.util.StringTokenizer;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
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
    
    public Gra widok;
    private Integer mojkolor;
    public static Integer czyjaTura = 0;
    private Integer ilegraczy, ilebotow = 0;
    public final int[] stany = {2, 3, 4, 6};

    
    static Stage stage;
	private Scene start, gra;
	private GridPane siatka;
	private Label ktoja = new Label();
	private Label jakatura = new Label();
	private StringProperty tura = new SimpleStringProperty();
	private HBox dane;
	
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
    	if(info != null){
			
    		if(info.startsWith("START")){
                
				st = new StringTokenizer(info,";");
                st.nextToken();
                mojkolor = Integer.parseInt(st.nextToken());
                ilegraczy = Integer.parseInt(st.nextToken());
                ilebotow = Integer.parseInt(st.nextToken());
                
                if(mojkolor == ilegraczy) send("TURA");
			}
			else if(info.startsWith("MAX")){
				
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
                Platform.runLater(new Runnable() {
                    @Override public void run() {
                        if (czyjaTura == mojkolor) tura.setValue("Twoja kolej");
                        else tura.setValue("Tura gracza " + czyjaTura);
                    }
              });

			}
			else if (info.startsWith("BOT")) {
				poruszboty();
			}
		}
    }



	public void nowyruch(String msg) { //odzwierciedlenie ruchu na planszy
		st = new StringTokenizer(msg,";");
        st.nextToken();
        widok.sterownik.plansza.setZawartoscTablicyOdInt(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0);
        widok.sterownik.plansza.setZawartoscTablicyOdInt(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
	}

	private void poruszboty() { //wywo³anie ruchów wszystkich botów
		for (int i=ilegraczy+1; i<=ilegraczy+ilebotow; i++) {
			if(!widok.czy_wygral(i))
				widok.ruch_bota(i);
			if (widok.zmiany.size()==4) {
				send("RUCH;" + widok.zmiany.get(0) + ";" + widok.zmiany.get(1) + ";" + widok.zmiany.get(2) + ";" + widok.zmiany.get(3) + ";" + i);
				widok.zmiany.clear();
			}
		}
		
	}



	@Override
	public void start(Stage primaryStage) throws Exception {
		        
		primaryStage.setTitle("Trylma");
		
		//Zawartosc okna startowego
		Label podajlg = new Label("Liczba graczy:");
		Label podajlb = new Label("Liczba botow:");
		

		ChoiceBox<Integer> liczbagraczy = new ChoiceBox<Integer>();
		ChoiceBox<Integer> liczbabotow = new ChoiceBox<Integer>();
		liczbagraczy.getItems().addAll(1, 2, 3, 4, 5, 6);
		liczbagraczy.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
		      @Override
		      public void changed(ObservableValue<? extends Number> wartosc, Number stara, Number nowa) {
		    	  liczbabotow.getItems().clear();
		    	  for(int i=0; i<stany.length; i++) {
		    		  if (stany[i]-(int)nowa-1>=0) liczbabotow.getItems().add(stany[i]-(int)nowa-1);
		    	  }
		  		liczbabotow.getSelectionModel().select(0);
		      }
		});

		Button dalej = new Button("Przejdz do gry");
		dalej.setOnAction(e -> { //konstrukcja nowej gry i budowanie planszy
			send("OPEN;" + (int)liczbagraczy.getValue() + ";PLAYERS;" + (int)liczbabotow.getValue() +";BOTS");
			mojkolor = 1;
			ilegraczy = (int)liczbagraczy.getValue();
			ilebotow = (int)liczbabotow.getValue();
			zbudujplansze();
	    	primaryStage.setScene(gra);
	    	if(mojkolor == ilegraczy) send("TURA");
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
			widok.nowatura();
		});
		siatka = new GridPane();

		BorderPane trybgry = new BorderPane();
		trybgry.setPadding(new Insets(10,10,10,10));
		trybgry.setCenter(siatka);
		trybgry.setAlignment(siatka, Pos.CENTER);
		tura.setValue("Czekamy na graczy");
		jakatura.textProperty().bind(tura);
		dane = new HBox();
		dane.getChildren().addAll(jakatura, zakonczture);
		dane.setPadding(new Insets(3,3,3,180));
		dane.setSpacing(10);
		trybgry.setBottom(dane);

		siatka.setPadding(new Insets(10,20,10,20));
		gra = new Scene(trybgry, 610, 750);

		
		//ustawienie okna
		if(ilegraczy != null) { 
			zbudujplansze();
			primaryStage.setScene(gra);
		}
		else primaryStage.setScene(start); //ustawienie pierwszego gracza
		
		primaryStage.setResizable(false);
		primaryStage.show();
		
		
	}
	
	private class MyEventHandler implements EventHandler<Event>{ //co robi klikniecie NA POLE

		@Override
		public void handle(Event evt) {
			if(mojatura()) {
				Pole temp = (Pole)evt.getSource();
				widok.wykonaj_ruch(siatka.getRowIndex(temp), siatka.getColumnIndex(temp));
				if (!widok.zmiany.isEmpty()) {
					send("RUCH;" + widok.zmiany.get(0) + ";" + widok.zmiany.get(1) + ";" + widok.zmiany.get(2) + ";" + widok.zmiany.get(3) + ";" + mojkolor);
					widok.zmiany.clear();
				}
				if(widok.czy_wygral(mojkolor))send("KONIEC");
			}

		}
	}
	
	private boolean mojatura() {
		if(czyjaTura == mojkolor) return true;
		return false;
	}
	
	
	public void zbudujplansze() {
		this.widok = new Gra(ilegraczy+ilebotow, mojkolor);
		for (int i = 0; i < 17; i++) {
			for (int j = 0; j < 25; j++) {
				ColumnConstraints kolumna = new ColumnConstraints(22);
				siatka.getColumnConstraints().add(kolumna);
				try {
					siatka.add(widok.sterownik.plansza.tablica[i][j], j, i, 1, 1);
					widok.sterownik.plansza.tablica[i][j].addEventHandler(MouseEvent.MOUSE_CLICKED, new MyEventHandler());
				}
				catch (NullPointerException n) {}
			}
			RowConstraints rzad = new RowConstraints(37);
			siatka.getRowConstraints().add(rzad);
		}
		
		ktoja = new Label("Jestes " + widok.getkolor(mojkolor));		
		dane.getChildren().add(ktoja);
	}
	
	
	public static void main(String[] args) throws Exception {
		launch();
	}
			  
}
