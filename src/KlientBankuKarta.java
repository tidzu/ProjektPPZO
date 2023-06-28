import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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
        // Additional logic for adding a client with a payment card
        // For example, adding the client to a file, checking the uniqueness of the card number, etc.
        System.out.println("Dodawanie klienta (karta płatnicza): " + getImie() + " " + getNazwisko() + ", numer karty: " + numerKarty);

        // Add your specific logic here to add the client with a payment card
        // For example:
        try {
            // 1. Open the file in append mode
            BufferedWriter writer = new BufferedWriter(new FileWriter("clients.txt", true));

            // 2. Check if the card number is unique
            boolean isUnique = isCardNumberUnique(numerKarty);
            if (!isUnique) {
                System.out.println("Error: Card number already exists in the file. Please choose a different card number.");
                // Handle the error condition as per your application's requirements
                writer.close(); // Close the file writer
                return;
            }

            // 3. If unique, write the client details (name, card number) to the file
            writer.write(getImie() + "," + getNazwisko() + "," + numerKarty);
            writer.newLine();

            // 4. Close the file writer
            writer.close();

            System.out.println("Client with payment card added successfully.");

        } catch (IOException e) {
            e.printStackTrace();
            // Handle any file I/O errors here
        }
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
    // Helper method to check if the card number is unique in the file
    private boolean isCardNumberUnique(String cardNumber) throws IOException {
        java.nio.file.Path filePath = java.nio.file.Paths.get("clients.txt");

        if (java.nio.file.Files.exists(filePath)) {
            // Read each line from the file and check if the card number exists
            try (java.util.stream.Stream<String> lines = java.nio.file.Files.lines(filePath)) {
                return lines.noneMatch(line -> line.contains(cardNumber));
            }
        }

        return true; // If the file doesn't exist, consider the card number as unique
    }

}
