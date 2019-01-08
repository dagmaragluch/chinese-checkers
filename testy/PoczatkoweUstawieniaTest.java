import org.junit.Assert;
import org.junit.Test;


public class PoczatkoweUstawieniaTest extends Plansza {

    private PoczatkoweUstawienia poczatkoweUstawienia = new PoczatkoweUstawienia();


    @Test
    public void ustawPionki_test1() {
        poczatkoweUstawienia.ustawPionki(6);
        Assert.assertEquals("zle usupelnienie tablicy", 1, poczatkoweUstawienia.getZawartoscTablicy(0, 12));
        Assert.assertEquals("zle usupelnienie tablicy", 3, poczatkoweUstawienia.getZawartoscTablicy(11, 23));
        Assert.assertEquals("zle usupelnienie listy graczy", 4, poczatkoweUstawienia.listaGraczy.get(3).getKolorGracza());
    }

    @Test
    public void ustawPionki_test2() {
        poczatkoweUstawienia.ustawPionki(4);
        Assert.assertEquals("zle usupelnienie tablicy", 2, poczatkoweUstawienia.getZawartoscTablicy(12, 24));
        Assert.assertEquals("zle usupelnienie listy graczy", 3, poczatkoweUstawienia.listaGraczy.get(2).getKolorGracza());
    }


    @Test
    public void ustawPionki_test3() {
        poczatkoweUstawienia.ustawPionki(5);
        Assert.assertTrue("lista graczy powinna byc pusta", poczatkoweUstawienia.listaGraczy.isEmpty());
    }


}