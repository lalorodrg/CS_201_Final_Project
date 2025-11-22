import java.util.ArrayList;


public abstract class Book implements BookInterface {

    //Fields for Book, inheritated by both subclasses
    private String title;
    private String author;
    private String genre;
    private double cost;
    private String bookType;

    //ArrayList that store objects for allBooks, printedBooks, and audioBooks
    protected static ArrayList<Book> allBooks = new ArrayList<>();



    /** Constructor accepts title, author, genre, cost, and book type.
     * The object is then stored in an ArrayList that stores Book objects called allBooks.
     * **/
    public Book(String title, String author, String genre, double cost, String bookType) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.cost = cost;
        this.bookType = bookType;

        allBooks.add(this);
    }


    //Getters for fields
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public double getBaseCost() {
        return cost;
    }

    public String getBookType() {
        return bookType;
    }

    //This abstract class will be implemented in Printed and Audio books
    public abstract double getCost();


    /** getTotalCost is inherited and gets implemented here since it doesn't need any fields
     * from Printed or Audio. When it is called it goes through the allBooks array list
     * and gets the cost of each stored Book object, and adds the cost to the total and returns
     * the value.
     * **/
    @Override
    public double getTotalCost() {

        double total = 0;

        for (int i = 0; i < allBooks.size(); i++) {
            total += allBooks.get(i).getCost();
        }
        return total;
    }


    /** getGenreCount is inherited and implemented in Book since it has access to allBooks list.
     * The method goes through all of the Book objects stored in allBooks, where it compares the
     * genre of the calling object to the genre of the object at each index, and if they are the same genre,
     * the count is incremented and outputted after checking allBooks.
     * **/
    @Override
    public int getGenreCount() {

        int c = 0;
        for(Book i : allBooks){
            if(i.getGenre().equalsIgnoreCase(this.genre)){
                c++;
            }
        }
        return c;
    }



    /** getLastSixBooks is inherited from the interface and is implemented in books
     * as it has access to the array list allBooks, which contains all the book objects
     * stored. This method first sets the starting point in the index to zero,then
     * it checks to see if it has any books stored. If no books are stored it will display
     * that to the user. If the array list has more than six book object stored, then
     * the starting point in the index of the arraylist is the size of the list minus 6.
     * Once the starting index point is known, the method goes through a loop starting at
     * the start value (0 or size of list-6) and display the last six books.
     * */
    @Override
    public void getLastSixBooks() {

        int start = 0;
       if (allBooks.size() <= 0){
           System.out.println("No books stored.");
           return;
       }

       if (allBooks.size() > 6){
           start = allBooks.size() - 6;
       }

       for(int i = start; i < allBooks.size(); i++){
           System.out.println(allBooks.get(i));
       }
    }


    //This is used when getLastXBooks is called to display the results easier.
    @Override
    public String toString() {
        return String.format("%s by %s (%s) | Cost: %.2f | Type: %s",
                title, author, genre, getCost(), bookType);
    }



}








