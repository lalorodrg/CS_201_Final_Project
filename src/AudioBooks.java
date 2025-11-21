import java.util.ArrayList;

public class AudioBooks extends Book {

    private int length;
    private final double COST_PER_MINUTE = 5;

    protected static ArrayList<AudioBooks> audioBooks = new ArrayList<>();

    public AudioBooks(String title, String author, String genre, double cost, int length){
        super(title,author,genre,cost, "audioBook");
        this.length = length;

        audioBooks.add(this);
    }


    public int getLength() {
        return length;
    }

    public void setLength(int l){
        this.length = l;
    }

    @Override
    public double getCost() {
        double totalMinutes = 0.0;

        if (audioBooks.isEmpty()){
            return 0;
        }

        for (AudioBooks ab : audioBooks){
            totalMinutes += ab.getLength();
        }
        return totalMinutes*COST_PER_MINUTE;
    }


    public double audioBookAvgLength(){

        double totalLength = 0.0;
        if (audioBooks.isEmpty()){
            return 0;
        }

        for (AudioBooks ab : audioBooks){
            totalLength =+ ab.getLength();
        }
        return totalLength/audioBooks.size();
    }

    public void getLastThreeBooks() {

        int start = 0;
        if (audioBooks.size() <= 0){
            System.out.println("No books stored.");
            return;
        }

        if (audioBooks.size() > 3){
            start = audioBooks.size() - 3;
        }

        for(int i = start; i < audioBooks.size(); i++){
            System.out.println(audioBooks.get(i));
        }
    }
}
