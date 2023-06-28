import java.io.*;

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
        // Additional logic for adding a client with a payment card
        // For example, adding the client to a file, checking the uniqueness of the card number, etc.
        System.out.println("Dodawanie klienta (karta płatnicza): " + getImie() + " " + getNazwisko() + ", numer karty: " + numerDowodu);

        // Add your specific logic here to add the client with a payment card
        // For example:
        try {
            // 1. Open the file in append mode
            BufferedWriter writer = new BufferedWriter(new FileWriter("idClients.txt", true));

            // 2. Check if the card number is unique
            boolean isUnique = isCardNumberUnique(numerDowodu);
            if (!isUnique) {
                System.out.println("Error: Card number already exists in the file. Please choose a different card number.");
                // Handle the error condition as per your application's requirements
                writer.close(); // Close the file writer
                return;
            }

            // 3. If unique, write the client details (name, card number) to the file
            writer.write(getImie() + "," + getNazwisko() + "," + numerDowodu);
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

        // Implement the logic to remove the client from the system
        // For example, delete the client's record from a database or remove their file

        // Add your specific logic here to remove the client with a payment card
        // For example:
        try {
            // Open the file in read mode
            BufferedReader reader = new BufferedReader(new FileReader("idClients.txt"));

            // Create a temporary file to store the updated content
            File tempFile = new File("tempFile.txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            // Read each line from the file and check if the card number matches
            String line;
            boolean found = false;
            while ((line = reader.readLine()) != null) {
                String[] clientData = line.split(",");
                if (clientData.length >= 3 && clientData[2].equals(numerDowodu)) {
                    found = true;
                    continue; // Skip the line with the matching card number
                }
                writer.write(line);
                writer.newLine();
            }

            reader.close();
            writer.close();

            // Replace the original file with the temporary file
            if (found) {
                File originalFile = new File("idClients.txt");
                tempFile.renameTo(originalFile);
                System.out.println("Client with payment card removed successfully.");
            } else {
                System.out.println("Error: Client with the specified card number not found.");
            }

        } catch (IOException e) {
            e.printStackTrace();
            // Handle any file I/O errors here
        }
    }

    // Helper method to check if the card number is unique in the file
    private boolean isCardNumberUnique(String idNumber ) throws IOException {
        java.nio.file.Path filePath = java.nio.file.Paths.get("idClients.txt");

        if (java.nio.file.Files.exists(filePath)) {
            // Read each line from the file and check if the card number exists
            try (java.util.stream.Stream<String> lines = java.nio.file.Files.lines(filePath)) {
                return lines.noneMatch(line -> line.contains(idNumber));
            }
        }

        return true; // If the file doesn't exist, consider the card number as unique
    }

}