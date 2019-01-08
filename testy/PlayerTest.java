import org.junit.Assert;
import org.junit.Test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.StringTokenizer;

import static org.junit.Assert.*;

public class PlayerTest {

    private Socket s = new Socket();
    private Player player;


    @Test
    public void init() {
        player = new Player(s, 1);
        Assert.assertNotNull(player);
    }


    @Test
    public void inputhandler_test() {
        init();
        String received = "OPEN;6;2;3;4";
        player.inputhandler(received);
        Assert.assertEquals("iluGraczy powinno byc = 6", 6, (Object) Serwer.iluGraczy);
        Assert.assertEquals("iluBotow powinno byc = 2", 3, (Object) Serwer.iluBotow);

        received = "RUCH;6;2;3;4";
        player.inputhandler(received);

        received = "TIRA;1;2;3;4";
        player.inputhandler(received);
    }


    @Test
    public void ruch_test() {
        init();
        player.ruch("Hello World!");
    }

//    @Test
//    public void run_test() {
//        init();
//        try {
//            player.is = new DataInputStream(s.getInputStream());
//            player.os = new DataOutputStream(s.getOutputStream());
//        } catch (IOException e) {}
//        player.run();
//    }

}