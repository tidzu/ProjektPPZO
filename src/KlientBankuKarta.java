import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class KlientBankuKarta extends KlientBanku {

    private String numerKarty;

    public KlientBankuKarta(String imie, String nazwisko, String numerKarty) {
        super(imie, nazwisko);
        this.numerKarty = numerKarty;
    }

    public String getNumerKarty() {
        return numerKarty;
    }

    public void setNumerKarty(String numerKarty) {
        this.numerKarty = numerKarty;
    }

    @Override
    public void identyfikuj() {
        System.out.println("Identyfikacja klienta (numer karty bankowej): " + getImie() + " " + getNazwisko() + ", numer karty: " + numerKarty);
    }

    @Override
    public void dodaj() {
        System.out.println("Dodawanie klienta (numer karty bankowej): " + getImie() + " " + getNazwisko() + ", numer karty: " + numerKarty);

        try {
            // Sprawdzanie poprawności numeru karty bankowej
            if (!isCardNumberValid(numerKarty)) {
                System.out.println("Błąd: Podany numer karty bankowej jest niepoprawny.");
                return;
            }

            // Sprawdzanie unikalności numeru karty bankowej
            if (!isCardNumberUnique(numerKarty)) {
                System.out.println("Błąd: Podany numer karty bankowej jest już przypisany do innego klienta.");
                return;
            }

            // Dodawanie klienta do pliku tekstowego
            BufferedWriter writer = new BufferedWriter(new FileWriter("kartaClients.txt", true));
            writer.write(getImie() + "," + getNazwisko() + "," + numerKarty);
            writer.newLine();
            writer.close();

            System.out.println("Klient został dodany.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void aktualizuj(String newImie, String newNazwisko) {
        System.out.println("Edycja danych klienta (numer karty bankowej): " + getImie() + " " + getNazwisko() + ", numer karty: " + numerKarty);

        try {
            BufferedReader reader = new BufferedReader(new FileReader("kartaClients.txt"));
            File tempFile = new File("tempFile.txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String line;
            boolean found = false;
            while ((line = reader.readLine()) != null) {
                String[] clientData = line.split(",");
                if (clientData.length >= 3 && clientData[2].equals(numerKarty)) {
                    found = true;
                    line = newImie + "," + newNazwisko + "," + numerKarty;
                }
                writer.write(line);
                writer.newLine();
            }

            reader.close();
            writer.close();

            if (found) {
                File originalFile = new File("kartaClients.txt");

                // Usuwanie oryginalnego pliku
                if (!originalFile.delete()) {
                    System.out.println("Błąd! Nie można usunąć oryginalnego pliku.");
                    return;
                }

                // Zmiana nazwy tymczasowego pliku na oryginalną nazwę
                if (!tempFile.renameTo(originalFile)) {
                    System.out.println("Błąd! Nie można zmienić nazwy oryginalnego pliku.");
                    return;
                }

                System.out.println("Dane klienta zostały zaktualizowane.");

            } else {
                System.out.println("Błąd: Nie znaleziono klienta o podanym numerze karty bankowej.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void usun() {
        System.out.println("Usuwanie klienta (numer karty bankowej): " + getImie() + " " + getNazwisko() + ", numer karty: " + numerKarty);

        try {
            BufferedReader reader = new BufferedReader(new FileReader("kartaClients.txt"));
            File tempFile = new File("tempFile.txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String line;
            boolean found = false;
            while ((line = reader.readLine()) != null) {
                String[] clientData = line.split(",");
                if (clientData.length >= 3 && clientData[2].equals(numerKarty)) {
                    found = true;
                    continue;
                }
                writer.write(line);
                writer.newLine();
            }

            reader.close();
            writer.close();

            if (found) {
                File originalFile = new File("kartaClients.txt");

                // Usuwanie oryginalnego pliku
                if (!originalFile.delete()) {
                    System.out.println("Błąd! Nie można usunąć oryginalnego pliku.");
                    return;
                }

                // Zmiana nazwy tymczasowego pliku na oryginalną nazwę
                if (!tempFile.renameTo(originalFile)) {
                    System.out.println("Błąd! Nie można zmienić nazwy oryginalnego pliku.");
                    return;
                }

                System.out.println("Klient został usunięty.");
            } else {
                System.out.println("Błąd: Nie znaleziono klienta o podanym numerze karty bankowej.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void wyszukaj(String imie, String nazwisko) {
        System.out.println("Wyszukiwanie klienta (numer karty bankowej): " + imie + " " + nazwisko);

        try {
            BufferedReader reader = new BufferedReader(new FileReader("kartaClients.txt"));
            String line;
            boolean found = false;

            while ((line = reader.readLine()) != null) {
                String[] clientData = line.split(",");
                String klientImie = clientData[0];
                String klientNazwisko = clientData[1];
                if (klientImie.equalsIgnoreCase(imie) && klientNazwisko.equalsIgnoreCase(nazwisko)) {
                    found = true;
                    setImie(klientImie);
                    setNazwisko(klientNazwisko);
                    setNumerKarty(clientData[2]);
                    break;
                }
            }

            reader.close();

            if (found) {
                System.out.println("Znaleziono klienta o podanym imieniu i nazwisku.");
                System.out.println("Imię: " + getImie());
                System.out.println("Nazwisko: " + getNazwisko());
                System.out.println("Numer karty bankowej: " + getNumerKarty());
            } else {
                System.out.println("Błąd: Nie znaleziono klienta o podanym imieniu i nazwisku.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void wyszukaj() {
        System.out.println("Wyszukiwanie klienta po numerze karty bankowej: " + "id" + numerKarty);

        try {
            BufferedReader reader = new BufferedReader(new FileReader("kartaClients.txt"));
            String line;
            boolean found = false;

            while ((line = reader.readLine()) != null) {
                String[] clientData = line.split(",");
                if (clientData.length >= 3 && clientData[2].equals(numerKarty)) {
                    found = true;
                    setImie(clientData[0]);
                    setNazwisko(clientData[1]);
                    break;
                }
            }

            reader.close();

            if (found) {
                System.out.println("Znaleziono klienta o podanym numerze karty.");
                System.out.println("Imię: " + getImie());
                System.out.println("Nazwisko: " + getNazwisko());
            } else {
                System.out.println("Nie znaleziono klienta o podanym numerze karty.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private boolean isCardNumberValid(String cardNumber) {
        if (cardNumber.length() != 16) {
            return false;
        }

        try {
            Long.parseLong(cardNumber);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isCardNumberUnique(String cardNumber) {
        try (Stream<String> lines = Files.lines(Paths.get("kartaClients.txt"))) {
            return lines.noneMatch(line -> {
                String[] clientData = line.split(",");
                return clientData.length >= 3 && clientData[2].equals(cardNumber);
            });
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}