public interface MetodyDoGry {

    int ktora_tura();

    Gracz czyja_tura();

    void skonczylem();

    void policz_nowe_ruchy(int x, int y);
    
    void wykonaj_ruch(int x, int y);

    boolean czy_wygral();


}
