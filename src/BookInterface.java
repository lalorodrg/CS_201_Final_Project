public interface BookInterface {


    //These 3 methods are inherited by Book, PrintedBooks, and AudioBooks
    //Returns the last 6 books stored
    default void getLastSixBooks(){}

    //Returns the total cost of all books
    double getTotalCost();

    //Returns count of the same genre
    int getGenreCount();

}
