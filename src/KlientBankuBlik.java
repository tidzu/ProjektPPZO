import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class KlientBankuBlik extends KlientBanku {


    private String numerBlik;

    public KlientBankuBlik(String imie, String nazwisko, String numerBlik) {
        super(imie,nazwisko);
        this.numerBlik = numerBlik;
    }

    public String getNumerBlik() {
        return numerBlik;
    }

    public void setNumerBlik(String numerBlik) {
        this.numerBlik = numerBlik;
    }
    @Override
    public void identyfikuj() {
        System.out.println("Identyfikacja klienta (numer BLIK): " + getImie() + " " + getNazwisko() + ", numer dowodu: " + numerBlik);
    }
    @Override
    public void dodaj() {
        System.out.println("Dodawanie klienta (numer BLIK): " + getImie() + " " + getNazwisko() + ", numer BLIK: " + numerBlik);

        try {
            // Sprawdzanie poprawności numeru BLIK
            if (!isBlikNumberValid(numerBlik)) {
                System.out.println("Błąd: Podany numer BLIK jest niepoprawny.");
                return;
            }

            // Sprawdzanie unikalności numeru BLIK
            if (!isBlikNumberUnique(numerBlik)) {
                System.out.println("Błąd: Podany numer BLIK jest już przypisany do innego klienta.");
                return;
            }

            // Dodawanie klienta do pliku tekstowego
            BufferedWriter writer = new BufferedWriter(new FileWriter("blikClients.txt", true));
            writer.write(getImie() + "," + getNazwisko() + "," + numerBlik);
            writer.newLine();
            writer.close();

            System.out.println("Klient został dodany.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void aktualizuj(String newImie, String newNazwisko) {
        System.out.println("Edycja danych klienta (numer BLIK): " + getImie() + " " + getNazwisko() + ", numer BLIK: " + numerBlik);

        try {
            BufferedReader reader = new BufferedReader(new FileReader("blikClients.txt"));
            File tempFile = new File("tempFile.txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String line;
            boolean found = false;
            while ((line = reader.readLine()) != null) {
                String[] clientData = line.split(",");
                if (clientData.length >= 3 && clientData[2].equals(numerBlik)) {
                    found = true;
                    line = newImie + "," + newNazwisko + "," + numerBlik;
                }
                writer.write(line);
                writer.newLine();
            }

            reader.close();
            writer.close();

            if (found) {
                File originalFile = new File("blikClients.txt");

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
                System.out.println("Błąd: Nie znaleziono klienta o podanym numerze BLIK.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void usun() {
        System.out.println("Usuwanie klienta (numer BLIK): " + getImie() + " " + getNazwisko() + ", numer BLIK: " + numerBlik);

        try {
            BufferedReader reader = new BufferedReader(new FileReader("blikClients.txt"));
            File tempFile = new File("tempFile.txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String line;
            boolean found = false;
            while ((line = reader.readLine()) != null) {
                String[] clientData = line.split(",");
                if (clientData.length >= 3 && clientData[2].equals(numerBlik)) {
                    found = true;
                    continue;
                }
                writer.write(line);
                writer.newLine();
            }

            reader.close();
            writer.close();

            if (found) {
                File originalFile = new File("blikClients.txt");

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
                System.out.println("Błąd: Nie znaleziono klienta o podanym numerze BLIK.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void wyszukaj(String imie, String nazwisko) {
        System.out.println("Wyszukiwanie klienta (numer Blik): " + imie + " " + nazwisko);

        try {
            BufferedReader reader = new BufferedReader(new FileReader("blikClients.txt"));
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
                    setNumerBlik(clientData[2]);
                    break;
                }
            }

            reader.close();

            if (found) {
                System.out.println("Znaleziono klienta o podanym imieniu i nazwisku.");
                System.out.println("Imię: " + getImie());
                System.out.println("Nazwisko: " + getNazwisko());
                System.out.println("Numer dowodu: " + getNumerBlik());
            } else {
                System.out.println("Nie znaleziono klienta o podanym imieniu i nazwisku.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void wyszukaj() {
        System.out.println("Wyszukiwanie klienta (numer BLIK): " + "id" + numerBlik);

        try {
            BufferedReader reader = new BufferedReader(new FileReader("blikClients.txt"));
            String line;
            boolean found = false;

            while ((line = reader.readLine()) != null) {
                String[] clientData = line.split(",");
                if (clientData.length >= 3 && clientData[2].equals(numerBlik)) {
                    found = true;
                    setImie(clientData[0]);
                    setNazwisko(clientData[1]);
                    break;
                }
            }

            reader.close();

            if (found) {
                System.out.println("Znaleziono klienta o podanym numerze BLIK.");
                System.out.println("Imię: " + getImie());
                System.out.println("Nazwisko: " + getNazwisko());
            } else {
                System.out.println("Nie znaleziono klienta o podanym numerze BLIK.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isBlikNumberUnique(String blikNumber) throws IOException {
        Path filePath = Paths.get("blikClients.txt");

        if (Files.exists(filePath)) {
            try (Stream<String> lines = Files.lines(filePath)) {
                // Sprawdzanie, czy podany numer BLIK jest już przypisany do innego klienta
                return lines.noneMatch(line -> {
                    String[] clientData = line.split(",");
                    return clientData.length >= 3 && clientData[2].equals(blikNumber);
                });
            }
        }
        return true;
    }

    private boolean isBlikNumberValid(String blikNumber) {
        // Sprawdzanie poprawności numeru BLIK (9 cyfr)
        return blikNumber.matches("[0-9]{9}");
    }
}