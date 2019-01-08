import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.Socket;

import static org.junit.Assert.*;

public class SerwerTest{

    private final static int port = 12373;
    private Serwer serwer;

    private Socket s = new Socket();
    private  Player player = new Player(s, 1);


    @Test
    public void init() {
        serwer = new Serwer(port);
        Assert.assertNotNull(serwer);
        Assert.assertTrue(serwer.isrunning);
    }


    @Test
    public void setCurrentPlayer_test() {
        Serwer.currentPlayer = 1;
        Assert.assertEquals(2, Serwer.setCurrentPlayer() );
    }


//    @Test
//    public void listening_test() throws IOException {
//        Serwer.players.add(player);
//        Serwer.players.add(player);
//        Serwer.iluGraczy = 2;
//
//        serwer.listening();
//    }


    @Test
    public void sendAll_test() {
    Serwer.sendAll("Hello World!");
    }


    @Test
    public void exit_test() {
        Serwer.exit(player);
    }

}