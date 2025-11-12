import java.util.ArrayList;


ArrayList<String> allBooks = new ArrayList<String>();

public abstract class Book implements BookInterface {
    private String title;
    private String author;
    private String genre;
    private Double BaseCost;

    public Book (String title, String author, String genre, Double cost){
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.BaseCost = cost;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public Double getBaseCost() {
        return BaseCost;
    }

}
