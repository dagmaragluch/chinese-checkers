import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class GraczTest {

    Gracz gracz;

    @Test
    public void tworzenieGracza_test() {
        ArrayList<ParaWspolrzednych> arrayList = new ArrayList<>();
        gracz = new Gracz(1, arrayList, false, false, arrayList);

        Assert.assertNotNull("powinnien stworzyc gracza", gracz);
    }


    @Test
    public void getKolorGracza_test() {
        tworzenieGracza_test();
        Assert.assertEquals("kolor gracza powinnien byc 1", 1, gracz.getKolorGracza());
    }

}