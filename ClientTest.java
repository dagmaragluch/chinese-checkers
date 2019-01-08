import javafx.stage.Stage;
import org.junit.Assert;
import org.junit.Test;


public class ClientTest {

    Client client = new Client();


    @Test
    public void init_test() {
        client.init();

        Assert.assertNotNull(client.s);
        Assert.assertNotNull(Client.dis);
        Assert.assertNotNull(Client.dos);
    }

    @Test
    public void createreadthread_test() {
    }

//    @Test
//    public void poruszboty_test() {
//        client.ilegraczy = 6;
//        client.ilebotow = 2;
//
//        client.poruszboty();
//    }


    @Test
    public void inputhandler_test() {
        client.info = "START;1;6;2;6;4" ;
        client.inputhandler();
        Assert.assertEquals("mojkolor powinno byc = 1", 1, (Object) client.mojkolor);
        Assert.assertEquals("ileGraczy powinno byc = 6", 6, (Object) client.ilegraczy);
        Assert.assertEquals("ileBotow powinno byc = 2", 2, (Object) client.ilebotow);

        client.info = "TURA;1;6;2;6;4" ;
        client.inputhandler();
        Assert.assertEquals("czyjatura powinno byc = 1", 1, (Object) Client.czyjaTura);

    }


//    @Test
//    public void zbudujplansze_test() {
//        client.mojkolor = 1;
//        client.ilegraczy = 6;
//        client.ilebotow = 2;
//
//        client.zbudujplansze();
//        Assert.assertNotNull("powinnien stworyc plansze", client.board);
//    }

//    @Test
//    public void nowyruch_test() {
////        client.nowyruch("T;1;6;2;6;4");
////        System.out.println("abc = " + client.board.betaSerwer.plansza.getZawartoscTablicy(Integer.parseInt(client.st.nextToken()), Integer.parseInt(client.st.nextToken())));
//    }


    @Test
    public void mojatura_test() {
        Client.czyjaTura = 2;
        client.mojkolor = 2;
        client.mojatura();
        Assert.assertTrue("powinno byc true", client.mojatura());
    }


}