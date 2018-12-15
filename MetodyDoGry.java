public interface MetodyDoGry {

    int ktora_tura();

    Gracz czyja_tura();

    void skonczylem();

    ParaWspolrzednych wykonaj_pierwszy_podruch(int x1, int y1, int x2, int y2);

    ParaWspolrzednych wykonaj_zwykly_podruch(ParaWspolrzednych stara, int x2, int y2);

    void wykonaj_ruch();

    //boolean czy_wygral();


}
