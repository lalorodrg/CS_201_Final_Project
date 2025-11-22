import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
public class BookFileHandler {

    private static final String CSV_HEADER = "title,author,genre,cost,length,pages,booktype";



        /** loadFile method is a unique method of BookFileHandler, where it
         * will read each line of a file, and check for the File and Exceptions, if either of
         * these are found the error is displayed to user. If there is no issue, then each
         * line check to see if it is empty. Lines that have information have the whitespace trimmed
         * with that information then is processed by the storeLine method.
         * **/
        public static void loadFile(String filePath) {

            String line;
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

                line = reader.readLine(); //reads first header line
                while ((line = reader.readLine()) != null) {
                    if (!line.trim().isEmpty()) {
                        storeLine(line);
                    }
                }
                System.out.println("File loaded!");

            } catch (FileNotFoundException e) {
                System.out.println("ERROR: FileNotFoundException");
            } catch (IOException e) {
                System.out.println("ERROR: IOException");
            }



        }

        /** The clean method is used in BookFileHandler class to clean up strings
         * by removing whitespace, N/A, n/a, and "" from the txt file for fields that
         * are not applicable for that type of book, ie audioBooks does not need or
         * have pages.
         * **/
        private static String clean(String f){
            if(f==null){
                return "";
            }
            return f.trim().replace("N/A","")
                    .replace("n/a", "").replace("\"","");

    }




    //title,author,genre,cost,length,pages, booktype
    /** storeLine method takes a string which contrains a line of text gotten from loadFile,
     * and then stores the individual parts of the fields into a string array, while they
     * are cleaned using the clean method, so no white space or quotes should be stored.
     * After it is store and some variables are parsed back into their original data type,
     * the method checks the book type of that line and call the constructor of the corresponding
     * booktype, storing it into the books respected array list
     * **/
    private static void storeLine (String line){

        String[] bookFields = line.replace("\"","").split(",");

        if (bookFields.length < 7){
            System.out.println("ERROR: Invalid format, (expected 7 fields): " + line);
            return;
        }

        String title = clean(bookFields[0]);
        String author = clean(bookFields[1]);
        String genre = clean(bookFields[2]);
        String costStr = clean(bookFields[3]);
        String lengthStr = clean(bookFields[4]);
        String pagesStr = clean(bookFields[5]);
        String bookType = clean(bookFields[6].toLowerCase());

        //Parse strings into doubles and ints, with a fallback for 0
        double cost = costStr.isEmpty() ? 0 : Double.parseDouble(costStr);
        int length = lengthStr.isEmpty() ? 0 : Integer.parseInt(lengthStr);
        int pages = pagesStr.isEmpty() ? 0 : Integer.parseInt(pagesStr);


        if (bookType.equals("printedbook")){
            new PrintedBooks(title,author,genre,cost,pages);
        } else if (bookType.equals("audiobook")){
            new AudioBooks(title,author,genre,cost,length);
        } else {
            System.out.println("Unknown book type and/or error: " + bookType);
        }


    }


    public static String fileSelect(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter file path: ");
        return scan.nextLine();
    }
    /** This method converts a book object into a CSV format string for file reading.
     * The fields are called and stored, and the method checks to see the book type,
     * and depending on the type, set the length or pages for that object, and returns
     * a CSV-formatted string for file reading.
     *
     * **/
    private static String formatBookForCSV(Book book) {
        String title = book.getTitle();
        String author = book.getAuthor();
        String genre = book.getGenre();
        double baseCost = book.getBaseCost();
        String bookType = book.getBookType();

        //Set as default
        String length = "N/A";
        String pages = "N/A";

        //Checks if book is printed or audio
        if (bookType.equals("printedBook")) {
            PrintedBooks pb = (PrintedBooks) book;
            pages = String.valueOf(pb.getPages());

        } else if (bookType.equals("audioBook")) {
            AudioBooks ab = (AudioBooks) book;
            length = String.valueOf(ab.getLength());
        }

        return String.format("\"%s\",\"%s\",\"%s\",%s,%s,%s,%s",
                title, author, genre, baseCost, length, pages, bookType);
    }






    /** This method exports all books into a CSV formated txt file containing
     * all fields. It checks to see if the array containing the book is empty
     * and tells the user if that is the case. Otherwise it begin creating a
     * file with the first line as a header, which is the title of all the fields
     * Once that is done, the method goes through all books and follows the same method
     * , calling each object in the array, formatting it to the CSV format, and writes
     * it to the file. It checks for exceptions as well.
     * */
    public static void writeToFile(String filePath) {
        if (Book.allBooks.isEmpty()) {
            System.out.println("No books to save.");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Write CSV header
            writer.write(CSV_HEADER);
            writer.newLine();

            // Write each book in CSV format
            for (Book book : Book.allBooks) {
                String line = formatBookForCSV(book);
                writer.write(line);
                writer.newLine();
            }

            System.out.println("File saved successfully to: " + filePath);

        } catch (IOException e) {
            System.out.println("ERROR: Failed to write to file: " + e.getMessage());
        }
    }


}
