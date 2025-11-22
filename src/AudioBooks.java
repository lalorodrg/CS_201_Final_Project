import java.util.ArrayList;

public class AudioBooks extends Book {


    //The fields for AudioBook is intialized
    private int length;
    private final double COST_PER_MINUTE = 5;


    //This array list stores objects of Audiobooks
    protected static ArrayList<AudioBooks> audioBooks = new ArrayList<>();


    /** This is the constructor for AudioBooks, the Book constructor is called,
     * the length is set, and the AudioBook object is stored in the audioBook array
     * list.
     */
    public AudioBooks(String title, String author, String genre, double cost, int length){
        super(title,author,genre,cost, "audioBook");
        this.length = length;

        audioBooks.add(this);
    }

    //Getters and setters
    public int getLength() {
        return length;
    }

    public void setLength(int l){
        this.length = l;
    }




    /** getCost is inherited from the Book class, and is implemented in AudioBooks.
     * The method first checks to see if the array list is empty, and if it is,
     * then 0 is return to user. Otherwise the method goes through audioBooks, gets the
     * length of each object and adds it to the totalMinutes. The totalMinutes is then
     * multiplied by the cost per minute which was given to be 5$/min and the value
     * is returned to user.
     * **/
    @Override
    public double getCost() {
        return length*COST_PER_MINUTE;
    }




    /** audioBookAvgLength returns the average length of all audio books.
     * First, the method checks to see if audioBooks is empty, and if it
     * is it returns 0 to user. Otherwise, it goes through the list and
     * adds the length of each object stored to totalLenght. Then totalLength
     * divided by the length of audioBooks and the value is return to user.
     * **/
    public double getAverageLength(){

        double totalLength = 0.0;
        if (audioBooks.isEmpty()){
            return 0;
        }

        for (AudioBooks ab : audioBooks){
            totalLength += ab.getLength();
        }
        return totalLength/audioBooks.size();
    }


   /** getLastThreeBooks returns the last 3 audioBooks stored. First the method
     *  checks to see if audioBooks is empty, and if it is it tells the user and ends.
     * If the size is greater than 3, then the starting index is subtracted by three,
     * other wise it starts at index 0. After that, the last 3 or less books is outputted.
     * **/
    public void displayLast3Audios() {

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
