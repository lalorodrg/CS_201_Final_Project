import java.util.ArrayList;

public class PrintedBooks extends Book {

    private int pages = 0;
    private static final int COST_PER_PAGE = 10;

    protected static ArrayList<PrintedBooks> printedBooks = new ArrayList<>();


    public PrintedBooks(String title, String author, String genre, double cost, int pages){
        super(title, author, genre, cost, "printedBook");
        this.pages = pages;

        printedBooks.add(this);
    }



    public void setPages(int p){
        this.pages = p;
    }

    public int getPages(){
        return pages;
    }

    /** printBookAvgPages is a method unique to printed books, which returns
     * the average of printed book pages. First the method checks the array list
     * printedBook which stores objects of PrintedBooks to see if it is empty.
     * If it is empty, 0 is returned. If the array list is not empty,
     * the method will go through the list and add up all the pages.
     * The total pages is then divided by the size of the list and the
     * value is returned.
     * **/
    public double printBooksAvgPages(){

        double totalPages = 0.0;
        if (printedBooks.isEmpty()){
            return 0;
        }

        for (PrintedBooks pb : printedBooks){
            totalPages += pb.getPages();
        }
        return totalPages/printedBooks.size();
    }



    /** getCost is a method inherited from BookInterface, implemented in printed books, which returns
     * the cost of printed books, if they cost 10$ per page. First the method checks the array list
     * printedBook which stores objects of PrintedBooks to see if it is empty.
     * If it is empty, 0 is returned. If the array list is not empty,
     * the method will go through the list and add up all the pages.
     * The total pages is then multiplied by 10, the cost per page, and
     * the value is returned to the user.
     * **/
    public double getCost (){
        double totalPages = 0.0;

        if (printedBooks.isEmpty()){
            return 0;
        }

        for (PrintedBooks pb : printedBooks){
            totalPages += pb.getPages();
        }
        return totalPages*COST_PER_PAGE;
    }



    public void getLastThreeBooks() {

        int start = 0;
        if (printedBooks.size() <= 0){
            System.out.println("No books stored.");
            return;
        }

        if (printedBooks.size() > 3){
            start = printedBooks.size() - 3;
        }

        for(int i = start; i < printedBooks.size(); i++){
            System.out.println(printedBooks.get(i));
        }
    }





}
