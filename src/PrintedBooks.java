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



}
