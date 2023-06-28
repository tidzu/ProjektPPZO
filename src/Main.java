import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ///KlientBanku klient1 = new KlientBankuDowod("Jan", "Kowalski", "AB123456");
        klient1.identyfikuj();
        klient1.dodaj();
        klient1.aktualizuj();
        klient1.wyszukaj();
        klient1.usun();

        KlientBanku klient2 = new KlientBankuKarta("Anna", "Nowak", "1234567890123456");
        klient2.identyfikuj();
        klient2.dodaj();
        klient2.aktualizuj();
        klient2.wyszukaj();
        klient2.usun();

        KlientBanku klient3 = new KlientBankuBlik("Piotr", "Wójcik", "987654");
        klient3.identyfikuj();
        klient3.dodaj();
        klient3.aktualizuj();
        klient3.wyszukaj();
        klient3.usun();
    }
}