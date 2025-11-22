import java.util.ArrayList;
import java.util.Scanner;

/** EDUARDO RODRIGUEZ
 * CS 201, FALL 2025
 * Final Project: Book Application
 *
 * The purpose of this program create a book storing system that stores audio books
 * and printed books, with their Title, Author, Genre, Cost, Pages, Length, and Book type.
 * The program can display the last 6 books stored, or the last 3 print/audio books stored. Users can
 * also input books individual books to be stored, with the capability of importing and exporting
 * txt files to the database. The program can also display the count of all genres stored, as well as give
 *  the cost of audiobooks if they cost 5$ per minute, or the cost of printed books if they cost 10$
 * per page.The last function of the program is to display the average of pages/length of printed/audio
 * books.
 * **/
public class BookApplicationMenu {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("   Welcome to Book Application System");
        System.out.println("========================================\n");

        boolean running = true;
        while (running) {
            displayMainMenu();
            int choice = getIntInput();

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    displayLastSixBooks();
                    break;
                case 3:
                    displayGenreCount();
                    break;
                case 4:
                    printedBookOptions();
                    break;
                case 5:
                    audioBookOptions();
                    break;
                case 6:
                    exportFile();
                    break;
                case 7:
                    importFile();
                    break;
                case 8:
                    running = false;
                    System.out.println("Thank you for using Book Application System. Goodbye!");
                    break;
                default:
                    System.out.println("ERROR: Invalid choice. Please try again.\n");
            }
        }
        scanner.close();
    }



    private static void displayMainMenu() {
        System.out.println("\n=== Main Menu ===");
        System.out.println("1. Add Book");
        System.out.println("2. Display Last Six Books");
        System.out.println("3. Display Genre Count");
        System.out.println("4. Printed Book Options");
        System.out.println("5. Audio Book Options");
        System.out.println("6. Export Books to File");
        System.out.println("7. Import Books from File");
        System.out.println("8. Exit");
        System.out.print("Choose an option (1-8): ");
    }

    /**
     *
     * This method prompts the user to add a printed or audio book,
     * upon the users selection, the user is prompted to add the basic fields,
     * and depending on the book type selected, the method prompts the
     * type specific field (ie printed=pages, audio=length).
     * Poly: Creates different object types based on user choice.
     * Encap: Uses constructors which handle adding to lists internally.
     * **/
    private static void addBook() {
        System.out.println("\n=== Add Book ===");
        System.out.println("1. Add Printed Book");
        System.out.println("2. Add Audio Book");
        System.out.print("Choose type (1-2): ");

        int choice = getIntInput();

        scanner.nextLine(); // Clear buffer

        System.out.print("Enter title: ");
        String title = scanner.nextLine();

        System.out.print("Enter author: ");
        String author = scanner.nextLine();

        System.out.print("Enter genre: ");
        String genre = scanner.nextLine();

        System.out.print("Enter base cost: ");
        double cost = getDoubleInput();

        if (choice == 1) {
            System.out.print("Enter number of pages: ");
            int pages = getIntInput();
            new PrintedBooks(title, author, genre, cost, pages);
            System.out.println("Printed book added successfully!");

        } else if (choice == 2) {
            System.out.print("Enter length (in mins): ");
            int length = getIntInput();
            new AudioBooks(title, author, genre, cost, length);
            System.out.println("Audio book added successfully!");

        } else {
            System.out.println("ERROR: Invalid choice.");
        }
    }

    /**
     * Displays the last 6 books stored in the application by calling the method
     * implemented in Book class.
     * ABSTRACTION: Uses Book.getLastSixBooks();
     * **/
    private static void displayLastSixBooks() {
        System.out.println("\n=== Last Six Books ===");

        if (Book.allBooks.isEmpty()) {
            System.out.println("No books in collection.");
            return;
        }

        // POLYMORPHISM: Calls getLastSixBooks() which works for all book types
        Book.allBooks.get(0).getLastSixBooks();
    }

    /**
     * This method first checks to see if there are books stored, if not the user is told
     * The method goes through the all books, but checks each book to see if the genre has already been printed,
     * it has been, then the method moves onto the next book in the list. If not, the method once again goes through
     * all the books and adds 1 to the count each time another book has the same genre. It will then print the genre
     * type and number, and repeat this until all books in the list have been looked at.
     * ENCAPSULATION: This method handles the counting and displaying on genres
     *
     * **/
    private static void displayGenreCount() {
        System.out.println("\n=== Genre Count ===");

        if (Book.allBooks.isEmpty()) {
            System.out.println("No books to count by genre.");
            return;
        }


        ArrayList<String> printedGenres = new ArrayList<>();

        for (Book book : Book.allBooks) {
            String genre = book.getGenre();


            boolean alreadyPrinted = false;
            for (String printed : printedGenres) {
                if (printed.equalsIgnoreCase(genre)) {
                    alreadyPrinted = true;
                    break;
                }
            }


            if (!alreadyPrinted) {
                int count = book.getGenreCount();
                System.out.println(genre + ": " + count + " book(s)");
                printedGenres.add(genre);
            }
        }
    }

    /**
     * This method prompts the user with 3 selections and waits for input.
     * Depending the input, the corresponding method for each method is called
     *
     * POLYMORPHISM: Methods specific to PrintedBooks are called
     * ENCAPSULATION: Private method that handles printed book options
     * **/
    private static void printedBookOptions() {
        System.out.println("\n=== Printed Book Options ===");
        System.out.println("1. Display Last 3 Printed Books");
        System.out.println("2. Display Cost of All Printed Books");
        System.out.println("3. Display Average Page Count");
        System.out.print("Choose option (1-3): ");

        int choice = getIntInput();

        switch (choice) {
            case 1:
                displayLastThreePrinted();
                break;
            case 2:
                displayPrintedBooksCost();
                break;
            case 3:
                displayAveragePages();
                break;
            default:
                System.out.println("ERROR: Invalid choice.");
        }
    }

    /**
     * Displays the last 3 printed books.
     * POLYMORPHISM: displayLast3Printed() is specific to PrintedBooks.
     * **/
    private static void displayLastThreePrinted() {
        System.out.println("\n--- Last 3 Printed Books ---");

        if (PrintedBooks.printedBooks.isEmpty()) {
            System.out.println("No printed books in collection.");
            return;
        }

        // POLYMORPHISM: Calls PrintedBooks-specific method
        PrintedBooks.printedBooks.get(0).displayLast3Printed();
    }

    /**
     * Calculates and displays total cost of all printed books.
     * Cost = pages * $10 per page.
     * POLYMORPHISM: getCost(); calculates differently for PrintedBooks vs AudioBooks.
     * **/
    private static void displayPrintedBooksCost() {
        System.out.println("\n--- Total Cost of Printed Books ---");

        if (PrintedBooks.printedBooks.isEmpty()) {
            System.out.println("No printed books in collection.");
            return;
        }

        double totalCost = 0;
        for (PrintedBooks pb : PrintedBooks.printedBooks) {
            totalCost += pb.getCost();  // POLYMORPHISM: getCost() = pages * $10
        }

        System.out.println("Total Cost: $" + String.format("%.2f", totalCost));
    }

    /**
     * Displays average page count for all printed books.
     * ABSTRACTION: getAveragePages() hides the calculation logic.
     * **/
    private static void displayAveragePages() {
        System.out.println("\n--- Average Page Count ---");

        if (PrintedBooks.printedBooks.isEmpty()) {
            System.out.println("No printed books in collection.");
            return;
        }

        double avgPages = PrintedBooks.printedBooks.get(0).getAveragePages();
        System.out.println("Average Pages: " + String.format("%.2f", avgPages));
    }

    /**
     * Displays menu for audio book operations.
     * POLYMORPHISM: Methods specific to AudioBooks are called.
     * ENCAPSULATION: Private method that handles audio book submenu.
     * **/
    private static void audioBookOptions() {
        System.out.println("\n=== Audio Book Options ===");
        System.out.println("1. Display Last 3 Audio Books");
        System.out.println("2. Display Cost of All Audio Books");
        System.out.println("3. Display Average Length");
        System.out.print("Choose option (1-3): ");

        int choice = getIntInput();

        switch (choice) {
            case 1:
                displayLastThreeAudio();
                break;
            case 2:
                displayAudioBooksCost();
                break;
            case 3:
                displayAverageLength();
                break;
            default:
                System.out.println("ERROR: Invalid choice.");
        }
    }

    /**
     * Displays the last 3 audio books.
     * POLYMORPHISM: displayLast3Audios() is specific to AudioBooks.
     * **/
    private static void displayLastThreeAudio() {
        System.out.println("\n--- Last 3 Audio Books ---");

        if (AudioBooks.audioBooks.isEmpty()) {
            System.out.println("No audio books in collection.");
            return;
        }

        // POLYMORPHISM: Calls AudioBooks-specific method
        AudioBooks.audioBooks.get(0).displayLast3Audios();
    }

    /**
     * Calculates and displays total cost of all audio books.
     * Cost = length (minutes) * $5 per minute.
     * POLYMORPHISM: getCost() calculates differently for AudioBooks vs PrintedBooks.
     * **/
    private static void displayAudioBooksCost() {
        System.out.println("\n--- Total Cost of Audio Books ---");

        if (AudioBooks.audioBooks.isEmpty()) {
            System.out.println("No audio books in collection.");
            return;
        }

        double totalCost = 0;
        for (AudioBooks ab : AudioBooks.audioBooks) {
            totalCost += ab.getCost();  // POLYMORPHISM: getCost() = length * $5
        }

        System.out.println("Total Cost: $" + String.format("%.2f", totalCost));
    }

    /**
     * Displays average length (in minutes) for all audio books.
     * ABSTRACTION: getAverageLength() hides the calculation logic.
     * **/
    private static void displayAverageLength() {
        System.out.println("\n--- Average Length ---");

        if (AudioBooks.audioBooks.isEmpty()) {
            System.out.println("No audio books in collection.");
            return;
        }

        double avgLength = AudioBooks.audioBooks.get(0).getAverageLength();
        System.out.println("Average Length: " + String.format("%.2f", avgLength) + " minutes");
    }

    /**ABSTRACTION: BookFileHandler handles all file reading/writing.
     * Exports all books to a file.
     *
     * **/
    private static void exportFile() {
        System.out.print("\nEnter file path to save (e.g., output.txt): ");
        String filePath = scanner.nextLine().trim();
        BookFileHandler.writeToFile(filePath);
    }

    /**ABSTRACTION, This method uses class BookFileHandler prompt the user to
     * enter a file path, where it is store as string filepath and cleaned
     * and finally the loadFile method from the FileHandler is called to
     * load the lines of the file to the allBooks array list.
     */
    private static void importFile() {
        System.out.print("\nEnter file path to load (e.g., books.txt): ");
        String filePath = scanner.nextLine().trim();
        BookFileHandler.loadFile(filePath);
    }

    //This method is used to get the user's menu selection, and includes exception handling, ENCAP
    private static int getIntInput() {
        try {
            int input = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            return input;
        } catch (Exception e) {
            scanner.nextLine(); // Clear invalid input
            return -1; // Return invalid choice to trigger error message
        }
    }

    //ENCAP, This method is used for menu selections that have two selections, such as the print/audio options section.
    private static double getDoubleInput() {
        try {
            double input = scanner.nextDouble();
            scanner.nextLine(); // Consume newline
            return input;
        } catch (Exception e) {
            scanner.nextLine(); // Clear invalid input
            return -1; // Return invalid value
        }
    }
}