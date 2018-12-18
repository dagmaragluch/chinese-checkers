import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class Serwer {

    private final static int port = 12345;
    ArrayList<Player> players = new ArrayList<>();
    ServerSocket listener;
    private boolean isrunning = false;

    PoczatkoweUstawienia poczatkoweUstawienia = new PoczatkoweUstawienia();
    int currentPlayer;
    int liczbaZalogowanychGraczy = 0;
    int index = 0;


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
            if (players.isEmpty()) {
                players.add(new Player(listener.accept(), liczbaZalogowanychGraczy));
                players.get(liczbaZalogowanychGraczy).start();
                liczbaZalogowanychGraczy++;
            } else if (players.size() <= poczatkoweUstawienia.iluGraczy) {     //w przyszlości od iluGraczy-ileBotow
                players.add(new Player(listener.accept(), liczbaZalogowanychGraczy));
                players.get(liczbaZalogowanychGraczy).start();
                liczbaZalogowanychGraczy++;
            }

        }
    }


    public int setCurrentPlayer() {
        currentPlayer = index;
        index++;
        if (index == players.size()) {
            index = 0;
        }
        return currentPlayer;
    }


    public void sendAll() {
        for(int i = 0; i<players.size(); i++){

        }
    }

}


/*****************************/

class Player extends Thread {
    private final static int port = 12345;
    Serwer serwer = new Serwer(port);

    private int id;
    private boolean czyZalogowany;
    private DataInputStream is;
    private DataOutputStream os;
    public Socket s;

    boolean czyWywolanoPoczUst = false;
    PoczatkoweUstawienia poczatkoweUstawienia = new PoczatkoweUstawienia();

   // Gra gra = new Gra(poczatkoweUstawienia.iluGraczy);


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
        while (true) {

            if (id == serwer.setCurrentPlayer()) {
                try {
                    if (!czyWywolanoPoczUst) {     //dodać Player id
                        //i tu poczatkowe ustawienia wrzucic
                    }
                    received = is.readUTF();
                    inputhandler(received);
                    serwer.setCurrentPlayer();

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }


    private void inputhandler(String received) {
        if (received.equals("START")) {
        } else if (received.equals("RUCH")) {
            ruch(received);
        }   //i to rozbudowac, przrobic itd.
    }


    public void ruch(String received) {
        StringTokenizer st = new StringTokenizer(received, ";");
        st.nextToken();
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        //gra.wykonaj_ruch(x, y);
    }


    public void send(String msg) {
        try {
            os.writeUTF(msg);
            os.flush();
        } catch (IOException ex) {
            ex.printStackTrace();

        }


    }


}