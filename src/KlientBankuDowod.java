public  class KlientBankuDowod extends KlientBanku {
    private String numerDowodu;

    public KlientBankuDowod(String imie, String nazwisko, String numerDowodu) {
        super(imie, nazwisko);
        this.numerDowodu = numerDowodu;
    }

    public String getNumerDowodu() {
        return numerDowodu;
    }

    @Override
    public void identyfikuj() {
        System.out.println("Identyfikacja klienta (dowód osobisty): " + getImie() + " " + getNazwisko() + ", numer dowodu: " + numerDowodu);
    }

    @Override
    public void dodaj() {
        System.out.println("Dodawanie klienta (dowód osobisty): " + getImie() + " " + getNazwisko() + ", numer dowodu: " + numerDowodu);
        // Logika dodawania klienta z dowodem osobistym
        // np. dodanie klienta do bazy danych, sprawdzenie unikalności numeru dowodu, itp.
    }

    @Override
    public void aktualizuj() {
        System.out.println("Aktualizacja klienta (dowód osobisty): " + getImie() + " " + getNazwisko() + ", numer dowodu: " + numerDowodu);
        // Logika aktualizacji klienta z dowodem osobistym
        // np. aktualizacja danych w bazie danych, zmiana numeru dowodu, itp.
    }

    @Override
    public void wyszukaj() {
        System.out.println("Wyszukiwanie klienta (dowód osobisty): " + getImie() + " " + getNazwisko() + ", numer dowodu: " + numerDowodu);
        // Logika wyszukiwania klienta z dowodem osobistym
        // np. wyszukanie klienta w bazie danych po numerze dowodu
    }

    @Override
    public void usun() {
        System.out.println("Usuwanie klienta (dowód osobisty): " + getImie() + " " + getNazwisko() + ", numer dowodu: " + numerDowodu);
        // Logika usuwania klienta z dowodem osobistym
        // np. usunięcie klienta z bazy danych na podstawie numeru dowodu
    }
}