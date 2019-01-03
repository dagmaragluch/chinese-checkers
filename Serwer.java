import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class Serwer {

    private final static int port = 12370;
    static ArrayList<Player> players = new ArrayList<>();
    ServerSocket listener;
    private boolean isrunning = false;

    Integer iluGraczy;
    static int currentPlayer = 0;
    static int liczbaZalogowanychGraczy = 0;
    static int index = 0;


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
			if (iluGraczy == null) {
                players.add(new Player(listener.accept(), liczbaZalogowanychGraczy));
                players.get(liczbaZalogowanychGraczy).start();
                players.get(liczbaZalogowanychGraczy).send("NOWA");
                liczbaZalogowanychGraczy++;
            } else if (players.size() <= iluGraczy) {     //w przyszlości od iluGraczy-ileBotow
                players.add(new Player(listener.accept(), liczbaZalogowanychGraczy));
                players.get(liczbaZalogowanychGraczy).start();
                players.get(liczbaZalogowanychGraczy).send("START;" + liczbaZalogowanychGraczy+1 + ";" + iluGraczy);
                liczbaZalogowanychGraczy++;                
            } else {
            	Player p = new Player(listener.accept(),99);
                p.send("FULL");
                p.s.close();
            };
            if (players.size()==iluGraczy) sendAll("TURA" + ";" + currentPlayer+1);
        }
    }


    public static int setCurrentPlayer() {
        currentPlayer = index;
        index++;
        if (index == players.size()) {
            index = 0;
        }
        return currentPlayer;
    }


    public static void sendAll(String msg) {
        for(int i = 0; i<players.size(); i++){
        	players.get(i).send(msg);
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
                    if(Serwer.players.contains(this)) {
                        Serwer.players.remove(this);
                    }
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
        if (received.startsWith("RUCH")) {
            ruch(received);
        } else if (received.startsWith("TURA")){
        	Serwer.setCurrentPlayer();
        	Serwer.sendAll("TURA" + ";" + Serwer.currentPlayer+1);
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