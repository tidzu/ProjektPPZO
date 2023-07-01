import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public  class KlientBankuDowod extends KlientBanku {
    private String numerDowodu;

    public KlientBankuDowod(String imie, String nazwisko, String numerDowodu) {
        super(imie, nazwisko);
        this.numerDowodu = numerDowodu;
    }

    public String getNumerDowodu() {
        return numerDowodu;
    }

    public void setNumerDowodu(String numerDowodu) {
        if (sprawdzNumerId(numerDowodu)) {
            this.numerDowodu = numerDowodu;
        }
        else{
            System.out.println("Błędny numer ID prawidłowy jest w stylu ABC12345");
            this.numerDowodu = "FAL000000";
        }
    }

    @Override
    public void identyfikuj() {
        System.out.println("Identyfikacja klienta (dowód osobisty): " + getImie() + " " + getNazwisko() + ", numer dowodu: " + numerDowodu);
    }

    @Override
    public void dodaj() {

        System.out.println("Dodawanie klienta (dowód osobisty): " + getImie() + " " + getNazwisko() + ", numer dowodu: " + numerDowodu);
        if (sprawdzNumerId(numerDowodu)) {

            try {
                // Sprawdzanie poprawności danych
                BufferedWriter writer = new BufferedWriter(new FileWriter("idClients.txt", true));

                // Sprawdzanie unikalności numeru dowodu
                boolean isUnique = isCardNumberUnique(numerDowodu);
                if (!isUnique) {
                    System.out.println("Błąd! klient z takim numerem ID już istnieje.");
                    // Handle the error condition as per your application's requirements
                    writer.close(); // Close the file writer
                    return;
                }

                // Dodawanie klienta do pliku tekstowego
                writer.write(getImie() + "," + getNazwisko() + "," + numerDowodu);
                writer.newLine();

                writer.close();

                System.out.println("Klient z podanym numerem dowodu poprawnie dodany do bazy.");

            } catch (IOException e) {
                e.printStackTrace();

            }
        }
        else {
            System.out.println("Błąd! klient z takim numerem ID nie może zostać dodany.");
        }
    }

    @Override
    public void aktualizuj(String newImie, String newNazwisko) {
        System.out.println("Aktualizacja klienta (dowód osobisty): " + getImie() + " " + getNazwisko() + ", numer dowodu: " + numerDowodu);
        System.out.println("Nowe dane (dowód osobisty): " + newImie + " " + newNazwisko + ", numer dowodu: " + numerDowodu);

        try {

            BufferedReader reader = new BufferedReader(new FileReader("idClients.txt"));

            File tempFile = new File("tempFile.txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String line;
            boolean found = false;
            while ((line = reader.readLine()) != null) {
                String[] clientData = line.split(",");
                if (clientData.length >= 3 && clientData[2].equals(numerDowodu)) {
                    found = true;
                    line = newImie + "," + newNazwisko + "," + numerDowodu; // Update the line with new client data
                }
                writer.write(line);
                writer.newLine();
            }

            reader.close();
            writer.close();

            if (found) {
                File originalFile = new File("idClients.txt");

                // Usuwanie oryginalnego pliku
                if (!originalFile.delete()) {
                    System.out.println("Błąd! Nie można usunąć oryginalnego pliku");
                    return;
                }

                // Zmiana nazwy tymczasowego pliku na oryginalną nazwę
                if (!tempFile.renameTo(originalFile)) {
                    System.out.println("Błąd! Nie można zmienić nazwy oryginalnego pliku.");
                    return;
                }

                System.out.println("Dane klienta zaktualizowane pomyślnie.");
            } else {
                System.out.println("Błąd: Nie znaleziono klienta o podanym numerze dowodu.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void wyszukaj() {
        System.out.println("Wyszukiwanie klienta (dowód osobisty): " + "id" + numerDowodu);

        try {
            BufferedReader reader = new BufferedReader(new FileReader("idClients.txt"));
            String line;
            boolean found = false;

            while ((line = reader.readLine()) != null) {
                String[] clientData = line.split(",");
                if (clientData.length >= 3 && clientData[2].equals(numerDowodu)) {
                    found = true;
                    setImie(clientData[0]);
                    setNazwisko(clientData[1]);
                    break;
                }
            }

            reader.close();

            if (found) {
                System.out.println("Znaleziono klienta o podanym numerze dowodu.");
                System.out.println("Imię: " + getImie());
                System.out.println("Nazwisko: " + getNazwisko());
            } else {
                System.out.println("Nie znaleziono klienta o podanym numerze dowodu.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void wyszukaj(String imie, String nazwisko) {
        System.out.println("Wyszukiwanie klienta (dowód osobisty): " + imie + " " + nazwisko);

        try {
            BufferedReader reader = new BufferedReader(new FileReader("idClients.txt"));
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
                    setNumerDowodu(clientData[2]);
                    break;
                }
            }

            reader.close();

            if (found) {
                System.out.println("Znaleziono klienta o podanym imieniu i nazwisku.");
                System.out.println("Imię: " + getImie());
                System.out.println("Nazwisko: " + getNazwisko());
                System.out.println("Numer dowodu: " + getNumerDowodu());
            } else {
                System.out.println("Nie znaleziono klienta o podanym imieniu i nazwisku.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void usun() {
        System.out.println("Usuwanie klienta (dowód osobisty): " + getImie() + " " + getNazwisko() + ", numer dowodu: " + numerDowodu);

        try {
            BufferedReader reader = new BufferedReader(new FileReader("idClients.txt"));


            File tempFile = new File("tempFile.txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String line;
            boolean found = false;
            while ((line = reader.readLine()) != null) {
                String[] clientData = line.split(",");
                if (clientData.length >= 3 && clientData[2].equals(numerDowodu)) {
                    found = true;
                    continue; // Pomiń linię jeśli zgadza się numer id
                }
                writer.write(line);
                writer.newLine();
            }

            reader.close();
            writer.close();

            if (found) {
                File originalFile = new File("idClients.txt");

                // Usuwanie oryginalnego pliku
                if (!originalFile.delete()) {
                    System.out.println("Błąd! nie można usunąć pliku");
                    return;
                }
                // Zmiana nazwy tymczasowego pliku na oryginalną nazwę
                if (!tempFile.renameTo(originalFile)) {
                    System.out.println("Błąd: Nie można zmienić nazwy oryginału.");
                    return;
                }

                System.out.println("Klient z tym numerem ID poprawnie usunięty.");
            } else {
                System.out.println("Błąd Klient z tym numerem ID nie znaleziony.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private boolean isCardNumberUnique(String idNumber) throws IOException {
        Path filePath = Paths.get("idClients.txt");

        if (Files.exists(filePath)) {
            try (Stream<String> lines = Files.lines(filePath)) {
                return lines.noneMatch(line -> {
                    String[] clientData = line.split(",");
                    return clientData.length >= 3 && clientData[2].equals(idNumber);
                });
            }
        }
        return true;
    }
    private boolean sprawdzNumerId(String numerId) {
        // Sprawdzenie długości numeru ID
        if (numerId.length() >= 10) {
            return false;
        }

        // Sprawdzenie pierwszej części (litery)
        String litery = numerId.substring(0, 3);
        if (!litery.matches("[a-zA-Z]{3}")) {
            return false;
        }

        // Sprawdzenie drugiej części (cyfry)
        String cyfry = numerId.substring(3);
        if (!cyfry.matches("[0-9]{3,7}")) {
            return false;
        }

        return true;
    }
}