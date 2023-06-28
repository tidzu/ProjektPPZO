public abstract class KlientBanku {

    private String imie;
    private String nazwisko;

    public KlientBanku(String imie, String nazwisko) {
        this.imie = imie;
        this.nazwisko = nazwisko;
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public abstract void identyfikuj();

    public abstract void dodaj();

    public abstract void aktualizuj();

    public abstract void wyszukaj();

    public abstract void usun();
}
