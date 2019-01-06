import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class Serwer {

    private final static int port = 12372;
    static ArrayList<Player> players = new ArrayList<>();
    ServerSocket listener;
    private volatile boolean isrunning = false;

    private static Integer iluGraczy = 1;
    static int currentPlayer = 0;
	static int iluBotow;


    public Serwer(int port) {
        try {
            listener = new ServerSocket(port);
            isrunning = true;
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
        System.out.println("Chinese checkers server is running on " + listener);
    }


    public static void main(String[] args) {
        Serwer s = new Serwer(port);
        try {
            s.listening();
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }

    private void listening() throws IOException {
        while (isrunning) {
			if (players.size() < iluGraczy) {
                players.add(new Player(listener.accept(), players.size()));
                players.get(players.size()-1).start();
                if (players.size() == 1) {
                	players.get(players.size()-1).send("NOWA");
                	iluGraczy = players.get(players.size()-1).value1;//w przyszlości od iluGraczy-ileBotow
                }
                else players.get(players.size()-1).send("START;" + players.size() + ";" + iluGraczy);
            } else if (players.size()==iluGraczy){
            	Player p = new Player(listener.accept(),99);                
                p.send("FULL");
                p.s.close();
            };  
        }
    }


    public static int setCurrentPlayer() {
        currentPlayer++;
        if (currentPlayer == players.size()+1) {
            currentPlayer = 1;
        }
        return currentPlayer;
    }
    


    public static void sendAll(String msg) {
        for(int i = 0; i<players.size(); i++){
        	players.get(i).send(msg);
        }
    }
    
    public static void setIluGraczy(int a) {
    	iluGraczy=a;
    }
    public static int getIluGraczy() {
    	return iluGraczy;
    }
    
    public static void exit(Player player){
        if(players.contains(player)) {
            players.remove(player);
        }
    }

    public void shutdown(){
        isrunning = false;
        try {
            listener.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}


/*****************************/

class Player extends Thread {
    private int id;
    private boolean czyZalogowany;
    private DataInputStream is;
    private DataOutputStream os;
    public Socket s;
    private StringTokenizer st;
    int value1, value2;

    public Player(Socket s, int id) {
        this.s = s;
        this.czyZalogowany = true;
        this.id = id;

        try {
            is = new DataInputStream(s.getInputStream());
            os = new DataOutputStream(s.getOutputStream());
        } catch (IOException e) {
            System.out.print("Socket is not connected");
        }
    }


    @Override
    public void run() {
        String received;
        while (czyZalogowany) {
            try {
                received = is.readUTF();
                inputhandler(received);
            } catch (IOException e) {
                try {
                    s.close();
                    czyZalogowany = false;
                    Serwer.exit(this);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        try {
            this.is.close();
            this.os.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void inputhandler(String received) {
    	System.out.println(received);
    	if (received.startsWith("OPEN")) {
    		st = new StringTokenizer(received,";");
            st.nextToken();
            Serwer.setIluGraczy(Integer.parseInt(st.nextToken()));
    	}
    	else if (received.startsWith("RUCH")) {
            ruch(received);
        } 
    	else if (received.startsWith("TURA")){
        	Serwer.sendAll("TURA" + ";" + Serwer.setCurrentPlayer());
        }
    	else if (received.startsWith("KONIEC")){
    		send("KONIEC");
    		Serwer.exit(this);
    	}
    }


    public void ruch(String received) {
        Serwer.sendAll(received);
    }


    public void send(String msg) {
        try {
            os.writeUTF(msg);
            os.flush();
            System.out.println(msg);
        } catch (IOException ex) {
            ex.printStackTrace();

        }


    }


}