public class KlientBankuKarta extends KlientBanku {
    private String numerKarty;

    public KlientBankuKarta(String imie, String nazwisko, String numerKarty) {
        super(imie, nazwisko);
        this.numerKarty = numerKarty;
    }

    public String getNumerKarty() {
        return numerKarty;
    }

    @Override
    public void identyfikuj() {
        System.out.println("Identyfikacja klienta (karta płatnicza): " + getImie() + " " + getNazwisko() + ", numer karty: " + numerKarty);
    }

    @Override
    public void dodaj() {
        System.out.println("Dodawanie klienta (karta płatnicza): " + getImie() + " " + getNazwisko() + ", numer karty: " + numerKarty);
        // Logika dodawania klienta z kartą płatniczą
        // np. dodanie klienta do bazy danych, sprawdzenie unikalności numeru karty, itp.
    }

    @Override
    public void aktualizuj() {
        System.out.println("Aktualizacja klienta (karta płatnicza): " + getImie() + " " + getNazwisko() + ", numer karty: " + numerKarty);
        // Logika aktualizacji klienta z kartą płatniczą
        // np. aktualizacja danych w bazie danych, zmiana numeru karty, itp.
    }

    @Override
    public void wyszukaj() {
        System.out.println("Wyszukiwanie klienta (karta płatnicza): " + getImie() + " " + getNazwisko() + ", numer karty: " + numerKarty);
        // Logika wyszukiwania klienta z kartą płatniczą
        // np. wyszukanie klienta w bazie danych po numerze karty
    }

    @Override
    public void usun() {
        System.out.println("Usuwanie klienta (karta płatnicza): " + getImie() + " " + getNazwisko() + ", numer karty: " + numerKarty);
        // Logika usuwania klienta z kartą płatniczą
        // np. usunięcie klienta z bazy danych na podstawie numeru karty
    }
}
