public class KlientBankuBlik extends KlientBanku {
    private String numerBlik;

    public KlientBankuBlik(String imie, String nazwisko, String numerBlik) {
        super(imie, nazwisko);
        this.numerBlik = numerBlik;
    }

    public String getNumerBlik() {
        return numerBlik;
    }

    @Override
    public void identyfikuj() {
        System.out.println("Identyfikacja klienta (BLIK): " + getImie() + " " + getNazwisko() + ", numer BLIK: " + numerBlik);
    }

    @Override
    public void dodaj() {
        System.out.println("Dodawanie klienta (BLIK): " + getImie() + " " + getNazwisko() + ", numer BLIK: " + numerBlik);
        // Logika dodawania klienta z BLIKiem
        // np. dodanie klienta do bazy danych, sprawdzenie unikalności numeru BLIK, itp.
    }

    @Override
    public void aktualizuj(String newImie, String newNazwisko) {
        System.out.println("Aktualizacja klienta (BLIK): " + getImie() + " " + getNazwisko() + ", numer BLIK: " + numerBlik);
        // Logika aktualizacji klienta z BLIKiem
        // np. aktualizacja danych w bazie danych, zmiana numeru BLIK, itp.
    }

    @Override
    public void wyszukaj() {
        System.out.println("Wyszukiwanie klienta (karta płatnicza): "+ "imie" + getImie() + " " + getNazwisko() + ", numer dowodu: " + numerBlik);
        // Logika wyszukiwania klienta z dowodem osobistym
        // np. wyszukanie klienta w bazie danych po numerze dowodu
    }
    @Override
    public void wyszukaj(String imie,String nazwisko) {
        System.out.println("Wyszukiwanie klienta (karta płatnicza): "+ "imie" + getImie() + " " + getNazwisko() + ", numer dowodu: " + numerBlik);
        // Logika wyszukiwania klienta z dowodem osobistym
        // np. wyszukanie klienta w bazie danych po numerze dowodu
    }
    @Override
    public void usun() {
        System.out.println("Usuwanie klienta (BLIK): " + getImie() + " " + getNazwisko() + ", numer BLIK: " + numerBlik);
        // Logika usuwania klienta z BLIKiem
        // np. usunięcie klienta z bazy danych na podstawie numeru BLIK
    }
}