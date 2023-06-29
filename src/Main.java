import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        KlientBanku klient1 = new KlientBankuDowod("Jan", "Kowalski", "ABC123456");
        klient1.identyfikuj();
        klient1.dodaj();
        klient1.aktualizuj("Jacek", "Placek");

        klient1.wyszukaj();
        klient1.setImie("Jamnusz");
        klient1.setNazwisko("Mr");

        ((KlientBankuDowod) klient1).setNumerDowodu("ASD006009");;
        klient1.dodaj();

        klient1.wyszukaj("Jan","Banach");
        klient1.identyfikuj();
       // klient1.usun();

/*
        KlientBanku klient2 = new KlientBankuKarta("Anna", "Nowak", "1234567890123456");
        klient2.identyfikuj();
        klient2.dodaj();
        klient2.aktualizuj();
        klient2.wyszukaj();
        klient2.usun();
 */

        KlientBanku klient3 = new KlientBankuBlik("Piotr", "Wójcik", "696969696");
        klient3.dodaj();
        klient3.dodaj();
        klient3.identyfikuj();
        klient3.dodaj();
        klient3.aktualizuj("Nowy","klinent");
        klient3.wyszukaj("Kasiarzyna","Kwialska");
        //klient3.usun();


    }
}