import java.util.ArrayList;

public class AudioBooks {

    private double length;

    protected static ArrayList<AudioBooks> audioBooks = new ArrayList<>();


    public double getLength() {
        return length;
    }

    public void setLength(double l){
        this.length = l;
    }


}
